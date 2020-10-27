需求ndk r19以上，使用的是非ndk-build 和非CMake  构建系统  

比如OpenSSL 和libbzip2  

> 其他系统想内部集成ndk 也可以在这里找到链接 

不再使用 `make_standalone_toolchain.py`

以Clang 为示例（变量看原文即可）：

```shell
# 通过target 取得对应的clang  
$ $NDK/toolchains/llvm/prebuilt/$HOST_TAG/clang++ \
    -target aarch64-linux-android21 foo.cpp
    
# 等同于（有些脚本的风格是GCC 的形式，不支持-target 完全参数，用这个就好）
$ $NDK/toolchains/llvm/prebuilt/$HOST_TAG/aarch64-linux-android21-clang++ \
    foo.cpp
```



然后介绍了两个`Autoconf` --`libpng` ,`Non-Autoconf` -- `libbzip2` 项目的配置