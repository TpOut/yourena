`backing fields ` -> 备份字段



**Any**

所有对象的基类  

`NaN` 和自己比较才相等，否则大于任何其他数，包括`POSITIVE_INFINITY` 

`-0.0` 比 `0.0` 要小



`interface`  

定义的属性没有支持字段，所以不能访问  

可以自己实现一个和备份字段无关的访问器，实现后子类/子接口不会被要求重写这个属性  

也可以在实现子类重写属性时赋值  

多继承，使用`super<SuperClass>.superFun()`  