程协 实际上就是对 scope, 协程的管理

scope 可以内嵌scope，它们之间会产生关联结构（父子）。默认情况下context 也会直接传递  

协程从scope 创建之后，调用者可以拿到job 

> 虽然实际上是通过job.children/parent 来实现关联结构的

job 可以取消（抛出CancellationException，属于正常的异常）。如果job 取消，则协程取消。

协程的取消会导致当前scope 的子scope 的协程取消；

job 出现异常（非Cancellation异常），协程也异常（异步协程不会）。异常会取消job，还会导致scope 的父scope 的协程取消，父scope 协程取消的原因就是此异常。如果父scope 的协程使用SupervisorJob，则不会导致父scope 取消逻辑。

协程的完成，需要等所有子scope 的协程完成  

父scope 的可以取消子scope 下的协程



协程的context 来自scope  







默认情况下，父job 需要等待子job 结束才能结束；

子job 执行失败，会导致它的coroutine 的父scope 失败。

而父scope 失败，会将所有的父scope 下的其他子scope 全部取消

如果不想子scope 失败引起父scope 失败，可以使用`supervisorScope`，它创建的特殊job 

