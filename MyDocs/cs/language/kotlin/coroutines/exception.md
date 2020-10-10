协程的取消会产生cancellationException

在设计上属于正常现象，不会真的“异常”



甚至timeout 时，因为timeoutCancellationException 是继承cancellationException 的，也不会有异常。

除非你自己去捕获。



而协程异常的捕获，首先`try/catch` , 然后 `CoroutineExceptionHandler`，再者`Thread.uncaughtHandler`

一般性只有顶级launch 协程才会用到  

> 子协程传递给父协程，所以无效；异步会传递给结果，所以无效
>
> runBlocking 设计上总是会被子协程异常取消，所以无效 





