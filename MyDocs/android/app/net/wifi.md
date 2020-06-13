- [`WIFI_P2P_CONNECTION_CHANGED_ACTION`](https://developer.android.google.cn/reference/android/net/wifi/p2p/WifiP2pManager#WIFI_P2P_CONNECTION_CHANGED_ACTION)
- [`WIFI_P2P_THIS_DEVICE_CHANGED_ACTION`](https://developer.android.google.cn/reference/android/net/wifi/p2p/WifiP2pManager#WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)



```kotlin
// 快速创建

val manager = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
val channel = manager.initialize(this, mainLooper, null)

// prefer 5G band for this group
val config = WifiP2pConfig.Builder()
    .setNetworkName("networkName")
    .setPassphrase("passphrase")
    .enablePersistentMode(false)
    .setGroupOperatingBand(WifiP2pConfig.GROUP_OWNER_BAND_5GHZ)
    .build()

// create a non-persistent group on 5GHz
manager.createGroup(channel, config, null)

manager.connect(channel, config, null)
```







 Wi-Fi Aware 无线感知 



```kotlin
val ss = ServerSocket()
val ns = WifiAwareNetworkSpecifier.Builder(discoverySession, peerHandle)
  .setPskPassphrase("some-password")
  .setPort(ss.localPort)
  .build()

val myNetworkRequest = NetworkRequest.Builder()
  .addTransportType(NetworkCapabilities.TRANSPORT_WIFI_AWARE)
  .setNetworkSpecifier(ns)
  .build()
  
  
val callback = object : ConnectivityManager.NetworkCallback() {
  override fun onAvailable(network: Network) {
    ...
  }
  
  override fun onLinkPropertiesChanged(network: Network,
      linkProperties: LinkProperties) {
    ...
  }

  override fun onCapabilitiesChanged(network: Network,
      networkCapabilities: NetworkCapabilities) {
    ...
    val ti = networkCapabilities.transportInfo
    if (ti is WifiAwareNetworkInfo) {
       val peerAddress = ti.peerIpv6Addr
       val peerPort = ti.port
    }
  }
  override fun onLost(network: Network) {
    ...
  }
};

connMgr.requestNetwork(networkRequest, callback)
```





高性能低延迟  

[WifiManager.WifiLock.createWifiLock()](https://developer.android.google.cn/reference/android/net/wifi/WifiManager.html#createWifiLock(int, java.lang.String)) with `WIFI_MODE_FULL_LOW_LATENCY` or `WIFI_MODE_FULL_HIGH_PERF`.



 [`WifiNetworkSpecifier`](https://developer.android.google.cn/reference/android/net/wifi/WifiNetworkSpecifier)  [`NetworkRequest`](https://developer.android.google.cn/reference/android/net/NetworkRequest) 

[`WifiNetworkSuggestion`](https://developer.android.google.cn/reference/android/net/wifi/WifiNetworkSuggestion)  [`addNetworkSuggestions()`](https://developer.android.google.cn/reference/android/net/wifi/WifiManager#addNetworkSuggestions(java.util.List)) and [`removeNetworkSuggestions()`](https://developer.android.google.cn/reference/android/net/wifi/WifiManager#removeNetworkSuggestions(java.util.List)), 



热点  

`WifiEnterpriseConfig`

 `setPlmn()` `setRealm()`.  `WifiConfiguration`  `FQDN` `providerFriendlyName`  `isPasspointNetwork()`



```
enableNetwork() with the disableAllOthers=true 
openConnection()`, `bindSocket()`, and the new `bindProcessToNetwork()
```



Wi-Fi Aware  

Neighbor Awareness Networking (NAN)



*Round-Trip-Time* (RTT)



wps 废弃  



---

https://github.com/android/connectivity-samples/tree/master/WifiRttScan  

