> 这里的类表示的是object ，而不是class 文件  

一般情况下， 最好能为每个类提供一个无参构造方法， 以便于对该类进行扩展， 同时避免错误

如果一个类有一个引用对象变量，如createDate，在设计getCreateDate\(\)的时候，不要直接返回该变量，会可能造成误操作修改。可以返回一个clone

Object.equals\(Object a, Object b\); 在重写equals方法的时候，可以先调用super.equals进行比较，再对剩余继承的变量进行比较即可。 章5.2.2说明了equals的设计理念。
Object.hashCode\(Object obj\);Object.hash\(Object... objs\)

子类可以覆盖父类的方法，并且定义成abstract。