[style]()

[type](./type/type-index.md)

[control-flow]()

[property](./property/property-index.md)

[function](./func/function-index.md)

[collections](./collections.md)

[expression](./expression.md)

[extend](./extend.md)

[operator](./operator.md)

[api](./api.md)

[keyword](./keyword.md)



[performance](./performance.md)

[interactive-with-java](./interactive-with-java.md)

[android-ktx](./ktx.md)

closable 拓展了一个use 函数，方便自动关闭



语法糖



可见性：  

```kotlin
private protected public 
internal  

// 顶级作用域时
public // 默认，全局
protected // 不支持
private // 表示file 范围
internal // 表示module 范围

// 类和接口时  
public // 如果类可见，则方法可见
protected // 当前类和子类
private // 当前类
internal // 如果类模块内可见，则模块内可见

// kotlin 中，外部类不能访问内部类的私有成员  
// 重写的属性，默认是protected  
```

> 模块的定义 https://kotlinlang.org/docs/reference/visibility-modifiers.html#modules



fun theAnswer() = 42



with(myTurtle) { 

}



属性代理  用反射实现的，好处是增加开发效率  

```kotlin
this is a fragment
```

---

grammar  

