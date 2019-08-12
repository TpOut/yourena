首先看文档：  https://square.github.io/okhttp/

Http是现代的网络方式。

然后OkHttp
- 支持 HTTP/2 -- 可以让目标是相同主机的所有请求分享一个socket  
- 如果某些情况无法使用HTTP/2, 支持连接池，可以减少请求的延迟（latency）
- 支持GZIP传输压缩，可以透明化的减少下载大小  
- 支持服务器相应缓存，可以禁止网络完全重复的请求

对于网络问题，会静默处理常规的链接问题  
比如服务如果有多个ip地址（ipv4+ipv6 或者 冗余服务主机），okhttp会自动切换
又或者一些现代TLS特性（TLS 1.3, ALPN, certificate pinning），让okhttp能够兼容为广义连接（fall back for broad connectivity）  

然后就是使用了，api的设计很强大，让你流式调用且不会变化，支持同步异步。  
get,post是基本操作。

OkHttp 环境需求 Android5.0+, java8+  
或者 3.12.x分支支持 Android2.3+, Java7+ ,但是这些平台不支持TLS1.2，所以不建议使用。但是为了兼容，还是会在2020.12.31之前修复重大bug    
依赖高性能的i/o库 Okio  

okhttp会持续追踪TLS生态，并且调整自身来提升连接性能和安全，所以建议时长更新。  
一般性okhttp使用系统内置的tls实现，如java是 BoringSSL -- 支持Conscrypt  
okhttp可以设置conscrypt为首要安全provider。  
`Security.insertProviderAt(Conscrypt.newProvider(), 1);`  

R8/ProGuard 选项：https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro  

服务器模拟测试：  
`testImplementation("com.squareup.okhttp3:mockwebserver:4.0.1")`   

## 请求  
包含 url, 方法（get,post）,一系列headers， body（某一种明确类型的数据流）  

这里有一个条件get(conditional GET)的概念，专门针对已经访问过某网站的场景。    

### 重写
当用okhttp发送http请求时，为了准确性和高效性，Okhttp会做一些重写。
比如添加原请求中没有存在的一些header:`Content-Length, Transfer-Encoding, User-Agent, Host, Connection, and Content-Type`, 还有`Accept-Encoding`.如果获取了cookies，会添加`Cookie`  

对于缓存响应，条件get会添加`If-Modified-Since`和`If-None-Match`的头来更新。    

### 跟随（follow-up）  
如果Url转移，且服务器返回302和新的地址。okhttp会跟随重定向结果。  

如果响应需要认证，Okhttp会询问配置的认证器（ Authenticator：https://square.github.io/okhttp/4.x/okhttp/okhttp3/-authenticator/）获取证书，并携带证书重试。  
这里可以用 Credentials.basic处理基本的请求编码，或者Response.challenges获取主题和领域    
获悉重试的次数`response.priorResponse`,  

### 重试
不同路由  

### RequestBody  
对于提交form和multipart部分的时候，可以很明显的看到，是兼容html页面的形式的。  
其中每个multipart请求体都可以定义自己的请求头  
如果有定义，那么这些头应该描述这些请求体，如`Content-Disposition`；`Content-Length、Content-Type`会在可用的情况下，被自动添加  

## 响应
包含 code（200,404）, headers, 可选的body  

### ResponseBody
okhttp对响应体使用 `ResponseBody`，每个响应体受限于资源（比如网络基于socket,缓存基于文件），所以需要合理处理资源的关闭。   

而且因为response可能很大，超过memory或者超出了本地存储大小，  
所以okhttp使用ResponseBody 来保持response的流连接。  
同时应用重新读取同一个请求的可能性很小，  
所以使用bytes/string时是一次性的（使用后会关闭）；  
或者可以用source/byteStream/charStream来持有整个流。  
>注意charStream使用`Content-Type`的header値来解析，默认使用UTF-8  

异步返回时，在onResponse里面读取Body依旧可能造成阻塞。  

### 重写  
如果无痕压缩(transparent compression)被使用，Okhttp会丢弃`Content-Encoding，Content-Length`，因为他们对解压后的响应body没用。  

如果条件get获取成功，根据规范，会合并网络和缓存的响应。  

## Call  
okhttp这边使用`Call`来满足日常请求的需求。  `As this object represents a single request/response pair (stream), it cannot be executed twice`    
异步使用Callback回调（和调用线程不一样）.  
Call可以在任何线程被取消，但是如果在写请求体或者读响应体的时候会抛出IOException      

同步请求需要自己控制并发数，不然浪费太多资源（延迟）    
异步请求会有Dispatcher来实现控制，可以设置每个服务器的最大数量，以及所有请求的最大并发数。  

## OkHttpClient  
大部分应用只需要一个OkHttpClient  
但是请求的配置可以单独设置。 `okHttpClient.newBuilder()`这个方法返回全局的“复制体”,共享连接池，dispatcher,以及配置  

## 连接  
虽然使用的时候只需要提供url，但是okhttp会尝试使用 url, address, route  

### URLs
只能够从http/https中看出是 明文（plaintext）的或加密的，甚至不知道用的啥加密算法      

没有明确对等证书的认证：https://developer.android.com/reference/javax/net/ssl/HostnameVerifier  
也没有说明如何信任一个证书：https://developer.android.com/reference/org/apache/http/conn/ssl/SSLSocketFactory.html  
同样没有对 是否使用代理服务、怎么使用代理服务器认证  进行说明  

### Addresses  
指出了服务器，以及所有需要的静态配置信息：端口号，https设置，偏好协议（http/2 或者 SPDY）  

使用同一个服务器的请求可能会被复用同一个tcp socket连接,通过ConnectionPool 管理    
### Routes  
可以通过路由获取连接到服务器的动态信息  
包括明确的ip地址（通过dns请求），确切的代理服务器（如果ProxySelector在使用），TLS的版本  

## Okio  
用来处理io操作  

## 缓存  
缓存目录不能同时多个访问     
响应缓存使用http头来设置所有配置。`Cache-Control:max-stale=3600`、`Cache-Control:max-age=9600`  
可以设置FORCE_CAECHE/FORCE_NETWORK 来修改

## 超时  
客户端连接超时  
读数据超时  
写数据超时  

## 拦截器  
主要实现是`chain.proceed()`
- application 拦截
在重定向的场景下（url -> url2），只会调用一次，拦截的是 请求url的数据和 url2的响应数据  
- 网络拦截器  
在重定向的场景下（url -> url2），会调用两次，url的请求响应，url2的请求响应  



## 其他
注意其中多次提到，内存不要超过1M  
