---
description: >-
  主要内容来自mdn, https://developer.mozilla.org/zh-CN/docs/Learn
  ;目前主要第一次看的笔记，比较凌乱。第二次html看到“多媒体与嵌入大章 -- 视频和音频内容（不包括）”，css单独看了“css排版概述”
---

# overview

### 整体理解

不管历史究竟如何，我觉得这么理解挺好的：

最开始为了显示一个文档，规定了一系列文档书写的规矩，即HTML；  
后来发现所有东西都写一个文档里，太乱了，思考了一下，可以把样式单独抽取出来，即CSS\(Cascading Style Sheets\)；  
再后来发现这样太死了，没有响应交互，就出来个东西去操控HTML和CSS，即JavaScript。

### 通用

标签：tag  
属性：attribute、property  
变量：variable  
选择器：selector  
类：class

#### 计划三部曲

A project never starts with the technical side.  
--from 《[https://developer.mozilla.org/en-US/docs/Learn/Common\_questions/Thinking\_before\_coding》](https://developer.mozilla.org/en-US/docs/Learn/Common_questions/Thinking_before_coding》)

延伸阅读：Wiki--Project Ideation, Project planning, Project Management

**确立目标**  
列出你想实现的功能模块，然后按重要性排序  
**整理思路**  
看下你想实现的功能模块，真的需要独立构建一个网站来实现吗，是否可以利用现有的？切不可本末倒置  
**细分功能** 将之前的功能模块细分成更小的一些功能，同样可以考虑利用现有的服务。

### web服务器理解

静态和动态的区分。

**web服务器框架**  
PHP: symfony  
Python: Django  
Node.js: express  
ruby: ruby on rails  
java: grails

Python: Django、Flask框架  
JavaScript\(Node.js\): Express  
PHP： MAMP,AMPPS,LAMP

含有免费服务：Neocities ， Blogspot ，和 Wordpress

#### 安全问题

跨站脚本\(XSS\)和跨站点请求伪造\(CSRF\) SQL注入  
HTTP数据头注入和电子邮件注入

你如何应对这些威胁呢?这是一个远远超出本指南的主题，但是有一些规则需要牢记。最重要的原则是:永远不要相信你的用户，包括你自己；即使是一个值得信赖的用户也可能被劫持。

所有到达服务器的数据都必须经过检查和消毒。总是这样。没有例外。

有潜在危险的字符转义。应该如何谨慎使用的特定字符取决于所使用的数据的上下文和所使用的服务器平台，但是所有的服务器端语言都有相应的功能。 限制输入的数据量，只允许有必要的数据。 沙箱上传文件\(将它们存储在不同的服务器上，只允许通过不同的子域访问文件，或者通过完全不同的域名访问文件更好\)。 如果你遵循这三条规则，你应该避免很多问题，但是如果你想要得到一个有能力的第三方执行的安全检查，这是一个好主意。不要以为你已经看到了所有可能的问题。

