
JNI 是一个native 编程接口，允许jvm 中的java 代码和其他语言（c/c++, 汇编）编写的应用/库 进行交互  

它最大的好处是不会束缚底层jvm 的实现。让jvm 供应者（vendors）在添加jni 支持时，不影响其他模块。  

开发者只需要编写一次native 应用/库，就可以在所有支持JNI 的vm 上运行。


由于基本用法的方式比较麻烦，java 和 C 代码分离编写。所以我们在Android 项目中进行相关函数的基本练习。  

[介绍](./introduction.md)

[设计概览](./design-overview.md)

[类型映射](./JNI-types-and-data-structures.md)

[相关函数](./JNI-functions.md)

[invocation](./the-invocation-API.md)