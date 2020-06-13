

```java
mMessages
mIdleHandlers //消息发完回调
mPtr
```

MessageQueue#enqueueSyncBarrier(long)
异步消息不遵从顺序，也不是同步壁（sync barriers）的阻拦对象 



```java
主要是next 方法

```

enqueueSyncBarrier 

会获取一个消息并设置他的Arg1 为token

然后如果当前有消息已经过时，那么插入到该消息后面；否则就放到队列顶部







