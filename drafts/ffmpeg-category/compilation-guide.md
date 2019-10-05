## 常规  
https://trac.ffmpeg.org/wiki/CompilationGuide  
https://trac.ffmpeg.org/wiki/CompilationGuide/Generic    
https://trac.ffmpeg.org/wiki/CompilationGuide/Ubuntu  

选择使用源码的理由：
    实时性--不会过时或者存在已知而未添加的功能
    移植性--不同平台、链接其他库
    自定义--需要自定义修改源码

常规安装步骤：
```
./configure  #configuration
make         #compilation
make install #installation
```

### 选项介绍：
- 默认安装路径在 /usr/local， 由prefix 配置
```
./configure --prefix=PREFIX
```
- PREFIX/bin: 包含生成后的二进制文件(如ffmpeg,ffplay,ffprobe)
- PREFIX/include: 包含库的头文件(如libavutil/avstring.h),这些文件会被链接到二进制文件
- PREFIX/lib: 包含生成的二进制文件（如libavutil）
- PREFIX/share: 包含系统无关的组件：一般是文档和示例

#### 需要根据PREFIX更新的环境变量介绍：
 - PATH: 使用冒号分割路径。`export PATH=PREFIX/bin:$PATH` ，用来给系统查找二进制
 - LD_LIBRARY_PATH:使用冒号分割。 `export LD_LIBRARY_PATH=PREFIX/lib:$LD_LIBRARY_PATH`,有些时候可以使用ldconfig工具，会修改/etc/ld.so.conf。部分系统出于安全考虑，这个变量会在shell profile中被重置，即每次都要设置
 - CFLAGS: c编译器使用的flag。一般包括`-IPREFIX/include`。很多构建系统允许`-extra-cflags`
 - LDFLAGS: 被链接器使用。一般包括`-LPREFIX/lib`来指定自定义的库路径。很多构建系统允许`-extra-ldflags`
 - PKG_CONFIG_PATH: 使用冒号分割。用于pkg-config来查找(detect)特殊库的CFLAGS/LDFLAGS

## tips
有很多方式来提升ffmpeg的性能，下面列举一些：
- 如果使用gcc/clang，考虑使用`--extra-cflags -march=native`，这会更好的使用硬件。更通用安全的方案可以制定`--arch`和`--cpu`选项，但是这样的优化效果比较无常，一般比较少。
- `--enable-hardcoded-tables`也是个有用的选项，但是会造成`libavcodec`大约15%的大小增加。这个选项可以减少表的创建时间，只需要在codec初始化的时候进行一次。

## ndk  

### 独立工具链
对应api的库在：$NDK/platforms/下  
他们依赖的头文件在：$NDK/sysroot  
脚本在$NDK/build/tools/目录下，现在有了py脚本，方便windows用户  

```
$NDK/build/tools/make_standalone_toolchain.py \
    --arch arm --api 21 --install-dir /tmp/my-android-toolchain
```
<<<<<<< HEAD
# Add the standalone toolchain to the search path.
export PATH=$PATH:`pwd`/my-toolchain/bin

# Tell configure what tools to use.
target_host=aarch64-linux-android
export AR=$target_host-ar
export AS=$target_host-clang
export CC=$target_host-clang
export CXX=$target_host-clang++
export LD=$target_host-ld
export STRIP=$target_host-strip

# Tell configure what flags Android requires.
export CFLAGS="-fPIE -fPIC"
export LDFLAGS="-pie"
=======
>>>>>>> add ndk-standalone-toolchain

从r18开始，所有独立工具链使用clang和libc++，即默认传递`-static-libstdc++`给链接器，用这个的同事一般需要传`-latomic`  
默认情况下使用libc++的分享库版本，即需要打包`libc++_shared.so`，位置在对应架构下，如`arm`---`$TOOLCHAIN/arm-linux-androideabi/lib/`目录下

如果使用了`--install-dir`，会在当前目录生成一个原始代码包`$TOOLCHAIN_NAME.tar.bz2`，可以通过`--package-dir`更换位置  

### Clang  
Clang会被独立工具链自动包含，位于`<install-dir>/bin`  
如果要使用clang,不要修改clang的脚本，而只是设置cc和cxx变量  

而里面也会有叫做`gcc`或者`g++`的脚本，但是他们里面其实调用的是clang, 只是为了方便以前的代码使用。如果出现不兼容的问题，就要替换咯  

arm的独立工具链默认指向armeabi-v7a ABI.可以被`-march=armv7-a`修改，然后会被`-target=armv7-none-linux-androideabi` 覆盖  
建议同时使用`-mthumb` -- `thumb-none-linux-androideabi`，会强制生成16-bit Thumb-2 instructions，  
对应`thumbv7-none-linux-androideabi`  

可以在运行的时候使用这些选项来确认编译器运行的是clang：
- -v
- -###
- -x c < /dev/null -dM -E  
- -save-temps

如果使用了NEON, 需要 -mfpu=neon  ，会强制使用VFPv3-D32  
还要给linker这些参数  `-march=armv7-a -Wl,--fix-cortex-a8` # 只是arm需要

禁止c++ 的属性 `-fno-exceptions and -fno-rtti`    

## ubuntu环境下编译

### 概念
摘自 [ffmpeg3.2.4 + ndk13 + osx/ubuntu](https://medium.com/@ilja.kosynkin/building-ffmpeg-for-android-607222677a9e)

- toolchain: 编译构建的工具
- sysroot: 编译器查找系统头文件和库的目录
- prefix: 构建结果输出目录
- cross-prefix: 使用的编译器的目录
- abi: 处理器架构
- cflags: c编译器的flag
- ldflags: 链接器的flag

### 预先处理  
- 防止输出文件命名格式不支持：因为以前的脚本对文件的命名格式不同，但是现在不用处理。  
```
SLIBNAME_WITH_VERSION= SLIBNAME_WITH_MAJOR=
```  
- 防止参数报错：修改configure的`SHFLAGS='-shared -Wl,-soname,$(SLIBNAME)'`为`SHFLAGS='-shared -soname $(SLIBNAME)'`  
- 防止命令行退出：修改`fftools/ffmpeg.c`里的`exit_program(received_nb_signals ? 255 : main_return_code)`为`ffmpeg_cleanup(received_nb_signals ? 255 : main_return_code)`  

### gcc
>上述的cflags 就是gcc的一些命令选项，但是实际上太多了。。。还是照搬前人的命令吧    
> https://gcc.gnu.org/onlinedocs/gcc-8.2.0/gcc/#toc-GCC-Command-Options

- [ffmpeg4.0 + ndk16 + ubuntu](https://blog.csdn.net/ericbar/article/details/80229592)  
主要是看他前面的那个文章，把[模块的so编译成一个总的so](https://blog.csdn.net/ericbar/article/details/76602720)  
- [ffmpeg4.0 + ndk16 + ubuntu](https://stackoverflow.com/questions/54339191/how-to-compile-ffmpeg-for-android-with-clang-in-ubuntu18-04)  
他是说4.1可以，但是4.1会遇到问题：  
```
libavformat/udp.c: In function 'udp_set_multicast_sources':
libavformat/udp.c:290:28: error: request for member 's_addr' in something not a structure or union
         mreqs.imr_multiaddr.s_addr = ((struct sockaddr_in *)addr)->sin_addr.s_addr;
                            ^
libavformat/udp.c:292:32: error: incompatible types when assigning to type '__be32' from type 'struct in_addr'
             mreqs.imr_interface= ((struct sockaddr_in *)local_addr)->sin_addr;
                                ^
libavformat/udp.c:294:32: error: request for member 's_addr' in something not a structure or union
             mreqs.imr_interface.s_addr= INADDR_ANY;
                                ^
libavformat/udp.c:295:29: error: request for member 's_addr' in something not a structure or union
         mreqs.imr_sourceaddr.s_addr = ((struct sockaddr_in *)&sources[i])->sin_addr.s_addr;
                             ^
ffbuild/common.mak:60: recipe for target 'libavformat/udp.o' failed
```
[搜索的答案](https://blog.csdn.net/myfather103/article/details/80460679#comments)，说把udp.c里的问题代码删掉就好，因为以前的版本没有这些代码。  
个人表示非常怀疑，并且因为他的版本实在太老不想尝试。

### clang  
- [ffmpeg4.0.2 + ndk17 + ubuntu](https://medium.com/@ilja.kosynkin/building-ffmpeg-4-0-for-android-with-clang-642e4911c31e)  
遇到问题:    
```
/home/tpout/Downloads/android-ndk-r19/toolchains/llvm/prebuilt/linux-x86_64/bin/clang is unable to create an executable file.
C compiler test failed.
```  
目前的理解是因为检测的方式clang不支持，我就把检测代码删了。。。
然后发现asm库找不到，一看目录的确有问题，在次级目录复制了一个出来。。。
然后碰到下面这个经常遇到的问题：  
```
static declaration of 'roundf' follows non-static declaration
static av_always_inline av_const float roundf(float x)
```  
对于这些，我还是从入门到放弃了。。以后会c之后再深入吧  
直接拿文章里的项目使用了。  

- [ffmpeg4.1 + ndk16 + mac](https://blog.csdn.net/biezhihua/article/details/86186420)  
快速配置，但是4.1的拉取也被写入脚本了。  
简单的看里面的脚本也是使用的clang的独立工具链    
但是好像只适合mac,ubuntu会遇到：   
<font color=red>GNU assembler not found, install/update gas-preprocessor</font>
```
If you think configure made a mistake, make sure you are using the latest
version from Git.  If the latest version fails, report the problem to the
ffmpeg-user@ffmpeg.org mailing list or IRC #ffmpeg on irc.freenode.net.
Include the log file "ffbuild/config.log" produced by configure as this will help
solve the problem.
Makefile:2: ffbuild/config.mak: No such file or directory
Makefile:40: /tools/Makefile: No such file or directory
Makefile:41: /ffbuild/common.mak: No such file or directory
Makefile:91: /libavutil/Makefile: No such file or directory
Makefile:91: /ffbuild/library.mak: No such file or directory
Makefile:93: /fftools/Makefile: No such file or directory
Makefile:94: /doc/Makefile: No such file or directory
Makefile:95: /doc/examples/Makefile: No such file or directory
Makefile:160: /tests/Makefile: No such file or directory
make: *** No rule to make target `/tests/Makefile'.  Stop.  
```

- [ffmpeg4.1 + ndk16 + ubuntu](https://stackoverflow.com/questions/54339191/how-to-compile-ffmpeg-for-android-with-clang-in-ubuntu18-04)  
下面部分有clang的配置，虽然没有成功，但是可以参考  

### 其他
[ffmpeg + ndk15 + Linux Mint 18.2 "Sonya" - Cinnamon (64-bit)](http://alientechlab.com/how-to-build-ffmpeg-for-android/) 这个是浏览某个看到的

## c++包含头
```
extern "C"{
#include <libavutil/imgutils.h>
}
```
如果有些编译器显示：’UINT64_C’ was not declared in this scope.
可以添加`-D__STDC_CONSTANT_MACROS`到CXXFLAGS中
