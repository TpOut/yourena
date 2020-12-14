

#### 抽象类

`abstract` , 默认open，可以将一个非抽象方法，改写成抽象方法



#### 接口

[interface]()



### 单例

也使用 `object` 定义，是线程安全的

不能声明为Local（即不能在方法中定义）， 但是可以内嵌在非内部类中（包括其他object 类）

```kotlin
//单例模式，使用object 关键字
object Repo{
  ... //其他都不变
}
//调用直接用类名
val users = Repo.users
```



#### object 小节

object 语句在使用的时候就被执行

object 声明在第一次被访问的时候初始化

companion object 在附生的类被加载的时候初始化



### data

```kotlin
data class User(var firstName: Sring?){
    var lastName: String 
};
```

自动生成以下方法，如有重写冲突看原文细节

- getter, setter
- equals(), hashCode(), toString()  

- copy(), componentN()

需要注意的是，firstName 会被当作这些方法的处理对象，而lastName 不会

> 相当于User 是通过firstName 来判断是否相等  



标准库有Pair 和Triple 两个data 类  



为了保证生成代码的准确性：

需求主构造函数起码有一个参数，且参数直接声明（val/var）

不能为abstract, open, sealed, inner



#### Enum

[enum]()



### Sealed

可以称为枚举（enum）的拓展。枚举的每个类型只有一个实例，而sealed 可以每个类有多个实例来保存状态。

sealed 类的子类必须和其定义在同一个文件，但是再次继承没这个限制

sealed 本身是抽象的，可以拥有抽象成员

不能拥有非Private 构造（默认就是Private）

**使用场景主要就在when ：**

```kotlin
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

//when 语句
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN //此时不需要使用else，即可覆盖其他所有情况
}
```

