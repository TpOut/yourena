- 可以将bean 直接在xml 中使用，可以使用简单的**表达式**  

    - 表达式整体上偏向脚本语言。特别支持资源索引时，格式化的字符串使用方法参数的形式。

    - 不支持泛型，需要在表达式中明确指出类型

    - 支持事件定义，如`onClick`。

        可以定义方法，在数据绑定的时候生成方法，需要固定格式

        > 和xml 本身支持的activity 方法定义的两点区别：
        >
        > 一个是编译期间，一个是可以指定非activity 类  

        也可以定义lambda，在事件产生的时候生成（执行表达式），这样可以直接做不可见时无事件的逻辑

    - 支持view 获取另一个view 的属性（如textView 获取editText 的值，减少监听代码的编写）

    - 变量：多配置布局需要注意，变量会被合并；为了方便，添加了context 变量；变量可以传递到include 布局

- 可以对数据进行监听，使变化马上映射到ui 的更新上  

    > 如初始的Observable 变量 到现在的LiveData 变量

    > 自定义通知时间时，可以使用BaseObservable 继承，如果不能继承，就仿照其实现写一个

- 

