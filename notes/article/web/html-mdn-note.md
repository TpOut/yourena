


### 表单

html5支持表单外使用相关部件，只需要添加form属性即可。但是浏览器的支持率还不高  
一个部件可以有多个label，但是最好还是不用  

#### 属性
action指定提交目的URL，默认（不写）为当前页面URL; method 指定提交方法  

#### 校验  
required  ; pattern(部分无需)  
minlength/maxlength min/max ;  

**自定义**
通过js实现，如果旧版不支持js的约束api，可以使用:  
https://hyperform.js.org/  

novalidate　关闭浏览器本身校验  
:valid、:invalid、:in-range 、:out-of-range   CSS伪类不会被关闭    

aria-live  展示给包括使用ScreenReader的人  


#### 小部件
```
<label>,
<input>, <textarea>, and <button>   
```

对于大多数表单部件，一旦表单提交，所有具有name属性的小部件都会被发送，即使没有任何值被填。对于单选框和复选框，只有在勾选时才发送它们的值。如果他们没有被勾选，就不会发送任何东西，甚至连他们的名字也没有。

**通用属性**  
autofocus,disabled,form,name,value   

**文本输入**  
readonly,placeholder,size,length  

- input
传递数据的时候，会把文本转换成单行文本。   
如果指定了type，但是某个浏览器不兼容，会解读成默认值text
- textarea   
cols, rows, wrap  
和Input的主要区别是，支持回车换行。  
html内容只是被显示成纯文本　　

**按钮**  
button类似于input，但是html值可以被处理。

图像按钮在提交时，不会提交图片，而是提交点击位置，如：  
http://foo.com?pos.x=123&pos.y=456  

**下拉框**  
select box，autocomplete box  　

**meter**
可以使用optimum，配合min,max,high,low来快速确定最优值、平均值、


## css语法

Cascading Style Sheets  

### 基本语法
属性和选择器

tagSelector {
    propertyName: propertyValue;
}  

declaration -- propertyName: propertyValue;  

tagSelector和propertyName都可以多个  

### 语句
@charset 和 @import （元数据）  
@media 或 @document 、@supports（条件信息，又被称为嵌套语句，见下方。)  
@font-face （描述性信息）  

### 简写属性
font，background，padding，border，和 margin  
如：

	background: red url(bg-graphic.png) 10px 10px repeat-x fixed;
	等价于
	background-color: red;
	background-image: url(bg-graphic.png);
	background-position: 10px 10px;
	background-repeat: repeat-x;
	background-scroll: fixed;

具体简写不可能写全，请在实践和css属性值索引界面查询。

### 选择器
- Simple selectors
>class="first done"  属于两个类，即first ,done  
可以用`*`匹配所有，但是明显影响性能，非必须不设置  
id索引用`#`，类索引用`.`

- Attribute selectors
 - Presence and value： [attr]；[attr=val]；[attr~=val] : 属性值包含val  
 - Substring value：[attr|=val] : 、[attr^=val] 以val开头、[attr$=val] 以val结尾、[attr*=val] 包含val

- Pseudo-classes、Pseudo-elements  
> Pseudo-class，以冒号：标记；Pseudo-elements, 以::标记  

- Combinators
> 匹配满足A和B--- A,B ： 即Multiple selectors     
> 匹配B--- A B (A >> B) ： B是A的后代结点; A > B ：B是A的直接子节点; A + B : B是A紧挨的下一个兄弟节点; A ~ B : B是A之后的兄弟节点中的任意一个

### 值和单位
px,mm, cm, in  
pt, pc: 点（Points (1/72 of an inch)）， 十二点活字（ picas (12 points.)    

em:1em与当前元素的字体大小相同（更具体地说，一个大写字母M的宽度）;浏览器给网页设置的默认基础字体大小是16像素，这意味着对一个元素来说1em的计算值默认为16像素。但是em单位是会继承父元素的字体大小，所以如果在父元素上设置了不同的字体大小  
ex, ch: 分别是小写x的高度和数字0的宽度。  
rem: REM（root em）和em以同样的方式工作，但它总是等于默认基础字体大小的尺寸；继承的字体大小将不起作用  
vw, vh: 分别是视口宽度的1/100和视口高度的1/100  

0可以无单位，line-height也可以（以font-size的倍数处理），动画次数无单位  

font和width的200%都是以父容器的对应属性为基准。

### 层叠和继承

**冲突选择优先级**  
重要性（Importance）:
!important 可以覆盖优先级，让当前选择器优先级变高，但是不建议开发使用，建议用户使用(理由如下)     

样式表分为三种，浏览器默认样式，web开发人员样式，用户自定义样式  
他们之间的重要性如下：  
默认　<　用户常规　< 开发常规 < 开发重要 < 用户重要  

专用性（Specificity）:  
一个选择器具有的专用性的量是用四种不同的值（或组件）来衡量的，填充顺序为0000；  
第一位: style直接子元素，即没有选择器的样式声明，填充为1；否则为0  
第二位：选择器中含有n个id，则增加n
第三位：选择器中含有n个[类 属性 伪类]，则增加n
第四位：选择器中含有n个[元素 伪元素]，则增加n  

所以 id > class > element  
这里有个问题，如果是十进制的四位数，那么有没有可能出现某位的数值大于10?然后大小比较如何处理？    

源代码次序（Source order）:  
如果多个选择器具有相同的重要性和专用性，后面的代码规则会覆盖前面的。

重要性 > 专用型 > 源代码次序  

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

### 视图模型
**box**  
注意: 外边距有一个特别的行为被称作外边距塌陷（margin collapsing）：当两个框彼此接触时，它们的间距将取两个相邻外边界的最大值，而非两者的总和。   

margin,border,padding,Content  

background包括 content --> border外边距  
content的高度，忽略高度百分比；border，忽略宽度（粗细）百分比  

border-sizing
overflow --- auto; hidden; visible(default)  
background-clip --- border-box(default); padding-box; content-box  
outline 一般用来高亮，但是也不要和超链接的高亮混淆。  

块框（ block box）是定义为堆放在其他框上的框（例如：其内容会独占一行），而且可以设置它的宽高，之前所有对于框模型的应用适用于块框 （ block box）
行内框（ inline box）与块框是相反的，它随着文档的文字（例如：它将会和周围的文字和其他行内元素出现在同一行，而且它的内容会像一段中的文字一样随着文字部分的流动而打乱），对行内盒设置宽高无效，设置padding, margin 和 border都会更新周围文字的位置，但是对于周围的的块框（ block box）不会有影响。
行内块状框（inline-block box） 像是上述两种的集合：它不会重新另起一行但会像行内框（ inline box）一样随着周围文字而流动，而且他能够设置宽高，并且像块框一样保持了其块特性的完整性，它不会在段落行中断开。（在下面的示例中，行内块状框会放在第二行文本上，因为第一行没有足够的空间，并且不会突破两行。然而，如果没有足够的空间，行内框会在多条线上断裂，而它会失去一个框的形状。）  

### 文字  
安全字体：  
Arial（Helvetica），Courier New（Courier），Georgia，Times New Roman（Times），Verdana  

**css默认字体种类**：  
serif	有衬线的字体 （衬线一词是指字体笔画尾端的小装饰，存在于某些印刷体字体中）	  
sans-serif	没有衬线的字体  
monospace	每个字符具有相同宽度的字体，通常用于代码列表  
cursive	用于模拟笔迹的字体，具有流动的连接笔画  
fantasy	用来装饰的字体  

可以使用REM-unit-polyfill兼容不支持rem单位的浏览器  

修改斜体、粗细、大小写、横线（波浪）、阴影、对齐   
行、字母、单词间距  

font简写：  
font-style, font-variant, font-weight, font-stretch, font-size, line-height, and font-family.  
其中font-size 和 font-family 是一定要指定的；font-size 和 line-height 属性之间必须放一个正斜杠。  

### 列表  
列表的每个项目的前置标记，可以通过 list-style-type 、list-style-position 、list-style-image（简写为list-style） 进行变化, 甚至可以自定义type计数    

因为image属性有大小限制，所以最好还是使用 background相关：  
background-image: url(star.svg);  
background-position: 0 0;  
background-size: 1.6rem 1.6rem;  
background-repeat: no-repeat;  

    <ol start="4" reversed>
        <li value ="2">
### 链接
状态： link(没访问过),visited,focus,hover,active(注意顺序，后者会包含前者)  
都有对应的伪类。  
color,cursor,outline  

### web字体
指定下载,注意字体版权:  

    @font-face {
        font-family: "myFont";
        src: url("myFont.ttf");
    }
### 背景  
默认实际上是透明的，所以需求白色的时候需要确切指定  
background-image: linear-gradient(to bottom, yellow, #dddd00 50%, orange);  

### border  
没有明确设置值时，border会默认使用文本的颜色，宽度为3px。  

圆角可以是椭圆的，也可以用多张图片填充    

### 高级区块效果  
box-shadow  
可以设置成内部阴影  

###　filters  
和css的shadow属性相比，会渲染box区域的实体部分，透明部分会被略过。　　  
filter属性，如果是一个实验性特性，会被某些浏览器要求以，　-webkit-filter的形式书写。　　

### 混合模式　　
https://mdn.github.io/learning-area/css/styling-boxes/advanced_box_effects/blend-modes.html  

background-blend-mode,   
mix-blend-mode  

下面两个属性可以实现背景只跟随文字  
-webkit-background-clip: text;
-webkit-text-fill-color: transparent;

## 布局  
默认是顺序读取元素并展示，

position ,默认static, 有相对，绝对和固定，以及sticky;绝对位置时，可以给直接或间接父节点添加position relative，来确定其所以来的父节点是哪个。（感觉这样会有歧义啊）；可以使用z-index修改定位区块的z轴关系，默认情况下，定位的元素都具有z-index为auto，实际上为0。        



float, 处理块级元素，让他们可以排成两列,不管float怎么使用，screenReader的读取依旧按照源码顺序；可以对某个元素使用clear,那么这个元素和之后的元素不再根据float的元素调整自身;float元素和非float元素之间无法设置间隔，需要用一个空的div作为过度。    

display, block, inline or inline-block 会改变元素在正常布局流中的行为方式,而一些不常见或特殊的值允许我们使用完全不同的方式进行布局，使用工具比如Flexbox  

form的css中使用display:table-相关值，可以让它实现table元素的效果  

父元素使用display:flex, 子元素使用flex:1，实现比例布局  

网格布局，还未得到支持。。  



## 等价字符引用

原义字符	等价字符引用

	<	&lt;
	>	&gt;
	"	&quot;
	'	&apos;
	&	&amp;  

  odd,even 分别表示奇数和偶数  



## js
语法和java类似  
严格意义等于 === , 严格意义不等于 !==  ； == ！= 只比较值，不比较数据类型，不建议使用。   


函数定义：  

    function checkGuess() {
        //doSomething
    }
常规函数调用：  
`checkGuess();`
事件函数传递：  
`guessSubmit.addEventListener('click', checkGuess);`  

获取选择器:  
`document.querySelector()`  
属性和style值对应，只是横杠分割的方式变成了驼峰方式  

日志，console.log()  

编写上，变量可以在被赋值之后再声明（实际解释上还是声明之后才赋值）  

变量，由数字下划线字母组成，不允许数字开头，不建议下划线开头（内置变量），区分大小写  

对象的定义：  

    //手动方式构造，最后一个变量不需要以逗号结尾，
		//dog称为对象的字面量(literal)，同时也作为命名空间namespace，约束这属性和方法    
	  //name,breed就改叫属性property,greeting改叫方法（本来是函数）。  
    var dog = {  
	      name : 'Spot',
	      breed : 'Dalmatian',  
	      greeting: function() {
            alert('Hi! I\'m ' + this.name[0] + '.');
        }
	  };  
调用方式除了常规的dog.name，也可以使用dog['name']的方式，因为后者对象有时候被称作关联数组(associative array)，  
可以在外部直接添加成员变量    
[]的方式，可以传递变量名，如dog['name']等同于 var v= 'name'; dog[v]  

    //构造函数；构造函数即其他语言的类  
    function Person(name) {
	      this.name = name;
	      this.greeting = function () {
		        alert('Hi! I\'m ' + this.name + '.');
	      };
    }
    //简化了常规的方式，myObj = func(){var object = {}, return object}
		//通过构造函数的方式叫做实例化  
		var person0 = new Person('Bob');

    //对象构造
		var person1 = new Object({
        name : 'Chris',
        age : 38,
        greeting : function() {
            alert('Hi! I\'m ' + this.name + '.');
        }
    })  
    //从现有对象复制对象，以 person1 为原型对象创建了 person2 对象
    var person2 = Object.create(person1);  

		var person3 = new person1.constructor('Karen', 'Stephenson', 26, 'female', ['playing drums', 'mountain climbing']);

继承的实现：

    function Teacher(first, last, age, gender, interests, subject) {
        Person.call(this, first, last, age, gender, interests);
				Teacher.prototype = Object.create(Person.prototype);
				Teacher.prototype.constructor = Teacher;

        this.subject = subject;
    }



join(), shift(), unshift()  

匿名函数，可以看出来可以传递：  
    myButton.onclick = function() {
        alert('hello');
    }
匿名函数也称为函数表达式。函数表达式与函数声明有一些区别。函数声明会进行声明提升（declaration hoisting），而函数表达式不会  

可以使用addEventListener来快速定义事件，https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener  

    //事件中可以传递event参数，event.target是事件所属的objects，    
    //还有一些函数有不同的参数    
    function bgChange(event) {
      var rndCol = 'rgb(' + random(255) + ',' + random(255) + ',' + random(255) + ')';
      event.target.style.backgroundColor = rndCol;
      console.log(event);   
    }  

    btn.addEventListener('click', bgChange);   

    //在表单中时可以阻止提交
    e.preventDefault();   
    //阻止事件冒泡  
    e.stopPropagation();


事件冒泡和捕获     

解析json：  
为了载入 JSON 到页面中，我们将使用 一个名为XMLHTTPRequest的API  


类结构：  
```
    |
    |__  constructor:
		|        |
		|		     |__ prototype
    |     
		|__  __proto__				

```
 默认情况下, 所有函数的原型属性的 __proto__ 就是 window.Object.prototype  
 用new创建的对象，有点类似继承，但是以Person作为父  

 原型链中的“连接”被定义在一个内部属性中，在 JavaScript 语言标准中用 [[prototype]] 表示（参见 ECMAScript）。然而，大多数现代浏览器还是提供了一个名为 __proto__ （前后各有2个下划线）的属性，其包含了对象的原型。疑问是，这里我看prototype是一个无限循环，__proto__ 才是正常的   
 只有在prototype中的元素才会被继承，如Object.prototype.valueOf()     

 每一个函数对象（Function）都有一个prototype属性，并且只有函数对象有prototype属性，因为prototype本身就是定义在Function对象下的属性。当我们输入类似var person1=new Person(...)来构造对象时，JavaScript实际上参考的是Person.prototype指向的对象来生成person1。另一方面，Person()函数是Person.prototype的构造函数，也就是说Person===Person.prototype.constructor  

 考虑到JavaScript的工作方式，由于原型链等特性的存在，在不同对象之间功能的共享通常被叫做 委托 - 特殊的对象将功能委托给通用的对象类型完成。这也许比将其称之为继承更为贴切，因为“被继承”了的功能并没有被拷贝到正在“进行继承”的对象中，相反它仍存在于通用的对象中。
