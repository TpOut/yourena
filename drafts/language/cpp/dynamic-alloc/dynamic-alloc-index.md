```c++
typeName * pointer_name = new typeName;
delete pointer_name; //只释放new 获取的指针，不要重复释放

int * psome = new int [10];
delete [] psome; //虽然delete能知道声明时的数组元素数，但是sizeof不能获悉
```

尽量不要把两个指针指向同一个地址，容易重复删

new 的变量存储在堆或者自由存储区中

