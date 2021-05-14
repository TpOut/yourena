谈到Android 存储：  

- 介质：
  - 内部存储
  - 外部存储 - 可插拔，且可能多个
- 内容：
  - 媒体（Media）
  - 文档（Document）
  - 其他
- 操作：
  - 读
  - 写
  - 访问



此时加入权限系统，限定了app 访问  

必然要添加一些途径让app 之间的数据更加方便的访问  



而Android 本身作为一个系统，为了将一些行为系统化，逐步升级了一些约定：

`File` 、`MediaStore` 、`ContentProvider` `FileProvider`  

> ContentProvider

- 封装了获取`FileDescriptor` 的api，入`StorageManager.openProxyFileDescriptor()`

- 支持网络形式Uri（虚拟文件），

- 为了方便开发，约定`DocumentsContract`  



统一UI :

- 老的直接分享，系统弹窗
- 新的快捷分享，`Share Shortcut` ；更有设计的表单 ，`ShareAssets` 
- 存储访问框架，`SAF`(Storage Access Framework)；统一的访问选择器`DocumentProvider` 



后来权限系统升级：

- 图片位置信息
- 批量删改文件



其他点：

apk 本身也是一种数据，所以安装的位置也可以配置，`installLocation`  





而文件部分，常见的就有持久文件，和缓存文件 



细节：

- 分享给其他app 时，不要用Uri.fromFile，用FileProvider.getUriForFile ,前者有权限问题    

- 使用MediaStore 时，查询路径用`DATA` 列；更新用`DISPLAY_NAME` / `RELATIVE_PATh`  

  