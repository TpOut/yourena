[channels]() 

[flow]() 

[debug]()  

[select]()  



#### delay

```kotlin
// delay 是对协程所绑定的线程进行延时处理  
GlobalScope.launch {
    delay(1000L)
    println("World!")
}

```







组合使用，

- 顺序调用

- 同时异步调用协程，使用async，然后await 获取结果。可以用LAZY 限制非马上启动，LAZY 限制之后要先start，否则await 启动并获取结果实际上和同步获取 一致。

    > 实际上launch 也可以异步调用，只是没有结果，要自己处理
    >
    > ```kotlin
    > val job = GlobalScope.async{doSomething}
    > doOtherLogic()
    > job.await()
    > // 这种单独执行async 的方式不被推荐，因为doOtherLogic 出错时抛出异常，取消整体操作，但是job 还在后台运行 -- 因为GlobalScope 
    > // 所以不管怎么样，最好是把一个操作的代码，都写在一个scope 中，基于结构化结构，便于取消
    > ```



#### tips

代码块中`try/catch/finally` ，一般性join 后finally 代码块都会立即执行，但是如果是一个需要挂起的操作，需要`withContext(NonCancellable)`  

但是正在进行计算（如循环）的协程需要逻辑控制来停止：

- 一个是逻辑代码判断当前协程是否已被取消（isAlive

    - 一个是库的yield 方法，自动帮助判断是否已被取消 

    

#### 超时

便捷的超时取消实现：

withTimeOut  / withTimeOutOrNull

> 具体是通过Dispatcher 来进行时间跟踪的



#### 同步

除了常规的线程同步方式，可以使用`thread confinement`  

即从库层面把所有的计算进行同步，通过`newSingleThreadContext`  



对应线程锁，协程也有锁：

`mutex.lock()` `mutex.unlock()` `mutex.withLock`



使用actor，通过顺序的channel，保证多个coroutine 的操作同步。



协程-local data，threadLocal.asContextElement(value = "launch")  

不管执行线程怎么切，协程上的 value 都是launch

> 注意使用，如果子协程会修改 协程-local，父携程是不会追踪数据变化的。所以父协程如果有使用需要，则在创建时要建立自己的 协程-local  
>
> **ensurePresent**



切换环境，主要是线程， withContext  