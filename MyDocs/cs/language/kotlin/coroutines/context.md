包括job 和dispatcher，name  

协程的isActive 其实就是调用的job?.isActive



dispatcher 则是管理协程和线程的绑定



```kotlin
// 这里为什么先打印unconfined 后打印runBlocking ，大概是底层实现的缘故吧
// 目前理解成launch 默认类似handler.post ，而launch(Unconfined) 在遇到suspend 之前是直接先执行
fun main() = runBlocking<Unit> {
    launch (){ // context of the parent, main runBlocking coroutine
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
}
```



newSingleThreadContext .use{}



协程-local data，threadLocal.asContextElement(value = "launch")  

不管执行线程怎么切，协程上的 value 都是launch

> 注意使用，如果子协程会修改 协程-local，父携程是不会追踪数据变化的。所以父协程如果有使用需要，则在创建时要建立自己的 协程-local  
>
> **ensurePresent**



切换环境，主要是线程， withContext  

