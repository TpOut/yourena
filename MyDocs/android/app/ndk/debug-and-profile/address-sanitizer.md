#### AddressSanitizer

缩写是ASan  

一个快速的基于编译器的检测native 代码内存bug 的工具，检测功能包括：

- 堆栈buffer 上下溢
- 堆在释放之后的使用
- 栈在作用域之外的使用
- 重复释放、野指针释放  



使用ASan 的CPU 负载增加会接近2倍、代码量增加在0.5~2 倍之间、内存负载增加近似2倍  



示例应该是ndk-build 系统。在Android 中需要配置变量来为可执行 添加ASan，会在检测到bug 后，在标准输出流中和`logcat` 中输出信息，并让进程崩溃。  



堆栈追踪：基于frame-pointer 的展开器（unwinder），记录程序分配和回收内存的栈追踪。需要重新编译带有ASan 的库，或者设置对应的变量  

配置符号化  

可以给整个系统刷入ASan，2GB RAM 可以hold 住  

也可以对ASan 的路径进行交换，让某个进程开启检测  

​    

#### HWAddressSanitizer

需要Android 10，AArch64 硬件，可以在支持的设备刷镜像。    

相对于ASan 而言，CPU 负载增加类似，代码量增加类似（但是是40-50% ？），占用很少的RAM（10%-35%）  

功能上增加了：检测栈返回后还被使用  

实现上基于`memory-tagging`，但是因为tag 的值只能是256个，所有会丢失0.4% 左右的bug。  

没有ASan 的限制：检测溢出限制大小、双重释放检测限制容量；即不管溢出多大，多久之前被回收的内存，都支持检测。  



---

HWASan 详细介绍：

https://arxiv.org/pdf/1802.09517.pdf  

https://clang.llvm.org/docs/HardwareAssistedAddressSanitizerDesign.html  