

```
# doze 
$ adb shell dumpsys battery unplug
$ adb shell dumpsys deviceidle step
$ adb shell dumpsys deviceidle -h
```

```
# standby
$ adb shell am broadcast -a android.os.action.DISCHARGING
$ adb shell am set-idle <packageName> true

# exit
$ adb shell am set-idle <packageName> false

# 特别注意通知和后台任务的恢复
```

