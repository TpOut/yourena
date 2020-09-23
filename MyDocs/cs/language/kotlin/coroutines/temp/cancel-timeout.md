需要使用 job.cancel 来取消，并且为了保证finalization 任务完成，马上调用 job.join  

> 拓展函数 cancelAndJoin



协程可以取消，并抛出对应取消异常。

代码块中`try/catch/finally` ，一般性join 后finally 代码块都会立即执行，但是如果是一个需要挂起的操作，需要`withContext(NonCancellable)`  

但是正在进行计算（如循环）的协程需要逻辑控制来停止：

- 一个是逻辑代码判断当前协程是否已被取消（isAlive
    - 一个是库的yield 方法，自动帮助判断是否已被取消 

    s



便捷的超时取消实现：

withTimeOut  / withTimeOutOrNull

