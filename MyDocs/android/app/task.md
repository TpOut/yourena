后台打开条件（对象是app）：  

- 有可见窗口，如前台activity 

- 前台task 的返回栈中有activity  

- 在“最近”中有task 的返回栈中有activity

  > 此时是静默打开。不影响当前任务

- 刚打开过activity  

- 刚`finish()` 过条件1、2 中的activity  

- 有系统绑定的服务 -- 具体服务有限制

- 有服务，被其他app 绑定（其他app 要可见  

- 短时间内（秒）收到系统发送的`PendingIntent`

- 短时间内（秒）收到其他可见app 的`PendingIntent`  

- 特殊硬件 - `CompanionDeviceManager`   

- 

