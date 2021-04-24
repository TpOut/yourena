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

但是可折叠手机，导致其比率可能低于1:1，类似平板了  



平板的认定是large / xlarge  



硬件功能的特性，独占性（如摄像头、麦克风等），可移除性（外接）  

`CameraManager.AvailabilityCallback#onCameraAccessPrioritiesChanged()`   



多展示屏，ActivityOptions  , wallpaper  

```kotlin
activityManager.isActivityStartAllowedOnDisplay(context, displayId, intent)

val options = ActivityOptions.makeBasic()
options.launchDisplayId = targetDisplay.displayId
startActivity(intent, options.toBundle())
```

  

layout alias   



manifest 中 anyDensity = false 或者Bitmap.inScaled = false  

```  xml
<meta-data
    android:name="android.supports_size_changes" android:value="true" />
```

多屏幕默认失去焦点的activity 会pause，和常规声明周期不同  

容易有逻辑问题，10 之后支持multi-resume  

```xml
// multi resume  
// android 9 兼容  
<meta-data
    android:name="android.allow_multiple_resumed_activities" android:value="true" />
```

​    

目前多窗口应该是不支持多个app 之间拖拽数据的  

  



