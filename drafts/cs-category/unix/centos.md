装机界面自带烧机功能：

[Troubleshooting] -- [Run a memory test]

重启 X Window :  `alt + ctrl + backspace`



终端界面：

```shell
# 书本，centOS7
ctrl + alt + [F1...F6] // 对应 tty1 ~ tty6
// tty 界面再切换 F1 就是回到 X Window

# 实操，centOS8
F1 是锁屏，F2 是X Window， F3开始是tty，而且没看出来是tty几，且不支持中文

startx # tty界面 启动图形界面
graphic.target # 设置默认是否启动图形界面
```



用户的home 目录，即`~`是一个变量，root用户的在 /root，其他用户的在/home/userName

而root 的提示符是`#`, 一般用户的提示符是`$` 

tty 的前面内容来自`/etc/issue` 文件

注销命令：`exit`



shell 如果内容太长，想要换行，则用反斜杠`\`接换行符来实现。



man page 的数据，一般存放在`/usr/share/man` 中 , `/etc/man_db.conf` (有些是 man.conf, manpath.conf, man.config)

info 在`/usr/share/info`

how-to 文档在`/usr/share/doc` 



nano 编辑器的 `^` 表示`ctrl` , `M` 表示 `alt`

群组owner/groups/others , 权限read/write/excute  

用户相关信息记录在`etc/passwd` 文件，个人密码记录在`etc/shadow`, 组名记录在`etc/group`





![文件属性](D:/yourena/drafts/cs-category/unix/文件属性.PNG)

s表示sockets类型，，一般在/run、/tmp目录看到

p表示pipe 类型，用于解决多个程序同时读取一个文件所造成的错误

原书225页，有对根目录FHS 进行介绍

![fhs](D:\yourena\drafts\cs-category\unix\fhs.PNG)

权限rwxs，对于文件而言，是内容读写执行；对于目录而言，是目录树可见（和文件与目录树的操作无关），目录树可操作，目录可进入

权限对应`chmod` 命令行，每位为421



i-node  



系统语言所在的位置`/etc/locale.conf`

进入目录需要有执行权限

所有人都可以工作的/tmp 目录



登录数据记录在`/var/log/wtmp `里

属于data file 需要使用`last` 指令读取



/usr 是Unix SoftWare Resource 的缩写

根据FSH建议，var最好能单独分区



cp -s 是软连接，-l 是实体连接。注意文件信息的拷贝



more时

space 下一页，enter 下一行，/字符串 搜索（n到下一个词）

q 立刻离开，b 或 ctrl+b 上一页（只对文件有效）

man 就是调用less 来查看说明文件的



文件的时间，包括文件内容修改时间(modification)，文件属性修改时间(status)，文件内容被访问时间（access）



文件默认权限为666（已去掉执行权限），文件夹默认为777。umask 指定创建文件时需要去掉的权限。前者再去掉一次后者的权限，就是创建时的权限。

umask的基本设定在 `/ect/bashrc` 中，但是修改建议使用`~/.bashrc` 



文件特殊权限，看一些文件的时候会发现x 的位置，显示的是s。甚至还有t，类似`[rw[x|s|t]]`。它们一般是系统的账号和系统的程序使用。

- s 在拥有者的x 位置

  表示SUID（ Set UID ），表示对二进制程序进行执行之时，会短暂的授予拥有者权限。

- s 在群组的x 位置

  表示SGID（Set GID），表示对二进制程序执行之时，或者进入文件操作之时，会短暂的修改用户群组权限。

- t  在其他人的x 位置

  表示SBIT（Sticky Bit）。当用户U 对于目录D 是群组或其他人，且拥有w 权限，常规情况下，U 是可以操作D 中任何人建立的目录或文件。但是加上t 之后，U 只能操作自己建立的目录或文件

在设置上，这个权限是一个新增的位，类似 `[sst][rwx][rwx][rwx]`。对应的SUID 为4, SGID 为2，SBIT 为1。

chmod 的时候，假设要设置666 的SGID权限 。则输入2666

注意st，都是“执行”时获取的权限，如果本身没有x 权限，即使设置了也是空，会显示成大写的ST



data 文件，二进制文件，文本文件

virtual filesystem switch 

df: 列出文件系统的整体磁盘使用量

du:评估文件系统的磁盘使用量