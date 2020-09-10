操作系统是多用户的  

Android app 实现时，每个app 都属于不同的用户。对应一个unique linux user id，并由此控制文件访问权限  

每个app 都有自己的jvm  

每个app 都运行在独立的进程，但是进程的创建和销毁时由系统控制的  



默认情况下，Android 系统秉承最小权限原则  

但是两个app 可以共享linux user id  

且可以动态申请权限  



  





