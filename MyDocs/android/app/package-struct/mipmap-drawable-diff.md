按目前的理解，mipmap 应该是出自 MIP map  

即同一个区域的纹理，有不同的分辨率图像  



很适合launcher 的布局动态设置时icon 要变化的情况，所以初创Android 时就拿来用了  



而drawable 是对所有可绘制的抽象称呼，

drawable 虽然也可以设置多种限定符用于适配不同设备，但是在固定设备上读取的文件应当是固定的。 

所以是两个概念



但是Bitmap 作为drawable 的一种，有一个mipMap 的属性，即可以设置是否有多种分辨率，

又表明drawable 虽然是独立的概念，但还是借鉴了mipmap 的理解。  



而实际的使用上，文件放在drawable 文件夹还是mipmap 下，没有什么区别。

