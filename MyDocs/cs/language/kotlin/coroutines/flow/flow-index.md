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
    // 可以用超时关闭，所以可理解成collect 里面是一个协程之内的逻辑？
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

上面示例的结果也揭示了flow 里的操作符都是按串行顺序执行  



具体分类有：

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

    具体代码看原文吧

    ```kotlin
    // take 
    // 在达到数量之后，取消执行，所以会引发异常需要捕获    
    ```

- 终端处理 collect / toList / first / reduce   

    ```kotlin
    // reduce, 和sequence 的操作感觉大体不差    
    val sum = (1..5).asFlow()
        .map { it * it }                          
        .reduce { a, b -> a + b }
    println(sum)
    ```

    



**启动方式：**

启动 collect / safeCollect  

修改运行协程 launchIn



组合 zip / flate

异常捕获 catch  



flow 结束状态 ： 成功，失败，取消  

结束 - 或发生被捕获异常，则成功

未捕获异常，失败

取消

> Q : 取消很奇怪，是依赖scope 取消的，而不能直接获取flow 自身之类的进行取消？
>
> A : launchIn 会返回job，那默认的呢？



flow 的context 是从“终端 - 操作” 的调用协程来获取的

可以在构建时修改context ，但是设计上不允许flow 的代码块中修改 -- flowOn  



