- [代码风格](./style.md)

- [概念语法](./concept-grammar/concept-grammar-index.md)

    - [类型](./type.md)
    - [函数](./function/function-index.md)

- [数据结构](./struct.md)

- [名词](./terms.md)

    

#### 文件尾

很多输入通过ctrl+z, ctrl+d, enter 等来中止，实际是 模拟文件尾


#### 入口

因为代码最后都是被对应的执行器执行，所以需要入口函数,一般叫main：

```c
int main(){

}
```

但是也有不需要的,如脚本语言，直接顺序执行就好  
说道顺序执行，顺便提一下断句，很多语言需要“;”来结束语句，这样可以随意回车  
而有些则是换行表示结束语句，一般需要一个`\`来表示语句是连续的



#### 分包

```java
// java/kotlin
package my.demo
import java.util.*;

... //code
```

```c++
//c++  
namespace cp{
    namespace ut{
        ... //code
    }
}
```



#### 文本变量\(有些叫 字面量\)

在文本中，用`$`关键字可以直接嵌入变量値  
甚至接受表达式

```text
"sum of $a"  
"${s1.replace("is", "was")}, but now is $a"
```



#### 方法

lambda的方法缩写情况下，参数可以直接用it 代表



#### 操作符

```C
^
&(作为条件为非短路) , &&(短路式)
|(作为条件为非短路), ||(短路式)
<< , >> , >>>
  
<<= , >>= , &= , ^= , |=
  
(type) -- 指强转
```



#### 其他

注释

c中条件是整形。

```c
tag:
    ...
    goto tag;
```

