[TOC]

### 构造

```kotlin
class Car private Constructor(val wheels: List<Wheel>){ // 主构造，没有修饰符可以省略
    val seats = listOf<Seat>()
    val wheelNum = wheels.size() // 可以直接调用构造参数
  
    private val close : Boolean = false
    var temp: Int = 15
        private set  //setter 访问权限控制 
  
    val index: Int = 4
    //or
    val index: Int  
    init{ // 初始化代码块，可以有多个init 块，init 中也可以用主构造的参数
        index = wheels.size()
    }
    
    constructor(name: String, wheels: List<Wheel>) : this(wheels) { // 次构造，代理主构造
        //...  
    }
}

val car = Car(mWheels) // 实例化，如果没有定义主次构造，则直接括号即可
//or
car = Car("lanbojini", mWheels)
```

构造顺序：主构造 >> 属性、init >> 次构造

类属性的默认访问权限是public

kotlin 在jvm 上会自动生成一个全参默认值的构造函数，方便Jackson 等序列化构造  



### 继承

所有的类都继承自 `Any` 类，包含`equals() `, `hashCode()` and `toString()`

`open`  



#### 方法重写

`super`  可以调用父类构造函数、方法、属性

`super@Outer` 外部类Outer 的父类

 `open` 只有父类为open 且方法也为open ，方法才可以被重写

`override` 子类重写父类方法的时候，需要指定

`final` override 方法在子类中也能被继承，除非用final 作为限制

```kotlin
open BaseLsj() {
  open fun draw(){...}
}

open class Lsj() : BaseLsj() {
    final override fun draw(){...}
}
```



#### 属性重写

类似方法，有一些限制：

- 属性的类型必须兼容
- 子类的重写属性，要能初始化，或者有get 方法

```kotlin
// 可以在主构造里使用, 
// 将val 属性重写为var 属性
class Rectangle(override var vertexCount: Int = 4) : Shape(val vertextCount: Int) 
```



#### 继承构造顺序

父主构造 >> 父init >> 父次构造 >> 子主构造 >> 子init  >> 子次构造  

因此要注意，父类的构造和初始化要谨慎甚至不使用open 属性，防止子类继承父类的open 属性后出现初始化顺序问题     



#### 内部类  

```kotlin
class FilledRectangle: Rectangle() {
    override fun draw() { 
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun drawAndFill() {
            super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
        }
    }
}
```



#### 多继承

```kotlin
// 如果出现继承方法冲突
open class Rect{
    open fun draw(){}
}
interface Polygon{
    fun draw(){}
}

class Square() : Rect(), Polygon {
    override fun draw(){
        super<Rect>.draw()
        super<Polygon>.draw()
    }
}
```



#### 析构(destruct)

```kotlin
val jane = User("Jane", 35) 
val (name, age) = jane
println("$name, $age years of age") 

for((firstName, lastName) int users!!)
```



#### 抽象类

`abstract` , 默认open，可以将一个非抽象方法，改写成抽象方法



#### 伴生类

`companion object`

这就是一种内置语法糖，在MyClass 里创建一个object，因为object 是单例  

所以每个myClass 实例持有的只有一个对象  

```kotlin
class MyClass{
    companion object{  // 可选类名
        println("companion");
    }
}

fun main(){
    MyClass.println(); // 对应调用可以省略object 名，如果要java 里调用也省略，需要注解，但是会增加实际的方法  
}
```



#### copy

```kotlin
//实现
fun copy(name: String = this.name, age: Int = this.age) = User(name, age)

//使用
val dest = src.copy(age = 2);
```



### data

需求主构造函数起码有一个参数，且参数直接声明（val/var）

不能为abstract, Open, sealed, inner

```kotlin
//data 关键字帮助class 自动生成getter, setter
//equals(), hashCode(), toString()，copy(), componentN()
data class User{var firstName: Sring?};
```

标准库有Pair 和Triple 两个data 类



### Sealed

可以称为枚举（enum）的拓展。枚举的每个类型只有一个实例，而sealed 可以每个类有多个实例来保存状态。

sealed 类的子类必须和其定义在同一个文件，但是再次继承没这个限制

sealed 本身是抽象的，可以拥有抽象成员

不能拥有非Private 构造（默认就是Private）



#### 使用场景：

```kotlin
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

//when 语句
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN //此时不需要使用else，即可覆盖其他所有情况
}
```



#### 嵌套类

```kotlin
class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

val demo = Outer.Nested().foo() // == 2

//如果使用inner 关键字，则可以访问外部类的变量
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

val demo = Outer().Inner().foo() // == 1
```



### 匿名类

对于只想修改某个现有类的一小部分，可以使用`object` 关键字

如下是匿名类的写法

```kotlin
window.addMouseListener(object : MouseAdapter() {

    override fun mouseClicked(e: MouseEvent) { ... }

    override fun mouseEntered(e: MouseEvent) { ... }
})

// jvm 中，单方法可以写成lambda
val listener = ActionListener { println("clicked") }

// 多继承
open class A(x: Int){
    public open val y: Int = x
}
interface B{ /*...*/ }
val ab: A = object : A(1), B {
    override val y = 15
}
```

如果这个类甚至不是修改一个现有的类型，而只是想快速添加以方便使用

```kotlin
fun foo() {
    // 这个匿名类的范围是local 或者private
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

// 对于上述更详细的示例
// 如果将匿名类作为public 属性，或者方法的public返回，那么会被转成已定义的父类或者Any
class C {
    // Private function, so the return type is the anonymous object type
    private fun foo() = object {
        val x: String = "x"
    }
    // Public function, so the return type is Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // Works
        val x2 = publicFoo().x  // ERROR: Unresolved reference 'x'
    }
}
```

匿名类可以访问最近一层作用域的变量



### 伴生类

```kotlin
//属于类，而不是属于“类对象”
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}
val instance = MyClass.create() // 直接使用

//可以省略声明对象名字
class MyClass {
    companion object { }
}
val x = MyClass.Companion //用Companion 表示

//如果一个类名被直接使用，就是代表获取这个类的伴生类对象，不管是不是有名字
class MyClass1 {
    companion object Named { }
}
val x = MyClass1
class MyClass2 {
    companion object { }
}
val y = MyClass2

//伴生对象可以作为类实现方法
interface Factory<T> {
    fun create(): T
}

class MyClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass = MyClass()
    }
}

val f: Factory<MyClass> = MyClass
```

在jvm 上，如果想用伴生类生成static 方法和fields，可以使用@JvmStatic 注解



### 单例

也使用 `object` 定义，是线程安全的

不能声明为Local（即不能在方法中定义）， 但是可以内嵌在非内部类中（包括其他object 类）

```kotlin
//单例模式，使用object 关键字
object Repo{
  ... //其他都不变
}
//调用直接用类名
val users = Repo.users
```



#### object 小节

object 语句在使用的时候就被执行

object 声明在第一次被访问的时候初始化

companion object 在附生的类被加载的时候初始化