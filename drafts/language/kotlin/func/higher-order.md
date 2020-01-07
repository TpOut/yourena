kotlin 的方法是first-class( 参看wiki)

[functional programming idiom `fold`](https://en.wikipedia.org/wiki/Fold_(higher-order_function))



方法类型

- 基本格式

`val funVar : () -> Unit = ...`

- 附加接收器类型

`A.(B) -> C`

- Suspending fuctions

需要在前面两者之前加`suspend` 关键字

