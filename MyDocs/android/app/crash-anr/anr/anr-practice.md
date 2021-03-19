1、事件来不及处理的anr

**Input dispatching timed out (Waiting to send key event because the focused window has not finished processing all of the input events that were previously delivered to it. Outbound queue length: 0. Wait queue length: 30.)**



**Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago. Wait queue length: 36. Wait queue head age: 5506.7ms.), VisibleToUser**  

看了下堆栈的相同点都是项目里，列表滑动时的release 操作。里面会先调用 mediaPlayer.reset 再调用mediaPlayer.release。而也有一些地方没有调用reset，就没有anr 日志。

同时大部分的机型都是2G 的ram ，少量6~8G ram 的手机次数极少。

理论上也没啥必要reset，所以就去掉看一下



2、binder 交互  

Broadcast of Intent { act=android.intent.action.SCREEN_ON flg=0x50000010 }。

> 堆栈
>
> ```java
>  #00  pc 000000000001cbbb  /system/lib/libbinder.so (android::IPCThreadState::talkWithDriver(bool)+138)
>   #00  pc 000000000001d1d7  /system/lib/libbinder.so (android::IPCThreadState::waitForResponse(android::Parcel*, int*)+42)
>   #00  pc 000000000001d379  /system/lib/libbinder.so (android::IPCThreadState::transact(int, unsigned int, android::Parcel const&, android::Parcel*, unsigned int)+124)
>   #00  pc 000000000001860f  /system/lib/libbinder.so (android::BpBinder::transact(unsigned int, android::Parcel const&, android::Parcel*, unsigned int)+30)
>   #00  pc 0000000000087657  /system/lib/libandroid_runtime.so (???)
>   #00  pc 0000000000ca1071  /system/framework/arm/boot.oat (Java_android_os_BinderProxy_transactNative__ILandroid_os_Parcel_2Landroid_os_Parcel_2I+144)
>   at android.os.BinderProxy.transactNative (BinderProxy.java)
>   at android.os.BinderProxy.transact (BinderProxy.java:496)
>   at android.content.ContentProviderProxy.call (ContentProviderProxy.java:641)
>   at android.provider.Settings$NameValueCache.getStringForUser (Settings.java:1253)
> ```

会看到"GCDaemon" daemon prio=5 tid=3 Blocked

或者 "HeapTaskDaemon" daemon prio=5 tid=7 Blocked

或者"Heap thread pool worker thread 0" prio=5 tid=3 Native (still starting up)



3、