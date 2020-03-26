**主要作用：**

​    将一个任务切换到某个指定的线程中去

​    （原因在于ui 的线程不安全。

​        使用ViewRootImpl 的CheckThread 来保证主线程调用，invalidate\layout 等。

​        又因为ANR，需要在子线程执行耗时操作，使用Handler做到单线程简单逻辑。）

**主要涉及对象：**

（以使用反推，首先是Handler -> MessageQueue -> Looper -> native-Looper -> native-MessageQueue）

[Handler](./handler.md)

--> 

ThreadLocal 

- 线程生命周期的变量快速获取
- 同时方便外部多处入口统一使用

```java
initialValue
```



主要流程：**

​    handler.send -> messageQueue.enqueueMessage -> looper. -> runnable.run / handler.handleMessage





