虽然有专门的BitmapDrawable ，但是其他类型也是bitmap 

<font color=red>为什么.9图的属性只有dither，而普通的有filter 等？</font>

- bitmap 

    `BitmapDrawable` 图形文件（png,gif,jpg）

    aapt 会在编译时进行优化，比如将色值少于256的true-color 图像转成8-bit color palette 存储。

    也因此在需要手动流式读取图像的时候，需要存放在`res/raw` 中

    > 这个因此不是很理解，怀疑8-bit 的色值索引是系统层而不是图像内部的

    可以直接xml 编写`<bitmap>`, 替代其他xml 索引的drawble。前者不会缩放，而后者会缩放以适应容器大小。  

- nine-patch

    `NinePatchDrawable` （.9.png）

- layer-list

    `LayerDrawable` 叠加drawable，以index 作为顺序依据

    - transition

        `TransitionDrawable` 两个drawable 之间的变换

- state-list

    `stateListDrawable` 状态相关，其中drawable 值可以使用@color 索引

    可以设置固定大小、padding 等

- level-list

    `LevelListDrawable` 叠加drawable，以level 区间设置

- inset, clip, scale 

    `InsetDrawable` 外部空隙

    `ClipDrawable` 裁剪

    ```kotlin
    if (drawable is ClipDrawable) {
        drawable.level = drawable.level + 1000
    }
    // 根据level 的值展示一部分
    ```

    `ScaleDrawable` 缩放

- shape

    `GradientDrawable` 几何图形，颜色和渐变  

    渐变有线性、radial、sweep



