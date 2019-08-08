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

服务器模拟：  
`testImplementation("com.squareup.okhttp3:mockwebserver:4.0.1")`   

## 请求  
包含 url, 方法（get,post）,一系列headers， body  


## 响应
包含 code（200,404）, headers, 可选的body  
