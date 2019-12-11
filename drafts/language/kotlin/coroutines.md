coroutine 是一个并发设计模式。可以简化Android中异步操作的代码

 这里讲到需要放到子线程的不仅仅是网络请求和数据库操作\(Dispatchers.IO -- disk or network\)， 甚至应该包括json解析，大列表循环（Dispatchers.Default -- CPU）  
但是并没有给出具体的影响时间，只是说可能影响到jank就要。

```kotlin
//使用示例
suspend fun fetchDocs(){
  val result = get(targetUrl)
  show(result)
}

suspend fun get(url: String)
= withContext(Dispatchers.IO){...}
```

主要增加了两个关键字

- suspend ，暂停当前的coroutine，保存所有本地变量
- resume ，从coroutine 中止的地方继续执行

而控制线程的方法是withContext\(Dispatchers...\)，即使运行在主线程（Dispatchers.Main）也需要使用这个方法。它对多次线程调用的创建做了优化，不会每次都创建；同样的对线程切换（IO和Default之间）也有优化。

使用withContext 切换线程之后，调用方无需再去考虑返回值的线程是否和调用时相同。（需要看一发原理）

`suspend` 方法只能被其他`suspend`方法，或者coroutine 构造器（launch 或者启动一个新的coroutine）调用

coroutine自己负责`suspend`，但是resume 是靠Dispatchers

kotlin 使用堆栈帧来管理方法对应的本地变量。

>Using a dispatcher that uses a thread pool like `Dispatchers.IO` or `Dispatchers.Default` does not guarantee that the block executes on the same thread from top to bottom. In some situations, Kotlin coroutines might move execution to another thread after a `suspend`-and-`resume`. This means thread-local variables might not point to the same value for the entire `withContext()` block.



#### 作用域（CoroutineScope）

可以在scope内开始一个新的coroutine和取消一个coroutine，且coroutine在离开scope 的时候会被取消。scope内的coroutine启动的coroutine 在同一个scope之内（但是只能Dispatchers来运行coroutine）

在Android中，可以关联到ViewModel 上。

 coroutine被取消的时候会产生一个CancellationException异常  

#### 开启

有两种方法：

- launch 没有返回值  

    ```kotlin
    fun onDocsNeeded(){
        //KTX extension
        viewModelScope.launch{ //Dispatchers.Main
            fetchDocs() //前面的示例，launch 后才能调用suspend方法
        }
    }
    ```

- async 会返回值。需要在返回前配合await 方法/awaitAll 来结束coroutine

    ```kotlin
    suspend fun fetchTwoDocs() =
        coroutineScope {
            val deferredOne = async { fetchDoc(1) }
            val deferredTwo = async { fetchDoc(2) }
            deferredOne.await()
            deferredTwo.await()
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

    > 对于异常，相应的会把异常重新抛给await。这样就会导致常规方法调用await 开启coroutine的时候无法在日志中获取异常信息



coroutines额外阅读:  
https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker  

https://medium.com/androiddevelopers/coroutines-on-android-part-i-getting-the-background-3e0e54d20bb