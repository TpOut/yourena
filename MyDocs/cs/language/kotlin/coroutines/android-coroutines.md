- 

- async 会返回值。需要在返回前配合await 方法/awaitAll 来结束coroutine

    ```kotlin
    suspend fun fetchTwoDocs() =
        coroutineScope {
            val deferredOne = async { fetchDoc(1) }
            val deferredTwo = async { fetchDoc(2) }
            deferredOne.await()
            deferredTwo.await()
        }
    
    suspend fun fetchTwoDocs() = 
        coroutineScope {
            val deferreds = listOf(
                async { fetchDoc(1) }, 
                async { fetchDoc(2) } 
            )
            deferreds.awaitAll()      
        }
    ```

    > 对于异常，相应的会把异常重新抛给await。这样就会导致常规方法调用await 开启coroutine的时候无法在日志中获取异常信息



