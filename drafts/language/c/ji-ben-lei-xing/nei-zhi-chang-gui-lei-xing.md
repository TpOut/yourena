# 数组

数组名 = 指向数组第一个元素的地址

在表达式中使用时， 等同一个指向数组第一个元素地址的常量指针，所以不能被修改

* sizeof 是个例外
* &也是：产生一个指向数组的指针

有明确的元素数量。

```c
int arrays[10];  
int *c;
c = &arrays[0] # 等同于 c = arrays  

arrays[n] # 等同于 *(arrays + n)，应该是单纯的转换，因为 n[arrays] 也有这样的效果
```

```c
*ap = array + 2;

ap[0] # c中的下表引用和间接访问表达式是一样的
```

```c
# 可以省略容量，编译器处理
int arrays[] = {1,2,3}
# 不完整初始化，补0
int arrays[5] = {1,2,3}

# 此时表示字符初始化列表
char arrays[] = {'h','e','l'}
char arrays[] = "hel"; 

char *arrays2 = "hello"; # 其他时候都表示常量
```

当数组作为实参传递给函数的时候，会被转化成一个指向数组开始位置的指针。

### 多维数组

以二维数组为例，传递给函数时候需要的格式，初始化类似：

```c
int matrix[3][10];
func(matrix);

void func(int (*mat)[10]);
void func(int mat[][10]);
```

注意：指向整形指针的指针和指向整型数组的指针 是不一样的。

