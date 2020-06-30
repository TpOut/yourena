主要来源：\[JavaCore卷Ⅱ中文第9版--章9\] 

深入拓展：[《Inside Java 2 Platform Security : Architecture, API Design, and Implementation》](http://www.oracle.com/technetwork/java/javaee/gong-135902.html)

### Java技术自身安全机制

1、语言特性 -- 数组边界检查、强类型、无指针算法等 2、访问控制 -- 文件访问、网络访问等 3、代码签名

### 类加载器

保证了类被加载到虚拟机时的完整性。 无论是默认类加载器还是自定义的，都需要与负责控制代码运行的安全管理类协同工作。

#### 介绍

Java编译器会把代码源文件（.java）转换成存储虚拟机指令的类文件（.class），好让虚拟机执行。 而虚拟机执行的时候，需要进一步转换成目标机器的机器语言。

虚拟机解读的步骤如下： 1.虚拟机通过加载类文件的机制 --磁盘读取文件或者请求web文件-- 加载一个MyProgram.class 2.然后解析类，如果拥有另一个类的域，或者具有超类，会逐个加载 3.然后执行MyProgram的main方法（静态方法，无需创建实例） 4.然后加载执行方法过程中所需的类

每个Java程序至少拥有三个类加载器：

* 引导类加载器
* 扩展类加载器
* 系统类加载器（或者叫 应用类加载器）

引导类加载器负责加载系统类（通常从rt.jar文件中加载）。它是虚拟机不可分割的一部分，而且通常是用C语言来实现的，且没有对应的ClassLoader对象，例如String.class.getClassLoader\(\)返回null 扩展类加载器负责加载“标准的扩展”（从jre/lib/ext目录中加载）。可以把需要的JAR文件放入该目录，这样扩展类加载器可以不通过类路径而直接找到类。 系统类加载器用于加载应用类。在由CLASSPATH环境变量，或者-classpath命令行选项设置的类路径中的目录或者是JAR/ZIP文件里查找类。 在Oracle的java实现中，后两者都是URLClassLoader的实例。

