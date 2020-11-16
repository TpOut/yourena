`if`, `when`, `for`, `while` 都是表达式  

try/catch 在这里都是表达式 



大括号返回 **最后的语句执行结果**



可以直接赋值，替代了三元运算符

```kotlin
val answerString: String = if(condition){
  "first line"
  "the condition is right !"
}else{
  "the condition is not right !"  
}
```

但是这样不够清晰，可以用when语句替代写法：

```kotlin
val answerString = when{
  count == 42 --> "i have the answer"
  count > 35 --> "the answer is close"  
  else -> "the answer eludes me"
}
//只是缩写版，而不会判断多个分支
```

when 的条件可以合并，甚至可以是表达式

```kotlin
when(x){
    0,1 -> print("x == 0 or x == 1")
    else -> print("otherwise")
}

when(x){
    parsInt(x) -> print(...)
    else -> print(...)
}
```

```kotlin
//1.3, 捕获
fun Request.getBody() =
        when (val response = executeRequest()) {
            is Success -> response.body
            is HttpError -> throw HttpException(response.status)
        }
```



try/catch 在这里都是表达式

```kotlin
 val result = try {
        count()
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
```

