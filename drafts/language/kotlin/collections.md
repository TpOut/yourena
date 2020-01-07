```kotlin
val fruits = listOf(...)
fruits
.filter{ it.startsWith("a") }
.sortedBy{ it }
.map{ it.toUpperCase() }
.forEach{ println(it) }

firstOrNull()
```

