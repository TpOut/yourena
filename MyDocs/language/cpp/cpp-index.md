> 主要内容摘自《c++ primer 6th》，部分内容来自 网络和开源项目。 
>
> 设计上是兼容c 的，很多新语法考虑 C++ release 2.0 ; C++ 11
>
> 
>
> 如果仔细阅读了书籍，则应很好地掌握了c++的规则，然而，这仅仅是学习这语言的开始。



一切都遵循 **单定义原则**。

> 内联函数例外，但是也要定义相同
>
> 就是不需要ifndef 来限定，头文件中定义好，其他文件都包含这个头文件即可。而不是在多个头文件中分别复制一份。
>
> 但是和ifndef 不冲突，写在里面也没关系，没必要单独处理了。

指导原则之一是，不要为不使用的特性付出代价（内存或者时间） -- 默认应当选择最高效率的方案



- 命名方式

    >__ (两个下划线，保留给编译器使用)
    >
    >_N (一个下划线，一个大写字母，保留给编译器使用)
    >
    >_(一个下划线，保留表示全局标识符)
    >
    >str或者sz（表示以空字符结束的字符串）b（布尔值）p（指针）c（char）m_lpctstr ( m_ 表示 类成员变量，long point const str)

- [语句](./statement.md)

- [编译过程](./compile-process.md)

- for语法

    > ```c++
    > //C++ 中for循环的 condtion 只要是表达式语句即可
    > //现在的for 规则如下，可以不管，原文p130
    > for(for-init-statement condition ; expression){
    >     statement;
    > }
    > //foreach, c++ 11
    > for(double x : prices) 
    > for(double &x : prices)
    > for(double x : {1.0, 2.0, 3.0})
    > ```

- typedef

    > ```c++
    > //声明结构
    > double value; //value 是声明的名称，double 是声明的类型修饰
    > double * (*pFun)(double *, int); //p_fun 是声明的名称，double * (*)(double *,int) 是修饰
    > 
    > //typedef 就是对声明的修饰进行一个提取，给一个新名字，然后可以用这个名字进行声明
    > typedef double real;
    > real n = 1.0;
    > 
    > typedef double * (*p_fun)(double *, int);
    > p_fun p1 = ...; //左右结构 比嵌套结构更容易 输入和阅读
    > ```

- sizeof

    > ```c++
    > int *num = new int[3]
    > sizeof(*num) = 4; //即 num[0]，int
    > sizeof(num) = 8; //指针本身
    > ```
    >
    > c++中字节 和实现的基本字符集有关，
    >
    > 如ASCII，足够使用8位容纳，则c++字节是8位
    >
    > 如果使用unicode，则可能需要相邻8位来容纳，因此有些实现可能是 16或者32位
    >
    > 术语octet 专门表示8位的字节
    >
    > sizeOf 方法返回的字节数，即C++ 的字节

- [api](./api.md)

- [类型](./type/type-index.md)

- [stl](./stl/stl-index.md)

- [输入输出](./io/io-index.md)

- [方法](./function.md)

- [exception](./exception)

- [作用域](./scope.md)

- [动态分配](./dynamic-alloc.md)

- [存储结构](./storage-struct.md)

- 保留字

    > ```c
    > and or not //c 中需要包含iso646.h
    > ```

- [tips](./cpp-tips.md)

- [utils](./cpp-utils.md)

- 附录

    > 计数系统，保留字（关键字, 替代标记alternative token, 库保留名称），ASCII 字符集
    >
    > 运算符优先级，其他运算符（位运算，解除引用运算，alignof, noexcept）
    >
    > ```c++
    > int Lsj::*pt = &Lsj::name; //*pt 是一种标识符，也可以用于标识函数
    > Lsj lsj;
    > Lsj *l = new Lsj;
    > cout << lsj.*pt; 
    > cout << lsj->*pt ; 
    > //* 和 ->* 都是成员解除引用运算符
    > ```
    >
    > 



#### RTTI(Runtime Type Identification)

只适用于包含虚函数的类



dynamic_cast 运算符，转化失败返回空指针

typeid 运算符，返回一个指出对象的类型的值？

type_info 结构存储了有关特定类型的信息

```c++
if(Lsj * lsj = dynamic_cast<Lsj *> baseLsj){};
//引用类似，但是无空引用，直接引发异常
try{
  Lsj &lsj = dynamic_casy<Lsj &>(baseLsj);
}catch(bad_cast &){}
```

```c++
try{
  typeinfo info = typeid(*lsj); //lsj为空直接异常，typeinfo 随厂商实现而不同
}catch(bad_typeid){}
typeid(Lsj) == typeid(*lsj)
```



---

作者主页](http://www.research.att.com/-bs) , c++ faq ](http://www.parashift.com/c++-faq/)

c++ 编译器，ide等工具：https://www.bfilipek.com/2019/10/cppecosystem.html

cpp预编译器：https://gcc.gnu.org/onlinedocs/cpp/index.html#SEC_Contents

精选读物：附录H

More Exception c++
https://news.codecademy.com/bjarne-stroustrup-interview/

书籍列表：http://club.topsage.com/thread-361458-1-1.html

练习：https://www.patest.cn/

