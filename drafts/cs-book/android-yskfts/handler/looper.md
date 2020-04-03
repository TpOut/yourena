```java
class LooperThread extends Thread{
  public Handler mHandler;
  public void run(){
    Looper.prepare(); //使用prepare 是因为需要ThreadLocal 存入本线程Looper
    mHandler = new Handler(){
      ...
    }
    Looper.loop();
  }
}
```

使用ThreadLocal 存储Looper，保存了sMainLooper

是否允许退出

```java
quit();
prepareMainLooper(); //保存了一个静态sMainLooper


```

总体上的作用就是循环分发消息

```java
//Binder.clearCallingIdentity, 最终获取了IPC 调用方的Uid,Pid ; 只是为何要调两次？
loop(); 
//获取queue，然后for 死循环，
//获取queue.next，分发 msg.target.dispatchMessage(msg)
postSyncBarrier //之后的同步消息会被延迟执行， 通过源码查看，就是viewRootImpl.scheduleTraversals 时调用，而schedule在多处调用延迟绘制时调用
removeSyncBarrier //移除消息的延迟， 通过源码搜索，就是unscheduleTraversals 时调用，
```







```java
looper.sThreadLocal
Binder.clearCallingIdentity(); //有点没看懂相关操作啥意思
```

