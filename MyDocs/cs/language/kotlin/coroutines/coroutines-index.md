#### 基本理解

先达成一个名词的统一解释：  

- coroutine ：协程是一个抽象概念，个人理解成可协调程序，更多理解成"程协"，即程序协调  



相对于已有的进程，线程，协程就是一个新晋的“程”。它们的共同特点是，都是代码块运行的环境。     

> Q：为什么说协程更轻量？
>
> A：轻量是相对于线程而言的，此时如果要对协程和线程进行比较。那么要把他们都作为代码块的运行环境进行理解：
>
> 线程，一般作为代码块的运行环境。而一段代码块在执行之后无法切换所附属的线程
>
> 而协程作为运行环境，代码块在“执行过程中是可以拆分”的，一部分在初始线程执行完，第二部分切换到另一个线程， 第三部分又能切换到最初的线程执行。  
>
> 那么对于线程这种死板玩意来说，代码块在协程中切来切去就显得轻量了。     

> 而协程能做到代码块拆分，是可继续代码块`Continuation` 的功劳    
>
> 在编译时，标记为挂起`suspend` 的代码块会被拆分，封装成多个可继续代码块。
>
> 也正是因为需要标记，所以`suspend` 等相关的“被标记”代码，只能在协程中调用。          
>
> 挂起只是相对于初始继续代码块的运行环境而言。即第二部分的代码块如果挂起，也只是脱离了第一部分代码块的执行线程。脱离后的第二部分的代码块会根据策略被分发器`Dispatcher` 分配到某一条线程，恢复`resume`执行。  

> 注意这里脱离后恢复的线程和脱离之前的线程可以是一样的    



在kotlin 中，协程是一个框架包 -- coroutine，也是所有相关内容的总集。  

它和所有对象一样，存在主要的两个特征：

- 运行的生命周期范围 `Scope`

- 运行的上下文 `Context` 

    - context 由多个元素`Element` 组成   

        - element 包括 

            - 代码块任务`Job`  

            - 协程运行所归属的线程调度器`Dispatchers.*`   

                默认Default，jvm 的共享线程池，2 ~ cpu 核数

            - 拦截器`Interceptor`  

                可以在恢复执行时，对其包装一层操作

            - 异常捕获器`ExceptionHandler`

        > `ContinuationInterceptor` 
        >
        > `CoroutineExceptionHandler`



从使用角度上讲，协程的核心在于Job，其余的一切都是为之服务。  

#### 基本使用

从特征值创建协程。  

Scope 基本类是`CoroutineScope`，通过它可以创建协程。其他所有的方式都是基于此。  

这个类里预置了一些库方法或类：

- `MainScope`  

- `GlobalScope`   

可以看到，在创建的时候，其内部组合了另一个特征值context。

它的基本类是`CoroutineContext`,  有实现类`CoroutineContextImpl` ，有一些方法或类：

- `EmptyCoroutineContext` 

- `newCoroutineContext` kotlinx.coroutines

- `createDefaultDispatcher`  即调度器，基本类`CoroutineDispatcher`

```kotlin
// 创建示例
val scope = MainScope()
```

创建了特征值之后，就要可以创建协程了，对应构造器：

- `launch` 
- `async` 
- `coroutineScope`   

> launch 和asyc 的区别是后者结果的异步返回，而不是线程的同步异步
>
> [launch 行为](launch-like-post)

```kotlin
// 启动示例
scope.launch{
    
}
```

然后我们就可以在协程里运行`suspend` 函数了

管理协程需要引用启动时返回的实例：

```kotlin
val job = scope.launch{
    
}
```

Job 的基本类是`Job`, 

有状态值：



有方法：

- `start`  

- `cancel`    

- `join` 等待job 代码块执行完毕(包括 finalization -- finally)

    ```kotlin
    val job = scope.launch{
        // doSomeThing
    }
    job.join()    
    ```

子类`Deffered` 有方法：

- `await` 等待job 代码块执行完毕

    ```kotlin
    suspend fun fetchDoc() =
        coroutineScope {
            val deferredOne = async { fetchDoc(1) }
            deferredOne.await()
        }
    
    suspend fun fetchTwoDocs() = 
        coroutineScope {
            val deferreds = listOf(
                async { fetchDoc(1) }, 
                async { fetchDoc(2) } 
            )
            deferreds.awaitAll()      
        }
    ```

构造器创建的协程，默认情况下会自动**启动 ** 

如果要修改特征值context，或者启动项`CoroutineStart`  都可以在这里配置。  

启动项包括：启动时机，是否可取消，在协程第一处可挂起之前运行在当前线程    

```kotlin
scope.launch(Dispatchers.IO,CoroutineStart.LAZY){
    
}
// 此时调用 `job.join` , `job.start` 之后才会真正执行
```

CompletableJob 线程安全  

[更多用法](use-scene)

#### 结构化

讲完基本使用，要来讲一下其结构的设计。  

为了防止创建后忘记关闭（不管我们有没有持有引用，协程启动后在scope 范围内就存在），默认以结构化的形式关联，基本体系如下：



我们拥有特征值的实例之后，可以用特征值实例作为接收器，来构造新协程。  

此时新协程的特征值默认使用接收器的特征值，  

且新协程的特征值与特征值实例会产生关联结构（父子）。     

> 会看到`AbstractCoroutine` 有 parentContext
>
> `ScopeCoroutine` 有parent 也是取自此   

```kotlin
// 示例
fun main() = runBlocking {
    GlobalScope.launch{} // 不会关联
    launch {} // 会关联
}
```



关联结构之间的互动有以下规则：

- 如果job 取消，则协程取消。
- 协程的完成，需要等所有子协程完成  

- 协程的取消会导致子协程取消；
- job 出现异常（非Cancellation异常），协程也异常（异步协程不会）。异常会取消job，还会导致父协程取消。以此类推到最顶层协程



- 合并规则3，4。只要有一个子协程出现异常，整个协程链都会被取消

- 针对规则4，异常传递可以止于使用`SupervisorJob` 的协程，不会再往父协程传递



#### 异常



#### 调试

基于上述的一些基本情况，我们差不多可以开始实践了  

此时最关键的还是在于[调试工具]()  



#### 测试



#### 代码验证

[coding]()



#### tips

1、即使scope 范围相同，两个scope 实例依旧是不同的，会造成额外的消耗 -- 如多次GlobalScope.

? 怎么看到GlobalScope 现在是object 了





invokeOnCompletion  

completionHandler    

  

`supervisorScope` 



- `await`  
- `awaitAll`



launch 处理了UncaughtException（`CoroutineExceptionHandler`），其他的如async 则需要在返回的结果中获取异常  



但是不建议使用launch 和async 启动协程，而一般自己建立一个CoroutineScope

可以使用produce，创建channel  





---

设计指南：https://github.com/Kotlin/KEEP/blob/master/proposals/coroutines.md

ui 编程：https://github.com/kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md  

api 文档：https://kotlin.github.io/kotlinx.coroutines/

 coroutines额外阅读:  
https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker  

https://medium.com/androiddevelopers/coroutines-on-android-part-i-getting-the-background-3e0e54d20bb