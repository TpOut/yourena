# 疑惑

请注意传递给 `exec()` 或 `eval()` 的代码不会将发起调用类的类名视作当前类；这类似于 `global` 语句的效果，因此这种效果仅限于同时经过字节码编译的代码。 同样的限制也适用于 `getattr()`, `setattr()` 和 `delattr()`，以及对于 `__dict__` 的直接引用。



#### 文档

读取空行之后缩进格数作为标准的理由是：  
\(We can’t use the first line since it is generally adjacent to the string’s opening quotes so its indentation is not apparent in the string literal.  
不太懂。从编码上看很明确吧？

#### module

当module更改的时候，可以使用improtlib.reload\(modulename\)来重新加载，但是这样子会加载整一个module，如果之前只是加载了这个module的一个方法，并且只是修改了这个方法，应该怎么重新加载更好？



#### 语句理解

--[https://docs.python.org/3/tutorial/modules.html](https://docs.python.org/3/tutorial/modules.html)  
the collection of variables that you have access to in a script executed at the top level and in calculator mode

脚本被拉进来之后，其变量会被执行到top levl以及处于calculator mode  
这里的calculator mode不是很理解

