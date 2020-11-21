很多列表啥的都有不可变的类型



```kotlin
val fruits = listOf(...)
fruits
.filter{ it.startsWith("a") }
.sortedBy{ it }
.map{ it.toUpperCase() }
.forEach{ println(it) }

firstOrNull()
```

