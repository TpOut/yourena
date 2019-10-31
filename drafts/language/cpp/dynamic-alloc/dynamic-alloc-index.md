```c++
typeName * pointer_name = new typeName;
delete pointer_name; //只释放new 获取的指针，不要重复释放

int * psome = new int [10];
delete [] psome; //虽然delete能知道声明时的数组元素数，但是sizeof不能获悉
```

尽量不要把两个指针指向同一个地址，容易重复删

new 的变量存储在堆或者自由存储区中



```c++
delete delete[] 和 new new[] 对应
如果有多个构造函数，则需要用相同的方式 new 或 new[]
如果实在不行，则可以把不兼容的初始化成空指针，这样不管是delete 还是delete[] 都兼容
（主要是构造函数初始化的场景，不针对中途赋值可能出现的new）
```



