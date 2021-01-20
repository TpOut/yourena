需要单独依赖包  

引用符号`::`    

很多接收器是通过参数形式的，用绑定形式引用后就是真正的接收器形式  

- 类引用

    引用的是KClass 类型

    ```kotlin
    // 类字面量 语法
    val c = MyClass::class    
    c.java // 有一个实例是java 的class 类型  
    
    fun getKClass(o: Any): KClass<Any> = o.javaClass.kotlin // 反之
    ```

    ```kotlin
    // 可以用对象实例当接收器  
    val widget: Widget = ...
    assert(widget is GoodWidget) { "Bad widget: ${widget::class.qualifiedName}" }
    ```

- 可调动(Callable)引用

    例如方法、属性、构造器等，共同父类 `KCallable<out R>`  

    - 方法类型属于`KFunction<out R>`，主要用于组合

        ```kotlin
        fun isOdd(x: Int) = x % 2 != 0
        val numbers = listOf(1, 2, 3)
        println(numbers.filter(::isOdd)) // ::isOdd 表示(Int) -> Boolean 类型的值
        
        // 成员方法或者 拓展方法时，需要类限定符，如
        String::toCharArray  
        
        // 但是拓展函数如果不显式声明出来，是没有接收器的（通过参数实现    
        val isEmptyStringList: List<String>.() -> Boolean = List<String>::isEmpty 
        ```

        ```kotlin
        fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
            return { x -> f(g(x)) }
        }
        
        fun isOdd(x: Int) = x % 2 != 0
        
        fun main() {
            fun length(s: String) = s.length
        
            val oddLength = compose(::isOdd, ::length)
            val strings = listOf("a", "ab", "abc")
        
            println(strings.filter(oddLength))
        ```

    - 属性类型属于`KProperty<>` / `KMutableProperty<>`  

        ```kotlin
        val x = 1
        var y = 1
        fun main() {
            println(::x.get()) // ::x 获取到属性对象
            println(::x.name) 
            ::y.set(2)
        }
        ```

        ```kotlin
        // 当方法只有一个泛型参数的时候，可以直接用属性引用 
        fun main() {
            val strs = listOf("a", "bc", "def")
            println(strs.map(String::length))
        }
        ```

        ```kotlin
        // 对象属性或 拓展属性，需要传参代替接收器  
        class A(val p: Int)
        val prop = A::p
        println(prop.get(A(1)))
        
        val String.lastChar: Char
            get() = this[length - 1]
        fun main() {
            println(String::lastChar.get("abc"))
        }
        ```

    - 构造器类型，和方法属于一类

        ```kotlin
        // 方法参数和构造器的相同时，可以简写
        class Foo
        fun function(factory: () -> Foo) {
            val x: Foo = factory()
        }
        function(::Foo)
        ```

        

    

