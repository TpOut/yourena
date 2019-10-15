# java程序设计第10版-进阶

**1、泛型** 泛型用大写字母代替类型，无字母限制，但是一般使用如下字母： E - Element \(在集合中使用，因为集合中存放的是元素\) T - Type（Java 类） K - Key（键） V - Value（值） N - Number（数值类型） ？ - 表示不确定的java类型 S、U、V - 2nd、3rd、4th types

受限泛型： T extends Object\(表示泛型必须是Object或者其子类，Object可以是任何类\) 通配泛型： 1、非受限通配 -- ? -- 基本没限制，等同于 ? extends Object 2、受限通配 -- ? extends T -- 表示T或者T的一个子类型（T需要替换成实际的类） 3、下限通配 -- ? super T -- 表示T或者T的一个父类型（T需要替换成实际的类）

受限泛型是指定义泛型相关类或方法时候的约束，而通配泛型则是在使用泛型类或方法时候的约束

泛型只是一种约束，实际上还是类型消除的，只是编译器进行了自动转化。 类型消除会带来一系列的限制，如： 不能new E\(\)；不能new E\[\] ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-8b8850ac57b4f1d6.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-1ed947a24faff5b5.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

泛型创建数组： E\[\] elements = \(E\[\]\) new Object\[capacity\]（会存在类型检测警告）

**2、容器** 在面向对象思想里，一种数据结构也被认为是一个容器（container）或者容器对象。

**3、合集（Collections）框架** java合集框架支持以下两种类型的容器：  
一种是为了存储一个元素合集，简称为合集\(collection\) 另一种是为了存储键值对，称为映射表（map） collection包括：set, list, queue。他们都继承Collection接口，以期实现一些统一共用的功能

![collection&#x63A5;&#x53E3;&#x5B9A;&#x4E49;](https://upload-images.jianshu.io/upload_images/1936727-fa16d58113e69962.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Q1：Collection 接口的部分方法在子类中无法实现，会在子类中抛出异常。比如add方法，在AbstractCollection,AbstractList中均抛出异常UnsupportedOperationException，而在ArrayList中才实现 A1：如果子类不需要实现add方法--比如UnmodifiableCollection--那么就可以不去实现，且可以从父类来统一异常标准，避免子类一个个实现。

Q2：有空看下Spliterator

**4、常用类** Objects、Comparator、Vector-HashTable（遗留类，修改操作同步）， Thread类

**5、Set** ![Set&#x7ED3;&#x6784;.PNG](https://upload-images.jianshu.io/upload_images/1936727-181c679fdf30c038.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

HashSet不保证元素顺序，LinkedHashSet按插入顺序排序，TreeSet则会对元素进行排序

**6、标签** 深层嵌套的时候使用有助于阅读 label79:{ if\(someCondition\){ break label79; } }

**7、Map** ![Map.PNG](https://upload-images.jianshu.io/upload_images/1936727-81d1de252ab5bd67.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

不需要排序使用HashMap，按插入顺序或者访问顺序排序使用LinkedHashMap，按键值排序使用TreeMap

**8、时间复杂度** ![&#x65F6;&#x95F4;&#x590D;&#x6742;&#x5EA6;.PNG](https://upload-images.jianshu.io/upload_images/1936727-77eee6135ad3414d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**9、排序算法**

| 算法名称 | 基本原理 | 时间复杂度 |
| :---: | :---: | :---: |
| 插入排序 | 区分出已处理部分和未处理部分，在未处理部分依序选择第一个数，依次与已处理部分的数比较，插入到合适的位置 | _O_\(n^2\) |
| 冒泡排序 | 区分出已处理部分和未处理部分，在未处理部分一一比较相邻的数，让大数移向已处理部分 | 最佳_O_\(n\)，最差_O_\(n^2\) |
| 归并排序 | 先将数据分成多段，对每个子段递归进行归并排序，再对排好序的子段进行合并排序 | _O_\(n\*log n\) |
| 快速排序 | 选取一个数，使左侧部分都小于中间数，右侧部分都大于中间数，再对左右分别递归使用快速排序 | _O_\(n\*log n\) |
| 二叉堆排序 | 前提：使用完全二叉树结构，每个结点大于（小于）等于他的子结点。使用上述条件，重复执行获取根结点，并重新排序剩余数据操作 | _O_\(n\*log n\) |
| 桶排序和基数排序 | 根据数字的位数的值逐位进行分桶排序 | _O_\(n\) |
| 外部排序 | 对无法再内存中处理的大文件数据，类似使用归并排序，先分段排序再逐步归并排序 | _O_\(n\*log n\) |

归并排序可以使用多线程并行处理 桶排序在整数时可用，不是比较算法，而其他算法都是比较算法。

**10、多线程** Thread.yield\(\);让当前线程切换成就绪状态，让所有线程重新竞争调度 thread1.join\(\);让线程thread1执行完毕之后，其他线程再执行 setDaemon来设置守护线程，

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

**14、网络** InetAddress、Socket

**问题** 二叉查找树、AVL树、图及其应用、加权图及其应用都被跳过了。

**一句话总结：** 用default修饰接口中的方法时，可以让方法拥有具体实现代码 LinkedList遍历操作不要使用循环get，应该使用iterator，foreach就是使用的iterator 通常对于比较器来讲，实现serializable比较好。 因为从复杂度上来讲 _O_\(log2 n\) = _O_\(log10 n\) = _O_\(loga n\) ，所以常量的底可以忽略，简单起见写成_O_\(log n\)

