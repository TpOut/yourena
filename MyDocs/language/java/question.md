super.super.method\(\);\(这个错误，嘿嘿\)

Q2：super.super.toString\(\)为何错误



```java
public void example(){
    int i = 0;
    {
         int i = 0;  // its error
    }
}
```

**自动垃圾回收**

finalizer、System.runFinalizers\(true\)、Runtime.addShutdownHook\(\)



为什么注解比枚举更适合android。



**Serializable**

```java
public class C implements java.1o.Serializable {
  private int vl;
  private static double v2;
  private transient A v3 = new A();
}
```

如果一个对象不止一次写入对象流， 会存储对象的多份副本吗？ 答案是不会。 第一次写入 一个对象时， 就会为它创建一个序列号。 Java 虚拟机将对象的所有内容和序列号一起写入 对象流。 以后每次存储时， 如果再写入相同的对象， 就只存储序列号。 读出这些对象时， 它们的引用相同， 因为在内存中实际上存储的只是一个对象。 很多时候这个序列号是自己写入的，不然对象被修改之后，可能就无法反序列化了

