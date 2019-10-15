# 预处理

提供了一种语法，在编译的过程中处理文本

### 关键词

```c
#include //在预处理阶段，进行文件内容 替换
#define //在预处理阶段，单纯的进行文本替换
#if condition//如果后面紧跟的条件不是0，那么执行endif之间的代码 
#elif //if分支
#endif //配合if使用
```

### 注释的处理

除了上述关键词有对应的处理，所有的注释也都会被替换成一个空格。

### 使用解释

先说define，本会被替换，可以实现一般的常量效果

{% page-ref page="define.md" %}

然后说include

{% page-ref page="include.md" %}

### 编译时修改

有些编译器允许在命令行中定义标识符，

如 int array\[ARRAY\_SIZE\];

可以编译的时候使用

```bash
# -Dname 修改値
cc -DARRAY_SIZE=100 prog.c
# -Uname 忽略値
```

### 条件编译

```c
#if
#if defined(symbol) && !defined(symbol)
#ifdef
#ifndef 
#else
#elif
#endif
```

### 其他

```c
//允许生成错误信息
#if
    ...
#else
    #error not this

//主要用于其他语言转换为c, number修改 __LINE__ , string 修改 __FILE__
#line number "string"

//编译器不同，不可移植性
#progma 
```

