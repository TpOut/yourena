`import java.util.*` 声明明确导入和声明通配符导入在性能上是没有什么差别的 

 -7%3=-1； 20%-13=7（分子负数则余负）

2 + 'a' = 97; "chapter " + '2' = "chapter 2"

| 类型名 |    位数    | 字节数 | 近似范围                |
| :----- | :--------: | :----- | :---------------------- |
| byte   | 8位带符号  | 1      | 正负128                 |
| short  | 16位带符号 | 2      | 正负3万                 |
| int    | 32位带符号 | 4      | 正负20亿                |
| long   | 64位带符号 | 8      | 正负900亿亿             |
| float  |    32位    | 4      | 正负1.4E-45到3.4E+38    |
| double |    64位    | 8      | 正负4.9E-324到1.79E+308 |
| char   |    16位    | 2      |                         |

编码，java的通用性，其实是把各种编码转化成jvm内部的unicode，再转化回对应编码的过程。

System.arraycopy（反驼峰啊）

浮点数加法时，先添加小数，后添加大数，其结果精确度更高。

== 比较基本类型的值，比较引用类型是否指向一个对象 .

string1.compareTo\(string2\)  

`String lineSeparator = System.getProperty\("line.separator"\);//行分隔符`

如果一个类被设计成可拓展的，不要忘了处理无参构造函数（小细节吧，用的时候也会看的啊） 

如果返回的数组数量为0，可以这么写 new elementType\[0\]，而不用Null来代替

如果一个类有一个引用对象变量，如createDate，在设计getCreateDate\(\)的时候，不要直接返回该变量，会可能造成误操作修改。可以返回一个clone

 深浅拷贝，数组类型的clone

用default修饰接口中的方法时，可以让方法拥有具体实现代码

 LinkedList遍历操作不要使用循环get，应该使用iterator，foreach就是使用的iterator 

通常对于比较器来讲，实现serializable比较好。

 自动装箱和拆箱，是编译器进行的 

一般情况下， 最好能为每个类提供一个无参构造方法， 以便于对该类进行扩展， 同时避免错误  
静态方法也能被继承。 但是， 静态方法不能被覆盖（只是隐藏）

 行分隔符获取：String lineSeparator = System.getProperty\("line.separator"\);

 能够使用迭代较清晰解决的问题不要使用递归，如果使用了，尽量使用尾递归（即继续调用方法时，方法尾部没有后续代码）

**20、方法重写** 因为返回类型不是方法的签名，所以覆盖方法的时候，需要注意返回类型 但是某些情况下，子类返回的类型可以是父类返回类型的子类。

**21、动态绑定** 如果方法是private,final,static，那么就不需要动态绑定了 而动态绑定看机制就知道比较耗时，所以会为每个类创建对应的动态方法表。

**22、Object** Object.equals\(Object a, Object b\); 在重写equals方法的时候，可以先调用super.equals进行比较，再对剩余继承的变量进行比较即可。 章5.2.2说明了equals的设计理念。

Object.hashCode\(Object obj\);Object.hash\(Object... objs\)

**23、常用注解** @SuppressWarnings\("unchecked"\)

**30、lambda** \(type1 param1, type2 param2, … ） -&gt; expression 或者 \(type1 param1, type2 param2, … ） -&gt; { statements; }

可以看下这个链接：[https://www.zhihu.com/question/20125256](https://www.zhihu.com/question/20125256)

**抽象类** 子类可以覆盖父类的方法，并且定义成abstract。

**标签**

 label79:{ if\(someCondition\){ break label79; } }

**观感**

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-ceda29040c622628.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**异常** 

即使到达finally块之前有return语句，依旧会执行finally块

**通用tag**

private static final String TAG = new Object\(\) {}.getClass\(\).getEnclosingClass\(\).getSimpleName\(\);

**格式化**

章节3.7.2内容比较多

**循环**

普通的循环配合：break,continue还有标签

case条件： 有兴趣可以再去看下为什么后面可以增加String ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-7364df9e8de2f5c4.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 

**代理** 

Proxy, InvocationHandler。 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-30470e914f330b16.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**双括号初始化** ![image.png](https://upload-images.jianshu.io/upload_images/1936727-e9cf1e1a918b48de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**代码中开启断言**

 assert 条件 或者 assert 条件 : msg

启用断言：

```text
//可以缩写成-ea
java -enableassertions MyJava
//包或者类
java -ea:MyClass -ea:com.company... MyJava
//禁用, -da
-disableassetions 
//"系统类"不支持，用-esa
-enablesystemassertions
```

ClassLoader中有对应的开关API

**调试技巧**

> 核心技术章11.6，很多说明

**字符编码**

 utf-8: [http://blog.csdn.net/hezh1994/article/details/78899683](http://blog.csdn.net/hezh1994/article/details/78899683)

所有数值操作符都可以用在 char 型操作数上。 

如果另一个操作数是一个数字或字符， 那么 char 型操作数就会被自动转换成一个数字。 

如果另一个操作数是一个字符串， 字符就 会与该字符串相连 int i = '2' + '3'

```java
 System.out.println\("i is "+ i\); 

int j = 2 + 'a' System.out.println\("j is "+ j\); 

System.out.println\(j + " is the Unicode for character "+ \(char\)j\); 

System.out.println\("Chapter "+ '2'\); 

//显示结果

 i is 101

 j is 99 

99 is the Unicode for character c

 Chapter 2
```

**字符串格式化** 

%5c、%6b、%5d、%10.2f、%10.2e、%12s 默认前面补位空格，即右对齐；可以添加-号，如%-5c进行左对齐 f和e会进行进位处理，所以使用请看情况。。

注意， 可以使用 format 方法将一个十进制数转换为十六进制数， 例如， String.format\("\x", 26\) returns 1A;

**重载**

```java
public double max\(int a, int b\){} public double max\(double a, double b\){} public double max\(float a, float b\){}

public double min\(int a, double b\){} public double min\(double a, int b\){}

max\(2, 4.5\)调用的是双double（会寻找最精确的方法） 如果实在无法分辨，会产生编译错误，如min\(1, 2\)
```



**数组**

 当创建数组后， 它的元素被賦予默认值， 数值型基本数据类型的默认值为 0, char 型的 默认值为 '\u0000’， boolean 型的默认值为 false。

double\[\] myList; myList ={1.9, 2.9};

 Q1：参考正常的写法，这样为什么是错误的？ 

A1（暂时猜测）：简单来看，正常的初始化包括，new double\[2\], new double\[2\]{1.9, 2.9}, {1.9, 2.9}。由于myList是一个引用，可以很好的理解，前面两种new的方式是新建了一个对象并赋值给myList，而要判断第三种方式，是不是新建一个对象，就比较麻烦？

复制数组：System.arraycopy\(\)、clone\(\)、逐个循环复制

二维数组（如果不知道具体每个子数组的长度，可以这么创建，第一个维度值是必须的（这里是5））： int\[\]\[\] triangleArray = new int\[5\]\[\]; triangleArray\[0\] = new int\[5\]; triangleArray\[1\] = new int\[4\]; triangleArray\[2\] = new int\[3\]; triangleArray\[3\] = new int\[2\]; triangleArray\[4\] = new int\[1\];



![&#x53EF;&#x89C1;&#x6027;.png](https://upload-images.jianshu.io/upload_images/1936727-710319b930ed2d2c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

