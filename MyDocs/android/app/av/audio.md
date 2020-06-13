- `MediaPlayer.getMetrics()`
- `MediaRecorder.getMetrics()`
- `MediaCodec.getMetrics()`
- `MediaExtractor.getMetrics()`



android 10 支持av1  ,native MIDI ( 非阻塞读，有示例)  

 



音频捕获  

https://android-developers.googleblog.com/2019/07/capturing-audio-in-android-q.html



 `AudioRecord.Builder`  `AudioTrack.Builder`

`onSearchRequested()`  `InputDevice`  `hasMicrophone()` 

 `getDevices()` `AudioDeviceCallback` 



`AudioManager`  



`VolumeShaper`



 `AudioFocusRequest` `requestAudioFocus()`,



控制

`MediaSession` `MediaController` `RemoteControlClient`  

`Notification.MediaStyle`  



`MediaSession`    



8.0 之后可以播放DRM-protected 材料

以及HLS 采样级别加密的媒体

支持seekTo  



MediaRecorder 支持MPEG2_TS 格式  

MediaMuxer 支持无线数量流  addTrack()  ,可以添加每帧metadata 信息用于追踪（追踪限于MP4，  

MediaDecoder  



`writeSampleData()`  传递ByteBuffer 和关联的时间戳  



生成的MP4 使用 `TextMetaDataSampleEntry` 标记metadata 的mime 格式

可以用MediaExtractor 解析出包含对应的信息的MediaFormat  



AudioManager  

AudioPlaybackConfiguration

getActivePlaybackConfiguration  



AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK  

``` 
onAudioFocusChange()
```

`AudioAttributes` 



媒体按钮事件，优先传递给前台activity，否则传递给最近播放过音频的app（首先判断session 是否还在，不在就给MediaButtonReceiver  



 High Efficiency Image File format ([HEIF](https://developer.android.google.cn/reference/android/media/MediaFormat#MIMETYPE_IMAGE_ANDROID_HEIC) or HEIC)  

[`HeifWriter`](https://developer.android.google.cn/reference/androidx/heifwriter/HeifWriter) 

[`MediaDrm`](https://developer.android.google.cn/reference/android/media/MediaDrm#getMetrics())   



AAudio [`AudioEffect`](https://developer.android.google.cn/reference/android/media/audiofx/AudioEffect)  





---

ISOBMFF 章节12.3.3.2 







