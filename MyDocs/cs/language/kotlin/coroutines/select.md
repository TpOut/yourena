select onJoin



select 在多个挂起函数同时等待时，选择第一个活跃的监听条件



监听条件包括：

onReceive/onReceiveOrNull  

onSend  

onAwait  



最后有个高级写法：switch over  

可以对两个不同的监听条件（示例是onReceiveOrNull 和onAwait ）进行处理，选择较快的值并丢弃较慢的值