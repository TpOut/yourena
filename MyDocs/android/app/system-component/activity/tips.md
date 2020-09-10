**点击事件**

xml 中view 添加`android:onClick` 属性，假设其值为`publicSingleParamMethod` ,在activity 中定义一个同名方法

```kotlin
fun publicSingleParamMethod(v: View){
    
}
```

即可调用



**标题栏返回**

```xml
<!-- 如果使用AppBar，会添加返回按钮 -->   
<activity android:name=".DisplayMessageActivity"       android:parentActivityName=".MainActivity"/>
```



