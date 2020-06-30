每个属性，变量，都有点像一个封装类。

它们带有默认的 get \ set 方法，还有一个默认的 field（叫做 **支持字段** ）

使用默认的get \ set 方法时，操作的就是这个field 

如果是自定义 get \ set 方法，也可以操作这个field

> 这里看起来有些负担，但是jvm 有优化，所以不会增加消耗
>
> 如果 get \ set 没有操作field，那么也不会生成这个field



**支持属性**

如果支持字段还是不能满足需求，那么可以考虑使用支持属性

```kotlin
private val _users = mutableListOf<String>() //接口定义可以读写
val users: List<String> //接口定义不可写
    get() = _users;  
```

如此可以做到调用者无法修改属性

