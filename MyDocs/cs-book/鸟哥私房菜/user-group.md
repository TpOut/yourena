UID 在/etc/passwd；GID 在/etc/group  

密码 在/etc/shadow

uid, 0 表示系统管理员，1-1000一般系统使用（200之前是发行版自己创建，其他是用户根据需要）为系统账号，最后的才是用户常规的账号



创建系统账号时不会默认创建/home



用户创建时在passwd 中出现的gid 是初始群组，即默认就添加的群组。后来使用命令添加的群组，在 group 中可以看到。



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