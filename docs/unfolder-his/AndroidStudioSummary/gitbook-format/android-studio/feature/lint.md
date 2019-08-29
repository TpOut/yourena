## lint    
https://developer.android.google.cn/studio/write/annotations  
![检查项：](https://developer.android.google.cn/studio/images/write/lint.png)  

AS可视化操作：https://developer.android.google.cn/studio/write/annotations#manuallyRunInspections
作用范围：  
- Globally (entire project)
- Project module
- Production module
- Test module
- Open files
- Class hierarchy
- Version Control System (VCS) scopes

比如：xml中如果包含无用的namespace，会占用空间并引发不必要的处理。  

### gradle link task
```
//结果输出/app/build/reports/lint-results.[html|xml]
gradlew lintDebug  
```  
### standalone lint  
在android_sdk/tools/bin/目录下  
```  
lint [flags] <project directory>
//查看支持的flag
lint --help
//查看xml支持的issue名称和id
lint --list
```
### 配置lint.xml  
```
<?xml version="1.0" encoding="UTF-8"?>
<lint>
    <!-- Disable the given check in this project -->
    <issue id="IconMissingDensityFolder" severity="ignore" />

    <!-- Ignore the ObsoleteLayoutParam issue in the specified files -->
    <issue id="ObsoleteLayoutParam">
        <ignore path="res/layout/activation.xml" />
        <ignore path="res/layout-xlarge/activation.xml" />
    </issue>
</lint>  
```
### 配置lint   
setting > Editor > Inspections.  

代码中忽略注解`@SuppressLint("NewApi")`：  
xml中忽略`tools:ignore="UnusedResources,StringFormatInvalid"`  
两者的忽略参数是 issue id  

Gradle的参看api文档  

### 基准快照（baseline snapshot）  
可以创建一个当前Lint提示的快照，让之后的检查都基于这个快照。这样可以不用重复查看 已经检查过的不准备处理的问题
```
android {
  lintOptions {
    # check 'NewApi', 'HandlerLeak' 如果写入，快照只会加入NewApi和HandlerLeak的问题
    baseline file("lint-baseline.xml")
  }
}
```  
此时，lint-baseline.xml即基准快照，会在不存在时被创建。要更新时，只需要删除文件即可。     
