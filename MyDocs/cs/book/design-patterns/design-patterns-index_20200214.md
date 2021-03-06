> 主要内容来自 设计模式-gof，也有一些网络边角信息。书的阐述非常详细，所以基本只记录要点
>
> 翻译词汇：http://www.kaiyuanba.cn/content/develop/design_mode_zh/07.pdf

关于设计模式，个人觉得都是基于[设计原则](./principle.md)，在复用的期望下衍生出来的程序结构，然后反作用于设计。

gof 设计原则：

- 对接口编程，而不是对实现编程

变量声明的时候不要指定具体的类，而是指定抽象的接口。当不得不使用具体的类的时候，使用创造型模式。

- 优先使用对象组合，而不是类继承

委托是对象组合的特例。另一种功能复用技术 (并非严格的面向对象技术 )是参数化类型(parameterized type)，也就是泛型( generic) 或模板(templates) 。



而对于程序的理解，参看[程序基本概念](./base-concept/base-concept-index.md)

比如MVC中，为了快速修改视图，独立分离了视图逻辑。而为了快速修改控制，独立分离了控制逻辑。对于同步数据逻辑，可以使用观察者模式实现。对于视图内部的同质性，可以用组合模式实现。对于控制器的配置，可以使用策略模式。等等等等



为了方便，使用[图例](./graph.md) 来简化一些描述



当然我们说设计，是在编写之前就要决定一个初始的模式。以满足目前的需求和一定的拓展性，也要方便替换成其他的模式。

前者需要我们了解[每个模式的要素](./base-use/base-use-index.md)，后者则需要了解[各个模式之间的关联](./relations.md)

然后总结一些[快速指导](./常见的设计场景指导.md)



UML类图工具：rational rose

---

《设计模式：可复用面向对象软件的基础》（GOF的ERICH Gamm,Richard Helm, Ralph Johnson, John Vlissides）

《设计模式解析》（Alan Shalloway, James R. Trott） 

《敏捷软件开发：原则、模式与实践》\(Robert C.Martin\) 

《重构--改善既有代码的设计》\(Martin Fowler\) 

《重构与模式》\(Joshua Kerievsky\) 

《Java与模式》\(阎宏\) 《Head first design patterns》\(eric freeman\)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-8174b52004d11690.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

两本弯路。。。

[《大话设计模式-程杰》](./da-hua-she-ji-mo-shi-cheng-jie.md)

> 语言是c#，前面感觉写的还好，后面的一些场景解释比较扯

[《大话设计模式-吴强-2010》](./da-hua-she-ji-mo-shi-wu-qiang-2010.md)

> 将模式分成了五类，接口型、责任型、构造型、操作型、拓展型，好像都不是一个层面的词？
>
> 前面还翻译成组合模式，后面就翻译成合成模式了。。  
> 而且还很多句子是中断的。。。 提到了接口和委托的区别，但是查了下，发现里面委托是c\#的概念？  
> 前面说用java语言，然后穿插了很多c/c++语言。。  
> 概念重复讲，又抽搐（不同的模式互相介绍。。）。。。

