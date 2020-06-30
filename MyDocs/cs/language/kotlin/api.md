声明和赋值

```kotlin
private val users:MutableList<User>? = null
init{
  users = ArrayList()
}

private val users = mutableListOf<User>()
```

遍历集合 map

https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/map.html

```kotlin
val formattedName: List<String>
    get(){
        return users.map{ user ->  //默认叫做it，代表每项user
            ...  //直接返回表达式结果
        }
    }
```

