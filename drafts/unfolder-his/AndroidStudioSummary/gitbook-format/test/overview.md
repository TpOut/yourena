主要有Mockito 做Android单元测试，espresso和uiAutomator来集成（instrumented）测试用户交互   

单元测试目录为：module-name/src/test/java/  ，适用环境为 无framework依赖或者可以模拟framework依赖  
>这些测试在一个修改的android.jar中执行，它会移除final关键字  

集成测试目录为：module-name/src/androidTest[VariantName]/java/ ，会产生一个apk,manifest是gradle自动生成  
```
android {
  ...
  defaultConfig {
    testApplicationId "com.test.foo"
    testInstrumentationRunner "android.test.InstrumentationTestRunner"
    // If set to 'true', enables the instrumentation class to start and stop profiling.
    // If set to false (default), profiling occurs the entire time the instrumentation
    // class is running.
    testHandleProfiling true
    // If set to 'true', indicates that the Android system should run the instrumentation
    // class as a functional test. The default value is 'false'
    testFunctionalTest true
  }
  testOptions {
    unitTests {
      // By default, local unit tests throw an exception any time the code you are testing tries to access
      // Android platform APIs (unless you mock Android dependencies yourself or with a testing
      // framework like Mockito). However, you can enable the following property so that the test
      // returns either null or zero when accessing platform APIs, rather than throwing an exception.
      returnDefaultValues true

      // Encapsulates options for controlling how Gradle executes local unit tests. For a list
      // of all the options you can specify, read Gradle's reference documentation.
      all {
        // Sets JVM argument(s) for the test JVM(s).
        jvmArgs '-XX:MaxPermSize=256m'

        // You can also check the task name to apply options to only the tests you specify.
        if (it.name == 'testDebugUnitTest') {
          systemProperty 'debug', 'true'
        }
        ...
      }
    }
    // Changes the directory where Gradle saves test reports. By default, Gradle saves test reports
    // in the path_to_your_project/module_name/build/outputs/reports/ directory.
    // '$rootDir' sets the path relative to the root directory of the current project.
    reportDir "$rootDir/test-reports"
    // Changes the directory where Gradle saves test results. By default, Gradle saves test results
    // in the path_to_your_project/module_name/build/outputs/test-results/ directory.
    // '$rootDir' sets the path relative to the root directory of the current project.
    resultsDir "$rootDir/test-results"
  }
```  

创建testmodule -- 先创建普通libray module,然后修改插件为 com.android.test  
test模块是一个debug变体  

## index  
official home : https://developer.android.google.cn/studio/test/index.html  
test coverage: https://www.jetbrains.com/help/idea/2018.1/running-test-with-coverage.html  
两个字符串之间assertEquals()失败的原因查询：https://www.jetbrains.com/help/idea/2018.1/differences-viewer.html

命令行测试: https://developer.android.google.cn/studio/test/command-line  
expresso测试: https://developer.android.google.cn/studio/test/espresso-test-recorder  
monkey测试： https://developer.android.google.cn/studio/test/monkey
monkeyrunner测试： https://developer.android.google.cn/studio/test/monkeyrunner/    

## note  

Espresso Test Recorder的自动化基于[Espresso Testing framework](https://google.github.io/android-testing-support-library/docs/espresso/)，所以测试前需要在开发者选项中关闭动画相关选项。具体参看：https://developer.android.google.cn/training/testing/ui-testing/espresso-testing.html#setup  
但是不需要在项目中另行引入依赖。  

### 定义类或方法的测试配置
>右键代码方法可以在go to选项中找到快速创建测试类的功能项，快捷键Ctrl + shift + t  

Android测试包括 instrumented test 或者 junit  
本地的单元测试，你还可以选择代码覆盖率(code coverage)  

在运行对应的单元测试的时候，也会有对应的临时配置产生。
