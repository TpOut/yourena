类型声明时基于pascal 的语法

#### 基本类型

都是类，不同类型之间的转化需要显示调用，如`intVal.toByte()`，但是计算的时候，可能会进行隐式推断。

**整形** 都是有符号，任何没指定类型的整形，如果没超出范围，默认都是Int

> 无符号数1.3 之后支持，但是还不稳定

Byte, Short, Int, Long（8. 16. 32. 64位）

```kotlin
val b: Byte = 1 //Byte
2L //Long
```



**浮点型**

Float, Double (32. 64位)

```kotlin
12.5e10 //double
3.14 //double，default
3.14f //float
```



**进制**，不支持八进制

```kotlin
0x0F //16进制
0b00001011 //2进制
```



#### char

```kotlin
\t, \b, \n, \r, \', \", \\, \$ //支持的字符
\uFF00 //除了上述字符，都用unicode
```



各种索引都可以用[] 的形式访问



**Unit** 



**Any**

`NaN` 和自己比较才相等，否则大于任何其他数，包括`POSITIVE_INFINITY` 

`-0.0` 比 `0.0` 要小