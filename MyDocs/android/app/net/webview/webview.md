CustomTabs



https://developer.chrome.com/multidevice/webview/overview  



检测：

浏览器渲染挂起检测 [`WebViewRenderProcessClient`](https://developer.android.google.cn/reference/android/webkit/WebViewRenderProcessClient)

 [`onRenderProcessResponsive()`](https://developer.android.google.cn/reference/android/webkit/WebViewRenderProcessClient#onRenderProcessResponsive(android.webkit.WebView, android.webkit.WebViewRenderProcess)) and [`onRenderProcessUnresponsive()`](https://developer.android.google.cn/reference/android/webkit/WebViewRenderProcessClient#onRenderProcessUnresponsive(android.webkit.WebView, android.webkit.WebViewRenderProcess))



`onShowFileChooser`  

setMixedContentMode()  setAcceptThirdPartyCookies()

智能化加载页面，如果去除：enableSlowWholeDocumentDraw()



8.0 websettings. savaFormData 的访问器无效，webviewDatabase 的clearFormData, hasFormData 无效  

运行在多进程  



其他进程可以 [`disableWebView()`](https://developer.android.google.cn/reference/android/webkit/WebView#disableWebView())   

[`WebView.setDataDirectorySuffix()`](https://developer.android.google.cn/reference/android/webkit/WebView#setDataDirectorySuffix(java.lang.String))  因为是不同实例，需要自行处理cockie  



使用安全模式：

AndroidManifest.xml

```
<manifest>
    <application>
        ...
        <meta-data android:name="android.webkit.WebView.EnableSafeBrowsing"
                   android:value="true" />
    </application>
</manifest>
```

MyWebActivity.java

```kotlin
private var superSafeWebView: WebView? = null
private var safeBrowsingIsInitialized: Boolean = false

// ...

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    superSafeWebView = WebView(this).apply {
        webViewClient = MyWebViewClient()
        safeBrowsingIsInitialized = false
        startSafeBrowsing(this@SafeBrowsingActivity, { success ->
            safeBrowsingIsInitialized = true
            if (!success) {
                Log.e("MY_APP_TAG", "Unable to initialize Safe Browsing!")
            }
        })
    }
}
```

MyWebViewClient.java

```kotlin
class MyWebViewClient : WebViewClient() {  // Automatically go "back to safety" when attempting to load a website that  // Safe Browsing has identified as a known threat. An instance of WebView  // calls this method only after Safe Browsing is initialized, so there's no  // conditional logic needed here.  override fun onSafeBrowsingHit(      view: WebView,      request: WebResourceRequest,      threatType: Int,      callback: SafeBrowsingResponse  ) {    // The "true" argument indicates that your app reports incidents like    // this one to Safe Browsing.    callback.backToSafety(true)    Toast.makeText(view.context, "Unsafe web page blocked.", Toast.LENGTH_LONG).show()  }}
```

