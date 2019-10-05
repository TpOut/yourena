##Linux内核编程重难点知识

***写在前面的话***:Linux会有多种变形内核版本，比如BSD、Solaries、HP-Unix等等，我也不可能带你全部学完，这个要学至少需要一定时间年限的积累，我们现在没那个必要全部学习所有的内核库函数。我一直说了学什么？怎么学？这是希望带给大家的，不希望大家成为知识的奴隶。

###C标准函数与系统函数的区别

​	系统调用，我们可以理解是操作系统为用户提供的一系列操作的接口（API），这些接口提供了对系统硬件设备功能的操作。这么说可能会比较抽象，举个例子，我们最熟悉的 `hello world` 程序会在屏幕上打印出信息。程序中调用了 `printf()` 函数，而库函数 `printf` 本质上是调用了系统调用 `write()` 函数，实现了终端信息的打印功能。

​	库函数可以理解为是对系统调用的一层封装。系统调用作为内核提供给用户程序的接口，它的执行效率是比较高效而精简的，但有时我们需要对获取的信息进行更复杂的处理，或更人性化的需要，我们把这些处理过程封装成一个函数再提供给程序员，更方便于程序猿编码。

![image-20181121155301473](/Users/wuyue/Library/Application Support/typora-user-images/image-20181121155301473.png)

***系统调用意义***:

- 避免了用户直接对底层硬件进行编程。比如最简单的`hello world`程序是将信息打印到终端，终端对系统来说是硬件资源，如果没有系统调用，用户程序需要自己编写终端设备的驱动，以及控制终端如何显示的代码。
- 隐藏背后的技术细节。比如读写文件，如果使用了系统调用，用户程序无须关心数据在磁盘的哪个磁道和扇区，以及数据要加载到内存什么位置。
- 保证系统的安全性和稳定性。要知道用户程序是不能直接操作内核地址空间的，比如一个刚出道的程序猿，让他直接去访问内核底层的数据，那么内核系统的安全性就无法保证。而系统调用的功能是由内核来实现，用户只需要调用接口，无需关心细节，也避免了系统的安全隐患。
- 方便程序的移植性。如果针对一个系统资源的功能操作比如 write()，大家都按照自己思路去实现这个功能，那么我们写出来的程序的移植性就会非常差。

###线程相关

**POSIX线程**（英语：POSIX Threads，常被缩写为Pthreads）是[POSIX](https://zh.wikipedia.org/wiki/POSIX)的[线程](https://zh.wikipedia.org/wiki/%E7%BA%BF%E7%A8%8B)标准，定义了创建和操纵线程的一套[API](https://zh.wikipedia.org/wiki/Application_programming_interface)。实现POSIX 线程标准的库常被称作**Pthreads**，一般用于[Unix-like](https://zh.wikipedia.org/wiki/Unix-like) POSIX 系统，如[Linux](https://zh.wikipedia.org/wiki/Linux)、 [Solaris](https://zh.wikipedia.org/wiki/Solaris)。但是[Microsoft Windows](https://zh.wikipedia.org/wiki/Microsoft_Windows)上的实现也存在，例如直接使用Windows API实现的第三方库pthreads-w32；而利用Windows的SFU/SUA子系统，则可以使用微软提供的一部分原生POSIX API。

关于API的总结：

Pthreads API中大致共有100个函数调用，全都以"pthread_"开头，并可以分为四类：

- 线程管理，例如创建线程，等待(join)线程，查询线程状态等。
- 互斥锁（Mutex）：创建、摧毁、锁定、解锁、设置属性等操作
- 条件变量（Condition Variable）：创建、摧毁、等待、通知、设置与查询属性等操作
- 使用了互斥锁的线程间的[同步](https://zh.wikipedia.org/wiki/%E5%90%8C%E6%AD%A5_(%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6))管理

我们在NDK的编程中，往往需要大量的使用创建线程。在音视频的编码中也需要通过线程来操作相关的解码数据。

在linux中查看相关的帮助手册，如有没有需要自己安装：

~~~shell
man -k pthread
yum -y install man-pages centos
sudo apt-get install manpages-de manpages-de-dev  manpages-dev glibc-doc manpages-posix-dev manpages-posix  ubuntu
~~~

实战：演示pthread相关的线程处理代码。Linux_project

~~~c
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

void* fun(void* arg){
    char* str = (char*)arg;
    int i=0;
    for (; i < 10; i++) {
        printf("%s线程的数据是,i是%d\n",str,i);
    }
    return 1;
}
//void main(){
//    printf("main thread");
//    pthread_t* tid;
//    pthread_create(&tid,NULL,fun,"1");
//    //sleep(100);
//    int* rval;
//    pthread_join(tid,&rval);
//    printf("rval数据是%d",rval); //注意在这里需要和学员说下Linux不同编辑器的区分 void*——————》int
//}

~~~

 线程互斥加锁演示：

~~~c
int i=0;
pthread_mutex_t mutex;

void* pthread_fun(void * arg){
    //加锁
    pthread_mutex_lock(&mutex);
    char* str = (char*)arg;
    for(;i<7;i++){
        printf("线程名字是%s,i的数值是%d\n",str,i);
        sleep(1);
    }
    i = 0;
    pthread_mutex_unlock(&mutex);
}
//void main(){
//    pthread_t tida,tidb;
//    //初始化mutex
//    pthread_mutex_init(&mutex,NULL);
//    pthread_create(&tida,NULL,pthread_fun,"thread-a");
//    pthread_create(&tidb,NULL,pthread_fun,"thread-b");
//
//    pthread_join(tida,NULL);
//    pthread_join(tidb,NULL);
//
//    //销毁锁
//    pthread_mutex_destroy(&mutex);
//}
~~~

生产消费者模式：（条件变量） 图像下载----音视频解码-----都需要这种设计模式 思考下你怎么写的  消息队列的使用

~~~htm 
pthread_cond_broadcast (3p) - broadcast or signal a condition
pthread_cond_destroy (3p) - destroy and initialize condition variables
pthread_cond_init (3p) - destroy and initialize condition variables
pthread_cond_signal (3p) - broadcast or signal a condition
pthread_cond_timedwait (3p) - wait on a condition
pthread_cond_wait (3p) - wait on a condition
~~~

~~~c
int ready = 0;
pthread_mutex_t mutex;
pthread_cond_t hasProduct;

void* fun_product(void* argc){
    char * str = (char*)argc;
    //条件变量
    while (1){
        pthread_mutex_lock(&mutex);
        ready++;
        pthread_cond_signal(&hasProduct);
        fflush(NULL); //注意这里的fflush signal会阻塞屏幕输出
        printf("有数据了，你可以消费了===>%s\n",str);
        pthread_mutex_unlock(&mutex);
        sleep(3);
    }
}

void* fun_consumer(void* argc){
    while (1){
        pthread_mutex_lock(&mutex);
        while (ready==0){
            pthread_cond_wait(&hasProduct,&mutex);
        }
        ready--;
        printf("我正在消费\n");
        sleep(1);
        pthread_mutex_unlock(&mutex);
    }
}

void main(){
    pthread_t tida,tidb;
    pthread_mutex_init(&mutex,NULL);
    pthread_cond_init(&hasProduct,NULL);

    pthread_create(&tida,NULL,fun_product,"thread-a");
    pthread_create(&tidb,NULL,fun_consumer,"thread-b");

    pthread_join(tida,NULL);
    pthread_join(tidb,NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&hasProduct);
}
~~~

实战案例：AndroidStudio使用pthread(参见android项目pthread)

~~~c++
#include <jni.h>
#include <string>

#include "pthread.h"
#include "AndroidLog.h"

pthread_t pthread;
void* callBack(void* data){
    LOGI("创建线程fromC++");
    pthread_exit(&pthread);
}

extern "C"
JNIEXPORT void JNICALL
Java_pthread_jesson_com_pthread_PthreadDemo_thread(JNIEnv *env, jobject instance) {
    pthread_create(&pthread,NULL,callBack,NULL);
}
~~~

实战案列：生产者和消费者编程模型：(音视频编解码主要用这个来操作)

~~~c++
#include <jni.h>
#include <string>

#include "pthread.h"
#include "AndroidLog.h"
#include "queue" //处理队列
#include <unistd.h>
using namespace std;

pthread_t pthread;
pthread_t product;
pthread_t customer;
pthread_mutex_t mutex;//锁
pthread_cond_t cond;
queue<int> queue_info;
bool exitFlag  = false;

void* callBack(void* data){
    LOGI("创建线程fromC++");
    pthread_exit(&pthread);
}


void* productCallBack(void* data){
    LOGI("product...");
    while (!exitFlag){
        pthread_mutex_lock(&mutex);
        queue_info.push(1);
        LOGI("生产者生产了一个产品，通知消费者来消费产品，产品数量是%d",queue_info.size());
        pthread_cond_signal(&cond);
        pthread_mutex_unlock(&mutex);
        sleep(5);
    }
    pthread_exit(&product);
}


void* customerCallBack(void* data){
    LOGI("customer...");
    while (!exitFlag){
        pthread_mutex_lock(&mutex);
        if(queue_info.size()>0){
            queue_info.pop();
            LOGI("消费者消费了一个产品，产品数量还剩下%d",queue_info.size());
        }else{
            LOGI("没有产品可消费，我在等待");
            pthread_cond_wait(&cond,&mutex);
        }

        pthread_mutex_unlock(&mutex);
        usleep(1000*500);
    }
    pthread_exit(&customer);
}

extern "C"
JNIEXPORT void JNICALL
Java_pthread_jesson_com_pthread_PthreadDemo_thread(JNIEnv *env, jobject instance) {
    pthread_create(&pthread,NULL,callBack,NULL);
}

extern "C"
JNIEXPORT void JNICALL
Java_pthread_jesson_com_pthread_PthreadDemo_mutexthread(JNIEnv *env, jobject instance) {
    //测试队列
    for (int i = 0; i < 10; ++i) {
        queue_info.push(i);
    }
    pthread_mutex_init(&mutex,NULL);
    pthread_cond_init(&cond,NULL);
    //开启生产者和消费者线程
    pthread_create(&product,NULL,productCallBack,NULL);
    pthread_create(&customer,NULL,customerCallBack,NULL);
}
~~~

###进程线程相关

### PCB

进程控制块的作用是使一个在多道程序环境下不能独立运行的程序（含数据），成为一个能独立运行的基本单位，一个能与其它进程并发执行的进程。或者说，OS是根据PCB来对并发执行的进程进行控制和管理的。 PCB通常是系统内存占用区中的一个连续存区，它存放着操作系统用于描述进程情况及控制进程运行所需的全部信息，它使一个在多道程序环境下不能独立运行的程序成为一个能独立运行的基本单位，一个能与其他进程并发执行的进程。

我们知道，每个进程在内核中都有一个进程控制块（PCB）来维护进程相关的信息，Linux内核的进程控制块是task_struct结构体。

/usr/src/linux-headers-3.16.0-30/include/linux/sched.h文件中可以查看struct task_struct 结构体定义。其内部成员有很多，我们重点掌握以下部分即可：

\* 进程id。系统中每个进程有唯一的id，在C语言中用pid_t类型表示，其实就是一个非负整数。

\* 进程的状态，有就绪、运行、挂起、停止等状态。

\* 进程切换时需要保存和恢复的一些CPU寄存器。

\* 描述虚拟地址空间的信息。

\* 描述控制终端的信息。

\* 当前工作目录（Current Working Directory）。

\* umask掩码。

\* 文件描述符表，包含很多指向file结构体的指针。

\* 和信号相关的信息。

\* 用户id和组id。

\* 会话（Session）和进程组。

\* 进程可以使用的资源上限（Resource Limit。

进程基本的状态有5种。分别为初始态，就绪态，[运行态](http://baike.baidu.com/subview/1730379/1730379.htm)，挂起态与终止态。其中初始态为进程准备阶段，常与就绪态结合来看。Linux内核中进程用`task_struct`结构体表示，称为进程描述符，该结构体相对比较复杂，有几百行代码，记载着该进程相关的所有信息，比如进程地址空间，进程状态，打开的文件等。对内核而言，进程或者线程都称为任务task。内核将所有进程放入一个双向循环链表结构的任务列表(task list)。

![task_struct](https://ws4.sinaimg.cn/large/006tNc79ly1fz2g8gbha6j30jq0gxgml.jpg)

关于创建：

- Linux进程创建： 通过fork()系统调用创建进程
- Linux用户级线程创建：通过pthread库中的pthread_create()创建线程
- Linux内核线程创建： 通过kthread_create()创建内核线程

内核线程：没有独立的地址空间，即mm指向NULL。这样的线程只在内核运行，不会切换到用户空间。 所有内核线程都是由kthreadd作为内核线程的祖师爷，衍生而来的。

代码实践：

~~~C
int var = 34;
int main(void)
{
    pid_t pid;
    pid = fork();
    if (pid == -1 ) {
        perror("fork");
        exit(1);
    } else if (pid > 0) {
        sleep(2);
        var = 55;
        printf("I'm parent pid = %d, parentID = %d, var = %d\n", getpid(), getppid(), var);
    } else if (pid == 0) {
        var = 100;
        printf("child  pid = %d, parentID=%d, var = %d\n", getpid(), getppid(), var);
    }
    printf("var = %d\n", var);
    return 0;
}
~~~

实践1: 执行代码`Thread.sleep()` 底层到底做了什么？源码分析

~~~html
http://hg.openjdk.java.net/jdk8u/jdk8u60/jdk/file/afbc08ea922b/src/share/native/java/lang/Object.c
http://hg.openjdk.java.net/jdk7/jdk7/hotspot/file/9b0ca45cd756/src/share/vm/prims/jvm.cpp#l2835
~~~

### mmap

~~~
内存映射，简而言之就是将用户空间的一段内存区域映射到内核空间，映射成功后，用户对这段内存区域的修改可以直接反映到内核空间，同样，内核空间对这段区域的修改也直接反映用户空间。那么对于内核空间<---->用户空间两者之间需要大量数据传输等操作的话效率是非常高的。
~~~

![image-20190131144230243](https://ws1.sinaimg.cn/large/006tNc79ly1fzprhoyp1rj30v80nmaf6.jpg)

​	mmap系统调用并不是完全为了用于共享内存而设计的。它本身提供了不同于一般对普通文件的访问方式，进程可以像读写内存一样对普通文件的操作。而Posix或系统V的共享内存IPC则纯粹用于共享目的，当然mmap()实现共享内存也是其主要应用之一。

​	mmap系统调用使得进程之间通过映射同一个普通文件实现共享内存。普通文件被映射到进程地址空间后，进程可以像访问普通内存一样对文件进行访问，不必再调用read()，write（）等操作。mmap并不分配空间, 只是将文件映射到调用进程的地址空间里（但是会占掉你的 virutal memory）, 然后你就可以用memcpy等操作写文件, 而不用write()了.写完后，内存中的内容并不会立即更新到文件中，而是有一段时间的延迟，你可以调用msync()来显式同步一下, 这样你所写的内容就能立即保存到文件里了.这点应该和驱动相关。 不过通过mmap来写文件这种方式没办法增加文件的长度, 因为要映射的长度在调用mmap()的时候就决定了.如果想取消内存映射，可以调用munmap()来取消内存映射。

~~~html
void * mmap(void *start, size_t length, int prot , int flags, int fd, off_t offset)
start：要映射到的内存区域的起始地址，通常都是用NULL（NULL即为0）。NULL表示由内核来指定该内存地址
length：要映射的内存区域的大小
prot：期望的内存保护标志，不能与文件的打开模式冲突。是以下的某个值，可以通过or运算合理地组合在一起
     PROT_EXEC //页内容可以被执行
     PROT_READ  //页内容可以被读取
     PROT_WRITE //页可以被写入
     PROT_NONE  //页不可访问
fd：文件描述符（由open函数返回）

flags：指定映射对象的类型，映射选项和映射页是否可以共享。它的值可以是一个或者多个以下位的组合体
MAP_FIXED ：使用指定的映射起始地址，如果由start和len参数指定的内存区重叠于现存的映射空间，重叠部分将会被丢弃。如果指定的起始地址不可用，操作将会失败。并且起始地址必须落在页的边界上。
MAP_SHARED ：对映射区域的写入数据会复制回文件内, 而且允许其他映射该文件的进程共享。
MAP_PRIVATE ：建立一个写入时拷贝的私有映射。内存区域的写入不会影响到原文件。这个标志和以上标志是互斥的，只能使用其中一个。
MAP_DENYWRITE ：这个标志被忽略。
MAP_EXECUTABLE ：同上
MAP_NORESERVE ：不要为这个映射保留交换空间。当交换空间被保留，对映射区修改的可能会得到保证。当交换空间不被保留，同时内存不足，对映射区的修改会引起段违例信号。
MAP_LOCKED ：锁定映射区的页面，从而防止页面被交换出内存。
MAP_GROWSDOWN ：用于堆栈，告诉内核VM系统，映射区可以向下扩展。
MAP_ANONYMOUS ：匿名映射，映射区不与任何文件关联。
MAP_ANON ：MAP_ANONYMOUS的别称，不再被使用。
MAP_FILE ：兼容标志，被忽略。
MAP_32BIT ：将映射区放在进程地址空间的低2GB，MAP_FIXED指定时会被忽略。当前这个标志只在x86-64平台上得到支持。
MAP_POPULATE ：为文件映射通过预读的方式准备好页表。随后对映射区的访问不会被页违例阻塞。
MAP_NONBLOCK ：仅和MAP_POPULATE一起使用时才有意义。不执行预读，只为已存在于内存中的页面建立页表入口


EACCES：访问出错
EAGAIN：文件已被锁定，或者太多的内存已被锁定
EBADF：fd不是有效的文件描述词
EINVAL：一个或者多个参数无效
ENFILE：已达到系统对打开文件的限制
ENODEV：指定文件所在的文件系统不支持内存映射
ENOMEM：内存不足，或者进程已超出最大内存映射数量
EPERM：权能不足，操作不允许
ETXTBSY：已写的方式打开文件，同时指定MAP_DENYWRITE标志
SIGSEGV：试着向只读区写入
SIGBUS：试着访问不属于进程的内存区

~~~

android 面试：

~~~
常规文件操作为了提高读写效率和保护磁盘，使用了页缓存机制，这是由OS控制的。这样造成读文件时需要先将文件页从磁盘拷贝到页缓存中，由于页缓存处在内核空间，不能被用户进程直接寻址，所以还需要将页缓存中数据页再次拷贝到内存对应的用户空间中。这样，通过了两次数据拷贝过程，才能完成进程对文件内容的获取任务。写操作也是一样，待写入的buffer在内核空间不能直接访问，必须要先拷贝至内核空间对应的主存，再写回磁盘中（延迟写回），也是需要两次数据拷贝。
而使用mmap操作文件中，由于不需要经过内核空间的数据缓存，只使用一次数据拷贝，就从磁盘中将数据传入内存的用户空间中，供进程使用。
mmap的关键点是实现了用户空间和内核空间的数据直接交互而省去了空间不同数据不通的繁琐过程。因此mmap效率更高。
~~~

https://github.com/pqpo/Log4a/blob/7d92dc4ad244c8af80d0c5ce6e02d7bff53277b8/librarylog4a/src/main/cpp/log4a-lib.cpp

## Cmake&make&makefile

- make与makefile

我们在处理命令时，如果单条命令可以直接执行，但是命令比较多的时候就没办法挨个手动调用，这时候可以写到*makefile*文件里，通过*make*命令批量处理。可以把*make*理解为批处理工具，批量处理*makefile*中的命令。

- cmake和CMakeLists.txt

当命令比较多的时候，我们把命令写到*makefile*中，通过*make*程序批量处理。但是*makefile*本身也比较难挨个手写，这时候就出现了自动生成*makefile*的工具*cmake*。也就是通过*cmake*我们可以很方便的生成*makefile*文件。那么问题来了，*cmake*依据什么来生成*makefile*文件呢，很显然，*cmake*通过*CMakeLists.txt*文件生成*makefile*文件。

![image-20190111111346622](https://ws2.sinaimg.cn/large/006tNc79ly1fz2h2cn5p8j310806ygm2.jpg)

~~~html
1. make 是用来执行Makefile的
2. Makefile是类unix环境下(比如Linux)的类似于批处理的"脚本"文件。其基本语法是: 目标+依赖+命令，只有在目标文件不存在，或目标比依赖的文件更旧，命令才会被执行。由此可见，Makefile和make可适用于任意工作，不限于编程。比如，可以用来管理latex。
3. Makefile+make可理解为类unix环境下的项目管理工具，但它太基础了，抽象程度不高，而且在windows下不太友好(针对visual studio用户)，于是就有了跨平台项目管理工具cmake
4. cmake是跨平台项目管理工具，它用更抽象的语法来组织项目。虽然，仍然是目标，依赖之类的东西，但更为抽象和友好，比如你可用math表示数学库，而不需要再具体指定到底是math.dll还是libmath.so，在windows下它会支持生成visual studio的工程，在linux下它会生成Makefile，甚至它还能生成eclipse工程文件。也就是说，从同一个抽象规则出发，它为各个编译器定制工程文件。
5. cmake是抽象层次更高的项目管理工具，cmake命令执行的CMakeLists.txt文件
6. qmake是Qt专用的项目管理工具，对应的工程文件是*.pro，在Linux下面它也会生成Makefile，当然，在命令行下才会需要手动执行qmake，完全可以在qtcreator这个专用的IDE下面打开*.pro文件，使用qmake命令的繁琐细节不用你管了。总结一下，make用来执行Makefile，cmake用来执行CMakeLists.txt，qmake用来处理*.pro工程文件。Makefile的抽象层次最低，cmake和qmake在Linux等环境下最后还是会生成一个Makefile。cmake和qmake支持跨平台，cmake的做法是生成指定编译器的工程文件，而qmake完全自成体系。具体使用时，Linux下，小工程可手动写Makefile，大工程用automake来帮你生成Makefile，要想跨平台，就用cmake。如果GUI用了Qt，也可以用qmake+*.pro来管理工程，这也是跨平台的。当然，cmake中也有针对Qt的一些规则，并代替qmake帮你将qt相关的命令整理好了。
~~~

![image-20190111111516064](https://ws3.sinaimg.cn/large/006tNc79ly1fz2h3wcgg7j30ye0kwwfk.jpg)

### Cmake

~~~html
https://developer.android.com/ndk/guides/cmake
https://cmake.org/cmake/help/v3.0/manual/cmake-variables.7.html
https://cmake.org/Wiki/CMake_Useful_Variables
https://www.hahack.com/codes/cmake/#stq=&stp=0
https://cmake.org/cmake-tutorial/
https://github.com/TheErk/CMake-tutorial/blob/master/examples/CMake-autobuild-v2.cmake
Android资料：
https://developer.android.com/studio/projects/add-native-code#create-cmake-script
~~~

一些注意的配置：

配置模板：

~~~
#cmake verson，指定cmake版本
cmake_minimum_required(VERSION 3.2)

#project name，指定项目的名称，一般和项目的文件夹名称对应
PROJECT(main)

#定义变量
SET(PROJECT_NAME main)

add_definitions("-g")
#add_definitions("-O3")
add_definitions("-Wall")
add_definitions("-Wextra")

#引入c++11
SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
#SET(CMAKE_SHARED_LINKER_FLAGS "-shared")

# 判断当前操作系统平台
#IF (WIN32)
#    MESSAGE(STATUS "Now is windows")
#ELSEIF (APPLE)
#    MESSAGE(STATUS "Now is Apple systens.")
#ELSEIF (UNIX)
#    MESSAGE(STATUS "Now is UNIX-like OS's. Including aPPLE os x  and CygWin")
#ENDIF ()

# or
#IF (CMAKE_SYSTEM_NAME MATCHES "Linux")
#    MESSAGE(STATUS "current platform: Linux ")
#ELSEIF (CMAKE_SYSTEM_NAME MATCHES "Windows")
#    MESSAGE(STATUS "current platform: Windows")
#ELSEIF (CMAKE_SYSTEM_NAME MATCHES "FreeBSD")
#    MESSAGE(STATUS "current platform: FreeBSD")
#ELSE ()
#    MESSAGE(STATUS "other platform: ${CMAKE_SYSTEM_NAME}")
#ENDIF (CMAKE_SYSTEM_NAME MATCHES "Linux")


#头文件目录
INCLUDE_DIRECTORIES(
    /usr/include
    /usr/local/curl/include
    )

#源文件目录
AUX_SOURCE_DIRECTORY(. SRC_DIR)

#库文件目录
#SET(LIBRARYS pcre
#    /usr/local/curl/lib/libcurl.a
#    /usr/local/c-ares/lib/libcares.a)

#生成的库文件名
#ADD_LIBRARY(demo.so SHARED ${SRC_DIR})

#设置环境变量，编译用到的源文件全部都要放到这里，否则编译能够通过，但是执行的时候会出现各种问题，比如"symbol lookup error xxxxx , undefined symbol"
SET(TEST_MATH
    ${SRC_DIR}
    )

#添加要编译的可执行文件
ADD_EXECUTABLE(${PROJECT_NAME} ${TEST_MATH})

#添加可执行文件所需要的库，比如我们用到了libm.so（命名规则：lib+name+.so），就添加该库的名称
# TARGET_LINK_LIBRARIES(${PROJECT_NAME} m)

#安装目录
#INSTALL(TARGETS demo.so LIBRARY DESTINATION usr/local/demo)

~~~

怎么指定 C++标准？

~~~gradle
externalNativeBuild {
  cmake {
    cppFlags "-frtti -fexceptions -std=c++14"
    arguments '-DANDROID_STL=c++_shared'
  }
}
~~~

add_library 如何编译一个目录中所有源文件？

~~~html
# 查找所有源码 并拼接到路径列表
aux_source_directory(${CMAKE_HOME_DIRECTORY}/src/api SRC_LIST)
aux_source_directory(${CMAKE_HOME_DIRECTORY}/src/core CORE_SRC_LIST)
list(APPEND SRC_LIST ${CORE_SRC_LIST})
add_library(native-lib SHARED ${SRC_LIST})
~~~

怎么调试 CMakeLists.txt 中的代码？

~~~html
cmake_minimum_required(VERSION 3.4.1)
message(STATUS "execute CMakeLists")
...
在 .externalNativeBuild/cmake/debug/{abi}/cmake_build_output.txt 中查看 log。
~~~

什么时候 CMakeLists.txt 里面会执行？

~~~
在 sync 的时候会执行。执行一次后会生成 makefile 的文件缓存之类的东西放在 externalNativeBuild 中。所以如果 CMakeLists.txt 中没有修改的话再次同步好像是不会重新执行的。（或者删除 .externalNativeBuild 目录）
真正编译的时候好像只是读取.externalNativeBuild 目录中已经解析好的 makefile 去编译。不会再去执行 CMakeLists.txt。
~~~

语法：

~~~groovy

https://github.com/googlesamples/android-ndk

cmake_minimum_required(VERSION 3.4.1)

# configure import libs
set(distribution_DIR ${CMAKE_SOURCE_DIR}/../../../../distribution)

# 创建一个静态库 lib_gmath 直接引用libgmath.a
add_library(lib_gmath STATIC IMPORTED)
set_target_properties(lib_gmath PROPERTIES IMPORTED_LOCATION
    ${distribution_DIR}/gmath/lib/${ANDROID_ABI}/libgmath.a)

# 创建一个动态库 lib_gperf 直接引用libgperf.so
add_library(lib_gperf SHARED IMPORTED)
set_target_properties(lib_gperf PROPERTIES IMPORTED_LOCATION
    ${distribution_DIR}/gperf/lib/${ANDROID_ABI}/libgperf.so)

# build application's shared lib
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

# 创建库 hello-libs
add_library(hello-libs SHARED
            hello-libs.cpp)

# 加入头文件
target_include_directories(hello-libs PRIVATE
                           ${distribution_DIR}/gmath/include
                           ${distribution_DIR}/gperf/include)
                           
                           
# 找到预编译库 log_lib 并link到我们的动态库 native-lib中
find_library( # Sets the name of the path variable.
              log-lib
              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# hello-libs库链接上 lib_gmath 和 lib_gperf
target_link_libraries(hello-libs
                      android
                      lib_gmath
                      lib_gperf
                      log)

~~~

实战：接口进行签名验证以防止被刷 openssl

HmacSHA1签名：HMAC是哈希运算消息认证码 (Hash-based Message Authentication Code)，HMAC运算利用哈希算法，以一个密钥和一个消息为输入，生成一个消息摘要作为输出。HMAC-SHA1签名算法是一种常用的签名算法，用于对一段信息进行生成签名摘要。

需要注意哪些问题：

- hmac-sha1可以认为是不可逆的加密算法，sk只能保存在服务器，不能通过任何途径传输给客户端

- 服务器发一个签名给客户端，此后服务器通过签名来校验客户端的访问权限

- msg通过明文传输，然后服务器使用sk和传输的msg生成签名进行校验 

-  客户端和服务器通过生成的签名是否一致判断校验是否通过

具体编译：

环境执行脚本：

~~~bash
#!/bin/bash
# Cross-compile environment for Android on ARMv7 and x86
#
# Contents licensed under the terms of the OpenSSL license
# http://www.openssl.org/source/license.html
#
# See http://wiki.openssl.org/index.php/FIPS_Library_and_Android
#   and http://wiki.openssl.org/index.php/Android

#####################################################################

# Set ANDROID_NDK_ROOT to you NDK location. For example,
# /opt/android-ndk-r8e or /opt/android-ndk-r9. This can be done in a
# login script. If ANDROID_NDK_ROOT is not specified, the script will
# try to pick it up with the value of _ANDROID_NDK_ROOT below. If
# ANDROID_NDK_ROOT is set, then the value is ignored.
# _ANDROID_NDK="android-ndk-r8e"
_ANDROID_NDK="android-ndk-r15c"
# _ANDROID_NDK="android-ndk-r10"

# Set _ANDROID_EABI to the EABI you want to use. You can find the
# list in $ANDROID_NDK_ROOT/toolchains. This value is always used.
# _ANDROID_EABI="x86-4.6"
# _ANDROID_EABI="arm-linux-androideabi-4.6"
_ANDROID_EABI="arm-linux-androideabi-4.9"

# Set _ANDROID_ARCH to the architecture you are building for.
# This value is always used.
# _ANDROID_ARCH=arch-x86
_ANDROID_ARCH=arch-arm

# Set _ANDROID_API to the API you want to use. You should set it
# to one of: android-14, android-9, android-8, android-14, android-5
# android-4, or android-3. You can't set it to the latest (for
# example, API-17) because the NDK does not supply the platform. At
# Android 5.0, there will likely be another platform added (android-22?).
# This value is always used.
# _ANDROID_API="android-14"
_ANDROID_API="android-19"
# _ANDROID_API="android-19"

#####################################################################

# If the user did not specify the NDK location, try and pick it up.
# We expect something like ANDROID_NDK_ROOT=/opt/android-ndk-r8e
# or ANDROID_NDK_ROOT=/usr/local/android-ndk-r8e.

if [ -z "$ANDROID_NDK_ROOT" ]; then

  _ANDROID_NDK_ROOT=""
  if [ -z "$_ANDROID_NDK_ROOT" ] && [ -d "/usr/local/$_ANDROID_NDK" ]; then
    _ANDROID_NDK_ROOT="/usr/local/$_ANDROID_NDK"
  fi

  if [ -z "$_ANDROID_NDK_ROOT" ] && [ -d "/opt/$_ANDROID_NDK" ]; then
    _ANDROID_NDK_ROOT="/opt/$_ANDROID_NDK"
  fi

  if [ -z "$_ANDROID_NDK_ROOT" ] && [ -d "$HOME/$_ANDROID_NDK" ]; then
    _ANDROID_NDK_ROOT="$HOME/$_ANDROID_NDK"
  fi

  if [ -z "$_ANDROID_NDK_ROOT" ] && [ -d "$PWD/$_ANDROID_NDK" ]; then
    _ANDROID_NDK_ROOT="$PWD/$_ANDROID_NDK"
  fi

  # If a path was set, then export it
  if [ ! -z "$_ANDROID_NDK_ROOT" ] && [ -d "$_ANDROID_NDK_ROOT" ]; then
    export ANDROID_NDK_ROOT="$_ANDROID_NDK_ROOT"
  fi
fi

# Error checking
# ANDROID_NDK_ROOT should always be set by the user (even when not running this script)
# http://groups.google.com/group/android-ndk/browse_thread/thread/a998e139aca71d77
if [ -z "$ANDROID_NDK_ROOT" ] || [ ! -d "$ANDROID_NDK_ROOT" ]; then
  echo "Error: ANDROID_NDK_ROOT is not a valid path. Please edit this script."
  # echo "$ANDROID_NDK_ROOT"
  # exit 1
fi

# Error checking
if [ ! -d "$ANDROID_NDK_ROOT/toolchains" ]; then
  echo "Error: ANDROID_NDK_ROOT/toolchains is not a valid path. Please edit this script."
  # echo "$ANDROID_NDK_ROOT/toolchains"
  # exit 1
fi

# Error checking
if [ ! -d "$ANDROID_NDK_ROOT/toolchains/$_ANDROID_EABI" ]; then
  echo "Error: ANDROID_EABI is not a valid path. Please edit this script."
  # echo "$ANDROID_NDK_ROOT/toolchains/$_ANDROID_EABI"
  # exit 1
fi

#####################################################################

# Based on ANDROID_NDK_ROOT, try and pick up the required toolchain. We expect something like:
# /opt/android-ndk-r83/toolchains/arm-linux-androideabi-4.7/prebuilt/linux-x86_64/bin
# Once we locate the toolchain, we add it to the PATH. Note: this is the 'hard way' of
# doing things according to the NDK documentation for Ice Cream Sandwich.
# https://android.googlesource.com/platform/ndk/+/ics-mr0/docs/STANDALONE-TOOLCHAIN.html

ANDROID_TOOLCHAIN=""
for host in "linux-x86_64" "linux-x86" "darwin-x86_64" "darwin-x86"
do
  if [ -d "$ANDROID_NDK_ROOT/toolchains/$_ANDROID_EABI/prebuilt/$host/bin" ]; then
    ANDROID_TOOLCHAIN="$ANDROID_NDK_ROOT/toolchains/$_ANDROID_EABI/prebuilt/$host/bin"
    break
  fi
done

# Error checking
if [ -z "$ANDROID_TOOLCHAIN" ] || [ ! -d "$ANDROID_TOOLCHAIN" ]; then
  echo "Error: ANDROID_TOOLCHAIN is not valid. Please edit this script."
  # echo "$ANDROID_TOOLCHAIN"
  # exit 1
fi

case $_ANDROID_ARCH in
	arch-arm)	  
      ANDROID_TOOLS="arm-linux-androideabi-gcc arm-linux-androideabi-ranlib arm-linux-androideabi-ld"
	  ;;
	arch-x86)	  
      ANDROID_TOOLS="i686-linux-android-gcc i686-linux-android-ranlib i686-linux-android-ld"
	  ;;	  
	*)
	  echo "ERROR ERROR ERROR"
	  ;;
esac

for tool in $ANDROID_TOOLS
do
  # Error checking
  if [ ! -e "$ANDROID_TOOLCHAIN/$tool" ]; then
    echo "Error: Failed to find $tool. Please edit this script."
    # echo "$ANDROID_TOOLCHAIN/$tool"
    # exit 1
  fi
done

# Only modify/export PATH if ANDROID_TOOLCHAIN good
if [ ! -z "$ANDROID_TOOLCHAIN" ]; then
  export ANDROID_TOOLCHAIN="$ANDROID_TOOLCHAIN"
  export PATH="$ANDROID_TOOLCHAIN":"$PATH"
fi

#####################################################################

# For the Android SYSROOT. Can be used on the command line with --sysroot
# https://android.googlesource.com/platform/ndk/+/ics-mr0/docs/STANDALONE-TOOLCHAIN.html
export ANDROID_SYSROOT="$ANDROID_NDK_ROOT/platforms/$_ANDROID_API/$_ANDROID_ARCH"
export CROSS_SYSROOT="$ANDROID_SYSROOT"
export NDK_SYSROOT="$ANDROID_SYSROOT"

# Error checking
if [ -z "$ANDROID_SYSROOT" ] || [ ! -d "$ANDROID_SYSROOT" ]; then
  echo "Error: ANDROID_SYSROOT is not valid. Please edit this script."
  # echo "$ANDROID_SYSROOT"
  # exit 1
fi

#####################################################################

# If the user did not specify the FIPS_SIG location, try and pick it up
# If the user specified a bad location, then try and pick it up too.
if [ -z "$FIPS_SIG" ] || [ ! -e "$FIPS_SIG" ]; then

  # Try and locate it
  _FIPS_SIG=""
  if [ -d "/usr/local/ssl/$_ANDROID_API" ]; then
    _FIPS_SIG=`find "/usr/local/ssl/$_ANDROID_API" -name incore`
  fi

  if [ ! -e "$_FIPS_SIG" ]; then
    _FIPS_SIG=`find $PWD -name incore`
  fi

  # If a path was set, then export it
  if [ ! -z "$_FIPS_SIG" ] && [ -e "$_FIPS_SIG" ]; then
    export FIPS_SIG="$_FIPS_SIG"
  fi
fi

# Error checking. Its OK to ignore this if you are *not* building for FIPS
if [ -z "$FIPS_SIG" ] || [ ! -e "$FIPS_SIG" ]; then
  echo "Error: FIPS_SIG does not specify incore module. Please edit this script."
  # echo "$FIPS_SIG"
  # exit 1
fi

#####################################################################

# Most of these should be OK (MACHINE, SYSTEM, ARCH). RELEASE is ignored.
export MACHINE=armv7
export RELEASE=2.6.37
export SYSTEM=android
export ARCH=arm
export CROSS_COMPILE="arm-linux-androideabi-"

if [ "$_ANDROID_ARCH" == "arch-x86" ]; then
	export MACHINE=i686
	export RELEASE=2.6.37
	export SYSTEM=android
	export ARCH=x86
	export CROSS_COMPILE="i686-linux-android-"
fi

# For the Android toolchain
# https://android.googlesource.com/platform/ndk/+/ics-mr0/docs/STANDALONE-TOOLCHAIN.html
export ANDROID_SYSROOT="$ANDROID_NDK_ROOT/platforms/$_ANDROID_API/$_ANDROID_ARCH"
export SYSROOT="$ANDROID_SYSROOT"
export NDK_SYSROOT="$ANDROID_SYSROOT"
export ANDROID_NDK_SYSROOT="$ANDROID_SYSROOT"
export ANDROID_API="$_ANDROID_API"

# CROSS_COMPILE and ANDROID_DEV are DFW (Don't Fiddle With). Its used by OpenSSL build system.
# export CROSS_COMPILE="arm-linux-androideabi-"
export ANDROID_DEV="$ANDROID_NDK_ROOT/platforms/$_ANDROID_API/$_ANDROID_ARCH/usr"
export HOSTCC=gcc

VERBOSE=1
if [ ! -z "$VERBOSE" ] && [ "$VERBOSE" != "0" ]; then
  echo "ANDROID_NDK_ROOT: $ANDROID_NDK_ROOT"
  echo "ANDROID_ARCH: $_ANDROID_ARCH"
  echo "ANDROID_EABI: $_ANDROID_EABI"
  echo "ANDROID_API: $ANDROID_API"
  echo "ANDROID_SYSROOT: $ANDROID_SYSROOT"
  echo "ANDROID_TOOLCHAIN: $ANDROID_TOOLCHAIN"
  echo "FIPS_SIG: $FIPS_SIG"
  echo "CROSS_COMPILE: $CROSS_COMPILE"
  echo "ANDROID_DEV: $ANDROID_DEV"
fi

~~~

设置环境变量 ANDROID_NDK_ROOT 

执行config：make -j8 make install

~~~
./config no-shared no-ssl2 no-ssl3 no-comp no-hw no-engine \
     --openssldir=/opt/soft/android/project/openssl-OpenSSL_1_1_1a/build --prefix=/opt/soft/android/project/openssl-OpenSSL_1_1_1a/build
~~~

![image-20190111133157445](https://ws2.sinaimg.cn/large/006tNc79ly1fz2l2db21lj31ss0u0to8.jpg)

***关于头文件的存放位置***：要注意。注意下面的设置务必正确，这个决定了路径的问题。

```c
target_include_directories(native-lib PRIVATE
        openssl-armeabi-v7a/include)
```

###Makefile

怎么学？看懂理解意思，不要求自己编写

推荐：https://seisman.github.io/how-to-write-makefile/introduction.html

文件搜索、预定义变量、条件判断、变量、include 需要重点关注

自己做一个增量更新的功能吧。

### 编辑器

gcc和g++都是GNU(组织)的一个编译器。

~~~html
1.后缀为.c的，gcc把它当作是C程序，而g++当作是c++程序；后缀为.cpp的，两者都会认为是c++程序，注意，虽然c++是c的超集，但是两者对语法的要求是有区别的。C++的语法规则更加严谨一些。
2.编译阶段，g++会调用gcc，对于c++代码，两者是等价的，但是因为gcc命令不能自动和C＋＋程序使用的库联接，所以通常用g++来完成链接，为了统一起见，干脆编译/链接统统用g++了，这就给人一种错觉，好像cpp程序只能用g++似的。
3.编译可以用gcc/g++，而链接可以用g++或者gcc -lstdc++。因为gcc命令不能自动和C＋＋程序使用的库联接，所以通常使用g++来完成联接。但在编译阶段，g++会自动调用gcc，二者等价。
~~~

clang：The Clang project provides a language front-end and tooling infrastructure for languages in the C language family (C, C++, Objective C/C++, OpenCL, CUDA, and RenderScript) for the [LLVM](http://www.llvm.org/) project. Both a GCC-compatible compiler driver (`clang`) and an MSVC-compatible compiler driver (`clang-cl.exe`) are provided. 

tips：最新的ndk版本开始采用clang。

gcc总结：

~~~html
Gcc指令的一般格式为：Gcc [选项] 要编译的文件 [选项] [目标文件] 
Linux 下GCC编译过程包含 预处理-----> 编译 ----> 汇编 ----> 链接 

gcc -E hello.c -o hello.i  在该阶段，编译器将C源代码中的包含的头文件如stdio.h编译进来，用户可以使用gcc的选项”-E”进行查看。**-E在预处理后停止编译**

gcc -S hello.i -o hello.s Gcc把代码翻译成汇编语言 ”-S”选项来进行查看，该选项只进行编译而不进行汇编，生成汇编代码。 

gcc -c hello.s -o hello.o 将汇编输出文件hello.s编译输出hello.o文件。 

gcc -o hello hello.s 链接阶段，链接的是**函数库**。在hello.c中并没有定义”printf”的函数实现，且在预编译中包含进的”stdio.h”中也只有该函数的声明。系统把这些函数实现都被做到名为`libc.so`的动态库。

~~~

关于动态和静态库：

- 静态库是指编译链接时，把库文件的代码全部加入到可执行文件中，因此生成的文件比较大，但在运行时也就不再需要库文件了。Linux中后缀名为”.a”。
- 动态库与之相反，在编译链接时并没有把库文件的代码加入到可执行文件中，而是在程序执行时由运行时链接文件加载库。Linux中后缀名为”.so”，如前面所述的libc.so就是动态库。gcc在编译时默认使用动态库。



交叉编译：指一个在某个系统平台下可以产生另一个系统平台的可执行文件的**编译**器。 **交叉编译**器在目标系统平台（开发出来的应用程序序所运行的平台）难以或不容易**编译**时非常有用。 **交叉编译**器的存在对于从一个开发主机为多个平台**编译**代码是非常有必要的。

ndk如何进行交叉编译？

https://gcc.gnu.org/onlinedocs/gcc/Directory-Options.html

~~~html
--sysroot=XX
    使用xx作为这一次编译的头文件与库文件的查找目录，查找下面的 usr/include usr/lib目录
-isysroot XX
    头文件查找目录,覆盖--sysroot ，查找 XX/usr/include
-isystem XX
    指定头文件查找路径（直接查找根目录）
-IXX
    头文件查找目录
优先级：
    -I -> -isystem -> sysroot
    
-LXX
    指定库文件查找目录
-lxx.so
    指定需要链接的库名
~~~

在linux环境中设置环境变量：

~~~bash
export CFLAG="--sysroot=/opt/soft/android/android-ndk-r14b/platforms/android-21/arch-arm -isystem /opt/soft/android/android-ndk-r14b/sysroot/usr/include/  -isystem /opt/soft/android/android-ndk-r14b/sysroot/usr/include/arm-linux-androideabi"
export CC = "/opt/soft/android/android-ndk-r14b/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc"

执行$CC $CFLAG -pie test.c -o test  可以生成Android可用的可执行文件
~~~

PIE&PIC：https://access.redhat.com/blogs/766093/posts/1975793

### Android.mk

微小 GNU makefile 片段。

将源文件分组为*模块*。 模块是静态库、共享库或独立可执行文件。 可在每个 `Android.mk` 文件中定义一个或多个模块，也可在多个模块中使用同一个源文件。 

```makefile
#源文件在的位置。宏函数 my-dir 返回当前目录（包含 Android.mk 文件本身的目录）的路径。
LOCAL_PATH := $(call my-dir)
#引入其他makefile文件。CLEAR_VARS 变量指向特殊 GNU Makefile，可为您清除许多 LOCAL_XXX 变量
#不会清理 LOCAL_PATH 变量
include $(CLEAR_VARS)
#存储您要构建的模块的名称 每个模块名称必须唯一，且不含任何空格
#如果模块名称的开头已是 lib，则构建系统不会附加额外的前缀 lib；而是按原样采用模块名称，并添加 .so 扩展名。
LOCAL_MODULE := hello-jni
#包含要构建到模块中的 C 和/或 C++ 源文件列表 以空格分开
LOCAL_SRC_FILES := hello-jni.c
#构建动态库
include $(BUILD_SHARED_LIBRARY)
```



**变量和宏**

定义自己的任意变量。在定义变量时请注意，NDK 构建系统会预留以下变量名称：

- 以 `LOCAL_` 开头的名称，例如 `LOCAL_MODULE`。
- 以 `PRIVATE_`、`NDK_` 或 `APP` 开头的名称。构建系统在内部使用这些变量。
- 小写名称，例如 `my-dir`。构建系统也是在内部使用这些变量。

如果为了方便而需要在 `Android.mk` 文件中定义自己的变量，建议在名称前附加 `MY_`。



**常用内置变量**

| 变量名                  | 含义                       | 示例                               |
| ----------------------- | -------------------------- | ---------------------------------- |
| BUILD_STATIC_LIBRARY    | 构建静态库的Makefile脚本   | include $(BUILD_STATIC_LIBRARY)    |
| PREBUILT_SHARED_LIBRARY | 预编译共享库的Makeifle脚本 | include $(PREBUILT_SHARED_LIBRARY) |
| PREBUILT_STATIC_LIBRARY | 预编译静态库的Makeifle脚本 | include $(PREBUILT_STATIC_LIBRARY) |
| TARGET_PLATFORM         | Android API 级别号         | TARGET_PLATFORM := android-22      |
| TARGET_ARCH             | CUP架构                    | arm arm64 x86 x86_64               |
| TARGET_ARCH_ABI         | CPU架构                    | armeabi  armeabi-v7a  arm64-v8a    |



**模块描述变量**

| 变量名                       | 描述                                   | 例                                                     |
| ---------------------------- | -------------------------------------- | ------------------------------------------------------ |
| LOCAL_MODULE_FILENAME        | 覆盖构建系统默认用于其生成的文件的名称 | LOCAL_MODULE := foo LOCAL_MODULE_FILENAME := libnewfoo |
| LOCAL_CPP_FEATURES           | 特定 C++ 功能                          | 支持异常:LOCAL_CPP_FEATURES := exceptions              |
| LOCAL_C_INCLUDES             | 头文件目录查找路径                     | LOCAL_C_INCLUDES := $(LOCAL_PATH)/include              |
| LOCAL_CFLAGS                 | 构建 C *和* C++ 的编译参数             |                                                        |
| LOCAL_CPPFLAGS               | c++                                    |                                                        |
| LOCAL_STATIC_LIBRARIES       | 当前模块依赖的静态库模块列表           |                                                        |
| LOCAL_SHARED_LIBRARIES       |                                        |                                                        |
| LOCAL_WHOLE_STATIC_LIBRARIES | --whole-archive                        | 将未使用的函数符号也加入编译进入这个模块               |
| LOCAL_LDLIBS                 | 依赖 系统库                            | LOCAL_LDLIBS := -lz                                    |

导出给引入模块的模块使用：

LOCAL_EXPORT_CFLAGS

LOCAL_EXPORT_CPPFLAGS

LOCAL_EXPORT_C_INCLUDES

LOCAL_EXPORT_LDLIBS

------

**引入其他模块**

```makefile
#将一个新的路径加入NDK_MODULE_PATH变量
#NDK_MODULE_PATH 变量是系统环境变量

$(call import-add-path,$(LOCAL_PATH)/platform/third_party/android/prebuilt)
#包含CocosDenshion/android目录下的mk文件
$(call import-module,CocosDenshion/android)

#这里即为 我需要引入 CocosDenshion/android 下面的Android.mk
#CocosDenshion/android 的路径会从 $(LOCAL_PATH)/platform/third_party/android/prebuilt 去查找
```



### Application.mk

同样是GNU Makefile 片段,在Application.mk中定义一些全局(整个项目)的配置



**APP_OPTIM**

将此可选变量定义为 `release` 或 `debug`。在构建应用的模块时可使用它来更改优化级别。发行模式是默认模式，可生成高度优化的二进制文件。调试模式会生成未优化的二进制文件，更容易调试。



**APP_CFLAGS**

为任何模块编译任何 C 或 C++ 源代码时传递到编译器的一组 C 编译器标志



**APP_CPPFLAGS**

构建 C++ 源文件时传递到编译器的一组 C++ 编译器标志。



**APP_ABI**

需要生成的cpu架构(ndk r17 只支持：armeabi-v7a, arm64-v8a, x86, x86_64)

| 指令集                             | 值                       |
| ---------------------------------- | ------------------------ |
| 基于 ARMv7 的设备上的硬件 FPU 指令 | `APP_ABI := armeabi-v7a` |
| ARMv8 AArch64                      | `APP_ABI := arm64-v8a`   |
| IA-32                              | `APP_ABI := x86`         |
| Intel64                            | `APP_ABI := x86_64`      |
| MIPS32                             | `APP_ABI := mips`        |
| MIPS64 (r6)                        | `APP_ABI := mips64`      |
| 所有支持的指令集                   | `APP_ABI := all`         |

不同 Android 手机使用不同的 CPU，因此支持不同的指令集。

**armeabi**

此 ABI 适用于基于 ARM、至少支持 ARMv5TE 指令集的 CPU。此 ABI 不支持硬件辅助的浮点计算。 相反，所有浮点运算都使用编译器 `libgcc.a` 静态库中的软件帮助程序函数。

**armeabi-v7a**

`armeabi-v7a` ABI 使用 `-mfloat-abi=softfp` 开关强制实施规则，要求编译器在函数调用时必须传递核心寄存器对中的所有双精度值，而不是专用浮点值。 系统可以使用 FP 寄存器执行所有内部计算。 这样可极大地加速计算。

如果要以 armeabi-v7a ABI 为目标，则必须设置下列标志：

```
CFLAGS= -march=armv7-a -mfloat-abi=softfp -mfpu=vfpv3-d16
```

**arm64-v8a**

此 ABI 适用于基于 ARMv8、支持 AArch64 的 CPU。它还包含 NEON 和 VFPv4 指令集。

**x86**

此 ABI 适用于支持通常称为“x86”或“IA-32”的指令集的 CPU。设置的标志如：

```
-march=i686 -mtune=intel -mssse3 -mfpmath=sse -m32
```

**x86_64**

```
-march=x86-64 -msse4.2 -mpopcnt -m64 -mtune=intel
```



现在手机主要是armeabi-v7a。查看手机cpu：

```
adb shell cat /proc/cpuinfo
adb shell getprop ro.product.cpu.abi
```



apk在安装的时候，如果手机是armeabi-v7a的，则会首先查看apk中是否存在armeabi-v7a目录，如果没有就会查找armeabi。

**保证cpu目录下so数量一致。**

​	如果目标是armeabi-v7a，但是拥有一个armeabi的，也可以把它放到armeabi-v7a目录下。但是反过来不行

| ABI(横 so)/CPU(竖 手机) | armeabi | armeabi-v7a | arm64-v8a | x86  | x86_64 |
| ----------------------- | ------- | ----------- | --------- | ---- | ------ |
| ARMV5                   | 支持    |             |           |      |        |
| ARMV7                   | 支持    | 支持        |           |      |        |
| ARMV8                   | 支持    | 支持        | 支持      |      |        |
| X86                     |         |             |           | 支持 |        |
| X86_64                  |         |             |           | 支持 | 支持   |



**APP_PLATFORM**

此变量包含目标 Android 平台的名称。例如，`android-3` 指定 Android 1.5 系统映像



**APP_STL**

默认情况下，NDK 构建系统为 Android 系统提供的最小 C++ 运行时库 (`system/lib/libstdc++.so`) 提供 C++ 功能。 

| 名称              | 说明>                        | 功能                    |
| ----------------- | ---------------------------- | ----------------------- |
| libstdc++（默认） | 默认最小系统 C++ 运行时库。  | 不适用                  |
| gabi++_static     | GAbi++ 运行时（静态）。      | C++ 异常和 RTTI         |
| gabi++_shared     | GAbi++ 运行时（共享）。      | C++ 异常和 RTTI         |
| stlport_static    | STLport 运行时（静态）。     | C++ 异常和 RTTI；标准库 |
| stlport_shared    | STLport 运行时（共享）。     | C++ 异常和 RTTI；标准库 |
| gnustl_static     | GNU STL（静态）。            | C++ 异常和 RTTI；标准库 |
| gnustl_shared     | GNU STL（共享）。            | C++ 异常和 RTTI；标准库 |
| c++_static        | LLVM libc++ 运行时（静态）。 | C++ 异常和 RTTI；标准库 |
| c++_shared        | LLVM libc++ 运行时（共享）。 | C++ 异常和 RTTI；标准库 |



