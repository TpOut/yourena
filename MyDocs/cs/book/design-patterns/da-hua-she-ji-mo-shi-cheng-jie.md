### 简单工厂模式

就是使用判断语句 来根据输入信息，得到对应的输出信息。 然后封装到单一的方法内部，不需要让调用者知道具体细节。

这样调用者还是需要知道工厂方法和输出信息。如：

```text
int a = 1;
int b = 2;
String operate = "/";

OperatorFactory.getOperator(operate).process(a, b);
```

### 策略模式

```text
int a = 1;
int b = 2;
String operate = "/";
//不一定要工厂嘛
Strategy strategy = StrategyFactory.getStrategy(operate);

new OperatorStrategy().setStrategy(strategy).do(a, b);
//当然可以在构造中直接进行
new OperatorStrategy(strategy).do(a, b);
```

不过可以把工厂方法放在策略类内部，而不单独建立工厂类 同时输出信息就是策略类本身 这样调用方就只需要知道一个输出信息。

```text
new OperatorStrategy().setStrategyByOperate(operate).do(a, b);
```

### 装饰模式

```text
//被装饰者
ConcreteComponent cc = new ConcreteComponent();
//装饰1
DecoratorComponent.ComponentA ca = new DecoratorComponent.ComponentA();
//装饰2
DecoratorComponent.ComponentB cb = new DecoratorComponent.ComponentB();
//装饰1持有被装饰者，并和被装饰者的行为统一
ca.setComponent(cc);
//装饰者2持有被装饰者1，行为统一
cb.setComponent(ca);
//最后一个装饰者调用统一的行为
cb.decoratorOperate();
```

DecoratorComponent通过setComponent 持有被装饰者，然后可以进行修饰行为 当然在构造函数中直接设置可以让调用看起来更加爽快

```text
cb(ca(cc)).decoratorOperate();
```

### 代理模式

```text
//不管是什么形式，proxyPursuit.doPursuit() 都将pursuit和target关联起来
//事件流
Target target = new Target();
ProxyPursuit proxyPursuit = new ProxyPursuit();
proxyPursuit.knowTarget(target);

Pursuitor pursuitor = new Pursuitor();
pursuitor.setProxy(proxyPursuit);

pursuitor.doPursuit();

//代码流
Target target = new Target();
Pursuitor pursuitor = new Pursuitor();
ProxyPursuit proxyPursuit = new ProxyPursuit();
proxyPursuit.knowTarget(target);
proxyPursuit.setFrom(pursuitor);

proxyPursuit.doPursuit();
```

### 工厂方法模式

让子类决定实例化哪一种示例，用接口统一化实现

相对于简单工厂而言，不违背OCP。

### 原型模式

用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。 java里就是clone

### 模板模式

利用java的多态，抽象类设计抽象方法即可。。。

### 外观模式

定义一个高层接口，屏蔽了对一系列低层接口的细节操作 （就是统一操作？）

### 建造者模式

需要构建顺序稳定的情况。 先创建一个抽象类，然后用一个辅助类持有引用，并在创建方法中设定创建过程。

```text
Director director = new Director();
Builder b1 = new Builder1();//需要构建的部分
director.build(b1);//控制Builder接口的调用顺序
b1.getResult().display();
```

这种形式感觉没有Android Dialog.Builder的顺畅

### 观察者模式

常规观察者模式比较简单，一方持有观察者的列表，通知时遍历即可。最多抽取一下接口 但是对于现有的代码结构，可能不能都继承这样的接口，即行为不统一。 此时需要使用方法委托，而java的方法委托实际上是用反射实现的，参看EventHandler

### 抽象工厂模式

在需要配置 多个总组件，而每个组件下面又有基本一样的组件结构的时候。 可以考虑这种模式，甚至加上反射来简化。 （其实感觉平时写的时候，只要追求抽象，在业务逻辑有这种需要时，总能写出来这样的代码。）

### 状态模式

状态有序化

### 适配器模式

持有转化

### 备忘录模式

虽然clone有类似的效果，但是定制性不高。建议自己写一个需要的状态存储类 提了下命令模式。。又讲到下面去了。。

### 组合模式

树状依赖 透明方式：把非共有的方法也定义在抽象接口，在实现时如不需要则不进行操作 安全方式：把共有的方法抽象，而非共有的方法在需要的地方添加。

### 迭代器模式

foreach 咯

### 单例模式

### 桥接模式

有点莫名其妙的。。。把一个东西的多个维度，如有需要，抽取维度为独立的部分，被总体持有。

### 命令模式

感觉就是把一些被调用方的判断抽取成对应的类，和工厂方法模式有的一拼？ 然后加个中间类进行控制，让被调用方对于调用方而言，更加直白。

### 中介者模式

就是增加了中间类，持有所有需要交互的平级类。平级类又持有中间类，进行交互。 感觉加一些东西就是命令模式了。。。

### 享元模式

还是通过第三者存储，然后处理统一接口 主要目的是为了共享对象，当然也可以不共享（个人觉得这是基本操作吧？谈不上模式？） 如果有不能共享的数据，考虑从外部传入。。

### 解释器模式

接口设置不同的解释器，然后通过接口方法统一执行。层层过滤。。没了。。。 真的感觉后面的这些模式没啥差别啊。。。

### 访问者模式

很多时候，主体有确定数量的类别，但有很多属性。如果正常的设计，需要实例化不同类别的主体，再增加对应的属性。 而修改的可能性很高，且涉及到主体的多次修改。。。。 这个时候可以反其道而行，按属性抽象；同时将属性赋值给主体的同时，调用主体的对应方法，可以把属性的行为封装起来。 这样修改的时候，只需要添加属性具体类，并放入较少的主体信息即可。

