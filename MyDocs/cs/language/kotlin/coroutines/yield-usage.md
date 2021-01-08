#### 使用场景

```kotlin
// 可以用于简便响应cancel 
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            yield() //如果不加这个suspend 函数，没有地方判断是否cancel
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}
```

```kotlin
// 用于生产列表  
fun simple(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(100)
        yield(i) // yield next value
    }
}

fun main() {
    simple().forEach { value -> println(value) } 
}
```

```kotlin
// 正常功能
val job = launch(Dispatchers.Default) {
    println("Launch start, current thread: ${Thread.currentThread()}")
    yield()
    println("After yield, current thread: ${Thread.currentThread()}")
}
```

#### 场景理解

官方的demo 理解无能，也不解释一下新概念（大概是因为太基础了把哈哈哈

> https://stackoverflow.com/questions/55129988/understanding-kotlins-yield-function
>
> 查了一下这个目前算是比较合理的，

yield 类似代码分割器，常规逻辑下，整体的代码都被yield 分割成代码块列表。

当执行到yield 的时候，会告诉调度器，给其他协程先用下    

下次调度执行的时候，会恢复到上一次执行的代码点，然后继续下一段（这部分类似coroutineScope 的逻辑）    

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

