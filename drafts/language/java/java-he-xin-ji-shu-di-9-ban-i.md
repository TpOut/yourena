# java核心技术第9版-I

感觉《java核心技术》有很多总结性的话，《java程序设计》更适合新学。 **1、数字的进制表示法** 16进制：0x 8进制：0 2进制：0b **2、数字的正无穷大** 整数被0除会出现异常 计算浮点数被0除或者负数的平方根结果为NaN 但是举个例子：

```text
//never true
x == Double.NaN
//下面这样才对
Double.isNaN(x)
```

**3、金融数值** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-190b4f68730d9707.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) **4、不常用的基本类型** float和char ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-8d290a7fdee7c2b7.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-385e8d35f3832c8e.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) **5、变量命名规则** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-dbcc0b31066a52a3.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) **6、Strictfp的说明** 3.5章节有说明，浮点数的截断实现。 **7、位移操作** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-473cf1a5e7993b03.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) **8、非常用类** StrictMath、 **9、数值转换** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-51b4305fcc43e9d6.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) **10、代码点和代码单元**

```text
//代码单元
str.length()
//代码点
str.codePointCount(0,str.length())
```

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-f067be055769ab87.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 参考[https://www.cnblogs.com/vinozly/p/5155304.html](https://www.cnblogs.com/vinozly/p/5155304.html)

**11、格式化** 章节3.7.2内容比较多。 **12、方法中嵌套块**

```text
public void example(){
    int i = 0;
    {
         int i = 0;  // its error
    }
}
```

Q1：很好奇这里的双层嵌套会有影响吗？或者说这么写有清晰代码的意义吗？ **13、循环**

普通的循环配合：break,continue还有标签

case条件： 有兴趣可以再去看下为什么后面可以增加String ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-7364df9e8de2f5c4.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) **14、数组** 如果返回的数组数量为0，可以这么写 new elementType\[0\]，而不用Null来代替 **15、类之间的关系** uses-a,has-a,is-a。。。感觉纯属强行总结。。。这几个关系

**16、访问器的设计** 如果一个类有一个引用对象变量，如createDate，在设计getCreateDate\(\)的时候，不要直接返回该变量，会可能造成误操作修改。可以返回一个clone

**17、自动垃圾回收** finalizer、System.runFinalizers\(true\)、Runtime.addShutdownHook\(\)

**18、JAR包** 使用ZIP格式 章4.8描述了shell命令将代码生成JAR的方式，同时简要描述了内部查询步骤。

**19、文档注释** @author, @version,@since,@deprecated,@see, @param,@return,@throws等 ,...,...等，不用...,... 如果引用了一个本地图片，可以放到doc-files目录下

@see用法示例：

```text
@see classPathName.className#methodName()
@see <a href="www.sample.com">this is a label</a>
//显示在see also
@see "Core java 2 volumn 2"
```

多个@see需要放在一起 如果要单独放置可以使用@link，如：

```text
{@link package.class#featrue label}
```

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-53b27b41ed675e20.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

4.9.7章描述了生成注释的步骤

**20、方法重写** 因为返回类型不是方法的签名，所以覆盖方法的时候，需要注意返回类型 但是某些情况下，子类返回的类型可以是父类返回类型的子类。

**21、动态绑定** 如果方法是private,final,static，那么就不需要动态绑定了 而动态绑定看机制就知道比较耗时，所以会为每个类创建对应的动态方法表。

**22、Object** Object.equals\(Object a, Object b\); 在重写equals方法的时候，可以先调用super.equals进行比较，再对剩余继承的变量进行比较即可。 章5.2.2说明了equals的设计理念。

Object.hashCode\(Object obj\);Object.hash\(Object... objs\)

**23、常用注解** @SuppressWarnings\("unchecked"\)

**24、自动装箱** 了解下原理，比如普通定义的时候怎么装、方法调用的时候怎么装 自动装箱的对象有可能指向同一个地址:

Integer a, b = 1000; if\(a == b\){ //maybe is true }

![201848-151119.jpg](https://upload-images.jianshu.io/upload_images/1936727-619334540d0bcbe1.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 自动装箱时编译器认可的，不是虚拟机处理的。

包装对象都是不可变的，如果希望修改，可以使用org.omg.CORBA.IntHolder

**25、枚举** 尝试去理解为什么注解更适合android。

**26、反射** 5.7章，Class类，顺便需要了解下类加载机制。 Class对象实际上是一个类型，这个类型不一定是一个类。如int.class Field, Method, Constructor类 Arrays.copyOf\(\)的实现，Array.newInstance

**27、类** 思考一下为什么Arrays.copy方法，没有直接接受comparable\[\]的方法 对于任意的x,y，sgn\(x.compareTo\(y\)\) = -sgn\(y.compareTo\(x\)\)，如果是异常也应当时一样的，继承的时候尤其注意

Q1：对于上述的x,y compareTo的设计标准，应该找一下原文

**28、clone** 深浅拷贝，数组类型的clone

**29、内部类** 访问该类定义所在的作用域中的数据，包括私有 可以对同一个包中的其他类隐藏 匿名内部类。

感觉可以理解成（内部机制另行研究）， 外部类如果构造在一个地址块，外部类的变量和内部类会被构造在该地址块内部，这样，内部类访问外部类的变量就好理解了。

内部类创建的时候，会由编译器合成一个带有外部类引用的构造器，可以分析对应的class文件（反射） 6.4.3，需要思考 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-caf7d4e331865c78.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```java
public class TalkingClock{
    private boolean beep;
    class TimePrinter{
        public void doSomeThing(){
            if(beep){...}
        }
    }
}
```

![&#x5185;&#x90E8;&#x7C7B;&#x7684;&#x4F2A;&#x5B9E;&#x73B0;.PNG](https://upload-images.jianshu.io/upload_images/1936727-0bdece40a0a8611f.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) access$0对应上述伪实现的accessBeep ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-f0b954259d692ffb.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以在方法快中定义类，这样基本做到完全隐藏，只对方法可见。（匿名内部类的真正语法）

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-21762462c5393631.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

\[图片上传失败...\(image-f9147a-1524051357145\)\]

**Tag** private static final String TAG = new Object\(\) {}.getClass\(\).getEnclosingClass\(\).getSimpleName\(\);

**30、代理** 就讲了一些思想和简单的使用， Proxy, InvocationHandler。 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-30470e914f330b16.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

对于我平时基本没用过而言还是不是很了解什么时候适合。 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-53b0caa0782894f5.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-c839075f86bde139.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Q1：里氏替换原则？

**双括号初始化** ![image.png](https://upload-images.jianshu.io/upload_images/1936727-e9cf1e1a918b48de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**31、设计模式** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-4f42867686286885.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-8174b52004d11690.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 包括组合、继承“模式”，装饰者、策略模式，MVC

**32、观感** 虽然是java的体系，但是感觉应该有办法“同化”在安卓上 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-ceda29040c622628.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**33、文本概念** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-84f334b866e717a5.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**34、名称** combo box--spinner; slider--progressbar

**快捷键** 语音？

**35、jar** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-0f7d7f9698d59c21.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-b614d43d80de739e.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**36、java客户端** 目前先略过，以后看 java web start,applet

**37、异常** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-324e21e91ee2df6a.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) error类和runtimeException类的子类属于未检查异常，其他异常属于已检查异常 RuntimeException和运行时没必要联系，反倒和c++中的logic\_error类似。非runtimeException类似c++中的runtime

对于那些可能被他人使用的java方法，应该根据异常规范在方法的首部声明可能的异常 覆盖方法：子类方法的异常需要更精确，父类方法无异常，则子类方法也不能有。

栈跟踪的原理。

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-8b6cc5ecf5083432.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-8972af8bc8080bef.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-b736719e80623fa8.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```text
    //覆盖返回值，返回值是2
    public static int getValue(){
        try{
            return 1;
        }finally {
            return 2;
        }
    }
```

对finally内的close执行操作进行双重catch：

```text
InputStream in = ...;
Exception ex = null;
try{
  try{
    ...
  }catch(Exception e){
    ex = e;
    throw e;
  }
}finally{
  try{
    in.close(); 
  }catch(... e){
    if(ex == null){throw e}
  }
}
```

//AutoCloseable接口资源文件，简单的双重catch方式（尽量别补上catch和finally块了）： ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-059b2c9986b5493d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

addSuppressed 方法增加到原来的异常

**堆栈跟踪** throwable.getStackTrace\(\); Thread.getAllStackTraces\(\); Thread.dumpStack\(\);

反射库的异常做法就不正确，调用者经常需要处理早已知道不可能发生的异常。 将异常转换成自己的异常时不要犹豫。

**38、代码中开启断言** assert 条件 或者 assert 条件 : msg

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

**39、Logger** 和Android的日志体系差不多，目前的水准认为没啥好看的。 11.6 调试技巧。很多说明。

**40、重定向**

```text
//System.out:
 java MyProgram > errors.txt
//System.err:
java MyProgram 2> erros.txt
//both
java MyProgram &> erros.txt
```

**41、泛型** E,K,V,T-U-S,

```text
//多接口限定，如果有类，那么只能是一个且需要在第一位
T extends Comparable & Serializable
```

![2018420-154411.jpg](https://upload-images.jianshu.io/upload_images/1936727-f72347d8a3ef5517.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**桥方法** 首先确认类型擦除的概念（处理成限定类型再强转，好奇三四个限定怎么个强转法？新建类？）

```text
class Pair<T>{
    private T first;
    private T second;
    ... constructor ...
    ... getter ... setter
}

class DateInterval extends Pair<Date>{
    public void setSecond(Date second){
        if(second.compareTo(getFirst() >= 0)){
            super.setSecond(second);
        }
    }
}

//擦除类型
class DateInterval extends Pair{
    public void setSecond(Date second){ ... }
    //擦除类型的pair中
    public void setSecond(Object second){ ... }
}

这里子类继承之后的方法不会进行擦除处理，而父类的被擦成了Object
但是他们应当是一个方法？
桥方法应运而生，

class DateInterval...{
    //编译器生成
    public void setSecond(Object second){
        setSecond((Date) second)
    }
}

class DateInterval...{
    //更奇怪的是get方法
    Date getSecond()
    Object getSecond()
}
这里在编写java的时候是不允许的，但是虚拟机里是可以共存的

//继承方法也是类似的：
//父类
public Parent getValue(){...}
//子类
public Son getValue(){...}
实际上两个方法是共存的，只是子类的getValue调用桥方法进行转换。
```

@SuppressWarnings\("unchecked"\)

instance的原理

由于数组会有自查限制，如 ArrayList\[\] p ,需要记忆p能够存储的类型，而泛型运行后只能识别ArrayList，有可能造成赋值错误。所以规定不能初始化泛型数组，但是作为变参（T ...）时，变相实现了初始化数组（会有警告，可以@SafeVarargs或者@SuppressWarnings\("unchecked"\)）。

```text
//初始化泛型实例
public Pair<T>{

    T first;

    public Pair(){
        first = new T();//error
        first = T.class.newInstance();//error
    }

    ...
    //如此看来，T这个符号其实更多的编译器实现泛型需求的标记符号
    public static <T> Pair<T> makePair(Class<T> clz){
        try{return new Pair<>(cl.newInstance)}catch{...}
    }
}
```

```text
//强转泛型数组类型
    public static <T extends Comparable> T[] minmax(T... a) {
        T[] mm = (T[]) Array.newInstance(a.getClass().getComponentType(), 2);
        for (int i = 0; i < mm.length; i++) {
            mm[i] = a[i];
            System.out.print(mm[i]);
        }
        return (T[]) mm;
    }
```

object\[\] arrayList.toArray\(\)

 T\[\] arrayList.toArray\(T\[\] var1\)

Q1：这里桥方法，既然把每个方法生成一个桥方法，为什么不干脆把方法给改成一个？

Q2：![2018423-110407.jpg](https://upload-images.jianshu.io/upload_images/1936727-5c16657f5f65a5fd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)，试了下声明也是不行的？

Q3：class 是泛型的设计理念

Q4：泛型异常--章12.6.7。不能设计泛型类继承异常， 也不能在catch\(T e\)中将T作为方法参数，但是可以在方法体中重新抛出{t.initiCause\(realCause\), throw t}。感觉前面不行是因为动态，但是为什么后面可以?

Q5：章12.6.7，

```text
//去除必检异常的检测
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void throwAs(Throwable e) throws T {
        throw (T) e;
    }
//这个方法的意义完全没看懂
```

**42、集合** 哈哈，很多修改就是没有理由的，太长 ![2018424-154443.jpg](https://upload-images.jianshu.io/upload_images/1936727-6c2dd7104ce3969a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-cc89b676b9343295.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Q1：无序的add就不能叫做add吗？![2018424-160744.jpg](https://upload-images.jianshu.io/upload_images/1936727-0cdf6af5d2c51381.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

EnumSet,IdentityHashMap; System.IdentityHashCode\(Object obj\)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-e6e99da503394309.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

RandomAccess接口用来标记是否支持高效的随机访问

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-3d9512520af4b485.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 老版集合，vector,stack,hashTable,properties

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-e78ddfebadd33ca3.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Collecitons.checkedList\(\)  
keySet，视图

```text
String[] values = (String[]) s.toArray();//error
String[] values = s.toArray(new String[size]);
```

**遗留**  
hashtable,properties,vector,stack,bitset  
enumeration

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-77bb54afb1d34195.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**43、多线程**

```text
public void run(){
    try{
        while(!Thread.currentThread().isInterrupted()){
            //interrupte只是一个boolean标志位，语言方面不要求中断线程，即可能一直执行。
            //如果在interrupt之后调用sleep,将抛出异常
            Thread.sleep();
        }
    }
}
```

InterruptedException最好别忽略，要么再抛出，要么在catch中调用Thread.currentThread\(\).interrupt\(\);

线程状态，new,runnable\(not run\),blocked,waiting,timed waiting,terminated 对应操作，new,start,试图获取内部对象锁而非concurrent中的锁且获取不到时，等待另一个线程通知调度器一个条件时，Thread.sleep--Object.wait--Thread.join--Lock.tryLock--Condition.await有超时参数时，run结束或者异常

抢占式调度，协调式调度，时间片机制

线程调度器在有机会重新选择线程时，会优先选择优先级高的线程。但是线程优先级是高度依赖系统的，优先级可能与代码中设置的不同。比如win7是7个优先级，导致java的10个优先级，会有部分在win7上是相同的优先级；而sun为linux提供的java虚拟机，线程的优先级被忽略，即所有线程有相同的优先级。

守护线程。一般作为计时使用，会在任何时候中断。

UncaughtExceptionHandler,ThreadGroup\(线程集合， 默认所有线程为同一个组，现在不建议用了\)

深入拓展：Brian Goetz\(Addison-Wesley Professional, 2006\)--《Java Concurrency in Practice》

lock  
ReentrantLock, 锁是可重入的，比如第一个加锁方法在unlock之前调用了另一个加锁的方法，会在后者lock时计数为2 虽然进行了加锁行为，还是得小心异常带来的数据损坏。

历史原因，条件对象被称为conditional variable  
condition, await, signalAll,singnal\(可能造成死锁\)

synchronized简化使用了对象内部锁。wait\(\)/notifyAll\(\)/notify\(\);如果是static，对应class对象。

锁的缺点：1、无法中断试图获取锁的线程；2、试图获取锁的时候不能设置超时；3、条件单一

建议优先使用concurrent包中的机制，然后是synchronized，然后是lock/condition。

client-side-locking,比较脆弱，不推荐使用 ,但是也有需要。

monitor,不安全的，章14.5.7

java内存模型和技术规范 jsr 133, [http://www.jcp.org/en/jsr/detail?id=133](http://www.jcp.org/en/jsr/detail?id=133)  
[http://www-106.ibm.com/developerworks/java/library/j-jtp02244.html](http://www-106.ibm.com/developerworks/java/library/j-jtp02244.html)

volatile,免锁读写，个人理解成及时刷新  
但是不保证原子性，如volatile boolean done; flipDone\(\){ done = !done };//not atomic

concurrent.atomic, 如AtomicInteger,建议开发并发工具的系统程序员使用。

当程序挂起时，键入ctrl+\,将得到所有线程的列表

```text
public static final SimpleDateFormat  dateFormat = new ...;
//如果在并发的情况下执行，可能造成结果混乱
String dateStamp  = dateFormat.format(new Date());
//如果每次都同步，感觉开销又太大

//可以使用以下方法为每个线程构建实例
public static final ThreadLocal<SimpleDateFormat> dateFormat = 
    new ThreadLocal<...>(){
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat(...);
        }
    }
```

ThreadLocalRandom.current\(\)

lock.tryLock\(\);允许打破死锁，线程中断抛出异常。lockInterruptibly\(无限超时的tryLock\)  
awaitUninterruptibly

concurrent.locks,ReentrantReadWriteLock

BlockingQueue.LinkedBlockingQueue,ArrayBlockingQueue,PriorityBlockingQueue,DelayQueue,TransferQueue,LinkedTransferQueue

concurrentHashMap,ConcurrentSkipListMap,CopyOnWriteArrayList,  
最好使用concurrent中的集合，而不是使用同步包装器Collections.synchronized...;例外：如果数组列表经常被修改，同步的ArrayList比CopyOnWriteArrayList更好

Callable,Future,FutureTask

ExecutorService,线程池。  
invokeAny,异步处理，取最早的结果。ExecutorCompletionService

fork-join,RecursiveTask--RecursiveAction,work stealing.ForkJoinPool

章节14.10有简单说明 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-f191432071a5ec2a.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

信号量，permits, synchronization primitive

Q1： final Map m = new Map\(\);//如果不是final,多线程读取可能会null ；感觉奇怪，为什么final有这种效果

**问题** Q1：可以思考Date和Calendar的设计思路 Q2：理解make工具 Q3: System.setOut\(\) change a final field by invoking native method Q4: try to find the mechanism of main method ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-11400fb5e790c1ef.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**总结** 静态引用如： import static java.lang.System.\*; 可以忽略类名直接书写对应的静态方法或域

