电量相关

- PowerManager 可以进行电量管理，有一个锁屏后的 部分唤醒功能`PARTIAL_WAKE_LOCK`  

  如果使用不合理，就可能造成资源的浪费  

- AlarmManager 类似可以做一些定时唤醒

  如果再接收唤醒消息的时候，处理耗时，也不太行  

- 后台wifi 搜索  

- 后台网络使用  

- 独立的buckets 电量管理，  



anr  

- io、calculation 

  io都应该使用子线程 / calculation 看情况

- 同步binder 返回过慢

- 等待其他线程同步锁、死锁  

  死锁有一些死锁防止算法

- 广播的耗时  



anr 检测方式：StrictMode  

- profiler - cpu - record trace  

```shell
# 获取anr 文件
adb root
adb shell ls /data/anr # 老版本一个traces.txt，新版本多个anr_*
adb pull /data/anr/<filename> 
```



crash 

- c++ 崩溃也有一个symbols 文件，用于解析方法信息  
- oom 可以找低ram 设备
- 网络错误可以用模拟器



渲染，检测方法：

- Visual inspection

  简略信息

  - 使用release 包
  - 冷启动，低端设备
  - 开发者选项 gpu 渲染分析，的确不太直观

- Systrace

  详细信息，用一个命令行，

  新的平台可以用Perfetto （这工具没用成功。。

- custom performance monitoring

  但是系统信息就比较杂乱，需要自定义来区分哪一部分  


  如果方法本身少于200us，或者被调用数十次，就不建议添加

  ```python
  // 命令行添加 -a
  python systrace.py -a com.example.myapp -b 16384 \
        -o my_systrace_report.html sched freq idle am wm gfx view binder_driver hal \
        dalvik camera input res
  ```

  ```kotlin
  // 代码添加
  Trace.beginSection("SampleTag")
  Trace.endSection()
  ```



常见的卡顿来源：

- 界面绘制

  可以使用《测试》里的一个方式进行测试

  ```shell
  // dumpsys, gfxinfo 包括期间的动画帧信息
  adb shell dumpsys gfxinfo <PACKAGE_NAME>  
  ```

  会有csv 格式的数据，自己处理一下：

  INTENDED_VSYNC 和VSYNC 对比，时差多就是有阻塞  

  HANDLE_INPUT_START 和ANIMATION_START 对比，时差多就是事件处理有阻塞

  ANIMATION_START  - PERFORM_TRAVERSALS_START，时差多就是动画有阻塞

  PERFORM_TRAVERSALS_START - DRAW_START 之间就是layout 和measure 的时间

  DRAW_START - SYNC_QUEUED - SYNC_START 之间是draw 的时间

  ​                           SYNC_QUEUED - SYNC_START 之间是renderThread 的时间  

  SYNC_START - ISSUE_DRAW_COMMANDS_START  之间是bitmap 绘制时间  

  ISSUE_DRAW_COMMANDS_START - FRAME_COMPLETED 是GPU 消耗时间

- 列表

  考虑刷新时用diffUtil，嵌套时公用view 池，设置预拉取数目   

  create 的时候inflate 远比一两个view 动态控制耗时（测量一下大致多少个？  

  recycleItem 的时候/setText 之类会引起resize 的  

- layout 场景：

  使用translation / rotation / alpha 之类的只触发draw 显然比padding / margins 之类的触发整个绘制的更好
  
- 渲染时机

  **Record View#draw** UIThread  

  - 在draw 里经常会绘制bitmap，调用drawBitmap。它是在cpu 上处理，会比较慢

    可以考虑保存BitmapShader，然后调用drawRoundRect。  

    或者设置绘制LayerType 为LAYER_TYPE_HARDWARE  
  
    > 常见的有装饰图片（设置圆角），在上面绘制渐变，或者图像过滤（ColorMatrixColorFilter）  
  
  **DrawFrame** renderThread  
  
  - 尽可能不要用Canvas.saveLayer  
  
  - drawPath 的内容如果在帧之间改动较大，建议用常规方法拼  
  
  - clipPath 类似，可以先处理好BitmapShader，然后draw  
  
  - bitmap 展示的时候，是以OpenGL textures 的形式上传给GPU，这个过程可以通过prepareToDraw() 提前触发。（一般性库都有做）
  
  - 还可能因为syncFrameState 卡住，IPC 调用卡住
  
    > syncFrameState 看下源码？
    >
    > ipc 可以用adb 查看
    >
    > ```shell
    > $ adb shell am trace-ipc start
    > … use the app - scroll/animate ...
    > $ adb shell am trace-ipc stop --dump-file /data/local/tmp/ipc-trace.txt
    > $ adb pull /data/local/tmp/ipc-trace.txt
    > ```
  
- 对象创建和回收

  memory Profiler  




**启动时间：**

冷启动、暖启动、热启动  

- 加载和运行app 
- 展示白屏
- 创建进程  
- 创建application
- 创建main thread
- 创建activity 
- 第一帧绘制后显示，替换掉白屏  

测量方式：

- ```shell
  ActivityManager: Displayed com.android.myexample/.StartupTiming: +3s534ms
  
  adb [-d|-e|-s <serialNumber>] shell am start -S -W
  com.example.app/.MainActivity
  -c android.intent.category.LAUNCHER
  -a android.intent.action.MAIN
  ```

手动触发时间日志

```shell
reportFullyDrawn()
system_process I/ActivityManager: Fully drawn {package}/.MainActivity: +1s54ms
```

优化方向：

- 懒加载，Hilt  
- content provider 统一启用，StartUp
- layout、 I/O、Bitmap、Vector  

也可以通过隐藏启动页，和修改展示背景来视觉上优化体验，本质上没啥用  



**进程和线程**

进程这部分，主要还是讲前后台，尽量存活久一点的逻辑  

分时控制，WorkManager /  AlarmManager  

设置优先级，Process.setThreadPriority  

控制数量  



**内存管理**  

减少对象分配回收 频繁过程  

四大组件，内存不足的时候有回调，onTrimLevel  

判断内存状态  

数据结构， sparse  

protobuf  

如果抽象代码没有明显的好处，就别抽了。。  



代码层面:

- stringbuffer ，和string

- int 数组 ，和 Integer 数组

- 两个列表，和封装的对象列表

- final static 变量 

- 对于arrayList 应当手写循环，其他的用for each 迭代  

- 浮点数比整数慢2倍

  

---

anr 分析思路，

https://blog.csdn.net/weixin_44708240/article/details/109159408  