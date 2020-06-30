# 字符串

c中不存在"string"数据类型，字符串就是一串以NUL字节结尾的字符，但是NUL只是作为字符串终止符，不被看做字符串的一部分

### 常量

"xyz" 实际上就是一个指向x的指针  

```c
char *str = "xyz"
char *str1 = "xyz"
//K&R C不会有问题，两个是不同的地址
//ANSI C允许两个指向同一个变量，所以编译器可能不允许或者提供选项配置
*str1 = 'a';  //试了下编译器直接报错，SIGSEGV
```



#### 方法

strlen，strcat, strcpy 等



