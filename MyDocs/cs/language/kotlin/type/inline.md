





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

