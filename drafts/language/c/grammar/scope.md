# 作用域

作用域用来控制标识符的有效范围。  

### 常规

单文件的作用域有如下几种

* 文件作用域（全文件，在代码块之外声明，注意\#include延伸了文件）
* 函数作用域（只适用于语句标签，配合goto，所以语句标签在函数中必须唯一）
* 代码块作用域（花括号范围）
* 原型作用域（方法原型定义时，参数不能相同）

就近覆盖

### 链接属性

由于结构需要，一个程序的源文件会被拆分编译。有些标识符可能在多个文件中出现重复，那么是多个文件共享一个实体还是每个文件独立拥有实体？ 

这时候需要引入连接属性：

* external： 表示全是同一个实体  
* internal： 表示相同的文件中的是一个实体，不同的文件中的是不同的
* none：表示全是不同的实体；函数形式参数和代码块内声明的变量在缺省情况下具有none链接属性

```c
int b; //external
int c(){ //external
    int e;//none
    int f(int g); //f external(因为是函数，必然对应代码块之外的原型),
}
```

#### static

如果某个缺省声明在正常情况下具有external链接属性，static可以让他变为internal  

```c
static int b; //internal
int c(){ //external
    static int e; //static has no effect
}
```

#### extern

extern 为一个标识符指定external链接属性。 

标识符在源文件中第一次声明时使用extern才有效

```c
extern int b; //如果b在多处声明定义，则定义处的extern不是必须的，但是有助于可读性
static int o;
int c(){
    extern int e; //external
    extern int o; //still internal
}
```

