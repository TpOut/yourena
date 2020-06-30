# 图片

```markup
<!--在img中或者CSS背景中直接引用SVG图片，那么SVG不能被JavaScript操作，同时也需要CSS样式内嵌在SVG中    
除非必要，不然不要使用picture的media属性 -->
<picture>
    <source media="(max-width: 799px)"
        srcset="elva-480w-close-portrait.jpg">
    <source media="(min-width: 800px)" srcset="elva-800w.jpg">
    <source type="image/svg+xml" srcset="pyramid.svg">
    <source type="image/webp" srcset="pyramid.webp">
    <img src="elva-800w.jpg" alt="Chris standing up holding his daughter
        Elva">
</picture>

<img srcset="elva-fairy-320w.jpg,
    elva-fairy-480w.jpg 1.5x,
    elva-fairy-640w.jpg 2x"
    src="elva-fairy-640w.jpg" alt="Elva dressed as a fairy">

<svg version="1.1"
    baseProfile="full"
    width="300" height="200"
    xmlns="http://www.w3.org/2000/svg">
    <rect width="100%" height="100%" fill="black"></rect>
    <circle cx="150" cy="100" r="90" fill="blue"></circle>
</svg>
```

