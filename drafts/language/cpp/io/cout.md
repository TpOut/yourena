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





