> 代码有点多，看原文吧
>
> 大致的效果有简单备注  

在多个挂起函数同时等待时，选择第一个活跃的监听条件。如select 包裹两个条件，顺序遍历条件，条件1 成立，则条件1 消费；否则使用条件2      

监听条件包括：

- onReceive/onReceiveOrNull  

    > merge 接收  

    ```kotlin
    suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
        // select 对两处 onReceive 进行监听， <Unit> 表示不产生结果  
        select<Unit> {
            fizz.onReceive { value ->  
                println("fizz -> '$value'")
            }
            buzz.onReceive { value ->  
                println("buzz -> '$value'")
            }
        }
    }
    // 如果onReceive 的时候channel 关闭会导致select异常
    // 可用onReceiveOrNull，注意String 为非空，保证和异常情况的区分  
    ```

- onSend  

    > merge 发送  

    ```kotlin
    // 能收就能发  
    fun CoroutineScope.produceNumbers(side: SendChannel<Int>) = produce<Int> {
        for (num in 1..10) { // produce 10 numbers from 1 to 10
            delay(100) // every 100 ms
            select<Unit> {
                onSend(num) {} // Send to the primary channel
                side.onSend(num) {} // or to the side channel     
            }
        }
    }
    ```

- onAwait    

    > 选择最快的，然后可以用业务取消另一个？  

    ```kotlin
    // 也少不了deferred
    val result = select<String> {
        list.withIndex().forEach { (index, deferred) ->
            deferred.onAwait { answer ->
                "Deferred $index produced answer '$answer'"
            }
        }
    }
    ```

- onJoin？

    

注意上述几种select 都是每调用一次执行一次  

#### 混合监听条件

可以对两个不同的监听条件（示例是onReceiveOrNull 和onAwait ）进行处理  

通过逻辑来实现遍历 发送deffered 的channel，并丢弃处理较慢的结果（类似背压？   

  

