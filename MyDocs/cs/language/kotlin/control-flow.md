```kotlin
for (i in array.indices) {
    println(array[i])
}

for ((index, value) in array.withIndex()) {
    println("the element at $index is $value")
}
```

跳转

```kotlin
val s = person.name ?: return  

// 通过标记实现
// continue
loop@ for (i in 0..4) {
    println("222")
    return@loop
}

// break inner
loop@ for (i in 1..100) {
    for (j in 1..100) {
        if (...) break@loop
    }
}
```

