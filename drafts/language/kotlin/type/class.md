类属性的默认访问权限是public

```kotlin
//默认构造函数
class Car
{
    val wheels = listOf<Wheel>()
}
val car = Car() //类的构造

//带参数的构造函数
class Car
(val wheels: List<Wheel>){
    private val tb : Boolean = false;
    var temp: Int = 15
        private set  //setter 访问权限控制 
}
val car = Car(...) 

//非默认构造函数，不能省略constructor关键字
class Car 
private constructor(){}
constructor(val wheels: List<Wheel>){}

//data 关键字帮助class 自动生成getter, setter
//equals(), hashCode(), toString()
data class User{var firstName: Sring?};
```

初始化代码

```kotlin
class Repo private constructor{
  private val users: MutableList<User>? = null;
  //默认构造函数不能包含代码，需要使用init
  //但是上面的声明时, 是可以写初始化代码的
  init{
    val user1 = User(...)
    users = ArrayList()
    users!!.add(user1)
  }
}
```

属于类的对象

```kotlin
//属于类，而不是属于“类对象”
companion object
```

单例

```kotlin
//单例模式，使用object 关键字
object Repo{
  ... //其他都不变
}
//调用直接用类名
val users = Repo.users
```

解构

```kotlin
//则可以直接使用属性
for((firstName, lastName) int users!!)
```

继承

`override` `super`

```kotlin
class Lsj: BaseLsj()
```

