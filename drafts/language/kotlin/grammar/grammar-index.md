`if`, `when`, `for`, `while` 都是表达式

#### 变量

基类：Any? 试问和unit的区别

#### for循环

可以循环 index : `list.indices`  
支持区间（步长）参数，  
lazy 加载

#### 类方法拓展定义

不需要实例，而是直接使用Class.newFunc\(\){} 定义好就能使用  

#### 特殊方法

with  
多个方法的顺序调用可以简写

also  
实现变量互换

#### 泛型

```text
inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)
```

### 風格


idea可以开启风格检查  
如果和java项目一起，那么遵循java的分包格式；如果只有kotlin,那么省略多余的相同包路径  
一个类的内容排序方案  
测试名字可以用反单引号括起来，然后包括空格，但是Android runtime还不支持，可以用下划线替代  
单例属性可以使用首字母大写的驼峰形式；枚举用大写或者驼峰都可  
方法名要有可读性，比如sort表示只是排序一个集合，而sorted表示会排序并返回  
缩进4空格,//后面加一个空格再写注释  
冒号：如果左右平级，如两个类型，则都加空格；否则，如类型声明，则左侧无空格  
修饰符顺序：[https://kotlinlang.org/docs/reference/coding-conventions.html\#modifiers](https://kotlinlang.org/docs/reference/coding-conventions.html#modifiers)  
不支持8进制数



`lateinit` : 常规的属性需要声明并初始化，此关键字用来延迟初始化



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





该运算符还可以用于更早的return:

```kotlin
fun smFunc(accont: Account?){		
  accont ?: return
  doSomething()
}
```

初始化区域:

```kotlin
class A{
  val index: Int = 4
  //or
  val index: Int  
  init{
    index = 4
  }
}
```

```kotlin
val nullableFoo :Foo? = ...
//只在不为空的时候，传入方法并且执行
nullableFoo?.let { foo ->
   foo.baz()
   foo.zap()
}
```

### 