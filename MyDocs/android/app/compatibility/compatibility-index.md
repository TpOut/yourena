app 兼容  



**屏幕兼容** 概览：

- 自适应  
- 多布局
- .9  
- dp  
- 多图  
- vector  



相关的类有：

`resources.displayMetrics`   

 `ViewConfiguration.get(myContext)`  



cutout 部分：`android:resizeableActivity` 这个属性不太行  

如果有需要，屏幕比率限制`android:maxAspectRatio`，最大为12:5 最小为1.33  (7.0 使用meta-data)  



平板的认定是large / xlarge  

​     

layout alias   



manifest 中 anyDensity = false 或者Bitmap.inScaled = false  







