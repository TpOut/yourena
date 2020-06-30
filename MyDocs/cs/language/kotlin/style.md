文件名要有标识性，采用驼峰式

如果文件内只有一个顶级类，直接以类名为文件名；

否则，以能描述清楚的名字作为文件名



包名扁平式，比如包名`org.example.kotlin` 的类直接放在根目录中；包名拓展`org.example.kotlin.network.socket` 的类放在根目录的`network.socket` 子目录下。

（如果和java 混合使用，那么按java 的结构来）



内容顺序：属性和初始化块，第二构造函数，方法，伴生对象(companion)

内部嵌套类，如果使用了则放在靠近使用的地方，如果是提供给外部使用，则放在最下方



重写的方法都写在一起



私有属性 以下划线开头

```kotlin
class Temp{
    private val _privateProperty
    val pubProperty
}
```



测试名字可以用反单引号括起来，然后包括空格，但是Android runtime还不支持，可以用下划线替代  
单例属性可以使用首字母大写的驼峰形式；枚举用大写或者驼峰都可  
方法名要有可读性，比如sort表示只是排序一个集合，而sorted表示会排序并返回  
缩进4空格,//后面加一个空格再写注释  
冒号：如果左右平级，如两个类型，则都加空格；否则，如类型声明，则左侧无空格  



https://developer.android.com/kotlin/style-guide

https://kotlinlang.org/docs/reference/coding-conventions.html