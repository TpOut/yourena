https://github.com/singwhatiwanna/dynamic-load-apk

主要思路是 宿主apk 启动后，读取 插件apk，然后加载其中的资源和代码



资源有一个关键问题，就是Android 打包时会确认资源定位，而插件加载 “不在范围”。

代码也有个关键问题，就是Activity 生命周期其实是系统管理的，而插件加载 “不受控制”。

针对资源，反射调用Android 的资源加载方法：getAssets()，getResources()可以设置资源路径，就可以添加插件apk 里的资源了。

针对Activity生命周期，在宿主apk 中创建一个代理Activity，然后代理插件中的activity，就可以正确处理生命周期 了。



说回读取插件apk，关键是使用DexClassLoader 。

一些小细节，比如注册表来解决多个插件apk 中类可能冲突的问题。

