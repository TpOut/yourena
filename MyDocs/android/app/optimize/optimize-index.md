UsageStats



电量优化：

`dumpsys` 查看电量使用数据，对应可视化工具：https://github.com/google/battery-historian  



长久运行的窗口，可以设置  

`Window.setSustainedPerformanceMode()`  , 失去焦点后失效  



帧测量：Frame Metrics API   

`adb shell dumpsys gfxinfo framestats`

`OnFrameMetricsAvailableListener.onFrameMetricsAvailable()`  

