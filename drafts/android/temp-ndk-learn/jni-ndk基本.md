> 写在前面的话:很多Android开发者在遇到NDK开发的时候就望而却步，随着Android市场下半程的正式开始，很多人工智能的应用场景需要在移动端落地，比如音视频开发、图像处理、智能家居场景、物联网等等。由于NDK涉及到了编译构建环境、C/C++基础语法、JNI原理、交叉编译工具链、崩溃发现和解析等知识，导致很多3-5年的工程师也跪在了NDK的方向，这也是我写这个系列文章的原因，希望2019年大家雄起，都能找到自己喜欢的工作，能和大家一起进步，我也很开心了。

下图是我这个系列文章所包含的内容，基本上涵盖了实际NDK开发中的方方面面。我将会用8篇文章来详细的带你进入NDK开发的领域，请大家关注。

![image-20181224133832199](https://ws1.sinaimg.cn/large/006tNbRwly1fyk7xkouovj30zn0u0dif.jpg)



### JNI和NDK是什么？

#### 基本概念

- Android NDK 

  `NDK `是一组允许你将 C 或 C++嵌入到 Android 应用中的工具。通过NDK开发我们可以在平台之间移植应用(音视频开源库FFmpeg、开源视觉库OpenCV等等)，在一些计算密集型的APP(比如游戏开发)中我们也需要做一些算法的移植工作。可以看出来NDK就是原生开发工具包，在AndroidStudio中我们在通过SDK Manager进行NDK工具包的更新和安装。NDK是属于Android的和Java没有特定的关系。

- Android JNI

  `Java Native Interface`Java本地接口，JNI是属于Java特有的特性，主要作用是让Java和C++、C等其他本地语言进行交互。通过JNI，可以让Java具备跨平台的特性，增强Java和本地语言交互的能力。

#### NDK和JNI的关系

NDK是Android SDK自带的工具开发包，用于快速的开发C和C++的动态代码库，并将相关的so文件打包进APK文件中。JNI是Java语言增强的特性之一，用于Java与本地语言的交互。总结下来：JNI是实现的终极目标，而NDK是Android集成开发环境实现JNI目标的手段。***通过NDK实现JNI目标。***

需要注意：
NDK提供的库本身也是有限的，仅仅用于处理算法效率和敏感的问题，NDK提供了交叉编译器，用于生成特定的CPU的平台动态库，另外NDK还提供了.so文件和.apk打包的工具。

#### AndroidStudio如何支持开发

还记的AndroidStudio2.2的版本吗？还记得Eclipse开发NDK吗？简直苦不堪言啊。考虑到目前大部分同学已经迁移到AndroidStudio，况且我相信绝大同学的版本已经在3.2了吧。我们直接就讲解最新的AndroidStudio集成NDK开发的方式。

- 保证你的Android SDK已经下载了Cmake、LLDB、NDK。

![image-20181224142135487](https://ws1.sinaimg.cn/large/006tNbRwly1fyk7xjrtyuj31o40qcdlm.jpg)

- 通过AndroidStudio创建Android Project，记得勾选Include C++ surpport

![image-20181224142447705](https://ws1.sinaimg.cn/large/006tNbRwly1fyk7xo2um9j31900oyq4c.jpg)

- 点击Next，一直到Customize C++ Support，勾选-fexceptions和-frtti

![image-20181224142937266](https://ws3.sinaimg.cn/large/006tNbRwly1fyk7xk79ktj31460u0404.jpg)

一个支持了NDK的Android的项目有哪些需要注意的点呢？

- Gradle配置

如果要手动将 Gradle 与你的本地库相关联，你需要在 module 层级的 build.gradle 文件中添加 `externalNativeBuild {}` 代码块，并且在该代码块中配置 `cmake {}` 或 `ndkBuild {}`。可以看到AndroidStudio默认使用Cmake构建系统。后续我们着重来说。

![image-20181224143508987](https://ws1.sinaimg.cn/large/006tNbRwly1fyk7xn15l6j31b40u0aeu.jpg)



```groovy
cmake {
    cppFlags "-frtti -fexceptions" //这里的设置了一些可选的标识符给C++的编译器，这也是我们之前在创建项目时候勾选的
}
```

- Cmake配置

![image-20181224144056905](https://ws2.sinaimg.cn/large/006tNbRwly1fyk7xiny7xj317m0u0jw0.jpg)

`add_library()` 、`find_library`和`target_link_libraries`都是Cmake脚本中定义的命令函数。其他一些Cmake脚本语法可以参见https://cmake.org/cmake/help/v3.0/command/find_library.html(注意版本区分)。

`add_library` 使用指定的源文件向工程中添加一个库。函数原型如下：

```cmake
 add_library(<name> [STATIC | SHARED | MODULE]
              [EXCLUDE_FROM_ALL]
              source1 source2 ... sourceN)
             # STATIC：静态库，是目标文件的归档文件，在链接其它目标的时候使用
             # SHARED：动态库，会被动态链接，在运行时被加载
             # MODULE：模块库，是不会被链接到其它目标中的插件，但是可能会在运行时使用dlopen-系列的函数
```

`find_library`用于查找一个库文件。函数原型如下：

```cmake
 find_library(
             <VAR>
             name | NAMES name1 [name2 ...]
             [HINTS path1 [path2 ... ENV var]]
             [PATHS path1 [path2 ... ENV var]]
             [PATH_SUFFIXES suffix1 [suffix2 ...]]
             [DOC "cache documentation string"]
             [NO_DEFAULT_PATH]
             [NO_CMAKE_ENVIRONMENT_PATH]
             [NO_CMAKE_PATH]
             [NO_SYSTEM_ENVIRONMENT_PATH]
             [NO_CMAKE_SYSTEM_PATH]
             [CMAKE_FIND_ROOT_PATH_BOTH |
              ONLY_CMAKE_FIND_ROOT_PATH |
              NO_CMAKE_FIND_ROOT_PATH]
            )
           # android系统每个类型的库会存放一个特定的位置，而log库存放在log-lib中
```

`target_link_libraries`设置要链接的库文件的名称。函数原型如下：

```cmake
target_link_libraries(<target> [item1 [item2 [...]]]
                      [[debug|optimized|general] <item>] ...)
```

通过上面的解析，我们知道AndroidStudio默认使用Cmake进行NDK构建环境，通过在CMakeLists.txt中进行编辑命令的编写，使得C++/C的代码可以通过Cmake工具进行编译。

- Java和C++代码JNI编写

Java：函数的native申明是在MainActivity中。我们再看看MainActivity文件，与以前的jni调用方式完成一样，包括了库的加载、native方法申明、jni方法调用三个过程。在native方法中通过点击鼠标左键可以直接跳转到C++的源码里。

![image-20181224145836640](https://ws1.sinaimg.cn/large/006tNbRwly1fyk7xmnj37j31980u0jve.jpg)

C++：这里是我们需要通过C/C++进行代码编写的地方。

![image-20181224150322849](https://ws4.sinaimg.cn/large/006tNbRwly1fyk7xl5gevj310o0d2gn8.jpg)

我们在AndroidStudio中进行运行，把项目跑到手机中，当我们点击TextView，我们可以看到Hello from C++，说明我们成功进行了Java和C++代码的相互调用，也完成了JNI第一个入门程序的分析。

#### 已有项目如何支持NDK开发

上面我们看到了如何在项目刚刚创建的时候集成NDK开发，如果针对一个已经开发的项目，那么如何进行NDK的集成呢？

- 在需要使用JNI的模块中新增一个CmakeLists.txt文件，按照Cmake语法规则进行脚本的编写

![image-20181224151310204](https://ws4.sinaimg.cn/large/006tNbRwly1fyk7xlsjixj31el0u0793.jpg)

- 配置项目支持JNI开发，在项目模块右键选择Link C++ Project with Gradle。

  ![image-20181224151702372](https://ws3.sinaimg.cn/large/006tNbRwly1fyk7xnmfjmj30qs0ckgmi.jpg)

- 在弹出的提示框中选择刚新建配置的CMakeLists.txt文件，并点击OK。

  ![image-20181224151924209](https://ws1.sinaimg.cn/large/006tNbRwly1fyk7xp0n34j30rs0b63zh.jpg)

  通过上述的步骤，我们可以看到AndroidStudio会帮我们在gradel脚本文件中自动创建`externalNativeBuild {}` 代码块，并且在该代码块中配置 `cmake {}` 代码块了。接下来我们就可以在Java代码中进行库的加载、native方法申明、jni方法调用三部曲了。



  现在，你是不是对NDK有了初步的了解了呢？后续将会进一步分享NDK，请大家保持关注。