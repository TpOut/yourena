不要使用动态版本，会产生很多未知版本更新问题,如
`com.android.tools.build:gradle:3.+`

```
dependencies {
    //本地module，需要在setting.gradle中include
    implementation project(":mylibrary")

    //本地jar，路径相对于此build.gradle文件
    //或：implementation files('libs/foo.jar', 'libs/bar.jar')
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    //远端库
    //是这种缩写：implementation group: 'com.example.android', name: 'app-magic', version: '12.3'
    implementation 'com.example.android:app-magic:12.3'
}
```

## 依赖配置

|      新的语法       | 废弃语法 |                             对比                             |
| :-----------------: | :------: | :----------------------------------------------------------: |
|   implementation    | compile  | gradle把依赖加入编译类路径，并加到构建输出中。 implementation能让gradle控制module的依赖不被其他module获取。 （但是运行时还是可以获取的，很好奇这个实现方式） 因为这样子，某个底层依赖变化时，关联这个依赖的module数量会减少，从而能够显著的减少构建时间 推荐大部分的应用和测试模块都是使用implementation |
|         api         | compile  | 与implementation相比，api则告知gradle不控制依赖 -- 和compile效果一样。 即编译的时期和runtime都可以 |
|     compileOnly     | provided | gradle只把依赖加入编译路径，而不加入构建输出--和provided效果一样。 他只需要在运行时获取成功即可，比如依赖一个implementation的库 |
|     runtimeOnly     |   apk    |         gradle只把依赖加入构建输出--和apk效果一样。          |
| annotationProcessor | compile  | 用于注解解析库，会被添加到annotationProcessor类路径。 从常规的编译类路径中分离出来,是因为如果放到一起，会影响常规编译的优势--[compile avoidance](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_compile_avoidance) |

### annotationProcessor

Android Gradle 会认为带有 META-INF/services/javax.annotation.processing.Processor的jar文件是一个注解解析库

这些名词可以和变体一样进行组合，如：

```
#如果只是一个flavor，添加前缀即可
dependencies {
    freeImplementation 'com.google.firebase:firebase-ads:9.8.0'
}

#如果是整个变体（即flavor和buildtype）,需要先定义在configurations中   
configurations {
    freeDebugRuntimeOnly {}
}
dependencies {
    freeDebugRuntimeOnly fileTree(dir: 'libs', include: ['*.jar'])
}
```

#### 传递参数

都是在module里的build.gradle文件

```
android {
    ...
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                argument "key1", "value1"
                argument "key2", "value2"
            }
        }
    }
}
```

在Android gradle 插件 >= 3.2.0
如果参数是文件或者文件夹，需要使用CommandLineArgumentProvider接口--[更多](https://guides.gradle.org/using-build-cache/#caching_java_projects)，
因为这样可以使用一些[注解](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks)来优化检查和性能。
示例如下：

```
class MyArgsProvider implements CommandLineArgumentProvider {

    @InputFiles
    // Using this annotation helps Gradle determine which part of the file path
    // should be considered during up-to-date checks.
    @PathSensitive(PathSensitivity.RELATIVE)
    FileCollection inputDir

    @OutputDirectory
    File outputDir

    MyArgsProvider(FileCollection input, File output) {
        inputDir = input
        outputDir = output
    }

    // Specifies each directory as a command line argument for the processor.
    // The Android plugin uses this method to pass the arguments to the
    // annotation processor.
    @Override
    Iterable<String> asArguments() {
        // Use the form '-Akey[=value]' to pass your options to the Java compiler.
        ["-AinputDir=${inputDir.singleFile.absolutePath}",
         "-AoutputDir=${outputDir.absolutePath}"]
    }
}

android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                // Creates a new MyArgsProvider object, specifies the input and
                // output paths for the constructor, and passes the object
                // to the Android plugin.
                compilerArgumentProvider new MyArgsProvider(files("input/path"),
                                         new File("output/path"))
            }
        }
    }
}
```

如果代码太老，annotation processor被compile了，而且你确定不需要这些processor.可以设置不报错：

```
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath false
            }
        }
    }
```

或者把值设成true，即和以前一样，把processor加入常规的编译类路径。但是这个属性的支持在未来会被移除

### Exclude

```
//如果是非混合的语法
implementation('some-library') {
        exclude group: 'com.example.imgtools', module: 'native'
    }

//如果是混合的语法，如androidTestImplementation
//那么gradle处理的时候，一定会包含Implementation的依赖  
//导致androidTestImplementation同时使用时无效，那么可以：
android.testVariants.all { variant ->
    variant.getCompileConfiguration().exclude group: 'com.jakewharton.threetenabp', module: 'threetenabp'
    variant.getRuntimeConfiguration().exclude group: 'com.jakewharton.threetenabp', module: 'threetenabp'
}
```

## 使用 感知变体(variant-aware)的 依赖管理

Android插件>=3.0, app的变体修改时，其依赖的模块也会改成对应的变体
而如果app的变体，不存在于其依赖的模块，可能会出现错误。需要你对变体里的配置声明备选项：

- buildType 没有

    ```
    // In the app's build.gradle file.
    android {
      buildTypes {
          debug {}
          release {}
          staging {
              matchingFallbacks = ['debug', 'qa', 'release']
          }
      }
    }
    ```

- flavorDimension有，但是flavor数目不足

    ```
    // In the app's build.gradle file.
    flavorDimensions 'tier'
      productFlavors {
          paid {
              dimension 'tier'
          }
          free {
              dimension 'tier'
              matchingFallbacks = ['demo', 'trial']
          }
      }
    ```

- flavorDimensions没有

    ```
    // In the app's build.gradle file.
    android {
      defaultConfig{
          missingDimensionStrategy 'minApi', 'minApi18', 'minApi23'
          //可以多次定义？
          missingDimensionStrategy 'abi', 'x86', 'arm64'
      }
      flavorDimensions 'tier'
      productFlavors {
          free {
              dimension 'tier'
              //可以覆盖default中的配置
              missingDimensionStrategy 'minApi', 'minApi23', 'minApi18'
          }
          paid {}
      }
    }
    ```

## 穿戴设备

略过

## 远程仓库

更多查看
https://docs.gradle.org/current/userguide/introduction_dependency_management.html#sec:repositories

```
//如果库在两个仓库中都有，按列表顺序优先使用  
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        mavenLocal()
    }
    //或者
    repositories {
       maven {
           url "https://repo.example.com/maven2"
       }
       maven {
           url "file://local/repo/"
       }
       ivy {
           url "https://repo.example.com/ivy"
       }
   }
}
```

gradle 4.1以下, google仓库使用：

```
maven {
    url 'https://maven.google.com'
}
// An alternative URL is
'https://dl.google.com/dl/android/maven2/'
```

### google仓库中的依赖查询

google maven 仓库中

依赖库列表xml格式
https://dl.google.com/dl/android/maven2/master-index.xml
依赖库版本可以在这里查：
`maven.google.com/<group_path>/group-index.xml`
示例：maven.google.com/android/arch/lifecycle/group-index.xml

依赖库含版本号列表：
https://dl.google.com/dl/android/maven2/index.html

下载依赖库：
`maven.google.com/<group_path>/<library>/<version>/<library-version.ext>`
示例：maven.google.com/android/arch/lifecycle/compiler/1.0.0/compiler-1. 0.0.pom

下载goolge maven不支持的库（一般都是老版本库），可以在SDK Manger中下载离线的 Google Repository包
离线的依赖都在：android_sdk_path/extras/

### 依赖优先顺序

简单理解是深度优先记录，如果后面遇到相同的依赖，那么移除之前的依赖记录，保留当前的记录
最后按顺序编译。。

### 依赖树

在工具窗口，gralde中，选择appName > android > android dependencies，可以输出项目的依赖结构

### 解决依赖方案错误

可以通过依赖树查看；或者 Navigate > Class 并勾选 Include non-project items 进行手动查找

#### class路径生成顺序

gradle处理编译类路径的时候，首先处理runtime的部分，然后使用这部分结果来确定哪些依赖需要被加入编译类路径，这里包括依赖的版本。

![img](https://developer.android.google.cn/studio/images/build/classpath_sync-2x.png)
然后是编译时、测试runtime 添加类路径。这时如果有重复或版本不同，就会报冲突

### 自定义构建逻辑

你也可以不适用默认的方案，而自定义：
https://developer.android.google.cn/studio/images/build/classpath_sync-2x.png