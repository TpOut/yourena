**启动流程：**

context.startService -> **AMS**.startService -> **ActivityServices**.startServiceLocked -> startServiceInnerLocked (这里开始出现的ServiceRecord 会贯穿整个流程)

-> bringUpServiceLocked -> realStartServiceLocked -> **app.thread**.scheduleCreateService （调用onCreate） 

此时即发消息给H, handleCreateService 主要完成：

- 类加载器创建Service
- 创建Application 的对象并调用onCreate
- 创建Context 并attach 关联
- 调用servic.onCreate 并把service 对象存储到ActivityThread 中的一个列表中

创建之后 ActivityThread.handlerServiceArgs （onStartCommand 等)



**绑定流程：**

context.bindService -> bindServiceCommon

(将客户端的ServiceConnection 通过**LoadedApk**.ServiceDispatcher 转化为ServiceDispatcher.InnerConnection , 这样才能支持跨进程操作)

  -> **AMS**.bindService -> **ActivityServices.**bindServiceLocked -> bringUpServiceLocked -> realStartServiceLocked -> **app.thread**.scheduleBindService

此时发送Handler 消息， handleBindService 方法：

- 调取对应的service，回调onBind 方法（客户端无感）
- 调用客户端的ServiceConnection.onServiceConnected（通过AMS 的publishService -> ServiceDispatcher.connnected -> handler）

- 然后handler 调用了ServiceDispatcher.doConnected -> ServiceConnection.onServiceConnected

