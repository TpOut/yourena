Android 10 go 设备无法获取SYSTEM_ALERT_WINDOW , 老版本升级的保持  



可折叠适配:

[`onTopResumedActivityChanged()`](https://developer.android.google.cn/reference/android/app/Activity#onTopResumedActivityChanged(boolean))   

[`android:minAspectRatio`](https://developer.android.google.cn/reference/android/R.attr#minAspectRatio)   



```shell
# 开启限制接口：
adb shell settings put global hidden_api_policy  1
```



AFW 完全忽略  

[`PrecomputedText`](https://developer.android.google.cn/reference/android/text/PrecomputedText) 

[`Magnifier`](https://developer.android.google.cn/reference/android/widget/Magnifier) 

Text Layout

TextClassifier , 语言检测(detectLanguage，[`ConversationActions`](https://developer.android.google.cn/reference/android/view/textclassifier/ConversationActions)   



设备号：`READ_PHONE_STATE`,  [`getSerial()`](https://developer.android.google.cn/reference/android/os/Build#getSerial()).  



8.0 app 前台的定义：可见activity；前台服务；绑定前台服务的app（使用IME, wallpaper service, notification listener, voice/text service )  

后台服务被停止时，就等于调用了stopself 



无法后台访问，麦克风和相机



语言和国际化：

Locale.getDefault(Category.DISPLAY)

- `Currency.getDisplayName()`
- `Currency.getSymbol()`
- `Locale.getDisplayScript()`



  `SYSTEM_ALERT_WINDOW` 

- `TYPE_PHONE`
- `TYPE_PRIORITY_PHONE`
- `TYPE_SYSTEM_ALERT`
- `TYPE_SYSTEM_OVERLAY`
- `TYPE_SYSTEM_ERROR`
- `TYPE_APPLICATION_OVERLAYTYPE_APPLICATION_OVERLAY` 



apk 安装  

`canRequestPackageInstalls()`.  

DISALLOW_INSTALL_UNKNOWN_SOURCES



应用id 可以使用ANDROID_ID，有google 广告的使用Advertising ID  



//查看api 更新

https://developer.android.google.cn/sdk/api_diff/19/changes



`android.app.usage` 设置里开启，可以分析每个app 的使用统计数据，最近使用时间，一段时间内使用时长，前后台切换，横竖屏切换  



ime : shouldOfferSwitchingToNextInputMethod  

switchToNextInputMethod  



`setUserAuthenticationValidityDurationSeconds()`  

`createConfirmDeviceCredentialIntent()`  



ChooserTargetService  



`Display.Mode` `getPhysicalWidth` `getSize`  

`preferredDisplayModeId`  



Context.getColorStateList()  



文字选择浮窗：`View` or `Activity` object, change your `ActionMode` calls from `startActionMode(Callback)` to `startActionMode(Callback, ActionMode.TYPE_FLOATING)`.  

`ActionMode.Callback2` 

 `onGetContentRect()` 

`invalidateContentRect()` 



快捷设置：`Tile`  



CallScreeningService



表情、换肤检查：hasGlyph(String)  

表情变体：https://www.unicode.org/Public/UNIDATA/StandardizedVariants.txt  

换肤：http://unicode.org/emoji/charts/full-emoji-list.html



onResolvePointerIcon()  



`DownloadManager` is using `ContentResolver.openFileDescriptor()`.  



设置》尺寸》字体大小变化，适配  

  

Debug.startMethodTracing() 现在存储在包目录共享存储  

Binder 传输数据过大会抛出异常  TransactionTooLargeExceptions  

view post runnable 在attach 之后才会执行  

如果卸载其他app 安装的应用，需要调用`PackageInstaller.uninstall()` 时监听`STATUS_PENDING_USER_ACTION`  



使用ACTION_MANAGE_STORAGE 引导用户到释放空间页面  



添加 [maxAspectRatio](https://developer.android.google.cn/reference/android/R.attr#maxAspectRatio)   



新属性：`layout_marginVertical` 包含`marginTop` `margintBottom`，同理horizontal ，padding   

不影响`start` 而影响`left`  



android:appCategory  



[`android:keyboardNavigationCluster`](https://developer.android.google.cn/reference/android/view/View#attr_android:keyboardNavigationCluster)   

`setKeyboardNavigationCluster()`  

`android:touchscreenBlocksFocus`  

`android:focusedByDefault`

`setFocusedByDefault()`



strictMode 规则：无buffer IO、无权限uri、未设置tag socket  

缓存数据按较多先删，按修改时间久远先删  

StorageManager.setCacheBehaviorAtomic() 设置原子删除  

setCacheBehaviorTombstone(File, boolean) 设置文件空  

 `allocateBytes(FileDescriptor, long)` `getAllocatableBytes(UUID)` `getUsableSpace()`



```
ArrayList<String> annotations = new ArrayList<>();

annotations.add("topic1");
annotations.add("topic2");
annotations.add("topic3");

intent.putStringArrayListExtra(
    Intent.EXTRA_CONTENT_ANNOTATIONS,
    annotations
);
```



8.0 editText.getText 返回Editable，可以直接做标记处理   

Text classifier  

