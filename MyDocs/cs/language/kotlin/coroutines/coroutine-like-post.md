首先了解下直接代码   

```kotlin
coroutineScope{
    launch{}
    println()
}

launch(Dispatchers.Unconfined){
    println()
    launch{}
}
// 上述两个例子的println 都会在当前线程，且是直接执行
```

可以理解成，生成的代码中，如果有和协程不相关的部分，会被当做直接执行的代码块   



launch 里的内容，即coroutine 的运行内容会被添加到“疑似队列”中，在直接执行代码之后运行。和Android 中的handler.post 倒是很类似   

再看下面两个示例：  

```kotlin
fun main() = runBlocking<Unit> {
    launch (){
        println("launch") //后执行
    }
    launch(Dispatchers.Unconfined) {
        println("launch unconfined") // 先执行
    }
}
```

```kotlin
runBlocking{
    coroutineScope{
        launch{ //先创建
            println("launch") //后执行  
        }
        println("scope") //先执行  
    }
}
```

