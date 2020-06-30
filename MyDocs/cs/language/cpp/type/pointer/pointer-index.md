

```c++
int* p1, p2 //创建一个指针和一个int变量
```

使用指针赋值之前，一定保证指针的地址是已知的

值为0的指针被称为空指针

```c++
int * psome = &stacks[0] = stacks; //数组指针
*psome = psome[0];
*(psome + 1) = psome[1];

```

数组和指针，在执行时，都会被转换成指针

arrayname[i] besoms *(arrayname + i)

pointername[i] becomes *(pointername + i)

#### 智能指针模版类

是行为类似于指针的类对象，可以做到退出作用域的时候，自动析构，用来帮助管理动态内存。

```c++
auto_ptr, // c++98，c++11摒弃
//c++11 
unique_ptr, 
shared_ptr
//还有一种，但是这本书不讨论
weak_ptr
```

```c++
auto_ptr<double> pd(new double); //另两个类似，注意不要传递不是new 出来的指针

auto_ptr<double> p = pd; //如果是p 和pd 都删除，会出现问题
//解决方法有3
//定义赋值运算符，执行深复制
//建立所有权，只有一个智能指针拥有对象，然后只有这个指针需要执行删除
//创建引用计数，只有最后一个指针过期的时候，才执行删除

//auto和unique是第2种策略
//share是第3种策略
```

```c++
//使用所有权规则
//pd不再保有之前的指针，如果后续使用，会产生问题
auto_ptr 没有提示，在运行时如果使用，则产生错误
unique_ptr 在赋值时，编译器会提示错误 //如果 unique_ptr 是个临时右值，则可以赋给 unique_ptr 左值
  
ps2 = std::move(ps1); //如此可以赋值
```

```c++
//unique 的另一个优点，是实现了数组的变体
unique_ptr<double[]> pda(new double(5));
```

如果编译器没有shared, 可以使用Boost 库提供的

