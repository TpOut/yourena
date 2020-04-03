应用安装的时候，通过PMS 解析并注册四大组件

context.registerBroadcast -> registerReceiverInternal 

(里面通过 packsgeInfo 获取 ReciverDispatcher，或者是LoadedApk.new)



