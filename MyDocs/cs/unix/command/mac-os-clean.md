```shell
diskutil apfs list # 查看具体的磁盘使用情况
du -shc /System/Volumes/Data/ # 在看对应的文件夹的大小
# 然后发现User 文件夹过大
du | sort -h # 这里看如果加参数看，大概率会没有权限，就直接du，然后sort 排序查看了
```

场景：

因为其他文件夹太大，一气之下重装了系统。

结果就装了一个AS，发现其他卷宗 竟然就20G 了。

赶紧查了一波。

如上先看到数据volume 过大，再看到data 里user 文件夹过大。  

再看下去对应到里面，可以找到大文件



