https://developer.android.google.cn/guide/topics/resources/layout-resource

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- root can be merge -->
<ViewGroup
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@[+][package:]id/resource_name"
    [ViewGroup-specific attributes] >

    <ViewGroup >
        <View >
           <!-- 会让父布局获取最初的焦点，一个文件只能有一个 -->
           <requestFocus/>
        </View>
    </ViewGroup>
    <!-- 外层声明属性，想要覆盖layout_resource自身的属性时，长宽属性需要同时存在 -->
    <include layout="@layout/layout_resource"/>
```

ViewGroup根据是否直接嵌套view分为  
- LinearLayout, RelativeLayout, FrameLayout  等   
- AdapterView  等


对于不同ViewGroup的xml属性请到类的索引页面查看“XML attributes”  
