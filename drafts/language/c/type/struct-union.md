# 结构和联合

### 结构

属于标量类型

```text
struct SAMPLE {
  int age;
  char *name;
} y[20], *z;

# 之后可以
struct SAMPLE x;

//合并
struct INIT {
  int age;
  short b[10];
  struct SAMPLE c;
} x = {
    10,
    {1,2,3}
    {25, 'a'}
}
```

```text
struct SELF; # 在互相引用时，可以先进行不完整声明

struct SELF_REF {
    int a;
    # struct SELF_REF b # 非法，会无限循环b
    struct SELF_REF *b; # 合法，因为指针大小已知，而且*b一般会指向另一个结构  
    int c;
}
```

### 存储

结构的地址和结构第一个成员变量的地址是一样的（可以通过强转进行切换）

不能通过第一个变量，`+1`来直接访问下一个变量

结构成员会有边界对齐的操作，而系统原因，结构的初始位置必须和第一个成员一样，既不能有空白。  

可以通过offsetof 宏获悉结构中制定成员的存储偏移。

#### 位段\(bit-field\)

声明和结构类似，成员实际上存储于一个或多个整型变量

可以指定每个成员的位数，

移植性不高（比如int的解释，比如最大位数的兼容，比如实现时地址是ltr或者rtl）

```c
struct CHAR {
    // 类型只能是int , unsigned int, signed int
    unsigned ch : 7;
    unsigned font : 6;
    unsigned size : 19; //16位机器将报错
}
```

还有一个使用的目的是，每个位段成员都很小，相对于移位操作，可以可读性更高的访问：

```c
看原文吧。。
```

### 联合

```text
union {
   float f;
   int i;
} fi = {5.0};

fi.f = 3.14159;
printf("%d\n", fi.i);
```

声明和结构类似，但是i和f 写入/读取的是内存中的同一个位置  
初始化时只能初始化为第一个成员的类型

联合的长度是最长成员的长度

