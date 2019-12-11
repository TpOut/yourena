可以直接在类的定义范围之外，拓展定义方法或者属性

```kotlin
data class User(var firstName: String?)

//
fun User.extendFun(): String {
    return "${firstName} after extand"
}

val user1 = User("l")
println(user1.extendTemp()) //
```

