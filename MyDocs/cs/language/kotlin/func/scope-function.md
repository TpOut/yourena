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



