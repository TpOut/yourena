official-home:https://developer.android.google.cn/studio/test/index.html

本地的jvm测试（Local unit tests）是通过临时创建jar包来运行的，所以你可以模拟一些数据  
集成测试则会把测试构建进apk，所以需要一个AndroidManifest.xml文件（gradle会默认构建，但是你可以自己替代他。包括里面有个`<instrumentation>`节点,可以在module的build.gradle中覆盖设置）。为什么要这个文件呢？可以了解一下build variants  
由于安装apk和测试apk共享一样的类路径，所以如果两者AndroidManifest中的相同库依赖的版本不同，会有冲突导致不可预料的错误或者崩溃（gradle处理不了）。此时需要使用一个项目级别的依赖，参见user-guide > configure-your-build > gradle tips and recipes # Configure project-wide properties  
如果建立了build variants,那么需要建立对应的目录路径，主文件夹规则为：androidTestVariantName。此时就支持快捷键（参看user-guide-meetAndroidStudio-index）创立测试文件了  
默认情况下，测试只在debug运行，可以修改testBuildType属性来运行，如testBuildType "staging"  
配置gradle测试选项： [testOptions{}](http://google.github.io/android-gradle-dsl/current/com.android.build.gradle.internal.dsl.TestOptions.html) 和 [unitTests {}](http://google.github.io/android-gradle-dsl/current/com.android.build.gradle.internal.dsl.TestOptions.UnitTestOptions.html) 即可  
配置测试运行器：Run > Edit Configurations  
测试覆盖率工具：选中测试类或方法，右键列表中Run “test-name” with coverage.

