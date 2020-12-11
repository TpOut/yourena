

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
