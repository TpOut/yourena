>主要内容摘自《pointer on c》，部分内容来自 网络和开源项目。 
>
>-- 原书第18chapter 讲述了运行时环境，主要是解释c运行的限制，以及如何和汇编一起工作。并没看
>
>如果没有说明，运行代码的IDE是Clion，系统可能是win可能是ubuntu,mac



- Main入口

    >对于main入口的参数，argc和 **argv（有些操作系统会传递第3个参数），是操作系统通过命令行形式传递的
    >
    >前者指定了参数的数量，后者则是指向“参数指针”数组的指针  
    >
    >第一个参数指针的値是程序名称，一般是文件名。但是unix的ls 命令就是一个例子
    >
    >返回查看stdlib 的常量

- [编译过程](./compile-process/compile-process-index.md)

- 系统常量在[API](./api.md)页面查找

- c标准

    >最开始，C语言社区遵循一种事实标准，该标准基于Kernighan 和 Ritchie 编写的《The C Programming Language》， 通常被称为K&R C，在ANSI C出现后，又被称作经典C ( Classic C )
    >
    >ANSI C 标准：不仅定义了C语言，还定义了一个ANSI C实现必须支持的标准C库
    >
    >最新的C标准为C99，ISO和ANSI 分别于1999年和2000年批准了该标准，主要添加了一些C++编译器支持的特性，如新的整形

- [函数](./function/function-index.md)

- [基本类型](../type/type-index.md)

- [const](./const.md)

- [作用域](./scope.md)

- [左右值](./l-value-r-value.md)

- [输入输出](./io.md)

- [操作符](../operator/operator-index.md)

- [关键字](./keyword.md)

- 格式化参数

    >需要了解的有printf , scanf -- 两者并不完全相同 
    >
    >查询之处 https://en.cppreference.com/w/c/io/fprintf

- 动态分配

    >malloc: 请求获取内存。返回的结果必然是连续的或者null  
    >calloc: 类似malloc，但是会先把指针初始化为0；  
    >realloc: 对已分配内存进行大小调整;参数Null 则和malloc一样  
    >free: 参数必须是上述方法返回的指针

- [代码内存模型](./struct-storage.md)



tips: 

- cdecl 可以把类型声明，转述成便于理解的日常英语

---



[《pointer on c》作者主页 ](https://www.cs.rit.edu/~kar/)

cpprefrence 站点可以查很多东西

gnu，libc 手册：https://www.gnu.org/software/libc/

c编译器：https://www.sigbus.info/how-i-wrote-a-self-hosting-c-compiler-in-40-days

modern-c : https://modernc.gforge.inria.fr/

ui库：[https://github.com/andlabs/libui](https://github.com/andlabs/libui)  

对比: [https://ds9a.nl/articles/posts/cpp-rust-go/](https://ds9a.nl/articles/posts/cpp-rust-go/)   

北大视频：[https://www.bilibili.com/video/av10046030/?p=1](https://www.bilibili.com/video/av10046030/?p=1)

面试题：[https://techiedelight.quora.com/Top-25-Programming-Puzzles-and-Brain-Teasers](https://techiedelight.quora.com/Top-25-Programming-Puzzles-and-Brain-Teasers)  

学习路径：[http://stevelosh.com/blog/2018/08/a-road-to-common-lisp/](http://stevelosh.com/blog/2018/08/a-road-to-common-lisp/) 

开源教程：[http://www.gigamonkeys.com/book/](http://www.gigamonkeys.com/book/)

c 内部：http://www.avabodh.com/cin/cin.html  

