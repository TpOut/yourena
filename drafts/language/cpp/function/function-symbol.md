编译器对方法进行标记

因为c 中方法不需要重载，故方法标记中的修饰部分，只是简单的方法名，比如spiff 方法会标记为 _spiff

而c++ 中方法有重载之说，对spiff(int), spiff(double, double) 分别标记为 _spiff_i, _spiff_d_d 

总之，两个语言的标记方法必然不同，所以在c++ 编译器编译 c 的代码的时候，一定要一个标识符来区分两者的编译。

故有

```c++
extern "C" void f(); //表明使用c 的修饰方式
extern "C++" void f(); //表明使用c++ 的修饰方式
//默认是 c++
```



