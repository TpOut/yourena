1、友盟上会出现一个提示：  
```  
java.lang.IllegalStateException: ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)
```  
而检查代码，怎么看都没有传递true。后来在一次快速的连续下拉刷新时复现了。。
