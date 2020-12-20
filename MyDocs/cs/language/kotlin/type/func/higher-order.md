kotlin 的方法是first-class function ( 参看wiki)

为了实现这种函数，就需要方法类型，



#### 方法类型

语法为 :  

`val funVar : (Receiver.(p1: Params) -> Unit)? = ...`

接收器和参数名是可选的  

> 接收器会被转化为方法的第一个参数，即有接收器的和无接收器的方法可以互相转换
>
> ```kotlin
> val repeatFun: String.(Int) -> String = ...
> val twoParameters: (String, Int) -> String = repeatFun // OK
> 
> fun runTransformation(f: (String, Int) -> String): String {
>     return f("hello", 3)
> }
> val result = runTransformation(repeatFun) // OK
> ```



有了方法类型的概念后，就可以做实例化赋值。  

- **匿名方法** 形式

    ```kotlin
    val f = fun(x: Int){...}
    ```

    

有了实例之后，调用方式：

```kotlin
f.invoke(receiver, x) 
f(receiver, x)
receiver.f(x)  
```



#### 高阶函数\(higher order functions\)

就是允许方法作为参数，或者作为返回值。



#### 语法糖

- [inline-function]() 优化调用

- [lambda]()

    > ```kotlin
    > // 注意lambda 和简写函数的定义区别 -- 个人老是会搞混  
    > // 简写代码块，右值一定是单句表达式，且参数是写在左值里的  
    > // 而lambda，即使是单句表达式，也需要加大括号，参数是写在右值里的    
    > ```

    > ```kotlin
    > { a, b -> a.length < b.length }
    > // 上面的lambda 字面量，等于下面的方法
    > fun compare(a: String, b: String): Boolean = a.length < b.length
    > // 注意单行函数的定义，但是如果是多行呢？
    > // 所以理解成编译器会帮忙生成函数  
    > ```

- 操作符实例化？

  ```kotlin
  // 操作符 形式
  val fun2 = object : (Int, Int) -> Int {
      override fun invoke(p1: Int, p2: Int): Int {
          println("$p1")
          return 1
      }
  }
  ```

- [callable references](callable references)



#### 字面量

lambda 和匿名方法 都叫做 方法字面量，意思是：方法没有被声明，但是会以表达式的形式传递    

字面量方法添加接收器的时候，不用添加参数，接收器会被隐式转化为 this：  

```kotlin
val sum: Int.(Int) -> Int = { other -> plus(other) } // 等效 this.plus() 
val sum = fun Int.(other: Int): Int = this + other  
```

```kotlin
class HTML {
    fun body() { ... }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}

html {       // lambda with receiver begins here
    body()   // calling a method on the receiver object
}
```





#### 问题

1、匿名函数、lambda、单句方法简写，感觉就是相同的方法，不同的三种语法糖

<font color = red>具体实现，以后提升了再看看</font>