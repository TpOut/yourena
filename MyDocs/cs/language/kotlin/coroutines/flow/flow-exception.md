不管是捕获collect 内，还是捕获flow 内，还是操作符内    

```kotlin
fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
     try {
        simple().collect { value ->         
            println(value)
            check(value <= 1) { "Collected $value" }
        }
     } catch (e: Throwable) {
         println("Caught $e")
     } 
} 

fun simple(): Flow<String> = 
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i) // emit next value
        }
    }
    .map { value ->
        check(value <= 1) { "Crashed on $value" }                 
        "string $value"
    }

fun main() = runBlocking<Unit> {
    try {
        simple().collect { value -> println(value) }
    } catch (e: Throwable) {
        println("Caught $e")
    } 
}
```

甚至还有操作符用于再度发送异常

```kotlin
fun main() = runBlocking<Unit> {
    simple()
        .catch { e -> emit("Caught $e") } // emit on exception
        .collect { value -> println(value) }
}
```

这种方式只能捕获catch 的上游  

而不能捕获下游  

如果有需要，可以用onEach 提前

```kotlin
simple()
    .onEach { value ->
        check(value <= 1) { "Collected $value" }                 
        println(value) 
    }
    .catch { e -> println("Caught $e") }
    .collect()
```

