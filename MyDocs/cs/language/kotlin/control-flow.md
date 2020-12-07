```kotlin
for (i in array.indices) {
    println(array[i])
}

for ((index, value) in array.withIndex()) {
    println("the element at $index is $value")
}
```

跳转，首先要注意，很多方法其实都是匿名函数，即lambda

```kotlin
val s = person.name ?: return  

// 通过标记实现
// return
fun main{
    int j = 0
    loop@ for (i in 0..4) {
        println("222")
        return@loop // 注意这里的loop 没有实际效用，return 的是loop@ 的外层
    }
    println(j) // 不执行
}

// break
fun main() {
    var k = 0
    loop@ for (i in 1..10) {
        println("i $i")
        if (i > 1) break@loop // 跳出到loop ，即使里面嵌套for  
        //if (i > 1) continue@loop
    }
    println(k) // 执行
}

fun foo() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // non-local return from the lambda passed to run
            print(it)
        }
    }
    print(" done with nested loop")
}

// continue
fun main() {
    var k = 0
    loop@ for (i in 1..10) {
        println("i $i")
        if (i > 1) continue@loop // 跳出到loop ，即使里面嵌套for 
    }
    println(k) // 执行
}

listOf(1, 2, 3, 4, 5).forEach lit@{
    if (it == 3) return@lit // local return to the caller of the lambda, i.e. the forEach loop
    print(it)
}
print(" done with explicit label")

listOf(1, 2, 3, 4, 5).forEach {
    if (it == 3) return@forEach // local return to the caller of the lambda, i.e. the forEach loop
    print(it)
}
print(" done with implicit label")

// break inner
loop@ for (i in 1..100) {
    for (j in 1..100) {
        if (...) break@loop
    }
}
```

