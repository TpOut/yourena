> https://developer.android.google.cn/ndk/guides  
>
> https://developer.android.google.cn/studio/projects/add-native-code#link-gradle
>
> https://developer.android.google.cn/studio/debug



有NDK 部分和Android Studio 部分，后者更多是实际配置，主要写入项目的NDK 模块里了。  


解析格式：库名`liblog.so`  -> 索引名`log`

native 开发流程简单讲就是编写native 代码（Android 应该特指c/c++），然后将代码链接编译，并合入apk。  

目前AS 支持两种链接编译方式，CMake 和 ndk-build。前者更通用，而后者更快但是只支持Android。目前两种方式不能同时存在于一个项目中。  

> 还有一种ndkCompile 的方式，已经废弃  
>
> 新的方式可以支持CCache

而对于JNI 的支持，需要注意下官方文档指向的是JDK-7 的页面。  



ndk 和构建工具可以在SDK Manager 中获取，包括NDK、CMake、LLDB。    

> AS 3.6.3 下载的时候没有LLDB，但是可以正常调试，估计被合入某个工具里了。  



配置流程：

- （源码）对应创建native 代码的源文件夹，
- （工具）配置cmake/ndk-build， 
- （整合）在build.gradle 里配置路径等

然后java 里写一个native 方法，再在cpp 下写一个c 文件

> c 文件输入java native 方法会自动提示对应的c 方法。

对于没有配置的模块，可以右键模块名，点击`Link C++ Project With Gradle` 快速创建  

如果项目里使用旧版NDk，配置为cmake 前记得去除android.useDeprecatedNdk = true  



**纯粹的native**

需要在Manifest 中，设置`Application`的`android:hasCode="false"` 表示没有java 代码, 并声明一个SDK 里的类 `android.app.NativeActivity` （或继承），在`<meta-data>` 里指定要加载的库名称。  

`native_activity.h` 定义了一个native 版本的NativeActivity，包含了一些需要的回调和数据结构。但是在主线程  

`android_native_app_glue.h`  是基于`native_activity.h` 接口的静态帮助库，但是在另一个线程，以处理回调和输入事件。  

`ANativeActivity_callback` 是需要实现的一些接口，如`ANativeActivity_onCreate` 是进入主程会回调的。  



https://android.googlesource.com/platform/bionic/+/master/android-changes-for-ndk-developers.md



ELF TLS instead of `emutls`

```
libgcc/compiler-rt
```



fdsan 分析fd 的错误拥有  

https://android.googlesource.com/platform/bionic/+/master/docs/fdsan.md  



文本重定位常规解决：  

https://wiki.gentoo.org/wiki/Hardened/Textrels_Guide  



7.0 更新警告：  

`libandroid_runtime.so`, `libcutils.so`, `libcrypto.so`, and `libssl.so`  



查看动态库用到的动态库列表：

```
aarch64-linux-android-readelf -dW libMyLibrary.so
```



属性获取：

```
#include <sys/system_properties.h>

__system_property_get
```