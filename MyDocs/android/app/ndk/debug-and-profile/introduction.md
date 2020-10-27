ndk-stack 帮助查看崩溃符号  

ndk-gdb 允许使用命令行链接`gdb`  

当然最好还是使用AS 内置的`lldb`  



调试native 内存问题：

- HWASan 和ASan 分析器

- Malloc debug（还可以自己用malloc hook 实现工具

- Mallinfo 查看malloc 数据    

- Simpleperf 查看native cpu profile

    

---

Last updated 2020-02-10