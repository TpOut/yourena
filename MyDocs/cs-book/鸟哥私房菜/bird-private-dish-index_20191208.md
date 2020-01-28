容量单位一般以1024 ，速度单位以1000 表示 位阶进位

[主板相关](./main-board.md)

虚拟内存，内存置换

System V、BSD



软件包管理：Tarball， RPM，DPKG， YUM， APT

云端虚拟化



**文件挂载**：

系统启动的时候，从硬盘读取系统数据，系统基本数据读取后，读取对应的硬件驱动信息。然后软件层面将文件系统和信息关联起来，叫做挂载。

光驱默认挂载 :` /media/cdrom` ; ` /mnt/`



**文件系统**

FAT，NTFS，EXT2

inode，data block， superblock

block group

常用目录：`/boot`,  `/`,  `/var`,  `/home` , `swap` （容量大且读写多） ， `/usr` , `temp` 

http://www.pathname.com/fhs/

文件系统检查 `fsck`



[压缩](./compress.md)



RAID 磁盘阵列

KVM 虚拟机



linux 的NAT 服务来实现ip 共享

SAMBA : 支持文件修改、上下载等



tab, 命令名补齐，文件名补齐



自启动脚本在/etc/init.d/ 这个目录下，或systemd

所有的服务启动脚本都放置在/etc/init.d/ 下面。

基本命令包含`<daemon> start, stop, restart, status`

/usr/lib/systemd/system/

/run/systemd/system/

/etc/systemd/system/



[用户群组](./user-group.md)

用户群组的预设还不够细的时候，使用ACL 针对单一用户，单一文件来进行r,w,x 的权限设定



[shell](./shell.md) 和nologin shell

/etc/nologin.txt

[Bash 语法和常用命令](./bash_20200117.md)



PAM 提供验证机制

/var/log/secure



杀死系统服务时，不建议用kill，而是 `systemctl`



/var/log/...  记录了日志信息