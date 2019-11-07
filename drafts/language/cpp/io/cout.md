c++ 标准允许输出区域性变化，如欧洲 的 2.54 以 2,54 展示。 头文件locale 提供了机制。



格式化部分在p741，章17.2.4有说明

cout   << hex << 16进制

​            << dec << 10进制

​            << oct  << 8进制

cout.put() //历史原因而存在，p49页有解释



cin.put(char)

> 类似c 的putchar()
>
> C++ 标准要求只有一个原型，但是很多实现有3个，cin.put(signed char)、cin.put(unsigned char)
>
> 这个时候如果传递int参数会出现错误，需要强转

```c++
cout.setf(ios_base::fixed, ios_base::floatfeild) //使用定点表示法
​        .sef(ios_base::boolalpha)
​        .pricision(2)
​        .setf(ios_base::showpoint)
         .width()
ios_base::fmtflags init;
init = os.setf(...);
os.setf(init);

> 不支持ios_base， 则是ios
```



```c++
char * ps = ...;
cout << "address : " << (char *)ps //如果是char * 类型，cout 一般输出ps 的内容，需要强转才会输出地址
```



cout.write

在p739 章17.2.2 中有说明

flush 刷新缓冲区（实际上是调用flush(cout) ），endl 刷新缓冲区并且插入一个换行符





