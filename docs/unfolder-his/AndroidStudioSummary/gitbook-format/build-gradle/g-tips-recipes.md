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
