完整语法：

```kotlin
// 类似方法类型的定义，但是右值必须有大括号
{ x: Int, y: Int -> x + y }

//一般用法
val sum: (Int, Int) -> Int = ...
```

```kotlin
// 在kotlin 中是一个抽象函数类
abstract class Lambda... : FunctionBase<R>
```



lambda又是一个表达式     

如果要在lambda 中提前返回，需要借助标签  

```kotlin
// 注意这里的filter 标签是库本身添加的  
list.filter {
    val shouldFilter = it > 0 
    return@filter shouldFilter
}
```

> 为什么不能直接return shouldFilter 呢  
>
> lambda 定义是一个表达式，表达式本身不应该添加scope  
>
> 即常规情况下，return 应该返回到最近的scope 对应的结束处  

> 为什么lambda 要定义成一个表达式呢
>
> ???



常规的return 是在最近的函数中进行返回，称作local-return    

而lambda，因为特殊性，其内部的return 不会将其当做”最近的函数“，而是在lambda 外层的函数返回，所以叫做non-local return  



#### 语法糖

```kotlin
// 方法实参，可以用lambda 简写
foo({input, extra - > input.length})
// 如果匿名方法是方法中的最后一个参数，可以把括号写在外面　　
foo(){ input, extra ->
    input.length
}
// 如果只有一个参数，可以去掉方法的小括号  
foo { input, extra ->
     input.length
}  
// 如果参数不使用，可以默认写成_
foo { input, _ ->
     input.length
} 
// 如果方法参数只有一个参数，可以不写参数部分, 默认为it  
foo {
    it.length
}
```





