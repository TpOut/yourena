# note

### 前后台带走

Spring 5.0之后有Spring版本  
Vert.x 构建基于JVM的 web App的框架,有专门的kotlin支持\(github\)和文档\([http://vertx.io/docs/vertx-core/kotlin/](http://vertx.io/docs/vertx-core/kotlin/)\)  
ktor 是jetBrains开发的创建web app的框架  
kotlinx.html 是一个脚本语言 持久化存储支持：直接JDBC访问，JPA,以及使用java驱动NoSQL数据库

heroku ?

### Android带走

小几百的方法数，apk增加的大小少于100k

已使用公司的分享：  
Pinterest:[https://www.youtube.com/watch?v=mDpnc45WwlI](https://www.youtube.com/watch?v=mDpnc45WwlI)  
Basecamp's:[https://m.signalvnoise.com/how-we-made-basecamp-3s-android-app-100-kotlin/](https://m.signalvnoise.com/how-we-made-basecamp-3s-android-app-100-kotlin/)  
keepsafe:[https://medium.com/keepsafe-engineering/lessons-from-converting-an-app-to-100-kotlin-68984a05dcb6](https://medium.com/keepsafe-engineering/lessons-from-converting-an-app-to-100-kotlin-68984a05dcb6)

### js带走

目前对接ECMAScript 5.1 并且正在考虑ECMAScript 2015  
实现是靠代码转化，但是会除去jdk，jvm,java框架或库 的代码  
客户端：可操作dom静态类型接口,可视化操作：WebGL  
服务端：可以和服务端的js交互，如Node.js

kotlin兼容commonjs,amd,umd等  
kotlin可以和已有的第三方库或者框架,如jquery/react 一起使用。  
对于访问强类型api： 可以对[Definitely Typed](http://definitelytyped.org/)这里定义的类型，使用ts2kt工具转化成kotlin  
对应非强类型：直接使用动态类型

### 原生带走

原生的意思是，直接编译（llvm）成原生二进制，这样运行不需要虚拟机环境  
适用一些嵌入式、ios等：[https://kotlinlang.org/docs/reference/native-overview.html\#target-platforms](https://kotlinlang.org/docs/reference/native-overview.html#target-platforms)

### Coroutines

应该是一个独有的概念，可以用来实现异步编程，当然也支持多线程，actors。。。

### 多平台

是一个试验功能。。那我就不看了。。

### 基本语法

包名和路径名并不需要一样

