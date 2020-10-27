步骤：

- 选择toolchain 的处理器架构 

- 选择sysroot（是一个包含系统头文件和库的目录，对应的native API 在`$NDk/platforms` , 头文件在`$NDK/sysroot` ）

    > 每个API-level 的目录，包含对应的CPU 和架构  

- 创建工具链，`make_standalone_toolchain.py` (是`make-standalone-toolchain.sh`的重写版本，在`$NDK/build/tools/`）

```shell
# 创建一个 android-21/arch-arm 的sysroot，同时工具链的目标二进制是32位arm，放入/tmp/my... 目录
# arch 是必须的，api 可选，默认32位架构是16，64位是21
# 如果不指定--install-dir, 会在当前目录生成 `$TOOLCHAIN_NAME.tar.bz2`, 可以通过 --package-dir 设定tarball 目录
$NDK/build/tools/make_standalone_toolchain.py \
    --arch arm --api 21 --install-dir /tmp/my-android-toolchain
```

从r18 开始，使用clang 和libc++（默认动态库，除非用于生成静态可执行）

```shell
# 指定静态库
-static-libstdc++
# 链接静态libc++ 时需要
-latomic
```

#### clang

生成单独工具链之后，在`<install-dir>/bin` 目录下有`clang` 和`clang++` 两个脚本，对应`CC` 和`CXX` 变量  

> 目录下还有gcc, g++ 脚本，但是实际上并不包含这个两个命令行，内部还是调用了clang/clang++  
>
> 一般是可以兼容的，但是如果实在不行，就要自己放进去
>
> 比如对于makefile，就需要使用gcc，g++。有些时候需要添加参数确认是否可行：
>
> - `-v` dump 和编译器驱动问题相关的命令
> - `-###` dump 命令行选项，包括隐式定义
> - `-x c < /dev/null -dM -E` dump 预定义的预处理器的定义
> - `-save-temps` 比较`*.i`、`*.ii` 预处理文件

构建ARM 的时候，clang 设置target 有单独的参数：`-march=armv7-a` 和 `-mthumb` ，会被 `-target`  覆盖。  

一般建议使用`-mthumb` 来生成16位thumb-2 指令集，默认是 32位ARM 指令集  

使用NEON 指令集需要 `-mfpu=neon` , 强制使用`VFPv3-D32`  

这几个参数是必传的：`-march=armv7-a -Wl,--fix-cortex-a8`

前者是armv7-a 需要的，后者是用于修复某些cortex-A8 cpu bug

<font color=red>这里的-march=armv7-a 是必传的？不是和前面说的-mthumb 是单独参数有冲突吗</font>



限制：

工具链默认支持 c++ exceptions, 和rtti 。可以关闭：`-fno-exceptions` and `-fno-rtti`

在只生成一个共享库、可执行的时候，推荐使用静态c++ 标准库；默认情况下是动态库，需要把对应`架构目录下/lib/libc++_shared.so` 放到app 中  

还要注意多个库依赖不同版本的libc 时



原文有使用makefile 的示例脚本，

还有使用configure 的实例脚本（即autoconf），有些autoconf 的项目不会有默认configure 文件，需要自己配置生成。  



