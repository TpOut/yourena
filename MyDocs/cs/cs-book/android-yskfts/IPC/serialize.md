```java
Serializable{
    serialVersionUID // 反序列化校验
    writeObj
    readObj
}
```

```java
Parcelable{
  
}
```

serializable 序列化/反序列化 需要大量I/O 操作

Parcelable 则效率较高，使用麻烦



后者主要用在内存序列化；前者在存储设备序列化或 网络传输时更方便

