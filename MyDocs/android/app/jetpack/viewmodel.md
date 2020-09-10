[SavedState 模块]  



ViewModel 生命周期，在设计时即比view 或者LifecycleOwners 更长。  

可以包含LifecycleObservers，但是不应该监听生命周期相关的对象。  



AndroidViewModel  



viewModelProvider 绑定了LifecycleOwner.Lifecycle  

