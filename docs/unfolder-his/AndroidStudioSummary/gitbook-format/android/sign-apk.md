检查是否允许未知来源你的app安装：canRequestPackageInstalls()   

Your application must be signed with a cryptographic key whose validity period ends after 22 October 2033.

End User License Agreement (EULA)  

 WebView.setWebContentsDebuggingEnabled()   

google play最大的versionCode值是  2100000000  
获取包信息的api: PackageManger.getPackageInfo(java.lang.String, int)  

debug keystore的默认位置：$HOME/.android/debug.keystore  

```
# 命令行生成签名  
keytool -genkey -v -keystore my-release-key.jks
-keyalg RSA -keysize 2048 -validity 10000 -alias my-alias
# 未签名包对其  
zipalign -v -p 4 my-app-unsigned.apk my-app-unsigned-aligned.apk
# 签名
apksigner sign --ks my-release-key.jks --out my-app-release.apk my-app-unsigned-aligned.apk
# 确认已经签名
apksigner verify my-app-release.apk
```  
签名的效用：  
- app更新
- app模块化：Android允许带有相同签名（certificate）的多个apk运行在同一个进程。这样实现了模块化（。。。太简单的说明了吧）  
- 携带权限的代码/数据共享：Android提供了基于签名的权限共享机制。
