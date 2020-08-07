命令ps , pstree



进程是一个动态实体，随着处理器执行机器代码指令而不断变化。除了程序指令和数据外，进程还包括程序计数器和所有CPU寄存器，以及包含临时数据（如例程参数、返回地址和保存的变量）的进程堆栈

数据结构是`task_struct` ，列表为`task` （所以系统的进程数受限于列表的大小）  

普通进程和实时进程，主要结构有：

- 状态：
  - 运行：`current` 指向，或者准备就绪（等待cpu）
  - 等待：等待事件或者等待资源。又细分为两类：可中断和不可中断
  - 停止：一般是收到信号（如debug）
  - 僵尸（挂起）：死亡（只是还在列表中）
  
- 调度信息：

  用于系统决定调度顺序

  使用抢占式，系统调用时等待并重分配，时间片

  分配方针：实时进程优先普通。而实时进程之间则是FIFO 或者round robin。而同种进程之间根据优先度。普通进程优先度命令 `renice `

  多处理器时，会通过一个掩码位，让cpu 选择对应位置运行过的程序。

- id：

  单纯的一个数字，但也有其他id：

  - uid, gid：运行的用户和组
  - effective uid ,gid ：用于*setuid* 
  - 文件系统uid ，gid：用于NFS，目的类似effective uid，gid，但是防止了kill signal 
  - saved uid , gid：用于修改uid,gid 时

- ipc：在ipc 章节讲

- 关联：每个进程都有父进程（除初始进程）。数据结构中还会有兄弟和子进程。

- 时间

  `jiffies` ，内核和用户态用时；

  定时器有三种：

  - real，SIGALRM
  - virtual，只在运行时，SIGVTALRM
  - profile，系统替代运行时，SIGPROF  

- 文件系统：

  包含打开文件的描述符，以及两个VFS 节点（home 和 pwd）  

  - `fs_struct`：umask
  - `files_struct`：文件描述符，*standard input*, *standard output* and *standard error* 

- 虚拟内存

  kernel 线程和daemon 没有，并且kernel 需要去管理所有进程的虚拟内存

  数据结构：`mm_struct` ， AVL tree  

  请求分配内存，实际上只是创建了虚拟内存地址。而对应的物理地址内存，需要真正使用时才会关联起来（通过page fault）  

- 特定上下文：会在等待时存储在task_struct



**进程的创建**

系统启动，kernel 模式，initial 进程，kernel 线程(init，循环空闲)，`init_task`  

初始进程做一些系统配置，如开启控制台、挂载文件系统，然后执行初始化程序。  

/etc/init ; /bin/init ; /sbin/init  ； /etc/inittab 

然后是复制， copy on write  



**执行程序**

通过shell 这个用户进程。  `tcsh`  



**脚本文件**

解释器：wish, perl 





