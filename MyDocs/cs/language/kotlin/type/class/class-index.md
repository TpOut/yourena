`backing fields ` -> 备份字段



**Any**

所有对象的基类，包含方法`equals() `, `hashCode()` and `toString()`

`NaN` 和自己比较才相等，否则大于任何其他数，包括`POSITIVE_INFINITY` 

`-0.0` 比 `0.0` 要小



**拷贝函数**

需要自己实现

```kotlin
//实现
fun copy(name: String = this.name, age: Int = this.age) = User(name, age)

//使用
val dest = src.copy(age = 2);
```



