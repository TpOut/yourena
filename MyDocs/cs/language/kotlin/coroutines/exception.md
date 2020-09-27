协程的取消会产生cancellationException

在设计上属于正常现象，不会真的“异常”



甚至timeout 时，因为timeoutCancellationException 是继承cancellationException 的，也不会有异常。

除非你自己去捕获。