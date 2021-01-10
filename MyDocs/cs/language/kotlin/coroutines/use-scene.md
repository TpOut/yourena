[channels]() 

[flow]() 

[debug]()  

[select]()  



#### suspend

关于suspend ，它只是一个标记，表示内部的代码块可被挂起  

被标记为suspend 的函数，会保证函数（调用点）之后的代码，在函数执行完成之后执行  

```kotlin
runBlocking{
    coroutineScope{
        println("xixi")  
    }
    println("over")  //一定在coroutineScope 里的内容执行后打印
}
```

#### GlobalScope

执行时，类似守护线程  

```kotlin
//GlobalScope.async 方法不是`suspend` 方法，它启动后是线程异步的。     
val job = GlobalScope.async{doSomething}
doOtherLogic()
job.await()
// 这种单独执行async 的方式不被推荐，因为doOtherLogic 出错时抛出异常，取消整体操作，但是job 还在后台运行  
```

#### delay

```kotlin
// 将协程延迟执行
GlobalScope.launch {
    delay(1000L)
    println("World!")
}

```

#### yield

[yield-usage]()

#### cancel

拓展函数 `cancelAndJoin`    

协程的可取消在于`suspend` 方法，每次执行时会进行判断。  

如果正在执行密集型计算，那么就没有地方会进行判断。需要代码里调用`yield`，或者显式判断逻辑`isActive`  

```kotlin
// 取消会引起异常  
val job = launch {
    try {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    } finally {
        println("job: I'm running finally")  
        // 一般性join 后finally 代码块都会立即执行  
        // 但是如果的确是一个耗时操作，需要`withContext(NonCancellable)`
        withContext(NonCancellable) {
            println("job: I'm running finally")
            delay(1000L)
            println("job: And I've just delayed for 1 sec because I'm non-cancellable")
        }
    }
}
delay(1300L)  
println("main: I'm tired of waiting!")  
job.cancelAndJoin()    

```

**超时**  

具体是通过Dispatcher 来进行时间跟踪的  

```kotlin
withTimeout(1300L) {
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
}

// 获取结果
val result = withTimeoutOrNull(1300L) {
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
    "Done" // will get cancelled before it produces this result
}
println("Result is $result")
```

超时的异常是异步的，所以可能在代码执行之前出现异常，但是在代码执行之后，才抛出  

所以在使用资源类需要关闭的逻辑时，不能使用上面的从结果赋值的方式，而是：  

```kotlin
var resource: Resource? = null
    try {
        withTimeout(60) {
        delay(50)
        resource = Resource()  
        }
    } finally {  
        resource?.close()   
    }
```



#### dispatcher

用来管理coroutine 和thread 的关联(confine)     

- `Dispatchers.Unconfined`   
- `Dispatchers.Default` 是一个共享的线程池，和Global.launch 使用的一个参数  
- `newSingleThreadContext` ，为携程单独创建的线程，实际应用中不推荐使用。即使用也最好是全局复用一下。有`close` 方法进行关闭
-   

除了常规的线程同步方式，可以使用`thread confinement`  



**同步**  

有多线程，就有同步的问题  

对应线程锁，协程也有锁：

`mutex.lock()` `mutex.unlock()` `mutex.withLock`



使用actor，通过顺序的channel，保证多个coroutine 的操作同步。



协程-local data，threadLocal.asContextElement(value = "launch")  

不管执行线程怎么切，协程上的 value 都是launch

> 注意使用，如果子协程会修改 协程-local，父携程是不会追踪数据变化的。所以父协程如果有使用需要，则在创建时要建立自己的 协程-local  
>
> **ensurePresent**



切换环境，主要是线程， withContext  



#### lazy 

```kotlin
fun main() = runBlocking {
    var lazyJob: Job? = null
    var lazyDef: Deferred<String>? = null

    lazyJob = GlobalScope.launch {
        if (lazyDef == null) {
            lazyDef = xixi()
        }
        launch {
            println("before await")
            println(lazyDef!!.await())
            println(lazyDef!!.await()) //deferred 会存结果，所以可以算是自带懒加载效果
        }
    }

    lazyJob.join()
}

private suspend fun xixi(): Deferred<String> {
    return GlobalScope.async(start = CoroutineStart.LAZY) {
        println("refresh deferred result")
        delay(100)
        "its deferred result"
    }
}
```



#### 种类

 suspendCancellableCoroutine  



#### tips

- `use` 方法，资源释放？  
- 计时： `measureTimeMillis {}`
- 不同的协程构造器对已有的特征值有不同的继承方式：
  - 比如launch 默认继承context    
  - 


