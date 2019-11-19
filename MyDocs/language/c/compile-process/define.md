

### 常规define

```c
#define name stuff   

#define DEBUG_PRINT printf("File %s line : "\
                        "x = %d,"\
                        __FILE__, __LINE__,\
                        x
                    )
```

name 会被替换成 stuff

```c
#define NORMAL 30
int main(){
  int a = NORMAL;
  printf("%d", a);
  return EXIT_SUCCESS;
}
```

打印结果就是30 

### 宏\(macro\)

```c
#define name(parameter-list) stuff

#define SQUARE(x) ((x)*(x))
```

主要在于紧跟着的`()`

#### 技巧

```c
//适用于字符串常量
#define PRINT(FORMAT, VALUE)    \
        printf("The value is " FOMRAT "\n", VALUE)
PRINT( "%d", x + 3);
```

```c
//在宏参数中，#arg 表示显示 arg的文本
#define PRINT(FORMAT, VALUE)    \
        print("The value of " #VALUE    \
        " is " FORMAT "\n", VALUE
PRINT("%d", x + 3);
//结果
The value of x + 3 is 25        
```

```c
//在宏参数中，## 可以动态拼接 标识符（变量名等）
#define ADD_TO_SUM(sum_number, value)    \
        sum ## sum_number += value    
ADD_TO_SUM(5, 25)
//相当于
sum5 += 25 //注意sum5的合法性
```

#### 实用性

类似泛型

```c
#define MAX(a,b) ((a) > (b) ? (a) : (b)) 

#define MALLOC(n, type) \
        ( (type *) malloc ( (n) * sizeof(type) ) )
```

### 重定义

```c
#undef name 
```

 如果要修改定义，必须先移除

