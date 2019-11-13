## 基本体系
c/c++代码 等同于 native代码  

native的开发、库等需要NDK  
native代码的构建需要编译工具，即CMake等  
而因为Android构建体系基于gradle, 所以NDK、编译工具和Android其他部分还需要gradle来关联  

### native代码
- 存放在module/cpp 目录下； 或者放在jni目录下
- 调试使用 [C++ Debugger](http://lldb.llvm.org/)
- 编译工具：默认是CMake,也支持使用`ndk-build`

### 构建流程  
所以基本流程可以确定，  
下载对应需要的工具：NDK;CMake;LLDB  
然后需要把native代码、库文件放入对应目录，  
然后根据对编译工具进行配置，一般是编写编译脚本  
最后通过gradle关联起来，  

运行起来后，gradle把脚本的命令传给编译工具，编译出对应的文件，然后在和其他部分一起打包进apk    

### 使用步骤  
在java代码中首先调用加载库，再声明、调用对应的方法即可---直接看sample就行
```
//注意不需要lib前缀
static{
  System.loadLibrary("hello-jni")
}
```
