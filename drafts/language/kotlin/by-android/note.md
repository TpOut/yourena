# note

val 是一个不能重新赋值的变量  
var 则可以 kotlin的类型推断是在编译时确定的  
不能对一个普通类型赋值为Null，但是在后面加问号则表示这个类型是nullable类型，即可以设置为null

```text
var temp: String?
```

大括号,会返回最后的语句执行结果，可以直接赋值

```text
val answerString: String = if(condition){
  "first line"
  "the condition is right !"
}else{
  "the condition is not right !"  
}
```

但是这样不够清晰，可以用when语句代替：

```text
val answerString = when{
  count == 42 --> "i have the answer"
  count > 35 --> "the answer is close"  
  else -> "the answer eludes me"
}
```

方法可以直接被语句赋值：

```text
fun generateAnswerString(countThreshold: Int): String = if (count > countThreshold) {
        "I have the answer"
    } else {
        "The answer eludes me"
    }
```

匿名方法：

```text
val stringLengthFunc: (String) -> Int = { input ->
    input.length
}
```

高阶函数\(higher order functions\)，允许方法参数:

```text
//如果匿名方法是方法中的最后一个参数，可以把括号写在外面　　
stringMapper("Android") { input ->
    input.length
}
```

类属性的默认访问权限是public

```text
class Car{

}
val car = Car() //类的构造
class Car1(val wheels: List<Wheel>){
  var temp: Int = 15
    private set  //set访问权限控制    
}
val car = Car(...)
```

迁移kotlin：  
1、兼容： 只需要创建一个新的kotlin文件,会有提示点击配置即可。  
２、转化: 选中java文件 &gt; 工具栏 &gt; code &gt; convert .. 转化只是从字节码层面等同，但是实际的结果代码还需自己优化\(比如有些变量虽然没有声明时初始化，但是使用的时候肯定是有値的情况。kotlin会在声明时设置为nullable,而每次使用时解包装\(unwrap\)\)

```text
//kotlin 路径配置
android {
   sourceSets {
       main.java.srcDirs += 'src/main/kotlin'
   }
}
```

在kotlin中，声明变量时必须初始化，否则使用lateinit关键字,比如最常见的view初始化

SAM可以缩写：

```text
loginButton.setOnClickListener {
  //just handle logic
}
```

java的静态，用companon object{}关键字代替static:

```text
class ProtocolAct : BlindBaseAct() {

    companion object {
        fun launch(context: Context) {

        }
        private const val TAG = "ff";//这里为什么和方法不一样，加一个const ?
    }
}
```

属性委托\(delegation\):[https://developer.android.com/kotlin/common-patterns\#delegate](https://developer.android.com/kotlin/common-patterns#delegate)  
这一段没用过，待后续再看

java本身是没有是否为空的严格类型的，所以kotlin调用java的类型时，可能出现无法判断是普通类型还是可空类型。!是一个适用此情况的平台类型，如String!,同时表示String和String?  
当然如果加了@Nonnull注解，会被解析成String；@Nullable会被解析成String?

```text
val account = Account("name")  //Account是java bean,　其中name　变量没有注解
val accountName = account.name!!.trim() // !!是非空断言,这样会让name作为非空String解析，可能异常
val safeAccountName = account.name?.trim()  //会自动判断name是否为空，不会有异常
```

对于null，有更为快速的Elvis运算符:

```text
val name = account.name?.trim() ?: "default"
```

该运算符还可以用于更早的return:

```text
fun smFunc(accont: Account?){
  accont ?: return
  doSomething()
}
```

初始化区域:

```text
class A{
  val index: Int = 4
  //or
  val index: Int  
  init{
    index = 4
  }
}
```

```text
val nullableFoo :Foo? = ...
//只在不为空的时候，传入方法并且执行
nullableFoo?.let { foo ->
   foo.baz()
   foo.zap()
}
```

### 协调器

coroutine 是一个并发设计模式。。 这里讲到需要放到子线程的不仅仅是网络请求和数据库操作\(Dispatchers.IO -- disk or network\)， 甚至应该包括json解析，大列表循环（Dispatchers.Default -- CPU）  
但是并没有给出具体的影响时间，只是说可能影响到jank就要。

主要是suspend 关键字,他不是回调，而是真正的阻塞协调器，在具体操作结束的时候再resume（这里需要看一发原理），这样一个明显好处是，不需要考虑回调方法所在的线程是否和调用方不一致。  
而控制线程的方法是withContext\(Dispatchers...\), 它则对多次线程调用的创建做了优化，不会每次都创建；同样的对线程切换也有优化。（简单理解成一个线程池就是了。。）

#### 制定协调器作用域（CoroutineScope）

主要用来控制coroutine的开始和取消，但是Dispatchers启动coroutine coroutine被取消的时候会产生一个CancellationException异常  
如果一个协调器启动另一个协调器，那么他们有相同的作用域。  
\(viewModelScope\)

#### 启动

launch 不会返回值  
async 会返回值，配合await/awaitAll\(async会在某个时间调用await,相应的会把异常传递给await,这样就有可能无法在日志中获取异常信息\)

如果在调用async的方法返回前不调用await方法，那么coroutine会在操作执行完之后才停止

架构组建库,scope地址：  
[https://developer.android.com/topic/libraries/architecture/coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)

### kotlin和java交互指南

#### kotlin调用java

java中需要注意的  
1、不能有kotlin的 hard keywords，不然需要倒引号来转义 2、除非必须，否则禁止使用Any的拓展方法或者拓展属性的名字，因为同名的成员或者域名会覆盖Any的。 3、公用部分的参数、返回等一定要添加nullability注解，否则会被解释成!  
4、SAM参数最好放在最后，可以简略书写  
5、getter,setter标准写成：getPro,setPro,isPro  
6、方法命名不要和操作符重载方法的名称相同

#### java调用kotlin

1、当一个文件包含一个顶级\(top-level\)方法或属性，使用`@file:JvmName("Foo")`来定义一个好名字,否则会被解释成MyClassKt；`@file:JvmMultifileClass`合并多个  
2、这部分需要看完kotlin语言再说。。。  
[https://developer.android.com/kotlin/interop\#kotlin\_for\_java\_consumption](https://developer.android.com/kotlin/interop#kotlin_for_java_consumption)

### 风格指南

