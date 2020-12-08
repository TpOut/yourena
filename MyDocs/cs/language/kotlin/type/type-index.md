[base-type](./base-type.md)

[string](./string.md)

[array](./array.md)

[class](./class.md)

[enum](./enum.md)

[generic](./generic.md)

[inline](./inline.md)

[typealias](./typealias.md)



**Unit** 



**Nothing** 

有助于编译器判断该方法后续代码不会执行

表达式没有返回的时候，就是

> throw Exception() 返回 Nothing

方法不会返回值的时候，也是  

> TODO() 返回 Nothing

类似的，当我们要抛出异常时，自定义方法可以返回Nothing



默认给变量指定null 值，且不声明类型，初始化为Nothing? 类型  





各种索引都可以用[] 的形式访问