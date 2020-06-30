c++ 定义了一个支持线程化执行的内存模型，

关键字 thread_local，将变量声明为静态存储，持续性和关联线程绑定

支持库包含 

- 原子操作库（atomic operation）, 头文件 atomic
- 线程支持库，头文件 thread, mutex, condition_variable, future

