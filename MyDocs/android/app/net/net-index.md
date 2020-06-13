`ConnectivityManager`  

`NetworkRequest` `requestNetwork` `registerNetworkCallback`  

`onAvailable` `Network`  



 ProxySelector.setDefault() 只会提取传入的地址，而不包含路径、参数等  

InetAddress.isReachable() 会尝试 ICMP 

8.0 之后不再支持SSLv3，老版本TLS 协议



网络变化：CONNECTIVITY_ACTION，需要动态注册

相机存储： `ACTION_NEW_PICTURE` and `ACTION_NEW_VIDEO`，不再发送



SSLSocketFactory  

自定义CA  

可调试  

明文传输报错  

选择可信任服务端  



SECCOMP  



DNS 获取：ACCESS_NETWORK_STATE NetworkRequest NetworkCallback  

```
Build.getSerial()  
```

