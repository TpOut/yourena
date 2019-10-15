# 语法

c的规范是不断演进的，所以需要明确编写代码和编译器所使用的规范是哪个，规范参看[c标准](../overview/c-biao-zhun.md)

从[helloworld.c](../overview/helloworld.c.md)顶部往下除了之前讲过的预处理，就是:

{% page-ref page="han-shu/" %}

这里的第一个函数的第一个参数为 \`int columns\[\]\`，引出

{% page-ref page="../ji-ben-lei-xing/" %}

第二个函数的第一个参数使用了\`char \*output\`的格式，\`\*\`是为

{% page-ref page="../ji-ben-lei-xing/zhi-zhen.md" %}

再往后一个参数 const，我们对其解释：

{% page-ref page="const.md" %}

再往下就到了main的作用域部分：

{% page-ref page="zuo-yong-yu.md" %}

中间出现了一个方法结果赋值语句，我们插播一下

{% page-ref page="../zuo-you.md" %}

再接着是gets

{% page-ref page="han-shu/" %}

{% page-ref page="../shu-ru-shu-chu.md" %}

然后while循环，其中条件`!=`

{% page-ref page="../cao-zuo-fu-you-xian-ji/" %}

方法体中的打印部分则引出了

{% page-ref page="../ge-shi-hua-can-shu.md" %}

现在已经了解了基本所有知识了，是时候了解一下

{% page-ref page="../cun-chu-jie-gou.md" %}

#### 转义字符：

```text
\ddd #将`ddd`转化成8进制
\xddd #16进制!
```

