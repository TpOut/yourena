adb 提供了开发机器操作设备很多功能  

开发机器通过adb 命令，启动adb 客户端，用来输入命令。而每个设备都有一个进程运行adbd（daemon）。因此还有一个在开发机器上的后台服务进程，用来处理客户端和daemon 的交互。  

默认占用tcp 端口 5037，前16个设备占用5554~5585 的端口，偶数用于console 连接，奇数为adb 连接    

如`emulator 1, console:5554 ; emulator 1, adb:5555`  



部分功能：

- 可以查看所连接设备

    （模拟器某些情况可能不显示  

    结果中的设备序列号，可以用于其他命令来指定某一个设备  

- 可以配置为无线连接

- 可以安装app

- port forward  

- 拷贝（拉取和推送）文件数据  

- 进入或退出shell

    - 支持大部分的unix 命令行，如`ls` ，基于toybox  

    - 可以`setprop`

    - 调用am

        启动activity、Service，发送广播、测试用例(instrument)  

        设置app 为debug 模式、监控崩溃或anr  

        修改屏幕属性、停止进程（强制或不影响用户，或后台）  

        dumpheap、profile 分析、查看屏幕尺寸、查看屏幕兼容模式   

        转化intent 为URI  

    - 调用pm

        展示所有package 列表、获取package 的路径、卸载安装package、清除package 数据、可用一个package 或组件、设置安装路径  

        展示所有权限列表、授权、取消授权  

        展示所有测试包  

        展示所有系统功能、设备上的库

        展示users、创建移除用户、使用户不可用、最大上限  

        缩减缓存  

    - 调用dpm（device policy manager）

        设置active admin  

        设置profile 所有者

        设置设备所有者  

        强制DPC 打印网络、安全相关日志  

- 拍照

- 录屏

- 查看ART profile  

- 重置测试设备  

- 查看数据库表数据和创建语句（要root 吧

- [查看日志](./logcat.md)



---

Last updated 2020-04-14.

