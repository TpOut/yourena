协程在执行后，都只是异步处理一个值  

而flow 可以异步处理多个结果并返回



listOf 马上装载  

sequence 懒加载循环器  

flow 则是协程环境下的懒加载循环器



flow / flowOf / asFlow ：感觉可以很简单就实现定时器的效果  

> flow 创建的会每次emit 时自动检测取消，
>
> asFlow 这种则不会，可以使用cancellable  



还有很多操作符，去看原文吧

如过程处理 map / transform 等，

过程配置 take / buffer / conflate / onEach 等

终端处理 toList / first / reduce 



启动 collect / safeCollect  

修改运行协程 launchIn



组合 zip / flate

异常捕获 catch  



flow 结束状态 ： 成功，失败，取消  

结束 - 或发生被捕获异常，则成功

未捕获异常，失败

取消

> Q : 取消很奇怪，是依赖scope 取消的，而不能直接获取flow 自身之类的进行取消？
>
> A : launchIn 会返回job，那默认的呢？



flow 的context 是从“终端 - 操作” 的调用协程来获取的

可以在构建时修改context ，但是设计上不允许flow 的代码块中修改 -- flowOn  



