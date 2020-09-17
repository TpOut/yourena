1、事件来不及处理的anr

**Input dispatching timed out (Waiting to send key event because the focused window has not finished processing all of the input events that were previously delivered to it. Outbound queue length: 0. Wait queue length: 30.)**



**Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago. Wait queue length: 36. Wait queue head age: 5506.7ms.), VisibleToUser**



看了下堆栈的相同点都是项目里，列表滑动时的release 操作。里面会先调用 mediaPlayer.reset 再调用mediaPlayer.release。而也有一些地方没有调用reset，就没有anr 日志。

同时大部分的机型都是2G 的ram ，少量6~8G ram 的手机次数极少。

理论上也没啥必要reset，所以就去掉看一下