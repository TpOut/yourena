语言基本

​    魔法方法：魔术方法： [https://rszalski.github.io/magicmethods/](https://rszalski.github.io/magicmethods/)  

库原理  

​    

交互式输入时，最后被打印的表达式会被赋值给“ \_ ”变量，可以用来方便记录  

```python
>>> price = 100
>>> price * 2
200
>>> price + _
300
```



脚本逻辑：

sys.argv\[0\]的赋值规则：  
脚本名和添加的参数会被一个string列表存储，即sys模块的argv变量，要获取可以导入sys模块。  
argv的长度起码为1,但是如果没有脚本和参数，argv\[0\]是空字符串。  
如果脚本名是‘-’，argv\[0\]就被设置成‘-’；如果使用‘-c’命令，argv\[0\]就被设置成‘-c’；如果使用‘-m’,那么被设置成模块的全名。

**指定编码：**

```python
# -*- coding: [encoding] -*-
```

```python
#!/usr/bin/env python3.5
# 让类unix 自动执行
```





速览：

- duck type

- 表达式：

  - 
  
- [操作符]()  

- [类型]()  

- [控制流]()  

- [文档]() 参看官网控制流章节最后    

- 类   

  > 任何形式为 `__spam` 的标识符（至少带有两个前缀下划线，至多一个后缀下划线）的文本将被替换为 `_classname__spam`，其中 `classname` 为去除了前缀下划线的当前类名称。

- 



解释器拓展

C 相关







学习100 天：[https://github.com/jackfrued/Python-100-Days](https://github.com/jackfrued/Python-100-Days) [https://realpython.com/python-random/](https://realpython.com/python-random/)    



数值类型 [https://docs.python.org/3/library/stdtypes.html\#typesnumeric](https://docs.python.org/3/library/stdtypes.html#typesnumeric)  
编码列表 [https://docs.python.org/3/library/codecs.html\#module-codecs](https://docs.python.org/3/library/codecs.html#module-codecs)

数值计算模块：[https://scipy.org/]

转化python 命令为图形工具：https://github.com/chriskiehl/Gooey