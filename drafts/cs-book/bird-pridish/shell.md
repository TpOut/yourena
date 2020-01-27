注释的指导风格：

- 功能内容
- 版本信息
- 作者联络
- 版权声明
- 建立日期
- 修改日志



执行方式，source、sh、./

除了source，其他的是开启子bash 来执行脚本



查询语法问题， `sh -n`

打印脚本，再打印脚本输出，`sh -v`

按执行过程打印脚本和输出，`sh -x`



`test` 查看一个文件是否存在，且区分文件类型和权限；比较两个文件、数字、文本；多重判断

```shell
[ "$VAR" == "$VAR2" -o "$VAR" == "$VAR3" ]  
# 因为中括号在很多地方包括通配符和正则语法，所以各个地方都有空格 
```



命令行参数对应为 `${0}, ${1}, ...`

`$#` 参数数量，`$@` 参数内容

`shift` 左移变量



```shell
if [ condition ] || [ condition ]; then
    expression
elif [ condition ]; then
    expression
else 
    expression
fi

case $VAR in
    "content")
        expression
        ;;
    "content")
        expression
        ;;
    *) # 其他所有情况
        expression
        ;;
esac
```



`netstat` 网络状态

```shell
function f(){
    expression ${1}  # 这里的是指方法参数，而不是shell 的
}

f params1  # 调用
```

```shell
while [ condition ]
do
    expression
done
```

```shell
until [ condition ]
do
    expression
done
```

```shell
for var in con1 con2 con3
do 
    expression
done

for var in $(seq 1 100)  # sequence, 等同{1..100}

for(( 初始值; 限制值; 执行步阶))
do
    expression
done 
```

