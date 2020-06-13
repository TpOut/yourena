doze  



app standby buckets

[`UsageStatsManager.getAppStandbyBucket()`](https://developer.android.google.cn/reference/android/app/usage/UsageStatsManager#getAppStandbyBucket()). 查看级别

```shell
# 设置级别
$ adb shell am set-standby-bucket packagename     active|working_set|frequent|rare
# 多包（应该是bundle 吧
adb shell am set-standby-bucket package1 bucket1 package2 bucket2...  
# 查看级别，不传即所有
adb shell am get-standby-bucket [packagename]  

```

battery saver

```shell
# 设置非充电模式
$ adb shell dumpsys battery unplug
# 低电模式
$ adb shell settings put global low_power 1
# 重置
$ adb shell dumpsys battery reset
```