去除水中照片的水背景
http://openaccess.thecvf.com/content_CVPR_2019/papers/Akkaynak_Sea-Thru_A_Method_for_Removing_Water_From_Underwater_Images_CVPR_2019_paper.pdf



https://drububu.com/tutorial/image-types.html

图像一般被分为两种，光栅图和矢量图

这里讲光栅图，主要有 png, jpg, gif

其中png 和gif 是无损的，jpg 会通过移除一些细节来进行压缩

png 和jpg 的色值无限，gif 只支持256 种



光栅图的表现形式即 bitmap

又可以被分为 indexed color 和 true color

应用时主要取决于场景中用到的颜色种类



indexed color 是设置一张色值查找表（有限数量），每一个像素的存储则是获取颜色在表中的索引值

当你的图像中的色值数量多于限制，而一定要使用indexed color 方式时，需要使用“dithering”



true color 则在每一个像素中存储对应的色值，比如RGB 是24位存储每一个像素点，每8位对应各自的色值；ARGB 则还有Alpha 数据



前者的缺点是色值数量上限，后者的缺点是存储空间较大



gif 使用indexed color

PNG（ *Portable Network Graphic* ）支持indexed color, grayscale, true color 模式

它相较于gif 的优点是：无专利限制；可变透明度；压缩性能更好；

缺点是 不支持动画

两者都支持图片加载过程中展示（Both *file formats* support *interlacing*: an image slowly builds up while the data is being loaded.）



1-bit, 8-bit, 24-bit 的概念是基于indexed color 的，即色值查找表的索引数量。