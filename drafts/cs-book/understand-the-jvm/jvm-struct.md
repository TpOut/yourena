> https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html

> 原文有一些细节，包括数值范围和具体实现规范，此篇只是为了记录



文档只是标定了抽象；而没有描述任何JVM 的实现

如果想实现JVM，只需要保证读取class 文件格式和操作正确。其他的则不会约束，如：运行时数据区域的内存布局、垃圾回收算法、jvm指令优化



**class文件格式**

编译后的jvm可执行代码 是硬件、操作系统无关的，一般存放在文件中（非必要），即class文件格式

它定义了类或接口的精确表示，包括被不同平台object文件格式所需求的字节码顺序（byte ordering）

具体的内容在第四章介绍



**data类型**

和java一样，jvm操作两种类型，基本类型和引用类型。

相应的，variables的存储、方法的参数和返回值、操作的对象都是这两种类型。

jvm期望把所有的类型检查都在运行时之前完成，一般是通过编译器，而不是他自己去检查。

基本类型可以不用tag或者在运行时审查类型，也不用和引用类型的值进行区分。

指令集根据类型区分，不同的类型分别有对应的指令表示相同操作

Jvm包含了对象的明确支持。一个对象是一个动态回收class实例，或者是一个数组。一个对象的引用属于引用类型，而引用类型的值可以理解成对象的指针。一个对象可以有多个引用。对象的操作、传递、测试都是使用引用类型值



**基本类型和值**

- 数值（numeric）
    - 整型（integral）
        - byte, short, int, long, char
    - 浮点
        - float, double
- 布尔：会被转化成int。没有直接操作boolean的指令，但是有布尔array 的指令。
- returnAddress：指向jvm 指令集的操作码的指针，运行时不可修改，和java语言的类型没有直接关联。



**引用类型**

引用类型可以是null，非运行时类型，但是可以转化为任何类型

- class，值指向动态创建的 class实例
- array，值指向动态创建的arrays。单维度统一元素类型（组成类型也可以是array）
- interface，值指向动态创建的接口实现的类实例或者arrays



**运行时数据区域**

有很多种类，有些数据区域和jvm 生命周期相同；其他的则和线程生命周期相同

jvm可以同时有很多线程同时运行，每条线程有自己的pc(program counter) 注册器，任何时间点，每条线程都在执行单个方法的代码，会对应有线程的方法。

如果方法不是native，pc注册器会包含当前执行指令的地址；如果线程是native，则不会定义pc注册器。



**JVM stack**

每个jvm 线程会有一个私有jvm stack, 一个jvm stack 存储frames。

jvm stack 和c的类似：保存local variables、部分结果，且在方法调用和返回时提供作用

Because the Java Virtual Machine stack is never manipulated directly except to push and pop frames, frames may be heap allocated.  物理内存不需要连续

jvm stack 可以是合适的大小（但不必再stack 创建的时候指定）、动态拓展缩略（执行计算时变化）

- 如果线程执行计算时，需求的stack 大小超出限制，会有 `StackOverflow`
- 动态拓展时/ 或者刚创建stack 时发现内存不足，抛出 `outOfMemory`

**heap**：

所有jvm 线程共享，运行时数据区域，class 实例和arrays 分配时从这里取数据

和jvm 生命周期相同，通过自动内存管理系统（即gc）进行内存分配。对象永不重新分配，jvm对gc 的类型一无所知。物理内存不需要连续

- 动态拓展时gc发现不足，outOfMemory

**方法区域：**

所有jvm 线程共享。类似常规语言，类似操作系统进程的文本片段("text" segment)

存储每个类结果，如运行时常量池，字段（filed）和方法数据，方法和构造函数的代码（包含class中的特殊方法，实例初始化，接口初始化）

jvm生命周期，逻辑上是heap 的一部分，但是实现上可能和heap不一致。

可以是合适的大小（但不必再stack 创建的时候指定）、动态拓展缩略（执行计算时变化），物理内存不需要连续

- 当有分配请求时，而方法区域内存不足，outOfMemory



**Frame**

存储数据和部分结果，执行动态链接，方法返回，分发异常

方法生命周期。不管是否异常都能销毁。从线程jvm stack中分配内存。

有自己的本地变量，操作栈，并持有当前方法所属类的运行时常量池的引用

