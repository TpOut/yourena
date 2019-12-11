属性代理？？用反射实现的，好处是增加开发效率

```kotlin
private val viewModel: LoginViewModel by viewModels()
```



变量在类中叫做属性

val 变量没有setter

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



字段

类中不能直接定义字段，

但是如果一个属性需要 **支持字段**(backing field)，kotlin 会自动生成，默认名字叫做field。

> 这里的需要是指 使用默认的accessor（getter/setter）
>
> 或者自定义的accessor 中使用了这个field

这个field字段只能在accesor中被使用



**支持属性**

如果支持字段还是不能满足需求，那么可以考虑使用支持属性

```kotlin
private val _users = mutableListOf<String>() //接口定义可以读写
val users: List<String> //接口定义不可写
    get() = _users;  
```

如此可以做到调用者无法修改属性