老的编译器可能不支持，或者默认关闭

0做分母的时候，很多编译器会生成一个无穷大的特殊浮点值来处理，cout显示为Inf, inf, INF



**程序中断**

cstdlib，abort() 中断，是否刷新文件缓冲区取决于实现

eixt() 退出，刷新文件缓冲区，不显示消息



**返回错误码**

例如cmath 的全局变量 errno



#### throw

throw 语句实际上是跳转

```c++
void f(){
  throw "something is wrong !";
  //
  throw Type;
}

try{
  f();
}catch(const char * s){ //catch 表示是一个处理程序
  
}catch(Type & t){  //即使声明引用，也是一个副本，但是引用可以兼容派生类
  
}catch(Type t){
  
} //如果catch 没有匹配，程序调用abort() 

catch(...){ //捕获任何异常
  
}
```

**异常规范**

c++98 新增，c++11 摒弃，以后可能会去除，建议不用，不好维护

```c++
double harm(double) throw(type); //可能抛出type 异常
double marm(double) throw(); //不抛出异常
```

新增的规范可用

```c++
double marm() noexcept; //有助于编译器代码优化
```

#### 栈解退(unwinding the stack)

p625，章15.3.6

常规的函数返回，只处理该函数放在栈中的对象；而throw 语句则处理try 和throw 之间整个函数调用序列放在栈中的对象。

而栈解退对于动态分类的指针，无法做到“自动”释放，需要自行处理。这样很容易造成内存泄漏，可以使用智能指针模版帮忙。

#### exception 类

exception 头文件，what 虚函数

stdexcept 头文件，logic_error、runtime_error；IDE 查看结构（p631, 章15.3.8描述使用时机）

如果有必要，应当从中继承



new 导致内存分配问题，c++ 最新处理方式是引发bad_alloc 异常。在new 头文件中

然而现行很多代码都是返回空指针，也因此，很多编译器提供标记来让用户选择

c++ 标准也有返回空指针的写法

```c++
int & pi = new (std::nothrow) int;
int * pa = new (std::nothrow) int[500];
if(pa == nullptr){}
```

**异常的问题**

意外异常，unexpected exception

未捕获异常，uncaught exception

默认情况下，两者都会导致程序异常终止，但可以修改程序对两者的默认行为



未捕获异常 首先调用 terminate() , 间接调用 abort()，来使程序终止

可以指定terminal() 的调用行为

```c++
typedef void (*terminate_handler)();
terminate_handler set_terminate(terminate_handler f) throw(); //c++98
terminate_handler set_terminate(terminate_handler f) noexcept; //c++11
```

意外异常 调用 unexpected() ，间接调用terminate，间接调用abort

```c++
//也可以修改 unexpected 函数行为
typedef void (*unexpected_handler)();
unexpected_handler set_unexpected(unexpected_handler f) throw();
...
//不过unexpected_handler 有一些限制
//可以调用 terminate, abort, exit
//可以再引发异常，会在最初发生异常的地方再次抛出，重新寻找处理器，如果无法找到，则查看bad_exception的处理器，如果还没有，则走terminal()流程。
//所以一般设计，可以在程序开头，重新定义并抛出bad_exception,并在相关处 catch(bad_exception)
void myUnexpected(){
  throw std::bad_exception(); //or just throw
}
```



异常不适用于模版

#### 其他

noexcept() //运算符