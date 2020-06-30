```kotlin
// 类
class Box<T>(t: T){
    var value = t
}

val box: Box<Int> = Box<Int>(1) // 可推断，可简写

// 方法
fun <T> singletonList(item: T): List<T> {
    // ...
}

fun <T> T.basicToString(): String {  // extension function
    // ...
}

val l = singletonList<Int>(1)
```

泛型一样会在运行时进行类型擦除

但是inline reified 可以应对此种情况，进行类型检查 

```kotlin
//  public final class Gson {
//     ...
//     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
//     ...

inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)
```



#### variance

Java 中，泛型是不可变( invariant，如List<String> 不是 List<Object> 子类 )的，所以使用通配符来增加适用性，但是很多时候还是很僵硬。



declaration-site variance 

对于没有消费元素类型（如上述List<Object> 只是从List<String> 获取数据，然后传给外部）的泛型而言，不应该禁止相对的转化。

```kotlin
//kotlin 就加了 out 修饰符, 叫做可变注解(variance annotation)
interface Source<out T>{
    fun nextT(): T
}
fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}

// 相对的增加了 in 修饰符，只能消费，不能生产
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}
```



type projections 

上述的方式在声明处标明类型的使用情况，但是很多时候虽然声明时既是消费者又是生产者，而使用的时候是独立的。

此时可以在使用处进行`out/in`声明

```kotlin
fun copy(from: Array<out Any>, to: Array<Any>) { ... }
```



star projections

![企业微信截图_0b0fa6ec-bf20-4474-9a89-3256bc697281](%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_0b0fa6ec-bf20-4474-9a89-3256bc697281.png)



#### 泛型约束

向上绑定(upper bound)，即java 的extend

```kotlin
fun <T : Comparable<T>> sort(list: List<T>) {  ... }

//默认的向上绑定是 Any?
```

```kotlin
//括号里只能写一个向上绑定，如果要多个，需要where 
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
    where T : CharSequence,
          T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
```



