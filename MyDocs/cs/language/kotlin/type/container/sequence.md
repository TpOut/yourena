实现了和Iterable 一样的方法  

但是每个元素都是懒加载的    

从底层来说，这样会增加一些负担，所以需要根据容器元素数量的多少，考虑哪个性能更好  



#### 语法糖

```kotlin
// 手动初始化
sequenceOf("two", "one") 
// 容器型初始化
listOf("one", "two").asSequence() // iterator 都可以
// 序列型初始化   
generateSequence(1) { it + 2 }.take(5).toList() // 中间返回null 时终止  
// 组合型初始化
sequence {
    yield(1)
    yieldAll(listOf(3, 5))
    yieldAll(generateSequence(7) { it + 2 })
}.take(5).toList() 
```

**操作符**

如果操作符返回一个sequence,也是懒处理的，这种叫做中间态(intermediate)  
否则就叫做最终态(terminal)。比如toList() / sum() ,
只有最终态的sequence 才能被检索  

```kotlin
map() filter() // 无状态，单独处理
take() drop()  // 无状态，一定数量  
               // 有状态，大量数量  
```



#### 对比

比如 list.filter.map.take  

对sequence.filter.map.take  

前者是 list.size -> filter -> filter.size -> map -> map.size -> take -> take.size  

后者是 take.size -> (filter -> map)

