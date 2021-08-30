AppGlideModule  

LibraryGlideModule  

实现都是对应的处理registerComponents 的部分



这里module 的概念是啥？



application 选项。

Glide 使用LruResourceCache，MemoryCache 接口的默认实现  。而LruResourceCache 的大小通过 MemorySizeCalculator 类实现，后者会侦测设备的内存。

app 可以自定义MemorySize， 

```java
@GlideModule
public class YourAppGlideModule extends AppGlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
    MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
        .setMemoryCacheScreens(2)
        .setBitmapPoolScreens(3)
        .build();
      // 也可以不配置calculator, 直接设置大小；甚至自己实现一个`MemoryCache`
    builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
      builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));
       builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));
      builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));
      builder.setDiskCache(
        new InternalCacheDiskCacheFactory(context, "cacheFolderName", diskCacheSizeBytes));
  }
}
```



Bitmap pool  

使用LruBitmapPool 作为默认的BitmapPool ,是一个自适应大小的，默认大小基于屏幕尺寸和density  ，会根据`isLowRamDevice` 进行变化



DiskCache  

DiskLruCacheWrapper，默认实现DiskCache  ，默认硬盘缓存大小是250M，存储在缓存目录的一个文件夹，当然也是可以配置的

可以自己实现对应的DiskCache.Factory 来生产自定义DiskCache，甚至连文件夹是否存在之类都是用的子线程    



全局Option ,会覆盖本地的设置 

```java
@GlideModule
public class YourAppGlideModule extends AppGlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
    builder.setDefaultRequestOptions(
        new RequestOptions()
          .format(DecodeFormat.RGB_565)
          .disallowHardwareBitmaps());
  }
}
```

只针对某一个Activity 或者Fragment  

RequestsManager 是每个Activity/Frgament 自有的。可以用apply 设置专享的配置  

```java
Glide.with(fragment)
  .applyDefaultRequestOptions(
      new RequestOptions()
          .format(DecodeFormat.RGB_565)
          .disallowHardwareBitmaps());
```

注意RequestManager 也有一个set 方法，用于替换全局的，但是慎用  



4.2.0 开始，可以配置异常策略，比如默认是打日志给Logcat，但是可以改为磁盘  

```java
@GlideModule
public class YourAppGlideModule extends AppGlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
    final UncaughtThrowableStrategy myUncaughtThrowableStrategy = new ...
    builder.setDiskCacheExecutor(newDiskCacheExecutor(myUncaughtThrowableStrategy));
    builder.setResizeExecutor(newSourceExecutor(myUncaughtThrowableStrategy));
  }

```



日志配置：

```java
@GlideModule
public class YourAppGlideModule extends AppGlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
    builder.setLogLevel(Log.DEBUG);
  }
}
```



components:

- modelLoader ：加载自定义model （Urls，Uris，POJOs） 和data（InputStream，FileDescriptors）
- resourceDecoder：解析资源（Drawables，Bitmaps），或者其他类型（InputStreams，FileDescriptors）  
- Encoder：将数据（InputStreams，FileDescriptors）  写入磁盘缓存
- resourceTranscoder：不同资源的转化，入BitmapResource 与DrawableResource
- resourceEncoder：对资源写入磁盘缓存的（BitmapResource，DrawableResource）  

这里奇怪，怎么encoder 就分开来写，



然后就可以在全局里注册了

上述的几种组件都可以有多个实现  



组件路径包括：

model -> data (ModelLoader)

---》 DiskCache

data -> resource (ResourceDecoder，)

---》 ResourceEncoder -> resourceDiskCache  

可选：resource -> Transcode Resource（ResourceTranscoder）  



registry 的prepend, append, replace 用于控制ModelLoader 和ResourceDecoder 的顺序，prepend 表示优先加载...

所以有一个注意点，如果你继承了一种类型，可以被默认的组件接收（多态），一定要把对应的自定义组件添加到前面

append 就比较常规  

replace 的话会替换所有同一类型的组件，比如能处理某一modle 的所有ModelLoader；一般用于替换网络客户端，比较唯一  



AppGlideModule 和LibraryGlideModule 的区别  

都可以注册组件，但是App 可以定义全局的设置，如缓存实现和大小  



合并策略

```java
@Excludes(com.example.unwanted.GlideModule.class)
```





