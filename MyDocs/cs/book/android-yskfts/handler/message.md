能够在大多数场景下不需要处理回收，那什么情况下需要呢？

```java
Message.obtain(); //Message 是以队列的形式索引，此方法类似pop，取顶端的Message 复用并从队列移除
//有很多重载方法修改  复用message 的参数。

recycle(); //进行队列的保存，以期复用

Handler.obtainMessage();
copyFrom() //部分拷贝
```



同步屏障，主要用来ui绘制，保证invalidate 之前的操作被延迟执行， 保证在nextFrame 到来的时候执行，保证resume 之前执行。

而异步消息，则针对需要及时响应的部分，一般如中断、输入事件



Messenger 发送时会指定sendingUid

