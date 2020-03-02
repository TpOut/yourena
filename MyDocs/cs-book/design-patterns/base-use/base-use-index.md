为了更好的理解，我们先编写一个迷宫游戏。

基本需求为：

- 迷宫的组成为房间、墙、门
- 房间的四周是墙或者门，起码有一扇门。门的两边可能是房间或者出口。出口只有一个。
- 初始化时，玩家随机在某个房间中，通过上下左右在迷宫中移动
- 地图简单模式（全图可见）
- 操作简单模式（随意移动）

可能需求：

- 修改迷宫界面
- 操作困难模式（门密码，房间炸弹）
- 地图中等模式（路径可见），地图困难模式（当前可见）



按基本需求先写，为了简化，迷宫大小为4，代码为版本1。



[抽象工厂(Abstract Factory)](./abstract-factory.md) 

[构建者(Builder)](./builder.md) 

[工厂方法(Factory Method)](./factory-method.md)

[原型(Prototype)](./prototype.md)

[单例(Singleton)](./singleton.md)



[适配器(Adapter)](./adapter.md) 



桥接(Bridge): 将抽象部分和实现部分分离，使他们都可以独立的变化



[责任链(Chain of Responsibility)](./chain-of_responsibility.md)

命令(Command): 将一个操作封装成一个对象，将其参数化。

复合(Composite): 单个对象和复合对象的使用具有一致性

装饰者(Decorator): 动态的给一个对象添加额外的修饰

外观(Facade): 为子系统中的一组接口提供一个一致的界面



享元(Flyweight): 用共享技术有效地支持大量细粒度的对象

解释器(Interpreter): 定义一种文法和解释器

迭代器(Iterator): 提供统一的方法顺序访问一个聚合对象中的各个元素。

[中介(Mediator)](./mediator.md)

备忘录(Memento): 不破坏封装性的前提下，在对象外部保存对象内部状态。

观察者(Observer): 保存对象的1对多关系，在对象改变时通知观察者

代理(Proxy): 代理对象的控制

[状态(State)](./state.md)

策略(Strategt): 封装算法，使可替换

模板方法(Template Method): 定义一个操作中的算法的骨架，而将一些步骤延迟到子类中

访问者(Visitor): 表示一个作用于某对象结构中的各元素的操作



[设计模式分类](./types-index.md)

Abstract Factory 与Builder 相似，因为它也可以创建复杂对象。主要的区别是:

- Builder 模式着重于一步步构造一个复杂对象。Abstract Factory 着重于多个系列的产品对象（简单的或是复杂的） 
- Builder 在最后的一步返回产品，而对于 Abstract Factory来说，产品是立即返回的

ProtoType 和Abstract Factory 模式在某种方面是相互竞争的。但是它们也可以一起使用

最初的抽取，都会是Factory Method 方法，后来为了进一步抽取，才会是 Abstract Factory, Builder, ProtoType 。而后三者都可以通过单例实现



大量使用 C o m p o s i t e（ 4 . 3）和D e c o r a t o r（ 4 . 4）模式的设计通常也可从 P r o t o t y p e模式处获
益

一个对象将请求委托给一个描述当前状态的 S t a t e对象来处理

这两个模式的目的都是通过改变受托对象来改变委托对象的行为



当使用模式而感觉类型变化不够灵活的时候，可以使用注册列表来动态添加删除

当使用模式而感觉类型转化不够灵活的时候，可以使用泛型来避免强转。



Abstract Factory 经常用Factory Method 来实现
Factory Method 通常在Template Methods 中被调用