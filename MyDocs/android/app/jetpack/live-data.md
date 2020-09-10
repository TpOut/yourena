可观察的数据存储器  

同时处理了activitiy，fragment，service 的生命周期用于自动释放  



控制生命周期部分：

- 活跃状态才会分发事件；如果不在活跃状态，切换到活跃状态时分发最新事件
- 如果配置项发生更改，会马上分发事件
- 可自定义生命周期发射（包括监听非生命周期相关部分）。



<font color=red>拓展小节，里面的StockManager 没有代码，没看懂拓展的实现</font>



Transform，map/switchMap 区别就是一个格式的封装，后者你可以指定已有的一个LiveData（方便复用），



可用于Repository，Room 数据变化的通知