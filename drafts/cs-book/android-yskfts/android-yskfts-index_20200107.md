[动态加载-- 插件化](./dynamic-load/dynamic-load-index.md)

[性能优化](./optimize/optimize-index.md)

[线程相关](./thread/thread-index.md)

[消息机制](./handler.md)



>ViewRootImpl 的checkThread 是包方法，有多处调用
>
>主要在invalidate 和layout 等，限制View 必须在主线程绘制

> View#invalidate 在下一帧准备就绪后再绘制的逻辑依赖于Message 的sync barrrier属性，同样的一些独立的事件如input 则需要async 



热插拔、插件化：

资源加载：反射获取AssetManager, 用addAssetPath设置资源路径  
生命周期管理：用代理activity的生命周期，通过反射或者接口来实现  
ClassLoader: 



AMS.stopServiceToken

</font>

