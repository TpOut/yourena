（需要看完之后再看一遍）https://developer.android.com/kotlin/interop

#### kotlin调用java

java中需要注意的  
1、不能有kotlin的 hard keywords，不然需要倒引号来转义

 2、除非必须，否则禁止使用Any的拓展方法或者拓展属性的名字，因为同名的成员或者域名会覆盖Any的。

3、java公用方法的参数、返回等一定要添加nullability注解，否则会被解释成`!`

> `!`语法在kotli 中没有意义。如String! 既兼容String 也兼容 String?

4、SAM参数最好放在最后，可以简略书写  
5、getter,setter标准写成：getPro,setPro,isPro  
6、方法命名不要和操作符重载方法的名称相同

#### java调用kotlin

1、当一个文件包含一个顶级\(top-level\)方法或属性，使用`@file:JvmName("Foo")`来定义一个好名字,否则会被解释成MyClassKt；`@file:JvmMultifileClass`合并多个  
2、这部分需要看完kotlin语言再说。。。  
https://developer.android.com/kotlin/interop\#kotlin\_for\_java\_consumption



#### 迁移kotlin  

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



try-with-resource  

```kotlin
val stream = Files.newInputStream(Paths.get("/some/file.txt"))
stream.buffered().reader().use { reader ->
    println(reader.readText())
}
```

