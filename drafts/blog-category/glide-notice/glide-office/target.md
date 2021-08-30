target 的定义是请求和请求者之间的媒介  

诸如正在展示的placeholder，已加载的资源，或是正在确定每次请求的合适尺寸  

最常用的有`ImageViewTargets` ,



常规用下into 用于target ，就是将请求结果传递给target  

```java
Target<Drawable> target = 
  Glide.with(fragment)
    .load(url)
    .into(new Target<Drawable>() {
      ...
    });

//也可以直接对target 进行操作
Glide.with(fragment)
  .load(newUrl) //比如加载一个新的资源，不过这会取消之前请求/释放之前资源
  .into(target);

//清除
Glide.with(fragment).clear(target)  
```

而平时写的into(ImageView) 就是对target 方法的包装  

同时因为可以再度操作的逻辑，肯定是target 将option 之类的状态给保存了，或者有地方保存两者的关联  

果然看到下文是用的View 的tag 作为索引键，也正因此，可以直接用imageView 对象本身进行取消  



当然在使用viewTarget，进行再度加载、取消或者检索之前加载信息的时候，需要tartget 继承自ViewTarget 或者在request 相关方法中实现检索关联  

诚如是，如果没有实现检索关联，比如SimpleTarget，在结束使用target 之后，一定记得clear  



target 的getSize 即请求的大小，一般在里面调用cb.onSizeReady 即可，也可以把cb 传出去，在适当的时候调用onSizeReady  

viewTarget 默认会根据view 的属性（layoutParams > dimension > WRAP ， 等测绘`OnPreDrawListener`，还没有使用Target.SIZE_ORIGINAL 或者override   



Gif 相关：

```java
Glide.with(fragment)
  .load(url)
  .into(new SimpleTarget<>() {
    @Override
    public void onResourceReady(Drawable resource, Transition<GifDrawable> transition) {
      if (resource instanceof Animatable) {
        resource.start();
      }
      // Set the resource wherever you need to use it.
    }
  });
```















