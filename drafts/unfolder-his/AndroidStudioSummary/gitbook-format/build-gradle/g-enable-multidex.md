64k方法数限制。主要来自单个dex可执行文件的限制：包括framework、lib和自身代码方法  

解决的办法很简单，支持多个dex文件(`classesN.dex`)即可。

>instant run在multidex和minSdkVersion<=20 的时候，不支持instant run  

## 避免64k限制  
多去检查直接或间接依赖，不要因为使用少数方法而引入一整个库。   
不要让遗留且无用的代码被打包进apk（可以使用proguard实现）  

## multidex配置  
```
android {
    defaultConfig {
        multiDexEnabled true
```
在5.0之前的附加配置
```
implementation 'com.android.support:multidex:1.0.3'
```
Android >= 5.0 的情况下，使用ART原生支持multidex，

如果没有自定义application，给manifest中的application添加`android:name="android.support.multidex.MultiDexApplication"`  
否则需要自定义application 继承 MultiDexApplication 或者重写方法：
```
@Override
  protected void attachBaseContext(Context base) {
     super.attachBaseContext(base);
     //不要install完成之前调用 反射代码或者jni
     //问题是如何知道install完成？
     MultiDex.install(this);
  }
```
## multidex支持库的限制  
因为dex文件安装到设备的data区域是很复杂的。如果第二个dex是很大的，有可能造成ANR。这时会需要你去优化apk大小来减少dex的大小  
4.0的设备还有问题--因为没有必要，就不记录了  

## 声明需要放到主dex文件中的类  
主dex文件中必须包含启动时所需的类。  
而使用复杂依赖的库，或者使用jni调用java代码，相对而言是不可见的，可能不会被放入主dex中。。  

此时可能出现不可预知的`java.lang.NoClassDefFoundError`,为了处理这些情况，可以通过配置使某些类一定被加入到主dex:  
- multiDexKeepFile
```
android {
    buildTypes {
        release {
            //相对路径
            multiDexKeepFile file('multidex-config.txt')
            ...
```
multidex-config.txt文件：
```
com/example/MyClass.class
com/example/MyOtherClass.class
```  
- multiDexKeepProguard
```
android {
    buildTypes {
        release {
            multiDexKeepProguard file('multidex-config.pro')
```
multidex-config.pro文件:
```
# 支持所有proguard的语法
-keep class com.example.MyClass
-keep class com.example.** { *; } // All classes in the com.example package
```
## 开发构建时的优化
因为multidex的复杂性，所以增加了很长的构建时间。  
你可以设置开发版的 minSdkVersion 21 来支持pre-class dexing功能，这样就能减少时间  
