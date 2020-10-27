资源包主要是指res 目录下的文件。  

>特别的如`xml/`，使用`Resources.getXML()` 获取，存储搜索配置项之类的
>
>`raw/` 使用`Resources.openRawResource()` 加载`R.raw.filename`，主要是音视频流 
>
>`../assets` 使用`AssetManger` 加载`filename`，完全自己控制

资源可以有别名

> animation\menu\raw\xml 例外  
>
> 图像可以使用bitmap 在drawable 目录，也可以使用drawable 在values 目录



资源的索引方式：

```xml
<!--代码中-->
[<package_name>.]R.<resource_type>.<resource_name>
<!--xml 中-->
@[<package_name>:]<resource_type>/<resource_name>
<!--属性-->    
?[<package_name>:][<resource_type>/]<resource_name>
```



基于适配的原因，系统提供了一系列限定词，这些限定词对应设备的一些[配置](./configure.md)。用于较粗粒度的设备分类  

限定词需要严格按照优先级顺序写入到文件夹命名中，同一类型（这里的类型和原文不完全相同）的限定词不能重复



同时设备配置发生改变的时候，可能需要重新加载对应分类的资源。  

此时要进行状态保存，小数据可以用[saveInstance/restoreInstance] 实现，现在推荐使用[ViewModel]  



~~~java
不写dimen 单位  
```
<resources>
    <!--没有单位的dimens-->
    <!--代码中引用请查看getNoUnitDimens()方法-->
    <item name="text_line_spacing" format="float" type="dimen">1.2</item>
</resources>

    /**
     * 引用没有单位的dimens
     */
    private void getNoUnitDimens() {
        TypedValue outValue = new TypedValue();
        mAppContext.getResources().getValue(R.dimen.text_line_spacing, outValue, true);
        float value = outValue.getFloat();
    }
```
~~~

