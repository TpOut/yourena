bind1st ， bind2ed

bind (可替代上面两个)

men_fn，能将成员函数当做常规函数进行传递

reference_wrapper，创建行为像引用但可被复制的对象

function，统一处理多种类似函数的行为



可调用类型现在已经很多了，包括函数名，函数指针，函数对象，有名称的lambda

使用模板时，如果传递一个可调用类型，模板需要根据这么多类型进行判断，分别实例化不同的类型，所以效率会很低

此时根据可调用类型的调用特征标(call signature) 来进行区分。如

```c++
double f(double, double);

class F{
    double operator()(double, double){
        ...
    }
}

auto lf = [](double, double){return double}
```

将被区分为一种可调用类型。用头文件functional 中的function 包装，可以表达为同一个类型。

```c++
std::function<double<double, double>> fddd; // = f or F() or lf
```



