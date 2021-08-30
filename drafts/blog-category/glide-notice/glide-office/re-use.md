资源复用  

bitmap , byte[], int[]，POJO



为了处理复用，确保复用的安全性    

into 方法会对一个资源进行引用计数的增加，  在clear 时减少（或者重新into，可以理解成内部clear）

不安全行为具体有(大致理解成已经被glide 释放的bitmap，可能会被复用，但是复用之前的版本还被应用使用，导致一些不可预期的问题)：

- 通过getImageDrawable 来获取对应的bitmap, drawable 资源，并用于展示
- 对bitmap 执行recycle  

为了杜绝应用的引用导致的不安全问题，应该在target 的onLoadCleared 里同时清除应用的引用  



可以配置bitmapPool  

