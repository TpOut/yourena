

### data

```kotlin
data class User{var firstName: Sring?};
```

自动生成

- getter, setter

- equals(), hashCode(), toString()

  > 当然如果父类 已经显示定义或者声明为final 那就不需要了  

- copy(), componentN()



标准库有Pair 和Triple 两个data 类  



为了保证生成代码的准确性：

需求主构造函数起码有一个参数，且参数直接声明（val/var）

不能为abstract, open, sealed, inner



### Sealed

可以称为枚举（enum）的拓展。枚举的每个类型只有一个实例，而sealed 可以每个类有多个实例来保存状态。

sealed 类的子类必须和其定义在同一个文件，但是再次继承没这个限制

sealed 本身是抽象的，可以拥有抽象成员

不能拥有非Private 构造（默认就是Private）



#### 使用场景：

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

