#### 传统磁盘

sector（扇区）, track（磁道）, cylinder（磁柱）, cluster(簇 -- allocator unit 回收单元)

https://www.cnblogs.com/joydinghappy/articles/2511948.html



SATA，USB，SAS

#### 固态硬盘

现在用  每秒可操作读写次数  来标识性能



#### 文件目录

/dev/     (device 目录)

/dev/sd[a~p] 

假设硬盘被检测时是 /dev/sdb , 那么如果有分区，则每个为 /dev/sdb[1~n]；但是1-4 是被主分区和拓展分区预定的，逻辑分区只能从5开始。



#### MBR

早期的磁盘在第一个扇区存储了整个磁盘的信息，即Master Boot Record，包括

- 开机管理程序。446bytes

- 分区表信息。分区即将扇区的范围进行划分。分区表信息的磁盘大小一般是64bytes，只能有4笔分区。分为主分区和拓展分区，后者只能有一个。为了更多的分区，拓展分区的头区块中也会记录一个分区信息，即进行更细的分区拓展，更新的分区为逻辑分区。

缺陷：2TB的分区系统无法读；开机管理程序的区块太小；第一个扇区损毁导致整个磁盘无法使用



其中开机管理程序，在加载的时候，可能会提供加载选择；然后将加载过程 转接到 每个分区的boot sector，每个分区的boot sector 负责执行接下来的操作，比如开启分区对应的操作系统

#### GPT

后来磁盘发展，就改成了 GPT（GUID partition table）

4k 的扇区，里面包含逻辑区块地址（Logical Block Address, LBA） 来兼容之前的512 扇区。第一个LBA 叫做LBA0。GPT 会用1~34个LBA 来记录分区信息，还有磁盘最后的33个LBA 作为备份。

支持64位寻址



