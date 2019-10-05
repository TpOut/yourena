拍照和录像，根据需求设置feature中required的值
```
    <uses-feature android:name="android.hardware.camera"
                  android:required="true" />
```
如果设置为false，则在代码中确认功能
```
hasSystemFeature()
```
外部存储需要写权限
```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

FileProvider.getUriForFile返回的URI是 `content:// `的格式，>=7.0 会因为跨越包解析`file://`格式的URI而报错，需要添加FileProvider　　

```
//外存相册
public void getExternalStoragePublicDirectory(DIRECTORY_PICTURES)　

//app内目录，>=4.４不需要写权限,即可以给权限添加属性`android:maxSdkVersion="18" `　　
getExternalFilesDir()
```

## API
### Camera
```
<uses-permission android:name="android.permission.CAMERA" />
<!-- 音频录制权限 -->
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<!-- 如果图片有gps信息 -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-feature android:name="android.hardware.location.gps" /><!-- >=21 -->
```
注意7.0, >=24 之后的多窗口功能，setDisplayOrientation()无法设置成适应activity的比例，
需要适配很多窗口大小和对应的aspect比例，或者使用letterbox?

可以控制的功能：图片格式，闪光模式，聚焦设置等等
主要是在 Camera.Parameters 设置，查看 https://developer.android.google.cn/guide/topics/media/camera#table1
还有测光和聚焦(Metering and focus areas)、人脸捕捉(face detection)、延时拍摄(Time lapse video)

人脸捕捉使用的时候，设置白平衡、聚焦区域、测光会无效

设置视频的AudioSource为MediaRecorder.AudioSource.UNPROCESSED的时候，可以用这个检测：
AudioManager.getProperty(AudioManager.PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED)

### Camera2


