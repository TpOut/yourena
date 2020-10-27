High Efficiency Image File (HEIF) , HEIC  



AdobeRGB, Pro Photo RGB, DCI-P3,  

ImageDecoder   替代BitmapFactory.Option  



`ImageDecoder` lets you create a [`Drawable`](https://developer.android.google.cn/reference/android/graphics/drawable/Drawable) or a [`Bitmap`](https://developer.android.google.cn/reference/android/graphics/Bitmap) from a byte buffer, a file, or a URI. To decode an image, first call [`createSource()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#createSource(java.nio.ByteBuffer)) with the source of the encoded image. Then, call [`decodeDrawable()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#decodeDrawable(android.graphics.ImageDecoder.Source)) or [`decodeBitmap()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#decodeBitmap(android.graphics.ImageDecoder.Source)) by passing the [`ImageDecoder.Source`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder.Source) object to create a [`Drawable`](https://developer.android.google.cn/reference/android/graphics/drawable/Drawable) or a [`Bitmap`](https://developer.android.google.cn/reference/android/graphics/Bitmap). To change the default settings, pass [`OnHeaderDecodedListener`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder.OnHeaderDecodedListener) to `decodeDrawable()` or `decodeBitmap()`. `ImageDecoder` calls [`onHeaderDecoded()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder.OnHeaderDecodedListener#onHeaderDecoded(android.graphics.ImageDecoder, android.graphics.ImageDecoder.ImageInfo, android.graphics.ImageDecoder.Source)) with the image's default width and height, once they are known. If the encoded image is an animated GIF or WebP, `decodeDrawable()` returns a `Drawable` that is an instance of the [`AnimatedImageDrawable`](https://developer.android.google.cn/reference/android/graphics/drawable/AnimatedImageDrawable) class.

[`setTargetSize()`](https://developer.android.google.cn/reference/android/graphics/ImageDecoder#setTargetSize(int, int)). `setTargetSampleSize()`.`setCrop`  `setMutableRequired`  



[`AnimatedImageDrawable`](https://developer.android.google.cn/reference/android/graphics/drawable/AnimatedImageDrawable)



```
在app 中要展示静态图片（images），就需要`Drawable` 来绘制形状（shapes）和图片

`Drawable` 是可被绘制的事物的一般抽象。

通过类的构造器，可以通过 项目中的一个图片资源（bitmap 文件）或者 一个xml 资源（定义了drawable 属性）来定义和实例化`Drawable`

#### 图片资源

app 中的图像（graphics）可以引用自项目资源 --`res/drawable/`-- 中的图片文件。支持的文件类型有PNG（优先），JPG（可接受），GIF（不推荐）。

> res/drawable/ 的图片资源可能在构建（build）的时候被aapt 进行无损压缩
>
> 比如true-color png 在不需要256 种颜色的时候，会被转化为的8-bit的index-color png（参看[image-types](./image-types.md)）。此时图片质量不变，但是需求更少内存，同时文件二进制流会被替换
>
> 如果需要把图片以比特流（bitstream）的形式转化成bitmap，则使用`res/raw/` 目录，一定不会被处理。

​```kotlin
// view 使用
ImageView(this).apply {
  setImageResource(R.drawable.my_image)
  // set the ImageView bounds to match the Drawable's dimensions
  adjustViewBounds = true
}

//直接获取对象
val myImage: Drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.my_image, null)
​```

> 同一个图片资源的drawable 对象实例，对状态的修改都会同步影响所有对象的实例。
>
> 如果实在需要处理，则考虑使用[tween animation](./tween-animation.md)

​```xml
# TransitionDrawable
<transition xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/image_expand">
    <item android:drawable="@drawable/image_collapse">
</transition>
​```

任何实现了`inflate()` 方法的`Drawable` 子类都支持在xml 中定义，然后在app 中实例化

​```kotlin
val transition= ResourcesCompat.getDrawable(
        context.resources,
        R.drawable.expand_collapse,
        null
) as TransitionDrawable

val image: ImageView = findViewById(R.id.toggle_image)
image.setImageDrawable(transition)

transition.startTransition(1000)
​```

实现了`draw()` 方法的则可以在自定义view 中使用，如ShapeDrawable

​```kotlin
class CustomDrawableView(context: Context) : View(context) {
    private val drawable: ShapeDrawable = run {
        val x = 10
        val y = 10
        val width = 300
        val height = 50
        contentDescription = context.resources.getString(R.string.my_view_desc)

        ShapeDrawable(OvalShape()).apply {
            // If the color isn't set, the shape uses black as the default.
            paint.color = 0xff74AC23.toInt()
            // If the bounds aren't set, the shape can't be drawn.
            setBounds(x, y, x + width, y + height)
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawable.draw(canvas)
    }
}
​```

NinePatch Drawables

可拉伸的bitmap 图片，是有一像素边界的标准png，以`*.9.png` 的格式放在`res/drawable/` 中

左上设置拉伸区域（stretchable area），右下设置绘制限制（drawable section、padding box），如果右下没设置，默认取左上。

自定义drawable 的关键，是继承自`Drawable` 并且实现`draw()` 方法



Android7.0 以上，可以使用xml 的形式来指定具体的实例化drawable 配置

​```xml
// 支持public top-level class
<com.myapp.MyDrawable xmlns:android="http://schemas.android.com/apk/res/android"
    android:color="#ffff0000" />
// public top-level classes and public static inner classes
<drawable xmlns:android="http://schemas.android.com/apk/res/android"
    class="com.myapp.MyTopLevelClass$MyDrawable"
    android:color="#ffff0000" />
​```



api 21 以上，可以使用tint 处理bitmaps 和定义为`alpha masks` 的nine-patch 图片

支持颜色资源和能转化为颜色资源的主题属性（如`?android:attr/colorPrimary`）

具体讲包括`BitmapDrawable`, `NinePatchDrawable`, `VectorDrawable`



可以使用`Palette` 提取图片的颜色
```

