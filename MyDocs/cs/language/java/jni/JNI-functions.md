介绍了所有的JNI 函数，以及展示了JNI 函数表的layout  

https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/functions.html  

内容有

- 接口函数表

- 版本信息

- 类操作

- 异常

- 全局和本地引用

- 弱全局引用

    弱全局引用比其他弱引用（java 的弱引用和软引用）更弱，如果其他弱引用还能获取成功，那么弱全局引用就不会获取到null。  

    *PhantomReferences*

- 对象操作

- 访问对象变量

- 调用实例方法

- 访问静态变量

- 调用静态方法

- 字符串操作

- 数组操作

    jbooleans 是一个特例

    1.3 之后，即使VM 不支持pinning，也可以直接获取数组的直接指针   

- 注册native 方法

- monitor 操作

- NIO 支持

- 反射支持

- JVM 接口

