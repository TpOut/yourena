
##[UI 表现](https://developer.android.google.cn/training/testing/performance.html#measure)
z最好保持在60帧，不要丢帧，或者说延迟
y意味着每帧在16ms左右完成
###系统工具
[dumpsys](https://source.android.com/devices/tech/debug/dumpsys)
>adb shell dumpsys gfxinfo <PACKAGE_NAME>
//6.0以上
>adb shell dumpsys gfxinfo <PACKAGE_NAME> framestats