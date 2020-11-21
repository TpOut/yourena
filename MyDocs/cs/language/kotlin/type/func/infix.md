infix 可以省略调用点和括号。但是必须是成员或拓展方法，不是变参和没有默认值的单参

```kotlin
//如位元操作符的实现， 只对于Int, Long 可用
shl(bits), shr(bits), ushr(bits)
and(bits), or(bits), xor(bits)
inv()
```

```kotlin

val x = (1 shl 2) and 0x000FF000

infix fun Int.shl(x: Int): Int { ... }

1 shl 2
1.shl(2)

//也有一点不方便
class MyStringCollection {
    infix fun add(s: String) { /*...*/ }
    
    fun build() {
        this add "abc"   // Correct
        add("abc")       // Correct
        //add "abc"        // Incorrect: the receiver must be specified
    }
}
```
