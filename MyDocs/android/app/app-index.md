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





---

编辑器：https://androidpal.com/studio/about

