可以替代 const 的作用，定义时支持到 long long

不要使用 ++ 

赋值只能使用枚举定义时的名称，而不是数值，如果要用数值，请用强转

枚举之间相加赋值也不要使用



枚举的合法范围，见原文4.6.2  

switch 中，enum 被提升为整型



#### 作用域枚举

在相同作用域中，传统的枚举会出现重名问题：

```c++
enum egg{Small};
enum t_shirtP{Small};
//添加类作用域，c++11
enum class egg{Small};
enum class t_shirt{Small};
enum struct egg{Small}; //关键字替代方案

egg::Small; //这样就不冲突了

//作用域枚举不会自动被提升为整型，需要显式转化
int a = int(Small); 
//常规枚举底层对应的整型类型取决于实现；而作用域枚举的底层实现默认为int，但也可以指定
enmu class : short pizza{Small};
```
