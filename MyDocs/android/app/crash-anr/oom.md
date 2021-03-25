1、创建页面时使用intent.getString 等基础方法报错

```java
Fatal Exception: java.lang.RuntimeException: Parcel android.os.Parcel@427bec10: Unmarshalling unknown type code 7340129 at offset 604
       at android.os.Parcel.readValue(Parcel.java:2087)
       at android.os.Parcel.readArrayMapInternal(Parcel.java:2321)
       at android.os.Bundle.unparcel(Bundle.java:249)
       at android.os.Bundle.getString(Bundle.java:1118)
       at android.content.Intent.getStringExtra(Intent.java:4860)
```

try catch 之后报OOM

```java
Fatal Exception: java.lang.OutOfMemoryError
       at android.util.ArrayMap.allocArrays(ArrayMap.java:197)
       at android.util.ArrayMap.ensureCapacity(ArrayMap.java:307)
       at android.os.Bundle.unparcel(Bundle.java:247)
       at android.os.Bundle.getString(Bundle.java:1118)
       at android.content.Intent.getStringExtra(Intent.java:4644)
```

