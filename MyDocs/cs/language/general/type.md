#### 基本类型

char,int,long,float,double,boolean/bool

每个类型都有占用空间。 有条件就有bool値 : 有些语言内置boolean基本类型，有些语言没有；部分语言支持空/0为false,真/1为true的基本判断

字面值、常量(literal)：const var , 65535（能容纳的最短类型），12L（long），3.1（double），3.14F(float)，07(8进制) ，0x7b(16进制)，

字符常量：'\n' (单引号包括)，L'X' (L特殊标示，宽字符常量，需要环境支持)

字符串常量化："literal"

动态语言，var 一般表示可变的类型，val 表示不可变（只读）



#### 枚举

enum

指値是符号常量，但不是字面值



#### 结构

struct、union



#### 类

自己定义结构的类型  

在大部分的语言中以class关键字标记  



类的结构包括：

- 构造器和初始化代码块
- 方法
- 属性

类嵌套

类型：

- 抽象类
- 接口

> 接口和抽象类的区别在于，接口不该初始化变量，最多做定义然后被重写
>
> 接口的方法默认无实现，即抽象方法，部分语言可以实现



类的使用包括显式调用、匿名类



**泛型**

https://en.wikipedia.org/wiki/Existentialism  



全局/本地的概念：

- 全局指类内，或者文件级别
- 本地指方法内



#### 数组

type[] 表示数组



#### collection  

- list
- set
- map

注意接口设计的时候，list / set 都是从collection 继承，而map 不是  

一般collection 继承自iterator .(即iterator 设计模式)。  

一般map 也支持iterator，只不过类似组合的方式实现（内部持有一个set  



ArrayList 理解成可变数组  



两个list 需要每个位置的值都顺序相等，才认为相等；  

set 则值相等即可，注意LinkedHashSet 也是如此  





