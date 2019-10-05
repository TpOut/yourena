曾以为，gradle基于groovy，要学习Android Gradle需要学习Android，groovy, gradle  
以至于卡了很久 -- 一副不想学又不得不学 和Android没有大关系的语言 的感觉

然而实际上，只需要了解gradle结构，照抄gradle中用到的groovy语法即可。分而治之。
等以后需要自定义groovy或需从语法学起。  

## 概览
在Android的gradle结构中，app打包时，会根据项目setting.gradle中配置的名称，依次调用整个项目的build.gradle和对应module的build.gradle  
而module的build.gradle，会根据第一行```apply plugin: '<pluginName>'```，指定打包模式  
其中pluginName对应gradle插件，包括：
|android pluginName|pluginExtensionName|use in module|
|---|---|---|
|com.android.application|AppExtension|app module|
|com.android.library   | LibraryExtension  | library module  |
|   | TestExtension | test module  |
|   | FeatureExtension  |  instant app的feature module |


### BaseExtension
https://google.github.io/android-gradle-dsl/current/com.android.build.gradle.BaseExtension.html#com.android.build.gradle.BaseExtension:defaultConfig　　

属性很多，页面顶层是一个总览图表，下面是详情，介绍的更确切。   
一个一个来，没什么必要的就不特别说明了。  

|属性/方法/块|内嵌属性/方法|描述|
|---|---|
|AaptOptions| cruncherProcesses | 设置压缩进程数 |
|| noCompress | 设置不压缩的文件 |
| packagingOptions| | 设置打包选项，first-picks,merges,excludes |
| resourcePrefix | | 资源前缀限制 |
| defaultConfig/ProductFlavor | manifestPlaceholders | 可以从这里动态填充manifest |
|  | testApplicationId ||
|  | applicationIdSuffix | 后缀 |
|  | buildConfigField | 在BuildConfig中添加变量 |
|   | consumerProguardFile | 设置lib库打包进aar的proguard |
|   | missingDimensionStrategy | 如果设置了flavor，而且依赖库的flavor没有在app中，可以设置这个来忽略依赖库的flavor类别 |
|   | proguardFile | proguard-android-optimize.txt会有个optimize，具体是什么？ |
|   | resConfig | 可以明确表示app的资源，防止多余资源被打包 |
| AndroidSourceSet |  | 包括aidl、RenderScript(了解下)等<br>可以使用`./gradlew sourceSets`查看具体路径 |
| split |  | 热更新app(instant app) |
| useLibrary |  | 可以添加AndroidSDK中包含(ship with)的可选库 |
| defaultConfig |  |   |
| DexOptions | keepRuntimeAnnotatedClasses | 可以在6.0以上且不使用反射的getDeclaredAnnotations时去除 |

在声明SourceSet路径的时候，尽量明确，不然会增加构建时间。  

### AppExtension  
比较于Base，多了unitTestVariants，testVariants,testBuildType 和 applicationVariants  

测试的几个暂且略过，  

|属性/方法/块|内嵌属性/方法|描述|
|---|---|
| ApplicationVariant |  | 获取所有变体，但是没有说明变体都有哪些属性，也没链接 |

### LibraryExtension / Feature　
和上述就没啥区别了　　
