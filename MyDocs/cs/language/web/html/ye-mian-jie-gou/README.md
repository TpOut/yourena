# 页面结构

### 整体

```markup
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>My test page</title>
  </head>
  <body>
    <p>This is my page</p>
  </body>
  <footer>
  copyrights
  </footer>
</html>
```

#### head中的自定义图标

可以在收藏书签等场景出现：

```markup
<!-- third-generation iPad with high-resolution Retina display: -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="https://developer.cdn.mozilla.net/static/img/favicon144.a6e4162070f4.png">
<!-- iPhone with high-resolution Retina display: -->
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="https://developer.cdn.mozilla.net/static/img/favicon114.0e9fabd44f85.png">
<!-- first- and second-generation iPad: -->
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="https://developer.cdn.mozilla.net/static/img/favicon72.8ff9d87c82a0.png">
<!-- non-Retina iPhone, iPod Touch, and Android 2.1+ devices: -->
<link rel="apple-touch-icon-precomposed" href="https://developer.cdn.mozilla.net/static/img/favicon57.a2490b9a2d76.png">
<!-- basic favicon -->
<link rel="shortcut icon" href="https://developer.cdn.mozilla.net/static/img/favicon32.e02854fdcf73.png">
```

这个标签也有属性：

```text
type="image/x-icon"
```

### 主体各部分

```markup
<body>
    <header />
    <nav /> #页面导航，不能有二级页面
    <main>
        <article>
            <header />
        </article>
        <section />
        <div />
        <aside />  #侧边栏
    </main>
</body>
```

### 例子

```markup
<!-- 历史遗留标签，表示文档类型 -->
<!DOCTYPE html>
<!--  根元素  -->
<html lang="zh-CN">
    <!-- 非用户可见内容。包括搜索引擎关键字和页面描述，引用CSS和encode等   -->
    <head>
        <!-- 元数据，可以自定义并利用以提供更好的体验 -->
        <meta charset="utf-8">
        <meta name="author" content="TpOut">
        <meta name="description" content="Mdn learning area">
        <meta property="custome" content="custome content">
        <!-- 显示在浏览器的标签页  -->
        <title>My test page</title>

        <!-- 缩略icon的兼容显示方式 -->
        <!-- third-generation iPad with high-resolution Retina display: -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
            href="https://developer.cdn.mozilla.net/static/img/favicon144.a6e4162070f4.png">
        <!-- iPhone with high-resolution Retina display: -->
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
            href="https://developer.cdn.mozilla.net/static/img/favicon114.0e9fabd44f85.png">
        <!-- first- and second-generation iPad: -->
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
            href="https://developer.cdn.mozilla.net/static/img/favicon72.8ff9d87c82a0.png">
        <!-- non-Retina iPhone, iPod Touch, and Android 2.1+ devices: -->
        <link rel="apple-touch-icon-precomposed"
            href="https://developer.cdn.mozilla.net/static/img/favicon57.a2490b9a2d76.png">
        <!-- 默认icon -->
        <link rel="shortcut icon"
            href="https://developer.cdn.mozilla.net/static/img/favicon32.e02854fdcf73.png">
        <!-- 样式表 -->
        <link href="./styles/style.css" rel="stylesheet" type="text/css">
        <!-- 字体 -->
        <link href="https://fonts.googleapis.com/css?family=Roboto"
            rel="stylesheet"
            type="text/css">
        <!--内嵌-->
        <style> p { padding: 0% }</style>
    </head>

    <!-- 用户可见内容 -->
    <body>
        <!-- 页眉,可以配合<body>,<section>等 -->
        <header>
            <!--  主要导航功能  -->
            <nav>
                <ul>
                    <li><span>Home</span></li>
                    <li><a href="#">Get started</a></li>
                    <li><a href="#">Photos</a></li>
                    <li><a href="#">Gear</a></li>
                    <li><a href="#">Forum</a></li>
                </ul>
            </nav>
        </header>
        <!-- 独特内容，一个页面一次，放在<body>中  -->
        <main>
            <!-- 单独内容，与页面上的其他内容无关 -->
            <article class="">
                <h2>Welcome</h2>

                <p>Welcome</p>
            </article>
            <!-- 类似<article>,但是一般都是伴随出现 -->
            <section>
                <!--不要同时添加allow-scripts和allow-same-origin到你的sandbox，不然会被关闭沙盒 -->
                <iframe src="https://developer.mozilla.org/en-US/docs/Glossary"
                    width="100%" height="500" frameborder="1"
                    allowfullscreen sandbox>
                    <p> <a
                            href="https://developer.mozilla.org/en-US/docs/Glossary">
                            Fallback link for browsers that don't support
                            iframes
                        </a> </p>
                </iframe>
            </section>
            <!-- 和主要内容不会有直接联系，但是也是有间接联系 -->
            <aside class="">
                <h2>Favourite photos</h2>

                <a href="./images/favorite-1.jpg"><img
                        src="./images/favorite-1_th.jpg" alt="Small black bird,
                        black claws, long black slender beak, links to larger
                        version of the
                        image"></a>
                <a href="favorite-2.jpg"><img src="favorite-2_th.jpg" alt="Top
                        half of a
                        pretty bird with bright blue plumage on neck, light
                        colored beak, blue
                        headdress, links to larger version of the image"></a>
            </aside>
        </main>
        <!-- 页脚 -->
        <footer>
            <p>Chris Mills, 2016.</p>
            <p><a href="http://game-icons.net/lorc/originals/dove.html">Dove
                    icon</a> by Lorc.</p>
        </footer>
    </body>
</html>
```

