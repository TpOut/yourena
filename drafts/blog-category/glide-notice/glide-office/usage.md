```java
//context可以是Activitiy 或者Fragment，这样会在destroy 的时候自动取消

Glide.with(context)  //默认跟了个asDrawable
    .load(url)
    .placeholder(..)  //可以在load 之后设置转换、缓存等操作
    .fitCenter()
    .into(ImageView)  
    
//而为了复用，操作可以被包裹。包裹同时支持gennerated API 拓展的自定义操作
RequestOptions sharedOptions = new RequestOptions()
    .placeHolder(...)
    .fitCenter();
Glide.with(context).load(url).apply(sharedOptions).into()
    
//清除
Glide.with(context).clear(imageView)
    
//加载bitmap 或者drawable  
Glide.with(context).load(url).into(new CustomTarget<Drawable>() {
    @Override
    public void onResourceReady(Drawable resource, Transition<Drawable> transition) {
      // Do something with the Drawable here.
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
      // Remove the Drawable provided in onResourceReady from any Views and ensure 
      // no references to it remain.
    }
  });

//单纯的后台线程
FutureTarget<Bitmap> futureTarget =
  Glide.with(context)
    .asBitmap()
    .load(url)
    .submit(width, height);
Bitmap bitmap = futureTarget.get();
//记得要清除
Glide.with(context).clear(futureTarget);

Glide.with(context)
  .asBitmap()
  .load(url)
  .into(new Target<Bitmap>() {
    ...
  });
```

在列表中一样，无需担心复用的问题，只是如果本来要加载，而复用不加载了要调用清除。

有一个fallback 图像，和placeholder 同级，考虑下

<font color=red>这里的后台线程没看懂，不是都在后台吗？</font>

