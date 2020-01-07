变量在类中叫做属性

**val** 是一个不能重新赋值的变量，即没有setter  
**var** 则可以  

```kotlin
val temp = "ddd" //静态类型，推断是在编译时确定的
//不能对一个普通类型赋值为Null，但是在后面加问号则表示这个类型是nullable类型，即可以设置为null
var temp: String?

//如果类型是 : T?
//在.调用时加 ?，如果temp是null，那么结果也是null
temp?.toUpperCase() 

if(temp != null){
  temp.toUpperCase() //如果已经判断了，那么不需要加
}

temp!!.toUpperCase() //断言非空，如果是空，会抛异常
```

```kotlin
var stringRepresentation: String
    get() = this.toString()
    set(value) {
        setDataFromString(value) //
    }

//作用域或者注解
var ...
    private set
 //or 
    @Inject set
```





`const` 编译时常量，需求条件：顶级、`object` 或`companion` 的成员、基本类型或者String、getter 为默认

`import` 不仅仅用于导入类，还能导入顶级方法和属性，`object` 定义的方法和对象，enum 常量

`lateinit` : 常规的属性需要声明并（或在构造函数里）初始化，此关键字用来延迟初始化，可以使用 .isInitialized 来进行检查。



`is` 类型检查

`in` 用于循环，包括for，区间



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

