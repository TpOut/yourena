

在ActivityThread 中，Activity 对象被创建之后，会把DecorView 添加在Window 中

同时创建ViewRootImpl , 并将ViewRootImpl 和DecorView 关联：

```java
root = new ViewRootImpl(view.getContext(), display);
root.setView(view, wparams, panelParentView);
```

>setContentView() 的名字由来是因为DecorView 作为顶级View(FrameLayout), 一般包含一个LinearLayout,分为标题和内容, 而内容就是content, 对应布局id 是android.R.id.content

