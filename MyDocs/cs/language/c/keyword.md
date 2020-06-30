[toc]

### sizeof

判断操作数的类型长度，以字节为单位

```c
sizeof( int )
sizeof x //如果是表达式，虽然大部分情况是变量，可以没有括号

sizeof( a = b + 1 ) //只是长度计算，所以不需要求値，既a并没有被赋值

int a[10];
sizeof(a) //返回整个数组的长度
```

### typedef

用于简化声明

```c
typedef struct {
  int age;
  char *name;
} Simple;

//然后可以
Simple x;
```

```c
typedef char * ptr_to_char;
//然后可以
ptr_to_char a;
```

define陷阱：

```c
char *a, b; //表示a是指针，b不是
  
#define ptr_to_char char *
ptr_to_char a, b; //a是指针，b不是

typedef char * ptr_to_char;  
ptr_to_char a, b; //a，b都是指针
```

### volatile

```c
if( value ){
    printf( "True\n" );
}else{
    printf( "False\n" );
}
if( value ){
    printf( "True\n" );
}else{
    printf( "False\n" );
```

如果在第二个if之前，value被信号处理函数异步修改，那么应该产生不同的结果。  

可是如果value没有被声明成volatile , 那么代码段可能被优化为：

```c
if( value ){
    printf( "True\n" );
    printf( "True\n" );
}else{
    printf( "False\n" );
    printf( "False\n" );
}
```

既可能出现非预期的结果  

### const

用于限定修饰的値不可更改。 

##### 修饰常规变量：

```c
//两者效果相同，a的値将不再可以修改
//所以声明的时候要初始化
int const a;
const int a;
```

define比较： 

define常量进行纯文本替换，可以用来声明数组的长度，而const只能修饰变量

##### 修饰引用（既指针）

```c
//指向整形常量的指针，可以修改指针的值，但是不能修改指向的整形的值
//理解成 *pci 是常量
int const *pci
//指向整形的常量指针，无法修改指针的值，但是可以修改指向的整形的值
//理解成 pci 是常量
int *const cpi

int const * const cpci //都不能修改
```

### static

一个是[修改作用域]，一个是[修改存储类型]

