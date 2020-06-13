ip地址，端口号

tcp:顺序无差错
http,ftp

udp:只认定起点和终点，不保证时间和可靠性  

URI：统一资源标识符
URL：统一资源定位器  
URL是一种特殊的URI，前者强调路径，后者强调资源  

http特点：简单快速，无连接，无状态  
**request：**
第一行包括请求方法，post/get，以及请求协议，http 1.1
host:
user-agent:浏览器信息
accept:可处理类型
referer:连接相关地址
accept-encoding:支持的编码
accept-language:支持的语言
if-non-match:从responce中获取一个etag，用来验证与之前的request的结果是否相同
cache-control:
if-modified-since:修改标记，

**responce:**
第一行类似request,协议和返回码
server:表示服务器的稳定信息
date:
last-modified:
etag:
expires:
cache-control:
proxy-connection:保持连接

**http1.1与http1.0**  
http1.0老一代可以优化的点：
- 浏览器阻塞：对于同一个域名，只能有4个连接
- DNS查询：
- 建立连接，每次都需要重新建立

1.1的不同：
缓存处理更多，不仅仅是if-modified-since  
支持请求资源的一部分范围  
支持host头处理  
长连接  

1.1的待优化：
中间人攻击  
header太大  
长连接对服务器性能要求更高  

get/post:
get是获取资源，参数放在url后面，用问号分割，有长度限制  
post是提交资源，放在包里的body，无长度限制，但是tomcat默认会设置   

cookie和session  
cookie是放在客户端的，请求之后服务器会在responce中放置一定的状态信息，就是cookie，设置到客户端。之后请求都带上cookie即可。  
session是客户端访问服务器之后，服务器自己设置一个session记录客户端信息。而客户端只会获取到session的id。之后请求都会带上id，方便服务器查询。  

https：
TLS/SSL  
对称加密，不对称加密  




socket，基本上是基于tcp/ip的稳定流套接字  
