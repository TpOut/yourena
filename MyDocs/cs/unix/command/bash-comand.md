查看密码

```shell
/root/anaconda-ks.cfg
```

kickstart

查看硬件组件的型号

```
cat/proc/cpuinfo  
lspci   
```

内核版本

```
uname -r
```



`history`



**alias**

`ll` 等同于 `ls -l`



apt-aptget的区别：https://itsfoss.com/apt-vs-apt-get-difference/  



日期和时间`date` ，日历`cal` , 

显示支持的语言 `locale`， 其显示的属性，除了`LANG` 可以直接赋值之外，其他的可以通过`export` 配置

基础计算器：`bc` 



`tab` 命令不全，不管是命令本身，命令参数（包括文件目录）

`ctrl+c` 终止操作

`ctrl+d` 模拟文件尾

`shift+[pageUp | pageDown] ` 翻页



```shell
# 求助
<command> --help
man <command>

#unix like 都有
man man
man -f man # 搜索说明中有 man 关键字的命令
man -k man # 模糊搜索..

#linux
info info
```



`who` 查看谁在linux 中

`netstat -a` 网络状态

`ps -aux` 查看执行程序

`sync` 数据同步写入硬盘，正常关机命令执行前都会先执行同步（root 用户和常规用户的同步数据会不同）

`shutdown` 一般惯用的关机，还有 `reboot` 重启 `halt` `poweroff` ，这几个命令都会调用 systemctl 



`su -` 切换身份， `exit` 退出切换

```shell
# 修改文件属性
chgrp
chown
chmod u=rwx,go=rx .fileName
chmod a+x .fileName
chmod a-x .fileName

umask
```

`mkdir`, `rmdir`

`touch`，建立空文件，修改文件时间

`cp` , `mv` , `rm`

`cat`

```shell
# 目录切换
.
..
-
~
~<account>
`cd `equals `cd ~` 
# 目录信息
pwd (print working directory)
ls (list)
basename # 获取文件名
dirname # 获取路径文件夹

# 隐藏属性, xfs支持部分，ext2/3//3 支持
chattr
lsattr
```

`echo`

```shell
# 文件查看
cat (concatenate), tac
nl
more , less 
head , tail
od

file # 基本数据
```

```shell
# 管线|，将前面的命令输出作为后面指令的输入
firstCommand | secondCommand
```

`which` 只能找PATH 路径下的

`type`

`whereis` 只找部分指定目录

`locate` 只找 `/var/lib/mlocaTE` ,

`updatedb` 根据`/etc/updatedb.conf` 来更新`/var/lib/mlocate `的数据库

`find` 比较耗硬盘

```shell
find / -exec <noAliasCommand> {} \;
```



```shell
# 文件系统
dumpe2fs

blkid  # 系统目前有被格式化过的装置
ls -l /lib/modules/$(uname -r)/kernel/fs # 支持的文件系统
cat /proc/filesystems # 已加载到内存中支持的文件系统
```





---

https://github.com/dylanaraps/pure-bash-bible
 https://wangdoc.com/bash/intro.html

  幂等bash脚本：https://arslan.io/2019/07/03/how-to-write-idempotent-bash-scripts/  
  bash alias:https://medium.com/@raimibinkarim/9-bash-aliases-to-make-your-life-easier-3e5855aa95fa  

syslog日志指南：https://devconnected.com/syslog-the-complete-system-administrator-guide/  

shell 命令解释：https://www.explainshell.com/

shell 命令检查：https://www.shellcheck.net/

Curl ：https://catonmat.net/cookbooks/curl

lsb: https://www.linuxbase.org/lsb-cert/productdir.php?by_lsb

