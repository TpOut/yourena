	<font color=red>allowTaskReparenting 需要看下现象  </font>  

有一定规则， 看原文就行了  

xml 中 

- intent-filter  

  这里的priority 和order 有点疑问,  

  为什么要三个类型  

  - `<action>`:    
  - `<data>`:  

- `<activity>` `<application>`  

  外部访问 export / permission / icon / label   

  - `<debuggable>` 
  - `<networkSecurityConfig>`    
  - testOnly

- `<activity>`  

  外部访问 export / permission / icon / label   

- provider

  - grant-uri-permission  

- instrumentation

- installLocation  
- [screen]() 

- uses-configuration / uses-feature / uses-library     

- uses-permission

  gp 会根据权限假定一些功能需求，以过滤设备，所以最好是声明一下app 的功能需求  



关于[task](./task.md)  ,关于 [graphics](), 关于[storage]()    

