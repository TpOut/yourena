```kotlin
Array(5){ i -> (i*i).toString() }
arrayOf(1,2,3)
arrayOfNulls()

// 各种类型的array 
ByteArray, ShortArray, ...
var arr: IntArray = intArrayOf(1, 2, 3)
arr[0] = arr[1] + arr[2]

arr.indices
arr.withIndex()

//在变参处看到，应该是array 的方法？
val a = arrayOf(1, 2, 3)
val list = asList(-1, 0, *a, 4)
```

