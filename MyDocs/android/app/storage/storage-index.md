变化安装：https://developer.android.google.cn/about/versions/marshmallow/android-6.0?hl=en#adoptable-storage  



Storage Access Framework (SAF)  优化了一些问题，如创建seekable 文件描述符，    

`DocumentProvider` 支持网络存储，或者使用MTP 协议（Media Transfer Protocol  



findDocumentPath()

`DocumentsContract`
`DocumentsProvider`
`DocumentsContract.Path`



`StorageManager.openProxyFileDescriptor()`
`openProxyFileDescriptor()` 
`ProxyFileDescriptorCallback`



`buildChildDocumentsUriUsingTree()`  `buildDocumentUriUsingTree` `query`    

`createDocument`  `COLUMN_FLAGS`  

`DocumentsProvider` `isChildDocument` `FLAG_SUPPORTS_IS_CHILD`  

`getExternalMediaDirs()` 

`MediaScannerConnection`  



共享某一个文件：`FileProvider`  `MediaStore`

共享某些数据：`ContentProvider` (也可以文件，一般性是数据)

查看，可以使用 intent.action 打开系统文件管理  



内部缓存的大小建议在1MB 左右 



#### 前置知识  

硬件基本：Android 本身自带内部存储和外接存储  

由于外部存储的可插拔，每次访问前都检查一下；甚至有些有多个外部设备，你需要选择主存    

apk 本身也是一种数据，所以安装的位置也可以配置，`installLocation`  

而文件部分，常见的就有持久文件，和缓存文件  



文件系统必然有极限，需要在存储之前，进行查询判断：

`getCacheQuotaBytes()`  `getAllocatableBytes()`  





媒体文件的访问，可以通过`MediaStore`  



对应文件的权限，有**读写**  

权限的划分逻辑，  

起初基于“文件的位置”，非专有目录的文件需要对应的读写权限   

但是此时有了权限后，也能访问其他应用的非专有外部目录     

android 10 添加了`Scoped Storage`，让app 只能访问自己的专用目录和自己创建的媒体文件  

android 11 将划分逻辑改为基于“使用文件的目的”，非专有目录和`MediaSotre` 的文件需要新增的**管理** 权限  



当然你也想在平时不暴露文件（即不放在MediaStore），而只在某些时候暴露 `FileProvider` 



#### 概念[^1]

Android 文件系统类似于其他平台的`disk-based` 文件系统，支持的存储类型有：

- app专有存储（app-specific）：

    会随着卸载移除  

    因为【硬件基本】，所以有两个独立的存储空间；    

- 共享存储：

    媒体文件，图像音视频，对应`MediaStore`  Api

    `文档形式` -- 其他类型文件，如下载文件，对应Storage Access Framework -- 系统文件选择器？

    数据集，多app 通用，对应`BlobStoreManager`

- Preferences：

- 数据库：

对于结构化数据，选择preference 或者数据库，前者键值对，后者对2列以上数据使用。



**安全使用**[^2]



Android10：

无需权限访问：`getExternalFilesDir` , 通过 `MediaStore` 创建的音视频图像  



- internal 存储目录：app私有存储，Android 10 以上路径会被加密

- external 存储目录：虽然其他app 有权限后可以访问，但是建议对这部分文件转移到共享存储  

两者都有对应的持久存储和缓存存储。



---



[^1]: https://developer.android.google.cn/training/data-storage
[^2]: https://developer.android.google.cn/topic/security/data