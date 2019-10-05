# Android NDK Tombstone/Crash 分析

##Android 开发中常见 Crash 的情况

在 Android 开发中，程序 Crash 分三种情况：

- 未捕获的异常
- ANR（Application Not Responding）
- 闪退（NDK 程序引发错误）

其中未捕获的异常根据 logcat 打印的堆栈信息很容易定位错误。

ANR错误也好查，Android规定，应用与用户进行交互时，如果5秒内没有响应用户的操作，则会引发ANR错误，并弹出一个系统提示框，让用户选择继续等待或立即关闭程序。并会在/data/anr目录下生成一个traces.txt文件，记录系统产生anr异常的堆栈和线程信息。

如果是闪退，这问题比较难查，通常是项目中用到了 NDK 引发某类致命的错误导致闪退。因为 NDK 是使用 C/C++ 来进行开发，熟悉 C/C++ 的程序员都知道，指针和内存管理是最重要也是最容易出问题的地方，稍有不慎就会遇到诸如内存地址访问错误、使用野指针、内存泄露、堆栈溢出、初始化错误、类型转换错误、数字除0等常见的问题，导致最后都是同一个结果：程序崩溃。它不会像在 Java 层产生的异常时弹出“xxx程序无响应，是否立即关闭”之类的提示框。当发生 NDK 错误后，logcat 打印出来的那堆日志根据看不懂，更别想从日志当中定位错误的根源。

首先，当 NDK 程序在发生 Crash 时，它会在路径 */data/tombstones/* 下产生导致程序 Crash 的文件 tombstone_xx。并且 Google 还在 NDK 包中为我们提供了一系列的调试工具，例如 **addr2line**、**objdump**、**ndk-stack**。

##Linux 信号机制

在介绍 Tombstone 之前，我们首先补充一个 Linux 信号机制的知识。

信号机制是 Linux 进程间通信的一种重要方式，Linux 信号一方面用于正常的进程间通信和同步，如任务控制(SIGINT, SIGTSTP,SIGKILL, SIGCONT，……)；另一方面，它还负责监控系统异常及中断。 当应用程序运行异常时， Linux 内核将产生错误信号并通知当前进程。 当前进程在接收到该错误信号后，可以有三种不同的处理方式。

- 忽略该信号。
- 捕捉该信号并执行对应的信号处理函数(signal handler)。
- 执行该信号的缺省操作(如 SIGSEGV， 其缺省操作是终止进程)。

当 Linux 应用程序在执行时发生严重错误，一般会导致程序 crash。其中，Linux 专门提供了一类 crash 信号，在程序接收到此类信号时，缺省操作是将 crash 的现场信息记录到 core 文件，然后终止进程。

Crash 信号列表：

| Signal  | Description                                                  |
| ------- | ------------------------------------------------------------ |
| SIGSEGV | Invalid memory reference.                                    |
| SIGBUS  | Access to an undefined portion of a memory object.           |
| SIGFPE  | Arithmetic operation error, like divide by zero.             |
| SIGILL  | Illegal instruction, like execute garbage or a privileged instruction |
| SIGSYS  | Bad system call.                                             |
| SIGXCPU | CPU time limit exceeded.                                     |
| SIGXFSZ | File size limit exceeded.                                    |

##Tombstone

Android Native 程序本质上就是一个 Linux 程序，因此当它在执行时发生严重错误，也会导致程序 crash，然后产生一个记录 crash 的现场信息的文件，而这个文件在 Android 系统中就是 tombstone 文件。tombstone 文件位于路径 */data/tombstones/* 下

~~~c
*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
Build fingerprint: 'Android-x86/android_x86/x86:5.1.1/LMY48W/woshijpf04211939:eng/test-keys'
Revision: '0'
ABI: 'x86'
pid: 1019, tid: 1019, name: surfaceflinger  >>> /system/bin/surfaceflinger <<<
signal 11 (SIGSEGV), code 1 (SEGV_MAPERR), fault addr 0x4
    eax a6265c06  ebx b7467d88  ecx b7631a22  edx a6265c06
    esi 00000000  edi b6867140
    xcs 00000073  xds 0000007b  xes 0000007b  xfs 00000000  xss 0000007b
    eip b745a639  ebp bfcfc1e8  esp bfcfc150  flags 00010282

backtrace:
    #00 pc 00006639  /system/lib/libui.so (android::Fence::waitForever(char const*)+41)
    #01 pc 00034b86  /system/lib/libsurfaceflinger.so
    #02 pc 0003229e  /system/lib/libsurfaceflinger.so
    #03 pc 0002cb9c  /system/lib/libgui.so (android::BufferQueue::ProxyConsumerListener::onFrameAvailable(android::BufferItem const&)+652)
    #04 pc 000342f4  /system/lib/libgui.so (android::BufferQueueProducer::queueBuffer(int, android::IGraphicBufferProducer::QueueBufferInput const&, android::IGraphicBufferProducer::QueueBufferOutput*)+2580)
    #05 pc 0004eafb  /system/lib/libgui.so (android::Surface::queueBuffer(ANativeWindowBuffer*, int)+411)
    #06 pc 0004ce06  /system/lib/libgui.so (android::Surface::hook_queueBuffer(ANativeWindow*, ANativeWindowBuffer*, int)+38)
    #07 pc 00014bc6  /system/lib/egl/libGLES_android.so
    #08 pc 00017f73  /system/lib/egl/libGLES_android.so (eglSwapBuffers+163)
    #09 pc 00015fdb  /system/lib/libEGL.so (eglSwapBuffers+203)
    #10 pc 000013ea  /system/lib/hw/hwcomposer.x86.so
    #11 pc 00034730  /system/lib/libsurfaceflinger.so
    #12 pc 000256d4  /system/lib/libsurfaceflinger.so
    #13 pc 00024bf4  /system/lib/libsurfaceflinger.so
    #14 pc 000236fb  /system/lib/libsurfaceflinger.so
    #15 pc 0002338a  /system/lib/libsurfaceflinger.so
    #16 pc 0001e0ff  /system/lib/libsurfaceflinger.so
    #17 pc 0001d9ce  /system/lib/libutils.so (android::Looper::pollInner(int)+926)
    #18 pc 0001db73  /system/lib/libutils.so (android::Looper::pollOnce(int, int*, int*, void**)+67)
    #19 pc 0001e561  /system/lib/libsurfaceflinger.so
    #20 pc 00022ce7  /system/lib/libsurfaceflinger.so (android::SurfaceFlinger::run()+39)
    #21 pc 00000ca3  /system/bin/surfaceflinger
    #22 pc 0001365a  /system/lib/libc.so (__libc_init+106)
    #23 pc 00000da8  /system/bin/surfaceflinger

stack:
         bfcfc110  00000000  
         bfcfc114  b6839270  
         bfcfc118  00000000  
         bfcfc11c  00000000  
         bfcfc120  b68394e0  
         bfcfc124  00000002  
         bfcfc128  00000002  
         bfcfc12c  b75d8185  /system/lib/libutils.so (android::RefBase::incStrong(void const*) const+53)
         bfcfc130  b6839270  
         bfcfc134  bfcfc1e8  [stack]
         bfcfc138  00000002  
         bfcfc13c  a6265c06  
         bfcfc140  b7467d88  /system/lib/libui.so
         bfcfc144  00000000  
         bfcfc148  b6867140  
         bfcfc14c  b745a639  /system/lib/libui.so (android::Fence::waitForever(char const*)+41)
    #00  bfcfc150  b683af18  
         bfcfc154  bfcfc1e8  [stack]
         bfcfc158  00000000  
         bfcfc15c  00000000  
         bfcfc160  00000000  
         bfcfc164  b683af18  
         bfcfc168  b75ec9c4  /system/lib/libutils.so
         bfcfc16c  b75d8285  /system/lib/libutils.so (android::RefBase::weakref_type::decWeak(void const*)+37)
         bfcfc170  00000000  
         bfcfc174  00000000  
         bfcfc178  00000000  
         bfcfc17c  00000000  
         bfcfc180  b7642968  /system/lib/libsurfaceflinger.so
         bfcfc184  bfcfc1e8  [stack]
         bfcfc188  b6867140  
         bfcfc18c  b7622b87  /system/lib/libsurfaceflinger.so
~~~

## tombstone 文件解析

tombstone 文件它主要由下面几部分组成：

- Build fingerprint
- Crashed process and PIDs
- Terminated signal and fault address
- CPU registers
- Call stack
- Stack content of each call

~~~
pid: 1019, tid: 1019, name: surfaceflinger  >>> /system/bin/surfaceflinger <<<
如果 pid 等于 tid ，那么就说明这个程序是在主线程中 Crash 掉的，name 的属性则表示 Crash 进程的名称以及在文件系统中位置。
~~~

~~~
signal 11 (SIGSEGV), code 1 (SEGV_MAPERR), fault addr 0x4
进程 Crash 的原因是因为程序产生了段错误的信号，访问了非法的内存空间，而访问的非法地址是 0x4。
~~~

## 定位 Crash 源码位置的工具

- addr2line    addr2line -f -e libui.so 00006639
- ndk-stack    ndk-stack -sym obj/local/x86/ -dump ~/android-x86-debug-log/tombstone_01
- objdump  objdump -S -D libc.so > deassmble_libc.txt 



							## 源码快读

bengin： com.android.server.am.ActivityManagerService.startObservingNativeCrashes 