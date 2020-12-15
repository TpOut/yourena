数值128 以内会被复用  

```kotlin
val A1 = 128
al A2 = 128
println(A1 === A2)

val aa = arrayListOf(127,127)
println(aa[0] === aa[1])

val ba = arrayListOf(128,128)
println(ba[0] === ba[1])

-----
true 
true 
false 
```



泛型擦除？

底层的class 文件没有泛型之说  