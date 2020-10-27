TextureView 用来展示一个内容流，比如视频或者OpenGL场景，可以本地也可以远程  
只能用在硬件加速窗口，否则无法绘制  

和SurfaceView创建一个单独的window不一样，TV是一个常规View。 所以可以执行移动、大小变化等动画，以及其他View属性  
主要是获取他的SurfaceTexture,  getSurfaceTexture() TextureView.SurfaceTextureListener  

而SurfaceTexture只在TV onAttachedToWindow() 之后有效；  
并且TextureView只能存在一个使用者  