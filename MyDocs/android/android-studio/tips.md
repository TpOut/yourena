## tips

assets关键类： AssetManager

## 模块

Android中的模块包括： Android 应用模块、库模块(构建后为aar/jar) ， Google App 引擎模块（[文档](https://cloud.google.com/tools/android-studio/docs/)）

## 项目结构

[Android项目结构](https://developer.android.google.cn/studio/projects#ProjectView)

### manifest

[AndroidManifest文件结构](https://developer.android.google.cn/guide/topics/manifest/manifest-intro)

## resource

[resource格式说明](https://developer.android.google.cn/guide/topics/resources/providing-resources)

## xml

[xml中tools工具支持的属性索引](https://developer.android.google.cn/studio/write/tool-attributes)





[Android开发工作流](https://developer.android.google.cn/studio/workflow)

[新建项目](https://developer.android.google.cn/studio/projects/create-project)

[android支持库23.2](https://android-developers.googleblog.com/2016/02/android-support-library-232.html)

[开发者选项](https://developer.android.google.cn/studio/debug/dev-options)





## 便捷功能

### 查找官方代码示例

官方代码示例，通过 file > new > import sample 查找；
或者选中代码段（会高亮），右键选项 find sample code(试了几段没卵用) .

### 可视化添加 app link

Android App links是一些HTTP URLs，它能够直接让用户跳到app中确定的内容。
这有助于分析用户喜好，并且方便分析。
AS2.3以上可以让你直接可视化操作，通过 tool bar > tools > App Links Assistant
原文连接：
https://developer.android.google.cn/studio/write/app-link-indexing

## 调试

Thread.dumpStack().

当你从别的地方获取崩溃日志的时候，可以在工具栏， analyze > analyze stacktrack中黏贴
这样就可以和日常调试一样，支持点击跳转。

如果有很多这样的查看需求，可以把面板下方的自动分析勾起来

### 布局调试

tools > layout inspector

可以只显示部分view tree（右键选项）
截图文件会被保存在：`project-name/captures/*.li`

## 查看设备上的文件

大部分设备数据是看不到的，除非root或者使用AOSP中系统镜像构建的模拟器



[AS 可配置项](https://developer.android.google.cn/studio/intro/studio-config)，包含：

- studio.vmoptions

- idea.properties

- settings.jar

- 低配设备的配置建议

    > 节能模式、lint调整、gradle离线模式、关闭并行编译　　

- instantRun

- jdk版本

- 代理

[idea--外部工具](https://www.jetbrains.com/help/idea/2018.1/configuring-third-party-tools.html)

[AS的Accessibility features](https://developer.android.google.cn/studio/intro/accessibility)

