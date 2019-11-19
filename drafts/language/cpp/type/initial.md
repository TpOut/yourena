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





