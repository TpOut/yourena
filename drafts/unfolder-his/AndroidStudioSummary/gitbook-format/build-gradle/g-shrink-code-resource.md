ProGuard：  https://stuff.mit.edu/afs/sipb/project/android/sdk/android-sdk-linux/tools/proguard/docs/index.html#manual/introduction.html  

```
android {
    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'),
                    'proguard-rules.pro'
        }
    }
    flavorDimensions "version"
        productFlavors {
            flavor1 {
            }
            flavor2 {
                proguardFile 'flavor2-rules.pro'
            }
    }
    ...
}
```
proguard-android.txt 是Android插件(个人理解成Gradle插件)捆绑的，  
开始编译的时候，会拷贝一份到project-dir/build/intermediates/proguard-files/  
在相同目录下，你还可以找到proguard-android-optimize.txt，两者有相同的规则，但是后者是bytecode级别的  
(ps, 没找到这个目录)

而proguard-rules.pro是可以自定义的  
flavor2-rules.pro 是额外增加的，即flavor2有三条规则  

每次构建会在目录`<module-name>/build/outputs/mapping/release/`下产生三个文件：  
- mapping.txt : 源文件和混淆后文件的对应关系  
- seeds.txt : 未混淆的类和成员  
- usage.txt : 从APK中移除的代码  

分析apk的类文件结构：`-dump <module-name>/build/outputs/mapping/release/dump.txt`  
### 自定义keep  
```
@Keep  

-keep public class MyClass  
```
### 还原混淆后的栈追踪
需要前面说的mapping文件，还要`<sdk-root>/tools/proguard/`下的retrace脚本  
```
retrace.bat|retrace.sh [-verbose] mapping.txt [<stacktrace_file>]
```  
### 和Instant Run一起使用  
Proguard是不支持的，现在有个实现性的插件功能，code shrinker(cs)  
cs现在不混淆和优化代码，只是移除无用的代码，所以还只能在debug的时候使用  
```
    debug {
        minifyEnabled true
        useProguard false
    }
```  
### 资源缩减  
如果开启了资源缩减，会在<module-name>/build/outputs/mapping/release/目录下找到`resources.txt`  

资源缩减(resource shrinker)是基于代码缩减之后的，只有去除了无用代码，才真的知道哪些资源毫无用处  
```
release {
    shrinkResources true
}
```  
rs现在不移除values下面的资源，因为AAPT不允许插件预先定义资源版本---参看 [issue 70869](https://code.google.com/p/android/issues/detail?id=70869)  

资源的忽略，存到项目资源下，如res/raw/keep.xml：    
```
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools"
    tools:keep="@layout/l_used*_c,@layout/l_used_a,@layout/l_used_b*"
    tools:discard="@layout/unused2" />
```
discard在变体时会有用：比如某个变体使用同一个基础库，但是明确知道有些文件不用；甚至在构建工具识别资源错误时有用。。。  

如果因为某些原因（库依赖，比如AppCompat），需要动态获取资源，调用了Resources.getIdentifier()。  
那么策略上是保守的，只要有可能用到，就不会被添加到后面的移除队列中    
当然，这个保守策略可以使用`tools:shrinkMode="strict"`进行关闭，这样你需要自己处理需要用到或者移除的资源  

### 去除无用的备份资源  
在gradle中明确指定涉及到的资源，可以去除其他备份资源。比如AppCompat自带所有语种翻译，我们可以指定`resConfigs "zh"`   
