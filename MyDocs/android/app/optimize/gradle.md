构建速度的优化主要有两步，一个是减少步骤；一个是优化每一步的瓶颈。

开发app的时候，最好是部署在Android >= 7.0，有更优化的ART和mutiple dex支持

## 优化配置

as和sdk tool；gradle插件，全部升级到最新

### 开发专用

```
productFlavors {
dev {
  // 可以禁用兼容到2.3的multidex方式，加快编译
  minSdkVersion 21
}
```

### 资源限定

```
  // 只编译限定的资源
  resConfigs "en", "xxhdpi"
```

### fabric

fabric ,crashlytics : https://docs.fabric.io/android/crashlytics/build-tools.html

```
buildTypes {
    debug {
      // 关闭插件
      ext.enableCrashlytics = false
      // 关闭id自增
      ext.alwaysUpdateBuildId = false
    }

//代码中
Crashlytics crashlyticsKit = new Crashlytics.Builder()
    .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
    .build();

Fabric.with(this, crashlyticsKit);
```

### debug构建 配置使用静态值

如果每次构建都会引起manifest或者资源更新，那么instant run的code/hot swap就无法生效
比如 versionCode 和 versionName,但是release版无需限制：

```
int MILLIS_IN_MINUTE = 1000 * 60
int minutesSinceEpoch = System.currentTimeMillis() / MILLIS_IN_MINUTE

android {
    ...
    defaultConfig {
        versionCode 1
        versionName "1.0"
        ...
    }
    applicationVariants.all { variant ->
        if (variant.buildType.name == "release") {
            variant.mergedFlavor.versionCode = minutesSinceEpoch;
            variant.mergedFlavor.versionName = minutesSinceEpoch + "-" + variant.flavorName;
        }
    }
}
```

### 抽取单独逻辑为task

task的结果会被缓存，且能够更好的支持并行执行
如果task数量过多，你可以创建task classes，然后放在`project-root/buildSrc/src/main/groovy/`目录下, gradle会自动把这些文件包含到用于build.gradle的类路径中

### 关闭png crunching

gradle插件3.0.0以上debug默认关闭，也可以设置到其他变体

```
crunchPngs false
```

### 其他

- 依赖不要使用动态版本，如 ：2.+

- 开启离线模式

- 开启configuration on demand模式,会只调试当前module（如果是3.0.1或3.1.0的gradle插件和4.6的gradle，开启会有问题）

    > 开关项在 File > Settings > Build, Execution, Deployment > Compiler

- 把图片转化成webP

- 开启instant run

- 开启build cache，默认开启

- 不使用annotation processor。不然会禁用java增量编译功能

## 分析构建

通过分析构建的情况来做出相应处理。具体的处理方式因项目而异
常规的：如果配置时间过长，可以“抽取单独逻辑为task”；如果资源合并任务(mergeDevDebugResources)时间过长，可以进行图片相关的优化

分析步骤如下：

```
//先清楚缓存
gradlew clean
//输出报告路径，可以在AS中右键通过浏览器打开
gradlew --profile --recompile-scripts --offline --rerun-tasks assembleFlavorDebug
//再执行一次，去除rerun-tasks(gradle会对task任务优化)
gradlew --profile --recompile-scripts --offline assembleFlavorDebug
```

然后可以从第一次的文件中查看哪些构建时间过长
从第二次文件中，查看哪些任务可以不重复执行

比如第二次文件如果:app:processDevUniversalDebugManifest 没有UP-TO-DATE，表示manifest每次构建都有更新

更健壮的分析器：https://github.com/gradle/gradle-profiler