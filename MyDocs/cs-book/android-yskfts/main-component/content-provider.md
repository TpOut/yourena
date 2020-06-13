在进程启动时，会同时被启动，并发布到AMS 中

contentProvider 的onCreate 会先于 Application 的onCreate 执行



应用启动 -> ActivityThread.main -> 创建ActivityThread 并创建主线程消息队列

-> ActivityThread.attach --> AMS.attachApplication  -> 提供ApplicationThread

-> ApplicationThread.bindApplication -> ActivityThread.H.handleBindApplication  -> 创建Application 对象，加载ContentProvider -> Application.onCreate



