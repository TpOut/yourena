讨论JNI 映射java 类型到 native C 类型  

https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/types.html  

使用modified UTF-8  字符串，多字节字符的字节，以大端形式 存储在class 文件中。其中和标准UTF-8 的区别是，0 是2个字节，且标准的4字节被处理成6字节的  

