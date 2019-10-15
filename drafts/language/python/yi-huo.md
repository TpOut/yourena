# 疑惑

#### function

```python
>>> def parrot(voltage, state="a stiff") :  
...     print("--this is voltage :" , voltage)  
...     print("--this is state :", state)  
...  
>>> parrot(voltage = 1, "stiff")  
  File "<stdin>", line 1  
SyntaxError: positional argument follows keyword argument  
>>> parrot(voltage = 1, state = "stiff")  
--this is voltage : 1    
--this is state : stiff
```

这里的设计有点疑问，positional argument 完全可以取顺位，是因为没必要做这点完善吗？

#### 文档

读取空行之后缩进格数作为标准的理由是：  
\(We can’t use the first line since it is generally adjacent to the string’s opening quotes so its indentation is not apparent in the string literal.  
不太懂。从编码上看很明确吧？

#### module

当module更改的时候，可以使用improtlib.reload\(modulename\)来重新加载，但是这样子会加载整一个module，如果之前只是加载了这个module的一个方法，并且只是修改了这个方法，应该怎么重新加载更好？

#### package

当package中文件列表发生变化的时候，"**init**.py"需要被更新，那么肯定有对应的已经写好的函数或者方法？

```python
#这几句话的描述没有看明白　　
import sound.effects.echo
import sound.effects.surround
from sound.effects import *
```

#### file方法

这句话没看明白。。随便写了几个offset都是可以的。。

In text files \(those opened without a b in the mode string\), only seeks relative to the beginning of the file are allowed \(the exception being seeking to the very file end with seek\(0, 2\)\) and the only valid offset values are those returned from the f.tell\(\), or zero. Any other offset value produces undefined behaviour.

#### 语句理解

--[https://docs.python.org/3/tutorial/modules.html](https://docs.python.org/3/tutorial/modules.html)  
the collection of variables that you have access to in a script executed at the top level and in calculator mode

脚本被拉进来之后，其变量会被执行到top levl以及处于calculator mode  
这里的calculator mode不是很理解

