分析已有架构的重试策略。

如链路是应答式重发

tcp 根据数据往返时间，动态计算。指数退避  



请求断开的重连逻辑，是直接断开连接并发送新的连接请求，新的连接甚至可以更换链路。  

  

对应tcp 的动态计算，应用层的一次发送和回应 也需要记录。但是分解成多个步骤：

- 请求从调用到实际发出所需要的时间（

- 请求和相应在客户端和服务端之间的耗时（需要配合
- 服务端处理请求需要的时间（



超时按上述大幅度定义之后，发现还是不足预期。

而由于数据包可能会被分割，所以又考虑了首包超时；包包超时。  



而对于动态计算的部分，现在没有很好的测算工具，所以就只是根据发送及回包的速度进行评估。分成优良和评估状态。
