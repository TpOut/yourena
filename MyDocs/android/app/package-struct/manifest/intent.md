结构包括：

- action

  ```java
  // Intent 类中，奇怪的ACTION 有：
  
  EXTRA_FROM_STORAGE   
  ACTION_CREATE_SHORTCUT  // 只发送的时候有app 可以接收，但是进入页面点击后，返回没有什么实质反应
  ACTION_CREATE_LIVE_FOLDER // launcher?
  ACTION_SYNC // 感觉有点设计过头？？ 这什么行为都没规定
  ACTION_ASSIST // 查了一下废弃了？
  ACTION_INSTALL_FAILURE // 类启动，但是没有application ？
  ACTION_DREAMING_STARTED
  ACTION_USER_PRESENT
  ACTION_LOCKED_BOOT_COMPLETED // 这个没用了吧？
  
  ACTION_OVERLAY_CHANGED
  ```

- data 

  主数据，一般期望是uri   

- category

  action 的分类（记录或查找

  多个category 时必须同时匹配，与  

- type

  大部分场景都是对Intent 中data 的MIME 类型进行记录  

- component

  组件的名字，计算优先级最高

- extras  

  携带的数据  



然后当使用component 时，显式   

其他（category / type / action）是隐式，从 *Intent resolution*  查找，过滤器有：`IntentFilter`  

> 隐式的时候，为了`startActivity` 可以被找到，要加`CATEGORY_DEFAULT`  



`PackageManager.queryIntentActivityOptions(ComponentName, Intent[\], Intent, int)`  







