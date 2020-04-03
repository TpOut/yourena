> 主要来自《深入理解Java 虚拟机，2th》
>
> 勘误地址：http://icyfenix.iteye.com

[jvm-struct](./jvm-struct.md)

[javap](./javap.md)

[高效并发](./multi-thread/multi-thread-index.md)

[对象头](./object-header.md)

Java 多线程内存模型（主内存，工作内存），和Java内存区域模型（堆，栈，方法区）不是一个层次的划分方式，两者基本没有关系。硬要对应起来：

主内存 --- Java 堆中的对象实例数据部分

> Java堆还有其他信息，比如HotSpot 虚拟机，有Mark Word(存储对象哈希码，GC标志，GC年龄，同步锁等信息)，KlassPoint(指向存储类型元数据的指针)，及一些用于字节对齐补白的填充数据（如果实例数据刚好满足8字节对齐，则可以不存在空白）

工作内存 --- 栈中的部分区域



对象的构造方法，在finalize 方法之前



this引用逃逸：类A内部类runnable 实例化后持有a 的引用，而A 的构造函数启动线程使用了runnable。此时外部调用的a 实例还没有构造完成，而线程运行已经开始使用a 未完全构造的实例。所以不要在构造函数结束之前启动线程。



---

《The Java Virtual Machine Specification, Java SE 7 Edition》

《The Java Language Specification, Java SE 7 Edition》

《Oracle JRockit The Definitive Guide》

《Inside the Java 2 Virtual Machine, Second Edition》

《Java Performance》

虚拟机圈：http://hllvm.group.iteye.com

圈主：http://rednaxelafx.iteye.com



HotSpot Internals: https://wikis.oracle.com/display/HotSpotInternals/Home

The HotSpot Group: http://openjdk.java.net/groups/hotspot

