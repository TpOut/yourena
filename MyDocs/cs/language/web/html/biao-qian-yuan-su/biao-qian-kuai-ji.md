# 标签快记

```markup
    <sub> <sup>  上标、下标
    
    <hr>    分割线
    <br>    换行
    <h1>...<h6>    h1通常在页面中只应该出现一次；一个页面使用h1-h6的种类尽量不超过三个。
    <img>
    

    <figure>  用于单独内容单元，内嵌<figcaption>用作说明
    <iframe>  嵌入其他网页,可以设置为不允许被嵌入
    <embed>、<object>  嵌入PDF,SVG等，属于历史技术，新场景不太常见

    <div>  块级无特定语义元素
    <!-- 内联 -->
    <span>  只是做标记用
    <i>    斜体
    <em>    强调
    <abbr>  缩写
   
    <q>  表示行内引用，对文本增加引号显示，可以使用<cite>内嵌标签，但超链接还是要配合<a>
```

### 补充说明：

```markup
<!-- srcset是新版属性，实现的浏览器也会支持svg -->
<figure> <!--语义，注意会和alt同时出现-->
    <img alt="My test image"> <!--可以是图片、视频、表格等-->
    <figcaption>A T-Rex on display in the Manchester University Museum.</figcaption>
</figure>
```

### 代码相关

```markup
<code>  表示代码
<pre>  保留空格
<var>  标记具体变量名称
<kbd>  标记输入设备的输入内容（如Ctrl+A）
<samp>  用于标记输出设备的显示内容(如shell内的： $ ping baidu.com)
```

### 时间日期

```markup
<!-- 标准简单日期 -->
<time datetime="2016-01-20">20 January 2016</time>
<!-- 只包含年份和月份-->
<time datetime="2016-01">January 2016</time>
<!-- 只包含月份和日期 -->
<time datetime="01-20">20 January</time>
<!-- 只包含时间，小时和分钟数 -->
<time datetime="19:30">19:30</time>
<!-- 还可包含秒和毫秒 -->
<time datetime="19:30:01.856">19:30:01.856</time>
<!-- 日期和时间 -->
<time datetime="2016-01-20T19:30">7.30pm, 20 January 2016</time>
<!-- 含有市区偏移值的日期时间 -->
<time datetime="2016-01-20T19:30+01:00">7.30pm, 20 January 2016 is 8.30pm in France</time>
<!-- 调用特定的周 -->
<time datetime="2016-W04">The fourth week of 2016</time>
```

