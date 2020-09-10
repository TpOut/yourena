相关的配置类是`Resources.getConfiguration`   

主要包括：

- 国家区域:

    手机国家码（手机网络码）、语言区域、布局方向

- 长宽：

    最小窄边大小、最小宽/高（包括非屏显宽高）

- screenLayout

    小中大等粗粒度、长宽比、是否圆形

- display

    是否广色域、是否高动态范围

- 设备

    横竖屏、屏幕导航方式、硬软键盘（keys 包括硬软两者）可用性和硬键盘类型、导航键可用性和类型、

- 模式（UIMode）

    场景模式（车载、手表等），日夜模式

- dpi

    如xxhdpi、nodpi、anydpi、nnndpi

- api 版本



**配置变化：**

有常规的平台提供：onSaveInstanceState  

或者屏蔽平台的重建，自己在回调处理逻辑：