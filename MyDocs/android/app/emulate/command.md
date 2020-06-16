```
#查看 ANDROID_SDK_HOME 下的列表  
emulator -list-avds  
#启动  
emulator -avd avd_name [ {-option [value]} … ]  
emulator @avd_name [ {-option [value]} … ]  

#查看文件夹和文件：
emulator -verbose
emulator -debug init
emulator -help-datadir

#模拟器在用户数据磁盘区(userdata-qemu.img)保存 应用、自身状态数据，如要不要数据
-wipe-data

#指定对应文件路径  
-kernel
-system
-ramdisk
# userdata.img
-initdata
-init-data
# userdata-qemu.img
-data
-sdcard

#保存临时文件
-cache

# dns配置，serverlist用`,`分割  
emulator -dns-server <serverList>  

# 配置代理
-http-proxy <proxy>
# proxy的格式
http://<machineName>:<port>  
http://<username>:<password>@<machineName>:<port>
#诊断代理问题
-debug-proxy
```

## 命令列表

相关的命令还在持续开发中。。。
https://developer.android.google.cn/studio/run/emulator-commandline#startup-options