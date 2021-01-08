首先两者根本不是一个机制的东西， 进行对比本身就有些坑  

我们只需要知道，runBlocking 不是`suspend`，而`coroutineScope`  是`suspend`  

runBlocking 里面，创建了一个eventLoop  



如上述， 第一个coroutine 执行(launch)，然后要延迟200ms    

就走到下一个coroutine 执行(coroutineScope)，  

然后发起下一个协程(launch)，延迟900ms    

又走到下一段代码，延迟100ms  

此时想走到下一段代码，

但是因为coroutineScope 的特性，后面的代码需要在coroutineScope 完成之后再执行，

所以此时不会走下一段代码，而是等待  

等待100ms 之后，执行代码，等待200ms 之后，执行协程；执行900ms 后，执行协程  

此时coroutineScope 执行完毕，走下一段代码  



而对于launch 和coroutineScope  

launch 的部分会被添加到eventLoop 中，在直接执行代码之后运行，而corouinteScope 包含的部分是直接执行的  



