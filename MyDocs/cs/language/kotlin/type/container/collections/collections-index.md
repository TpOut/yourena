

kotlin 中增加了一个mutable 类型，非mutable 的则是只读的。  

只读的collections 是协变的。map 的key 不是，value 是  



list 默认推导是ArrayList   

set 默认推导是 LinkedHashSet    

map 默认推导是LinkedHashMap



继承相关 `AbstractCollection`  `AbstractList`  `AbstractSet`  `AbstractMap`



#### 语法糖

```kotlin
// set 和map 也都有对应写法

emptyList()
listOf()
mutableListOf()  
.toList() // 元素深拷贝  
.toMutableSet() // 转换  
.forEach{}
```

[functions]()  

```kotlin
// list 可以通过构造函数创建  
val doubled = List(3, { it * 2 })  // it 是索引  
println(doubled)
// 有专门的迭代器，支持双向、获取索引，可以编辑的还支持增改      
.listIterator()  
```

```kotlin
// map
mapOf("key1" to 1)  // 注意to 这种写法会创建一个临时对象Pair，极致性能需求时，不建议使用  
```





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

