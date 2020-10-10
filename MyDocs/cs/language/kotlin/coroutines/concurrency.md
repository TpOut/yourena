除了常规的线程同步方式，可以使用`thread confinement`  

即从库层面把所有的计算进行同步，通过`newSingleThreadContext`  



对应线程锁，协程也有锁：

`mutex.lock()` `mutex.unlock()` `mutex.withLock`



使用actor，通过顺序的channel，保证多个coroutine 的操作同步。