[`USE_FULL_SCREEN_INTENT`](https://developer.android.google.cn/reference/android/Manifest.permission#USE_FULL_SCREEN_INTENT) 



锁屏等级 `setVisibility`  

private：基本信息

public : 所有信息

secret ：无



Notification.Builder

setPublicVersion  

setCategory 

setPriority

addPerson

setVisibility()

https://material.google.com/patterns/notifications.html  



Notification.MediaStyle  

RemoteViews.RemoteView  

fullScreenIntent



 `RINGER_MODE_SILENT`  `RINGER_MODE_NORMAL`  `RINGER_MODE_VIBRATE`

`STREAM_RING` or `STREAM_NOTIFICATION`.



`NotificationListenerService`  

`Notification.extras` `Notification.Action`  



onNotificationRemoved()  

`setTimeoutAfter()`

 `setSettingsText()` 

`Notification.INTENT_CATEGORY_NOTIFICATION_PREFERENCES` `EXTRA_CHANNEL_ID`, `NOTIFICATION_TAG`, and `NOTIFICATION_ID`.



```
setColor()`. Doing so allows you to use `setColorized()
```

```
addHistoricMessage()
```



8.1 声音频率1秒1次  

低ram 设备`ActivityManager.isLowRamDevice()` 不支持：

`NotificationListenerService` and `ConditionProviderService`



```kotlin
// Create new Person.
val sender = Person()
        .setName(name)
        .setUri(uri)
        .setIcon(null)
        .build()
// Create image message.
val message = Message("Picture", time, sender)
        .setData("image/", imageUri)
val style = Notification.MessagingStyle(getUser())
        .addMessage("Check this out!", 0, sender)
        .addMessage(message)
```



- [`EXTRA_REMOTE_INPUT_DRAFT`](https://developer.android.google.cn/reference/android/app/Notification#EXTRA_REMOTE_INPUT_DRAFT) 

    [`setGroupConversation()`](https://developer.android.google.cn/reference/android/app/Notification.MessagingStyle#setGroupConversation(boolean)) t

-  [`setSemanticAction()`](https://developer.android.google.cn/reference/android/app/Notification.Action.Builder#setSemanticAction(int)) 

- e [`RemoteInput.setChoices()`](https://developer.android.google.cn/reference/android/app/RemoteInput.Builder#setChoices(java.lang.CharSequence[])) to



isBlocked ?  

 [`getNotificationChannelGroup()`](https://developer.android.google.cn/reference/android/app/NotificationManager#getNotificationChannelGroup(java.lang.String))   



9.0 [`NotificationManager`](https://developer.android.google.cn/reference/android/app/NotificationManager#constants) 加了很多类型  

10.0 允许添加机器学习按钮，[setAllowGeneratedReplies()](https://developer.android.google.cn/reference/android/app/Notification.Action.Builder#setAllowGeneratedReplies(boolean)) and [setAllowSystemGeneratedContextualActions()](https://developer.android.google.cn/reference/android/app/Notification.Builder#setAllowSystemGeneatedContextualActions(boolean)).  

支持SeekBar  



10 通知自动建议actions  

[`setAllowGeneratedReplies()`](https://developer.android.google.cn/reference/android/app/Notification.Action.Builder#setAllowGeneratedReplies(boolean)) and [`setAllowSystemGeneratedContextualActions()`](https://developer.android.google.cn/reference/android/app/Notification.Builder#setAllowSystemGeneratedContextualActions(boolean)). 



