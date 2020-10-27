运行 `ndk-build` ，当然有很多参数，具体可以参看原文

```shell
$ cd <project>
$ <ndk>/ndk-build
```

相当于运行：

```shell
$GNUMAKE -f <ndk>/build/core/build-local.mk
<parameters>
```

其中GNUMAKE 指向 GNU Make 3.81 或更新版本，即`ndk-build` 底层就是执行`make` 命令  

如果GNUMAKE 的不正确，会使用NDK 自己的一个GNU Make 拷贝

NDK r18 以上可以生成 JSON Compilation Database



#### Android.mk

`jni/` 下 的子目录中，是一个项目范围内的设置，描述了源码和共享库，覆盖子模块的配置，包括`Application.mk` ， 构建系统， 以及环境变量   

可以组织源码为各个模块：静态库、共享库、单独可执行  

也可以把一个源码生成多个模块，但是构建系统只会打包共享库  



mk 文件简单说明（注意include 是用来划分模块的）：

```makefile
# Android.mk 必须？以此开头，my-dir 是一个宏，返回mk 文件所在文件夹的路径  
LOCAL_PATH := $(call my-dir)
# 指向特殊的makefile，清除很多模块单独配置的`LOCAL_XXX` 变量，`PATH `不会被清除
include $(CLEAR_VARS)
# 生成共享库的名字
LOCAL_MODULE    := native-activity
# native 源码
LOCAL_SRC_FILES := main.c
# -l 前缀表示要添加的库名称
# 搜索路径：<ndk>/platforms/android-<sdk_version>/arch-<abi>/usr/lib/
LOCAL_LDLIBS    := -llog -landroid -lEGL -lGLESv1_CM
# 静态库名称
LOCAL_STATIC_LIBRARIES := android_native_app_glue
# 指向特殊的makefile，收集最近的`include` 之后单独配置的`LOCAL_XXX` 变量  
include $(BUILD_SHARED_LIBRARY) / include $(BUILD_EXECUTABLE) / include $(BUILD_STATIC_LIBRARY) / include $(PREBUILT_SHARED_LIBRARY)
```



生成的so 也会被拷贝到jni 目录下, `.a` 则不会被拷贝  



#### 变量

如果要自定义变量，建议以`MY_` 开头，因为`LOCAL_` 开头的, `PRIVATE_` , `NDK_` , `APP` 开头的，小写字母如`my-dir` 开头的，都有特定的功用。   

```makefile
# 变量都是类似可用的  
LOCAL_SRC_FILES := $(TARGET_ARCH_ABI)/libfoo.so
```



[包含其他makefile](./https://www.gnu.org/software/make/manual/html_node/Include.html)  



`Android.mk` 会根据`APP_ABI` 里定义的类型，每种各编译一遍。  

默认基于`thumb/` 目录下的STL 库，生成16 位宽的指令的目标二进制。  



可以指定target 系统，和target ABI（也可以一个变量同时指定）  

可以指定生成模块的名称  

可以指定文件名拓展类型  

可以指定编译语言功能选项

> 相对于`LOCAL_CPPFLAGS` 更推荐 `LOCAL_CPP_FEATURES` , 来设置`-frtti` 、`-fexceptions`  
>
> 因为前者是全局的配置，后者则是模块的

可以指定c和c++ 编译选项，也可以只指定C++ 的  

可以指定把所有的依赖打进最终的包（而不是模块依次依赖，解决循环依赖）  

系统的库有单独的指定方式  

可以指定连接选项  

可以去除未知符号定义报错（会允许加载）  

可以指定`arm` 位模式（生成32位宽的arm 模式）  

可以指定使用`arm` 的`neon` 指令集（是Single Instruction Multiple Data，不是所有基于ARMv7 的cpu 都支持）  

> thumb ,arm , neon 虽然概念上有关联，但是在变量配置时，是三个不同的设定

可以设定`printf` 检查非常量字符串（不建议开启）  

可以传递CFLAGS、INCLUDE、LDFLAGS、LDLIBS 给依赖模块  

可以设定一个简短命令（SHORT_COMMAND）模式，windows 上比较有用  

可以将静态库的打包简化成路径（减少大小，但是只能是相对路径）  

可以通过shell 命令过滤一定的文件  



#### 宏

固定格式是 `$(call <function>)`  

可以返回最近include 的makefile 的目录路径， 一般是`Android.mk` 的目录  

可以返回自己、父、爷 makefile 路径

可以通过名字导入一个模块的`Android.mk`   



> my-dir 被覆盖的问题，可以修改include 的位置或者做缓存来解决
>
> MY_LOCAL_PATH := $(call my-dir)  



#### Application.mk

默认在`jni/Application.mk` , 同样有很多变量，但是大部分都有模块版本，如`APP_CFLAGS` 对应`LOCAL_CFLAGS`，模块的设置会覆盖App 的设置。  

可以设置ABI（会被gradle 的设置 覆盖）  

可以配置汇编（`.s` 和`.S` 文件）选项，可以配置YASM 选项（`.asm` 文件）  

可以配置`Android.mk` 的路径（会被gradle 配置覆盖）  

可以配置C 编译器的选项（包括cxx ）  

可以全模块范围内打开clang-tidy  的功能，并且配置clang-tidy 相关  

可以设置debug 模式  

可以设置链接时的配置  

可以配置Manifest 路径  

可以指定需要编译的`Android.mk`  

可以设置优化模式是`release` 或者`debug` (会被application 的属性覆盖)  

可以指定app 支持的平台最小版本  

可以指定项目的目录  

可以指定简短命令  

可以选择 C++ 标准库  

可以指定STRIP 模式（strip 用于剥离信息）  

可以将静态库的打包简化成路径（减少大小，但是只能是相对路径）   

可以指定一个wrap.sh ()



#### 使用预构建库

构建系统会把预构建共享库拷贝到 `$PROJECT/obj/local` ，也会拷贝一份去掉debug 信息的`$PROJECT/libs/<abi>`  

**调试**  

因为`/libs/<abi>` 下的用`ndk-build` 默认会去掉debug 信息，所以你想调试需要使用`ndk-gdb`  

 