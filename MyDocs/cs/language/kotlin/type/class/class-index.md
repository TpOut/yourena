`backing fields ` -> 支持字段



**Any**

所有对象的基类，包含方法`equals() `, `hashCode()` and `toString()`

`NaN` 和自己比较才相等，否则大于任何其他数，包括`POSITIVE_INFINITY` 

`-0.0` 比 `0.0` 要小



**拷贝函数**

需要自己实现

```kotlin
//定义，注意参数默认为当前实例的值
fun copy(name: String = this.name, age: Int = this.age) = User(name, age)

//使用，可以部分参数变更
val dest = src.copy(age = 2);
```



**inline 和typealias** 对比

```kotlin
typealias NameTypeAlias = String
inline class NameInlineClass(val s: String)

fun acceptString(s: String) {}
fun acceptNameTypeAlias(n: NameTypeAlias) {}
fun acceptNameInlineClass(p: NameInlineClass) {}

fun main() {
    val nameAlias: NameTypeAlias = ""
    val nameInlineClass: NameInlineClass = NameInlineClass("")
    val string: String = ""

    acceptString(nameAlias) // OK: pass alias instead of underlying type
    acceptString(nameInlineClass) // Not OK: can't pass inline class instead of underlying type

    // And vice versa:
    acceptNameTypeAlias(string) // OK: pass underlying type instead of alias
    acceptNameInlineClass(string) // Not OK: can't pass underlying type instead of inline class
}
```



