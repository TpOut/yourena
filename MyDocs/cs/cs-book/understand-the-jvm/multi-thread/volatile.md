**可见性：**

当一条线程修改了这个变量的值，新的值对于其他多有线程来说是可以立即得到的

（volatile 在各个线程的工作内存中可以不一致，但是执行引擎在使用之前都会刷新，所以可以说volatile 没有一致性问题）



误区：

​    因此而以为线程安全。

实际上java 里的运算并非原子操作，所以依旧是线程不安全的

[测试代码](./VolatileTest.java)

[字节码](./volatile-assemble.md)

等于说，线程1拿到数据n，执行加1，再同步回工作内存 n+1；线程2同时拿到数据n，执行加1，再同步回工作内存n+1。于是会出现同步“丢失”的情况



使用场景

- 变量的新值并不依赖变量的当前值，或者只有的一条线程在修改变量的值
- 变量不需要与其他的状态变量共同参与不变约束



比如

```java
volatile boolean shouldShutDown;
//thread1
Utils.makeShutDown(); // shouldShutDown = true
//thread2
while(!shouldShutDown){
  ...  // 这里就会立即停止了
}
```



**禁止指令重排序优化**

比如

```java
boolean afterConfig;
//thread1
func{
  config();
  afterConfig = true;
}
//thread2
while(!afterConfig){
  sleep();
}
doOthers();

//本身目的是希望配置之后，其他线程可以正确处理
//但是这里的thread1 内的代码是同步的，所以jvm 可能先执行了afterConfig = true，再config()
//此时导致thread2 的操作提前，出现问题

而添加了volatile 的话，就不会有这个问题
  关键在于底层会自动插入一个空指令，实现内存屏障的效果
```

>内存屏障的实现，有点类似为前面的操作添加依赖，保证执行到内存屏障时，所有该执行完毕的指令都已经执行完毕。http://g.oswego.edu/dl/jmm/cookbook.html



**和内存模型的关联规则**

T 表示线程，V表示volatile 型变量，那么对read,load,use, assign, store, write 操作有以下限制：

- T use V 的前一个指令，必须是load；load V的后一个指令，必须是use

- T store V 的前一个指令必须是assign；assgin 的后一个指令，必须是store

- 基本模型中，没有规定read  和load 之间；store 和write 之间不能有其他操作

    所以读取a,b 变量的操作可能是， read a ,read b, load b , load a

    但是如果是针对volatile 变量V1，V2

    那么一定要保证read V1, loadV1; read V2, load V2 

