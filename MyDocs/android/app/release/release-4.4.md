Dalvik JIT、KSM( kernel samepage merging)、  zRAM

厂商可以调整 进程OOM 的级别、图像缓存大小、内存回收等。   

NFC (通过Host Card Emulation 实现)

打印框架（google 一般会内置打印服务

存储访问框架（文件管理器

低电耗传感器（sensor batching；计步器

SMS 提供者  

全屏沉浸式（透明系统ui，提供了侧滑手势  

动画转化框架（ `android.transition`

通知样式更丰富  

Chromium WebView  

屏幕录制（

播放无缝切换分辨率  

HLS 支持更新  

音频统一DSP 处理，系统管理

> 有个tunnel 的概念没看懂，目前理解成，audio 统一交给DSP 处理，需要一个隧道技术。而隧道技术，通常就是理解成一个封装。

可以用音频播放展示设备上正在播放音频的峰值和RMS  

支持HAL 上报pts

支持Miracast 认证作为显示设备

GPU 加速、RenderScript 优化

NDK 支持RenderScript  

> RenderScript 可以自动处理多核CPU、GPU、其他处理器（如DSP）的异步调用

OpenGL ES 2.0

支持多屏  

IR Blaster (红外连接，用于控制tv 等)

无线支持TDLS  

无障碍字幕 (实时修改字幕设置，VideoView 支持WebVTT 格式添加字幕  

Drawable 支持RTL (开发者选项，方便测试)  

SELinux  

加密算法：ECDSA

VPN 分用户；FORTIFY_SOURCE  