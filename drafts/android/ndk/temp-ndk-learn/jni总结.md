### JNI知识总结和使用技巧

#### JNI定义

JNI(Java Native Interface)意为JAVA本地调用，它允许Java代码和其他语言写的代码进行交互。一种在Java虚拟机控制下执行代码的标准机制。

#### 从代码的角度再回顾JNI

组织结构：
![image-20181225135732031](https://ws1.sinaimg.cn/large/006tNbRwly1fylapvqypnj315e0mq0y7.jpg)

JNI函数表的组成就像C++的虚函数表，虚拟机可以运行多张函数表。JNI接口指针仅在当前线程中起作用，指针不能从一个线程进入另一个线程，但可以在不同的线程中调用本地方法。
我们在来看看JNI的头文件：

![image-20181225140011087](https://ws3.sinaimg.cn/large/006tNbRwly1fylapy8dvtj30uu0u078l.jpg)

JNI类型和数据结构：

![image-20181225140502085](https://ws3.sinaimg.cn/large/006tNbRwly1fylapzqs6uj30y90u041n.jpg)

引用类型：

![image-20181225140549436](https://ws2.sinaimg.cn/large/006tNbRwly1fylapwdcztj314u0n442q.jpg)

属性、方法、值类型：

```c++
jni.h 102行
struct _jfieldID;                       /* opaque structure */
typedef struct _jfieldID* jfieldID;     /* field IDs */

struct _jmethodID;                      /* opaque structure */
typedef struct _jmethodID* jmethodID;   /* method IDs */

struct JNIInvokeInterface;

typedef union jvalue {
    jboolean    z;
    jbyte       b;
    jchar       c;
    jshort      s;
    jint        i;
    jlong       j;
    jfloat      f;
    jdouble     d;
    jobject     l;
} jvalue;
```

Type Signatures：

![image-20181225141323294](https://ws3.sinaimg.cn/large/006tNbRwly1fylapxa115j30u014atc2.jpg)

***举一反三***：

```java
String类型的域描述符为 Ljava/lang/String;      
[ + 其类型的域描述符 + ;  
int[ ]     其描述符为[I  
float[ ]   其描述符为[F  
String[ ]  其描述符为[Ljava/lang/String;  
Object[ ]类型的域描述符为[Ljava/lang/Object;  
int  [ ][ ] 其描述符为[[I  
float[ ][ ] 其描述符为[[F 
---------------------------------------------------------------------------------------------
Java层方法                                               JNI函数签名  
String test ( )                                         Ljava/lang/String;  
int f (int i, Object object)                            (ILjava/lang/Object;)I  
void set (byte[ ] bytes)                                ([B)V  
```

#### JNIEnv与JavaVM 

JNIEnv 概念 : 是一个线程相关的结构体, 该结构体代表了 Java 在本线程的运行环境 ; 

JNIEnv 与 JavaVM : 注意区分这两个概念; 

- JavaVM : JavaVM 是 Java虚拟机在 JNI 层的代表, JNI 全局只有一个;
- JNIEnv : JavaVM 在线程中的代表, 每个线程都有一个, JNI 中可能有很多个JNIEnv;

JNIEnv 作用 : 

- 调用 Java 函数 : JNIEnv 代表 Java 运行环境, 可以使用 JNIEnv 调用 Java 中的代码;
- 操作 Java 对象 : Java 对象传入 JNI 层就是 Jobject 对象, 需要使用 JNIEnv 来操作这个 Java 对象;

***总结：***JNIEnv只在当前线程中有效。本地方法不能将JNIEnv从一个线程传递到另一个线程中。相同的 Java 线程中对本地方法多次调用时，传递给该本地方法的JNIEnv是相同的。但是，一个本地方法可被不同的 Java 线程所调用，因此可以接受不同的 JNIEnv。

#### JNI调用流程梳理

/[packages](http://androidxref.com/8.0.0_r4/xref/packages/)/[apps](http://androidxref.com/8.0.0_r4/xref/packages/apps/)/[Bluetooth](http://androidxref.com/8.0.0_r4/xref/packages/apps/Bluetooth/)/[src](http://androidxref.com/8.0.0_r4/xref/packages/apps/Bluetooth/src/)/[com](http://androidxref.com/8.0.0_r4/xref/packages/apps/Bluetooth/src/com/)/[android](http://androidxref.com/8.0.0_r4/xref/packages/apps/Bluetooth/src/com/android/)/[bluetooth](http://androidxref.com/8.0.0_r4/xref/packages/apps/Bluetooth/src/com/android/bluetooth/)/[btservice](http://androidxref.com/8.0.0_r4/xref/packages/apps/Bluetooth/src/com/android/bluetooth/btservice/)/[AdapterApp.java](http://androidxref.com/8.0.0_r4/xref/packages/apps/Bluetooth/src/com/android/bluetooth/btservice/AdapterApp.java)

其实要学习JNI的知识做好是Android的源码，我们知道蓝牙、MediaPlayer都使用了JNI技术。大家注意在看源码的时候一定要注意梳理调用流程。

- Java代码会 有System.loadLibrary("****")和相关的native函数。
- 在JNI具体实现的对应的CPP源码中一般会有如下NativeMethod注册函数和具体实现Java代码中对应的native函数。
- JNI_OnLoad函数中进行方法的动态注册，方便C++和Java的调用。（其实在JNI_OnLoad中最终都会调用jniRegisterNativeMethods这个方法。）

```c++
static JNINativeMethod gMethods[] = {
    ······
    {"native_init",         "()V",                              (void *)android_media_MediaPlayer_native_init},
    // 这边是 native_setup ： 第一个 是java函数名，第二个是签名，第三个是 jni具体实现方法的指针
    {"native_setup",        "(Ljava/lang/Object;)V",            (void *)android_media_MediaPlayer_native_setup},
    {"native_finalize",     "()V",                              (void *)android_media_MediaPlayer_native_finalize},
    ······
};

// jni具体实现方法的指针
static void
android_media_MediaPlayer_native_setup(JNIEnv *env, jobject thiz, jobject weak_this)
{
    ALOGV("native_setup");
    sp<MediaPlayer> mp = new MediaPlayer();
    if (mp == NULL) {
        jniThrowException(env, "java/lang/RuntimeException", "Out of memory");
        return;
    }

    // create new listener and give it to MediaPlayer
    sp<JNIMediaPlayerListener> listener = new JNIMediaPlayerListener(env, thiz, weak_this);
    mp->setListener(listener);

    // Stow our new C++ MediaPlayer in an opaque field in the Java object.
    setMediaPlayer(env, thiz, mp);
}

// This function only registers the native methods
static int register_android_media_MediaPlayer(JNIEnv *env)
{
    // gMethods 在这边被调用，系统可以拿到AndroidRuntime:，我们拿不到，只能分析，他注册的时候做了什么事情，
    // 分析： env ，"android/media/MediaPlayer" 是MediaPlayer.java的包名+类名
    // gMethods
    // NELEM(gMethods)算这个结构体数组的占多少个字节，将这个大小放进去(是个宏定义，便于复用)
    // # define NELEM(x) ((int)(sizeof(x) / sizeof((x)[0])))
    // registerNativeMethods 具体实现在AndroidRuntime.cpp 具体见下一段代码
    return AndroidRuntime::registerNativeMethods(env,
                "android/media/MediaPlayer", gMethods, NELEM(gMethods));
}

//  这边重写了jni.h声明的 JNI_OnLoad方法，在JNI_OnLoad中进行注册（register_android_media_MediaPlayer），在注册过程中，声明了一个gMethods的结构体数组，这里面写好了方法映射。而JNI_OnLoad的调用处，就是System.loadLibrary 的时候会走到这里，然后进行动态注册
jint JNI_OnLoad(JavaVM* vm, void* /* reserved */)
{
    JNIEnv* env = NULL;
    jint result = -1;

    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        ALOGE("ERROR: GetEnv failed\n");
        goto bail;
    }
    assert(env != NULL);

    ...
    // register_android_media_MediaPlayer 在这边被调用
    if (register_android_media_MediaPlayer(env) < 0) {
        ALOGE("ERROR: MediaPlayer native registration failed\n");
        goto bail;
    }
    ...

    /* success -- return valid version number */
    result = JNI_VERSION_1_4;

bail:
    return result;
}
```



![image-20181225144805278](https://ws1.sinaimg.cn/large/006tNbRwly1fylapwuqihj31si0t6dn5.jpg)

```c
https://android.googlesource.com/platform/libnativehelper/+/jb-mr1.1-dev-plus-aosp/JNIHelp.cpp#71

extern "C" int jniRegisterNativeMethods(C_JNIEnv* env, const char* className,
    const JNINativeMethod* gMethods, int numMethods)
{
    JNIEnv* e = reinterpret_cast<JNIEnv*>(env);
    ALOGV("Registering %s's %d native methods...", className, numMethods);
    scoped_local_ref<jclass> c(env, findClass(env, className));
    if (c.get() == NULL) {
        char* msg;
        asprintf(&msg, "Native registration unable to find class '%s'; aborting...", className);
        e->FatalError(msg);
    }
    if ((*env)->RegisterNatives(e, c.get(), gMethods, numMethods) < 0) {
        char* msg;
        asprintf(&msg, "RegisterNatives failed for '%s'; aborting...", className);
        e->FatalError(msg);
    }
    return 0;
}
```

#### JNI函数总结

| 函数                                                         | Java 数组类型 | 本地类型 | 说明                                                         |
| ------------------------------------------------------------ | ------------- | -------- | :----------------------------------------------------------- |
| GetBooleanArrayElements                                      | jbooleanArray | jboolean | ReleaseBooleanArrayElements 释放                             |
| GetByteArrayElements                                         | jbyteArray    | jbyte    | ReleaseByteArrayElements 释放                                |
| GetCharArrayElements                                         | jcharArray    | jchar    | ReleaseShortArrayElements 释放                               |
| GetShortArrayElements                                        | jshortArray   | jshort   | ReleaseBooleanArrayElements 释放                             |
| GetIntArrayElements                                          | jintArray     | jint     | ReleaseIntArrayElements 释放                                 |
| GetLongArrayElements                                         | jlongArray    | jlong    | ReleaseLongArrayElements 释放                                |
| GetFloatArrayElements                                        | jfloatArray   | jfloat   | ReleaseFloatArrayElements 释放                               |
| GetDoubleArrayElements                                       | jdoubleArray  | jdouble  | ReleaseDoubleArrayElements 释放                              |
| GetObjectArrayElement                                        | 自定义对象    | object   |                                                              |
| SetObjectArrayElement                                        | 自定义对象    | object   |                                                              |
| GetArrayLength                                               |               |          | 获取数组大小                                                 |
| New<Type>Array                                               |               |          | 创建一个指定长度的原始数据类型的数组                         |
| GetPrimitiveArrayCritical                                    |               |          | 得到指向原始数据类型内容的指针，该方法可能使垃圾回收不能执行，该方法可能返回数组的拷贝，因此必须释放此资源。 |
| ReleasePrimitiveArrayCritical                                |               |          | 释放指向原始数据类型内容的指针，该方法可能使垃圾回收不能执行，该方法可能返回数组的拷贝，因此必须释放此资源。 |
| NewStringUTF                                                 |               |          | jstring类型的方法转换                                        |
| GetStringUTFChars                                            |               |          | jstring类型的方法转换                                        |
| DefineClass                                                  |               |          | 从原始类数据的缓冲区中加载类                                 |
| FindClass                                                    |               |          | 该函数用于加载本地定义的类。它将搜索由CLASSPATH 环境变量为具有指定名称的类所指定的目录和 zip文件 |
| GetObjectClass                                               |               |          | 通过对象获取这个类。该函数比较简单，唯一注意的是对象不能为NULL，否则获取的class肯定返回也为NULL |
| GetSuperclass                                                |               |          | 获取父类或者说超类 。 如果 clazz 代表类class而非类 object，则该函数返回由 clazz 所指定的类的超类。 如果 clazz指定类 object 或代表某个接口，则该函数返回NULL |
| IsAssignableFrom                                             |               |          | 确定 clazz1 的对象是否可安全地强制转换为clazz2               |
| Throw                                                        |               |          | 抛出 java.lang.Throwable 对象                                |
| ThrowNew                                                     |               |          | 利用指定类的消息（由 message 指定）构造异常对象并抛出该异常  |
| ExceptionOccurred                                            |               |          | 确定是否某个异常正被抛出。在平台相关代码调用 ExceptionClear() 或 Java 代码处理该异常前，异常将始终保持抛出状态 |
| ExceptionDescribe                                            |               |          | 将异常及堆栈的回溯输出到系统错误报告信道（例如 stderr）。该例程可便利调试操作 |
| ExceptionClear                                               |               |          | 清除当前抛出的任何异常。如果当前无异常，则此例程不产生任何效果 |
| FatalError                                                   |               |          | 抛出致命错误并且不希望虚拟机进行修复。该函数无返回值         |
| NewGlobalRef                                                 |               |          | 创建 obj 参数所引用对象的新全局引用。obj 参数既可以是全局引用，也可以是局部引用。全局引用通过调用DeleteGlobalRef() 来显式撤消。 |
| DeleteGlobalRef                                              |               |          | 删除 globalRef 所指向的全局引用                              |
| DeleteLocalRef                                               |               |          | 删除 localRef所指向的局部引用                                |
| AllocObject                                                  |               |          | 分配新 Java 对象而不调用该对象的任何构造函数。返回该对象的引用。clazz 参数务必不要引用数组类。 |
| getObjectClass                                               |               |          | 返回对象的类                                                 |
| IsSameObject                                                 |               |          | 测试两个引用是否引用同一 Java 对象                           |
| NewString                                                    |               |          | 利用 Unicode 字符数组构造新的 java.lang.String 对象          |
| GetStringLength                                              |               |          | 返回 Java 字符串的长度（Unicode 字符数）                     |
| GetStringChars                                               |               |          | 返回指向字符串的 Unicode 字符数组的指针。该指针在调用 ReleaseStringchars() 前一直有效 |
| ReleaseStringChars                                           |               |          | 通知虚拟机平台相关代码无需再访问 chars。参数chars 是一个指针，可通过 GetStringChars() 从 string 获得 |
| NewStringUTF                                                 |               |          | 利用 UTF-8 字符数组构造新 java.lang.String 对象              |
| GetStringUTFLength                                           |               |          | 以字节为单位返回字符串的 UTF-8 长度                          |
| GetStringUTFChars                                            |               |          | 返回指向字符串的 UTF-8 字符数组的指针。该数组在被ReleaseStringUTFChars() 释放前将一直有效 |
| ReleaseStringUTFChars                                        |               |          | 通知虚拟机平台相关代码无需再访问 utf。utf 参数是一个指针，可利用 GetStringUTFChars() 获得 |
| NewObjectArray                                               |               |          | 构造新的数组，它将保存类 elementClass 中的对象。所有元素初始值均设为 initialElement |
| Set<PrimitiveType>ArrayRegion                                |               |          | 将基本类型数组的某一区域从缓冲区中复制回来的一组函数         |
| GetFieldID                                                   |               |          | 返回类的实例（非静态）域的属性 ID。该域由其名称及签名指定。访问器函数的 Get<type>Field 及 Set<type>Field系列使用域 ID 检索对象域。GetFieldID() 不能用于获取数组的长度域。应使用GetArrayLength()。 |
| Get<type>Field                                               |               |          | 该访问器例程系列返回对象的实例（非静态）域的值。要访问的域由通过调用GetFieldID() 而得到的域 ID 指定。 |
| Set<type>Field                                               |               |          | 该访问器例程系列设置对象的实例（非静态）属性的值。要访问的属性由通过调用 SetFieldID() 而得到的属性 ID指定。 |
| GetStaticFieldID   GetStatic<type>Field  SetStatic<type>Field |               |          | 同上，只不过是静态属性。                                     |
| GetMethodID                                                  |               |          | 返回类或接口实例（非静态）方法的方法 ID。方法可在某个 clazz 的超类中定义，也可从 clazz 继承。该方法由其名称和签名决定。 GetMethodID() 可使未初始化的类初始化。要获得构造函数的方法 ID，应将<init> 作为方法名，同时将void (V) 作为返回类型。 |
| CallVoidMethod                                               |               |          |                                                              |
| CallObjectMethod                                             |               |          |                                                              |
| CallBooleanMethod                                            |               |          |                                                              |
| CallByteMethod                                               |               |          |                                                              |
| CallCharMethod                                               |               |          |                                                              |
| CallShortMethod                                              |               |          |                                                              |
| CallIntMethod                                                |               |          |                                                              |
| CallLongMethod                                               |               |          |                                                              |
| CallFloatMethod                                              |               |          |                                                              |
| CallDoubleMethod                                             |               |          |                                                              |
| GetStaticMethodID                                            |               |          | 调用静态方法                                                 |
| Call<type>Method                                             |               |          |                                                              |
| RegisterNatives                                              |               |          | 向 clazz 参数指定的类注册本地方法。methods 参数将指定 JNINativeMethod 结构的数组，其中包含本地方法的名称、签名和函数指针。nMethods 参数将指定数组中的本地方法数。 |
| UnregisterNatives                                            |               |          | 取消注册类的本地方法。类将返回到链接或注册了本地方法函数前的状态。该函数不应在常规平台相关代码中使用。相反，它可以为某些程序提供一种重新加载和重新链接本地库的途径。 |

#### JNI字符串、数组处理总结

我们来完成一个小的案列来说明知识点吧。我们在Java层经常使用AES加密算法，服务器、移动端都需要做一套算法，那我们就可以利用JNI来实现一套，给多端调用。闲话不多说，直接上代码。

下面的是JNIUtils类，主要完成so加载、AES算法初始化等等。关注几个静态native函数。

```c++
public class JNIUtils {
    public static native String getStringFormC(); //演示从C++获取字符串
    public static native byte[] getKeyValue();//用于AES加密的密钥也是从C++获取字节数组
    public static native byte[] getIv();//使用CBC模式，需要一个向量iv，可增加加密算法的强度

    //-----演示字符串用法-------
    public native static String sayHello(String text);


    private static byte[]keyValue;
    private static byte[]iv;

    private static SecretKey key;
    private static AlgorithmParameterSpec paramSpec;
    private static Cipher ecipher;

    static {
        //加载动态库
        System.loadLibrary("native-lib");
        //配置AES算法 初始化 这里可以在网上查阅下AES相关的资料
        keyValue = getKeyValue();
        iv = getIv();
        if(null != keyValue && null !=iv) {
            KeyGenerator kgen;
            try {
                kgen = KeyGenerator.getInstance("AES");
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                random.setSeed(keyValue);
                kgen.init(128,random);
                key =kgen.generateKey();
                paramSpec =new IvParameterSpec(iv);
                ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                e.printStackTrace();
            }
        }
    }
    /**加密**/
    public static String encode(String msg) {
        String str ="";
        try {
            //用密钥和一组算法参数初始化此 cipher
            ecipher.init(Cipher.ENCRYPT_MODE,key,paramSpec);
            //加密并转换成16进制字符串
            str = asHex(ecipher.doFinal(msg.getBytes()));
        } catch (BadPaddingException | InvalidAlgorithmParameterException
                | InvalidKeyException | IllegalBlockSizeException ignored) {
        }
        return str;
    }
    /**解密函数**/
    public static String decode(String value) {
        try {
            ecipher.init(Cipher.DECRYPT_MODE,key,paramSpec);
            return new String(ecipher.doFinal(asBin(value)));
        } catch (BadPaddingException | InvalidKeyException | IllegalBlockSizeException
                | InvalidAlgorithmParameterException ignored) {
        }
        return"";
    }
    /**转16进制**/
    private static String asHex(byte buf[]) {
        StringBuffer strbuf =new StringBuffer(buf.length * 2);
        int i;
        for (i = 0;i <buf.length;i++) {
            if (((int)buf[i] & 0xff) < 0x10)//小于十前面补零
                strbuf.append("0");
            strbuf.append(Long.toString((int)buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }
    /**转2进制**/
    private static byte[] asBin(String src) {
        if (src.length() < 1)
            return null;
        byte[]encrypted =new byte[src.length() / 2];
        for (int i = 0;i <src.length() / 2;i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);//取高位字节
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);//取低位字节
            encrypted[i] = (byte) (high * 16 +low);
        }
        return encrypted;
    }
```

我们在来看下关键的C++核心代码：

```c++
#include <jni.h>
#include <string>
#include<android/log.h>

#define LOG    "ndk-jni" // 这个是自定义的LOG的标识
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,LOG,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...)  __android_log_print(ANDROID_LOG_FATAL,LOG,__VA_ARGS__) // 定义LOGF类型

const char keyValue[] = {  //秘钥key
        21, 25, 21, 45, 25, 98, 55, 45, 10, 35, 45, 35,
        26, 5, 25, 65, 78, 99, 85, 45, 5, 10, 0, 11,
        35, 48, 98, 65, 32, 14, 67, 25, 36, 56, 45, 5,
        12, 15, 35, 15, 25, 14, 62, 25, 33, 45, 55, 12, 8
};

const char iv[] =  {    //16bit 增强的iv向量，用于AES算法增强
        33, 32, 25, 25, 35, 27, 55, 12, 15,32,
        23, 45, 26, 32, 5,16
};


extern "C"
JNIEXPORT jstring JNICALL
Java_ndk_jesson_com_ndkdemo_JNIUtils_getStringFormC(JNIEnv *env, jclass type) {
	//NewStringUTF
    return env->NewStringUTF("这是来自C++的原始字符串");
}

/***
 * 获取c++里面的数组jbyte
 */
extern "C"
JNIEXPORT jbyteArray JNICALL
Java_ndk_jesson_com_ndkdemo_JNIUtils_getKeyValue(JNIEnv *env, jclass type) {
    jbyteArray kv = env->NewByteArray(sizeof(keyValue));
    jbyte* bytes = env->GetByteArrayElements(kv,0);

    int i=0;
    for (i;i < sizeof(keyValue); ++i) {
        bytes[i] = keyValue[i];
    }
    env->SetByteArrayRegion(kv,0, sizeof(keyValue),bytes);
    env->ReleaseByteArrayElements(kv,bytes,0);
    return kv;

}

extern "C"
JNIEXPORT jbyteArray JNICALL
Java_ndk_jesson_com_ndkdemo_JNIUtils_getIv(JNIEnv *env, jclass type) {

    jbyteArray ivArray = env->NewByteArray(sizeof(iv));
    jbyte *bytes = env->GetByteArrayElements(ivArray, 0);

    int i;
    for (i = 0; i < sizeof(iv); i++){
        bytes[i] = (jbyte)iv[i];
    }

    env->SetByteArrayRegion(ivArray, 0, sizeof(iv), bytes);
    env->ReleaseByteArrayElements(ivArray,bytes,0);
    return ivArray;
}
```

我们在来重点看下下面几个函数：

![image-20181227142614253](https://ws4.sinaimg.cn/large/006tNbRwly1fylapxv5v3j30zz0u0wlu.jpg)

在jni.h里面有数组定义，jsize代码数组的大小(typedef  jint  jsize;)。那么下面的几个函数你一定要熟悉：

```Java
jbyteArray (*NewBooleanArray)(JNIEnv*, jsize); //初始化数组
jbyte* (*GetByteArrayElements)(JNIEnv*, jbyteArray, jboolean*);//获取数组元素
void (*SetCharArrayRegion)(JNIEnv*, jcharArray, jsize, jsize, const jchar*);//设置数组数据
void (*ReleaseByteArrayElements)(JNIEnv*, jbyteArray, jbyte*, jint);//释放数组占用的内存资源
```

总结一下：在AES的例子中，实现AES是用的Java SDK API。我演示的C++的代码主要是为了演示数组的上面的几个函数的使用。希望通过这篇文章，让你真正的了解了JNI的一些初步的概念，后续我会继续演示JNI的相关重点用法，包括字符串、异常、多线程。欢迎大家继续关注我的NDK开发系列文章。