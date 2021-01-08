#### 基本理解

runBlocking 中协程是阻塞的。   

官方示例将其和coroutineScope 对比： 

```kotlin
 fun main() = runBlocking { // this: CoroutineScope
     launch { 
         delay(200L)
         println("Task from runBlocking")
     }
     
     coroutineScope { // Creates a coroutine scope
         launch {
             delay(500L) 
             println("Task from nested launch")
         }
     
         delay(100L)
         println("Task from coroutine scope")
     }
     
     println("Coroutine scope is over")
 }
```

这里其实比较迷惑，盲猜一波应该想表达这样的意思：

```kotlin
// runBlocking 
fun main() = runBlocking { // this: CoroutineScope
     launch { 
         delay(200L)
         println("Task inner runBlocking")
     }
     ...
     println("out")
 }

//coroutineScope
...
     coroutineScope { // Creates a coroutine scope
         launch {
             delay(500L) 
             println("Task inner launch")
         }
     
         delay(100L)
         println("out")
     }  
...
```

这样在runBlocking 中launch 开启的子协程，会在out 打印之前打印task  ；  

而coroutineScope 中launch 开启的子协程，会在out 打印之后。  

所以表达了runBlocking 是阻塞而coroutineScope 是挂起的  



但是，还有情况需要理解，如果去掉coroutineScope：

```kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { 
        delay(200L)
        println("Task from runBlocking")
    }
    
    println("Coroutine scope is over")
}
```

这种情况下，是先打印over，再打印task  



总之官方的示例非常迷，查了一下：

https://stackoverflow.com/questions/53535977/coroutines-runblocking-vs-coroutinescope#  



结论大概是，首先两者根本不是一个机制的东西， 进行对比本身就有些坑    

我们只需要知道，runBlocking 不是`suspend`，而`coroutineScope`  是`suspend`    

`suspend` 本身的特性就是如果在一个协程内，其后的代码会在`suspend` 完成之后执行  

然后runBlocking 里面，创建了一个eventLoop      



然后解释解释一下官方示例的打印流程：

第一个coroutine 执行(launch)，然后内容延迟200ms    

走到下一个coroutine 执行(coroutineScope)    

发起下一个协程(内部 launch)，延迟900ms    

又走到下一段代码，延迟100ms  

此时想走到下一段代码，但是因为coroutineScope 的特性，后面的代码需要在coroutineScope 完成之后再执行，

所以此时不会走下一段代码，而是等待完成    

等待100ms 之后，执行；等待200ms 之后，执行；执行900ms 后，执行  

此时coroutineScope 执行完毕   

走下一段代码   

> 为了说明是因为coroutineScope 导致最后一段代码最后执行  
>
> 可以将200ms 改成 > 900ms 的值，这样200ms 后的内容就会是最后执行的  



#### 用途

可以用于测试

```kotlin
class MyTest {
    @Test
    fun testMySuspendingFunction() = runBlocking<Unit> {
        // here we can use suspending functions using any assertion style that we like
    }
}
```

