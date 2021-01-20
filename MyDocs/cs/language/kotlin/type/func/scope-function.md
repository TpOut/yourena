**作用域方法**

这些方法会形成一个临时scope，包括 let, run, let, apply, also

https://github.com/JoseAlcerreca/kotlin-std-fun



在kotlin 标准库中，有很多方法，只是为了在某个对象环境中执行一段代码

基于lambda表达式的支持，这段代码可以直接访问对象而不需要变量名



有五个方法，他们的区别主要在于对象如何被代码块访问，以及表达式的返回值

对于访问，有lambda receiver(`this` ) 和lambda argument(`it` ) 的区别  

run \ with \ apply 用this， 因为this 可以省略，容易和外部混淆，一般建议使用属性、方法配置  

let \ also 使用it,  一般在对象作为方法参数时使用  

run \ with \ let 返回lambda 结果 ； apply \ also 返回对象



> Here is a short guide for choosing scope functions depending on the intended purpose:
>
> - Executing a lambda on non-null objects: `let`
> - Introducing an expression as a variable in local scope: `let`
> - Object configuration: `apply`
> - Object configuration and computing the result: `run`
> - Running statements where an expression is required: non-extension `run`
> - Additional effects: `also`
> - Grouping function calls on an object: `with`