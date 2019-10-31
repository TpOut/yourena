指定位置的new，默认行为 基本上就是把地址强制转化成 void* 返回（c++ 允许重载）

```c++
char arr[50];
double *p_d = new(arr + 5 * sizeof(double))double[N];
//此时arr 的第5个double 位之后的N个double 位被赋值给p_d
//p_d 的地址不需要也无法delete
```

```c++
//当针对对象的时候
Lsj lsj = new (buffer)Lsj;
delete[] buffer; //只能删除buffer 指针
lsj -> ~Lsj(); //所以需要主动调用析构函数，但是要在delete[] buffer之前调用
//如果有覆盖操作，则按覆盖顺序反序释放
```



new 失败的时候，

最开始 c++ 是返回空指针，现在引发异常 std:bad_alloc 

运算符new 和new[] 分别调用如下函数：

```c++
//分配函数
void * operate new(std::size_t);
void * operate new[](std::size_t);
```

 对应delete 和delete[] 

```c++
//释放函数
void operate delete(void *);
void operate delete[](void *);
```



```c++
//自动重载
int * pi = new int;
=>
int * pi = new(sizeof(int));

int * pa = new int[40];
=>
int * pa = new(40 * sizeof(int));

int * pa = new(buffer) int;
=>
... new(sizeof(int), buffer)
  
int * pa = new(buffer) int[40];
... new(40 * sizeof(int), buffer)
```



