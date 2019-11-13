## 多语种编辑器(Translations Editor)
以前都是把默认string.xml文件给翻译公司，然后把翻译后的文件放到对应的语言文件夹中  
翻译有一个过程，会造成后续加的strings没有翻译  
这时候，这个editor就非常有用了  

打开方式： > 随便打开一个string.xml > 点击右上角的open editor  

无需翻译的字符串在代码中可以这么定义：  
```  
<string name="app_name" translatable="false">EasyApp</string>
```  
支持 [BCP47](https://tools.ietf.org/html/bcp47)  

翻译服务（editor右上角的order入口地址无法访问了）：　　　　
https://android-developers.googleblog.com/2013/11/app-translation-service-now-available.html　

## 重构布局以支持RTL(Layout Editor)
as自带重构命令， 在 Refactor > Add RTL support where possible ，可以处理 TextView, CosntraintLayout, LinearLayout中的双向文字(bidirectional text)。  
同时这个命令会自动左右镜像应用的ui布局，和view组件   
弹出面板的选项对应作用：1、在AndroidManifest.xml中的<application>添加android:supportsRTL="true" ； 2、布局中left,right要改成start,end（需要17以上）或全写/添加v17（16以下）；   
更多查看 《原生系统4.2对RTL布局的支持》：  
https://android-developers.googleblog.com/2013/03/native-rtl-support-in-android-42.html  
文章说可以在LayoutEditor中选择一个text widget，然后选择属性，如firstStrong,但是我试了一下都没有试出来。仅对规则做记录：      
firstStrong(fs): root view的默认设置。fs字符确认段落方向。如果没有，那么用view的布局方向    
anyRtl: 如果包含strong RTL(sRTL)字符,则段落使用RTL；否则，如果有sLTR, 使用LTR；否则用布局方向  
ltr，或者rtl ：直接使用对应方向　　
locale: 跟系统locale一样　　
inherit: 和parent的一样　　
>上述strong 默认是 strong directional，一般这些字符会预设方向。如大部分字母(alphabetic)符号和音(syllabic)符、非欧洲且非阿拉伯数字(digits)、象形词（Han ideographs）、punctuation characters that are specific to only those scripts.

测试本地化可以用Pseudolocales（这篇文章在guide章节）：　　　　
https://developer.android.google.cn/guide/topics/resources/pseudolocales　

## 主题编辑器(theme editor)
tool bar: tool > theme editor  
可以设置常规主题色，有很多material规约帮助，比如：
- 可以在选择颜色后　获取最接近的material 颜色
- 在选择背景的时候，如果和文字颜色对比度不够，会警告  
- 设置与state对应的colorlist(原谅我没找到在哪)
- 快速配置不同设备（根据values目录的变体）　　
