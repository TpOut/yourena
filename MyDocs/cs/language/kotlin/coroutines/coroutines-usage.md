coroutine 可以从scope 创建

> 为什么不是从主要对象job 创建，因为内部拆分job 的细节，让开发者去处理就不友好了
>
> 而一个代码块肯定有运行的范围，所以以范围为基准创建协程也可以

Scope 基本类是`CoroutineScope` 

- 使用这个类创建基本范围，一般用提供的快捷方法`coroutineScope`
- `GlobalScope` 是创建一个全局的范围
- 可以指定参数`CoroutineStart` 实现懒加载，在`job.join` , `job.start` 之后才会真正执行

newCoroutineContext



创建方式：

`runBlocking` 创建一个绑定在当前线程的协程，会先执行协程内容；一般用于测试

`coroutineScope` 创建一个scope，并使用创建它的coroutine 传递的context，但是会用参数中的代码块创建一个新的job 来替代原先context 的job

构造协程：launch，通过scope 创建协程并根据设置启动协程

`supervisorScope` 



启动方式（构造器）：

- launch 

  设置包括是否创建后马上执行、

- async

  和普通的协程大致相同，但是job 是一个`Deffered` ，`CompletableDeferred` 



返回job ：

join，当前协程，等待job 所在的协程 执行完毕

await，

awaitAll，



launch 处理了UncaughtException（`CoroutineExceptionHandler`），其他的如async 则需要在返回的结果中获取异常  

