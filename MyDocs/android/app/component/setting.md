```kotlin
val panelIntent = Intent(Settings.Panel.settings_panel_type)
startActivityForResult(panelIntent)
```

[`ACTION_INTERNET_CONNECTIVITY`](https://developer.android.google.cn/reference/android/provider/Settings.Panel#ACTION_INTERNET_CONNECTIVITY)

Shows settings related to internet connectivity, such as Airplane mode, Wi-Fi, and Mobile Data.

[`ACTION_WIFI`](https://developer.android.google.cn/reference/android/provider/Settings.Panel#ACTION_WIFI)

Shows Wi-Fi settings, but *not* the other connectivity settings. This is useful for apps that need a Wi-Fi connection to perform large uploads or downloads.

[`ACTION_NFC`](https://developer.android.google.cn/reference/android/provider/Settings.Panel#ACTION_NFC)

Shows all settings related to near-field communication *(NFC).*

[`ACTION_VOLUME`](https://developer.android.google.cn/reference/android/provider/Settings.Panel#ACTION_VOLUME)