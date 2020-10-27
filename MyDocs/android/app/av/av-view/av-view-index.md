SurfaceControl  ，用于：

- 多Surface 同步；
- 跨进程surface 嵌套
- 底层生命周期管理



#### Surface

承受由屏幕合成器管理的原始缓冲数据  

一般是由一个图像缓冲数据的消费者（如SurfaceTexture, MediaRecorder, or Allocation）来创建  
并且被传递给图像缓冲数据的产生着（如OpenGL, MediaPlayer, or CameraDevice）

并且Surface自身子再被回收的时候不会在持有消费者的引用，可以理解成弱引用  

#### SurfaceTexture

可以把一个图像流 转化成 OpenGL ES 纹理，纹理对象 使用 GL_TEXTURE_EXTERNAL_OES   
可以在任何线程创建  

updateTexImage()调用的时候，texture对象创建时的内容会被最近的图像流更新，即中间的一些可能会被跳过  
从texture采样的时候，需要先转化矩阵坐标 getTransformMatrix(float[])，矩阵坐标可能在每次updateTexImage() 的时候变化  

还是注意线程之间的调度，比如另一条线程的frame-available callback 不能直接调用updateTextImage() 方法