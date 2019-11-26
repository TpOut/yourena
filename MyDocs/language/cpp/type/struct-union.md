#### struct

c++ 使用结构定义变量的时候，允许省略 struct 关键字

也可以有成员函数，只是一般都直接用类了。



位字段，一般在低级编程中使用，大部分情况可以用位运算替代：

```c++
    struct temp {
        unsigned int num:4;
        unsigned int : 4;
        bool goodIn : 1;
        bool goodOut : 1;
    };

    temp t = {
      15, 
      true, //这是goodIn
      false
    };
```



#### union

覆盖存储

语法基本和struct 类似，但是有一种匿名写法：

```c++
struct wiht{
  int type;
  union {
    int number;
    char[] number;
  }
}

wiht w;
...
if(w.type == 1){
  w.number //直接忽略union 名称
}
```

c++ 允许union有构造函数和析构函数，不能有虚函数。