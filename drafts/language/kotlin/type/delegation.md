### 代理属性

现在有很多常用的属性类型，如

- lazy ：只有第一次访问的时候才被计算
- observable : 改变时会通知监听者
- map storing : 集合存储

可以在local 中使用代理属性哦，比如合理利用lazy 可以减少计算量

为了将这些类型以统一的语法形式进行覆盖，以方便后续切换，kotlin 提供了一种代理属性

```kotlin
class Example{
    var p: String by Delegate()
    // 语法规则是，val/var <property name>: <Type> by <expression>
}
```

其奥妙在Delegate() 有getValue() 和setValue() 的实现，会替代属性的get()，set() 方法

```kotlin
import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
 
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

//可以使用标准库方法
interface ReadOnlyProperty<in R, out T> {
    operator fun getValue(thisRef: R, property: KProperty<*>): T
}

interface ReadWriteProperty<in R, T> {
    operator fun getValue(thisRef: R, property: KProperty<*>): T
    operator fun setValue(thisRef: R, property: KProperty<*>, value: T)
}
```

使用代理后，实际上编译器会生成一个`prop$delegate` 的变量

```kotlin
class C {
    var prop: Type by MyDelegate()
}
// 编译器生成的
class C {
    private val prop$delegate = MyDelegate()
    var prop: Type
        get() = prop$delegate.getValue(this, this::prop)
        set(value: Type) = prop$delegate.setValue(this, this::prop, value)
}
// this::prop 是一个KProperty 的反射对象，它描述prop 的信息
```

#### 代理的代理

还有个代理的属性适用场景，是在getter/setter 之外，即属性创建的时候，检查属性的一致性。

这个行为称为对代理属性的代理，具体可以实现一个`provideDelegate`  操作符方法(operator)，那么就会调用它来创建对应的实例。也可以不创建哈哈哈（因为没有使用过，看的有点迷，具体在https://kotlinlang.org/docs/reference/delegated-properties.html#property-delegate-requirements）



#### lazy

```kotlin
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}
//默认情况下，lazyValue 的计算是同步的
//如果lazy 的参数是LazyThreadSafetyMode.PUBLICATION， 则线程安全
//LazyThreadSafetyMode.NONE，则不保证
```

#### observable

```kotlin
var name: String by Delegates.observable("<no name>") {
    prop, old, new ->
    println("$old -> $new")
}
//如果要做赋值拦截，使用 vetoable() 方法，会在赋值之前被回调
```

#### map storing

```kotlin
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}
class MutableUser(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int     by map
}
// 获取时就是从map 中查询key 为name、age 的值
```



#### 代理模式

Kotlin 原生支持，使用`by` 实现

```kotlin
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b {
    // override fun print(){} //也可重载，来自己实现
}

fun main() {
    val b = BaseImpl(10)
    Derived(b).print() // 自动在Derived 中生成Base 的所有方法
}
```

