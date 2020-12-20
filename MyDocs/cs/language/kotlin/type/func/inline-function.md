使用高阶方法之后，每个方法都是一个对象，且持有最近的闭包    

显然是一个运行负担  

```kotlin
// 比如我们最开始编写同步锁方法  
val l : Lock = ...
fun lock1(){
    l.lock()
    try {
        foo()
    }
    finally {
        l.unlock()
    }
}
fun lock2(){
    l.lock()
    try {
        bar()
    }
    finally {
        l.unlock()
    }
}
// 有了高阶函数之后，抽取了一下方法
fun lock(val foobar = fun()->Unit){
    l.lock()
    try{foobar}
    finally
}
// 此时foobar 会产生对象，增加负担 
```

需要一个机制，让它是写法用简洁的写法，而生成的代码和非高阶函数时相同（不会有对象，只是顺序调用方法 

这个机制就是标记方法为**inline**  

```kotlin
inline fun <T> lock(lock: Lock, body: () -> T): T { ... }
```

此时内部的所有逻辑会在调用端被处理成inline  

如果有不需要处理的，需要用**noinline** 标记



#### 语法糖

我们知道lambda 的返回需要借助标签，  

但是如果使用lambda 参数的方法标记为inline , 则允许在lambda 中直接返回函数（non-local ）， 因为编译器将其改写了    

```kotlin
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // returns from hasZeros
    }
    return false
}
```

当然如果lambda 参数还有嵌套关系，需要使用`crossinline`

```kotlin
inline fun f(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ...
}
```

beak/continue 目前还不支持  



#### Reified type parameters

`reified`  

注意只有inline 方法可以使用（毕竟语法糖，不是改底层    

```kotlin
// 常规情况下，如果获取一个类的具体类型  
// 我们只能从class 入手，通过反射处理  
fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}
// 这在调用端就很丑  
treeNode.findParentOfType(MyTreeNode::class.java)  
// 希望和常规的函数调用保持一致，即：
treeNode.findParentOfType<MyTreeNode>()   
// 则需要声明T 为reified  
inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) { //且此时用as 也是ok  
        p = p.parent
    }
    return p as T?
}
```

