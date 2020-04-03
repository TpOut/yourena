google-android优化专题：https://www.youtube.com/watch?v=qk5F6Bxqhr4&list=PLWz5rJ2EKKc9CBxr3BVjPTPoDPLdPIFCE

##布局优化
层级优化：
尽可能低层级layout，比如现在ConstraintLayout。
include标签，自身属性支持android:layout_... ; id属于特例
merge标签，同方向LinearLayout嵌套，会消除内层LinearLayout  
ViewStub，用到再加载

##绘制优化  
Space，不绘制  
onDraw中，对象池  

##内存泄漏优化  
正确处理生命周期  
handler,webView,动画

减少使用内部类  

##ANR
主线程卡顿5s

##加载优化
ListView,viewHolder;  
Bitmap,BitmapFactoryOptions

##线程优化
线程池

##对象优化
SparseArray,Pair;软引用，弱引用；LruCache,DiskLruCache  



摘自：
开发艺术探索，第4章，view的工作原理
