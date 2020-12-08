```kotlin
// 看接口注释
// 分为 lambda \ 匿名函数 \ 方法引用 
Function
```



支持 **默认参数**
并且调用的时候可以指定参数名赋值

（如果第一个参数是默认参数，会有实参优先级赋值的限制，写代码了解即可）

重写方法会使用被重写方法的默认参数，且不能重写默认值

positional 参数和named 参数，named 实参只能写在在positional 实参之后



有大括号的方法，一定要返回类型，主要是为了增加可读性

如果一个方法没有return 任何有用的值(useful value)，那么返回的类型是**`Unit` **。即可以省略return Unit

Unit 是一个只有一个值的类型



常规的return 是在最近的函数中进行返回，称作local-return

而lambda，因为特殊性，其内部的return 不会将其当做”最近的函数“，而是在lambda 外层的函数返回，所以叫做non-local return  

tips: list.foreach 可以传递匿名函数，在匿名函数内部return 不会跳出外部  



[infix]()

方法可以直接被语句赋值：

```kotlin
fun generateAnswerString(countThreshold: Int): String = 
    if (count > countThreshold) {
        "I have the answer"
    } else {
        "The answer eludes me"
    }
```

匿名方法：

```kotlin
val stringLengthFunc: (String) -> Int = { input ->
    input.length
}
```

高阶函数\(higher order functions\)，允许方法作为参数。

```kotlin
//而方法实参，可以用匿名函数
stringMapper("Android",{input - > input.length})
//如果匿名方法是方法中的最后一个参数，可以把括号写在外面　　
stringMapper("Android") { input ->
    input.length
}

fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /*...*/ }

foo(1) { println("hello") }     // Uses the default value baz = 1
foo(qux = { println("hello") }) // Uses both default values bar = 0 and baz = 1 
foo { println("hello") } 
```

[higher-order](./higher-order.md)



**变参**

`vararg` variable number of arguments

只有一个参数可以被声明为变参

如果变参不是最后一个参数，那么之后的参数可以使用named 实参

```kotlin
fun foo(vararg strings: String) { /*...*/ }

foo(strings = *arrayOf("a", "b", "c"))
```





**作用域方法**

包括 run, let, apply, also

这些方法都有一个接收器 this， 可能有参数 it， 可能有返回

https://github.com/JoseAlcerreca/kotlin-std-fun



#### 尾递归

`tailrec` 编译器自动优化

```kotlin
val eps = 1E-10 // "good enough", could be 10^-15

tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))

//生成代码
val eps = 1E-10 // "good enough", could be 10^-15

private fun findFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (Math.abs(x - y) < eps) return x
        x = Math.cos(x)
    }
}
```

