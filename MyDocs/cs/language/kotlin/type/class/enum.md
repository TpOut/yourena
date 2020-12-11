声明，每个实例都是object，都有name 和 ordinal 属性来标记名称和位置，且以声明顺序实现了comparable

```kotlin
enum class Direction(val rgb: Int){
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

```

 方法重载

```kotlin
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    }, // 分隔符是逗号

    TALKING {
        override fun signal() = WAITING
    }; // 分隔符是分号

    abstract fun signal(): ProtocolState
}

// 继承接口时，enum 声明可以不写方法，实例里写即可
enum class Temp : IntBinaryOperator {
    f2{
        override fun applyAsInt(left: Int, right: Int): Int {
            return 1
        }
    },
    f3{
        override fun applyAsInt(left: Int, right: Int): Int {
            return 2
        }
    };
}
```

语法糖

```kotlin
// 通过名称获取实例，会抛异常
EnumClass.valueOf(value: String): EnumClass
// 生成列表
EnumClass.values(): Array<EnumClass>
```

对泛型的支持

```kotlin
// since 1.1, 获取，enumValues<T>() 和 enumValueOf<T>()
enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

printAllValues<RGB>() // prints RED, GREEN, BLUE
```

