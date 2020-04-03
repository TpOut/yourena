[Thread](./Thread.md)



Thread.yield\(\);让当前线程切换成就绪状态，让所有线程重新竞争调度

 thread1.join\(\);让线程thread1执行完毕之后，其他线程再执行

 setDaemon来设置守护线程，

加锁：

```text
public synchronized void method(){

}
//等同于
public void method(){
    synchronized(this){

    }
}
//等同于
class Class{
    private Lock lock = new ReentrantLock();

    public void method(){
        lock.lock();
        //doSomething
        lock.unlock();
    }

}
```

其中ReentrantLock可以制定公平策略，使时间等待较长的线程先获得锁

线程间协作： 可以通过Lock的newCondition\(\)方法创建对象，然后就可以调用await\(\)、signal\(\)和signalAll\(\)方法实现相互通信。这个条件的创建会让某些情况下无用的线程不再造成抢占资源的消耗，也可以确保有用的线程准确被调用。

```text
//使用琐和条件的方式更具灵活性
class Class{
    private Lock lock = new ReentrantLock();
    private Condition c = lock.newCondition();

    public void method(){
        lock.lock();
        //doSomething
        c.wait();
        lock.unlock();
    }

    //else invoke c.signal()
}
//等同于
synchronized(anObject){
    anObject.wait();
}
//else invoke anObject.notify()
```

解决死锁的常规办法应该是在加锁的时候按照资源排序的方式进行（即每个加锁的地方的加锁顺序一致）。

ForkJoinTask,ForkJoinPool

Q1：这本书已经是第十版了，还是说Java虚拟机总是选择当前优先级最高的可运行线程。可是事实上，只是优先级高的更可能被选定，也就是说，优先级实际上没有保证性。

**11、阻塞队列** ArrayBlockingQueue、LinkedBlockingQueue、PriorityBlockingQueue

**12、信号量** Semaphore

**13、线程安全的集合** ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-f5b9c77493f8d70d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)







```text
public void run(){
    try{
        while(!Thread.currentThread().isInterrupted()){
            //interrupte只是一个boolean标志位，语言方面不要求中断线程，即可能一直执行。
            //如果在interrupt之后调用sleep,将抛出异常
            Thread.sleep();
        }
    }
}
```

InterruptedException最好别忽略，要么再抛出，要么在catch中调用Thread.currentThread\(\).interrupt\(\);

线程状态，new,runnable\(not run\),blocked,waiting,timed waiting,terminated 对应操作，

new,start,试图获取内部对象锁而非concurrent中的锁且获取不到时，等待另一个线程通知调度器一个条件时，Thread.sleep--Object.wait--Thread.join--Lock.tryLock--Condition.await有超时参数时，run结束或者异常

抢占式调度，协调式调度，时间片机制

线程调度器在有机会重新选择线程时，会优先选择优先级高的线程。但是线程优先级是高度依赖系统的，优先级可能与代码中设置的不同。比如win7是7个优先级，导致java的10个优先级，会有部分在win7上是相同的优先级；而sun为linux提供的java虚拟机，线程的优先级被忽略，即所有线程有相同的优先级。

守护线程。一般作为计时使用，会在任何时候中断。

UncaughtExceptionHandler,ThreadGroup\(线程集合， 默认所有线程为同一个组，现在不建议用了\)

lock  
ReentrantLock, 锁是可重入的，比如第一个加锁方法在unlock之前调用了另一个加锁的方法，会在后者lock时计数为2 虽然进行了加锁行为，还是得小心异常带来的数据损坏。

历史原因，条件对象被称为conditional variable  
condition, await, signalAll,singnal\(可能造成死锁\)

synchronized简化使用了对象内部锁。wait\(\)/notifyAll\(\)/notify\(\);如果是static，对应class对象。

锁的缺点：1、无法中断试图获取锁的线程；2、试图获取锁的时候不能设置超时；3、条件单一

建议优先使用concurrent包中的机制，然后是synchronized，然后是lock/condition。

client-side-locking,比较脆弱，不推荐使用 ,但是也有需要。

monitor,不安全的，章14.5.7

java内存模型和技术规范 jsr 133, [http://www.jcp.org/en/jsr/detail?id=133](http://www.jcp.org/en/jsr/detail?id=133)  
[http://www-106.ibm.com/developerworks/java/library/j-jtp02244.html](http://www-106.ibm.com/developerworks/java/library/j-jtp02244.html)

volatile,免锁读写，个人理解成及时刷新  
但是不保证原子性，如volatile boolean done; flipDone\(\){ done = !done };//not atomic

concurrent.atomic, 如AtomicInteger,建议开发并发工具的系统程序员使用。

当程序挂起时，键入ctrl+\,将得到所有线程的列表

```text
public static final SimpleDateFormat  dateFormat = new ...;
//如果在并发的情况下执行，可能造成结果混乱
String dateStamp  = dateFormat.format(new Date());
//如果每次都同步，感觉开销又太大

//可以使用以下方法为每个线程构建实例
public static final ThreadLocal<SimpleDateFormat> dateFormat = 
    new ThreadLocal<...>(){
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat(...);
        }
    }
```

ThreadLocalRandom.current\(\)

lock.tryLock\(\);允许打破死锁，线程中断抛出异常。lockInterruptibly\(无限超时的tryLock\)  
awaitUninterruptibly

concurrent.locks,ReentrantReadWriteLock

BlockingQueue.LinkedBlockingQueue,ArrayBlockingQueue,PriorityBlockingQueue,DelayQueue,TransferQueue,LinkedTransferQueue

concurrentHashMap,ConcurrentSkipListMap,CopyOnWriteArrayList,  
最好使用concurrent中的集合，而不是使用同步包装器Collections.synchronized...;例外：如果数组列表经常被修改，同步的ArrayList比CopyOnWriteArrayList更好

Callable,Future,FutureTask

ExecutorService,线程池。  
invokeAny,异步处理，取最早的结果。ExecutorCompletionService

fork-join,RecursiveTask--RecursiveAction,work stealing.ForkJoinPool

章节14.10有简单说明 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-f191432071a5ec2a.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

信号量，permits, synchronization primitive

Q1： final Map m = new Map\(\);//如果不是final,多线程读取可能会null

**问题** Q1：可以思考Date和Calendar的设计思路 Q2：理解make工具 Q3: System.setOut\(\) change a final field by invoking native method Q4: try to find the mechanism of main method ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-11400fb5e790c1ef.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**总结** 静态引用如： import static java.lang.System.\*; 可以忽略类名直接书写对应的静态方法或域



---

深入拓展：Brian Goetz\(Addison-Wesley Professional, 2006\)--《Java Concurrency in Practice》

