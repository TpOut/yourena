`CMakeLists.txt` ，具体的使用查看项目代码    

重命名或者移除一个CMake 构建脚本的时候，记得clean  

AS 自动`Project` 将面板下的源代码文件和头文件作为一个cpp 组添加。   



NDK 通过使用CMake 的工具链文件来达到对其的支持，在`<NDK>/build/cmake/android.toolchain.cmake` 目录下    

编译参数都是以命令行的形式传递，对应 externalNativeBuild -> cmake.arguments    

> CMake 自己有内置NDK 支持，但是目前不适用于Android  



可以配置ABI  

配置ARM模式：THUMB、ARM、NEON（只支持armeabi-v7a，23及以上默认开）      

配置链接器：lld 实验性

配置平台版本  

指定STL（默认libc++ 静态库，要注意C++ 的支持）  



原文有 cmake 命令行示例



支持YASM （这个有点汇编语法？），`.asm` 文件  

​    



---

https://cmake.org/cmake/help/latest/manual/cmake-commands.7.html

