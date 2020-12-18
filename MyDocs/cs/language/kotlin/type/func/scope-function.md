**作用域方法**

包括 run, let, apply, also

这些方法都有一个接收器 this， 可能有参数 it， 可能有返回

https://github.com/JoseAlcerreca/kotlin-std-fun





在kotlin 标准库中，有很多方法，只是为了在某个对象环境中执行一段代码

基于lambda表达式的支持，这段代码可以直接访问对象而不需要变量名



有五个方法，他们的区别主要在于对象如何被代码块访问，以及表达式的返回值

对于访问，有lambda receiver(`this` ) 和lambda argument(`it` ) 的区别

#### let

```kotlin
Person("Alice", 20, "Amsterdam").let {
    println(it)
    it.moveTo("London")
    it.incrementAge()
    println(it)
}
```



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

