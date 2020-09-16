coroutine ：协程是一个概念，可以理解成对kotlin 线程管理的一个统称，更多的作为名词 -- 偏向程协，即线程管理器  

（当然里面的粒度是协程代码块，而不是线程。稍微区别是，

线程创建时基本对应一个绑定的代码块，后续切换代码块大部分时候需要自己处理。

而协程创建时也对应一个绑定的代码块，但是

）



环境特征包括 scope，context

- scope 理解成运行环境的有效范围，离开运行范围的coroutine 自动取消

- context 理解成运行环境的上下文（内容）

  包括 job, `ContinuationInterceptor`

  - job 理解成代码块任务抽象，coroutine 的取消即job 取消。

    对应`job.cancel`

  - `ContinuationInterceptor`

  - 

- coroutine 可以从scope 创建

  - `coroutineScope{}` 创建一个scope，并使用创建它的coroutine 传递的context，但是会用参数中的代码块创建一个新的job 来替代原先context 的job
  
    > 由于context 是传递的，所以默认情况下，子job 执行失败，会导致它的coroutine 的父scope 失败。
    >
    > 而父scope 失败，会将所有的父scope 下的其他子scope 全部取消
    >
    > 如果不想子scope 失败引起父scope 失败，可以使用`supervisorScope`，它创建的特殊job 
  
  - `GlobalScope.launch` 是创建一个全局的范围（方便统一管理

- 