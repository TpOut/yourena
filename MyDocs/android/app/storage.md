变化安装：https://developer.android.google.cn/about/versions/marshmallow/android-6.0?hl=en#adoptable-storage  



https://developer.android.google.cn/training/data-storage/files/media#provide-hint

https://developer.android.google.cn/training/data-storage/files/external#unique-volume-names



scope 存储

https://developer.android.google.cn/training/data-storage/files/external-scoped

https://developer.android.google.cn/training/data-storage/files/external

https://developer.android.google.cn/training/data-storage/files/media



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

`getExternalFilesDirs`   

`MediaScannerConnection`  



4.4 之后`getExternalStoragePublicDirectory()` 需要`READ_EXTERNAL_STORAGE`  



`DocumentProvider`  



共享某一个文件：`FileProvider`  `MediaStore`

共享某些数据：`ContentProvider` (也可以文件，一般性是数据)

查看，可以使用 intent.action 打开系统文件管理  



内部缓存的大小建议在1MB 左右 



#### 概念[^1]

Android 文件系统类似于`disk-based` 文件系统，系统提供了存储的类型：

> 原文有各自的获取方法，权限相关，其他app 访问控制，是否随app 卸载移除的总结

- app专有存储（app-specific）：

    空间有限制，且可能会被动态移除（外接设备）

- 共享存储：

    `MediaStoreApi` -- 媒体文件，图像音视频

    `文档形式` -- 其他类型文件，如下载文件

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