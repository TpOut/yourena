不记得怎么找命令行规范的原始文档了。。

又因为同事说看不懂命令行文档的格式

所以写一个临时文档凑数，以git 的命令为例

相信看了本文之后，你不会再觉得命令提示密密麻麻很难受了。



#### 示例

```shell
git --help

#可以看到如下内容
usage: git [--version] [--help] [-C <path>] [-c <name>=<value>]
           [--exec-path[=<path>]] [--html-path] [--man-path] [--info-path]
           [-p | --paginate | -P | --no-pager] [--no-replace-objects] [--bare]
           [--git-dir=<path>] [--work-tree=<path>] [--namespace=<name>]
           <command> [<args>]
```



#### 语法糖

```shell
#命令格式
命令执行体 参数1 参数2 ...
```

命令执行体在这里就是 git

后面跟的参数是可选项 `[]`和必选项 `<>`，且

参数里面又可以使用键值对 `=` 与选项分割符 `| `

且前两个 `[]` 、`<>` 语法可以嵌套



#### 可选项

挑选参数进行说明

```shell
git [--version]
#表示下面两种可以
$ git
$ git --version
```

#### 嵌套可选项

挑选参数进行说明

```shell
git [--exec-path[=<path>]] 
# 表示就算有--exec-path 参数，path也是可选的
$ git --exec-path #valid
$ git --exec-path=pathValue #valid
```

#### 嵌套必选项

挑选参数进行说明

```shell
git [-C <path>]
# 表示如果有-C 参数，必须紧跟path
$ git -C #invalid
$ git -C pathValue #valid
```

```shell
git [<args>]
# 表示可选的内容来自args，args一般在说明下方可以看到
$ git clone ... #...表示省略，不再拓展
```

#### 键值对

挑选参数进行说明

```shell
git [-c <name>=<value>]
# 表示如果有-c 参数，必须紧跟键值对
$ git -c nameValue #invalid
$ git -c nameValue=valueValue #valid
```

#### 选项分隔符

挑选参数进行说明

```shell
git [-p | --paginate | -P | --no-pager]
# 表示可选的内容来自 分隔符左右的内容
$ git -p #valid
$ git --paginate #valid
$ git -P #valid
$ git --no-pager #valid
$ git -pp #invalid
```

