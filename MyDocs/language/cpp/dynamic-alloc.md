```c++
typeName * pointer_name = new typeName;
delete pointer_name; //只释放new 获取的指针，不要重复释放

int * psome = new int [10];
delete [] psome; //虽然delete能知道声明时的数组元素数，但是sizeof不能获悉
```

尽量不要把两个指针指向同一个地址，容易重复删

new 的变量存储在堆或者自由存储区中

```c++
delete delete[] 和 new new[] 对应
如果有多个构造函数，则需要用相同的方式 new 或 new[]
如果实在不行，则可以把不兼容的初始化成空指针，这样不管是delete 还是delete[] 都兼容
（主要是构造函数初始化的场景，不针对中途赋值可能出现的new）
```

#### NEW

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
