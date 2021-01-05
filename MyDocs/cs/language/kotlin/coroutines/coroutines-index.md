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
> 挂起只是相对于初始继续代码块的运行环境而言。即第二部分的代码块如果挂起，也只是脱离了第一部分代码块的执行线程。 且一般情况下，脱离后的第二部分的代码块会马上被分发器`Dispatcher` 分配到某一条线程，恢复`resume`执行。  

> 注意这里脱离后恢复的线程和脱离之前的线程可以是一样的    



在kotlin 中，协程是一个框架包 -- coroutine，也是所有相关内容的总集。  

它和所有对象一样，存在主要的两个特征：

- 运行的生命周期范围 `Scope`

- 运行的上下文 `Context` 

    - context 由多个元素`Element` 组成   

        可以用启动项`CoroutineStart` 配置一些参数

        - element 包括 

            - 代码块任务`Job`  

            - 协程运行所归属的线程调度器`Dispatchers.*`   

                默认Default，jvm 的共享线程池，2 ~ cpu 核数

            - 拦截器`Interceptor`  

                可以在恢复执行时，对其包装一层操作

            - 异常捕获器`ExceptionHandler`

        > `ContinuationInterceptor` 
        >
        >`CoroutineExceptionHandler`



从使用角度上讲，协程的核心在于Job，其余的一切都是为之服务。  

#### 基本使用

从特征值创建协程。  

Scope 基本类是`CoroutineScope`，通过它可以创建协程。其他所有的方式都是基于此。  

这个类里预置了一些库方法或类：

- `MainScope`  

- `GlobalScope`   
- `coroutineScope`   

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

- `launch` 同步
- `async` 异步

```kotlin
// 启动示例
scope.launch{
    
}
```

构造器创建的协程，默认情况下会自动**启动 ** 

启动后的协程，我们需要进行管理，则引用启动时返回的实例：

```kotlin
val job = scope.launch{
    
}
```

Job 的基本类是`Job`, 有方法：

- `cancel`  

- `join` 等待job 代码块执行完毕(包括 finalization -- finally)

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

CompletableJob 线程安全  

[更多用法]()

#### 结构化

讲完基本使用，要来讲一下其结构的设计。  

为了防止创建后忘记关闭（不管我们有没有持有引用，协程启动后在scope 范围内就存在），默认以结构化的形式关联，基本体系如下：

  

协程运行时，可以直接调用构造器新开协程，用的就是所在协程的scope(看代码就知道，代码块的this 就是scope)。而新开的协程会作为原先协程的子协程。即它们之间会产生关联结构（父子）。默认情况下context 也会直接传递  

> 实际上应该是通过job.children/parent 来实现关联结构的
>
> 即使scope 范围相同，两个scope 实例依旧是不同的，会造成额外的消耗 -- 如多次GlobalScope.



关联结构之间的互动有以下规则：

- 如果job 取消，则协程取消。
- 协程的完成，需要等所有子协程完成  

- 协程的取消会导致子协程取消；
- job 出现异常（非Cancellation异常），协程也异常（异步协程不会）。异常会取消job，还会导致父协程取消。以此类推到最顶层协程



- 合并规则3，4。只要有一个子协程出现异常，整个协程链都会被取消

- 针对规则4，异常传递可以止于使用`SupervisorJob` 的协程，不会再往父协程传递



#### 异常



 

#### 代码验证

[coding]()















invokeOnCompletion  

completionHandler  



- 可以指定参数`CoroutineStart` 实现懒加载，在`job.join` , `job.start` 之后才会真正执行

- 启动时机，是否可取消，在协程第一处可挂起之前运行在当前线程  

    

`runBlocking` 创建一个绑定在当前线程的协程，会先执行协程内容；一般用于测试

`supervisorScope` 



- `await`  
- `awaitAll`



> 既然取消的设计产生的finally ，那么finally 里就不该做耗时操作；
>
> 但是的确有需求，可以使用withContext(NonCancellable)



launch 处理了UncaughtException（`CoroutineExceptionHandler`），其他的如async 则需要在返回的结果中获取异常  



[协程的结构限制](./coroutines-struct.md)  

但是不建议使用launch 和async 启动协程，而一般自己建立一个CoroutineScope

可以使用produce，创建channel





---

设计指南：https://github.com/Kotlin/KEEP/blob/master/proposals/coroutines.md

ui 编程：https://github.com/kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md  

api 文档：https://kotlin.github.io/kotlinx.coroutines/

 coroutines额外阅读:  
https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker  

https://medium.com/androiddevelopers/coroutines-on-android-part-i-getting-the-background-3e0e54d20bb