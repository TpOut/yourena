请求之前会进行缓存判断：

- 是否有活动资源，已经在某个view 中展示

- 内存缓存，资源是否还在内存中

- 资源缓存，是否有解码后的，转化过的图像被写入过缓存

- 数据缓存，是否有原始图像数据的缓存

  

前两者直接返回，后两者异步返回 

最后才会正常的拉取原始数据



缓存的key：

- 自定义model 需要实现hashCode 和equals  
- 可选的`Signature`  

上面是关键的信息，但是除了原始数据缓存，其他缓存会被分的更细：

- 宽高
- 是否有转化
- 添加的选项
- 请求的数据类型

甚至资源缓存还有一些 bitmap 配置项、解码时间等  



然后所有的这些信息合到一块，hash 一下，作为文件名



硬盘缓存配置策略：默认AUTOMATIC 对远端图片缓存原始数据，对本地图片缓存修改后的图

可以用diskCacheStrategy 作为单次请求的磁盘缓存设置方法

```java
Glide...
  .diskCacheStrategy(DiskCacheStrategy.ALL)
    
Glide...
  .onlyRetrieveFromCache(true) //只从缓存获取，没有则失败
    
    .skipMemoryCache(true) //跳过内存缓存
    
Glide...
    .diskCacheStrategy(DiskCacheStrategy.NONE)  //跳过磁盘缓存
    
Glide.get(applicationContext).clearDiskCache(); //清除磁盘缓存，需在子线程

```

可以设置不使用、或者不写入磁盘缓存；

可以仅缓存未修改的原始数据；仅缓存缩略图；或者两者都缓存  



如果要强制刷新缓存，使用`invalidation`  

由于缓存key 的特殊性，不好删除，所以提供了signature ，用于额外信息的合成   

- 媒体商店内容：可以使用`MediaStoreSignature` ，包含修改时间，mimel 类型，图像的方向
- 文件：`ObjectKey` 文件修改时间
- urls：`ObjectKey` 特定的metadata，比如版本信息

```java
Glide...
    .signature(new ObjectKey(yourVersionMetadata))
    //
    .signature(new MediaStoreSignature(mimeType, dateModified, orientation))
```

同样支持自定义，实现`Key` 接口即可  



内存缓存和BitmapPool 都是使用ComponentCallbacks2  

```java
//动态调整Glide 的内存使用策略
Glide.get(context).setMemoryCategory(MemoryCategory.LOW); //HIGH,NORMAL

//完全清楚内存缓存和BitmapPool,容易jank，主线程
Glide.get(context).clearMemory();
```



监听

```java
Glide.with(fragment)
   .load(url)
   .listener(new RequestListener() {
       @Override
       boolean onLoadFailed(@Nullable GlideException e, Object model,
           Target<R> target, boolean isFirstResource) {
         // Log the GlideException here (locally or with a remote logging framework):
         Log.e(TAG, "Load failed", e);

         // You can also log the individual causes:
         for (Throwable t : e.getRootCauses()) {
             //  if (t instanceof HttpException) {
           Log.e(TAG, "Caused by", t);
         }
         // Or, to log all root causes locally, you can use the built in helper method:
         e.logRootCauses(TAG);

         return false; // Allow calling onLoadFailed on the Target.
       }

       @Override
       boolean onResourceReady(R resource, Object model, Target<R> target,
           DataSource dataSource, boolean isFirstResource) {
         // Log successes here or use DataSource to keep track of cache hits and misses.

         return false; // Allow calling onResourceReady on the Target.
       }
    })
    .into(imageView);
```









