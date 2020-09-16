coroutine 可以从scope 创建

> 为什么不是从主要对象job 创建，因为内部拆分job 的细节，让开发者去处理就不友好了

- `coroutineScope{}` 创建一个scope，并使用创建它的coroutine 传递的context，但是会用参数中的代码块创建一个新的job 来替代原先context 的job
- `GlobalScope.launch` 是创建一个全局的范围（方便统一管理
- 可以指定参数`CoroutineStart` 实现懒加载，在`job.join` , `job.start` 之后才会真正执行

