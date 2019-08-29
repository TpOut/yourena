
## 运行app  
连接设备前提是安装adb和配置udev规则

- 可以用apt安装adb,会配置一个默认的udev（由社区管理）。需要保证自己在plugdev组内
```
apt-get install adb
```
- 或者安装Androidstudio，需要自己配置一个51-android文件。  

然后就可以在列表上(`adb devices`)看到了

如果在连接设备上有什么问题，可以查看工具栏的 tools > connection assistant  

Android4.2.2(17)以上的设备在连接时会弹出 rsa安全码 的确认提示  

## 视频拍摄
支持3分钟，mp4格式，没有声音

## 启动调试  
c/c++代码需要在SDK管理器中安装LLDB  
开启设备的调试开关  
```
buildTypes {
        customDebugType {
            debuggable true
```

AS调试期间，垃圾回收是非常松散的。在断开调试之前，可能会随时间产生很多对象垃圾。。。     

c/c++代码会被编译器优化，可能在调试的时候导致一些不可预知的问题，对应的可以选择关闭这个功能。  

```
if (savedInstanceState != null) {
    Log.d(TAG, "onCreate() Restoring previous state")
} else {
    Log.d(TAG, "onCreate() No saved state available")
}
```  

默认情况下，调试器会开启两个，即jvm相关和c/c++相关。当你在调试后者的时候，前者会被暂停。  
### watchpoints  
调试c/c++代码的时候，可以添加特殊的断点，即watchpoint。  
相关：https://developer.android.google.cn/studio/debug/#watchpoints

### 断点数据显示  
可以修改显示类型，右键variable，选择view as，还可以添加自己的显示类型：  
https://www.jetbrains.com/help/idea/2018.1/debugger-data-type-renderers.html

## bug report  
真实设备：开发者选项 > take bug report        
模拟器： more > extended controls > Bug Report  
或者使用adb命令  

### report文件
bugreport-BUILD_ID-DATE.zip，包含：
- version.txt: Android release 信息
- systrace.txt： 包含app进程和系统进程的一些显示执行时间。需要开启systrace。
- bugreport-BUILD_ID-DATE.txt： 综合了system services (dumpsys), error logs (dumpstate), and system message logs (logcat)  

### debug配置
从工具栏的，Run > Edit configurations 进入面板    
其中default文件夹下是自带的模板，default之上都是自定义的。  

自定义配置必须基于default里的模板。
而app配置则是创建项目的时候，自动基于default创建的  

可配置项包括  
- 修改启动的activity：  
所以你也可以对一个activity的java文件，右击，并选择run... 这时候as会选择对应的默认模板，再修改对应的activity然后运行  
- 设置预运行操作  
这是一个高级功能。一般性预操作是通用的，所以建议在build.gradle中同时配置好   
默认有一个Gradle-aware make,作用是编译项目和运行gradle

你也可以右键一个包，选择对应的run选项

这样创建的配置都是临时的，可以在面板列表中选择保存。临时配置最多可以有5个（可以在default文件夹 修改数量，多了会移除之前的）；或者run按钮边上的配置列表也可以操作  

虽然自定义配置是基于默认配置的，但是在修改默认配置的时候，不会联动修改已经生成的自定义配置。  

#### 分享模板
使用分享按钮之后，as会把配置以xml格式存放在`project_directory/.idea/runConfigurations/`文件夹    
(格式限制：https://www.jetbrains.com/help/idea/2018.1/creating-and-managing-projects.html#project-formats)  

#### 支持配置模板说明  
https://developer.android.google.cn/studio/run/rundebugconfig  
id #android-application  
id #android-tests  

#### 不支持模板说明  
很多idea的配置模板不适合Android开发：
- Application
- Compound
- Gradle
- Groovy
- Jar Application
- Junit Test Discovery
- Java Scratch
- Remote
- TestNG
- testNG Test Discovery

所以如果想要用，请参阅：  
https://developer.android.google.cn/studio/run/rundebugconfig#unsupported-templates  

## 其他

[idea--创建和编辑Run/Debug的可配置项]  
https://www.jetbrains.com/help/idea/2018.1/creating-and-editing-run-debug-configurations.html  
https://www.jetbrains.com/help/idea/2018.1/run-debug-configurations-dialog.html

idea调试页面：https://www.jetbrains.com/help/idea/2018.1/debugging-code.html  

lldb调试器：https://lldb.llvm.org/tutorial.html  
调试窗口frame的介绍：https://www.jetbrains.com/help/idea/2018.1/debug-tool-window-frames.html  

开发者选项设置: https://developer.android.google.cn/studio/debug/dev-options  
查看错误报告的：https://source.android.google.cn/source/read-bug-reports.html
