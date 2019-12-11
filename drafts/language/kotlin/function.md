支持文件级方法（不需要在类内定义）

支持 **默认参数**
并且调用的时候可以指定参数名赋值

（如果第一个参数是默认参数，会有实餐优先级赋值的限制，写代码了解即可）



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
```



**作用域方法**

包括 run, let, apply, also

这些方法都有一个接收器 this， 可能有参数 it， 可能有返回

https://github.com/JoseAlcerreca/kotlin-std-fun