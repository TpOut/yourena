View的常用回调：

onAttachedToWindow(),（简单理解成生命周期的开始，一般作变量的初始化）

onVisibilityChanged(), （需要按实际情况使用）

onDetachedFromWindow() （简单理解成生命周期的结束，一般作变量的释放）

onWindowFocusChanged() （能保证view 初始化完毕，但是会频繁被调用）



自定义View分为两种：

- 继承现有ViewGroup，添加子组件，实现组合控件，按需添加自定义属性、业务等简化重复开发。
- 继承现有View(Group)，修改绘制流程（包括measure, layout, draw），按需添加自定义属性实现需求。



对于组合控件，看官方文档基本够用了。

对于绘制流程，通过ViewRoot 的performTraversals 方法开始，经历measure, layout, draw。

具体可以从[viewRoot.perfromTraversals](./viewRoot.performTraversals.md)开始看，本文大致抽取如下：

viewRoot.performTraversals

|

viewRoot.performMeasure-->viewGroup.measure-->viewGroup.onMeasure

​     --- childView.measure --> childView.onMeasure

|

viewRoot.performLayout-->viewGroup.layout-->viewGroup.onLayout 

​    --- childView.layout --> childView.onLayout

|

viewRoot.performDraw-->viewGroup.draw-->viewGroup.dispatchDraw 

​    --- childView.draw --> childView.dispatchDraw



#### MeasureSpec

View的静态内部类，保存了模式SpecMode 和大小SpecSize   

其中模式包括UNSPECIFIED，EXACTLY，AT_MOST

(unspecified 的应用场景可以看一下ScrollView )

#### measure

View 的宽高测量。

测量过程使用MeasureSpec 来作为测量数据的记录类：

系统会在父容器的约束下，将view.LayoutParams 转换成对应的measureSpec。其中父容器的约束，

- 在DecorView 是窗口的尺寸

    viewRoot.measureHierarchy 方法中getRootMeasureSpec 部分可以看到具体逻辑

- 在View 是ViewGroup 的MeasureSpec

    在ViewGroup 的measureChildWithMargins 里可以看到具体逻辑

view 再根据这个转化后的measureSpec 设置宽高



结论有：

- 直接继承View 的自定义控件需要重写OnMeasure 方法，并处理wrap_content 时的大小测量逻辑

    ```java
    //示例代码
    protected void onMeasure(...){
        ...
        setMeasuredDimension(
            widthSpecMode == AT_MOST ? selfDefineSize : widthSpecSize,
            heightSpecMode == AT_MOST ? selfDefineSize : heightSpecSize
        );
    }
    ```

    

measure之后，可以getMeasuredWidth 和getMeasuredHeight 

有些时候多次绘制 ，值可能会变化，因此在layout 时获取的值才是最有保证的

activity 的生命周期和measure 方法同时异步执行，所以没法保证resume 时获取宽高成功，解决方案有：

- 在onStart 的时候，post 一下执行方法。

- 在onStart 的时候，view.getViewTreeObserver().addOnGlobalLayoutListener...

- 如果view 不是match_parent，可以手动触发measure 方法

    ```java
    //示例代码
    //假设具体的宽高写死100
    int widthMeasureSpec = MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY);
    ...
    view.measure(widthMeasureSpec, ...);
    //假设是content_wrap
    int widthMeasureSpec = MeasureSpec.makeMeasureSpec((1 << 30) - 1, MeasureSpec.EXACTLY);
    ...
    view.measure(widthMeasureSpec, ...);
    ```

#### layout

View 的位置确定。

确认了View 的四个顶点坐标，可以getTop, getBottom, getLeft, getRight, getWidth 和getHeight

setFrame和onlayout -- layout 不断调用

>getMeasuredWidth和getWidth赋值的时间不同，值在默认情况（即正常的layout）下相同。
>
>当然你可以修改layout 方法参数来修改width，但没啥意义

#### draw

View 的内容绘制。

顺序依次是：

- 绘制背景background.draw,
- 绘制自己onDraw,
- 绘制子视图dispatchDraw，
- 绘制装饰onDrawScrollBars  



>setWillNotDraw。在有些ViewGroup中被置为true,自己处理的时候注意  
