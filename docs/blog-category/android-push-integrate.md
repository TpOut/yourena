公司服务：信鸽，百度云，阿里云移动
现有的推送工具：umeng，个推，极光  
厂商： 小米，华为，魅族，oppo, vivo

RegisterationId 的生成： imei,mac,androidId ; 外部存储
透传可能无法保证到达率？  
## 信鸽  
https://xg.qq.com/docs/android_access/xg_push_introduction.html  
厂商通道是保存了一个厂商token和自身token的映射，app端有个推送服务    
自身实现主要是一个单独的Service进程和app进程备份service（用于互相拉起），TCP Socket长连接和心跳  

消息形式：通知和透传  
账号、标签（地理位置，应用版本号）和设备  
消息点击  

支持动态加载厂商通道，非完全适配。  

兼容Android P,默认支持HTTPS, HTTP  

广播接收器  

开屏和网络切换  

服务会在很多情况尝试拉起，如蓝牙状态，电量连接或断开 的系统广播监听  

## 个推
可能发送广告？

## 尝试

      抵达回调，点击回调，支持透传  
小米：        ，   不   ，
华为：   不   ，        ，
魅族：        ，        ，   不  

## 路人混合  
https://leancloud.cn/docs/android_mixpush_guide.html  
华为透传无法接收。  


 


