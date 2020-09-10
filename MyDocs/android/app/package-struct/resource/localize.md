字符串命名时，可能需要考虑组件类型，这样在做翻译的时候能大致知道宽度限制程度。  

不翻译 `<xliff:g>`

本地化需要考虑的情况列表，在原文有指出  

```kotlin
// 代码指定本地化
val primaryLocale: Locale = context.resources.configuration.locales[0]
val locale: String = primaryLocale.displayName
```



```shell
# 切换locale
adb shell setprop persist.sys.locale [BCP-47 language tag];stop;sleep 5;start
```



还可以用pseudolocales 测试（没看出来和设置本地语言什么区别）  



**unicode 和国际化支持库：**

6.0以前，ICU、CLDR ； 对应暴露`Locale` ，`Character`，`java.text` 的子类；需要打包进apk

7.0，ICU4J 部分； 对应暴露`android.icu` ；系统提供



**语言查找策略**：

6.0 及老版本：

Try fr_CH => Fail
Try fr => Fail
Use default (en)

7.0 之后，且支持多个语言查找：

Try fr_CH => Fail
Try fr => Fail
Try children of fr => fr_FR
Use fr_FR



7.0 之后api，获取列表：`LocaleList.getDefault()`

除了常规的大地区翻译不同，一个大地区的小划分中，数字的格式化、日期等使用习惯都可能不同。  

如：

```kotlin
val skeleton: String = if (DateFormat.is24HourFormat(context)) "Hm" else "hm"
val formattedTime: String = android.icu.text.DateFormat.getInstanceForSkeleton(
        skeleton,
        Locale.getDefault()).format(Date()
)

// 数字使用string 的占位模式：%d 
```



android 10 增加了：`Transliterator.getAvailableIDs()`

