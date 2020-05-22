#### JNI 概览

在编写java 程序的时候，总会遇到单单使用java 无法满足你需求的时候，此时就是JNI 用武之地  

具体有：

- 标准java 类库不支持某些 平台依赖功能
- 已经有了其他语言的实现库
- 注重时间效率

JNI 的功能包括：

- 创建、检查（inspect）、更新java 对象（包括arrays 和strings）
- 调用java 方法
- 捕获和抛出异常
- 加载class 和获取（obtain）class 信息
- 运行时类型检查

同时可以使用JNI 和`Invocation` 接口把任意native 应用嵌入到jvm 中，这比链接到vm 的源码中更方便



#### 历史背景

在JNI 统一之前，不同的vm 实现了不同的native 方法接口。这导致开发者不得不根据平台去生产、管理、分发native 方法库的多个版本

这里列出几个：

- JDK 1.0 native method interface
    有两个主要的原因导致它不能被其他jvm 接受

    - native 代码访问java 对象的field 是以c 的结构体形式访问的。但是因为java 语言规范没有规定对象在内存中的lay out 方式，而c 的结构体是有顺序的，所以对不同的jvm 可能需要进行不同的编译

    - 依赖于保守垃圾收集器（conservative garbage collector）。The unrestricted use of the `unhand` macro, for example, made it necessary to conservatively scan the native stack

        <font color=red>这里没有看明白：初以为，保守垃圾收集器在遇到经常使用`unhand` 宏的时候，需要不断扫描native 栈，比较影响性能？</font>

        >https://www.gnu.org/software/guile/manual/html_node/Conservative-GC.html

- NetScape's Java Runtime Inteface

    这是一个通用的jvm 服务接口，设计时考虑了移植性 -- 只依赖于少数的jvm 实现细节。但是有大范围的问题：native 方法，调试，反射，嵌套（调用）

- Microsoft's Raw Native Interface and Java/COM interface

    微软实现了两套接口。低层面的是RNI，在源码级别提供了较高程度的向后兼容性（兼容 native method interface，只是有一个比较大的差异），在垃圾收集器交互上，也通过RNI 的方法进行了抽象。

    高层面的，Java/COM 接口为jvm 提供了 不依赖语言的标准二进制接口，即可以java 对象的方式使用COM 对象，一个java class 也可以被看作一个 COM class



#### 目标性

统一的、深思熟虑的标准接口更有益于所有人：

- vm 供应者可以支持更大量的native 代码
- 工具构造者不需要去处理兼容性问题
- 应用开发者能只需要写一套代码

而最好的实现标准native 方法接口的方式，就是召集所有对Java VM 感兴趣的团队。一系列讨论后，标准要求：

- 二进制兼容性
- 高效性：和二进制兼容性是有冲突的，只能做一个折中
- 功能性：足够的JVM 内部



#### Java Native Interface 达成（Approach）

最初也是希望从已有的历史方案中选择，但是没有一个能符合

RNI 最接近，且实现了native 代码和非保守垃圾收集器共同工作，但是也不够：

- 还是有vm 依赖性，

- RNI native 方法访问java 对象，还是转化为c 结构体，一个是上文说到的lay out 问题，RNI 是暴露了这部分信息来处理；另一个是这种结构，无法让“white barriers” 有效合并，这对高级垃圾收集算法很重要。

COM 能够支持二进制兼容性，且只有较少负载增加，但是也不够：

- 缺少期望的功能，比如访问私有fields，抛出异常
- COM 自动提供的IDispatch 是大小写不敏感的，且相关的java 方法需要被封装一层来支持动态类型检查
- COM 是设计用来处理软件组件（software components）,而java 和native 方法并不一定适合被处理成组件
- linux 平台不支持

JNI 和RNI ，二进制不兼容；JNI 和COM，二进制兼容

JNI 使用jump table 结构



**等java 生态更加成熟，native 方法将失去意义**

