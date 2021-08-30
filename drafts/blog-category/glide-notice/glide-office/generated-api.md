生成api  ，用于

- 集成拓展glide api 的库
- 在应用内，通过添加绑定了操作的方法，拓展glide api

一个应用，包括依赖的库，只能使用一个api 的实现



```java
// 实现一个类，
继承AppGlideModule
@GlideModule
public final class MyAppGlideModule extends AppGlideModule {}

//然后rebuild，在同一个包下会生成一个GlideApp，代替普通使用时的Glide  
GlideApp.with(context)...
```



GlideExtension：

api 可以被应用和库拓展。通过注解静态方法，来添加新的操作/修改已有的操作（GlideOption），或者添加类型（GlideType）。    

继承api 的类需要用`GlideExtension` 标记，且需要一个私有的空构造，同时是final 和包含方法只能是静态的，还可以包含静态变量、或引用其他类、对象  

拓展类的数量没有限定，最终都会被合入AppClideModule  

  

然后讲Option，可以用于合并原有的操作，也可以自己添加组合

```java
@GlideExtension
public class MyAppExtension {

  private static final int MINI_THUMB_SIZE = 100;

  private MyAppExtension() { } // utility class

  @NonNull
  @GlideOption
  public static BaseRequestOptions<?> miniThumb(BaseRequestOptions<?> options，int size) {
    return options
      .fitCenter()
      .override(size); //这里的方法size 当然可以直接用静态常量，不加size 这个参数
  }
//上面的方式会产生一个下面的类
public class GlideOptions extends RequestOptions {
  
  public GlideOptions miniThumb(int size) {
    return (GlideOptions) MyAppExtension.miniThumb(this);
  }

  ...
}
//然后就可以使用了
GlideApp.with(context).load(url).miniThumb(size).into(imageView)  
```



再试Type 

```java
@GlideExtension
public class MyAppExtension {
  private static final RequestOptions DECODE_TYPE_GIF = decodeTypeOf(GifDrawable.class).lock();

  //GifDrawable 是一个泛型的具体化
  @NonNull
  @GlideType(GifDrawable.class)
  public static RequestBuilder<GifDrwable> asGif(RequestBuilder<GifDrawable> requestBuilder) {
    return requestBuilder
      .transition(new DrawableTransitionOptions())
      .apply(DECODE_TYPE_GIF);
  }
}
// 上面的部分会生成下面这段
public class GlideRequests extends RequesetManager {
    
  public GlideRequest<GifDrawable> asGif() {
    return (GlideRequest<GifDrawable> MyAppExtension.asGif(this.as(GifDrawable.class));
  }  
  ...
}
// 然后就可以使用
GlideApp.with(fragment).asGif().load(url)
```







