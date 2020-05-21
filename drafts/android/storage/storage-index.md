docs/app-data-and-files  



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





---



[^1]: https://developer.android.google.cn/training/data-storage
[^2]: https://developer.android.google.cn/topic/security/data

