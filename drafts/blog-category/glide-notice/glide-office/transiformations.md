对资源进行转换(mutate)  ，一般有对bitmap 的裁剪，过滤

内置的有：

- centerCrop

  默认imageView 是`CENTER_CROP` 时自动设置

- fitCenter

  默认imageView 是`FIT_CENTER` 或`CNETER_INSIDE`时自动设置

- circleCrop

> 取消默认可以强制设置具体的或者 dontTransform

```java
Glide...
    .fitCenter()
    
// 也支持RequestOptions 包装
```

多层转换，使用`transform`  ,也是按编写的顺序依次转换

```java
Glide...
  .transform(new MultiTransformation(new FitCenter(), new YourCustomTransformation()))
  .into(imageView);
//api 升级，可以不传MultiTransformation 对象了
Glide...
  .transform(new FitCenter(), new YourCustomTransformation())
  .into(imageView);
```



自定义：

- BitmapTransformation

  主要重写转化过程`transform`， 和equals、hashCode 以及硬盘缓存相关key的部分



transformation 是无状态的，所以也建议复用



  自定义资源，4.0 可以做到自定义，所以你没法让api 判断drawable 是bitmapDrawable 还是gifDrawable，以致transformation 没法很好的调用。这里的Glide 使用一个map 处理，合起来就是把bitmap transformations 应用到不管是bitmapDrawable 还是gifDrawable 还是Bitmap 资源中。当然这个map 里需要你自己去注册相应的option 和transform  



不管是多层还是单个，都是互斥的，以最后一个为准  



