## icon设计

[status bar icon design](https://developer.android.google.cn/guide/practices/ui_guidelines/icon_design_status_bar)

## .9图

[.9图的创建](https://developer.android.google.cn/studio/write/draw9patch)

## webP

[webP图像创建](https://developer.android.google.cn/studio/write/convert-webp)

## 使用Image Asset Studio

打开： 右键res > new > Image asset 可以生成 launcher图标，Action bar和tab图标，notification图标
支持以下文件类型：png(首选)、jpg(可接受)、gif(不鼓励)

可以给生成的图标设置文本

## 添加矢量图

支持的矢量图格式包括：svg、psd
默认支持4.4以上，可以用兼容库（VectorDrawableCompat）到2.1，
导入用vector asset studio(右键res > new > vector asset)。 导入后是VectorDrawable, 在5.0及以上可以使用AnimatedVectorDrawable为其添加属性动画，或者用AnimatedVectorDrawableCompat在3.0及以上添加动画

- 矢量图适用于简单的图标

    > 例如Material 图标。反例如启动图标，包含许多细节更适合用光栅图像。

- 建议矢量图像限制为最大 200 x 200 dp

    > 相对于光栅图像，矢量图首次加载时可能消耗更多的 CPU 资源。之后，二者的内存使用率和性能则不相上下。

- 建议矢量图默认颜色（fillcolor）为黑色，通过tint修改

兼容策略及支持的元素 ：
https://developer.android.google.cn/studio/write/vector-asset-studio#apilevel
对PSD文件的支持和限制：
https://developer.android.google.cn/studio/write/vector-asset-studio#PSD
对SVG文件的支持和限制：
https://developer.android.google.cn/studio/write/vector-asset-studio#SVG