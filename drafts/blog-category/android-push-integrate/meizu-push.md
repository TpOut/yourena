flyme系统。  

唯一标识叫做PushId;   
根据设备标识，appid生成；  
注册后默认30天失效，期间调用注册实际不会有请求操作。  

统计包括展示数量、点击数量  

单个业务推送，限制 500条/秒（可调整）； 1000次/天；
一个ip请求api有限制/小时  

全量推送也是1000次/天  

## 推送方式  
- 非任务推送  
PushId 推送，别名，直接推送即可  
- 任务推送  
还包含标签推送，应用全部推送  
首先需要获取taskId, 然后用taskId 绑定推送消息（有点像封装好的接口）；客户端可以获取    

## 标签  
一个app用户标签上限100（可配置）;一个标签不能超过20字符

失效条件：  
标签对应的PushId 已失效  

## 别名  
别名（不能超过20字符），一个app用户仅能设置一个别名，一个别名可以指向多个app用户

有点整合了小米的user account  

## 通知点击  
打开 应用页面，应用内页面；  
打开 uri , 客户端自定义  

## 透传
已经停用  


集成推送平台   
OPPO MANUFACTURER:OPPO model:R7Plusm  
VIVO MANUFACTURER:vivo model:vivo X6D  
HUAWEI MANUFACTURER:HUAWEI model:MHA-AL00 brand:MHA  
MEIZU MANUFACTURER:MEIZU  
