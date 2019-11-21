#### 时间

函数clock()，

返回程序开始执行后 流逝的系统时间。返回值不一定是秒，而且可能是long， unsigned long 或其他

##### ctime

```c++
time(0) //返回当前时间
CLOCKS_PER_SEC //clock() 函数对应的每秒时钟数
```



#### 随机数

```c++
//头文件cstdlib，c++11中头文件random有更强的实现
rand() //伪随机数
srand() //可以传入种子
```



#### climits

文件是由编译器厂商提供的，所以不同系统的编译器会有不同的文件。



#### initializer_list

可以作为常规函数的参数