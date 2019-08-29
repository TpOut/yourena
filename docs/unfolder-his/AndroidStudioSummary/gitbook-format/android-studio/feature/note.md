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
