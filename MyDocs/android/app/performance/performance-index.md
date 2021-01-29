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

  自定义

  







