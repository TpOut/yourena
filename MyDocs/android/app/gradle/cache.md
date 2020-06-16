构建缓存（build cache）保存gradle插件生成的输出，包括未打包的aar和pre-dexed的远程依赖

但是如果以下的配置，有一项变化，那么pre-dexed的缓存就不被保存。

```
android {
  defaultConfig {
    // If you do enable multidex, you must also set
    // minSdkVersion to 21 or higher.
    multiDexEnabled false
  }
  buildTypes {
    <build-type> {
      minifyEnabled false
    }
  }
  dexOptions {
    preDexLibraries true
  }
  ...
}
```

> 这个库的minSdkVersion的说明有些奇怪

## 修改路径

默认路径的缓存数据可以被所有项目共享

```
//默认路基
<user-home>/.android/build-cache/

//如果配置了 ANDROID_SDK_HOME 或 user.home 或 HOME 环境变量（优先级 左到右 降低）
<path-variable>/.android/build-cache/
```

如果想单独项目使用，可以在gradle.properties中配置：

```
//支持绝对、相对路径
android.buildCacheDir=<path-to-directory>
```

不用使用`<project-root>/build/ or <project-root>/<module-root>/build/`作为缓存路径,会被clean掉

## 清除缓存

执行cleanBuildCache任务
如果自定义了单独项目的路径，只会删除项目的缓存

## 禁止

感觉没啥必要，要看翻原文