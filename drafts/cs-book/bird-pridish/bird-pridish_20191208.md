国家高速网络与计算中心：http://www.nchc.org.tw/ （这个地址？）

全世界最快速的前500大超级计算机：http://www.top500.org/



容量单位一般以1024 ，速度单位以1000 表示 位阶进位



主板上的内存插槽可能有颜色对应，标识相同的颜色支持双通道功能。（甚至有更高）



bios （一种 firmware，特指写入硬件的程序）为了自身升级的方便，现在都是写入在flash 或者 eeprom

CMOS 的记录主板上各种参数，如各项设备的I/O地址和IRQ。

UEFI （Unified Extensible Firmware Interface）



一台机器可以有多个网卡，连接到不同的网域

网卡也很主要组件一样有很多规格



虚拟内存，内存置换



System V、BSD

GNU

FSF: https://www.fsf.org/resources



GPL v2 v3，copyleft (更多：https://opensource.org/licenses/alphabetical)

free software / open software

posix



查看linux kernal版本 : `uname -r`



Tarball， RPM，DPKG， YUM， APT



云端虚拟化



/user/share/doc

the linux document project : http://www.tldp.org/



光驱默认挂载 :` /media/cdrom` ; ` /mnt/`

常用目录：`/boot`,  `/`,  `/var`,  `/home` , `swap` （容量大且读写多） ， `/usr` , `temp` 

文件系统检查 `fsck`



RAID 磁盘阵列

KVM 虚拟机



文件挂载：

系统启动的时候，从硬盘读取系统数据，系统基本数据读取后，读取对应的硬件驱动信息。然后软件层面将文件系统和信息关联起来，叫做挂载。



secure boot



linux 的NAT 服务来实现ip 共享



SAMBA : 支持文件修改、上下载等