is A View that **displays web pages**.  
This class is the basis upon which you can **roll your own web browser** or simply display some online content within your Activity  
 It uses the **WebKit rendering engine** to display web pages and includes methods to navigate forward and backward through a history, zoom in and out, perform text searches and more. 
 
默认行为：  
By default, a WebView provides no browser-like widgets, does not enable JavaScript and web page errors are ignored.  
 

外部打开应用：
Uri uri = Uri.parse("https://www.example.com");
 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
 startActivity(intent);

创建方式：  xml或者代码
WebView webview = new WebView(this);
 setContentView(webview);

接着加载页面：
可以直接加载页面或者自己写一个页面，注意编码？

javascript same origin policy

loadData(String data, String mimeType, String encoding)
使用base64或者URL编码，除了base64的编码，the data uses ASCII encoding for octets inside the range of safe URL characters and use the standard %xx hex encoding of URLs for octets outside that range----详情看
[rfc3986](https://tools.ietf.org/html/rfc3986#section-2.2)
mimeType，默认为text/html,如果解析不了，就下载
The 'data' scheme URL formed by this method uses the default US-ASCII charset。如果有其他的scheme,需要以loadUrl调用，且优先级大于html或者XML文档自身的规定。

loadDataWithBaseUrl()
Note that content specified in this way can access local device files (via 'file' scheme URLs) only if baseUrl specifies a scheme other than 'http', 'https', 'ftp', 'ftps', 'about' or 'javascript'.

WebChromeClient  
WebViewClient 
WebSettings
addJavascriptInterface(Object, String) 

显示进度
```
// Let's display the progress in the activity title bar, like the
 // browser app does.
getWindow().requestFeature(Window.FEATURE_PROGRESS);
activity.setProgress(progress * 1000);
```

Using zoom if either the height or width is set to WRAP_CONTENT may lead to undefined behavior and should be avoided.

cache, cookie store -- not share the Browser application's data
By default, requests by the HTML to open new windows are ignored. This is true whether they be opened by JavaScript or by the target attribute on a link。可以用WebChromeClient定义行为

横向纵向变更引起的重新创建。

HTML5 Video support -- hardware acceleration 