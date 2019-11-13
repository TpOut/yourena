分析的时候要关闭instant run

## cpu分析
cpu信息追踪主要分为：
- 系统追踪：捕获app和系统资源交互的条理性细节
- 方法追踪：可以找到在某个时间片段上执行的方法（包括调用者和被调用者），以及对应消耗的资源

可以看到一个app中的线程数量，比如我现在的项目，主页啥都没干，有76个线程，只有两个活跃的，包括一个主线程一个渲染线程

record时间可以拖拽，Thread time和Wall clock time的区别是去除了不活跃的时间

追踪记录类型：
- sampled（java）: 快速间隙式捕获java代码执行时的调用栈，通过比较数据来获取时间线和资源使用信息。
>有一个明显的问题就是，如果在两次捕获间隙的时候方法调用并执行完毕，那么就不会被记录。如果不想错过，可以使用instrumented(java)
- instrumented(java): 在运行时集成，记录每一个方法开始和结束的时间戳，然后分析出数据。
>因为集成运行，所以肯定是会影响原始性能，从而导致数据被影响，特别是方法执行非常快的情况
- smapled(native): 需要运行在 >=8.0，使用simpleref跟踪native代码
- system trace:>=6.0,捕获app和系统资源交互的条理性细节。这种情况下，可以在代码中继承trace相关api，来记录数据。

里面讲到一个call chart tab，但是实际没有看到。。

## 性能分析

分析器兼容在 >= 5.0

对于<=7.1 需要在configuration中开启高级分析的功能，包含：
- 所有分析器的事件时间线
- 内存分析器的对象回收数量、gc事件
- 网络分析器的传输文件信息

native（c/c++,jni）相关的回收和网络是无法被获取的

## trace
9.0以上的机子，都会有这个app, System Tracing
使用方式请看：https://developer.android.google.cn/studio/profile/systrace-on-device

### 代码中集成

可以用Debug类在app中更好的控制要追踪的信息
保存日志，需求权限：WRITE_EXTERNAL_STORAGE

```
//存储在 getExternalFilesDir()，如果文件存在，会覆盖
Debug.startMethodTracing("sample");
//>=5.0,对性能影响更小
//startMethodTracingSampling()
...
Debug.stopMethodTracing();
```

此方法记录出来的trace文件路径，在 sdcard/Android/data/<packageName>/files/...trace
导到pc就可以用cpu profiler 查看了

### note
直接从apk调试，操作没有成功，查看原文：https://developer.android.google.cn/studio/debug/apk-debugger


分析相关，不只是profiler,更有run里的configuration


分析器解析：https://developer.android.google.cn/studio/profile/cpu-profiler#inspect-traces

## 单独
就看了两篇
