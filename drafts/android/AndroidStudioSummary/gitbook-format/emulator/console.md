## 控制台
可以使用console 控制模拟环境，如管理端口重定向，网络状态，电话事件等。  

感觉命令行还是要依赖实例，而实例必须可视化。。那么还是可视化操作比较快。。毕竟还要学一套命令  

```
#端口可以在模拟器拓展面板的标题看到，或者adb devices
telnet localhost <console-port>

#帮助
help
help <command>
help-verbose
```
telnet之后按提示认证即可。如果不需要认证，可以删除文件内的认证token

退出console: quit/exit  

模拟器命令行参数：https://developer.android.google.cn/studio/run/emulator-console#querycontrol 
