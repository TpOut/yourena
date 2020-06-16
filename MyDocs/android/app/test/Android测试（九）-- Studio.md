
##studio - 测试

###[使用命令行](https://developer.android.google.cn/studio/test/command-line.html)
####gradlew
```
- Local unit test|'   ./gradlew test"|  HTML test result files: path_to_your_project/module_name/build/reports/tests/ directory.
XML test result files: path_to_your_project/module_name/build/test-results/ directory.
- Instrumented unit test:   ./gradlew connectedAndroidTest|   HTML test result files: path_to_your_project/module_name/build/outputs/reports/androidTests/connected/ directory.
XML test result files: path_to_your_project/module_name/build/outputs/androidTest-results/connected/ directory.

//默认对所有对象执行测试
./gradlew cAT	//支持缩写
./gradlew mylibrary:connectedAndroidTest	//支持module选择
./gradlew testVariantName
./gradlew connectedVariantNameAndroidTest	//支持变体选择
./gradlew testVariantName --tests *.sampleTestMethod	//支持筛选测试方法
```
[test的使用](https://docs.gradle.org/current/userguide/java_plugin.html#test_filtering)

[ADB](https://developer.android.google.cn/studio/command-line/adb.html)

日志路径如上所述，但是测试如果对多个module进行，肯定希望合并报告一并查看

在project中:apply plugin: 'android-reporting'

	./gradlew connectedAndroidTest mergeAndroidReports
	./gradlew connectedAndroidTest mergeAndroidReports --continue  //--continue忽略错误，执行完
结果在PATH_TO_YOUR_PROJECT/build/ directory.
### adb
...
https://developer.android.google.cn/studio/test/command-line.html

## [AS运行espresso指南](https://developer.android.google.cn/studio/test/espresso-test-recorder.html)
## [monkeyrunner](https://developer.android.google.cn/studio/test/monkeyrunner/index.html)