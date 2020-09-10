流程根据构建和销毁主要有5个状态：

initialized -> created -> started ->resumed

resumed -> started -> created -> destroyed  



注意ON_STOP 事件是在`onSaveInstanceState` 时调用，和fragmentManager 理念一致，在此之后就不应该改变界面的状态了。

> 但是老版本的LifeCycle 并不会在此时更新状态为 started，而是在onStop() 时，两者是有一定的时差的
>
> 所以会有一些问题，比如窗口覆盖时，只会调用saveInstance 导致事件、状态不一致



`LifecycleOwner` `ProcessLifecycleOwner`  

`LifecycleObserver`  指定观察状态

`Lifecycle` `LifecycleRegistry` 注册管理、分发状态



