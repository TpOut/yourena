
```
adb install -d
```
### 调试  
```
#上报 report
`adb [-s deviceSerialNumber] bugreport [targetPath]`  
```

### 模拟器
```
#录制视频：
`adb emu screenrecord start --time-limit 10 [path to save video]/sample_video.webm`  
#截图：  
> `adb emu screenrecord screenshot [destination-directory]`
```
