一般使用adb 和setprop 来调试Android 的要素（Android 8以下，property 名字会有32字符限定。)

#### crash dumps

崩溃除了在 logcat 中，还会在`/data/tombstones` 中写入更详细的部分：所有线程的栈追踪、完整的内存map、所有打开的fd 列表  

8.0之后，使用`crash_dump` ，会被`strace`、 `gdb` 等冲突屏蔽

遇到崩溃的时候，logcat 和`tombstone` 可以通过`aosp/WORKING_DIRECTORY/development/scripts/stack` 进一步解析`unstrip` 信息  

 也可以通过`debuggerd` 对正在运行的进程进行栈dump。  



老版本的展开器会在碰到java 堆栈的部分时直接停止后续的解析  

示例对解析进行了说明，看起来基本是一行代码，一段解释器  

如果栈信息最后指向了类似`libc.so(__pthread_start)` 这样的线程启动代码，那么表示展开正确；否则都是被截断的，基本不能确认是正确  



---

c++ 相关的检测：https://source.android.google.cn/devices/tech/debug/native-crash?hl=zh-cn