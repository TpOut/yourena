





就是内联的意思吧，  

将代码块在调用端，在编译期就生成一份  

[class - inline]()

[funciton - inline]() 



inline property

```kotlin
// 没有支持字段的属性可以使用  
val foo: Foo
    inline get() = Foo()

var bar: Bar
    get() = ...
    inline set(v) { ... }

inline var bar: Bar
    get() = ...
    set(v) { ... }
```

  

如果公开的内联api 方法里，调用了私有方法  

而私有方法发生了变化，此时项目没有整体编译，就可能造成调用端的代码还是老的私有方法  

所以内联api 需要内部调用的方法都是公开的，  

`internal` 的方法则可以使用注解`@PublishedApi`  

