> 主要来自《深入理解Java 虚拟机，3th》
>
> 勘误地址：http://icyfenix.iteye.com



[发展历程](./history.md)

[编译jdk](./compile-jdk.md)

[对象](./obj.md)

[对象死亡判断](./reference-null.md)

[垃圾回收算法](./garbage-collection-algrothim.md)

[内存区域和内存溢出异常](./memory-about.md)

[内存分配原则](./memory-allocate.md)

[经典垃圾收集器](./garbage-collections.md)

[调优工具](./tools.md)

[class文件](./class-file.md)

[jvm-struct](./jvm-struct.md)

[javap](./javap.md)

[高效并发](./multi-thread/multi-thread-index.md)

[对象头](./object-header.md)

寄存器架构和栈架构



调用点限定符?



一种名为“Java逆向移植”的工具（Java Backporting Tools）应运而生，Retrotranslator[插图]和Retrolambda是这类工具中的杰出代表



新生代、老年代、永久代、Eden空间、From Survivor空间、To Survivor空间

元空间



Jdk7 中把方法区 从永久代移出到 堆中, Jdk8之后，永久代被元空间替代 



Java 多线程内存模型（主内存，工作内存），和Java内存区域模型（堆，栈，方法区）不是一个层次的划分方式，两者基本没有关系。硬要对应起来：

主内存 --- Java 堆中的对象实例数据部分

> Java堆还有其他信息，比如HotSpot 虚拟机，有Mark Word(存储对象哈希码，GC标志，GC年龄，同步锁等信息)，KlassPoint(指向存储类型元数据的指针)，及一些用于字节对齐补白的填充数据（如果实例数据刚好满足8字节对齐，则可以不存在空白）

工作内存 --- 栈中的部分区域



对象的构造方法，在finalize 方法之前



大内存必须有64位Java虚拟机的支持，但由于压缩指针、处理器缓存行容量（Cache Line）等因素，64位虚拟机的性能测试结果普遍略低于相同版本的32位虚拟机。



this引用逃逸：类A内部类runnable 实例化后持有a 的引用，而A 的构造函数启动线程使用了runnable。此时外部调用的a 实例还没有构造完成，而线程运行已经开始使用a 未完全构造的实例。所以不要在构造函数结束之前启动线程。



ForkJoinPool，多队列，工作窃取



虚拟内存映射技术：

垃圾收集器日志的查看。





---

《The Java Virtual Machine Specification》

《The Java Language Specification》

《垃圾回收算法手册：自动内存管理的艺术》 -- 机械工业，2016.3

《Oracle JRockit The Definitive Guide》

《Inside the Java 2 Virtual Machine, Second Edition》

《Java Performance》

虚拟机圈：http://hllvm.group.iteye.com

圈主：http://rednaxelafx.iteye.com



HotSpot Internals: https://wikis.oracle.com/display/HotSpotInternals/Home

The HotSpot Group: http://openjdk.java.net/groups/hotspot

