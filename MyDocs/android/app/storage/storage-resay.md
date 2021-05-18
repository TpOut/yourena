谈到Android 存储：  

- 介质：
  - 内部存储
  - 外部存储 - 可插拔，且可能多个
  - 云存储 - 虚拟文件
  
- 内容：

  按作用分：

  - 常规 (持久)
  - 缓存：内部缓存建议1MB 左右（这个是不是太老了  

  按类型分：

  - 媒体（Media）
  - 文档（Document）
  - 其他

- 操作：
  - 读
  - 写
  - 访问
  
- 形式:

  - 文件：Android 10 私有存储会加密  
  - 键值对
  - 数据库



操作中加入权限系统，限定了app 访问  

> 权限的划分逻辑，  
>
> 起初基于“文件的位置”，非专有目录的文件需要对应的读写权限   
>
> 但是此时有了权限后，也能访问其他应用的非专有外部目录     
>
> android 10 添加了`Scoped Storage`，让app 只能访问自己的专用目录和自己创建的媒体文件  
>
> android 11 将划分逻辑改为基于“使用文件的目的”，非专有目录和`MediaSotre` 的文件需要新增的**管理** 权限  



但是用户的很多操作必然是跨app 的，因此要添加一些途径让app 之间的数据更加方便的访问  

而Android 本身作为一个系统，为了将一些行为系统化，逐步升级了一些约定：

`Uri`  

`File` 、`MediaStore` 、`ContentProvider` `FileProvider`  

`BlobStoreManager`     

- 封装了获取`FileDescriptor` 的api，如`StorageManager.openProxyFileDescriptor()`

- 支持网络形式Uri（虚拟文件）  

- 为了方便开发，约定`DocumentsContract`  



统一UI :

- 老的直接分享，系统弹窗
- 新的快捷分享，`Share Shortcut` ；更有设计的表单 ，`ShareAssets` 
- 存储访问框架，`SAF`(Storage Access Framework)；统一的访问选择器`DocumentProvider` 



后来权限系统升级：

- 图片位置信息
- 批量删改文件



开发边际：

- 磁盘必然有极限，注意判断



其他点：

- apk 本身也是一种数据，所以安装的位置也可以配置，`installLocation`  
- Binary Large Object (BLOB) implemented as a 64KB  

- 分享给其他app 时，不要用Uri.fromFile，用FileProvider.getUriForFile ,前者有权限问题    

- 使用MediaStore 时，查询路径用`DATA` 列；更新用`DISPLAY_NAME` / `RELATIVE_PATh`  

  