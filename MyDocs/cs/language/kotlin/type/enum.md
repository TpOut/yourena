每个enum 都有name 和 ordinal 属性来标记名称和位置，且以声明顺序实现了comparable

```kotlin
enum class Direction{
    NORTH, SOUTH, WEST, EAST
}
// 初始化
enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
}
// 方法重载
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}
// 传递
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}
// 获取,EnumClass 是一个示例
EnumClass.valueOf(value: String): EnumClass
EnumClass.values(): Array<EnumClass>

// since 1.1, 获取，enumValues<T>() 和 enumValueOf<T>()
enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

printAllValues<RGB>() // prints RED, GREEN, BLUE
```

