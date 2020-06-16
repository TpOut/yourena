这里算是抽取了一下其他章节的说明，但多了下面的：

测试相关：https://developer.android.google.cn/studio/build/gradle-tips#test-your-app

文件签名：

```
def keystorePropertiesFile = rootProject.file("keystore.properties")
def keystoreProperties = new Properties()
keystoreProperties.load(new FileInputStream(keystorePropertiesFile))

android {
  signingConfigs {
      config {
        keyAlias keystoreProperties['keyAlias']
        keyPassword keystoreProperties['keyPassword']
        storeFile file(keystoreProperties['storeFile'])
        storePassword keystoreProperties['storePassword']
      }
    }
}
```

自定义BuildConfig属性或者string.xml里的值：

```
android {
  ...
  buildTypes {
    release {
      // These values are defined only for the release build, which
      // is typically used for full builds and continuous builds.
      buildConfigField("String", "BUILD_TIME", "\"${minutesSinceEpoch}\"")
      resValue("string", "build_time", "${minutesSinceEpoch}")
      ...
    }
    debug {
      // Use static values for incremental builds to ensure that
      // resource files and BuildConfig aren't rebuilt with each run.
      // If they were dynamic, they would prevent certain benefits of
      // Instant Run as well as Gradle UP-TO-DATE checks.
      buildConfigField("String", "BUILD_TIME", "\"0\"")
      resValue("string", "build_time", "0")
    }
  }
}
//读取
Log.i(TAG, BuildConfig.BUILD_TIME);
Log.i(TAG, getString(R.string.build_time));
```





## 工具栏Build

各个选项对应的作用：

|            选项            |                             描述                             |      |
| :------------------------: | :----------------------------------------------------------: | ---- |
|        Make Module         | 编译所选module的所有源文件和关联的构建任务,包括依赖的module.不产生apk |      |
|        Make project        |                     对所有Module执行make                     |      |
|       Clean project        |                 删除所有 媒介/缓存 构建文件                  |      |
|         Build apk          | 构建一个apk,包含所选变体的所有module. apk路径在 project-name/module-name/build/outputs/apk/ |      |
|        Build bundle        | 构建一个Android app bundle,包含所选变体的所有module. 路径在 project-name/module-name/build/outputs/bundle/ |      |
|      Rebuild project       |         对所选构建变体执行Clean project,再执行build          |      |
| Generate Signed Bundle/apk |                      可视化签名操作窗口                      |      |

默认情况下,debug的apk会被一个系统的debug key签名,可以直接安装.release的需要配置签名. debug,bundle也具有签名,可以使用bundle tool 来部署.release的前面需要使用jarsigner.

现在的as run 按钮构建apk的时候,会添加命令 testOnly = "true",只能通过adb安装. 如果要直接构建可直接安装的apk,使用Build apk选项.或者执行`./gradlew assemble`

在工具窗口 > 构建中,build tab页显示 构建和编译时错误,sync tab页显示同步错误. restart按钮等统于Make project

--stacktrace和 --debug配置在as设置中的 Build, Execution, Deployment > Compiler.

## other

项目module和app的结构相同，只是打包方式不同。即所使用的gradle插件不一样：

```
apply plugin: 'com.android.application'  
# or  
apply plugin: 'com.android.library'
```

## note

资源合并优先级：build variant > build type > product flavor > main source set > library dependencies

资源文件目录配置：

```
android {
    sourceSets {
        main {
            res.srcDirs = ['resources/main']
        }
        debug {
            //可以使用两个文件夹，但是注意命名冲突，不然合并的时候会报错（根据资源合并优先级，两者同级）　　
            res.srcDirs = ['resources/debug', 'res2']
        }
    }
}
```

Resource shrinking 功能可以自动移除无用的资源和库依赖

## 修改manifest的占位符

```
//build.gradle 中
android {
    defaultConfig {
        manifestPlaceholders = [hostName:"www.example.com"]
    }
    ...
}

<!--manifest中-->
<intent-filter ... >
    <data android:scheme="http" android:host="${hostName}" ... />
    ...
</intent-filter>
```

默认情况下，还有`${applicationId}`占位符也会被applicationId替换。