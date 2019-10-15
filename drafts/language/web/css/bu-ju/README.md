# 布局

默认是顺序读取元素并展示，

position ,默认static, 有相对，绝对和固定，以及sticky;绝对位置时，可以给直接或间接父节点添加position relative，来确定其所以来的父节点是哪个。（感觉这样会有歧义啊）；可以使用z-index修改定位区块的z轴关系，默认情况下，定位的元素都具有z-index为auto，实际上为0。

float, 处理块级元素，让他们可以排成两列,不管float怎么使用，screenReader的读取依旧按照源码顺序；可以对某个元素使用clear,那么这个元素和之后的元素不再根据float的元素调整自身;float元素和非float元素之间无法设置间隔，需要用一个空的div作为过度。

display, block, inline or inline-block 会改变元素在正常布局流中的行为方式,而一些不常见或特殊的值允许我们使用完全不同的方式进行布局，使用工具比如Flexbox

form的css中使用display:table-相关值，可以让它实现table元素的效果

父元素使用display:flex, 子元素使用flex:1，实现比例布局

网格布局，还未得到支持。。

