# 精通Python爬虫框架scrapy

### XPath

develop tools - console  
inspect - 选中一个标签 - 右键可以 copy xpath

```text
输入 $x('/html/body/h1') 来搜索<body>内的<h1>元素 (按路径检索)      
输入 $x('//h1')来搜索页面的<h1>元素（模糊检索，搜索所有）      
输入 $x('//a/@href')、$x('//a[@href="..."]')（搜索属性,[]内还可以使用一些函数，如contains等）        
输入 $x('//a/text()')（选取文本）
```

\[\]内函数参见：http:www.w3schools.com/xsl/xsl\_functions.asp\(这个页面已经无法访问\)

```text
scrapy shell https://www.example.com
# HtmlResponse  
response.xpath(...).extract()
```

使用XPath检索时，尽量将表达式开头写的更靠近目标一些，防止变化引起的不确定性。对于类的运用也尽量减少，因为一般性类是用来改变外形的，也比较容易变化。而id相对以上的方式明显是最可靠的。当然，上述的可靠性是根据其与元素的关联性来确定的，关联性越强，变化性越弱

### 安装

书本强烈推荐Vagrant。。可能是为了虚拟机环境更统一  
我直接上的Scrapy.org进行安装的[Anaconda](https://docs.anaconda.com/anaconda/),[Miniconda](https://conda.io/docs/user-guide/install/index.html)  
后来发现好像conda只是集成了很多库的python...于是又像Vagrant屈服了

需要安装Vagrant和Docker（用lantern连接这两个站点的网速好像有点僵。。）  
用[https://www.grc.com/securable.htm](https://www.grc.com/securable.htm) 下载SecurAble工具，可以检测是否支持Hardware Virtualization  
如果不支持，可以在scrapybook.s3.amazonaws.com在线运行

