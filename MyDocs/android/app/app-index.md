按使用来讲，打开一个app 的方式无非一个地方触发，然后系统会启动app。  

按开发来讲，触发点为[四大组件]，启动app 展示的页面叫做入口界面。     

>这里涉及到[intent] 和[启动流程]  

入口界面是一个特殊的界面，界面实现包括[代码编写]、[资源编写] - [LayoutEditor] - [Window-ViewGroup/View]、其他如[manifest]、[Strings - TranslationsEditor] 编写   

这些编写的文件打包是通过[gradle] 实现，[Android studio] 帮我们集成了构建流程  

打包后的格式是apk

> [打包压缩流程]

当我们运行app 时可以选择设备，如[emulator]    

 而每个app 运行在自己的[sandbox] 中

运行之后，会调用每个activity，对应生命周期，解藕来说 

```kotlin
lifecycleObservable   
@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)  
```

而activity 过重，产生了更灵活的[fragment]()   



```
可以将apk 安装在外部存储：

​```xml
<manifest ...
  android:installLocation="preferExternal">
  ...
</manifest>
​```



Android 10之后，对于外部存储设置的存储域（scoped storage），只能访问app专属的外部存储目录和app自己创建的共享媒体文件

文件的打开和读取很耗性能  
```



https://developer.android.com/dev-summit/



```
intent.Extra.key 支持跨app 的时候，最好使用包名前缀：

`const val EXTRA_MESSAGE = BuildConfig.APPLICATION_ID + ".MESSAGE"` 
```



https://codelabs.developers.google.com/?cat=Android

https://developer.android.google.cn/samples?technology=  

https://github.com/android/architecture-samples

https://github.com/android/architecture-components-samples



兼容库：https://developer.android.google.cn/topic/libraries/support-library/features  



10 以上on-device trace 分析结果的格式是 https://ui.perfetto.dev/#!/  。可以转化为systrace 的格式   



免安装 应用： https://developer.android.google.cn/topic/google-play-instant/getting-started/feature-plugin  



应用模块化  

https://developer.android.google.cn/studio/projects/dynamic-delivery#customize_delivery  

https://developer.android.google.cn/guide/app-bundle/playcore  



###追踪类 android.os.Debug类 使用起来比较简单，我们先导入 import android.os.Debug这个包。 在需要开始跟踪的地方加入 Debug.startMethodTracing(“/sdcard/debug”); 最终在停止调试的地方加入 Debug.stopMethodTracing(); 最终在sdcard上生成的debug文件我们可以用sdk/tools中的traceview来查看运行的结果。



art  

 Android 10 adds Generational Garbage Collection to ART's Concurrent Copying (CC) Garbage Collector to make garbage collection more efficient



FEATURE_DEVICE_ADMIN  什么用处？



AutoFill 框架

 [`FillRequest.FLAG_COMPATIBILITY_MODE_REQUEST`](https://developer.android.google.cn/reference/android/service/autofill/FillRequest#FLAG_COMPATIBILITY_MODE_REQUEST) SaveInfo.FLAG_DELAY_SAVE

The `BaseAdapter` class now includes the `setAutofillOptions()` method, which allows you to provide string representations of the values in an adapter. This is useful for [spinner](https://developer.android.google.cn/guide/topics/ui/controls/spinner) controls that dynamically generate the values in their adapters. For example, you can use the `setAutofillOptions()` method to provide a string representation of the list of years that the users can choose as part of a credit card expiration date. Autofill services can use the string representation to appropriately fill out the views that require the data.

Additionally, the `AutofillManager` class includes the `notifyViewVisibilityChanged(View, int, boolean)` method that you can call to notify the framework about changes in the visibility of a view in a virtual structure. There's also an overload of the method for non virtual structures. However, non virtual structures usually don't require you to explicitly notify the framework because the method is already called by the `View` class.

Android 8.1 also gives Autofill Services more ability to customize the save UI affordance by adding support for `CustomDescription and` `Validator` within `SaveInfo`.

Custom descriptions are useful to help the autofill service clarify what is being saved; for example, when the screen contains a credit card, it could display a logo of the credit card bank, the last four digits of the credit card number, and its expiration number. To learn more, see the `CustomDescription` class.

`Validator` objects are used to avoid displaying the autofill save UI when the Validator condition isn't satisfied. To learn more, see the [Validator](https://developer.android.google.cn/reference/android/service/autofill/Validator.html) class along with its subclasses, [LuhnChecksumValidator](https://developer.android.google.cn/reference/android/service/autofill/LuhnChecksumValidator.html) and [RegexValidator](https://developer.android.google.cn/reference/android/service/autofill/RegexValidator.html).



刘海屏DisplayCutout  



Vss = virtual set size
Rss = resident set size
Pss = proportional set size
Uss = unique set size

CodeLab:
https://codelabs.developers.google.com/?cat=Android
Training Course:
https://developer.android.google.cn/courses
Kotlin:  
https://www.udacity.com/course/developing-android-apps-with-kotlin--ud9012  


//代码示例
https://developer.android.google.cn/samples/index.html

//tts介绍  
https://android-developers.googleblog.com/2009/09/introduction-to-text-to-speech-in.html  


//测试相关  
Android测试库 ：  
https://github.com/googlesamples/android-testing  
ViewActions:  
https://developer.android.google.cn/reference/android/support/test/espresso/action/ViewActions   
Robolectric:  
http://robolectric.org/getting-started/

---
https://developer.android.google.cn/



---

编辑器：https://androidpal.com/studio/about
