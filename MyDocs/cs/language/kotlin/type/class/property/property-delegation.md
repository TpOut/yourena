属性的处理有很多常见的场景，比如只处理一次，后续用缓存；设置的时候，通知其他需要知道的对象等等  

当我们针对这些场景，对属性的处理进行封装的时候，会套上一层操作，即进行属性代理  



属性代理的语法是：

```kotlin
// 语法规则是
val/var <property name>: <Type> by <expression>
// 如
class Example{
    var p: String by Delegate()
}
fun example(){
    var p: String by Delegate()
}
// 这里的Delegate() 即是代理属性表达式, by 表示从表达式获取代理
// 而因为get()/set() 对应getValue()/setValue() 方法 
// 所以Delegate() 需要实现getValue() 和setValue() -- val 不需要set  
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
 
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}
// 此时你调用p = "2" 和获取p 的时候 对应会调用Delegate.setValue ，Delegate.getValue  
// 但是日志会显示
这个例子中 
thisRef 的类型可以是Example 和它的父类，KProperty 可以是String 和它的父类，value 和属性一样或者是父类
thisRef = p所依附的类/如果在方法中则是null, property.name = p, value = "2"
```



而套上的那一层操作可能有很多形式（即上面的set/get），但一般而言有：

- 获取的时候，只有第一次获取是计算的，后面都是获取缓存。实现为lazy

    ```kotlin
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }
    // 默认情况下，lazyValue 的计算是`synchronized` 的，单线程执行，其他线程马上可见
    // 如果lazy 的参数是LazyThreadSafetyMode.PUBLICATION，则认为可以多线程执行  
    // 如果参数是LazyThreadSafetyMode.NONE，不做任何额外线程相关保证。Android 适合
    ```

- 设置的时候，通知其他想知道的人。实现为obervable

    ```kotlin
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
    // 默认值是 "<no name>"
    // 这里的方法是在赋值之后调用，如果要赋值之前调用（比如要做赋值拦截），使用 vetoable() 方法
    ```

- 将一个属性 代理为另一个属性

    ```kotlin
    // 适用规则
    class MyClass(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
        var delegatedToMember: Int by this::memberInt
        var delegatedToTopLevel: Int by ::topLevelInt
        
        val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
    }
    var MyClass.extDelegated: Int by ::topLevelInt
    
    // 在废弃一个属性，新建一个属性时很有用
    class MyClass {
       var newName: Int = 0
       @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
       var oldName: Int by this::newName
    }
    
    fun main() {
       val myClass = MyClass()
       // Notification: 'oldName: Int' is deprecated.
       // Use 'newName' instead
       myClass.oldName = 42
       println(myClass.newName) // 42
    }
    ```

- 很多属性单独代理的话会很麻烦，希望批量处理。

    ```kotlin
    class MutableUser(val map: MutableMap<String, Any?>) {
        var name: String by map
        var age: Int     by map
    }
    val user = User(mapOf(
        "name" to "John Doe",
        "age"  to 25
    ))
    // 比如json 等动态处理时
    ```




属性代理的底层实现是编译器会生成一个`prop$delegate` 的变量

```kotlin
class C {
    var prop: Type by MyDelegate()
}
// 编译器生成的
class C {
    private val prop$delegate = MyDelegate()
    //如果实现provideDelegate
    private val prop$delegate = MyDelegate().provideDelegate(this, this::prop)
    
    var prop: Type
        get() = prop$delegate.getValue(this, this::prop)
        set(value: Type) = prop$delegate.setValue(this, this::prop, value)
}
// this::prop 是一个KProperty 的反射对象，它描述prop 的信息
```



代理属性甚至可以用于本地方法：

```kotlin
fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)
    // 如果条件不成立，完全不需要调用doSomething
    if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }
}
```



更简便的方式，甚至不需要创建一个代理类，直接匿名类实现接口即可：

```kotlin
interface ReadOnlyProperty<in R, out T> {
    operator fun getValue(thisRef: R, property: KProperty<*>): T
}

interface ReadWriteProperty<in R, T> {
    operator fun getValue(thisRef: R, property: KProperty<*>): T
    operator fun setValue(thisRef: R, property: KProperty<*>, value: T)
}
```



但是在使用ReadOnlyProperty 实现的时候发现还有个不足：

```kotlin
// 类似Android 中初始化，findViewById 之后，需要转化成ImageView 或者TextView  
// 所以我们需要传入一个string, 即“image”/"text" 来区分
class MyUI {
    val image by bindResource(ResourceID.image_id, "image")
    val text by bindResource(ResourceID.text_id, "text")
}

fun <T> MyUI.bindResource(
        id: ResourceID<T>,
        propertyName: String
): ReadOnlyProperty<MyUI, T> {
   if(checkProperty(this, propertyName)){
       return ImageDelegate
   }else{
       return textDelegate
   }
}
// 上面这种需要将属性的类别显式传给代理方法

--<font color =red >没看懂这里为什么改成下面就不需要了</>--

// 为了减少这种不方便，kotlin 为你提供一个provideDelegate 方法
// 此方法在属性依赖类（MyUI）创建时生效，即代理对象创建时

class ResourceDelegate<T> : ReadOnlyProperty<MyUI, T> {
    override fun getValue(thisRef: MyUI, property: KProperty<*>): T { ... }
}

class ResourceLoader<T>(id: ResourceID<T>) {
    operator fun provideDelegate(
            thisRef: MyUI,
            prop: KProperty<*>
    ): ReadOnlyProperty<MyUI, T> {
       if(checkProperty(this, prop.name)){
           return ImageResourceDelegate
       }else{
           return textResourceDelegate
       }
    }

    private fun checkProperty(thisRef: MyUI, name: String) { ... }
}

class MyUI {
    fun <T> bindResource(id: ResourceID<T>): ResourceLoader<T> { ... }

    val image by bindResource(ResourceID.image_id)
    val text by bindResource(ResourceID.text_id)
}
```

```kotlin
// provider 同样可以简写为方法
val provider = PropertyDelegateProvider { thisRef: Any?, property ->
    ReadOnlyProperty<Any?, Int> {_, property -> 42 }
}

val delegate: Int by provider
```
