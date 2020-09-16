coroutine ：协程是一个概念，可以理解成线程管理的抽象，更多理解成动词 -- 程协，即线程协调 

在kotlin 里coroutine 是一个框架包，宏观上是所有相关内容的总集。

具象上是一段代码块的运行环境（对应java 里线程作为代码块的运行环境，协程环境更加[轻量](./why-coroutine-light.md)）。



协程作为环境，其特征包括 scope，context

- scope 理解成运行环境的有效范围，离开运行范围的协程自动就取消了

- context 理解成运行环境的上下文（内容）

  包括 job, `ContinuationInterceptor`

  - job 理解成代码块任务抽象。

    > 为什么说是抽象，因为内部实现时job 会被拆分成好几块。

    而因为概念上协程是代码块的运行环境，所以协程的主要对象还是job 。其他都是用于实现协调的衍生品  

  - `ContinuationInterceptor`