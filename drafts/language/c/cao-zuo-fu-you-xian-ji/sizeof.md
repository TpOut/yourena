# sizeof

判断操作数的类型长度，以字节为单位

```c
sizeof( int )
sizeof x //如果是表达式，虽然大部分情况是变量，可以没有括号

sizeof( a = b + 1 ) //只是长度计算，所以不需要求値，既a并没有被赋值

int a[10];
sizeof(a) //返回整个数组的长度
```

