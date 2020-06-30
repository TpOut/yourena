> 目前主要来自
>
>《java 程序设计第10版 - 基础/进阶》
>
>《java核心技术第9版 - 卷I》
>
>《java核心技术》有很多总结性的话，《java程序设计》更适合新学。



[命名风格](./../general/style.md)  
[类型](./type/type-index.md)  
lambda

> 可以看下这个链接：[https://www.zhihu.com/question/20125256](https://www.zhihu.com/question/20125256) 

标签
>  label79:{ if\(someCondition\){ break label79; } }

> java 对字母有一个特殊的对待
>
> ![ddd](./ddd.png)

[tips](./tips.md)
[安全](./secure.md)
[反射](./reflect.md)
[API](./api.md)
[异常](./exception.md)
[输入输出](./io.md)
[jar包](./jar.md)
[关键字](./keyword.md)
[多线程](multi-thread.md)
[调试](./debug/debug-index.md)
[jvm](./jvm/jvm-index.md)
[jni](./jni/jni-index.md)
[疑问](./question.md)

`import java.util.*` 声明明确导入和声明通配符导入在性能上是没有什么差别的 



```java
static class DeadLoopClass{
  static{
    //如果不加if，编译器会检测到while(ture) 死循环，同时发现在静态初始化部分，然后拒绝编译
    //添加if 则会“绕过”这个检测
    if(true){
      Sytem.out.println("...");
      while(true){
        
      }
    }
  }
}
```

---
设计模式 : https://java-design-patterns.com/patterns/

快速容器化jib : https://jaxenter.com/jib-java-containerization-146647.html

10本必读书：[https://javarevisited.blogspot.com/2018/06/10-all-time-great-books-for-java.html](https://javarevisited.blogspot.com/2018/06/10-all-time-great-books-for-java.html)  

知识收集：[https://github.com/crossoverJie/JCSprout](https://github.com/crossoverJie/JCSprout)  

多线程编程：[https://www.tutorialdocs.com/article/java-inter-thread-communication.html](https://www.tutorialdocs.com/article/java-inter-thread-communication.html)   

java的null设计错误：[https://medium.com/@elizarov/null-is-your-friend-not-a-mistake-b63ff1751dd5](https://medium.com/@elizarov/null-is-your-friend-not-a-mistake-b63ff1751dd5)



