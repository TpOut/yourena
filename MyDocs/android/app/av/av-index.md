audio app:https://github.com/android/uamp



ExifInterface



色彩提取Palette  



MicrophoneDirection  



MPEG-DASH  

CENC (Common Encryption)  

DRM  

HLS (HTTP Live Streaming  

DSP (digital signal processor)  

PTS (presentation timestamps)



WebVTT(Web Video Text Tracks)



```java
// 音频实时数据，峰值和均方根
Visualizer
// 音量增强
LoudnessEnhancer
```



`MediaCodec` 支持分辨率自适应播放（无缝playback on Surface  

首先确认是否支持`isFeatureSupported("FEATURE_AdaptivePlayback")`

在`configure` 的`MediaFormat` 中设置最大宽高，即开启自适应播放 (在这个最大宽高之内的分辨率变化是无缝的，大于则不保证)    

`MediaCodec.queueInputBuffer()`

h264 的一些属性概念：

Sequence Parameter Set (SPS) 

Picture Parameter Set (PPS)

Instantaneous Decoder Refresh (IDR)



音视频同步：

`AudioTimestamp` `AudioTrack`  



`ImageReader` 可以用`MediaPlayer` `MediaCodec` 获取 帧图像  



`RemoteControlClient` 锁屏外设控制监听  

`NotificationListenerService`  

`Rating`    

  

opus 解码: https://www.opus-codec.org/



`yuv` `bayer`  



ENCODING_PCM_FLOAT  



浏览

`MediaBrowswe` `MEdiaBrowserService` 

