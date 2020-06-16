在指定时间内收集系统的信息，默认包括所有支持的服务，可以指定忽略和指定某一个服务。  

可以查找某个包的uid  

可以分析：

- 输入

    分析系统输入设备的状态，包括硬件按钮 和屏幕触摸，以及输入事件的处理，一般输出有：

    - event-hub-state

        所有配置过`key-layout,key-character` 映射文件的设备信息都会被打印，

        示例中包含

        `BuiltInKeyboardId` 内置键盘id，没有默认-2

        `classes` 字段，对应`event-hub.h` 里定义的设备类型  

    - input-reader-state

        负责解析kernel 传递的输入事件，检查屏幕触摸和遥感数据正确性

        包含所有设备信息，会展示配置信息和状态的变化信息，注意屏幕分辨率和校准信息，最后会有一个`configuration` 记录全局的配置参数比如点击(tap)间隔  

    - input-disoatcher-state

        负责分发输入事件给app，展示触摸的窗口、输入队列的状态、ANR 进程等等  

- 测试UI 性能

    主要在[docs/testing/testing-ui-performance] 介绍

- 网络

    记录设备启动后的网络使用数据。每个设备的展示信息会差很大

    示例展示了部分：

    - 可用接口和可用uid 接口（两者基本一样）
    - `Dev` 和`Xt` 信息
    - UID 对应的信息，可以看到某段收集时间(bucketDuration)内，接受和发送的包数和字节数

- 内存

    - 进程状态

        展示后台运行时长和对应的内存使用。有[PSS、USS、RSS](https://stackoverflow.com/questions/22372960/is-this-explanation-about-vss-rss-pss-uss-accurate)

    - 内存信息

        展示所有当前的内存分配类型。主要看PSS 、Pivate RAM(clean、dirty) 、Heap Alloc。  

        dirty RAM 不会以持久化存储（storage），因为Android 不使用swap，它是被修改了但是还没有提交回RAM 的页，Dalvik 和native 堆分配（包括共享部分）都是经由dirty RAM。clean RAM 则是从文件映射出来的页，比如正在执行的代码。

        PSS 很适合用于分析进程的内存消耗占系统消耗的比例。

        其他项不细介绍：可以分析native 本地和全局引用是否过多、分析Window 泄漏、分析activity 泄漏。

- 电量   

    - 电量相关事件的记录
    - 设备全局的分析
    - Per-app mobile milliseconds per packet
    - 每个UID 和系统组件的使用近似值
    - 系统UID 和app UID 的数据



<font color=red> 问题：</font>

1、在哪里找到 tuna-gpio-keykad.kcm  

2、网络分析部分有`Dev` 和`Xt`，不知道`Xt` 的时候以为`Dev` 是device，但是搜了一下估计`Xt` 是https://en.wikipedia.org/wiki/Spark_New_Zealand_Mobile 。这样`Dev` 和`Xt` 就不是一个类别了。

3、电量数据中有很多缩写词，也提供了相对应的描述。但是还是看懂，比如sy ，有三列数据sync, time, count。这里的sync 什么意思？



---

Last updated 2019-12-27

