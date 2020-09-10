表达式用于属性时，默认是找对应的set 方法，但是有很多时候属性的set 方法不是单纯的在属性名字前面加一个set，此时就需要自定义Binding Adapter  

> Android 框架的属性，需要添加Adapter 的大部分已经处理过了

- 如果整体逻辑都有不同，使用`BindingAdapter`  

    adapter 功能比较强大，可以添加多个属性参数，并设置是否全部变更时调用

    甚至可以将属性的旧值和新值一起作为参数  

    通过这个方式，当你自定义view 的时候，添加一个自定义属性，并配合adapter 实现自定义逻辑  

    甚至view 本身没有的属性，都可以被定义。比如android:onLayoutChange，<font color=red>不过你会发现没法传递参数，impl 里就没写，由此可以推断只有实现了的属性才能定义</font>

    >所以binding 只是单纯的对布局关联方法，不校验有效性

- 如果只是方法名称的不同，使用`BindingMethod` ，应该是简化版的Adapter  

- 如果方法类型不同，比如`background` 可以使用color，在必要时用`BindingConversion` 对类型进行转换。  

    > 试了半天只能用java 写public, static 方法才行
    >
    > 然后估计方法名无所谓，入参和返回对象是匹配的依据





 

