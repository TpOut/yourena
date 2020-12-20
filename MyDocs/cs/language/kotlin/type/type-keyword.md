**val** 是一个不能重新赋值的变量，即没有setter  
**var** 则可以  

```kotlin
val temp = "ddd" //静态类型，推断是在编译时确定的
//不能对一个普通类型赋值为Null，但是在后面加问号则表示这个类型是nullable类型，即可以设置为null
var temp: String?

//如果类型是 : T?
//在.调用时加 ?，如果temp是null，那么结果也是null
temp?.toUpperCase() 

if(temp != null){
  temp.toUpperCase() //如果已经判断了，那么不需要加
}

temp!!.toUpperCase() //断言非空，如果是空，会抛异常
```

```kotlin
var stringRepresentation: String
    get() = this.toString()
    set(value) {
        setDataFromString(value) //
    }

//作用域或者注解
var ...
    private set
 //or 
    @Inject set
```



`lateinit` : 非空类型需要声明并初始化，此关键字用来延迟初始化，可以使用 .isInitialized 来进行检查。

适用类属性、文件属性、本地变量  

```kotlin
if (foo::bar.isInitialized) {
    println(foo.bar)
}
```



**Unit** 

方法默认返回，  

Unit 是一个只有一个值的类型  



**Nothing** 

有助于编译器判断该方法后续代码不会执行

表达式没有返回的时候，就是

> throw Exception() 返回 Nothing

方法不会返回值的时候，也是  

> TODO() 返回 Nothing

类似的，当我们要抛出异常时，自定义方法可以返回Nothing



默认给变量指定null 值，且不声明类型，初始化为Nothing? 类型  





各种索引都可以用[] 的形式访问



`internal`  