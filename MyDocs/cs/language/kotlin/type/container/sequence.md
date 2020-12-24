实现了和Iterable 一样的方法  

但是每个元素都是懒加载的    

从底层来说，这样会增加一些负担，所以需要根据容器元素数量的多少，考虑哪个性能更好  



#### 语法糖

```kotlin
sequenceOf  //
.asSequence() // iterator 容器转换
```



