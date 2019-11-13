承受由屏幕合成器管理的原始缓冲数据  

一般是由一个图像缓冲数据的消费者（如SurfaceTexture, MediaRecorder, or Allocation）来创建  
并且被传递给图像缓冲数据的产生着（如OpenGL, MediaPlayer, or CameraDevice）

并且Surface自身子再被回收的时候不会在持有消费者的引用，可以理解成弱引用  
