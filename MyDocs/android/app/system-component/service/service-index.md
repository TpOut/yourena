Q：android service 如果正在运行,系统是怎么做到终结它的?



8.0 服务受限：  

限制场景下启动startService 会报异常  

限制场景下startForegroundService 五秒之内必须 startForeground  



startService, bindService

由于隐式intent 启动service 容易造成未知运行，所以5.0 以后不允许



JobIntentService  

[`FOREGROUND_SERVICE`](https://developer.android.google.cn/reference/android/Manifest.permission#FOREGROUND_SERVICE)  9.0 前台需要申请，是个普通权限。  



前台服务类型：  

![image-20200613110940128](image-20200613110940128.png)



