```kotlin
fun simple(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it
        yield(i) // yield next value
    }
}

fun main() {
    simple().forEach { value -> println(value) } 
}
```

```kotlin
val job = launch(Dispatchers.Default) {
    println("Launch start, current thread: ${Thread.currentThread()}")
    yield()
    println("After yield, current thread: ${Thread.currentThread()}")
}
```

官方的demo 理解无能，也不解释一下新概念（大概是因为太基础了把哈哈哈

> https://stackoverflow.com/questions/55129988/understanding-kotlins-yield-function
>
> 查了一下这个目前算是比较合理的，
>
> yield 类似代码分割器，常规逻辑下，整体的代码都被yield 分割成代码块列表。
>
> 每次执行的时候，都执行下一段并返回结果

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child is cancelled")
            }
        }
        yield()
        println("Cancelling child")
        child.cancel()
        child.join()
        yield()
        println("Parent is not cancelled")
    }
    job.join()    
}
```

```kotlin
supervisorScope {
            val child = launch {
                try {
                    println("The child is sleeping")
                    delay(Long.MAX_VALUE)
                } finally {
                    println("The child is cancelled")
                }
            }
            // Give our child a chance to execute and print using yield 
            yield()
            println("Throwing an exception from the scope")
            throw AssertionError()
        }
```

