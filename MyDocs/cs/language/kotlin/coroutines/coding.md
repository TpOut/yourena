```kotlin
// 这里为什么先打印unconfined 后打印runBlocking ，大概是底层实现的缘故吧
// 目前理解成launch 默认类似handler.post ，而launch(Unconfined) 在遇到suspend 之前是直接先执行
fun main() = runBlocking<Unit> {
    launch (){ // context of the parent, main runBlocking coroutine
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
}
```







