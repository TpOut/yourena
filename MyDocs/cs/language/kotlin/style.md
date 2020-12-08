#### 命名

包名小写开头，一般不写连词，但实在有必要，可以首词小写后续驼峰  

属性如果引用object ，可以大写开头  

属性用作支持（back）时，以下划线开头  

接口Foo，实现类FooImpl，但是构造方法不必要Impl，即`fun foo(): Foo`



**测试** ：

```kotlin
@Test fun `ensure everything works`() { /*...*/ }
     
@Test fun ensureEverythingWorks_onAndroid() { /*...*/ }
```



#### 排序

class 内部排序顺序：

- 属性和初始化代码块  
- 构造函数
- 方法定义
- 伴生对象



总结起来就是按关联性排序，

对于方法排序，按功能而不是按可见性、是否拓展函数等  

对于伴生对象，关联性不高，所以放底部。。

对于重载，写在一块


