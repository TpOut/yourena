协程的取消会产生cancellationException

在设计上属于正常现象，不会真的“异常”  



甚至timeout 时，因为timeoutCancellationException 是继承cancellationException 的，也不会有异常。

除非你自己去捕获。



- 异常都被协程处理成**uncaught** 异常，从子协程一层层往根协程上抛。    

- 而协程异常的捕获优先级，首先`try/catch` , 然后 `CoroutineExceptionHandler`，再者`Thread.uncaughtExceptionHandler`  

- 因为async 和receive 是接收触发性质的，它们的异常在触发时抛出。  

对于`CoroutineExceptionHandler` ，因为上述的原因，只有根协程才会用到。  

> 子协程传递给父协程，所以无效；
>
> 触发式会捕获所有，然后传递给结果，所以无效
>
> runBlocking 设计上总是会被子协程异常取消，所以无效 





android 默认`uncaughtExceptionPreHandler`    

所以使用async 时，要么`try/catch` 要么主动`await` 一下 -- 不然没有日志很蛋疼    



而使用`CoroutineExceptionHandler` 处理时，只有所有的子协程都关闭了之后，才会开始处理异常。  

所以如果同时多个协程有多个异常，除了第一个是常规的exception，其他都在`exception.suppressed` 中 -- 对应jdk 1.7+  



包装：

```kotlin
    val job = GlobalScope.launch(handler) {
        val inner = launch { 
            throw RuntimeException("ddd") // the original exception
        }
        try {
            inner.join()
        } catch (e: RuntimeException) {
            println("Rethrowing CancellationException with original cause ${e}")
            throw e
        }
    }
    job.join()
```

inner.join ，不管捕获什么异常，都是`JobCancellationException`  

asyncJob.join 同上，  

asyncJob.await 则必须捕获对应抛出的源异常   

但是coroutineExceptionHandler 都能获取到对应的源异常





