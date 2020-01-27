特殊符号

```shell
[:alnum:] # 等同0-9，A-Z, a-z
[:alpha:] # 等同A-Z, a-z
[:blank:] # 空格和tab
[:cntrl:] # 控制按键，包括CR，LF，Tab，Del...
[:digit:] # 0-9
[:graph:] # 除了空格和tab
[:lower:] # a-z
[:upper:] # A-Z
[:print:] # 任何可被打印的字符
[:punct:] # 标点
[:space:] # 空白符
[:xdigit:] # 16位数字，0-9，A-F, a-f
```

```shell
grep `[^]` # 在[] 里面表示非
grep `^` # 直接用则表示行首

grep `$` # 表示行尾
```



- `.` 表示一个任意字符
- `*` 表示重复前一个字符，0~无穷次

```shell
grep `o\{2,5\}` # 限定范围，2至5个o
```



#### 拓展语法

```shell
grep -E
egrep  
```

```shell
+  # 重复一个或一个以上 前面的字符
?  # 重复0个或一个 前面的字符
|  # 或者，`|`
()  # 组合单元，`(|)`
()+  # 重复一个或以上 前面的组合单元
```

