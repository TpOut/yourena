

#### 实现原理

range\(\)的省空间原理：  
It is an object which returns the successive items of the desired sequence when you iterate over it, but it doesn’t really make the list, thus saving space.

### 语法

#### 纯概念

列表和字符串都属于sequence  
**字符串** print\(\)会对传入的字符串的换行标识等进行识别；  
没有character类型，会被认为是长度为1的字符串  
字符串不可改变  
[https://docs.python.org/3/library/stdtypes.html\#textseq](https://docs.python.org/3/library/stdtypes.html#textseq) **tuple** tuple一般是包含不可变的、不一样的元素，他们会以拆包的方式或者索引的方式被引用；  
列表一般包含同类的可变的元素，是用遍历的方式引用 **列表** 支持 + 也支持索引，返回的是列表的浅拷贝  
列表元素可改变  
使用\[\]推导式来生成列表\(list\)的话，一个\[\]对应一个list.append的需求 **dictionary** dictionary用key来索引。任何不可变的类型都可以被用作key，比如string和number  
tuple只包含不可变类型的时候也可以  
支持推导式 **set** 创建空set的时候，因为dictionary也是大括号，使用set\(\)函数，否则用大括号包含即可。。  
支持类似列表的推导式  
**循环语句** while  
if elif else  
for in; for i in range\(len\(a\)\) = enumerate\(\)  
for else（从属for的else，是在for中没有break时执行）

pass\(表示确定跳过\) **条件** while if in ;not in is ;is not a &lt; b == c

not and or \(优先级从左到右递减，也是短路判断\)

#### 示例

```python
#除法
1/2 = 0.5  #永远返回浮点
1//2 = 0  #取整
#序列赋值
#Note that multiple assignment is really just a combination of tuple packing and sequence unpacking.
>>> t  = 12, 34, 'hello'  
>>> x, y, z = t  
>>> x  
12
#字符串
3 * 'a' = 'aaa'  
('a''b') = 'ab'  
#索引
>>> word = 'Python'
word[0],word[-1]
word[0:2],word[0:],word[:-2]
error: word[6]; handled: word[2:6]

 +---+---+---+---+---+---+
 | P | y | t | h | o | n |
 +---+---+---+---+---+---+
 0   1   2   3   4   5   6
-6  -5  -4  -3  -2  -1
#tuple
t = 12345,3445,'gekko'  #tuple初始化如果不包含元素，用括号构建；如果只包含一个元素，需要跟一个逗号
#序列
for i, v in enumerate(['tic', 'tac', 'toe']):  #同类型的序列也可以被比较，字典序。
#列表
letters = ['a','b','c']; letters[2:3] = []
#字典
tel = {'jack': 4098, 'j': 4139}  
'jack' in tel
for k, v in tel.items():
#set
basket = { 'apple', 'adad'}
a = set('asdfsdf')

#技法
print(b, end=',')  #print如想不换行，则可以把end符号替换掉  
[(x, y) for x in [1,2,3] for y in [3,1,4] if x != y]  #推导式  

#可以把字符串用来作为条件，但是返回值不变:  
>>> str1, str2, str3 = '', 't', 'h'  
>>> no_null_str = str1 or str2 or str3  
>>> no_null_str  
't'  

#变量在循环之后还存在
>>> squares = []  
>>> for x in range(10):  
...     squares.append(x**2)  
...
>>> squares  
[0, 1, 4, 9, 16, 25, 36, 49, 64, 81]  
>>> x  
9
```

### 函数

```python
def funcName(param,p1=defaultValue,p2=None,*p3，**p4,...):
    """optional function description"""
    if p2 is None:
        p2 = []
    return result
```

带有默认值的参数可以在调用时不赋值 在知道param名字时，调用也可以param=value的形式，甚至在没有以value调用时，可以打乱顺序。 以value调用的参数需要在param=value形式调用的参数前面。

param1只初始化默认值一次，多次调用函数可能造成param1数据变化 funcName展示函数实例,funcName\(value\)调用函数，没有返回的函数默认返回None  
newFuncName = funcName ; newFuncName = funcName\(value\);  
print\(fib\(0\)\)，查看函数默认返回值

_表示数组: "1","2","3"；\*\*表示map: a="1",b="2"；数组必须在map之前调用 在调用时，多个value可以被理解成_param ；同样的，_value可以被解析成多个param （\*_类似）

#### 文档

```python
def docFunc():
    """optional function description
    #多行文档的时候，需要第二行空出来   
    Thirdline
    >>> print(average([20, 30, 70]))
    40.0
    """

print(docFunc.__doc__)
```

#### annotations

see PEP 3107 ; PEP 484

```python
def f(ham: str, eggs: str = 'egg') -> str:

-> str 表示函数的返回值类型是str

print(docFunc.__annotations__)
```

#### lambda语法

lambda param : statement  
这样的格式表示一个（匿名）函数，可以被传递

```python
>>> pairs = [(1, 'one'), (2, 'two'), (3, 'three'), (4, 'four')]
>>> pairs.sort(key=lambda pair: pair[1])
>>> pairs
[(4, 'four'), (1, 'one'), (3, 'three'), (2, 'two')]
```

### 模块

解释器一般性只加载一次module，如果修改了module，重启解释器或者重新加载： import importlib; importlib.reload\(modulename\)

内置模块依赖平台，比如sys是全平台通用，winreg只有Windows平台有。

```python
#test.py
import test
import test as newName
from test import variab
from fibo import * # 不包含 _开头的，一般不用
```

dir\(moduleName\) 会展示模块里定义的所有names，names包含variables, modules, functions，etc；但是不包括内置的function和variable，如需显示，需要导入builtins模块  
如果不传入moduleName会取最近定义的模块

#### 脚本形式

```python
python test.py <arguments>
```

如果以脚本形式执行module,那么**name**变量会被赋值成"**main**"。这个条件，可以写在测试脚本作为判断语句，那么如果误加载了module，它就不会被执行。  
也正因为name都是main，所以需要在导入的时候写绝对路径。

```python
if __name__ == "__main__":
    import sys
    fib(int(sys.argv[1]))
```

#### 搜索路径

当一个module被import，解释器先找Build-in，再找sys.path中的字典里，

> sys.path从这里初始化：1、包含执行脚本的目录，没脚本时是当前目录。2、环境变量的PYTHONPATH。3、默认安装依赖；如需修改，直接对sys.path进行操作
>
> Note: On file systems which support symlinks, the directory containing the input script is calculated after the symlink is followed. In other words the directory containing the symlink is not added to the module search path.

#### 编译文件

**pycache**/module.version.pyc

不检查缓存的情况：1、以脚本形式。2、二进制文件，非module 命令优化缓存： -O 移除assert 语句 -OO 移除assert和**doc**

### 包

在包目录配置**init**.py，该文件用来防止目录名被命名成通用名字，如string。 同时，使用from someModul import \* 加载模块的时候，会读取**init**中的**all**变量并加载并递归每一个子包的**init**（而不是递归遍历文件）。  
介于上面的原因，**init**是会首先加载，所以也可以在里面做一些初始化操作，如 import sys

记住，虽然允许使用import \*，但是这是糟糕的操作，应该用啥导啥  
import可以使用相对路径（非脚本执行），如 from . import echo -- 当前目录； from .. --上级目录

packages还有**path**属性。

### 输入输出

#### 格式化

print\(\)  
write\(\) sys.stdout

以f或者F开头，接一个前引号'或者三引号包裹的字符串，字符串中用{}包裹的内容会被替换

```python
testVariable = 1
f'Results of testVariable is {testVariable}'
```

str\(\) 方法，將字符串转化成易读的格式；repr\(\)方法，将字符串转换成解释器易解析格式。

```python
#限制宽度，不足填充空白
print(f'{name : 10} ==> {phone:11d})   
#!r表示以repr()的形式,!s对应str(),!a对应ascii()  
print(f'simple encoding {animal !r}')
```

类似sprintf\(\)风格的格式化：

```python
import math
print('some %5.3f' % math.pi)
```

**string.format\(\)**

string模块提供了类似$x格式的占位符，但是支持的格式较少

```python
'total : {:-9}, right percent {:2,2%} '.format(totalVariable,percentVariable)

'This {food} is {adjective}.'.format(food='spam', adjective='absolutely horrible')
```

如果字符串中的{}佔位符不指定名字，則按位置顺序匹配，否则以Key匹配，可以混搭

如果变量太多，建议以dic存储，可以传递：

```python
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 8637678}
>>> print('Jack: {0[Jack]:d}; Sjoerd: {0[Sjoerd]:d}; '
...       'Dcab: {0[Dcab]:d}'.format(table))    

print('Jack: {Jack:d}; Sjoerd: {Sjoerd:d}; Dcab: {Dcab:d}'.format(**table))
```

str.rjust\(\),str.ljust\(\),str.center\(\) 返回新字符串，不会对原字符进行裁剪  
如需裁剪，使用 s.ljust\(n\)\[:n\]  
str.zfill\(\) 数字左侧填充0

#### 读写文件

```python
f = open('tempFile','rb+')
```

r = only read\(默认\), w = only write \(会擦出同名文件\)，a = appending, r+ = read and write  
b = binary\(非文本通用\) ，不写则为文本模式

默认情况下，读取会把\r\n（win）转化成\n\(unix\)

```python
#使用with会自动close流
with open('tempFile') as f:
    read_data = f.read()
```

**file方法**

f.read\(size\) 如果读到底端，会获取空字符串  
f.readline\(\) 字符串以\n结尾，除了底端最后一句

```python
#逐行操作  
for line in f:
    print(line, end='')
```

f.readlines\(\) / list\(f\) 获取文件的所有行  
f.write\(\) 写入文件，文本模式下需要转换成字符串，二进制一样。  
f.tell\(\) 二进制返回当前byte位置；文本的话类似，但是包含一些不可见字符，如\n  
f.seek\(offset, from\_what\) from\_what有三种模式，0表示开头（默认），1表示当前，2表示结尾。

**保存为json**

```python
import json
json.dumps([1,'simple'])

f = open('tempFile', w)
json.dump('string', f)
tempStr = json.load(f)
```

### errors/exceptions

errors一般指语法错误

```python
whilte true:
    try:
        x = int(input("Please enter a number : "))
        break
    except (ValueError, TypeError):
        print("Oops! Try again...")
    except OSError as err:
        print(type(err))
        print(err.args)  #错误参数存在args中
        print(err)  #err.__str__()等同于args，方便输出
    except:  #最后一个分支可以重新抛出异常
        print('reraise')
        raise
    else:  #如果没有抛出异常  
        print('yes')
    finally:  #不管如何一定会执行
```

继承异常同时抛出：

```python
class B(Exception):
    pass

class C(B):
    pass

for cls in [B, C, D]:
    try:
        raise cls()
    except C:
        print("C")
    except B:
        print("B")
```

如果先抛出B再抛出C，则打印结果是B,B

### class

麻蛋啊。装逼作者，写了Modula-3，pascal，smalltalk，C++四种语言作为对比。。。

混合了C++和Modula-3的机制  
多继承，多态  
内置类型可以作为基类，内置操作符也可以被重定义

类可以在if的语句分支中，也可以在一个函数中定义

```python
class MyClass(BaseClass，BC2):
    """introduction docs"""
    i = 12345

    def __init__(self, arg1, arg2)
        self.arg1 = arg1

    def f(self):
        self.arg1 = i
        return 'hello myClass'

    def doToF(self):
        self.f()

d = MyClass(1,2)
```

MyClass.f 叫做函数对象， myClass.f 叫做方法对象。

myClass.f\(\) 等同于 MyClass.f\(myClass\)

这里的i类似于静态变量，所有实例共享；arg1则每个实例不同，可以myClass.arg1的方式调用

因为数据和方法可能重名，导致调用myClass.a\(\)的时候输出myClass.a的变量值，所以要注意命名习惯。

没法将数据变量设置成外部不可访问的，即私有变量是不存在的。但是有一个约定，使用一个下划线_开头的变量会被理解成私有的，非公开接口。  
而为了避免子类命名冲突，python提供了一种private机制：前面至少两个下划线，\_\_spam_，后面最多一个下划线。这样的命名会被替代为_classname**spam（只在类定义内有效），然后子类就不会继承了  
Notice that code passed to exec\(\) or eval\(\) does not consider the classname of the invoking class to be the current class; this is similar to the effect of the global statement, the effect of which is likewise restricted to code that is byte-compiled together. The same restriction applies to getattr\(\), setattr\(\) and delattr\(\), as well as when referencing** dict\__ directly.

#### 快速初始化

```python
class Employee:
    pass

john = Employee()  # 创建一个新的 employee 记录
# 给记录的各个字段赋值
john.name = 'John Doe'
```

调用父类方法：  
BaseClassName.methodname\(self,arguments\)  
如果在调用myClass.methodname的地方可以访问到BaseClassName，那么也可以直接调用。  
在类里面定义函数的时候，可以引用类外部的函数  
在多继承时，变量会在本地，然后并以深度优先，从左到右的方式在父类查找  
isinstance\(\),issubclass\(\)

**class**

### scope

这一段讲的真是烂。。。一大堆文字；介绍namespace用scope，介绍scope用namespace，循环介绍。。。

对象是一个object，但是可以有多个互相独立的（被scope分隔）的绑定name，类似别名。

namespace（下称为命名空间）是name到object的映射，  
如 built-in names ; global name in module ;local name in function 都有命名空间，对象的属性（属性是指.之后的name，例如modname.funcName，modname.viriableName）也会形成一个命名空间。  
最主要的是，这几个命名空间之间的name，一般情况下绝无关联。

绝无关联的例外：模块中name的引用就是属性的引用，在modname.funcName的情况下，会有一个关联模块属性和模块global name的直接映射，即他们共享了一个命名空间。

共享命名空间的例外：模块对象有一个秘密的只读属性叫做**dict**用来实现模块的命名空间;**dict**名称是一个属性,但不是一个全局name。显然,使用这种违反名称空间实现的抽象,并且应该局限于事后调试器之类的东西

built-in命名空间在解析器启动时创建，并不会被删除；模块的global命名空间在模块定义的时候被读取，在解析器退出前一直存在；方法命名空间随方法退出被遗忘。  
（build-in的names实际上也在模块中存货，作为buildins）

又绕到scope了，scope是命名空间被直接访问的文本区域。

* innermost scope, local name
* enclosing scope, non-local  non-global name
* next-to-last scope, module global name
* outermost scope, build-in name

如果一个name被定义为global，那么所有的引用和赋值会直接从对应的scope中找。如果在innermost scope的里面，要引用innermost scope外面的变量，需要使用nonlocal声明。

local space是指当前变量所处的space,如果变量在方法里，则local space 就是innermost space ; 在方法外，local space就是 module sapce。而类的定义，也会增加一套local scope对应的space

闭包函数，即嵌套scope，表示non-local non-global；为什么不会多层嵌套?因为闭包的情况只是把内层函数写在外层内部，还是需要调用语句，所以就算多层嵌套，而言是双层嵌套的递归形式。

global 声明被用在要指定某个特殊的变量要在全局作用域中存活且应该在这重新被绑定的情况下；nonlocal 声明则是用在指示某变量存在于某封闭的作用域且应该在这被重新绑定的情况下。

定义的顺序无所谓，因为是在运行时动态寻找名称的，但是语法正在朝静态编译发展（本地变量已经是静态的了），所以不要依赖动态策略。

### 迭代器

for会对定义了 **iter**\(self\),**next**\(\)方法的对象，进行迭代操作。其中**next**\(\)中抛出StopIteration来作为停止标志位。

```python
class Reverse:
    """从后向前的迭代器"""
    def __init__(self, data):
        self.data = data
        self.index = len(data)

    def __iter__(self):
        return self

    def __next__(self):
        if self.index == 0:
            raise StopIteration
        self.index = self.index - 1
        return self.data[self.index]
```

#### 迭代器生成器

```python
def reverse(data):
    for index in range(len(data)-1, -1, -1):
        yield data[index]
```

简化了自己写迭代器时要实现的iter和next方法，以及自动处理使用到的index,data变量，并根据条件自动抛出StopIteration

### 类库一览

```python
dir(moduleName)
help(moduleName)
```

```python
import os  
os.getcwd() #current working directory
os.chdir() #change cwd
os.system() #run commnd in system shell
```

文件和目录的管理

```python
import shutil   
shutil.copy()  
shutil.move()
```

glob语法

```python
import glob  
glob.glob()
```

args相关

```python
import sys  
print(sys.argv)   

getopt() #convention
argparse #module
```

标准输入输出

```python
sys.stderr.write(str) #stdin, stdout  
sys.exit()
```

正则

```python
import re
re.findall()  

str.replace()
```

网络

```python
from urllib.request import urlopen  

import smtplib #需要邮件服务器
```

压缩

```python
 zlib, gzip, bz2, lzma, zipfile and tarfile.
```

性能测量

```python
timeit profile pstats
```

doctest,unittest

### 虚环境

用于解决类库重复依赖冲突问题

#### 其他

**编码风格**：主要参看PEP8  
\(个人建议用ide如pycharm，或者带修正的编辑器\)  
缩进4空格 ; 单行79字符；函数和类的区域分割，用2空行；操作符空格；  
类名用CamelCase,函数名用 lower\_case; self作为方法的第一参数

