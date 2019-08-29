[NDK](https://developer.android.google.cn/ndk/guides/)

## 概念
主要组件：  
- native shared libraries: 从native代码构建出来，以库或者.so文件的形式
- native static libraries: 同样可以构建static库或者.a文件，这些库可以被其他库关联
- java native interface:   
- Application Binary Interface ： 指定了代码在运行时和系统的交互方式，ndk用.so文件处理。
- AndroidManifest.xml: 如果是一个纯粹没有java组件的native app，需要声明NativeActivity  

## 其他
发现页面很多都是根据ndk-build来写的，所以只能用作借鉴  
