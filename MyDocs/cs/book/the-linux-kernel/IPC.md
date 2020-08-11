- the classic Unix `T`M IPC mechanisms of signals, pipes and semaphores 
-  the System V IPC mechanisms of shared memory, semaphores and message queues.



**signals**

最老的方式，发送异步事件的信号给进程。

信号包括键盘中断、错误（如访问未知地址）。同时产生的两个信号，因为只是一个标志位，所以不会马上让进程“知晓”，也不能保证被进程处理的顺序，同时一个进程接收到的同类型信号数量也没有统计。      

只有kernel、super 和同uid/gid 的进程 和同进程组的 可以发送

接收进程可以忽略这些信号，除了SIGSTOP 和SIGKILL。同时可以选择处理方式，或者扔给kernel 默认处理。  



shell 也使用这种方式控制子进程  

`kill -l` 罗列所有信号类型  



信号数量和处理器的位数(word size)有关。

`signal`, `blocked`, `sigaction`  

  

**pipes**

大部分shell 都支持重定向，将前一个程序的standard output 写入下一个程序的standard input 中   

linux 是通过两个`file` ，指向同一个临时inode，而这个inode 指向具体的共享数据页，来实现的。    

为了保证写读的顺序，使用了锁，等待队列，和signals  

已命名管道（named pipes），俗称FIFOs，相关命令 `mkfifo`



**sockets**





- system v IPC 机制

  这个机制下的方式，使用通用的认证方法，调用系统方法时需要传递一个唯一的引用id  

  关键结构`ipc_perm`    

  - 消息队列

    `msqid_ds` 组成的`msque`   

  - semaphores

    信号量的最简单实现就是一块谁都能访问的内存。然后用一个标记值来表示数据是否正被访问，以此让其他进程等待。

    系统的`semary` 指向 `semid_ds` ，  

    但是正在访问的进程可能崩溃，导致标志位无法恢复，此时有恢复机制`sem_undo`

  - 共享内存

    `shmid_ds`  组成的`shm_segs`  

    

  

