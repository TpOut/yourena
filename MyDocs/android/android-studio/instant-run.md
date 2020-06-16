## instant run介绍

暂时不支持 app bundle

|   项目源文件修改行为   |                     对应instant run行为                      |
| :--------------------: | :----------------------------------------------------------: |
| 对一个已有方法代码修改 | hot swap: app持续运行.新实现的方法会在下次用到时调用. 但是因为对象无法重新初始化,所以需要重启activity(默认行为). |
|      删改现有资源      |            swarm swap: app持续运行.会重启activity            |
|     结构性代码改动     |                    cold swap: 必须重启app                    |
|    manifest关联改动    |                        会重新部署app                         |

- 工具栏run下面的一个选项重启activity, 或者用run,或debug按钮重启app

    > 如果要禁止instant run时自动重启activity，可以在设置的instant run选项中关闭

- 结构性代码改动包括:

    - 增删改注解、实体域、静态域、静态方法signature、实体方法signature
    - 修改继承的父类
    - 修改实现的接口列表
    - 修改类的静态 块(initializer)
    - 重新排序使用动态资源id的布局元素

- manifest关联改动：

    - 修改manifest

    - 修改被manifest引用的资源

    - 修改Android系统ui，如widget（应该是特指桌面widget）和notification

        > 需要注意gradle可以修改versionCode和versionName

    windows defender影响：https://answers.microsoft.com/en-us/protect/forum/protect_defender-protect_scanning/how-to-exclude-a-filefolder-from-windows-defender/f32ee18f-a012-4f02-8611-0737570e8eee

### 限制

在#ir-limitations

- as2.3有一些限制，如有兴趣，可以看原文
- 不能同时运行在多个设备
- minSdkVersion >= 21, Instan run会自动将app配置成multidex.因为instant run 只在debug版本有用，你在发布release的时候可能需要配置一下（没看懂这个因果关系？）
- 运行或调试instrumented测试时，会在设备的同一个进程加载debug apk和 test apk。所以不支持instant run
- 性能分析器时需要关闭。因为instant run本身就有一些小的性能影响，而在hot swap的时候性能影响很大。同时重写方法还会让堆栈跟踪变得更复杂
- 第三方插件冲突。as会禁止JaCoCo库和ProGuard,因为debug不需要但是有可能和instant run混合产生问题
- 多进程。只支持主进程hot和swarm swap。其他进程的修改最多支持cold swap
- 使用work profile(or other secondary profile)打开instant run的app会崩溃.具体思路查看原文，目前还不清楚这个profile.