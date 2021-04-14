
要理解一个 root activity 的概念 = activities that start a new task   

xml 中 activity 属性有 

- allowTaskReparenting  

  修改页面再次显示时关联的堆栈  

- alwaysRetainTaskState      

  永远保留状态（saveInstance 的意思么）？默认会在一定时长不操作后被清除  

- autoRemoveFromRecents

  退出最后activity（也就是root activity ?） 后，不再recent 保留  

- clearTaskOnLaunch  

  清除root activity 以上的activity     	

- documentLaunchMode  

  可以设置recent 页的记录逻辑  

- taskAffinity  

  
  
  excludeFromRecents  

finishOnTaskLaunch // 这个逻辑就很奇葩  

maxRecents  

noHistory  

relinquishTaskIdentity  



launchMode  

taskAffinity  



`ActivityManager.TaskDescription`  

