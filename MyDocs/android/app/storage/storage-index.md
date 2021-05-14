

分享：

android 6.0 直接分享（direct share）  

android 10 快捷分享（share shortcut）  



`getExternalMediaDirs()` 

`MediaScannerConnection`  



共享某一个文件：`FileProvider`  `MediaStore`

共享某些数据：`ContentProvider` (也可以文件，一般性是数据)

查看，可以使用 intent.action 打开系统文件管理  



内部缓存的大小建议在1MB 左右 



 



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



虚拟文件：  指没有直接字节码到文件，如云端文件，以URI 来通用表示  

因为7.0 以前必须传递stream   

可以用  `CATEGORY_OPENABLE` 过滤  

```
https://developer.android.com/about/versions/nougat/android-7.0.html#virtual_files
```



#### 管理所有文件

需要一个权限  

且还是以最小使用为主吧，google play会有一些政策  



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