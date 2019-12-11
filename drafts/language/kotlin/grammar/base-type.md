**val** 是一个不能重新赋值的变量  
**var** 则可以  

```kotlin
val temp = "ddd" //静态类型，推断是在编译时确定的
//不能对一个普通类型赋值为Null，但是在后面加问号则表示这个类型是nullable类型，即可以设置为null
var temp: String?
//基本类型
Byte, Short, Long, Float, Double

//如果类型是 : T?
//在.调用时加 ?，如果temp是null，那么结果也是null
temp?.toUpperCase() 

if(temp != null){
  temp.toUpperCase() //如果已经判断了，那么不需要加
}

temp!!.toUpperCase() //断言非空，如果是空，会抛异常
```

