c的函数需要在被调用之前声明，叫做函数原型。（ANSI C 中是可选的）

而实现可以在声明的同时，或者在main函数之后。  

一般性的做法是，将声明放在头文件中。然后头文件被包含在主文件，并在某个主文件实现。



没有原型的函数，编译器默认其返回一个整型値。

* K&R C中，参数传递时，char short 会被提升成int, float会被提升成double  
* ANSI C对于没有原型的函数保留这种提升。但是有原型的不会。



ANSI C 中；

如果原型中的参数为空，则表示不限制，即参数列表在定义时确定

### 可变参数列表

需要看 api#stdarg.h

```c
//语法
float
average(int n_values, ...)
```

使用方式查找一下cpprefrence

虽然定义了函数的原型，但是`...`并不能表示确切的参数原型，所以会和K&R C的类型提升一样。且最少需要传入一个参数才能正确使用var_start。  



