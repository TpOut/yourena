double 转换成 float， 如果不足保值，结果不确定

float 转换成 int，如果不足保值，结果不确定 （因为c++ 没有定义）

long 转换成 short，如果不足保值，复制右边字节



算术类型转换：

- 自动转换

  涉及两种类型运算时，较小的类型被转换成较大的类型 

  （包括整形提升， 包含于 **校验表**，详细见 p64、65）

- 强制转换

  （long）var ;  int (var)

其他类型转换

- static_cast<targetType>(srcTypeValue)
- Dynamic_cast<targetType>(srcTypeValue)



```c++
//auto 不能作用在列表初始化时 (试了一下会初始化成initialize_list)
auto a = {1,4,5}; //not ok

int a[] = {};
auto b = a; //ok
```



cctype，有很多类型相关的常用方法











