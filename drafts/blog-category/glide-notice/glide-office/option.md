- RequestBuilder Options

  大部分选项，是直接设置到`RequestBuilder` 上的，它由`Glide.with()` 构建  

  可用的选项包括：

  - 占位符

  - transformation

    包括`CenterCrop`  

  - 缓存策略

  - 组件专门选项：编码质量，解码bitmap 配置

- `RequestOptions`

  选项可以被打包，操作符是apply，多次应用以后者为准  

  `RequestOptions rp= new RequestOptions().centerCrop(context); Glide...apply(rp)`  

- TransisionOptions

  在你请求的加载已经完成时应用，应该理解成转场？

  `Glide...transition(withCrossFade())`

  同时和你加载完成的类型关联，如bitmap 对应`BitmapTransitionOptions`，否则是`DrawableTransitionOptions`

  - 渐入

  - `cross fade` 

    默认以placeholder 为基本

    或者可以自定义设置

  - 无（直接展示）



这里能感觉，RequestOptions 就是RequestBuilder 构建的，然后Glide又支持传入RequstOptions 就是了  

对一个资源的加载，我们可以理解成一个请求，所以`Request` ，而因为配置项过多，所以用Builder 模式  

对于`RequestBuilder` ，我们可以设置：

- 加载的资源那类型：bitmap, drawable

  Glide.with(context).asDrawable  //因此可以看出RequestBuilder 是个泛型，因为不同类型有不同处理

- 加载的资源来源：url, model

- 加载的资源去处：view

- option

- thumbnail



requestBuilder 本身可以被多处加载复用

```java
for (int i = 0; i < numViews; i++) {
   ImageView view = viewGroup.getChildAt(i);
   String url = urls.get(i);
   requestBuilder.load(url).into(view);
}
```



thumbnail 是一个简化版request ， 是在加载的时候，并发请求 。同时支持本地和远程  

```java
Glide.with(fragment)
  .load(url)
  .thumbnail(
    Glide.with(fragment)
      .load(thumbnailUrl))
  .into(imageView);
```

这种形式比较好理解，但是如果你只有一个url，也可以通过设置大小来处理，

```java
Glide.with(fragment)
  .load(url)
  .thumbnail(
    Glide.with(fragment)
      .load(url)
      .override(size) 
   )
  .into(imageView);

//或者这样比较直观
Glide.with(fragment)
  .load(localUri)
  .thumbnail(/*sizeMultiplier=*/ 0.25f)
  .into(imageView);
```





4.3.0 之后，还有一个便捷操作，就是失败重试

```java
Glide.with(fragment)
  .load(primaryUrl)
  .error(
      Glide.with(fragment)
        .load(fallbackUrl))
  .into(imageView);
```



组件选项，首先理解一下glide 组件，包括`ModelLoaders`, `ResourceDecoders`，`ResourceEncoders`, `Encoders`  

这些组件也都可以设置一些选项

```java
Glide...
  .option(MyCustomModelLoader.TIMEOUT_MS, 1000L)
    
//同样可以组合
RequestOptions options = new RequestOptions().set(MyCustomModelLoader.TIMEOUT_MS, 1000L);

Glide.with(context)
  .load(url)
  .apply(options)
  .into(imageView);
```













