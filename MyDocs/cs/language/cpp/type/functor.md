

函数对象，又叫函数符（functor），是可以以函数方式和`()`结合使用的任何对象。包括函数名，指向函数的指针和重载了`()`运算符的类对象（即定义operator()()的类）,还有lambda



我们回头来看

for_each(begin, end, ShowReview);

第三个参数可以是常规函数，也可以是函数符，但是这是如何实现的呢

首先，不能声明为函数指针，因为函数指针 特征标 包括参数类型，而容器应该包含任意类型，所以预先无法知道应该使用哪种参数。

STL 通过模版解决:

```c++
//for_each 原型概要
template<class InputIterator, class Function>
Function for_each(InputIterator first, InputIterator last, Function f);

此时的Function 可以是函数，也可以是类，即上面说的函数符
```

#### 函数符概念

如果不用参数，则叫做生成器（generator）

如果需要一个参数，则叫做一元函数（unary function），如果还返回bool，叫做谓词(predicate)

如果需要两个参数，则叫做二元函数（binary function），如果还返回bool，叫做二元谓词



list 模版，有一个需要谓词的remove_if() 成员，

假设需要实现移除 >num 的元素，

如果使用函数实参，则num 会被写死在函数里面，每次变化需要重新定义

而如果使用类实参，则num可以通过构造函数传递，每次变化重新实例化即可

所以将类函数符理解成 函数函数符的 适配器。



常用STL 函数符

transform(),

functional(function.h) 中包含一些常规运算操作的函数符（老式的乘法是times）



每种函数符都是自适应的（在现在认为，都是模版化的）

每个函数符都有  标识参数类型和返回类型的typedef 成员（result_type, first_argument_type, second_argument_type）

如此，函数适配器对象 可以使用函数对象，并认为typedef 成员存在。

然后STL 的binder1st 和binder2nd 可以自动将 函数对象，转化成对应的 函数适配器对象

```c++
binder1st(f2, val) f1;
//此时调用 
f1(x) = f2(val, x)；
```

上面的赋值操作还是有些麻烦，可以直接生成：

```c++
bind1st(f2, val);
```
