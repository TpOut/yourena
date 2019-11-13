

View一般会绘制一些东西，让用户可见并与之交互，其对象通常叫做widgets  
ViewGroup则一般是个布局容器，其对象通常叫做layouts  

布局属性说明
```xml
<!-- @符号用来告诉解析器,将后面的字符串解析成id资源,存在R.java文件; +表示新建id -->
<!-- 编译之后,id会被编译成整数,对应xml中的字符串 -->
android:id="@+id/my_button"
<!-- android自带资源,使用包形式;引用自android.R -->
android:id="@android:id/empty"
```

view的几何区域是矩形。
padding的实现由view提供支持，margin的实现由viewGroup提供支持  

layout层级应当越少越好。即相同实现下，广度优先结构比深度优先结构要好。  

AdapterView =》 ListView, GridView  
常见Adapter => ArrayAdapter, SimpleCursorAdapter
