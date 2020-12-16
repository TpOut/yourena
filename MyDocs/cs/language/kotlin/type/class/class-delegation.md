这种写法简化了Derived.b 的组合代理    

Base 可以继承自多个接口

```kotlin
interface Base {
    val message: String
    fun print()
}
class BaseImpl() : Base {
    override val message = "BaseImpl: x = $x"
    override fun print() { print(message) }
}

// Base 必须是接口
class Derived(b: Base) : Base by b {
    // 重写之后，如果print 没重写，则无法获取这个message，即b 实例无法获取重写后的 message
    // override val message = "Message of Derived" 
    
    // 重写之后不会调用代理
    // override fun print(){} 
}

fun main() {
    val b = BaseImpl(10)
    Derived(b).print() // 自动在Derived 中用b 代理所有方法 
}
```

