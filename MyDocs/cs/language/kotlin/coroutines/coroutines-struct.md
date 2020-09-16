

默认情况下，父job 需要等待子job 结束才能结束；

子job 执行失败，会导致它的coroutine 的父scope 失败。

而父scope 失败，会将所有的父scope 下的其他子scope 全部取消

如果不想子scope 失败引起父scope 失败，可以使用`supervisorScope`，它创建的特殊job 

