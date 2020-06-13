获取屏幕内容: `MediaProjection`  



可以用adb 启动，



`adb shell screenrecord.`  



```java
// 在ui 中加一个sv，禁止截屏和录屏（录屏是黑的
SurfaceView.setSecure(true)   
// 禁止截屏和录屏 (录屏是黑的
getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE);
```

`createVirtualDisplay`  

`createScreenCaptureIntent()`  

