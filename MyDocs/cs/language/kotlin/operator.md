```kotlin
 ==  //操作符调用对象的equals 进行比较
 ===  //比较对象是否是同一个

name = firstName ?: "Unkonwn" 
//Elvis运算符，firstName为空则返回后者
name = firstName ?: return //可以直接返回
```

```kotlin
//位操作，只对于Int, Long 可用
shl(bits), shr(bits), ushr(bits)
and(bits), or(bits), xor(bits)
inv()

//
val x = (1 shl 2) and 0x000FF000
```

