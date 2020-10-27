Application Binary Interface (ABI)

每个Android 设备使用不同的cpu, 支持不同的指令集。两者合称ABI，包括：

支持的cpu 指令集、内存存储和加载的字节顺序（endianness，Android是小端）、系统和App 之间传递数据的协议（包括对齐约束，系统对栈的使用和注册）、可执行二进制的格式（android 使用ELF）、C++ 名字的mangled 方式。

> ELF 和 mangled 方式 有外链可以了解  

r17 开始移除了对 32位、64位 MIPS 的支持，移除了ARMv5 的支持  



`armeabi-v7a` 接口使用`-mfloat-abi=softfp` 参数，保证把`float` 存入一个整型寄存器（register），而把`double` 存入两个整型寄存器（而不管系统是否支持浮点）  

`arm64-v8a` 默认支持高级SIMD（neon），neon 有对应的开发代码。可查看原文。而在Android 上，x18 寄存器是需要保留而不应该用代码编写的，对应`-ffixed-x18` （clang 会默认添加）。  

本着Android 目前就这两个主用，`x86/x86_64` 就不看了。  

配置ABI的方式 https://developer.android.google.cn/ndk/guides/abis#gc  



apk 打包后的共享库目录：`/lib/<abi>/lib<name>.so`

`/lib/armeabi` 在4.0.3 中会覆盖`armeabi-v7a`  



使用`__arm__` 等预定义判断ABI，使用`libc's sysconf(3)` 判断cpu 核数和在线数，

API 18 以上，可以使用`libc's getauxval(3)` 获取cpu 特定的功能列表，而有些设备可能有些bug，可以使用 [google 的库](https://github.com/google/cpu_features)  



neon 需要对应的编程支持，ARMv7-a 不支持neon 的设备数量极少。可以在运行时用上面说的cpu 功能相关函数进行检测。（neon sample里有）