电量相关

- PowerManager 可以进行电量管理，有一个锁屏后的 部分唤醒功能`PARTIAL_WAKE_LOCK`  

  如果使用不合理，就可能造成资源的浪费  

- AlarmManager 类似可以做一些定时唤醒

  如果再接收唤醒消息的时候，处理耗时，也不太行  

- 后台wifi 搜索  

- 后台网络使用  



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
  - gpu 渲染分析

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

  **DrawFrame** renderThread  

  

  

  

  



