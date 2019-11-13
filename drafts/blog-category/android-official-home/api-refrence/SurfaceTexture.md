可以把一个图像流 转化成 OpenGL ES 纹理，纹理对象 使用 GL_TEXTURE_EXTERNAL_OES   
可以在任何线程创建  

updateTexImage()调用的时候，texture对象创建时的内容会被最近的图像流更新，即中间的一些可能会被跳过  
从texture采样的时候，需要先转化矩阵坐标 getTransformMatrix(float[])，矩阵坐标可能在每次updateTexImage() 的时候变化  

还是注意线程之间的调度，比如另一条线程的frame-available callback 不能直接调用updateTextImage() 方法
