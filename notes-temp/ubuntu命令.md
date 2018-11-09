ctrl+p: 前一个命令
ctrl+n: 下一个命令

# 获取数据
wget -qO- 127.0.0.1

curl 127.0.0.1

```
$ sudo groupadd docker  //添加分组
$ sudo usermod -aG docker $USER  //分配入组
```

**修改文件夹归属和权限**  
```
$ sudo chown "$USER":"$USER" /home/"$USER"/.docker -R
$ sudo chmod g+rwx "$HOME/.docker" -R
```
**自启动**  
```
$ sudo systemctl enable docker  
$ sudo systemctl disable docker  
```
Ubuntu14.10之下  
```
$ echo manual | sudo tee /etc/init/docker.override  
```
**检查**  
```
$ sudo chkconfig docker on
```
