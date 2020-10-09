deffered 方便协程之间的单数据传递

channels 提供了数据的流式传递

ProducerScope 



类似`BlockingQueue`，但是一个挂起协程发送，一个接收就不会阻塞接收线程   



可以一个一个接收，方法有send，receive；

可以阻塞接收，`for( x in channel)` , 记得调用`channel.close`

```kotlin
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        println("before delay")
        delay(1000)
        channel.close()
    }
    for (y in channel) println(y)
    println("Done!")
}
// 发送和接收不是无缝的（毕竟是协程之间调度），打印结果：
1
4
9
16
before delay
25
Done!
```



这种发送接收的形式就是常规的生产者消费者，   

对于生产协程，有一个便捷封装produce  

对于消费协程，有一个便捷封装consumeEach（需要是produce 返回的ReceiveChannel）  

> ```
> for(y in channel) 
> consumeEach
> 区别在于后者，如果有一个处理协程被取消，那么全部都会被取消  
> ```



生产协程，可以别另一个生产协程再处理，这种方式叫做管道（pipelines）  



iterator 也是一种管道的使用，对应produce。 yield 对应 send， next 对应receive。 Iterator 对应 ReceiveChannel  



一个生产者可以被多个消费者消费（各自蚕食，不会叠加） 

同样的一个消费者可以接收多个生产者  

> channel 的发送和接收都是 公正的，先入先出



可以使用`ticker` 实现一个延时发送的需求（在上一个被接收后或者固定时长）  







