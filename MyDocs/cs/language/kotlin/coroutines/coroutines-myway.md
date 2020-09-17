coroutine ：协程是一个概念，可以理解成线程管理的抽象，更多理解成动词 -- 程协，即线程协调 

在kotlin 里coroutine 是一个框架包，宏观上是所有相关内容的总集。

具象（微观）上是一段代码块的运行环境（对应java 里线程作为代码块的运行环境，协程环境更加[轻量](./why-coroutine-light.md)）。

所以本文对所有需要宏观理解的地方都叫做程协，具象的地方叫做协程



协程作为环境，其特征包括 scope，context

- scope 理解成运行环境的有效范围，离开运行范围的协程自动就取消了

- context 理解成运行环境的上下文（内容）

  context 由element 构成，包括 job, 所绑定的线程（池）,

  - job 理解成代码块任务抽象。

    > 为什么说是抽象，因为内部实现时job 会被拆分成好几块。

    而因为概念上协程是代码块的运行环境，所以协程的主要对象还是job 。其他都是用于实现协调的衍生品

> 但是在应用部分，
>
> scope 并不会被认为是协程的组成，它只是协程的外部限制
>
> 而context 基本可以认为是协程的内部组成，即协程是context 的容器



context 执行可以做到suspend 和resume，是`Continuation` 的功劳（这个概念还没看懂，理解成“部分代码块”）  

suspend 的意思应该是指将协程从当前线程剥离，只是会马上被dispatcher 进行分配（一般会直接resume 到另一条线程）



context.element：

`CoroutineDispatcher` : 默认Default，jvm 的共享线程池，2 ~ cpu 核数

`ContinuationInterceptor` ：可以在resume 执行`Continuation` 时，对其包装一层操作

`CoroutineExceptionHandler`



context 的配置参数：

`CoroutineStart`：启动时机，是否可取消，在协程第一处可挂起之前运行在当前线程  

