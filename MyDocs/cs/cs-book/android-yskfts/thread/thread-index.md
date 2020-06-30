AsyncTask 封装了线程和Handler，方便处理线程操作和ui 更新

- 需要在主线程初始化，默认串行执行，执行一次

HandlerThread 是将Handler 的创建直接封装好，线程消息循环

- 手动退出哦

IntentService 则是基于 HandlerThread，任务结束时停止服务，相比于没有四大组件时的线程，更低概率被杀死

- stopSelf(int) 保证消息处理完



[线程池](./thread-pool.md)

