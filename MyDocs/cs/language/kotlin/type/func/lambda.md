

lambda 理解成一个特殊的匿名函数   

```kotlin
// 在kotlin 中是一个抽象函数类
abstract class Lambda... : FunctionBase<R>
```

同时又是一个表达式，将最后一行的结果作为返回值   

所以不能有一个显示的返回（如果有，就截断了本来作为返回值的最后一行的结果）  



常规的return 是在最近的函数中进行返回，称作local-return

而lambda，因为特殊性，其内部的return 不会将其当做”最近的函数“，而是在lambda 外层的函数返回，所以叫做non-local return  

tips: list.foreach 可以传递匿名函数，在匿名函数内部return 不会跳出外部  



但是如果标记为inline , 则允许有显示返回， 因为编译器将其改写了  



在lambda 中返回只能借助标签    



#### 语法糖

```kotlin
// lambda 作为方法最后一个参数的时候，可以简写
fun foo(
    qux: () -> Unit,
) { /*...*/ }


//而方法实参，可以用匿名函数
stringMapper("Android",{input - > input.length})
//如果匿名方法是方法中的最后一个参数，可以把括号写在外面　　
stringMapper("Android") { input ->
    input.length
}
foo { println("hello") }  
```





