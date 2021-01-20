```kotlin
// 语法是写在类前面
annotation class Fancy  

// 定义部分
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
@MustBeDocumented
annotation class Fancy  

// 构建时可以传递参数，这里有些细节看原文即可
annotation class Special(val why: String)
@Special("example") class Foo {}  

// 使用部分
@Fancy class Foo @Inject constructor(dependency: MyDependency) {
    var x: MyDependency? = null
        @Inject set
    
    @Fancy fun baz(@Fancy foo: Int): Int {
        return (@Fancy 1)
    }
}

fun @receiver:Fancy String.myExtension() { ... }

// 甚至用于lambda  
annotation class Suspendable
val f = @Suspendable { Fiber.sleep(10) }

// 甚至修改文件名
@file:JvmName("Foo")
package org.jetbrains.demo

// 语法糖  
// 由于注解可以用于很多个调用处，处处写很烦，
// 所以提供了“调用处注解”  
class Example(@field:Ann val foo,    // annotate Java field
              @get:Ann val bar,      // annotate Java getter
              @param:Ann val quux)   // annotate Java constructor parameter

// 提供了打包注解
class Example {
     @set:[Inject VisibleForTesting]
     var collaborator: Collaborator
}


```

