https://android.googlesource.com/platform/bionic/+/master/android-changes-for-ndk-developers.md



ELF TLS instead of `emutls`

```
libgcc/compiler-rt
```



fdsan 分析fd 的错误拥有  

https://android.googlesource.com/platform/bionic/+/master/docs/fdsan.md  



文本重定位常规解决：  

https://wiki.gentoo.org/wiki/Hardened/Textrels_Guide  



7.0 更新警告：  

`libandroid_runtime.so`, `libcutils.so`, `libcrypto.so`, and `libssl.so`  



查看动态库用到的动态库列表：

```
aarch64-linux-android-readelf -dW libMyLibrary.so
```



属性获取：

```
#include <sys/system_properties.h>

__system_property_get
```