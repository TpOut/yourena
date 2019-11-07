STL 中算法库分成4组

- 非修改式序列操作
- 修改式序列操作
- 排序和相关操作
- 通用数字运算

本来4组都在algo.h 中

后来前3组在algorithm, 第4组在numeric



数据地址不变的是 in-place algorithm ； 变化的是 copying algorithm



有些函数有以_if 结尾的变体，这个变体 将函数应用于容器元素得到的结果来执行操作

（如 replace_if 返回true，才执行替换操作）

```c++
sort();
next_permutation(); //排列组合
```



string 虽然不是STL的类，但是设计的时候考虑了STL，所以有一些end(), begin()

count()，

complex 类提供 复数 类模版，



vector 支持面向容器的操作，如排序、插入、重新排序、搜索、数据转移等

valarray 类模版面向数值计算，不是STL 的一部分，没有push_back, insert 方法，但是为很多数学运算提供了一个简单、直观的接口

array是为了代替内置数组而设计的，虽然也不支持push_back, insert，但是包含了多个STL 方法，如 begin(), end()。。。



```c++
//valarry 没有 begin， end 方法
sort(&va[0], &va[10]);
//是否可以像上述这样使用？
//6种编译器都是可行的
//但是如果va 的内存块和预留给堆 的内存块相邻，会出现问。具体什么原因呢？直接无法访问？
//替代方案有，c++11
sort(begin(va), end(va)) //模版函数
//简单看了下，begin 还是调用 va.__begin__, 没明白为什么就可以了？
```



valarray 特性

```c++
valarray<bool> vbool = numbers > 9;
//vbool[i] 被设置为 numbers[i] > 9

val[slice(1,4,3)] = 10;
//则索引 1，4，7，10 被设置为10，还有gslice 表示多维
```



initializer_list（c++11）

容器类都包含一个 initializer_list<T> 作为参数的构造函数，所以可以

```c++
std::vector<int> payments({1,2,3,4})
//考虑c++ 通用语法，可以使用{} 来构造函数
std::vector<int> vi{10};
//在此时，如果类支持 初始化列表 构造，则选择初始化列表构造，否则正常构造
std::vector<int> vi(10); //如果等同于此，则是创建10个未初始化的元素
std::vector<int> vi({10}); //如果等同于此，创建一个元素值为10
```

