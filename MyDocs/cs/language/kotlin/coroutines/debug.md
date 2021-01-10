

在AS 运行处，edit configuration 添加vm 启动参数：-Dkotlinx.coroutines.debug  

就可以在日志中，使用`Thread.currentThread().name` 打印出coroutine 的id  

```kotlin
// 示例
main @coroutine#2
```

而很多时候id 是动态的，不能确定场景，需要名称：

```kotlin
// 创建时传入context 即可
val v1 = async(CoroutineName("v1coroutine")) {
    delay(500)
    log("Computing v1")
    252
}
```





---

IDEA 的调试插件https://kotlinlang.org/docs/tutorials/coroutines/debug-coroutines-with-idea.html  

启动参数详细：https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-d-e-b-u-g_-p-r-o-p-e-r-t-y_-n-a-m-e.html

