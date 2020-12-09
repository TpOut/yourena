拓展的实现上其实是创建一个接收对应对象的方法，然后处理成一定的结果返回（通过字节码反编译查看）  

只是在语法糖包装之后，看起来像是对象的成员  

拓展部分在调用端，需要导入    

伴生类也可以使用拓展属性和方法，伴生方法和属性属于定义时所处的包    



#### 拓展属性

属性拓展时也只是种”假象“，并不是类本身添加了这个属性

所以它不会有 支持字段，因此不能声明时初始化，且只能通过 get / set 进行访问



#### 拓展方法

可以直接在类的定义范围之外，拓展定义方法或者属性  

```kotlin
data class User(var firstName: String?)

// User 是方法的接收者  
fun User.extendFun(): String {
    return "${firstName} after extand"
}

val user1 = User("l")
println(user1.extendTemp()) //
```

```kotlin
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // this 会在实例化的时候持有实例
    this[index1] = this[index2]
    this[index2] = tmp
}
```



拓展方法可以重写，但是**拓展方法不是虚**的，它是在编译的时候静态绑定的  

> 看编译后代码，个人理解成，其实就是两个方法

```kotlin
open class Shape
class RecShape : Shape()
fun Shape.getName() = "shape"
fun RecShape.getName() = "RecShape"
fun printClassName(s : Shape){ print(s.getName()) }

printClassName(RecShape()); //此时输出是”Shape“
```



拓展函数和成员函数相同时，以成员函数优先。但是如下nullable 示例，**Any? 和 Any 不属于一个类型**  



可以对nullable 类型指定拓展

```kotlin
//这样不需要在调用方进行空判断
fun Any?.toString(): String{
  if(this == null) return "null"
  retrun toString()
}
```



#### 拓展成员函数  

如下示例，Connection（B） 中定义了Host（A） 的拓展函数。  

此时A 的拓展函数内，可以访问 A 和B 实例（不需要限定符）,即拓展函数有多个接收器    

A实例 叫做拓展接收器（extension receiver）, B例 叫做分发接收器(dispatch receiver) 。   

> 通过反编译查看，常规的拓展函数是静态方法，而这种情况是一个成员方法  
>
> 可以被继承重写

```kotlin
class Host(val hostname: String) {
    fun printHostname() { print(hostname) }
}

class Connection(val host: Host, val port: Int) {
     fun printPort() { print(port) }

     fun Host.printConnectionString() {
         printHostname()   // calls Host.printHostname()
         printPort()   // calls Connection.printPort()
     }

     fun connect() {
         host.printConnectionString()   // calls the extension function
     }
    
     // 重名方法优先调用A 的。可以通过this@ 显式限定符
     fun Host.getConnectionString() {
        toString()         // calls Host.toString()
        this@Connection.toString()  // calls Connection.toString()
     }
}
```

之前说过拓展函数不是虚函数，而此时作为成员函数，它是虚的。  

取决于接收器的类型。  

