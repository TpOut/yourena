```kotlin
// 看接口注释
// 分为 lambda \ 匿名函数 \ 方法引用 
Function
```

`fun`  

如果一个方法没有return 任何有用的值(useful value)，那么返回的类型是**`Unit` **。可以省略return Unit



**变参**

`vararg` variable number of arguments

只有一个参数可以被声明为变参

如果变参不是最后一个参数，那么之后的参数可以使用named 实参

```kotlin
fun foo(vararg strings: String) { /*...*/ }

foo(strings = *arrayOf("a", "b", "c"))
```



**作用域**

文件 / 类 / 方法内



#### 语法糖

[infix]()

声明调用  

```kotlin
fun powerOf(
    number: Int = 0, // 支持默认参数
    exponent: Int, // 可以使用逗号结尾
) 
// 调用
fun powerOf(exponent = 1)

// 动态参数
fun foo(vararg strings: String) { /*...*/ }
foo(strings = asList("a", "b", "c"))
```

重写  

```kotlin
open class A {
    open fun foo(i: Int = 10) {
        println(i)
    }
}

class B : A() {
    override fun foo(i: Int) { // 使用被重写方法的默认参数，即不能重写默认值
        println(i)
    }
}
// B().foo() // 结果是10
```

简写代码块（需要表达式单句 -- single expression  

```kotlin
fun generateAnswerString(countThreshold: Int): String = 
    if (count > countThreshold) {
        "I have the answer"
    } else {
        "The answer eludes me"
    }
```



#### 高阶函数

[higher-order](./higher-order.md)



#### 尾递归优化

`tailrec` 关键字    

编译器自动优化，需要递归是最后一行  

不能有try/catch/finally  

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

