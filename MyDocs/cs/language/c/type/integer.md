# 整型

| 类型 | 最小范围 |
| :---: | :---: |
| char | 0~127 |
| signed char | -127~127 |
| unsigned char | 0~255 |
| short int | -32767~32767 |
| unsigned short int | 0str[] = ; ~ 65535 |
| int | -32767~32767 |
| unsigned int | 0 ~ 65535 |
| long int | -2147483647~2147483647 |
| unsigned long int | 0~4294967295 |

```text
//两者相同，即具有int以外的声明时，整形变量的声明可以省略int  
unsigned short     a;
unsigned short int a;
```

即short int至少16位； long int 至少32位, 且比int 长； 而int没规定长短，但至少比short int 长；

signed关键字一般用于char, 其他整型默认为signed

缺省的char声明，在不同的编译器（由于编译器实现）可能是signed，也可能是unsigned。  
char型变量的值在signed char和 unsigned char的交集中时，具有强可移植性（ASCII字符都在）；只有当char型变量显式声明为signed和unsigned时，才对它执行算数运算。

整形的特点在[limits.h](../api.md#limits-h)中有说明

### 声明

```c
int a = 128u //u后缀
```

### 整形提升

char 和 short计算之前会被转换成普通int  

但是编译器可以优化，不进行提升

### 算术转换

转换优先级越来越高（既 double + int 时，int 被转换成 double）

long double ; double; float; unsigned long int; long int; unsigned int; int

```c
int a = 5000;
int b = 25;
long c = a * b; //16位机器会溢出
long c = (long)a * b;
```

int转换成float 可能损失精度，float 转换成int 可能产生未知结果  

