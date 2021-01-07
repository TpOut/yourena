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

这里其实比较迷惑，查了一下应该想表达这样的意思：

```kotlin
// runBlocking 
fun main() = runBlocking { // this: CoroutineScope
     launch { 
         delay(200L)
         println("Task from runBlocking")
     }
     ...
     println("over")
 }

//coroutineScope
...
     coroutineScope { // Creates a coroutine scope
         launch {
             delay(500L) 
             println("Task from nested launch")
         }
     
         delay(100L)
         println("Task from coroutine scope")
     }  
...
```

这样在runBlocking 中launch 开启的子协程，会在over 打印之前打印task  ；  

而coroutineScope 中launch 开启的子协程，会在print 打印之后。  

所以表达了runBlocking 是阻塞而coroutineScope 是挂起的  



但是，还有情况需要理解：

```kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { 
        delay(200L)
        println("Task from runBlocking")
    }
    
    println("Coroutine scope is over")
}
```

这种情况下，是先打印



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

