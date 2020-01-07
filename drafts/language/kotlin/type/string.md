```kotlin
for( c in str){ println(c) }
```

```kotlin
//escape type
val s = "hello \n"

//raw type, 不支持backslash escaping
val text = """
    for(c in "foo")
        print(c)
"""

val text= """
    |Tell me and I forget.
    |Teach me and I remember.
""".trimMargin()  //默认使用符号| 表示margin 的开始，可以通过参数指定其他符号

//转义
val price = """
${'$'}9.99
"""
```

String 不管哪种类型，都支持 文本变量

