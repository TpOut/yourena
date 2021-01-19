### 继承

所有的类都继承自 `Any` 类，

`open`

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



#### 解构(destruct)

```kotlin
val jane = User("Jane", 35) 
val (name, age:String):User = jane  // 这里的类型参数可选
println("$name, $age years of age") 

// 实现的关键在于
operator fun User.component1() = getName()
operator fun User.component2() = getAge()

// 省略无用参数，可以减少对应的方法调用  
val (_, age) ... 
```

`componentN()` 是系统提供的一种语法机制，便于使用  

提供了n 个方法，就可以解构成n 个参数



