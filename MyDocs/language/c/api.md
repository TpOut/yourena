### 常量

其他常量请在对应头文件里查看

> NUL 字节指字节模式为全0的字节，即`\0`字符  
> NULL 指一个值是0的指针，所以NUL和NULL的值是一样的；NULL写在stdio.h；
>
> NUL没有预定义，需要自定义

### stdlib.h

常量：EXIT\_SUCCESS, EXIT\_FAILURE 

#### 算数

绝对值：abs ; labs

除法：div ; ldiv

#### 随机数

伪随机数：rand\(如需调整范围，用取模处理\) ; srand  

#### 字符串转化为数字

基数为10：atoi ; atol  

基数可以制定：strtol ; strtoul（而且第二个参数可以被返回不合法的字符）

#### 终止执行

abort\(SIGABORT\) , atexit\(注册退出函数\), exit

#### 环境

getenv

#### 系统执行命令

system

#### 排序和查找

qsort  , bsearch  

### stddef.h

container\_of : 

### math.h

mathcall.h

除了stdlib中的算数部分，剩余的数学函数声明都在这里。返回值和绝大部分参数都是double　　

函数的参数不合法，称作domin error。此时函数返回一个编译器定义的错误值。并且在errno中存储EDOM値；函数的结果过大或者过小，称作range error。此时函数过大返回HUGE\_VAL，过小返回0。可能会在errno中设置ERANGE。

#### 三角函数

sin ;  cos ; tan ; asin ; acos ; atan ; atan2 

#### 双曲函数  

sinh ; cosh ; tanh 

#### 对数和指数函数

exp ; log ; log10

#### 浮点表示形式

用于不兼容浮点格式的机器

指数和小数：frexp ; ldexp 

整数和小数：modf  

#### 幂函数

pow ; sqrt

#### 顶数、底数、绝对值和余数

floor ; ceil ; fabs ; fmod

#### 字符串转化浮点

atof ; strtod  

### time.h

\(机器无法提供，或者提供的値太大，返回-1\)  

偶尔会出现“闰秒”，所以秒数可能是61

#### 处理器时间

程序执行之后的消耗时间：clock  \(需要除以CLOCKS\_PER\_SEC\)

#### 当天时间

time  ;

#### 日期和时间的转化

ctime （转化成字符串，内部可能是调用了asctime）; difftime（计算差值）  

gmtime \(UTC\GMT\); localtime

> time\_t　在很多编译器定义为32位，这样从1970到2038年的秒数会溢出

asctime,  strftime

mktime\(可以简单判断日期属于星期几\)

### setjmp.h

类似goto语句的机制

setjmp  , longjmp  

### signal.h 

SIG\_ERR, SIG\_DFL, SIG\_IGN  

\(在我的环境下，信号是在signum-generic.h中，如SIGABORT\) 

raise （可以手动触发信号，主要用来测试，避免goto的效果）

void \(\*signal\(int sig, void \(\* handler\)\(int\)\)\)\(int\)  

sig\_atomic\_t  

### stdarg.h  

可变参数

类型 va\_list 和三个宏 : va\_start , va\_arg , va\_end

arg参数需要用va\_start进行初始化  

### assert.h  

添加 -DNDEBUG 编译器命令或者在assert.h的文件中添加`#define NDEBUG` 可以消除所有断言

assert 

### locale.h  

setlocale  

#### 数值和货币格式

localeconv  

### limits.h  

数值的最大最小值

```c
UCHAR_MAX
UINT_MAX
```

### float.h

FLT\_MAX,... //浮点数大小

### string.h

strcoll \(根据当前locale的LC\_COLLATE\); strxfrm ； strdup

memchr  
memcmp  
memcpy  
memmove  
memset

|       函数名       |                             作用                             |                    备注                     |
| :----------------: | :----------------------------------------------------------: | :-----------------------------------------: |
|     strlen\(\)     |                             长度                             |                返回的是uint                 |
| strcpy\(\)/strncpy |                             拷贝                             | 注意越界,可以调用strcpy\(..., size\_t len\) |
| strcat\(\)/strncat |                             拼接                             |                    同上                     |
| strcmp\(\)/strncmp |                             比较                             |                   字典序                    |
|     strchr\(\)     |                    查找第一个匹配字符位置                    |                                             |
|    strrchr\(\)     |                   查找最后一个匹配字符位置                   |                                             |
|    strpbrk\(\)     |               查找一组字符中第一个匹配字符位置               |                                             |
|     strstr\(\)     |                          查找字符串                          |                                             |
|     strspn\(\)     | 统计属于某一组字符中的个数，直至遇到不属于该组字符的字符为止 |                                             |
|    strcspn\(\)     |                                                              |                与上一个取反                 |
|    strtrok\(\)     |                          去除分隔符                          |               会修改源字符串                |
|    strerror\(\)    |              获取操作系统errno对应的描述字符串               |                                             |

### ctype.h

|   函数名    |                          作用                          |                   备注                    |
| :---------: | :----------------------------------------------------: | :---------------------------------------: |
|   iscntrl   |                          控制                          |                                           |
|   isspace   |       空格，换页，换行，回车，制表符或垂直制表符       |                                           |
|   isdigit   |                          0~9                           |                                           |
|  isxdigit   |                     0~9, a~f, A~F                      |                                           |
|   islower   |                          a~z                           | 自己直接判断 &gt;a,&lt;z 的方式移植性不足 |
|   isupper   |                          A~Z                           |                                           |
|   isalpha   |                  islower and isupper                   |                                           |
|   isalnum   |                  isalpha and isdigit                   |                                           |
|   ispunct   | 标点符号，任何不属于数字或字母的图形字符（可打印字符） |                                           |
|   isgraph   |                      任何图形字符                      |                                           |
|   isprint   |         任何可打印字符，包括图形字符和空白字符         |                                           |
| tolower\(\) |                                                        |                                           |
| toupper\(\) |                                                        |                                           |

