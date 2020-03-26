ThreadPoolExecutor

核心线程数，最大线程数，线程存活时间（默认非核心，可以配置为对核心线程生效），拒绝新任务



Android 的常规配置可以参考AsyncTask 的THREAD_POOL_EXECUTOR



Excutors.new...ThreadPool 创建不同类型

- FixedThreadPool : 只有固定数量核心线程，响应较快

    主任务

- CachedThreadPool ：只有不定数量的非核心线程，空闲超时60s，全空闲时无线程。

    主要用来大量耗时少任务

- ScheduledThreadPool ：核心固定，非核心随意，空闲超时0s，

    主要用来定时和固定周期任务

- SingleThreadExecutor ：一个核心线程

    同步任务