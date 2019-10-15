# css

#### 基本语法

属性和选择器

tagSelector { propertyName: propertyValue; }

declaration -- propertyName: propertyValue;

tagSelector和propertyName都可以多个

#### 语句

@charset 和 @import （元数据）  
@media 或 @document 、@supports（条件信息，又被称为嵌套语句，见下方。\)  
@font-face （描述性信息）

#### 简写属性

font，background，padding，border，和 margin  
如：

```css
background: red url(bg-graphic.png) 10px 10px repeat-x fixed;
等价于
background-color: red;
background-image: url(bg-graphic.png);
background-position: 10px 10px;
background-repeat: repeat-x;
background-scroll: fixed;
```

具体简写不可能写全，请在实践和css属性值索引界面查询。

#### 选择器

* Simple selectors

  > class="first done" 属于两个类，即first ,done  
  > 可以用`*`匹配所有，但是明显影响性能，非必须不设置  
  > id索引用`#`，类索引用`.`

* Attribute selectors
  * Presence and value： \[attr\]；\[attr=val\]；\[attr~=val\] : 属性值包含val  
  * Substring value：\[attr\|=val\] : 、\[attr^=val\] 以val开头、\[attr$=val\] 以val结尾、\[attr\*=val\] 包含val
* Pseudo-classes、Pseudo-elements

  > Pseudo-class，以冒号：标记；Pseudo-elements, 以::标记

* Combinators

  > 匹配满足A和B--- A,B ： 即Multiple selectors  
  > 匹配B--- A B \(A &gt;&gt; B\) ： B是A的后代结点; A &gt; B ：B是A的直接子节点; A + B : B是A紧挨的下一个兄弟节点; A ~ B : B是A之后的兄弟节点中的任意一个

#### 值和单位

px,mm, cm, in  
pt, pc: 点（Points \(1/72 of an inch\)）， 十二点活字（ picas \(12 points.\)

em:1em与当前元素的字体大小相同（更具体地说，一个大写字母M的宽度）;浏览器给网页设置的默认基础字体大小是16像素，这意味着对一个元素来说1em的计算值默认为16像素。但是em单位是会继承父元素的字体大小，所以如果在父元素上设置了不同的字体大小  
ex, ch: 分别是小写x的高度和数字0的宽度。  
rem: REM（root em）和em以同样的方式工作，但它总是等于默认基础字体大小的尺寸；继承的字体大小将不起作用  
vw, vh: 分别是视口宽度的1/100和视口高度的1/100

0可以无单位，line-height也可以（以font-size的倍数处理），动画次数无单位

font和width的200%都是以父容器的对应属性为基准。

#### 层叠和继承

**冲突选择优先级**  
重要性（Importance）: !important 可以覆盖优先级，让当前选择器优先级变高，但是不建议开发使用，建议用户使用\(理由如下\)

样式表分为三种，浏览器默认样式，web开发人员样式，用户自定义样式  
他们之间的重要性如下：  
默认 &lt; 用户常规 &lt; 开发常规 &lt; 开发重要 &lt; 用户重要

专用性（Specificity）:  
一个选择器具有的专用性的量是用四种不同的值（或组件）来衡量的，填充顺序为0000；  
第一位: style直接子元素，即没有选择器的样式声明，填充为1；否则为0  
第二位：选择器中含有n个id，则增加n 第三位：选择器中含有n个\[类 属性 伪类\]，则增加n 第四位：选择器中含有n个\[元素 伪元素\]，则增加n

所以 id &gt; class &gt; element  
这里有个问题，如果是十进制的四位数，那么有没有可能出现某位的数值大于10?然后大小比较如何处理？

源代码次序（Source order）:  
如果多个选择器具有相同的重要性和专用性，后面的代码规则会覆盖前面的。

重要性 &gt; 专用型 &gt; 源代码次序

**继承**  
子元素沿用父元素的属性行为叫做继承，内置的默认继承规则叫做自然继承。  
大部分情况下，属性都是根据常理来设计成自然继承或者非自然继承。如有疑问，查看css语法章节。

可以将属性设置成下列值来控制继承性：  
使用inherit，表示和父元素对应属性的值一样  
使用initial，表示和浏览器对应属性的默认值一样；如果没有对应值，则自然继承；如果非自然继承，没说怎么反应。  
使用unset，首先自然继承；否则继承浏览器对应属性  
使用revert，使用自定义样式定义的属性；如果没有，则设置成用户代理的样式

疑问： 如果不用属性控制，那么链接的颜色是蓝色； 如果用了initial，那么链接的颜色是黑色。按理说都是浏览器的默认值？

all属性 = 所有自然继承属性

#### 视图模型

**box**  
注意: 外边距有一个特别的行为被称作外边距塌陷（margin collapsing）：当两个框彼此接触时，它们的间距将取两个相邻外边界的最大值，而非两者的总和。

margin,border,padding,Content

background包括 content --&gt; border外边距  
content的高度，忽略高度百分比；border，忽略宽度（粗细）百分比

border-sizing overflow --- auto; hidden; visible\(default\)  
background-clip --- border-box\(default\); padding-box; content-box  
outline 一般用来高亮，但是也不要和超链接的高亮混淆。

块框（ block box）是定义为堆放在其他框上的框（例如：其内容会独占一行），而且可以设置它的宽高，之前所有对于框模型的应用适用于块框 （ block box） 行内框（ inline box）与块框是相反的，它随着文档的文字（例如：它将会和周围的文字和其他行内元素出现在同一行，而且它的内容会像一段中的文字一样随着文字部分的流动而打乱），对行内盒设置宽高无效，设置padding, margin 和 border都会更新周围文字的位置，但是对于周围的的块框（ block box）不会有影响。 行内块状框（inline-block box） 像是上述两种的集合：它不会重新另起一行但会像行内框（ inline box）一样随着周围文字而流动，而且他能够设置宽高，并且像块框一样保持了其块特性的完整性，它不会在段落行中断开。（在下面的示例中，行内块状框会放在第二行文本上，因为第一行没有足够的空间，并且不会突破两行。然而，如果没有足够的空间，行内框会在多条线上断裂，而它会失去一个框的形状。）

#### 文字

安全字体：  
Arial（Helvetica），Courier New（Courier），Georgia，Times New Roman（Times），Verdana

**css默认字体种类**：  
serif 有衬线的字体 （衬线一词是指字体笔画尾端的小装饰，存在于某些印刷体字体中）  
sans-serif 没有衬线的字体  
monospace 每个字符具有相同宽度的字体，通常用于代码列表  
cursive 用于模拟笔迹的字体，具有流动的连接笔画  
fantasy 用来装饰的字体

可以使用REM-unit-polyfill兼容不支持rem单位的浏览器

修改斜体、粗细、大小写、横线（波浪）、阴影、对齐  
行、字母、单词间距

font简写：  
font-style, font-variant, font-weight, font-stretch, font-size, line-height, and font-family.  
其中font-size 和 font-family 是一定要指定的；font-size 和 line-height 属性之间必须放一个正斜杠。

#### 列表

列表的每个项目的前置标记，可以通过 list-style-type 、list-style-position 、list-style-image（简写为list-style） 进行变化, 甚至可以自定义type计数

因为image属性有大小限制，所以最好还是使用 background相关：  
background-image: url\(star.svg\);  
background-position: 0 0;  
background-size: 1.6rem 1.6rem;  
background-repeat: no-repeat;

```text
<ol start="4" reversed>
    <li value ="2">
```

#### 链接

状态： link\(没访问过\),visited,focus,hover,active\(注意顺序，后者会包含前者\)  
都有对应的伪类。  
color,cursor,outline

#### web字体

指定下载,注意字体版权:

```text
@font-face {
    font-family: "myFont";
    src: url("myFont.ttf");
}
```

#### 背景

默认实际上是透明的，所以需求白色的时候需要确切指定  
background-image: linear-gradient\(to bottom, yellow, \#dddd00 50%, orange\);

#### border

没有明确设置值时，border会默认使用文本的颜色，宽度为3px。

圆角可以是椭圆的，也可以用多张图片填充

#### 高级区块效果

box-shadow  
可以设置成内部阴影

#### filters

和css的shadow属性相比，会渲染box区域的实体部分，透明部分会被略过。  
filter属性，如果是一个实验性特性，会被某些浏览器要求以， -webkit-filter的形式书写。

#### 混合模式

[https://mdn.github.io/learning-area/css/styling-boxes/advanced\_box\_effects/blend-modes.html](https://mdn.github.io/learning-area/css/styling-boxes/advanced_box_effects/blend-modes.html)

background-blend-mode,  
mix-blend-mode

下面两个属性可以实现背景只跟随文字  
-webkit-background-clip: text; -webkit-text-fill-color: transparent;



odd,even 分别表示奇数和偶数

### 示例

```css
font-family: 'Open Sans', sans-serif;
background: url(pattern.png);
clear: left;
box-shadow: inset 0 3px 2px rgba(0,0,0,0.5),
                    inset 0 -3px 2px rgba(0,0,0,0.5);
display: block;
float: left;
padding:10em
        width: 100%;
        max-width: 1200px;
        margin: 0 auto;
        background-color: white;
        position: relative;
   border-collapse: collapse;
    border: 2px solid rgb(200,200,200);
    letter-spacing: 1px;
    font-size: 0.8rem;
  box-sizing: border-box;
```

```css
div+div {
  margin-top: 1em;
}

label span {
  display: inline-block;
  width: 120px;
  text-align: right;
}

input, textarea {
  font: 1em sans-serif;
  width: 250px;
  box-sizing: border-box;
  border: 1px solid #999;
}

input[type=checkbox], input[type=radio] {
  width: auto;
  border: none;
}

input:focus, textarea:focus {
  border-color: #000;
}

body > * {
  background-color: red;
  padding: 1%;
}
```

```css
/* 验证失败的元素样式 */
input:invalid{
  border-color: #900;
  background-color: #FDD;
}

/* 错误消息的样式 */
.error {
  width  : 100%;
  padding: 0;

  font-size: 80%;
  color: white;
  background-color: #900;
  border-radius: 0 0 5px 5px;

  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
```

