tansition 的场景主要包括  

- placehodler 到新加载的图片  

- thumbnail 到全尺寸的图片  



它只在每次请求的时候生效，不能跨请求。后者可以考虑使用ViewSwitcher 实现  

而如果是从内存缓存获取数据，那就没必要执行过场  



 [`BitmapTransitionOptions`](http://bumptech.github.io/glide/javadocs/400/com/bumptech/glide/load/resource/bitmap/BitmapTransitionOptions.html) or [`DrawableTransitionOptions`](http://bumptech.github.io/glide/javadocs/400/com/bumptech/glide/load/resource/drawable/DrawableTransitionOptions.html). For types other than `Bitmaps` and `Drawables` [`GenericTransitionOptions`](http://bumptech.github.io/glide/javadocs/400/com/bumptech/glide/GenericTransitionOptions.html) 



动画，特别是alpha 值相关的，消耗甚巨。

所以列表之类的地方使用时，最好考虑使用预加载，这样早点缓存到内存中，会减少动画  



cross fade 通过`TransitionDrawable` 支持，有两种模式，`setCrossFadeEnabled`，如果disable 则从顶部飘入；enable，则透明渐变不透明，原先的图是不透明变透明  

前者可能会在加载后还展示背景图，后者则可能让人看起来闪一下白屏

```java
DrawableCrossFadeFactory factory =
        new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

GlideApp.with(context)
        .load(url)
        .transition(withCrossFade(factory))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.color.placeholder)
        .into(imageView);
```

  

  自定义转场需要实现 `TransitionFactory` ，TransitionFactory  

然后用 DrawableTransitionOptions 加载  

参考 DrawableCrossFadeFactory 









