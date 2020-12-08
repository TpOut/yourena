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



`is` 类型检查

`in` 用于循环，包括for，区间( ... , until , downTo , step )，通过`IntRange` `IntProgress` 等实现  



let

```kotlin
value?.let {
    ... // execute this block if not null
}

val nullableFoo :Foo? = ...
//只在不为空的时候，传入方法并且执行
nullableFoo?.let { foo ->
   foo.baz()
   foo.zap()
}
```

with

```kotlin
//同时执行一个类的方法
class Turtle {
    fun penDown()
    fun penUp()
    fun turn(degrees: Double)
    fun forward(pixels: Double)
}

val myTurtle = Turtle()
with(myTurtle) { //draw a 100 pix square
    penDown()
    for(i in 1..4) {
        forward(100.0)
        turn(90.0)
    }
    penUp()
}
```

apply

```kotlin
//配置属性，弥补构造？
val myRectangle = Rectangle().apply {
    length = 4
    breadth = 5
    color = 0xFAFAFA
}
```

also

```kotlin
var a = 1
var b = 2
a = b.also { b = a }
// a = b 之后再执行一遍also 括号里的内容b = a，但是a 还是之前的内容
```

as

```kotlin
import org.example.Msg
import org.text.Msg as textMsg
```



yield

```
// 目前的理解是和sequence 的构造器配合，实现一个`发射` 数据的功能

// 但是不太理解和协程配合时为什么阻塞和不阻塞的问题

Restricted suspending
```



inline 

```kotlin
inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)
```

