View 的位置参数：元坐标左上角

MotionEvent：ACTION_DOWN, MOVE, UP, CANCEL 等

TouchSlop：最短距离，`ViewConfiguration.get(context).getScaledTouchSlop()`

VelocityTracker：

```java
VelocityTracker velocityTracker = VelocityTracker.obtain();
velocityTracker.addMovement(event);
velocityTracker.computeCurrentVelocity(1000); //先计算1000ms 内的值，再获取
int xVelocity = (int) velocityTracker.getXVelocity();
int yVelocity = ...
  
velocityTracker.clear();
velocityTracker.recycle();
```

GestureDetector：

```java
GestureDetector mGestureDetector = new GestureDetector(this, simpleOnGestureListener);
//长按屏幕后无法拖动的解决
mGestureDetector.setIsLongpressEnabled(false);

this.onTouchEvent{
  boolean consume = mGestureDetactor.onTouchEvent(event);
  return consume;
}
```

建议滑动自己实现？双击则用GestureDetector.

Scroller：

```java
Scroller scroller = new Scroller(context);
void smoothScrollTo(int destX, int destY){
  int scrollX = getScrollX();
  int delta = destX - scroolX;
  mScroll.startScroll(scroollX, 0, delta, 0 , 1000);
  invalidate();
}
@Override
void computeScroll(){
  if(mScroller.computeScrollOffset()){
    scrollTo(mScroller.getCurrX(),mScroller.getCurrY();
    postInvalidate();
  }
}
```