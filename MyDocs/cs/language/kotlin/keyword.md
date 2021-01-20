`@` 可以用于标记表达式，但是并不影响表达式本身    

```kotlin
loop@ for (i in 0..4) {
    println("222")
    return@loop
}

var result = loop@{ // 结果是 () -> kotlin.Int
    return@loop 1 // non-local return from the lambda passed to run
}

var result = run loop@{ // 结果是 1
    return@loop 1 // non-local return from the lambda passed to run
}

var result = run{ // 结果是 () -> kotlin.Int
        loop@{
            return@loop 1 // non-local return from the lambda passed to run
        }
    }

```



`import` 导入包、顶级方法和属性、object 单例的方法和属性、枚举常量  



`const` 编译时常量，需求条件：顶级、`object` 或`companion` 的成员、基本类型或者String、getter 为默认

`import` 不仅仅用于导入类，还能导入顶级方法和属性，`object` 定义的方法和对象，enum 常量



`is` `!is` 类型检查

`in` 主要用于循环，包括for，区间( .. , until , downTo , step )，通过`IntRange` `IntProgression` 等实现

```kotlin
class Version(val version: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int {
        return version - other.version
    }
}

// Range. 
// 要自己实现compareTo() 让contains（即in） 方法做判断    
// 同时为了支持..，需要对应实现 rangeTo 方法（当然Comparable 已经实现拓展方法了
val versionRange = Version(1, 11)..Version(1, 30)
println(Version(0, 9) in versionRange)

//实现rangetTo() 让... 操作符调用   


//Progress，实现了Iterator，所以支持collection 的变换方法  
IntProgression, LongProgression, and CharProgression.
```



`this` 表示接受器实例或者最近的闭包(*innermost enclosing scope*)  

https://kotlinlang.org/docs/reference/this-expressions.html  



`*` 展开参数  

```kotlin
fun foo(vararg strings: String) { /*...*/ }
foo(strings = *arrayOf("a", "b", "c"))
```



`as ` / `as?`

```kotlin
import org.example.Msg
import org.text.Msg as textMsg

val aInt: Int? = a as? Int // 如果a 不是Int  
```



`yield ` 

```
// 目前的理解是和sequence 的构造器配合，实现一个`发射` 数据的功能

// 但是不太理解和协程配合时为什么阻塞和不阻塞的问题

Restricted suspending
```
