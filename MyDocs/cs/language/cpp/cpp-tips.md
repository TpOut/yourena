main方法，执行完毕默认返回0



代码结构分成三个部分（术语可以叫做 文件、单元）：

header : 包含结构声明和使用这些结构的原型函数

> 不要把函数定义写在头文件中
>
> 一般包含：
>
> 函数原型、#define或const定义的符号常量、内联函数
>
> 结构声明、类声明、模板声明

source code：包含与结构有关的函数的代码

source code:  包含调用与结构相关的函数的代码



头文件（函数定义）和库文件（函数实现）是分开放置的，所以可能你包含了头文件，但是编译还是找不到对应的库文件。

> 在 CMakeLists.txt 中，add_executable 中，如果头文件只有方法声明，可以不包含头文件



cv 限定符（const， volatile）

- const作用于声明，表示该声明不可修改其对应的值（const double ar[] 被解释成 const double *ar）  

    非类中 const 相当于 c 中的const static（这里的static 表示内部链接性，但类中又有 static const，其中的static 表示静态存储）

    这是为了方便将const 写在头文件中设置的，可以用extern 修改成外部链接性

    >c99允许const作为数组长度，但是数组必须是变量数组；c++标准则没有  
    >
    >c中const变量是存储在某个地方，而编译器无法在编译的时候获取这个地方。所以无法作为数组长度

- volatile 告诉编译器，变量可能实时变化，不要进行“缓存”优化，每次都要读取最新值。

mutable：结构变量为const 时，指出某个变量有特权，可以被修改



访问控制：public、protected、private，friend



如果选项超过2个，在switch 支持的情况下，应当优先使用switch 而不是 if else



define的建议替代

- 常量 const , 可以指定类型、支持复合类型（[]）、拥有作用域。

- `\#define Cube\(x) x*x*x `

    表达式，尽量使用inline代替上面这种容易出现意外的define方式  

- 泛型，使用模板替代

更多的一些老式写法改写成新标准的建议，参考附录I



++type ; type++
如果是基础类型，注意此时的返回值是type，而不是单纯的type + 1（会有数值转换的处理）
如果是自定义类型type， type++ 会先复制一个type，然后type+1，并返回；
比 ++type 的效率略低

同时不要在一个表达式中多次使用递增、递减，会产生无法预期的结果
c++ 不保证是每次使用递增就 +1，还是整个语句结束后 +n



空指针：（void *）0，NULL， nullptr（c++11）



#### 断言

assert , static_assert



#### 低级编程

放宽了POD标准

一个计算机系统可能要求double值的内存地址为偶数，另一个要求起始位置为8的整数倍。

可以使用 alignof() 来获取 某个类型或对象的对齐要求

