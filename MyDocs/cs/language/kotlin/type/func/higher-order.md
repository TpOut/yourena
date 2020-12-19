#### 方法类型

kotlin 的方法是first-class function ( 参看wiki)

为了实现这种函数，就需要方法类型，语法为 :  

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

```kotlin
// 匿名函数形式
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

lambda 和匿名方法 都叫做 方法字面量（literal）。

字面量的意思是：方法没有被声明，但是会以表达式的形式传递  

> ```kotlin
> { a, b -> a.length < b.length }
> // 上面的字面量，等于下面的方法
> fun compare(a: String, b: String): Boolean = a.length < b.length
> ```



- [inline-function]() 优化调用

- [lambda]()

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
