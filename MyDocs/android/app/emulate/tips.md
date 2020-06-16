模拟器mac问题：Warning: No DNS servers found
检查是否有/etc/resolv.conf，没有则`ln -s /private/var/run/resolv.conf /etc/resolv.conf`

## 已知问题

https://developer.android.google.cn/studio/run/emulator-troubleshooting

Q: 启动时挂起或者崩溃 A: 起码需要2GB的磁盘空间

Q: 保存和加载快照的时候很慢。 A: 可能是 杀毒软件(antivirus)的原因。 首先因为他会监管所有的读写操作，所以会影响模拟器的操作。最好是把模拟器加入白名单中，你也可以测测看。
然后有些杀软和模拟器不太兼容，如Avast，试试disabling Use nested virtualization when available and Enable Hardware assisted virtualization in the Avast Troubleshooting settings

Q: Android模拟器更新后运行很慢 A: 看原文

## 索引

AS 模拟器需求：https://developer.android.google.cn/studio/run/emulator#requirements_and_recommendations AS 模拟器的软硬件配置：https://developer.android.google.cn/studio/run/managing-avds
AS 模拟器的加速配置：https://developer.android.google.cn/studio/run/emulator-acceleration AS 模拟器不同启动工具的对比：https://developer.android.google.cn/studio/run/emulator-comparison
传感器值计算：https://developer.android.google.cn/reference/android/hardware/SensorEvent#values

Quick Emulator (QEMU) hypervisor： https://wiki.qemu.org/Main_Page

