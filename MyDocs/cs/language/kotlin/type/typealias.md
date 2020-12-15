`typealias`  

你觉得可以简写的类型声明都可以使用别名

```kotlin
// 类
typealias NodeSet = Set<Network.Node>
typealias FileTable<K> = MutableMap<K, MutableList<File>>

// 嵌套类
class A {
    inner class Inner
}
typealias AInner = A.Inner

// 方法
typealias MyHandler = (Int, String, Any) -> Unit
typealias Predicate<T> = (T) -> Boolean

// 传参
typealias Predicate<T> = (T) -> Boolean
fun foo(p: Predicate<Int>) = p(42)

fun main() {
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f)) // prints "true"

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p)) // prints "[1]"
}
```





