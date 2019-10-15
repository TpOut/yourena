# java语言程序设计

### java语言程序设计

#### 章2

命名可以使用：字母、数字、下划线\(\_\)和美元符号\($\)  
但是不能以数字开头，$一般用在机器生成代码上，区分大小写

数据类型范围（平时真的一般用不着，提醒下自己） -7%3=-1； 20%-13=7（分子负数则余负） 浮点数近似表示（IEEE754）

编码，java的通用性，其实是把各种编码转化成jvm内部的unicode，再转化回对应编码的过程。

2 + 'a' = 97; "chapter " + '2' = "chapter 2"

逻辑运算符： ^（异或）

格式化：%b,%C,%d,%f,%e,%s（可以在百分号和字母中间加宽度和精度，加"-"表示左对齐，转义%%）

#### 章4

浮点数加法时，先添加小数，后添加大数，其结果精确度更高。

蒙特卡罗模拟

#### 章6

System.arraycopy（反驼峰啊）

查找、排序（已经可以给我原理，我写出来；但是原理和名字记不完全。。。）

Arrays

#### 章9

== 比较基本类型的值，比较引用类型是否指向一个对象 string1.compareTo\(string2\) Character String lineSeparator = System.getProperty\("line.separator"\);//行分隔符

#### 章10

has\_a,组合、聚合

#### 章11

如果一个类被设计成可拓展的，不要忘了处理无参构造函数（小细节吧，用的时候也会看的啊） super.super.method\(\);\(这个错误，嘿嘿\)

#### 章13

异常类型 即使到达finally块之前有return语句，依旧会执行finally块

#### 章14

Calendar Comparable Cloneable

#### 章16

OuterClass.InnerClass innerObj = outerObj.new InnerClass\(\) OuterClass.InnerClass innerObj = new OuterObj.InnerClass\(\)

#### 补充材料

VII A 九宫格实现

#### 章21

通配泛型： ?、? extends T、? super T E\[\] elements = \(E\[\]\)new Object\[capatity\];

