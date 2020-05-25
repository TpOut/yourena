@[toc]
#理念
MaterialDesign是现实世界的缩影，包括材质、纹理、光影。  

其特征是三维坐标系：  
![这里写图片描述](https://img-blog.csdn.net/20180527230011515?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4NjczMDE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**方位**：上下对应Y坐标；前后对应Z坐标；左右对应X坐标

**术语**：Sticky--随界面滚动，且在某个位置会固定住的元素；Floating--在滚动界面之前的元素

##光影
主光源产生锐利的背景阴影，环境光漫反射产生柔和的周边阴影

##材质
材质：z轴上的每一个dp，都可以产生一个图层。每个图层可以分割多个区域，每个区域即一个对象，本文称之为图层对象。
###elevation
值 = 图层对象上面z轴坐标高度 - 地面z轴坐标高度 ，以dp为单位   

###图层对象属性
图层对象厚度固定为1，具有elevation属性[^footnote-elevation]。  
[^footnote-elevation]:[resting elevation and response offset](https://material.io/design/environment/elevation.html#default-elevations)  

>每个图层对象具有一个默认elevation属性，即resting elevation，在用户点击时，需要响应变化的组件会对应变化elevation。
>一般而言，具有高优先级的，或者具有控制权的图层对象的elevation值较高。

图层对象是类似纸张的实体，所以动画效果不应该有类似气体生成、胶体粘连、液体流动等无规则效果。但是允许透明度--包括渐变、大小、位置的变化。

###基本规则
1、同一图层的图层对象可以以边联结，变成一个图层对象（但是示例中需要两条边一样长）；一个图层对象也可以分割成两个。	  
2、同一图层的图层对象不能穿插，即部分联结；不同图层的图层对象也不能穿插，即不能移动穿越。  
3、一个图层对象应该是无限大的。类似.9图片的处理，不受拉伸影响。  
4、一个图层对象只属于一个图层，即图层对象不能有卷曲的部分。

###elevation和规则冲突
由于elevation变化，可能造成图层碰撞，与基本规则2冲突，所以需要进行合理设计。比如card列表，点击card的时候，移除fab（当然最好是不要有这种情况）。 

###图层对象边界
不同的图层对象通过明确的边界进行区分。可以用颜色，透明度达到效果。但是不同颜色的重叠让人对前后的感受不是那么明确；透明度在某些情况下连前后都不能让人区分出来。

此时在三维世界的背景下，光源和材质作用后，产生的阴影将具有奇效。阴影基于elevation的变化对应变化，

##纹理
纹理：在这边的理解就是显示的内容（有点像是贴图）。

内容是独立的部分，可以进行放大缩小移动的行为。和材质不同是，没有厚度，不属于图层，属于（依赖）图层对象。但是行为和所依赖的图层对象的行为是独立的。

#布局
（vertical布局为例）
##网格
所有的组件（如appbar或者list item的高度），基于8dp的网格；  
![这里写图片描述](https://img-blog.csdn.net/2018052809473356?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4NjczMDE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
组件里的元素（图标和字体等），会布上4dp的网格。  
![这里写图片描述](https://img-blog.csdn.net/20180528092349759?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4NjczMDE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
如上图，字的基线需要严格压在网格的某一条线上；除非使用居中的时候，则不需要这么严格处理。

永久性的布局和临时性的布局是在网格设计之外的（即不造成现有网格布局的变化的情况）。
##布局分割
整体布局由三个部分组成，他们都是基于8dp网格的：
1、margins：内容左侧到窗口左侧，和内容右侧到窗口右侧的距离；为固定的值
2、gutters：内容和内容之间的间隙；为固定的值
3、columns：除去margins,gutters,剩余的部分按照一定数量等分，就是columns；为百分比

内容则占据一个或多个columns（包含跨越的gutters）

margins和gutters的值以及columns的等分数量是根据宽度等级划分的[^footnote-breakpoints]
[^footnote-breakpoints]:[layout - breakpoints](https://material.io/design/layout/responsive-layout-grid.html#breakpoints)    

一般性有16dp，24dp，4列，8列的设计，且margins和gutters没有必要完全一样。    
>比如图片之间使用8dp gutters会让人觉得更有联系感，而相册之间使用32dp gutters会让人感觉更有独立感。    
>比如图片左右使用8dp margins可以让图片更有显示空间；  
>比如理想的每行字数是40-60字，可以使用64dp margins来防止字数太多 

 如果空间足够，那么类似抽屉菜单的菜单栏可能是固定在一侧不动的，这个时候不用把这部分算入布局细分;  
 或者抽屉菜单进行toggle的时候，布局也是随着所占据空间的伸缩而进行伸缩的（即需要缩放，平移的肯定不考虑）。
##keylines  
keylines也是基于8dp网格的，是一些竖的基准线，值为到窗口边界的距离，根据breakpoint进行设计     
##padding
padding是基于4dp网格的，是两个元素之间的间隙距离，可以横向（包括内容和内容之间的间隙，gutters是竖向的）也可以竖向，一般是24dp  
##比例
尽量保证app显示的比例统一。  
推荐比例：16:9, 3:2, 4:3, 1:1, 3:4, and 2:3 
##组件宽度
因为手指大小的缘故，可触摸区域至少需要48dp*48dp的范围，两个元素之间至少8dp空余
不可触摸的可点击区域，至少需要24dp*24dp的范围，两个元素之间至少8dp空余

组件宽度基于上述区域最小范围进行设计，始终要考虑breakpoint的设计，当然也不建议太大(例如横跨屏幕左右)
##density--紧凑度
这里将dense理解成紧凑度高。

默认使用低紧凑的，如果有优化体验的必要，则使用紧凑度高（dense）的。   

适用场景：
List, tables, and long forms。  
如单行文本，default density -- 48; high density -- 32  
如material design输入框，default -- 56 ; high -- 44  
不适用场景：
日历这种就不要使用紧凑了，影响使用。提示也不要使用紧凑了，会降低关注度。

字体的紧凑度用行高来控制。 

###紧凑度对布局的影响
高紧凑度的布局中的margins和gutters也需要变大，这样易于观察到布局的分组。  

#导航
横向导航，提供到达所有一级页面的功能项。   
如果功能项大于5，使用navigation drawer；否则如果功能项大于1，使用bottom navigation bar。  
当然大于2的情况下，可以使用tabs作为另一种方案（tabs可以作为任何层级的导航方式，不一定是一级。） 

#颜色和文字
总之，要易分辨，可读性强，容易记住。  
当然要有区分的同时具有统一性，  

##对比度  
W3C推荐，小文本和背景起码要4.5:1的对比度  
大文本（14粗体，或者18以上）和背景的对比度起码在3:1以上  

（对比度怎么计算？） 
##颜色
区分：
variant，颜色的近似变体，  
dark light，同一种颜色的亮色暗色，比如AppBar和StatusBar用亮暗色区分  

统一：  
primary，最主要使用  
secondary，可选，如果没有，则用Primary。
>secondary主要用在：fab,选择控制--sliders,switches,高亮选中文字，进度条，超链接和headlines。  

上面两种是和品牌展示相关的，而下面这些则不是：
Surface, background, and error colors
还有基于上述五种颜色的颜色：
Typography and iconography colors  

而选定颜色时，MaterialDesign会有十种变体颜色[^footnote-color]，需要仔细检查对于字体和图标颜色的显示是否清晰  
经过研究表明，白色字体的最好背景是400，黑色字体的最好背景是50。

当然颜色的设计没有固定准则：  
白天和黑夜模式是独立的；相同产品不同模块之间也是相互独立的  
有些地方黄色对应兴趣、粗体对应有活力，具体的表现需要整体展现

[^footnote-color]:[material palette](https://material.io/design/color/the-color-system.html#tools-for-picking-colors)  
##文字
###字色
改变透明度优于改变颜色  
##字体
两段字体的放置间距，以其基线到上一段字体的底部的距离为计算。
多出提及Serif or sans serif字体更好

如果数字经常变化，使用tabular figures（monospaced numbers）  
系统自带字体，没有啥特殊性，所以一般应用在大量文本的地方，或者字号小于14sp的地方。  
###noto
Android默认字体是roboto，而noto是被设计成跨语言的字体
##宽高
英文文字一般一行 40~60字，小一些的为20~40字  
一段字体之间的行间距，为上一行baseline到下一行Baseline的距离；字体大小14对应行间距20，字体大小20对应行间距28（大致）

此时中文20dp的话，行间距是8dp 

>多语言的考虑：  
>每句话对应的字数不同，可能需要调整字间距；每句话的高度不同，可能需要预留一定的高度；句子的书写方向不同，左右上下或上下右左。

##相关软件
sketch, gallery, Material Theme Editor 

#图标
图标需求是48*48，但是在制作的时候，使用192*192,基于4的网格。 

有一些基准提示线，如152*152的正方形区域（同心）；或者176直径的圆；或者176*128的长方形（包含横竖）  
以及基准线、网格俩俩之间的交接点组成的新的分割线。   

斜角图标的切角应该是45度

#动画
补间、fading（透明度渐变）、共享   
除了组件，同样适用于Icon  
很多的动画设计都是为了增加前后的关联性
##时间
开关、选择的单一状态变化，时间是100ms  
dialog的进入是150ms，退出是75ms（fade）  
icon变化这种（比如返回键变成关闭），是200ms  
图标的细节变化，500ms  
侧边栏（部分屏幕）打开是250ms,关闭则是200ms  
点击卡片放大到全屏的时间是300ms，缩回则是250ms  
##变加速度
不是固定的加速度会更加“顺滑”，分为开始变加速时间、匀速时间和结束变加速时间[^footnote-easing]。  
[^footnote-easing]:[motion-easing](https://material.io/design/motion/speed.html#easing)

Standard easing(250-350ms)（开始变加速时间短，然后一定时间固定加速度，结束变加速度时间长）  
Emphasized easing（比standard更少加速时间，更多减速时间。如果standard曲线执行时间某动画超过400ms，可以使用）  
Decelerate easing（具有初始速度，然后减速） 
Accelerate easing（先加速，然后保持一定速度）

相关联的组件变化应该保持一致，比如a的位置和b的颜色变化，曲线应当一样。   
也可以单独为某个组件增加一个结束时间，以突出该组件  

布局变化的时候，因为布局位置的移动，会和元素移动产生视觉竞争，所以元素进行fading处理，而不能用位移。   
元素变化的时候，不能产生重叠（图层对象不碰撞原理。。。）   
可以有底部渐变的感觉，不能让人产生剪切的感觉  

##其他
震荡（不断减缓回弹）、伸缩、回弹   
别搞重要的图标。。。

四边放大，可以直接直线操作，即常规的scale操作  
但是如果只有一边沿着某条线放大，那么有些混淆，是左右还是上下的移动。  
所以一边的放大，最好是弧线形的 
有横向弧线（即最后是左右放大完毕），和纵向弧线  
如果页面本身有滑动功能（如list），请保持方向与滑动方向一致。   

尾巴（影子）效果，动画时间在150-350ms,需要连贯性   
列表的顺延出现很重要，时间也不能太长。。。  
先加载了一部分布局，后加载的布局不能对已显示的部分造成较大的布局变化，  

一秒内最大闪烁次数为3，禁止大范围的闪烁 [^footnode-threshold]
[^footnode-threshold]:[thresholddef](https://www.w3.org/TR/WCAG20/#general-thresholddef)

#手势
Tap
Scroll and pan
Drag
Swipe
Pinch

Tap or long press  
Double tap 

#状态
如果同时有多个状态，要一起显示，可以加深。  

不同状态对应的透明度需要在不同的背景上进行调整，但是各个状态相对的比例应该是一样的。  
 https://material.io/design/interaction/states.html  

disable , 38%  
floating action buttons (FABs), bottom and side sheets, dialogs, menus, tooltips, and alerts, or navigational components (such as app bars, tabs, bottom navigation and steppers)  
如果fab不可用，那么应该隐藏而不是disable  


Components that cannot inherit activation states include: buttons, floating action buttons, selection controls, sliders, app bars, bottom and side sheets, dialogs, or alerts.

Dragged states cannot be inherited by: app bars, buttons, bottom navigation, dialogs, alerts, menus, or steppers.

##空状态  
不要写驱动用户进行操作的提示语句，  

可以放一些指导内容。近似提示内容，依据情况  

#提示
snackbar 和toast的区分？  

snackbar最多有一个action
alert、dialog 有1-2个  
空白状态提示，0-2个 

#操作指引
可以放置一个没有意义的元素，提示用户操作。  
出现场景：
first-run experience (FRE)  
用户长时间未进行操作  
一次重大的布局更新之后  

注意使用总体次数，单独一次提示最多两次   

#帮助、反馈
位置要显然可见，如抽屉栏，或者标题栏（点开列表）  

帮助要放在最后一项，反馈放在帮助前，  

或者当帮助页面是单独一个页面时，可以把反馈放到里面。  

有专有图标。。。。
如果是帮助，用黑色背景问号图标；如果帮助有说明，用白色背景问号图标  
如果是反馈，用黑色背景感叹号图标  

帮助文档要简洁明了（禁止技术术语），结构性强  

#onboarding(管理？)  
对于已经安装过的用户，需要激励他们  
对于跃跃欲试的用户，让他们更多使用  
对于刚接触的，让他们熟悉

一些设置(self-select)，在内部定制页面展示   
预先关注的主题(quick start），第一次或某个时间策略内使用，
更新后的告知页面（top user benefits），第一次或重大更新使用  

不要把self-select和top user benefits联合使用。  

页面要少，每页10个以内  

quick start可以给予提示  

在用户不熟悉之前，不使用界面元素表示更新。  

toothbrush features” (meaning, a feature you would use once or twice a day)

#时间格式  
主要是明确时间  
month,day,year 如果在当前年份内，不显示年份  

时间起止，中间用“-”联结，且不加空格;如果起止年份一样，只写在末尾。  
Dec 6,2013-Jan 2,2014; Jan 4-June 2,2014  

加上时区之后，单个数字之前不加0，如UTC+5:00  
而使用本地时间时，用两个字母，如ET，即不添加S或者D；使用国际化时，才加上。  

S是标准时间，D是夏令时  
EDT.美国东部夏季时间。  

月份和日期的顺序在不同国家不同，意思一样  
10 Jan,08:00  
Jan 5,7:16  

事件事件：只用显示日期  

如果有缩写的必要，斜杠也可（/），首字母也行（周日S），去除：00也行。  

最好还是和日常语言交接。更方便理解就行 .

#省略号缩略  

##中间省略
使用3点的情况： 大部分数字，社保号等

跨平台和字体的规则： 
1. Place two regular spaces before the first midline ellipse.
2. Then add a thin space between each midline ellipse and after the final one. 


Or, use the Unicode string instead: U0020,U0020,U2022,U2006,U2022,U2006,U2022,U2006

使用四点的规则：银行卡号（借记卡、信用卡）  
U0020,U0020,U2022,U2006,U2022,U2006,U2022,U2006,U2022,U2006  
##句尾省略
U2026，U2026，U2026

#镜像布局（RTL）  
默认情况： 数字和未翻译字符（即使是一部分）不会变化方向  

列表icon,不表示方向性，可以不对称处理  
阿拉伯数字在某些国家也需要翻译？  
Bengali, Marathi, Nepali, and some Arabic-speaking locales use different forms of numbers.

斜杠在大部分国家是不需要对称处理的，（那哪些要呢？）  
时钟顺时针方向也是不需要处理的，如需对称，可以改成线性方向。  

本身对称的图片也不需要处理（意思是处理需要消耗一下性能咯，）  

一些图标的左右性，是类似右手习惯的原因，比如搜索按钮。那么也是不需要对称处理的。  

视频播放相关的按钮顺序、和进度条也不需要对称处理，因为他们表示的是磁带播放顺序，而不是时间。。。    

#ScreenReader  

TalkBack   

Screen readers read all text and elements (such as buttons) on screen aloud, including both visible and nonvisible alternative text.  

不要在很高优先级的功能上使用timer,可能会发生不正常的读取  

##色盲  
red-green, blue-yellow, and monochromatic等  

所以需要提示文本  

##自定义  

需要让听者知道用途。  
不要规定死操作，因为输入端可以有很多种      

hint speech  ?

#指纹
指纹icon是64dp*64dp,不要对图标拉伸也不要外面加圆   
或者指纹36*36,背景圆是64*64，注意背景颜色不要混合  

#问题
Q1：基于 理念-材质-基本规则4，很好奇翻书效果要怎么实现。
Q2：如下滑动效果在Android上的实现，需要连贯的伸缩效果
![这里写图片描述](https://img-blog.csdn.net/20180527234436999?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4NjczMDE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
![这里写图片描述](https://img-blog.csdn.net/20180527234248598?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4NjczMDE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
Q3：光影部分，都知道会产生阴影。那么应该产生多大范围的？具体计算公式？	
Q5：基于 布局-网格，文字需要严格压在网格上，需要怎么实现？根据经验，文字在显示的时候，自己会有一个底部空间。
Q6：基于 布局-组件宽度，以及下示例图，输入框属于点击组件？不属于触摸？
![这里写图片描述](https://img-blog.csdn.net/20180528145937826?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4NjczMDE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
Q7：基于 布局-组件宽度，以及下示例图，按钮的空余区域没有8dp?还是描述上有些问题？
![这里写图片描述](https://img-blog.csdn.net/20180528150616424?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4NjczMDE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)