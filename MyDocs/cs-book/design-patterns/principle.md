OCP -- Open\(for extend\)-Closed\(for modify\) principle

DIP -- Dependence Inversion principle

LoD -- Law of Demeter



- **单一职责** 原则：（SRP） 

就一个类而言，应该仅有一个引起它变化的原因 

- **开闭** 原则：\(OCP\) 

软件实体（类、模块、函数等）应该可以拓展，但是不可修改 

- **依赖倒转** 原则：

    高层模块不依赖低层模块，两者应该依赖抽象；抽象不依赖细节，细节依赖抽象 

- **里式代换**原则：（LSP）

子类型必须能够替换掉它们的父类型

- **迪米特**法则：（LoD）

如果两个类不必彼此直接通信，那么这两个类就不应当发生直接的相互作用，可以通过第三者转发。 

- **合成/聚合复用**原则：（CARP）

优先使用 合成/聚合，而不是类继承

- 对接口编程，而不是对实现编程

### 关于OCP原则

碰到一个场景，如果是数据库查询，我们将数据库操作简单抽象一下：

```text
interface Operate{
    boolean insert();
    TableBean query();
}
```

如果具体实现是单独的操作类，如SqlOperate 这个时候，我们需要添加一个表，那么需要对这个Operate进行操作，改成：

```text
interface Operate{
    boolean insertTable1()；
    boolean insertTable2();
    ...
}
```

那么就修改了这个操作 但是如果我们把数据库的操作实现在数据层，那么只需要添加对应的Table2的表 大概是model层操作数据的由来？

