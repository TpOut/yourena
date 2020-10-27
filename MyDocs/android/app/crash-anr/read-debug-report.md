Android 可以通过adb 捕获bug ，工具包含

- `dumpsys` 
- `dumpstate`
- `logcat`  



还有功能：

- ANR 和死锁分析

    `/data/anr` 日志使用grep `am_anr`，或者直接搜索`ANR` 也可以

    注意日志是发生ANR 时的信息，而不一定是产生ANR 的原因。以及日志可能会有两部分，一个是`JUST NOW` , 一个是`LAST ANR`  

    ANR 也可能是死锁引起的，如果死锁击中了系统服务，会被看门狗杀掉（`WATCHDOG KILLING SYSTEM PROCESS`）。

    > 系统服务被杀掉，实际是runtime 重启，设备会回到启动动画
    >
    > 而系统重启，是kernel 崩溃，设备会回到google 启动logo  



分析技巧：

- activity 相关

    因为获取焦点的窗口一定是一个，所以崩溃时获取聚焦的activity 会得到很多关联信息。同时能知道哪些进程启动或停止也会很有帮助  

    搜索``am_focused_activity` 

- 进程状态

    `Start proc`  

    确认一个设备是否 thrashing，搜搜短时间内的`am_proc_died` and `am_proc_start`

- 内存

    因为内存不够的时候，总是会为了开启进程而回收一些进程。所以分析thrash 即可以间接判断内存是否不够。

    `am_low_memory` 表示最后一个进程缓存都被杀掉，之后便会开始清理services  

    其他的还有`kswapd`, `kworker`, and `mmcqd`  

    
