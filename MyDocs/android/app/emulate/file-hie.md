## 理解默认文件夹和文件

Android模拟器使用 Quick Emulator (QEMU) hypervisor.
最初版本的模拟器使用 QEMU 1 (goldfish),之后的版本都使用 QEMU 2 (ranchu).

### AVD系统文件夹

里面存放用来模拟操作系统的Android系统镜像，具有

- 平台标识
- 同类AVD共享的只读文件
    - API 等级: 如Marshmallow 预览版 android-M; release版 android-23
    - CPU 架构: x86
    - Android 变体: 一般对应功能，如 google_apis, android-wear

系统文件夹的默认路径：
`~/Library/Android/sdk/system-images/android-apiLevel/variant/arch/`
使用 `-sysdir` 来指定系统文件夹路径

模拟器会读取文件夹中的这些数据：

- kernel-qemu/kernel-ranchu: 二进制内核镜像
- system.img: 纯净版只读镜像；包含API level和变体需要交互的数据，以及系统库
- ramdisk.img: 启动部分镜像。是system.img的一个子集，包含小部分binaries和初始化脚本。会在系统镜像被挂载之前加载。
- userdata.img: 纯净版数据区域。对应data/ 文件夹，里面的数据是可写的。在创建新的avd或者对老的avd指定-wipe-data时使用。

### AVD数据（内容）文件夹

avd示例及其所有可修改的数据，默认路径(name即avd名字):
`~/.android/avd/name.avd/`
使用 `-datadir` 指定avd数据文件夹

这个文件夹包含的数据：

- userdata-qemu.img: 不使用userdata.img的时候使用这个，独立非共享。
- cache.img: 对应cache/ 文件夹，保存临时下载文件。会在关机时删除，可以使用`-cache`保留临时文件
- sdcard.img: 可选的。可以用avd管理器或者使用mksdcard工具创建这个镜像。

