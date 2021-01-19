```kotlin
 ==  // 操作符调用对象的equals 进行比较
 ===  // 比较对象是否是同一个
 a == null // 会自动被转换成 a === null

name = firstName ?: "Unkonwn" 
//Elvis运算符，firstName为空则返回后者
name = firstName ?: return //可以直接返回
```

```kotlin
//**bitwise** ：位操作，在kotlin 中是infix 方法
```

```kotlin
..
in !in
downto, until, step

// 上面主要用于for 循环
// 而for 循环支持，实现以下操作方法的事物（成员函数，或者拓展）
operator iterator()：Type{operator next(); operator hasNext()}   
```

