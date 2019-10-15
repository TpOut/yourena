# 枚举

```c
//定义
enum Enum_Type { CUP, PINT, QUART[=4], T} 
    type_cup; //可以在定义时声明
//单独声明
enum Enum_type type_cup;
```

枚举实际上是整数型存储，默认情况下，从0开始计算，否则为前一个的值+1。

既CUP是0，PINT是CUP + 1 = 1，T是 QUART+1 = 5

