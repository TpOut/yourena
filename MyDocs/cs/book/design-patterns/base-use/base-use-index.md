为了更好的理解，以一个[迷宫游戏](../maze-game.md)为例。

而设计模式包括名称、问题（适用场景）、方案（设计抽象描述）、效果



[抽象工厂(Abstract Factory)](./abstract-factory.md) 

：约定格式，组合系列

[构建者(Builder)](./builder.md) 

：抽取构建过程

[工厂方法(Factory Method)](./factory-method.md)

：封装变化，延迟生产

[原型(Prototype)](./prototype.md)

：clone组合替代子类

[单例(Singleton)](./singleton.md)



[适配器(Adapter)](./adapter.md) 

：调解不同接口

[桥接(Bridge)](./bridge.md)

：抽象实现部分

[组合(Composite)](./composite.md)

：用户无感知，应当忽略不同以约定格式

[装饰者(Decorator)](./decorator.md)

：用户无感知，约定格式以封装装饰的逻辑

[外观(Facade)](./facade.md)

：对外简化接口

[享元(Flyweight)](./flyweight.md)

：剥离较少的差异，共享相同的部分，用时赋予差异

[代理(Proxy)](./proxy.md)

：操作预处理



[责任链(Chain of Responsibility)](./chain-of_responsibility.md)

: 约定格式，转发或者处理

[命令(Command)](./command.md)

：封装操作对象，抽象调用方

[解释器(Interpreter)](./interpreter.md)

：语法解释

[迭代器(Iterator)](./iterator.md)

：约定格式，封装操作

[中介(Mediator)](./mediator.md)：

：交互经由中介

[备忘录(Memento)](./memento.md)

：封装状态对象

[观察者(Observer)](./observer.md)

：把状态变化通过抽象接口反馈

[状态(State)](./state.md)

：约定格式，把状态封装

[策略(Strategy)](./strategy.md)

：将行为（多为算法）封装

[模板方法(Template Method)](./template-method.md)

：将行为延迟到子类

[访问者(Visitor)](./visitor.md) 

：按功能聚合操作。



[设计模式分类](./types-index.md)



当使用模式而感觉类型变化不够灵活的时候，可以使用注册列表来动态添加删除

当使用模式而感觉类型转化不够灵活的时候，可以使用泛型来避免强转。
