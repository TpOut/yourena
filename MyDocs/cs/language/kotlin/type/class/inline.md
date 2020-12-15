```kotlin
// gradle 开关
kotlin {
    sourceSets.all {
        languageSettings.enableLanguageFeature("InlineClasses")
    }
}
```

为了程序员的sufu，类型的用法上有很多封装，比如基本类型   

会导致很多多余的实例创建

这样jvm 的内存分配压力就很大  



inline 就是为了减少这种场景  

首先要定义inline 使用的场景：

- inline class 在主构造函数中必须是一个属性  

- 这样代码会在运行时，尽量不创建这个class，而是只创建这个属性的class  

    > 编译器会保留两个class，以便运行时互转
    >
    > 也因此，引用相等在这没啥意义

- 然后赋值给对应的左值  

- 不能有init 代码块

    > 因为不会创建实例

- 不能继承类，且是final     



当然如果只有属性，场景就很小了，还是可以添加属性和方法的

- 不能有计算属性（lateinit/代理属性，支持字段）
- 方法可以继承自接口

```kotlin
inline class Name(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}    
// 不管是方法name.greet() 还是属性name.length 都会被处理成静态方法
fun main() {
    val name = Name("Kotlin")
    name.greet()
    println(name.length)
}
```



#### box

这里还是没看太懂为什么是这样？

```kotlin
interface I

inline class Foo(val i: Int) : I

fun asInline(f: Foo) {}
fun <T> asGeneric(x: T) {}
fun asInterface(i: I) {}
fun asNullable(i: Foo?) {}

fun <T> id(x: T): T = x

fun main() {
    val f = Foo(42)

    asInline(f)    // unboxed: used as Foo itself
    asGeneric(f)   // boxed: used as generic type T
    asInterface(f) // boxed: used as type I
    asNullable(f)  // boxed: used as Foo?, which is different from Foo

    // below, 'f' first is boxed (while being passed to 'id') and then unboxed (when returned from 'id')
    // In the end, 'c' contains unboxed representation (just '42'), as 'f'
    val c = id(f)
    println(c.javaClass) // 打印的类型是Foo  
}
```



#### Mangling

如此解封装之后，两个方法可能产生冲突：

```kotlin
inline class UInt(val x: Int)

fun compute(x: Int) { }
fun compute(x: UInt) { }

// 都生成 public final void compute(int x)
```

为了解决冲突，使用inline class 参数的方法会被添加hash

```kotlin
public final void compute-<hashcode>(int x)  
```





