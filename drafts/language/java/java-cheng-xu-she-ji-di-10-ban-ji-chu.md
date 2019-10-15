# java程序设计第10版-基础

**1、输入输出** Java 使用 System.out 来表示标准输出设备， 而用 System.in 来表示标准输入设备 Java 并不直接支持控制台输人， 但是可以使用 Scanner 类创建它的对象， 以读取来自 System.in 的输入， 如下所示： Scanner input = new Scanner \(System.ln\); PrintWriter pw = new PrintWriter\(file\); **2、文件导入** import java.util. _声明明确导人和声明通配符导人在性能上是没有什么差别的 **3、标识符** 以英文、下划线、$开头、数字组成，但是数字不能作为开头 tip:不要用字符 $ 命名标识符。 习慣上， 字符 $ 只用在机器自动产生的源代码中。 \*4、字节数_

| 类型名 | 位数 | 字节数 | 近似范围 |
| :--- | :---: | :--- | :--- |
| byte | 8位带符号 | 1 | 正负128 |
| short | 16位带符号 | 2 | 正负3万 |
| int | 32位带符号 | 4 | 正负20亿 |
| long | 64位带符号 | 8 | 正负900亿亿 |
| float | 32位 | 4 | 正负1.4E-45到3.4E+38 |
| double | 64位 | 8 | 正负4.9E-324到1.79E+308 |
| char | 16位 | 2 |  |

**5、浮点数** 遵循[IEEE-754标准](https://people.eecs.berkeley.edu/~wkahan/ieee754status/IEEE754.PDF) 通常情况下， 应该使用 double 型， 因为它比 float 型更精确 一个 float 值有 7 到 8 位小数位， 一个 double 值有 15 到 17 位小数位

第1个bit表示符号sign,0表示正数,1表示负数; 第2-9个bit表示指数e，需要正负指数，所以偏移127 后面的bit表示1.f中的小数位f SEEE EEEE EFFF FFFF FFFF FFFF FFFF FFFF 即结果为：\(-1\)^\(sign\)  _1.f_  2^\(exponent\) 范围为：正负2^\(-127\) ---&gt; \(2-2^\(-23\)\) \* 2^128

这里2-2^\(-23\)的计算方法： 设a = 2^-1 + 2^-2 + 2^-3... + 2^-n 则2 \* a = 2^0 + 2^-1 + 2^-2 + ... + 2^-\(n-1\) 相减为：a = 1 - 2^-n 再加上前缀即可

其他特殊表示: 1.当指数部分全0而且小数部分不全0时表示的是非规格化的浮点数，此时后面的bit表示0.f中的小数位f 范围为：正负2^\(-149\) ---&gt; \(1-2^\(-23\)\) \* 2^\(-126\) 2.当指数部分和小数部分全为0时,表示0值,有+0和-0之分\(符号位决定\),0x00000000表示正0,0x80000000表示负0. 3.指数部分全1,小数部分全0时,表示无穷大,有正无穷和负无穷,0x7f800000表示正无穷,0xff800000表示负无穷. 4.指数部分全1,小数部分不全0时,表示NaN.

S1：由于平时习惯一直没有这个问题，但是还是写一下 不要用浮点数加减来进行判断，如float f = 0.1f, \(f - 0.01\)== 0.09是不一定成立的

strictfp POSITIVE\_INFINITY、 NEGATIVE-INFINITY 和 NaN

**6、数字的可读性** 为了提高可读性， Java 允许在數值直接量的两个数字间使用下划线。 例如， 下面的直接量是正确的：基本程序设计 43 long l = 232\_44\_19; long l = 224\_4545\_4519L;

**7、数值类型转换** int i = 2; bye b = i; \(错误，需要强转\) final int i = 2; byte b = i; \(正确，同属整型-可以直接截断-且范围不会有问题\) final int i = 128; byte b = i; \(错误，超出范围\)

Q1：final long l = 1; int i = l; 针对上述第二行，这里很奇怪，只有long不行，short,int,byte,char都是可以的&lt;/&gt; A1：暂时不知道，但是肯定和short,byte,char计算时自动扩大成int的机制有关系

Q2：final double d = 2.0; float f = d; （错误，float和double不是一个类型）

这里有个问题，既然有规则可寻，就不能进行转换吗？影响效率？&lt;/&gt; A2：比如float计算时会自动扩大成double，那么就应该可以反向缩小啊？？

关于A1的延伸查询： 大概是java最初设计的时候觉得32位已经很吊了，所以int是一个设计点  
比如switch最开始只支持整形，后来jdk1.6加了enum, 1.7加了String的支持，其内部实现其实是用hashCode对应来进行转换的？（听起来很有道理，想验证的话可以先编译成字节码在反编译） 现在好像正在努力改变这个设计点

**8、逻辑操作符** ！、&&、\|\|、^ p1 ^ p2 等同于 p1 !=p2

&&和\|\|会有短路效果，Java 也提供了无条件AND \(&\) 和 OR \( I \) 操作符

**9、字符编码** utf-8: [http://blog.csdn.net/hezh1994/article/details/78899683](http://blog.csdn.net/hezh1994/article/details/78899683)

所有数值操作符都可以用在 char 型操作数上。 如果另一个操作数是一个数字或字符， 那么 char 型操作数就会被自动转换成一个数字。 如果另一个操作数是一个字符串， 字符就 会与该字符串相连 int i = '2' + '3' System.out.println\("i is "+ i\); int j = 2 + 'a' System.out.println\("j is "+ j\); System.out.println\(j + " is the Unicode for character "+ \(char\)j\); System.out.println\("Chapter "+ '2'\); 显示结果 i is 101 j is 99 99 is the Unicode for character c Chapter 2

**10、转义字符** \b,\t,\n,\f,\r,\,\"

**11、常用类** Math类，Character类， Arrays类，Date类，Random类，Point2D类 Number类，Rational 类，Biglnteger 和 BigDecimal 类 Collections类，File类 URL类，Calendar类，GregorianCalendar类，Color类，Font类 RandomAccessFile类

**12、字符串格式化** %5c、%6b、%5d、%10.2f、%10.2e、%12s 默认前面补位空格，即右对齐；可以添加-号，如%-5c进行左对齐 f和e会进行进位处理，所以使用请看情况。。

注意， 可以使用 format 方法将一个十进制数转换为十六进制数， 例如， String.format\("\x", 26\) returns 1A;

**13、输入输出重定向** java SentinelValue &lt; input .txt  
java ClassName &gt; output.txt 这个是系统的能力，可以合并成一条语句

**14、for循环** for \(int i = 0, j = 0; i + j &lt; 10; System.out.print\(i + j\), j++\){ // Do something } 这样是正确的，但是可读性较差；甚至条件判断都可以是空的，默认为true

**15、重载** public double max\(int a, int b\){} public double max\(double a, double b\){} public double max\(float a, float b\){}

public double min\(int a, double b\){} public double min\(double a, int b\){}

max\(2, 4.5\)调用的是双double（会寻找最精确的方法） 如果实在无法分辨，会产生编译错误，如min\(1, 2\)

**16、数组** 当创建数组后， 它的元素被賦予默认值， 数值型基本数据类型的默认值为 0, char 型的 默认值为 '\u0000’， boolean 型的默认值为 false。

double\[\] myList; myList ={1.9, 2.9}; Q1：参考正常的写法，这样为什么是错误的？ A1（暂时猜测）：简单来看，正常的初始化包括，new double\[2\], new double\[2\]{1.9, 2.9}, {1.9, 2.9}。由于myList是一个引用，可以很好的理解，前面两种new的方式是新建了一个对象并赋值给myList，而要判断第三种方式，是不是新建一个对象，就比较麻烦？

复制数组：System.arraycopy\(\)、clone\(\)、逐个循环复制

二维数组（如果不知道具体每个子数组的长度，可以这么创建，第一个维度值是必须的（这里是5））： int\[\]\[\] triangleArray = new int\[5\]\[\]; triangleArray\[0\] = new int\[5\]; triangleArray\[1\] = new int\[4\]; triangleArray\[2\] = new int\[3\]; triangleArray\[3\] = new int\[2\]; triangleArray\[4\] = new int\[1\];

**18、排序算法**

| 算法名称 | 基本原理 | 时间复杂度 |
| :---: | :---: | :---: |
| 选择排序 | 区分出已处理部分和未处理部分，不断从未处理部分中选择最小的数，依次放入已处理部分 | O\(N\) |

Arrays.parallelSort\(\) JDK8新加的API Q1：在学习的过程中很容易出现这种，本身学习的时候没有的API，而学习到一半的时候，被新加入JDK中，应该怎么样更好的及时察觉？（目前都是重新全部翻看的时候才会注意到，这样的时间代价很大）

**17、查找算法**

| 算法名称 | 基本原理 | 时间复杂度 |
| :---: | :---: | :---: |
| 线性查找 | 逐个一步步的查找 | O\(N\) |
| 二分查找 | 首先需要排序，然后从中间开始比较，如果没有匹配，则从一侧的中间继续比较，直到最后 | O\(log2 N\) |

二分查找的实现方式，可以通过添加两个变量记录下标，来控制选取的列表内容。 Q1：如果 high 是一个非常大的整数， 比如最大的 int 值 2147483647,\(low + high\)/2 可能导致 溢出。 如何修改从而防止溢出？ A1：可以使用&gt;&gt;&gt;进位符号

**18、命令行运行** java TestMain argO argl arg2 当符号 \* 用于命令行时表示当前目录下的所有文件

**19、UML图** ![&#x4E3E;&#x4F8B;.PNG](https://upload-images.jianshu.io/upload_images/1936727-84b8935e73ad1ed3.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**20、静态方法**

```text
//没有错误
public class Test {
    private double t = 1;
    public static void main(String[] args) {
        new Test().t = 2;
    }
```

**21、this** 如果一个类有多个构造方法， 最好尽可能使用 this\( 参数列表 ） 实现它们。 通常， 无参數或参数少的构造方法可以用 this\( 参数列表 ） 调用参数多的构造方法。 这样做通常 可以简化代码， 使类易于阅读和维护。

但是android中很多系统组件构造时，某个少参数的构造方法会带有主题属性，而多参数的没有主题属性，在自定义继承的时候，需要调用super才更合适。

**22、类的关系**

* 关联：即两个类之间存在联系
* 聚合（聚集）：特殊的关联--从属关系，从对应多个属
* 组合：特殊的关联--从属关系，是一一对应的
* 继承：

  > 因为聚合和组合实际在代码上没有什么区别，即从类作为一个属类的变量，所以很多地方统称为组合

**23、字符串** String s1 = "Welcome to java"; String s2 = new String\("Welcome to java"\); String s3 = "Welcome to java";

s1 == s2 is false s1 == s3 is true

**24、多态。动态绑定** 多态：子类型可以作为形参，被当做父类型进行调用

动态绑定：可以用父类型作为声明类型，而子类型作为实际类型，调用声明类型的方法时，会根据实际类型的方法进行调用。

**25、可见性** ![&#x53EF;&#x89C1;&#x6027;.png](https://upload-images.jianshu.io/upload_images/1936727-710319b930ed2d2c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**26、异常分类** ![&#x5F02;&#x5E38;&#x5206;&#x7C7B;.PNG](https://upload-images.jianshu.io/upload_images/1936727-fdaba825485b4ac8.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x5F02;&#x5E38;&#x5BF9;&#x65B9;&#x6CD5;&#x5185;&#x4EE3;&#x7801;&#x6267;&#x884C;&#x7684;&#x5F71;&#x54CD;.PNG](https://upload-images.jianshu.io/upload_images/1936727-6676b723bb180672.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以在catch语句中重新抛出异常给调用者。

如果方法没有在父类中声明异常， 那么就不能在子类中对其进行继承来声明异常。 Q1：异常堆栈追踪的原理？

**27、try-with-resources** ![&#x7B80;&#x5316;&#x7F16;&#x5199;AutoCloaseable&#x7684;&#x7A0B;&#x5E8F;.PNG](https://upload-images.jianshu.io/upload_images/1936727-d553f48103ac0b9b.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**28、抽象类** 子类可以覆盖父类的方法，并且定义成abstract。

**29、接口** 由于接口中所有的數据城都是 public static final 而且所有的方法都是 public abstract, 所以 Java 允许忽略这些修饰符

Comparable接口， 强烈建议（尽管不要求） compareTo 应该与 equals 保持一致。 也就是说， 对于两个对象 ol 和 o2, 应该确保当且仅当 ol.equals\(o2\)为 true 时 ol.compareTo\(o2\) = 0 成立。

Cloneable接口

**30、lambda** \(type1 param1, type2 param2, … ） -&gt; expression 或者 \(type1 param1, type2 param2, … ） -&gt; { statements; }

可以看下这个链接：[https://www.zhihu.com/question/20125256](https://www.zhihu.com/question/20125256)

**31、内部类** 非静态内部类的创建方式： OutClass outObject = new OuterObject\(\); OuterClass.InnerClass innerObject = outerObject.new InnerClass\(\); 静态内部类的创建方式： OuterClass.InnerClass innerObject = new OuterClass.InnerClass\(\)；

Q1：— 个匿名内部类总是使用它父类的无参构造方法来创建一个实例。好像不太对，尝试创建一个有参数的构造函数也没有问题。

**32、二进制I/O类** ![&#x4E8C;&#x8FDB;&#x5236;I/O&#x7C7B;.PNG](https://upload-images.jianshu.io/upload_images/1936727-2bc434877a6fbeda.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里的分类就有些奇怪，照理filter只是对file的一个处理，object更是一个filter的子集

但是事实是，data是一个字符串和基本类型的过滤器，buffer是一个部分读取的过滤器，object反倒包含了data的所有功能。

**33、UTF-8编码** [https://www.zhihu.com/question/23374078](https://www.zhihu.com/question/23374078)

**34、Serializable**

```text
public class C implements java.1o.Serializable {
  private int vl;
  private static double v2;
  private transient A v3 = new A();
}
```

如果一个对象不止一次写入对象流， 会存储对象的多份副本吗？ 答案是不会。 第一次写入 一个对象时， 就会为它创建一个序列号。 Java 虚拟机将对象的所有内容和序列号一起写入 对象流。 以后每次存储时， 如果再写入相同的对象， 就只存储序列号。 读出这些对象时， 它们的引用相同， 因为在内存中实际上存储的只是一个对象。 很多时候这个序列号是自己写入的，不然对象被修改之后，可能就无法反序列化了

**35、位操作** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-0123ce5848dcc27d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**36、附录** 枚举，正则

**问题** Q1：突然没有找到Integer.parseInt\(String s ,int radix\)方法，只有Integer.parseInt\(String s\)。难道是因为用的android自带的jre的原因？ Q2：super.super.toString\(\)为何错误

**一句话总结** 自动装箱和拆箱，是编译器进行的 一般情况下， 最好能为每个类提供一个无参构造方法， 以便于对该类进行扩展， 同时避免错误  
静态方法也能被继承。 但是， 静态方法不能被覆盖（只是隐藏） 行分隔符获取：String lineSeparator = System.getProperty\("line.separator"\); 能够使用迭代较清晰解决的问题不要使用递归，如果使用了，尽量使用尾递归（即继续调用方法时，方法尾部没有后续代码）

