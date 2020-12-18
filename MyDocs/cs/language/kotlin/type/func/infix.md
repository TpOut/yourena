infix 可以省略调用点和括号。

- 必须是成员或拓展方法  
- 单参，不是变参和不能有默认值  
- 省略调用点时，必须指明receiver  

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
```
