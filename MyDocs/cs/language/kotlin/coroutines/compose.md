组合使用，

- 顺序调用

- 同时异步调用协程，使用async，然后await 获取结果。可以用LAZY 限制非马上启动，LAZY 限制之后要先start，否则await 启动并获取结果实际上和同步获取 一致。

    > 实际上launch 也可以异步调用，只是没有结果，要自己处理
    >
    > ```kotlin
    > val job = GlobalScope.async{doSomething}
    > doOtherLogic()
    > job.await()
    > // 这种单独执行async 的方式不被推荐，因为doOtherLogic 出错时抛出异常，取消整体操作，但是job 还在后台运行 -- 因为GlobalScope 
    > // 所以不管怎么样，最好是把一个操作的代码，都写在一个scope 中，基于结构化结构，便于取消
    > ```



