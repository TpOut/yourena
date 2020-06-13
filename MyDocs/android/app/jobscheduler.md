`jobscheduler.enqueue`  

`JobIntentService`   

[`JobInfo.Builder.setClipData()`](https://developer.android.google.cn/reference/android/app/job/JobInfo.Builder#setClipData(android.content.ClipData, int)) 

`JobInfo.isRequireStorageNotLow()`

`JobInfo.isRequireBatteryNotLow()`

[`NETWORK_TYPE_METERED`](https://developer.android.google.cn/reference/android/app/job/JobInfo#NETWORK_TYPE_METERED)



[`setEstimatedNetworkBytes()`](https://developer.android.google.cn/reference/android/app/job/JobInfo.Builder#setEstimatedNetworkBytes(long, long)), [`setPrefetch()`](https://developer.android.google.cn/reference/android/app/job/JobInfo.Builder#setPrefetch(boolean)), and [`setRequiredNetwork()`](https://developer.android.google.cn/reference/android/app/job/JobInfo.Builder#setRequiredNetwork(android.net.NetworkRequest))  

使用 [`Network`](https://developer.android.google.cn/reference/android/net/Network) object returned by [`JobParameters.getNetwork()`](https://developer.android.google.cn/reference/android/app/job/JobParameters#getNetwork())

