deffered 方便协程之间的单数据传递  

channels 提供了数据流的传递      



类似`BlockingQueue`，但是发送和接收都不会阻塞  

```kotlin
fun main() = runBlocking {
    val channel = Channel<Int>()  //创建
    launch{
        for (x in 1..5) { // 用于证明不阻塞
            delay(100)
            println("x * x")
        }
    }
    launch {
        for (x in 1..5) {
            delay(100)
            println("send")
            channel.send(x * x)  //发送
        }
        delay(1000)
        channel.close() //关闭
    }
    repeat(5) { println(channel.receive()) }  //接收
    // for (y in channel) println(y)  // 上面的简写
    println("Done!") // 会在最后一个receive 之后打印
}
```

发送和接收是在会和的时候才会触发  

比如先send，后receive，就在receive 时；先receive，再send，就在send 时。    

可以设定`capacity` 来调节触发前缓冲的值数量  

```kotlin
fun main() = runBlocking<Unit> {
    val channel = Channel<Int>(4) // 设置4个buffer
    val sender = launch {
        repeat(10) {
            println("Sending $it") 
            channel.send(it) // 打印5个之后，在这里挂起
        }
    }
    delay(1000) // 没有调用receiver
    sender.cancel()
}
```



#### 生产者消费者  

这种发送接收的形式就是常规的**生产者消费者模式**，    

然后改写成方法的话，就是在一个方法里面，执行生产(send )操作，并让这个方法返回channel 实例。  

这样外部就可以进行消费(receive )  



对于生产部分，有一个便捷封装produce  

对于消费部分，有一个便捷封装consumeEach（需要是produce 返回的ReceiveChannel）  

```kotlin
val squares = produceSquares()
squares.consumeEach { println(it) }
println("Done!")
```



#### 管道

生产协程，可以被另一个生产协程再处理，这种方式叫做管道（pipelines）  

```kotlin
fun main() = runBlocking {
    val numbers = produceNumbers() 
    val squares = square(numbers) // 管道传递
    repeat(5) {
        println(squares.receive())
    }
    println("Done!")
    coroutineContext.cancelChildren() // 取消相关的协程
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) 
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}
```

> 使用CoroutineScope 拓展函数可以方便管理，比如取消  

iterator 构造器其实和管道的原理类似，对应produce。 yield 对应 send， next 对应receive。 Iterator 对应 ReceiveChannel  

**循环传递**

```kotlin
// 打印质数，这种实现很笨，只是为了说明可以这么使用
fun main() = runBlocking {
    var cur = numbersFrom(2)
    repeat(10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren() 
}

fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) send(x++)
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers) if (x % prime != 0) send(x)
}
```

#### Fan-Out  

一个生产者channel 可以被多个消费者消费（各自蚕食，不会重复） 

```kotlin
fun main() = runBlocking<Unit> {
    val producer = produceNumbers()
    repeat(5) { launchProcessor(it, producer) }
    delay(950)
    producer.cancel()
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1 
    while (true) {
        send(x++)
        delay(100)
    }
}
// 当多个消费者时，不要用consumeEach  
// 因为这种方式产生多个不同协程，不会受各自异常影响  
// 而在consumeEach 的过程中，异常会导致所有处理结束    
fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }    
}
```

#### Fan-In

一个生产者channel 可以在多个地方生产  

```kotlin
fun main() = runBlocking {
    val channel = Channel<String>()
    launch { sendString(channel, "foo", 200L) }
    launch { sendString(channel, "BAR!", 500L) }
    repeat(6) {
        println(channel.receive())
    }
    coroutineContext.cancelChildren()
}

suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}
```



> channel 的发送和接收都是 公正的，先入先出



#### ticker

可以使用`ticker` 实现一个延时发送的需求（在上一个被接收后或者固定时长）  

有模式参数[TickerMode.FIXED_DELAY](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-ticker-mode/-f-i-x-e-d_-d-e-l-a-y.html) 等

```kotlin
fun main() = runBlocking<Unit> {
    val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0)
    var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("初始值立马生效")

    nextElement = withTimeoutOrNull(50) { tickerChannel.receive() }
    println("不够")

    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
    println("50 + 60 够了")

    delay(150)
    nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("够了")
    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() } 
    println("够了 150 + 60 - 100")

    tickerChannel.cancel()
}
```





