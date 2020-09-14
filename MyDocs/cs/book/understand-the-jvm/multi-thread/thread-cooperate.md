java线程变量共享分类：

- 不可变

    final 修饰自身，且保证自身不会变化

    api包括 String, 枚举类，Number部分子类，数值包装类，大数据类型

- 绝对线程安全：不管何时，调用者都不需要添加同步处理代码。

    例如vector，虽然所有add,remove操作都是同步的，但是我们外部获取vector.size() 后遍历循环时，可能vector 的size 发生了变化，导致获取错误。那它就不是绝对线程安全

- 相对线程安全：除了一定顺序的连续调用时，都不需要添加同步处理代码。

    大部分的java 线程安全类都是这种。例如vector

- 线程兼容：调用者可以通过同步处理代码实现并发使用

    大部分的类都是这种，如ArrayList

- 线程对立：不管是否同步处理，都无法实现并发使用

    如Thread.suspend，resume (可能会产生死锁，所以被废弃)

    类似的还有System.setIn, System.setOut, System.runFinalizersOnExit



**同步：**

指多个线程并发访问共享数据时，保证共享数据在同一个时刻只被一个线程（信号量则是一些）使用

**互斥：**

实现同步的一种手段，临界区（Critical Section）,互斥量(Mutex)，信号量（Semaphore）



java 中基本互斥同步方式是synchronized。synchronized关键字经过编译之后，会在同步块的前后分别形成monitorenter, monitorexit 字节码指令。这两个字节码都需要一个reference 类型的参数来指定要锁定和解锁的对象。reference值可能是指定的对象参数、对象实例、Class对象

锁计数器。在执行moniterenter的时候，如果对象没有被锁定或者已经被当前线程锁定，则锁计数器加1；对应的moniterexit 的时候，计数器减1。计数器为0 的试试，锁被释放。如果获取对象锁失败，则被阻塞等待。

但是synchronized 是一个重量级操作

我们还可以使用concurrent 的ReentrantLock 来实现同步，它和前者类似，但是有些高级功能：

- 等待可中断：
- 可实现公平锁：按申请顺序依次获得锁
- 可绑定多个条件：条件指Condition对象

1.6之后，两者的性能基本没差，所以还是优先使用synchronized



**非阻塞同步（Non-blocking synchronized）**

上述的等待线程是要被阻塞的，而且不管数据是否真的出现竞争。都要进行加锁（虽然jvm会优化掉很多）、用户态核心态切换、维护锁计数器、检查是否要唤醒线程。

称之为**悲观锁**，现在有一种基于冲突检测的**乐观锁**：

先进行操作，如果没有线程竞争数据，那么成功；否则进行策略处理，一般是重新进行操作直到成功为止。这种方式的许多实现都不需要挂起线程，所以是非阻塞同步



但是有一个前提，就是硬件指令集支持将“操作”和“冲突检测”实现为原子性。

这类指令常用的有：

- 测试并设置（Test and Set）
- 获取并增加（Fetch and Increment）
- 交换（Swap）
- 比较并交换（Compare and Swap）
- 加载链接/条件存储（Load linked/ Store conditional）

前3条20世纪已经常见，后两条是现代处理器新增的



CAS：有三个操作数。首先是变量的内存地址，变量的旧预期值，变量的新预期值。在执行操作之后，只有旧预期值等同于当前内存地址的变量值时，才会用新预期值更新内存地址的值。否则不更新。

1.5之后Unsafe里有对应的compareAndSwap* 的方法，Atomic*类的incrementAndGet 调用compareAndSet 就是实际调动了这个。

CAS 的逻辑漏洞在于，内存地址的变量可能经历多次变化后，和之前的旧预期值依旧一样。这个叫做ABA 问题。当然也有补充方案：AtomicStampedRefrence。只是这个逻辑漏洞并不影响大部分并发情况的正确性



**无需同步**

- 可重入代码（纯代码）

    只要参数相同，结果就能相同的代码

- 线程本地存储

    

**锁优化**

- 适应性自旋（Adaptive Spinning）

如果线程只需稍等一段时间就能获取执行时间，那并不需要去执行挂起和唤醒的步骤。只需要让线程“自旋”一下（执行忙循环即可），自旋的次数，自旋的时长是自适应的。

- 锁消除（Lock Elimination）

    依赖<font color=red>逃逸分析</font>的数据支持。会检测明显不可能存在数据竞争的锁并进行消除。

- 锁粗化（Lock Coarsening）

    对连续的加锁同步操作，进行粗化，减少加锁解锁的次数。

- 轻量级锁（LightWeight Locking）

    传统的锁机制基于操作系统的互斥量实现，而轻量级是基于CAS 更新Mark word来实现的

    在没有线程竞争时，减少互斥量性能消耗；但是有竞争时，平白增加了CAS 操作。

    在大部分锁不存在竞争情况的前提下，能够提升同步性能。

- 偏向锁（Biased Locking）

    偏向于第一个获得偏向锁的线程，如果执行过程中，锁没有被其他的线程获取，则持有偏向锁的线程不需要进行同步。

    可以提高带有同步但无竞争的程序性能。
