多平台转化



包名和路径名并不需要一样



属性代理？？用反射实现的，好处是增加开发效率

```kotlin
private val viewModel: LoginViewModel by viewModels()
```



区间（Ranges）： 

```kotlin
for (i in 1..100) { ... }  // closed range: includes 100
for (i in 1 until 100) { ... } // half-open range: does not include 100
for (x in 2..10 step 2) { ... }
for (x in 10 downTo 1) { ... }
if (x in 1..10) { ... }
```



接口`interface` 可以包含方法实现，但是里面的属性没有 field（所以不能访问）



可见性：`private`, `protected`, `public`, `internal`

**对于顶级而言**

默认public，private 表示文件范围，internal 表示module 范围（module 的定义是一起打包的文件，如idea的 module， maven 的project，https://kotlinlang.org/docs/reference/visibility-modifiers.html#modules），

protected 不能在顶级范围使用

**对于类构造而言**

类似于顶级的情况

**对于类而言**

private 表示仅类内部可见，protected 表示类和子类可见，

internal 则表示外部能访问类的情况下，且在module 范围下才行

public 则表示外部能访问类的情况下，均可

**本地变量**

没有可见性



懒加载属性：

```kotlin
val p: String by lazy {
    // compute the string
}
```



Try-with-resources

```kotlin
val stream = Files.newInputStream(Paths.get("/some/file.txt"))
stream.buffered().reader().use { reader ->
    println(reader.readText())
}
```



```kotlin
//无参的TODO() 会抛异常
fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")
```



lable，以`@` 标记

```kotlin
loop@ for(){
  ...
  break@loop
}

return@a 1 //表示在标签a 处返回1
```

使用场景，等同于continue 效果

```kotlin
fun foo(){
    listOf(1,2,3,4,5).forEach lit@{
        if(it == 3) return@lit
        print(it)
    }
    print("end")
}
//如果没有标签，return 会直接退出foo 方法。但是用标签，可以只退出内部的循环区块
//而实际上，内部的区块是一种lambda，它隐式使用了方法名作为标签
fun foo(){
    listOf(1,2,3,4,5).forEach{
        if(it == 3) return@forEach
        print(it)
    }
    print("end")
}

//还有一种写法，类似于break
fun foo() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // non-local return from the lambda passed to run
            print(it)
        }
    }
    print(" done with nested loop")
}
```



#### 泛型

```text
inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)
```



SAM（Single Abstract Method）可以用表达式自动转化

```kotlin
//此处的OnClickListener.OnClick 就是SAM
//再加上匿名函数就是下面的写法
loginButton.setOnClickListener {
    val authSuccessful: Boolean = viewModel.authenticate(
            usernameEditText.text.toString(),
            passwordEditText.text.toString()
    )
    if (authSuccessful) {
        // Navigate to next screen
    } else {
        statusTextView.text = requireContext().getString(R.string.auth_failed)
    }
}
```

###



#### 语言官网：

 https://kotlinlang.org/



[https://www.jetbrains.com/help/education/learner-start-guide.html?section=Kotlin Koans](https://www.jetbrains.com/help/education/learner-start-guide.html?section=Kotlin%20Koans)  
[https://www.jetbrains.com/help/idea/converting-a-java-file-to-kotlin-file.html](https://www.jetbrains.com/help/idea/converting-a-java-file-to-kotlin-file.html)

blog : [https://blog.jetbrains.com/kotlin/](https://blog.jetbrains.com/kotlin/)  
forum slack: [https://kotlinlang.slack.com/](https://kotlinlang.slack.com/)

在线编辑:

https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLjYwIiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiIsImpzQ29kZSI6IiIsIm5vbmVNYXJrZXJzIjp0cnVlLCJ0aGVtZSI6ImlkZWEiLCJjb2RlIjoiLyoqXG4gKiBZb3UgY2FuIGVkaXQsIHJ1biwgYW5kIHNoYXJlIHRoaXMgY29kZS4gXG4gKiBwbGF5LmtvdGxpbmxhbmcub3JnIFxuICovXG5cbmZ1biBtYWluKCkge1xuIFxuICAgIHZhciBkID0gMjtcbiAgICBwcmludGxuKFwiSGVsbG8sIHdvcmxkICRkISEhXCIpXG59In0=



例子学习： [https://play.kotlinlang.org/byExample/overview](https://play.kotlinlang.org/byExample/overview)

修正案例： [https://play.kotlinlang.org/koans/overview](https://play.kotlinlang.org/koans/overview)



#### android 部分:

（完毕）https://developer.android.com/kotlin

(正在，建议先学完基本语法)https://codelabs.developers.google.com/codelabs/kotlin-coroutines/#0

https://developer.android.com/courses/kotlin-bootcamp/overview

https://developer.android.com/kotlin/overview

##### ktx

https://developer.android.com/kotlin/ktx

##### 资源

https://developer.android.com/kotlin/resources

https://www.jetbrains.com/help/education/

https://cn.udacity.com/course/kotlin-bootcamp-for-programmers--ud9011

##### 已知issue

 https://developer.android.com/studio/preview/kotlin-issues.html

