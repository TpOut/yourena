`aosp/src/frameworks/base/core/java/android/view/ViewRootImpl.java` 

当前从绘制流程看到这里，所以以performTraversals 为起点。

往调用处看，会看到scheduleTraversals 添加runnable， unscheduleTraversals 移除runnable

