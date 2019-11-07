```c++
//声明结构
double value; //value 是声明的名称，double 是声明的类型修饰
double * (*pFun)(double *, int); //p_fun 是声明的名称，double * (*)(double *,int) 是修饰

//typedef 就是对声明的修饰进行一个提取，给一个新名字，然后可以用这个名字进行声明
typedef double real;
real n = 1.0;

typedef double * (*p_fun)(double *, int);
p_fun p1 = ...; //左右结构 比嵌套结构更容易 输入和阅读
```

