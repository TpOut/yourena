Handler 通过绑定线程的MessageQueue 发送和处理消息（ Message/Runnable ）对象。

主要作用：定时器；切换线程



当app 进程创建时，主线程会运行一个MessageQueue 来处理 应用顶级对象（activity，broadcast receivers 等等）。

不管什么线程，只要调用handler 的send/post 方法，就可以将其放入创建handler 的线程处理。



源码顺序：

```java
FIND_POTENTIAL_LEAKS //变量，用来检测泄漏
Callback // 用来替代子类继承的方式(参看 dispatchMessage)
dispatchMessage //收到事件后进行分发，-> msg.runnable ?.-> mCallback ?.-> handleMessage() 
Handler() //构造函数，默认当前线程Looper，可以指定mCallback，指定是否asynchronous 
```

> 构造函数中Looper 初始化

```java
obtainMessage //全局消息池，会自动设置Message.target = this，其他配置同Message 直接实例化
  
post //将Runnable 封装到Message 调用SendMessage
postAtFrontOfQueue //...sendMessageAtFrontOfQueue
removeCallbacks //runnable，操作 MessageQueue
  hasCallbacks
  
runWithScissors //等待另一个handler 的任务执行完毕，可以用来做handler 启动阻塞
```

> runWithScissors 中使用了[BlockingRunnable](./blocking-runnable.md) ,只是使用wait/notifyAll 进行等待

```java
sendMessage
sendMessageAtFrontOfQueue //在下次Iteration 之前加入
hasMessages
removeMessages  //操作 MessageQueue
  
enqueueMessage //post -> sendMessage -> enqueueMessage, 操作 MessageQueue
```

> enqueueMessage 中会同步构造函数中的async 给所有Message

```java
getLooper
getIMessenger //MessengerImpl extends IMessenger.Stub, 持有handler 实例，且用Binder.getCallingUid赋值sendingUid
```

>  IMessenger 在[Messenger](./messenger.md) 中调用



**final** MessageQueue mQueue**;** **final** Looper mLooper**;** **final** Callback mCallback**;** **final** **boolean** mAsynchronous**;** IMessenger mMessenger**;**

