
## Gradle
## applicationId
application id规则：  
起码有两段，每段以字母开头，且所有字符在[a-zA-Z0-9_]内。  

Context.getPackageName()返回的是applicationId,而不是包名，只是两者经常一样。  

如果使用了WebView，在修改applicationId的时候，还是把包名作为前缀的好，不然可能有问题：  
https://issuetracker.google.com/issues/37102241  

Manifest中使用 ${applicationId}。可以在构建时，gradle会将其替换成app id  

app的R.java类根据package生成，manifest中声明activity时的相对路径也是相对于此。

为了避免测试app的冲突，构建工具在对测试apk生成R类的时候，会使用test application id  

## 构建进程
![](https://developer.android.google.cn/images/tools/studio/build-process_2x.png)    

apk packager 到最后的apk，需要zipalign进行优化，可以让运行时使用更少内存  

## 自定义构建配置

- Build types: 默认debug, release
- Product flavor: 需手动配置，比如免费和付费版
- Build Variant: 是type和flavor的交叉混合体    
- Manifest Entries: build可以覆盖配置manifest中的一些属性。

## 构建配置文件

 ![结构图](https://developer.android.google.cn/images/tools/studio/project-structure_2x.png)  

### setting.gradle
比较直白。注意没有 块(block)  
### top-level build.gradle:  
```
//不应该在这里定义module的依赖
buildscript{
    //gradle搜索依赖的仓库，预支持 jcenter,maven central,ivy
    repositories{

    }
    //gradle依赖配置
    dependencied{

    }
}
//配置全局的仓库和依赖(是不是类似组件化里的base？)
allprojects{
    repositories{

    }
    depen...
}
//任何enhanced objects都可以处理，但是不要放到module中，耦合且解析慢
//任何模块都可以使用：rootProject.ext.compileSdkVersion
ext {

}
```
### model-level build file
```
//声明插件，使android{}生效
apply plugin: 'com.android.application'
//插件支持语法
android{
    //可选
    buildToolsVersion “28.0.3”
    //配置到所有变体
    defaultConfig{
        applicationId "com.example.myapp"
    }
    //配置build types
    buildTypes {
         release {
             minifyEnabled true
             proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    //配置product flavors
    flavorDimensions "tier"
    productFlavors {
        free {
            dimension "tier"
            applicationIdSuffix ".free"
        }
        paid {
            dimension "tier"
            applicationId 'com.example.myapp.paid'
        }
    }
    //分包，目前支持>=21，仅instant App
    splits {
        density {
            // Enable or disable building multiple APKs.
            enable false
            exclude "ldpi", "tvdpi", "xxxhdpi", "400dpi", "560dpi"
        }   
    }
}
//依赖
dependencies{}
```
### gradle properties
```
gradle.properties: 项目范围的设置
local.properties: 自动生成。加入忽略
```  
### source sets
对应变体
```
src/main/   
src/buildType/   
src/productFlavor/   
src/productFlavor1ProductFlavor2/  
src/productFlavorBuildType/   
```  

### 其他  
没找出来在哪里  
Note: When you create a new file or directory in Android Studio, using the File > New menu options, you can create it for a specific source set. The source sets you can choose from are based on your build configurations, and Android Studio automatically creates the required directories if they don't already exist.

## index
gradle插件更新：https://developer.android.google.cn/studio/releases/gradle-plugin    
as更新：https://developer.android.google.cn/studio/intro/update   

gradle并行执行：https://docs.gradle.org/current/userguide/multi_project_builds.html#sec:parallel_execution  
configure on demond:https://docs.gradle.org/current/userguide/multi_project_builds.html#sec:configuration_on_demand  
自定义task classes:https://docs.gradle.org/current/userguide/custom_tasks.html

java增量编译：https://docs.gradle.org/current/userguide/java_plugin.html#sec:incremental_compile

支持的ABI:https://developer.android.google.cn/ndk/guides/abis
兼容屏幕：https://developer.android.google.cn/guide/topics/manifest/compatible-screens-element  

proguard--keep 选项：http://proguard.sourceforge.net/manual/usage.html#keepoptions  
