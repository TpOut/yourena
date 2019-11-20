double 转换成 float， 如果不足保值，结果不确定

float 转换成 int，如果不足保值，结果不确定 （因为c++ 没有定义）

long 转换成 short，如果不足保值，复制右边字节



#### 浮点型

float 

double 

long double

float 至少32，double 至少48，且三者递增

cfloat 文件

float 保证6位有效性

如果不支持 9.0f， 可以使用 (float)9.0

#### 整形

c++ 标准的整形长度借鉴自c，在其已有的基础上，添加了一条

long long 至少64位，且比long 要长



int 对于机器而言是最“自然”的长度，所以一般性使用int

一般除非大型数组，否则没啥必要用short，当然尽量用（编写角度）

如果整数值可能大于16位整数，用long；可以增加移植性

如果超过20亿，则使用long long



c++中字节 和实现的基本字符集有关，

如ASCII，足够使用8位容纳，则c++字节是8

如果使用unicode，则可能需要相邻8位来容纳，因此有些实现可能是 16或者32位

术语octet 专门表示8位的字节

sizeOf 方法返回的字节数，即C++ 的字节



climits 文件是由编译器厂商提供的，所以不同系统的编译器会有不同的文件。



进制： 42，042， 0x42

对于不带后缀的10进制，存储最小选择：int， long， long long

对于不带后缀的16、8进制，最小选择：int，unsigned int, unsigned long, long long/unsigned long long

![企业微信截图_cbb21020-b55d-4e36-9ce0-632c8df9b8da](%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_cbb21020-b55d-4e36-9ce0-632c8df9b8da.png)



整形提升：bool，char，unsigned chat, signed char, short 的都会被转化成int

如果short 比 int 短，unsigned short 被转换成 int；

如果两者的长度相同，unsigned short 被转换成 unsigned int

wcahr_t 则被提升成第一个宽度足够的类型：int、unsigned int、long、unsigned long





#### 类型转化

算术类型转换：

- 自动转换

    涉及两种类型运算时，较小的类型被转换成较大的类型 

    （包括整形提升， 包含于 **校验表**，详细见 p64、65）

- 强制转换

    （long）var ;  int (var)

常规的类型转化太过松散

```c++
//单纯的向上转类型（is-a 才行）
dynamic_cast<type-name>(expression) 
//只改变const, volatile
const_cast<type-name>(expression) 
//type-name和expression相互可以隐式转化时，可以用于基本数值类型转换
static_cast<type-name>(expression) 
//比较底层，有点类似于将某个地址的存储强转成某个类型，具体在p652，有一些限制
reinterpret_cast<type-name>(expression) 
```





```c++
//auto 不能作用在列表初始化时 (试了一下会初始化成initialize_list)
auto a = {1,4,5}; //not ok

int a[] = {};
auto b = a; //ok
```



cctype，有很多类型相关的常用方法



c++ 98 : char, wchar_t  ; c++ 11 : long long , usigned long long , char16_t, char32_t





#### 初始化



```c++
int num(432) ; // c++ 赋值语法

int num = {} //叫做列表初始化，C++11
int num{1} //也可以不传等于号

432ULL

int *pi = new int (6);

//c++ 11
int *pi = new int {6};
struct where {double v1, double v2}
where * one = new where(2.5, 3.5);
int * ar = new int[2]{2, 4, 5, 6};
```



2.54e+8

8.33E-4



通用编码名：\u00E2teau ; \U..



列表项初始化，{}，禁止类型缩小转换；

如果有些实现不支持，需要添加static，如：

```c++
某些版本的 int array[3] = {} 不行，则使用 static int array[3] 初始化
```







