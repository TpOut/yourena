#### 连接app

步骤：https://developer.android.google.cn/training/basics/firstapp/running-app#RealDevice  

**linux 注意点**：

连接设备前提是安装adb和配置udev规则

- 可以用apt安装adb,会配置一个默认的udev（由社区管理）。需要保证自己在plugdev组内

    ```
    apt-get install adb
    ```

- 或者安装Androidstudio，需要自己配置一个51-android文件。

然后就可以在列表上(`adb devices`)看到了

如果在连接设备上有什么问题，可以查看工具栏的 tools > connection assistant

