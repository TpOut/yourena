```kotlin
// 同步问题还是看经典的++  
var counter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            counter++
        }
    }
    println("Counter = $counter")
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines 
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")    
}
```

上述使用基本类型，所以还能用诸如`Atomic*` 的方式处理  

但是如果类型变得复杂，就不太够了。想想还有一些方案：  

-  把数据处理的代码绑定在同一个线程上

    ```kotlin
    withContext(newSingleThreadContext("CounterContext")) {
        ....
        counter++
    }
    ```

- 把这段代码设置为互斥状态  

    ```kotlin
    Mutex().withLock {
        ....
        counter++
    }
    ```

- 使用channel 的一个线程安全方式

    ```kotlin
    //IncCounter 和GetCounter 是sealed 类
    fun CoroutineScope.counterActor() = actor<CounterMsg> {
        var counter = 0
        for (msg in channel) {
            when (msg) {
                is IncCounter -> counter++
                is GetCounter -> msg.response.complete(counter)
            }
        }
    }
    
    withContext(Dispatchers.Default) {
        ....
        counter.send(IncCounter)
    }
    ```

    

- 



对应线程锁，协程也有锁：

`mutex.lock()` `mutex.unlock()` `mutex.withLock`



除了常规的线程同步方式，可以使用`thread confinement` 

