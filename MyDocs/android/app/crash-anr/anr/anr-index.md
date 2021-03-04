SystemServer  

InputManagerService  



[anr define]()  

线程状态：  

Thread.State / Thread.cpp   

[anr-practice]()   



tips:  

有些手机无法获取trace 文件：

```shell
adb bugreport log.zip
```



```shell
setenforce 0 （不执行这个，会无法写入文件）

chmod 777 /data/anr

rm /data/anr/traces.txt

ps

kill -3 PID

adb pull data/anr/traces.txt  ~/traces.txt
```



