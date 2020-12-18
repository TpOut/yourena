kotlin 的方法是first-class function ( 参看wiki)

为了实现这种函数，就需要方法类型，即一种定义方式 :  

`val funVar : (Receiver.(p1: Params) -> Unit)? = ...`

接收器和参数名是可选的  



有了方法类型的概念后，就可以做实例化：

```kotlin
// lambda 形式
val stringLengthFunc: (String) -> Int = { input ->
    input.length
}

// 匿名函数形式
... = fun(s: String): Int { return s.toIntOrNull() ?: 0 }

// 可调用形式 [callable references]()
```



而高阶函数\(higher order functions\)则是允许方法作为参数，或者作为返回值。

```

```



当将方法作为参数的时候，可以用 [lambda]() 简化编写

