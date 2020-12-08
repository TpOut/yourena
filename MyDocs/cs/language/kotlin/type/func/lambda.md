lambda 理解成一个特殊的匿名函数   

```kotlin
// 在kotlin 中是一个抽象函数类
abstract class Lambda... : FunctionBase<R>
```

同时又是一个表达式，将最后一行的结果作为返回值   

所以不能有一个显示的返回（如果有，就截断了本来作为返回值的最后一行的结果）  



但是如果标记位inline , 则允许有显示返回， 因为编译器将其改写了  



在lambda 中返回只能借助标签    




