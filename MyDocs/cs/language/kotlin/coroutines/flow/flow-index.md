listOf 马上装载  

sequence 懒加载循环器  

flow 则是协程环境下的懒加载循环器  



因此，flow 的代码块中可以调用suspend 方法异步处理结果，如：    

```kotlin
fun simple(): Flow<Int> = flow { // 构造器
    for (i in 1..3) {
        delay(100)
        emit(i) // 发射
    }
}

fun main() = runBlocking<Unit> {
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // 可以用超时关闭  
    withTimeoutOrNull(250) {
        // 接收
        simple().collect { value -> println(value) } 
    }
}
```

**创建方式：**

除了上面的flow 还有 flowOf / asFlow  

```kotlin
// flowOf
flowOf(1..3)

// asFlow，用于iterable  
(1..3).asFlow()
```

flow 创建的会每次emit 时自动检测取消，asFlow 这种则不会  



**操作符：**

最常见的map 示例如下：  

```kotlin
suspend fun performRequest(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}

fun main() = runBlocking<Unit> {
    val currentTimeMillis = System.currentTimeMillis()
    (1..3).asFlow() // a flow of requests
        .map { request ->
            println("${System.currentTimeMillis() - currentTimeMillis} passed, before request $request")
            performRequest(request)
        }
        .collect { response ->
            println("${System.currentTimeMillis() - currentTimeMillis} passed, before collect")
            println(response)
        }
}
//结果是先走完请求1 -> map -> collect，
//再开始请求2 -> map -> collect 
```

可理解成flow 包括collect 是**一个协程之内**的逻辑。也因此默认都是按串行顺序执行：一个值的操作走到底，再走下一个      

flow 对应协程的context 来自调用环境，叫做`context preservation`  

flow 内部的代码不允许使用`withContext` 进行调度器切换，而是在flow 构造后调用`flowOn`    

```kotlin
fun simple(): Flow<Int> = flow {
    //withContext(Dispatchers.Default){  //不允许
    for (i in 1..3) {
        Thread.sleep(100)
        log("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.Default) //正确的切换  

fun main() = runBlocking<Unit> {
    simple().collect { value ->
        log("Collected $value") 
    } 
}            
```

当使用`flowOn` 切换调度器的时候，flow 内部的协程和collect 内部的**协程就不是一个**了  

而且因为在不同线程，flow 内部的逻辑和collect 内的逻辑是同时进行的    



操作符具体分类有：

- 过程处理 map / transform 等，

    ```kotlin
    // transform 
    (1..3).asFlow()
            .transform { request -> 
                //发射多个
                emit("Making request $request") 
                emit(performRequest(request)) 
            }
            .collect { response -> println(response) }
    ```

- 过程处理 take / buffer / conflate / onEach 等

    ```kotlin
    // take 
    // 在达到数量之后，取消执行，所以会引发异常需要捕获    
    
    // buffer  
    // 将flow 协程拆分成多个，flowOn 的内部buffer 机制也是如此，但这里没有切换context    
    val time = measureTimeMillis {
        simple()
            .buffer() // emmit 部分不等待后续collect 执行完成
            .collect { value -> 
                delay(300)
                println(value) 
            } 
    }   
    
    // conflate  
    // collect 只处理运行时最后emit 的值
    val time = measureTimeMillis {
        simple()
            .conflate()
            .collect { value -> 
                delay(300)
                println(value) 
            } 
    }  
    
    // onEach  
    (1..3).asFlow().onEach { delay(300) }   
    ```

- 合并操作 

    ```kotlin
    // zip
    // 如果一个flow 400ms，一个flow 300ms 延迟，那么结果取大的400ms 延迟 
    val nums = (1..3).asFlow() 
    val strs = flowOf("one", "two", "three") 
    nums.zip(strs) { a, b -> "$a -> $b" } 
        .collect { println(it) }   
    
    // combine 
    // 两个flow 各自发射的时候，都会取两个flow 最新的值，进行接收
    nums.combine(strs) { a, b -> "$a -> $b" } 
        .collect { value -> println(value) } 
    ```

- 嵌套操作

    flattenConcat 

    ```kotlin
    // flatMapConcat  
    // 将 嵌套的requestFlow 的值，平铺到外部的flow 中
    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: First") 
        delay(500) // wait 500 ms
        emit("$i: Second")    
    }
    
    fun main() = runBlocking<Unit> { 
        val startTime = System.currentTimeMillis() // remember the start time 
        (1..3).asFlow().onEach { delay(100) }
            .flatMapConcat { requestFlow(it) }                                                                           
            .collect { value -> 
                println("$value") 
            } 
    }
    // flatMapMerge  
    // 类似flatMapConcat, 虽然flatMapMerge 代码块是串行的，但是collect 是并行的   
    // 等效于 map{}.flattenMerge  
    (1..3).asFlow().onEach { delay(100) } 
            .flatMapMerge { requestFlow(it) }                                                                           
            .collect { value -> println("$value) 
            } 
                                        
    // flatMapLatest  
    // emit 新值的时候取消前一个  
    ```

- 终端

    ```kotlin
    // collectLast  
    // 如果emit 的时候，有在执行的collect，则取消并用最新的值运行  
    // 如果没有在执行中，那么也会来一次打印一次    
    val time = measureTimeMillis {
        simple()
            .collectLatest { value -> 
                println("Collecting $value") 
                delay(300)
                println("Done $value") 
            } 
    }   
    
    // launchIn  
    fun main() = runBlocking<Unit> {
        events()
            .onEach { event -> println("Event: $event") }
            .launchIn(this) // <--- Launching the flow in a separate coroutine
        println("Done")
    }   
    ```

- 终端处理 / toList / first / reduce   

    ```kotlin
    // reduce, 和sequence 的操作感觉大体不差    
    val sum = (1..5).asFlow()
        .map { it * it }                          
        .reduce { a, b -> a + b }
    println(sum)
    ```

[异常]()



flow 结束状态 ： 成功，失败，取消  

- 结束 - 或发生被捕获异常，则成功

- 未捕获异常，失败

- 取消

    scope 取消就取消，或者job -- launchIn 返回 -- 取消  

    内部emit 的时候都会有`ensureActive` 进行判断是否已取消  

    如果没有emit 的地方，需要手动添加

    ```kotlin
    (1..5).asFlow()
        .cancellable() // 等同于.onEach { currentCoroutineContext().ensureActive() }
        .collect { value -> 
            if (value == 3) cancel()  
            println(value)
        } 
    ```

结束逻辑：

```kotlin
// 隐式 
fun simple(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking<Unit> {
    try {
        simple().collect { value -> println(value) }
    } finally {
        println("Done")
    }
}
// 显式
simple()
    .onCompletion { println("Done") }
    .collect { value -> println(value) }
```

用onCompletion 还有个好处，可以判断是否异常结束   

但是注意它不处理异常  

```kotlin
fun simple(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun main() = runBlocking<Unit> {
    simple()
        .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally") }
        .catch { cause -> println("Caught exception") }
        .collect { value -> println(value) }
}            
```

