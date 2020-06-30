Invocation API 能让软件供应商在完全的native 应用中加载JVM，减少了链接JVM 源码的步骤  

CreateJavaVM 加载和初始化JVM 并返回一个 JNI 接口指针（JNIEnv），创建的线程被视为主线程  

JNIEnv 只在当前线程有效，其他线程需要使用  AttachCurrentThread 来附着到VM 以获得JNIEnv，在native 方法中附着成功后，native 线程就会和常规的java 线程一样。一直持续到 DetachCurrentThread()  ，如果在调用栈上存在Java Methods，则不能detach。  

每个附着线程需要有足够的栈空间，而每个线程的栈空间是因系统而异的。  

DestoryJavaVM，VM 会一直等到当前线程是唯一的非守护用户线程时，才会实际卸载。用户线程包含 java 线程和附着的native 线程。这是因为VM 无法自动释放资源，同时需要用户在线程中自行保证  



一旦一个native 库被加载，会对所有class loaders 可见，因此两个不同class loader 加载的类可能链接相同的native 方法。这样有两个问题：

- class loader A加载的名称1 的class，它会加载一个native 库，而这个native 库会链接一个名称为1的class  
- 类似的native 方法很容易 混搭 不同class loader 加载的class，

所以设定每个class loader 管理它自身的native 库set，相同的JNI native 库不能被多个class loader 加载。（对应UnsatisfiedLinkError）   

这样还能在class loader 被垃圾收集时卸载掉native 库  



OnLoad 在native 库加载后 的时候由VM 调用，如果返回JNI_VERSION_1_2 ，默认为新版本；如果没有这个方法，认为是JNI_VERSION_1_1；如果返回VM 不支持的版本号，则会卸载  

静态加载则是JNI_Onload_L 相关，基本思路一样

OnUnload 在class loader 被当垃圾收集时由VM 调用，但是因为调用的位置不可知，所以保守来讲不要在里面实现java 回调。

这两个是JNI 方法，而不是VM  



  





