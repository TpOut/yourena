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

