> 主要内容摘自《c++ primer 6th》，部分内容来自 网络和开源项目。 
>
> 设计上是兼容c 的，很多新语法考虑 C++ release 2.0 ; C++ 11



一切都遵循 **单定义原则**。

> 内联函数例外，但是也要定义相同
>
> 就是不需要ifndef 来限定，头文件中定义好，其他文件都包含这个头文件即可。而不是在多个头文件中分别复制一份。
>
> 但是和ifndef 不冲突，写在里面也没关系，没必要单独来记忆了。

指导原则之一是，不要为不使用的特性付出代价（内存或者时间） -- 默认应当选择最高效率的方案



- 命名方式

    >__ (两个下划线，保留给编译器使用)
    >
    >_N (一个下划线，一个大写字母，保留给编译器使用)
    >
    >_(一个下划线，表示全局标识符)
    >
    >str或者sz（表示以空字符结束的字符串）b（布尔值）p（指针）c（char）m_lpctstr ( m_ 表示 类成员变量，long point const str)

- [语句](./statement.md)

- [编译过程](./compile-process.md)

- [语法](./grammar.md)

- [api](./api.md)

- [exception](./exception)

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

- [作用域](./scope.md)

- [动态分配](./dynamic-alloc.md)

- [存储结构](./storage-struct.md)

- 保留字

    > ```c
    > and or not //c 中需要包含iso646.h
    > ```

- [tips](./cpp-tips.md)



lambda 表达式，是函数指针和函数符（函数对象）的替代品



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