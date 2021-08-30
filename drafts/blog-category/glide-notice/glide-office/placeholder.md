包括三种：

- placeholder

  主线程加载的哦，不会被“转化”效果应用

  加载资源的过程中使用，成功之后被替换。如果从内存中加载，那就很可能不显示了

  加载失败，则优先展示error；加载内容为空，则优先fallback 或error

- error

  请求确定失败的情况展示；或者加载内容为空，优先fallback

- falback

  对于加载内容为空的定义包括，地址为空，图像的meta-data 不合法  

  



```java
Glide.with(context)
    .load(url)
    .placeholder(R.drawable.placeholder) //.error() ; new ColorDrawable(Color.BLACK)  
```





