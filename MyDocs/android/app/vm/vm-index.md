这一章有点超出能力范围，比如art中CollectorTransition的复制顺序，是猜的，也不知道对不对。



### 在dalvik中

```
//格式
D/dalvikvm(PID): GC_Reason Amount_freed, Heap_stats, External_memory_stats, Pause_time  
//示例
D/dalvikvm( 9050): GC_CONCURRENT freed 2049K, 65% free 3571K/9991K, external 4703K/5261K, paused 2ms+2ms
```

GC的原因：

- GC_CONCURRENT: 在Heap将满（begins to fill up）的时候，发生并发GC以释放内存
- GC_FOR_MALLOC: 在heap已经满的时候，发生GC以重新分配内存（app会暂停）
- GC_HPROF_DUMP_HEAP： 创建Hprof文件用作分析heap的时候
- GC_EXPLICIT： 明确的GC,比如调用gc()方法的时候--应该避免使用gc()
- GC_EXTERNAL_ALLOC： <=10才会发生。那时把一些数据，比如像素信息存在native内存或NIO二进制缓冲中。

### art的日志信息

明确的GC永远会在这里打印。
而其他的不明确的GC，只有GC过程缓慢（暂停超过5ms或者时长超过100ms）的才会被打印
而如果app在后台时，gc进行了暂停，由于用户无法察觉到，所以也被认为是不必要的而不进行打印

```
//格式  
//
I/art: GC_Reason GC_Name Objects_freed(Size_freed) AllocSpace Objects,
    Large_objects_freed(Large_object_size_freed) Heap_stats LOS objects, Pause_time(s)
//示例  
I/art : Explicit concurrent mark sweep GC freed 104710(7MB) AllocSpace objects,
    21(416KB) LOS objects, 33% free, 25MB/38MB, paused 1.230ms total 67.216ms
```

GC的原因：

- Concurrent: 这种GC不会暂停app线程，在后台运行且不会阻止（内存）分配
- Alloc: 在heap已经满了又需要分配内存的时候发生，在allocating线程进行回收（gc was initiated）
- Explicit: app非常明确的调用，如 Runtime.getRuntime().gc()/System.gc(). 这种方式会阻塞allocating线程且浪费cpu周期；如果抢断了其他线程，还会导致jank (stuttering, juddering, or halting in the app)
- NativeAlloc: native内存产生压力时，如Bitmap;RenderScript
- CollectorTransition： heap转换时发生。8.0之前的低RAM设备，从用户无法察觉暂停的状态切换到可以察觉的状态时，会把所有的对象从fress-list backed space复制到bump pointer space（反之亦然）
- HomogeneousSpaceCompact： 一般发生在app进入不可察觉暂停时，对free-list进行压缩、碎片整理heap，以达到减少RAM使用的效果
- DisableMovingGc： 这不算一个原因，主要用来表示 使用GetPrimitiveArrayCritical造成的阻塞。
- HeapTrim： 这不算一个原因，表示heap trim结束后还是阻塞状态。

GC NAME:

- Concurrent mark sweep (CMS): 对除了image空间的所有空间进行整体的heap回收
- Concurrent partial mark sweep： 除去image和zygote的基本所有空间，进行整体的heap回收
- Concurrent sticky mark sweep： 释放最近一次GC以来分配的对象。出现次数更多
- Marksweep + semispace： 非并发，复制性质的GC，类似HomogeneousSpaceCompact的作用

objects freed/size freed都是针对non large object space
ART CMS GCs 只会在GC快结束的时候产生一次暂停。