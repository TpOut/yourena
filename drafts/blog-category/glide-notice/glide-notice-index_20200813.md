



首先看[官方文档](./glide-office/glide-office-index.md)

components



dalvik 的gc 有两种类型，一个GC_CONCURRENT，会阻塞主线程两次，每次5ms ；还有GC_FOR_ALLOC 会阻塞125+ ms ;

所以尽量避免大内存的分配



硬件bitmap ，是将副本传到gpu，减少内存和渲染（常规的上传）时间。



结合下来注意点有：

- 调试的时候，可以用tag，`ConnectivityMonitor` 查看网络连接的日志；或用`Glide` 查看一些常规日志。以及查看溢出：`GlideExecutor.UncaughtThrowableStrategy`；`Engine` 查看缓存的情况和 key 

- 关闭老版本的兼容，减少解析

  ```java
  @GlideModule
  public final class MyAppGlideModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
      return false;
    }
  }
  ```

平时不怎么用的小技巧：

- 在recyclerView 中使用时，view 会被复用，甚至大小会发生变化。像之前我都是动态设置layoutParams 的，但是文档里通过viewTarget 传递一个waitForLayout 值实现。

  ```java
  Glide...
      .into(new DrawableImageViewTarget(holder.imageView, /*waitForLayout=*/ true));
  ```

- 图片加载我们经常放在云服务器上，并且云服务器支持拼接参数进行裁剪。而我们在不同地方用了不同大小的图片的时候，可能会造成实际上是一张图，glide却缓存了两个原始图片

  ```java
  //这时候我们可以重写GlideUrl 来让他们被glide 正确识别
  public class MyGlideUrl extends GlideUrl {
      @Override
      public String getCacheKey() {
          return mUrl.replace(findTokenParam(), "");
      }
  }
  
  Glide.with(this)
       .load(new MyGlideUrl(url))
  ```

  



Q：transfom 和transition 什么区别

提到了 资源解码管道(resource decoding pipeline)，不明白，后续看？

Q：glideApp的操作，不需要单独创建一个`RequestOptions`，为什么呢？

没有说，大概是默认就进行了共享吧？

Q：option 至少看起来比较明确，替换之前的option，那type 的例子，看不懂啊？

Q：带状态的drawable 共享的问题，我们知道Android drawable 是自己会只有一个实例的。但是glide 只要传递一个resource id 就解决了这个问题。

Q：可以加载url ，model ；那么这里的model 什么意思

Q：如果把可选部分理解成option，那么为什么thumbnail 是单独的呢

Q：如果只有一个url，还用thumbnail，那么这里加载的原始资源应该是同一个请求吧？

Q：加载viewTargets 的时候，尺寸大小获取是layoutParams，然后从view dimension 获取。这里的dimension 和layoutParams 的区别是啥？  

Q：recyclerView 复用view 大小变化时，waitForLayout  怎么实现的

Q：为什么一再强调，into时，target 需要清除，而view 不需要手动清除

Q：BitmapPool 复用时，老的图像可能会被新的图像替代，难道bitmap 就是一个单纯的位图？  

Q：4.3.0 开始，可以直接再error 方法里重新发起请求，而之前在requestListener 的失败里时只能用handler 实现，可以看下怎么回事 





