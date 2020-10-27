https://www.android.com/security-center/publications/  



```
Security 库提供了两种级别：

- 考量加密性和性能的强安全级别

    适合消费者，例如银行app 或者聊天app 等需要证书检查

- 最大安全级别  

    适合硬件keystore



使用两级key，低级keyset 直接加密文件、数据，keyset 本身就存储在`sharedPreference` ,高级 **master key** 则是加密keyset 的，存储在Android keystore 系统中。  



`EncryptedFile` , `EncryptedSharedPreferences`

<font color=red>说是有个Security 库，但是搜依赖也没搜到</font>
```



```
Android KeyStore 系统能让用户存储密钥（cryptographic keys） 

这么做的好处主要是更难被提取，而且在访问的时候对时间和方式做了限制  

需要4.3 以上，及Security 库，`KeyChain` API  



可以单独存在一个 硬件加密模块  

Trusted Execution Environment (TEE)  



三个方面：

*cryptography*: authorized key algorithm, operations or purposes (encrypt, decrypt, sign, verify), padding schemes, block modes, digests with which the key can be used;

合法时间间隔：

用户授权：



---

tink 库：
```



