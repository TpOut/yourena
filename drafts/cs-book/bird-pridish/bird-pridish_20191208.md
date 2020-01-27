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



iconv (编码格式转化)

tab, 命令名补齐，文件名补齐

启动脚本在/etc/init.d/ 这个目录下，或systemd



UID 在/etc/passwd；GID 在/etc/group  

密码 在/etc/shadow

uid, 0 表示系统管理员，1-1000一般系统使用（200之前是发行版自己创建，其他是用户根据需要）为系统账号，最后的才是用户常规的账号

创建系统账号时不会默认创建/home



`passwd` 修改账号信息，如密码

`authconfig` 查询加密机制, `--test | grep hashing `

`authconfig-tui` 外部身份认证系统



用户创建时在passwd 中出现的gid 是初始群组，即默认就添加的群组。后来使用命令添加的，在 group 中可以看到。

```shell
usermod -a -G <group> <user>  # usermod 可以调整useradd 的设定

# 系统管理员可以专门创建群组管理员，进行群组的管理，/etc/gshadow
gpasswd
```

`groups` 查到的群组中第一个为有效群组，即现在操作时的所在群组。

`newgrp` 可以在已有群组中切换有效群组



`useradd` 用户的创建，/etc/default/useradd；/etc/login.defs

`passwd` 设定密码，/etc/pam.d/passwd（密码检查pam_cracklib.so）

`chage` 查询和修改用户的密码信息

`userdel` 删除用户相关数据



用户功能：

`id` 查询用户UID/GID 相关信息

`finger` 查询用户相关的信息，大部分是/etc/passwd 内的

`chfn` change finger

`chsh` change shell 



`groupadd`

`groupmod`

`groupdel`



ACL(Access Control List)

针对单一用户，单一文件来进行r,w,x 的权限设定

```shell
dmesg | grep -i acl
setfacl
getfacl
```



`su` 一般使用`su -`  需要使用exit 退出

`sudo`  switch user， /etc/sudoers

`visudo` {%群组名} 可以设定群组的sudo 权限，同时可以限定命令的参数格式





shell 和nologin shell 的概念还有些模糊

/etc/nologin.txt



PAM(Pluggable Authentication Modules)，提供验证机制

/var/log/secure



`last` 用户当月的登录信息

`w``who` 当前已登录的用户

`lastlog` 最近登录时间 /var/log/lastlog



`write` 和其他用户聊天

`mesg` 设置是否接受其他用户信息（root 除外）

`wall` 广播消息给用户



`mail` /var/spool/mail



`pwck` passwd check

`chpasswd` 读取/etc/login.defs 进行加密



`quota` 磁盘分配

可以限制群组，用户，文件的最大自盘配额



`RAID` 

`LVM` Logical Volume Manager



计划服务

`at` 只执行一次，需要atd 服务，/var/spool/at，/etc/at.allow, /etc/at.deny

```shell
systemctl restart atd
systemctl enable atd
systemctl status atd
```

`atq` at query

`atrm`

`batch` 在cpu 负载小于0.8 的时候，才执行任务

`crontab` 循环执行，/etc/crontab，需要crond 服务，/etc/cron.allow, /etc/cron.deny, /var/spool/cron	

`anacron` 执行超时而未执行的任务（如停电导致）

`uptime` 查看负载



`PID`

`PPID` parent PID

`ps` process status

```shell
ps -l  # 查阅自己bash 的进程 
ps aux  # 所有在运行的进程
```

`kill`

名字之后加个d 一般是指daemon，如httpd



`ctrl + z` 将工作暂停并转到后台

`jobs` 目前的后台工作状态

`fg` 将后台工作提到前台

`bg` 将后台暂停的工作转为运行



`nohup` 

所有的服务启动脚本都放置在/etc/init.d/ 下面。基本命令包含<daemon> start, stop, restart, status

/usr/lib/systemd/system/

/run/systemd/system/

/etc/systemd/system/



杀死系统服务时，不建议用kill，而是 `systemctl`

系统服务这边，很多服务都没接触过，内容看起来就比较陌生，基本无消化



/var/log/...

记录了日志信息

`dmidecode` 解析cpu、主板、内存型号等

`lspci` 系统内总线相关装置

`lsusb`  

`iostat` 磁盘开机之后存取的数据