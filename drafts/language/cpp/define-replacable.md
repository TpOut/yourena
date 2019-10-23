# note

#### 预编译

预编译指令 ： \`\#pragma\` 可以控制特定的编译指令  

#### Const本身不同

c99允许const作为数组长度，但是数组必须是变量数组；c++标准则没有  

> c中const变量是存储在某个地方，而编译器无法在编译的时候获取这个地方。所以无法作为数组长度

c++中const 是内部链接作用范围，c中则是外部链接范围  

既前者的 const = 后者的 static const

#### Const和\#define比较

前者可以指定类型、支持复合类型（\[\]）、拥有作用域。

#### inline和\#define  

\`\#define Cube\(x\) x\*x\*x \`

尽量使用inline代替上面这种容易出现意外的define方式  

#### 模板和\#define

\#define的主要作用，除去const和inline，还可以被模板替代。

