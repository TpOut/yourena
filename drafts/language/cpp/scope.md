在函数内，调用  ::var 表示调用全局的变量var

方法里的变量也可以用extern



 方法也可以使用 extern 和 static



#### 名称空间

名称可以是变量，函数及其成员，结构，枚举，类以及其成员

之前的名称作用范围已经明了，但是还是不能满足需求

于是添加了一个namespace 的关键字

namespace 只能是全局的，但是可以嵌套。

如此概念下，原先的文件作用范围 就是一个全局名称空间

```c++
//域名解析
namespace Jack{
  int Son;
  namespace Jay{
    int Chou;
  }
  using namespace some;
  using std::cout;
}
Jack::Son = 1; //直接使用

using Jack::Chou 或 Jack::Jay::Chou //可以直接跳级索引，不需要一级一级
using Jack::Son; //使用声明, 添加Jack 到对应的范围中，后续直接Son
using namespace Jack; //编译指令，添加命名空间的所有声明到对应范围，但是如果有同范围内的同名变量，会覆盖命名空间的变量。

namespace JJC = Jack::Jay::Chou; //别名，便于简写
```



```c++
//无名名称空间
namespace{
  int ice；
}
//等同于声明之后马上使用using
在大部分作用范围没有啥用，但在全局使用时，
作用等同于：链接性为内部的静态变量
static int ice;
```



```c++
// namesp.h
namespace pers{...}
namespace debts{
  using namespace pers;
  ...
}
//namesp.cpp
namespace pers{...}
namespace debts{
  //无需再写using
  ...
}
```

如果有方法

```c++
using debts::itisFunc
//则会导入所有的重载方法
```

