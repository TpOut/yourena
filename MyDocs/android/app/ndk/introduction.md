因为现在除了java 还有kotlin ，所以整体称之为 managed code



NDk 最终构建`.so` 库文件（shared）。中间可能会有`.a` 库文件（static）作为其他库的链接依赖。  

这个文档用的示例是ndk-build，但是和CMake 类似的，配置部分有ABI -- 默认所有, toolchain -- 默认Clang，Mode -- 默认release，STL -- 默认系统  

apk 还有专门的Obb 文件路径  



jni 开发指导：

- 减少传递的数据量
- 尽量不要使用不同语种的异步交互，比较容易控制（即最好java 自身异步，调用的c 在某个线程中同步即可）    
- 减少线程的直接接触（如果java 和c 都必须有线程池，也最好通过线程池的持有者进行交互，而不是对单独的线程直接交互）  
- 简化接口代码，并放在容易识别的地方  



JavaVM 和JNIEnv，两者都是指向函数表指针的指针。（c++ 版本中，they're classes with a pointer to a function table and a member function for each JNI function that indirects through the table.）  

JavaVM 主要是”invocation interface“ 方法。JNIEnv 则提供了大部分JNI 函数。    

理论上一个进程可以有多个jvm，但是Android 只能有一个  

JNIEnv 是线程本地存储，如果其他代码没有办法获取JNIEnv（理解为其他线程），那么就共享JavaVM，然后使用`GetEnv` 来获取当前线程的JNIEnv，同时注意AttachCurrentThread  

c 和c++ 的`jni.h` 不一样，所以最好不要在头文件中包含`JNIEnv` 参数  



**threads**

所有的线程都是linux 线程，被kernel 管理。一般性代码中是`Thread.start()`，但是可以在任何地方创建，并且`attatch` 到`JavaVM` 。如`pthread_create()` 或`std::thread` 创建之后，调用`AttachCurrentThread()` 。attach 之后，便有了JNIEnv。  

用`Thread.start` 的好处是有足够栈空间，且拥有正确的`ThreadGroup`，相同的`ClassLoader` ，易于设置线程名 -- 即便于管理。  

而native attach的时候会创建一个`java.lang.Thread` 对象，并且添加到`main` 这个`ThreadGroup` 。   

Android 不会挂起正在执行native 代码的线程。比如gc 正在进行，或者调试器请求挂起，Android 会在下次调用JNI 时暂停。  

在通过JNI attach 的线程退出之前，还必须调用`DetachCurrentThread()` 



**方法调用**

`FindClass` 获取类对象，`GetFieldID` 获取变量ID，`GetIntField` 获取变量  

类卸载会引起引用的变化，所以`jclass` 需要用`NewGlobalRef` 来保护其免受回收。  

也可以在类静态代码块中执行逻辑，来获取新的引用  



**本地和全局引用**

JNI 中几乎所有的对象，包括传递的参数和返回值，都是本地引用。  

即`jobject` 的子对象，包括`jclass`, `jstring`, `jarray` 都是这样  

如果要延长引用的生命周期，则提升为全局引用（`NewGlobalRef` and `NewWeakGlobalRef`.）  

```c++
jclass localClass = env->FindClass("MyClass");
jclass globalClass = reinterpret_cast<jclass>(env->NewGlobalRef(localClass));
```

JNI 方法都可以接受本地和全局引用作为参数，很可能同一个对象的引用指向不同的值。比如同一个对象，连续调用两次`NewGlobalRef` 产生的值可能就不一样。需要用`IsSameObject` 进行比较引用是否指向同一个对象。

因此无法假设对象引用 是常量或是唯一的。对象在native 中使用32位 表示，可能同一个方法的两次返回值是不同的，也可能两个对象的值时一样的。所以不要使用`jobject` 值作为key  

对于大对象，要记住早点手动回收。  

可以使用`EnsureLocalCapacity` ,`PushLocalFrame` 确认和拓展局部变量数  

`jfieldID` 和`jmethodID` 是opaque 类型，不是对象引用，所以不能被提升为全局引用  

raw 数据指针（如`GetStringUTFChars` 和 `GetByteArrayElements` 返回的，也不是对象引用），可以在线程之间传递  

另一个需要注意的是，`AttachCurrentThread` 的native 线程中，本地引用自动释放是在线程detach 的时候进行的。  



**UTF-8 和UTF-16 字符串**

java 使用UTF-16，为了方便，JNI 还提供了 Modified UTF-8 。后者把`\u0000` 解析成`0xc0` `0x80`，而不是`0x00`。这样可以更好的和c-style 的字符串交互（0作为字符串中断）。缺点是不完全适应UTF-8（cannot pass arbitrary UTF-8 data to JNI and expect it to work correctly）  

如果可以，最好使用UTF-16 字符串，即`GetStringChars` . 它不需要进行额外的拷贝。而  

`GetStringUTFChars` 需要分配操作和对UTF-8 的转化操作。但是UTF-16 不是zero-terminated，且`\u0000` 是允许的，所以你要另外持有字符串长度的变量以及 jchar 指针  

一定要记得释放`Get` 获取的字符串。`jchar*` 和 `jbyte*` 是 C-sytle 指向基本数据 的指针，即不是本地引用，不会在方法返回时被释放  

传递给`NewStringUTF` 的必须是`Modified UTF-8` 格式（7-bit ASCII 也可，是子集）  



**基本数组**

必须释放 `Get...ArrayElements` 获取的数组  

在获取的时候，还有一个`isCopy` 参数，可以获悉是否拷贝。我们称拷贝为copy，否则是pin  

释放的时候有个`mode` 参数，对应pin 或copy 有不一样的行为： 

- `0`：pin 时会对数组对象un-pin；copy 时会将数据拷贝回原数组，buffer 被释放

- `JNI_COMMIT`： pin 时无操作；copy 时数据被拷贝回原数组，buffer 没有被释放

    需要手动释放

- `JNI_ABORT`： pin 时数组对象un-pin，之前的写不会被终止；copy 时数据的修改被对，buffer 被释放  

    避免多余赋值

> JNI 里说如果`*isCopy` 是false ，即不是拷贝的形式获取的数据 执行释放会没有效果。但是在Android 中认为这是个错误。因为 If no copy buffer was allocated, then the original memory must be pinned down and can't be moved by the garbage collector.



**区域（Region）**

一种简化`Get<Type>ArrayElements` 和`GetStringChars` 等的方式，在只想拷贝数据的时候很有用   

```c++
jbyte* data = env->GetByteArrayElements(array, NULL);
if (data != NULL) {
    memcpy(buffer, data, len);
    env->ReleaseByteArrayElements(array, data, JNI_ABORT);
}

//可被简化为
env->GetByteArrayRegion(array, 0, len, buffer);
```

减少方法调用消耗，省略 `Get` 的copy 或者 pin 消耗，避免忘记调用release  



**异常**

异常检测 `ExceptionCheck`, `ExceptionOccurred` ，

在异常待处理（pending）的时候，大部分JNI 函数都是不允许被调用的，原文有一个可调用方法列表。  

 interpreted code 抛出的异常不会展开（unwind）  native 栈帧，Android 也不支持 C++ 异常。JNI 的`Throw` 和 `ThrowNew` 指令只是在当前线程设置一个异常指针  

所以要在可能抛出异常的地方主动检测  

`ExceptionClear`  

没有内置的方法控制 Throwable 对象，如果需要信息，可以获取`getMessage "()Ljava/lang/String;"` 的方法ID ，调用其返回并转化为`GetStringUTFChars`  



**拓展检测**

JNI 只有很少的错误检测，一般性直接导致崩溃  

Android 提供一个 CheckJNI 模式，来增加一些[额外的检测](./https://developer.android.google.cn/training/articles/perf-jni#extended-checking)：

数组分配负数、Bad 指针（bad jarray/jclass/jobject/jstring，或者非空参数传递了空指针）、类名过滤（”java/lang/String“）等

```shell
# 开启CheckJNI 模式

# root 设备
adb shell stop
adb shell setprop dalvik.vm.checkjni true
adb shell start
# 成功展示
D AndroidRuntime: CheckJNI is ON

# 常规设备，需要重启app，重启设备或者设置值为其他值即可关闭
adb shell setprop debug.checkjni 1
# 成功展示
D Late-enabling CheckJNI

# 某个app，在manifest 里配置 android:debuggable 
# Android 构建工具会自动为当前构建类型 添加
```



**本地库**

`System.loadLibary` 加载共享库（min SDK 23 以下的项目可能需要ReLinker）    

可以写在class 的静态初始化中，也可以卸载Appication 初始化中  

为了在运行时找到native 方法，你可以

- 显式通过`RegisterNatives` 注册方法
- 让运行时通过`dlsym` 动态查找

`RegisterNatives` 方式可以让你前置（up-front）检查符号的存在，且在导出时只包含`JNI_OnLoad` 以实现更快更小的共享库。  

而`dlsym`  的优势是少写一些代码  

```java
// 使用RegisterNatives
// 主要是实现 JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved)
// 然后在里面用 RegisterNatives 注册方法
// 最后构建的时候添加参数  -fvisibility=hidden，会只导出`JNI_OnLoad`，以达到更快更小的代码。这样还能减少多个库之间的潜在冲突，但是也同时会减少有用堆栈的输出  
JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    //使用的是loadLibrary 时的ClassLoader 
    jclass c = env->FindClass("com/example/app/package/MyClass");
    if (c == nullptr) return JNI_ERR;

    // Register your class' native methods.
    static const JNINativeMethod methods[] = {
        {"nativeFoo", "()V", reinterpret_cast<void*>(nativeFoo)},
        {"nativeBar", "(Ljava/lang/String;I)Z", reinterpret_cast<void*>(nativeBar)},
    };
    int rc = env->RegisterNatives(c, methods, sizeof(methods)/sizeof(JNINativeMethod));
    if (rc != JNI_OK) return rc;

    return JNI_VERSION_1_6;
}
```

`FindClass` 在别的地方调用时，使用栈顶方法的关联ClassLoader。如果是native 线程且刚attached，那么可能没有设置classLoader，此时会使用系统的ClassLoader。系统的ClassLoader 无法知道你的Application 的classes，所以你无法用它来查找你自己的classes。  

这样得用`JNI_OnLoad` 作为一个快捷的方式去查找和缓存classes：一旦你有一个jclass，可以在任何attached 线程使用。（具体可以看原文FindClass 失败的一些解决）    



**64位考虑**

为了支持64位指针的架构，当保存一个java field 为一个native 结构体的时候使用`long` 而不是`int`



**未支持的功能的向后兼容**

JNI 1.6 的功能都支持，除了`DefineClass` 并没有实现，因为Android 不适用Java bytecodes 和class 文件。所以不能传递二进制class 数据。  

兼容大部分都是Android 4.0之前的，现在没啥必要看了：

Android 8.0 之前，本地引用的数量是有每个版本限制的，而8.0之后无限数量  



FAQ：包含了`byte[]` 和`ByteBuffer[]` 的选择考虑、`FindClass` 的的常见问题和分析解决、`UnsatisfiedLinkError` 的解决分析