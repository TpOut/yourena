OpenGL ES  

OpenSL ES  



libstdc++ 是系统库，但是实际上只有 `new` 和`delete` 

libc++ (c++17) ，r18 之后只有 LLVM's libc++ ，非系统，需要包含在apk 内    

system (r18 后废弃)，不支持exception 和RTTI  

none (no headers, limited c++)  



如果只有一个库，那么用静态链接把所有的都连接到最终库中    