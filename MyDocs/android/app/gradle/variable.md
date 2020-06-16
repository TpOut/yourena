```
android{
  buildTypes {
         release {
         }

         debug {
         }
  }
  //api的配置比mode有更高的优先级
  flavorDimensions "api", "mode"
  productFlavors {
    demo {
      dimension "mode"
    }
    full {
      dimension "mode"
    }

    minApi24 {
      dimension "api"
    }
    minApi23 {
      dimension "api"
    }
  }

  //过滤器
  variantFilter { variant ->
    def names = variant.flavors*.name
    // To check for a certain build type, use variant.buildType.name == "<buildType>"
    if (names.contains("minApi21") && names.contains("demo")) {
        // Gradle ignores any variants that satisfy the conditions above.
        setIgnore(true)
    }
  }

}
```

对应变体可能为：[minApi24 | minApi23 | minApi21][Demo | Full][Debug | Release]

## 资源集

可以在项目中添加对应的变体资源目录：

```
app/src/demoDebug/
```

gradle对目录的合并可以在 工具窗口gradle > MyApplication > Tasks > android > sourceSets 中找到

### 配置目录

sourceSets的目录当然也是可以配置的

```
android {
  sourceSets {
    main {
      java.srcDirs = ['other/java']

      // res1和res2平级；res1和res2不能是父子集目录关系
      res.srcDirs = ['other/res1', 'other/res2']
      manifest.srcFile 'other/AndroidManifest.xml'
    }

    androidTest {
      setRoot 'src/tests'
    }
  }
}
```

### 目录合并优先级

1. src/demoDebug/ (build variant source set)
2. src/debug/ (build type source set)
3. src/demo/ (product flavor source set)
4. src/main/ (main source set)

对于多个变体维度的情况，如demo,api
则 demoApi >> demo >> api

java文件不能同名，其他都可以相同。manifest和values按优先级覆盖, res/和asset/按优先级合并

### 依赖声明

可以把变体名字作为前缀+关键字

### 配置签名

```
android {
    ...
    defaultConfig {...}
    signingConfigs {
        release {
            storeFile file("myreleasekey.keystore")
            storePassword "password"
            keyAlias "MyReleaseKey"
            keyPassword "password"
        }
    }
    buildTypes {
        release {
            ...
            signingConfig signingConfigs.release
        }
    }
}
```

这种方式明显太不安全，可以配置环境变量让脚本读取:

```
storePassword System.getenv("KSTOREPWD")
keyPassword System.getenv("KEYPWD")
```

或者用脚本打包

```
storePassword System.console().readLine("\nKeystore password: ")
keyPassword System.console().readLine("\nKey passwo
```