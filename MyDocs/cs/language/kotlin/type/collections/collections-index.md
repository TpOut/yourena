

kotlin 中增加了一个mutable 类型，非mutable 的则是只读的。  

只读的collections 是协变的。map 的key 不是，value 是  



list 默认推导是ArrayList   

set 默认推导是 LinkedHashSet    

map 默认推导是LinkedHashMap



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

