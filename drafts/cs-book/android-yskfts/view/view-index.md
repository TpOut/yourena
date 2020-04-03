移动相关

scrollTo / scrollBy ：onCreate 之后生效（mScrollX= x），移动内容（不包括背景，包括前景）

view 自身属性（平移，translate）：

view 布局（layoutParams）：



**事件分发：**

view 中dispatchTouchEvent，

先预处理，如ACTION_DOWN 停止嵌套滑动。

然后调用onTouchListener 的onTouch ,如果没消费，再调用onTouchEvent 看是否消费。

如果都没有消费，则恢复嵌套滑动，返回消费结果。



viewGroup 中dispatchTouchEvent,

ACTION_DOWN 或者没有触摸目标（mFirstTouchTarget ）的时候，先判断是否disallowIntercept，如果可以拦截，调用onInterceptTouchEvent

如果没有touchTarget，遍历查找child，dispatchTransformedTouchEvent -> child.dispatchTouchEvent , 如果分发成功，会设置touchTarget，后续的ACTION 直接处理；如果没有分发成功，再次 dispatchTransformedTouchEvent -> super.dispatchTouchEvent（即父容器的view实现，去调用onTouchListener, onTouchEvent）



window..

decor.dispatchTouchEvent  // decor 就是viewGroup 了



Activity 中dispatchTouchEvent

getWindow**().**superDispatchTouchEvent，如果没消费，自己touchEvent



整体流程在 Activity -> Window -> View 中，



**滑动冲突**

水平竖直滑动的距离、速度值都可以判断

父容器拦截

```java
interceptTouchEvent(){
    ACTION_DOWN:
      false; 
    ACTION_MOVE:
      if(){true}
    default:
      false
}
```

子view拦截

```java
parent.interceptTouchEvent(){
    ACTION_DOWN:
      false; 
    ACTION_MOVE:
      true
    default:
      true;
}

dispatchTouchEvent(){
    ACTION_DOWN:
      parent.requestDisallowInterceptTouchEvent(true);
    ACTION_MOVE:
      if(){parent.requestDisallowInterceptTouchEvent(false)};
  
  super.dispatch
}
```



