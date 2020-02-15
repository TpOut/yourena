当前内容来自《鸟哥私房菜》

https://www.gnu.org/software/make/manual/make.html



使用`make` 命令依据makefile 的内容执行各种操作

而很多tarball 根据configure 脚本，配置对应参数，来生成makefile



makefile 语法：

```shell
LIBS = -lm  # 变量和值之间用等号；等号两边可以填充空格，但不能有冒号
OBJS = main.o haha.o  # 变量左侧不能有tab，习惯用大写字母
main: ${OBJS}  # main 是target 名称,用于命令行索引；$() 也可以引用变量
	gcc -o main ${OBJS}  # 命令行的第一个字符要是`<tab>`
	# 这里的main 可以用$@ 替代，后者表示当前的target
clean:
	rm -f main ${OBJS}
```

执行shell 的环境变量可以被shell 读取

```shell
CFLAGS="-Wall" make clean main
# make 会先执行target: clean 处的代码，再执行main
```

变量优先级：make 后面加的 > makefile 指定的 > shell 环境



**函数库**

静态.a 函数库，可以独立执行，会整合进执行程序

动态.so 函数库，以指针的的形式整合进执行程序



`idconfig` /etc/ld.so.conf

`ldd` 查看某个可执行的二进制文件含有的动态函数库

