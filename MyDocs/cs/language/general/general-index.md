一段程序的执行，实际是将一份既定的规则让机器运行 

应用开发接触的层面就是，编写一段“代码文本”

这段文本最后都是被对应的执行器执行，

```c
既定规则是从入口执行： int main(){}
或者文本顺序读取执行： 如 shell
或者文本乱序跳转执行： 如 makefile
```

为了区分文本的段落，需要断句

如java 用“;” 来结束语句，这样可以随意回车 
如kotlin 换行即结束语句，一般需要一个`\`来表示另一行的语句是连续的

那机器怎么知道文件读取完了呢  

则是文件自身带有一个文件尾的标志位，很多输入通过ctrl+z, ctrl+d, enter 等来中止，实际就是 模拟文件尾



当然文本文档的内容语法也是各种语言不同，包含一些基础概念：

- [名词](./terms.md)
- [代码风格](./style.md)
- [概念](./concept.md)
- [类型](./type.md)
- [操作符]()
- [函数](./function.md)
- [控制流]()
- [构建系统](./build-system/build-system-index.md)
- [数据结构](./struct.md)
- [算法](./algorithm.md)
- [图例](./UML.md)

当项目变得比较大型之后，需要处理命名冲突问题，引入了[分包]()的概念



---
how to design program：https://htdp.org/2018-01-06/Book/index.html
