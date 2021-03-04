





老生常谈，鉴于最近的确是要搞搞性能优化了。  

是时候抓一波基础，看下究竟什么叫做 anr 



现在[官方文档](https://developer.android.google.cn/topic/performance/vitals/anr)里说anr 的触发条件：  

- 有前台activity，在input event 或者广播没有在5 s内响应

- 没有前台activity，广播在一定时间内没有执行完  



然后用anr 搜了一下wanandroid 里的过往文章，  

他们说谷歌文档对anr 产生的原因是这么描述的：

- 5秒内无法对输入事件（按键及触摸）做出响应
- 广播接收器无法在10秒内结束运行

以及提到了Service、ContenProvider 等  



于是产生了问题：

- anr，input event， 广播，5秒，10秒  他们究竟是怎样相爱相杀的？



既然有了问题，就先搞起来：

InputDispatcher  

BroadcastReceiver  





