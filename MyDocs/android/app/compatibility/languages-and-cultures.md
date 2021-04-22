感觉这就是locale   ，主要有一个RTL 的处理   

包括 (官网还提供了一些checklist )

- 系统支持的，要注意的

在很多情况并不会如预期自动转换方向    

有相关的解决方案：`BidiFormatter.unicodeWrap`  

数字的格式化，要用`Locale.US`  



```kotlin
// view, drawable 等
getLayoutDirection() == LAYOUT_DIRECTION_RTL   
// 配置  
val config: Configuration = context.resources.configuration
view.layoutDirection = config.layoutDirection
	
onRtlPropertiesChanged()  
```

  

`android:autoMirrored`  这个好像vector 才有？  

- 自定义view， 要注意的

```kotlin
Gravity.getAbsoluteGravity(gravity, layoutDirection)

```

