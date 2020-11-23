**主题是**：**Android 应用在进程未被杀死的情况下，可能会重建Application 类**



#### 前情提要

基本页面：应用有 开屏页Splash，主页Home

> 正常点击icon 是从Splash -> Home   

需求：为了让Home 页的操作更加流畅，需要在启动时提前做一些操作

> 为了方便描述，操作的事叫做 Thing  

实现：考虑到App 里需要异步初始化Thing，”启动“ 这个时机改到Splash 进行



**SHOW U CODE** ：

```kotlin
// App
class App{
    fun onCretate(){
        Log.d("创建 app")
        Thing.init()
    }
}
// SplashActivity
class SplashAct{
    fun preDoThing(){
        Thing.env = Thing.Env()
        Thing.preDo()
    }
}
// HomeActivity
class HomeAct{
    fun doThing(){
        Thing.do()
    }
}
// Thing
object Thing{
    var env? = null
    ...略 ...
    fun doThing(){
        env!!
    }
}
```



#### **异常：**

上线之后，发现26 行产生了一堆kotlin-npe   



#### **分析过程：**

经过打点核查，发现崩溃用户都没有进入过SplashAct，直接进入的HomeAct，且页面是重新创建的    

检查了一下没有类似sheme 的跳转打开方式  



？？？



既然事实如此，我们分析一波，首先HomeAct 必须是用户点击进入 

> 不然用户点击一个页面，却打开另一个页面，Android 要炸

我们知道只有已经打开过的情况，再次进入才能越过SplashAct  



于是方向设定在 内存不足，HomeAct 缩入后台，然后被回收，重新创建

嗯。开启了不保留活动页，复现了一下，没有出来



？？？



#### **解题方法 - 暴力篇：**

既然事实是有问题，而我复现一下没有出来

那么我就复现无数下 。。。 。。。

时间过去一个小时，复现了一次。

- 开发喊：测试过来，看我神之左手 -- 阿西吧，复现路径不管用

时间又过去了一个小时，又复现了一次。

- 开发跪：测试大佬，你看到就行，没有复现路径，不断尝试一下

时间再次过去了一个小时，再次复现并打印了日志。

- 开发笑：日志在此，你复现不了是你不够努力。



#### **解题方法 - 智力篇**

> 其实是在暴力篇的结论下，偏向性看的代码，如有错误，欢迎拍砖！！！

其实还是因为公司电脑没拉aosp，不得不刚，回家我就打开源码看起来（假装认真脸



我们知道应用启动流程 ： 

```java
appThread.scheduleLaunchActivity
    ⤵️
actThread.H.handleLaunchActivity -- 
    performLaunchActivity
    ⬇️
activityClientRecord.packageInfo.makeApplication  -- 
    instrumentation.callApplicationOnCreate(app)
```



现在的问题是application 在activity 创建的时候会重新被创建。

我们直奔主题，查看application 的构建：

```java
// makeApplication
if (mApplication != null) {
    return mApplication;
}
```

就是说，如果app 重建，需要activityClientRecord.packageInfo 重建  

那么就往回看它的创建

```java
// performLaunchActivity # getPackageInfo -- getPackageInfoNoCheck
WeakReference<LoadedApk> ref;
if (includeCode) {
    ref = mPackages.get(aInfo.packageName);
 } else {
    ref = mResourcePackages.get(aInfo.packageName);
}
```

以防万一，再往上一层，看下activityClientRecord 创建的地方有没有默认的packageInfo 

```java
// scheduleLaunchActivity
ActivityClientRecord r = new ActivityClientRecord();
...
```

可以看到是新建了ActivityClientRecord ，此时packageInfo 是空  



那么弱引用的packageInfo，在内存不够时就会释放，然后依据上述逻辑会重建  

当然前提是要确认其他引用的地方会被释放  

于是在上面的链路上，重新找一下相关的引用链

```java
//ActivityThread # mActivities -> activityClientRecord -> packageInfo
final ArrayMap<IBinder, ActivityClientRecord> mActivities
            = new ArrayMap<IBinder, ActivityClientRecord>();

// performLaunchActivity 
mActivities.put(r.token, r);
// performDestroyActivity
mActivities.remove(token);
```



所以简单讲，当页面被销毁，且程序某次minorGC 将packageinfo 重建了，就会重建application  



额，好吧，细想一下，有两个问题：

- 首先肯定是所有页面销毁，不是单个页面

答：packageInfo 虽然是被ActivityClientRecord 关联，但是其信息是共享的，即所有页面的activityClientRecord 都会持有。如此只要有一个页面存在，就不会被回收。

- 还有就是minorGC 就重建application 也太脆弱了吧

答：实际上并不是的，如果所有页面都销毁了，那对应的后台变化活动都应该结束，此时进程内的内存应该非常够用，就不应该有minorGC 产生  



#### 总结

假设页面具有前后性的操作，都可以重新写一下了。

