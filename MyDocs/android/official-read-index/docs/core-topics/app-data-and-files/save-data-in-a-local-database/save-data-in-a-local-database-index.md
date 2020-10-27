基本都用注解实现，

entitiy 类对应表，列可以嵌套（实际是平摊），可以自定义复杂类和对应列值的转换

表一对多，多对多（以及嵌套），可以定义一个方法快速查询

支持视图，增删改查（多个查询可以合并为一个事务）  

编译生成

> 可以检查错误，变量名引用于数据库语句，POJO 映射查询列，多种返回类型等	

支持Rxjava 

支持从文件（assets / disk file）恢复

升级和迁移，测试和[分析器](../../../android-studio/debug-your-app/debug-your-db-with-inspector.md)



- overview

  Last updated 2020-08-27 UTC.

- defining data using room entities

  Last updated 2019-12-27 UTC.

- define relationship between objs

  Last updated 2020-03-20 UTC.

- create views into a database

    Last updated 2019-12-27 UTC.

- access data using daos

  Last updated 2020-07-13 UTC.

- pre-populate your database

- migrate your database 

- test and budeg your database  

    Last updated 2020-10-12 UTC.

- reference complex data

  Last updated 2019-12-27 UTC

- save data using SQLite



---

https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/

https://codelabs.developers.google.com/codelabs/android-persistence/