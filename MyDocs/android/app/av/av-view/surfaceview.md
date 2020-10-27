SurfaceView 提供了一个内嵌在View体系里的专用于绘制的surface  
你可以控制它的大小、格式   

这个surface是Z轴控制的，一般性它绘制在持有SurfaceView的window之下（相对于用户）。    
SurfaceView 在它的窗口中打开一个‘洞’来让surface展示  

任何同级View(sibling)一般都会在SurfaceView的显示之上-- 经测试，也是按View序  
但是要注意，如果在surface上面的控件存在透明的（full alpha-blended composite），则每次surface change的时候都会重新合成，会影响性能。  

surface的控制通过 getHolder获取  
SurfaceHolder.Callback.surfaceCreated(SurfaceHolder) 在SurfaceView的window可见时回调  
SurfaceHolder.Callback.surfaceDestroyed(SurfaceHolder) 在SurfaceView的window 不可见时回调  

SurfaceView 的一个目标是为了可以在 子线程渲染，如果要在子线程使用：  
那么所有的SurfaceView 方法和SurfaceHolder.Callback 方法都会在子线程被调用，那么注意线程间状态数据的交互  

注意， >=24 开始，在SurfaceView的window 位置会和其他View 一起同步渲染。就是说移动和缩放SurfaceView不会有视觉(渲染)效果  
而 <24 时，是异步渲染，所以会有效果。  