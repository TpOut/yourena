即AAC  

此架构体系认为：

- 分离关注点：[系统组件]是Android 操作系统和应用的粘合类，只应该包含界面和操作系统交互的操作。最好减少对它们的依赖
- 数据驱动界面：最好是持久性数据模型。



基本模型：

activity/fragment 只和ViewModel 层交互，ViewModel 从Repository 获取数据，而数据包括Model（数据库等持久性数据模型）和Remote Source（远端数据源）



基础：LifecycleOwner，android.arch.lifecycle:reactivestreams   



- [activity](./) 、 [fragment]
- [ViewModel]
- [Repository] 
- [Model]、[Source]



每一层都是往下依赖，

但是事件驱动都是希望往上传递事件，用 [LiveData 组件]

然后每一层都会有多个实例，就可能出现层级依赖的复用问题，用[dagger2]



如此架构之后，在测试上有很多便利性：

- activity、fragment 测试：插桩测试 instrumented unit tests 或者espresso，模拟UserProfileViewModel
- ViewModel 测试：junit 模拟UserRepository
- Repository 测试：junit 模拟，具体实现可能不同，示例是 WebService 和UserDao 
    - 网络调用情况
    - 数据保存情况
    - 缓存刷新逻辑
- UserDao：插桩测试
- WebService：也尽量模拟，如okhttp github 库的MockWebServer 

> jetpack 提供了专门的测试工具用于控制后台线程：
>
> androidx.arch.core:core-testing
>
> InstantTaskExecutorRule -- 立即在调用线程执行后台操作； CountingTaskExecutorRule -- 等待jetpack 后台操作；可以和espresso 的空闲资源功能配合



