## 基本内容

为了和物理设备体验一致，模拟器中，捆绑google play store的system镜像会被一个release key签名，即无法修改权限为root。如果有需要，可以在aosp中获取一个没有google play store的镜像

功能支持：拨入电话、文本短信、设置location、不同网速、屏幕旋转
传感器支持: 温度、磁场、物体靠近（Proximity，比如脸靠近的时候可以拨打电话）、光感、气压、湿度
不支持: 蓝牙，nfc,sd卡插拔，外接耳机(headphone),usb
api 25 >=25 ,会连接到一个叫“AndroidWifi”的模拟wifi，可以在命令行启动时带参数禁止：`-feature -Wifi`

拖拽到模拟器的文件，会被放到/sdcard/Download/目录，可以用as的Device File Explorer或者模拟器的“下载”、“文件”app查看

## 快照

包含设备系统设置，应用状态，用户数据
每个AVD,你可以有一个quick-boot的快照，和无数个常规快照 quick-boot可以让你重启的时候，直接进入保存的状态（个人经常遇到问题）

可以在avd的设置-快照栏，设置是否在退出时自动保存quick-boot状态
也可以在 tools > avd manager 中：

- 设置单次正常启动（cold-boot）：选择一个模拟器右侧的下拉菜单，点击cold boot now
- 设置每次正常启动：编辑avd，Show Advanced Settings ==》Emulated Performance ==》Select Cold boot.

问题：

- 不支持 Android 8.0的arm 镜像
- 在软件渲染开启的时候失效。可以尝试 编辑avd，把graphics改成hardware或者automatic
- 快照的保存是内存敏感操作。如果ram不够，会把快照的一些信息写入硬盘，导致速度非常慢。所以在保存的时候要尽量保证ram比较空闲

## 触屏

鼠标代替手指比较好理解
但是 pinch和spread,不明白。。按住ctrl操作，感觉和普通的点按一样啊

## 操作

在#navigate
F1 打开 extended control(ec) 面板

> 在ec的setting中，有全部的快捷键
>
> Ctrl + m 模拟menu按钮 ctrl + shift + r 直接打开 screen record 选项

截图路径默认在桌面，可以在ec的setting中设置

## 定位

Latitude：10进制为 -90.0 ～ +90.0 degrees； 60进制（sexagesimal）为-90 ~ +90 degrees, 0 ~ 59 min, 0.0 ~ 60.0 seconds
Longitude: 10进制为 -180.0 ~ 180.0 ; 60进制（sexagesimal）为-180 ~ +180 degrees, 0 ~ 59 min, 0.0 ~ 60.0 seconds
`+` 表示东、北; `-`表示西、南
Altitude： -1,000.0 ～ +10,000.0 meters

支持从文件导入，模拟运动： GPS exchange format (GPX) or Keyhole Markup Language (KML) file:

## 网络

### 网络类型

GSM: Global System for Mobile Communications
HSCSD: High-Speed Circuit-Switched Data
GPRS: Generic Packet Radio Service
EDGE: Enhanced Data rates for GSM Evolution
UMTS: Universal Mobile Telecommunications System
HSPDA: High-Speed Downlink Packet Access
LTE: Long-Term Evolution
Full (default): Use the network as provided by your computer

voice和data状态：
Home (default)
Roaming
Searching
Denied (emergency calls only)
Unregistered (off)

### 网络地址

每个模拟器都是运行在独立于开发机器网络的虚拟路由/防火墙服务下的
模拟器不知道开发机器，也不知道其他模拟器，只知道自己连通了 这个 路由/防火墙

每个路由管理 10.0.2/24 网络地址空间：

- 10.0.2.1 是路由/网关 地址
- 10.0.2.2 宿主回路接口的别称(loopback)，对应开发机器的127.0.0.1
- 10.0.2.3 第一个DNS服务器
- 10.0.2.4/.5/.6 可选的第二、三、四个DNS服务器
- 10.0.2.15 模拟器网络接口
- 127.0.0.1 模拟器设备回路接口

所有模拟器都被赋予上述路由规则。

### 本地网络限制

- app的网络借助模拟器，模拟器的借助开发机器。
- 模拟器自身不限制TCP,UDP 链接/消息
- 视环境而定，可能不支持其他的协议，如ICMP--即不支持ping
- 目前不支持IGMP或者广播

### 网络重定向

使用模拟器console

> 或者使用adb, 当前adb不支持移除重定向，哪怕是kill

### 配置DNS

在`/etc/resolv.conf`中解析（奇怪的是找不到这个文件了，而是resolvconf文件夹，里面还有文件）或者命令行

### 配置代理

目前只支持所有TCP连接，而UDP不支持。

使用命令行或者直接配置`http_proxy`环境变量，一劳永逸

### 多个模拟器之间连接

A,B是运行在同一个主机的模拟器

而A创建了一个服务，需要C链接：

- 让服务监听 10.0.2.15:
- 重定向 Host:localhost: to A:10.0.2.15:
- 让B访问主机： 10.0.2.2:

如：

```
B listens on 10.0.2.15:80
On the B console, issue redir add tcp:8080:80
C connects to 10.0.2.2:8080
```

## 多个模拟器拨打语音电话或者发送短信

语音电话：可以在一个模拟器的拨号软件上，直接拨打目标模拟器的端口号。 发送短信：使用SMS软件，然后端口号作为目标号码，发送消息即可

## 渲染

关于OpenGL ES渲染的渲染器种类和api版本

### 图像加速

模拟器图像加速可以使用开发机器的图像硬件优势，如GPU渲染
Android设备使用OpenGL作为内嵌系统(OpenGL ES或GLES)渲染2d和3d图像的支持

## 其他

Directional Pad：在很多设备不支持，一般有android watch可以 Fingerprint： 需求 api >= 23
有些app需要支持快捷键，可能会和模拟器的产生冲突。可以在 设置--常规--发送键盘快捷键 中处理。