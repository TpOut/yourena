translate or note / text

自带folder-index，用来检查文章的增删(目前用js来获取，直接在html的console中用即可)

所有文章分为 copy, index, note
copy为无后缀文本格式，index 和 note 为md格式

以安卓官网为例：android studio 》 user guide 》 Meet Android Studio
下面包含overview, install android studio等等文章

创建文件夹 android 》（为了减少层级）android-studio-user-guide 》 Meet-Android-Studio  
一般对应创建 overview, overview-index.md, overview-note.md 三个文件
以及 install-android-studio, install-android-studio-index.md, install-android-studio-note.md等

第一个copy存储原文内容，用来和下次观看做比较，google比较好，文章底部有时间可以更方便比较。  
第二个index用来存储摘要的索引，一是留待后看（还没用到），一是需要每次观看（容易变更）。
第三个note是对除了索引之外的部分，进行摘要，方便后续检索，而无需观看原文（英文真的累）。

如果index和note内容较少，会写成一个文件，如overview.md  
如果原文被记为索引，那么无需写三个文件，如install-android-studio就没有  
如果原文内容写在一个文件也不够看，会拼到章节文件，如meet-android-studio.md/或者干脆就叫overview.md 以后再整理

如果有copy，则表示该文章已经读过，可以看需要先去看下原文有无更新，对比只看index或者note即可（如果index和note都无相关内容，则可能表示这篇文章毫无可记之处，如android-studio-user-guide 》write-your-app 》build-a-ui-with-layout-editor）。  
如果无copy，index中可能存在,也可能不存在相关记录，无需在意。重看即可或者通过index进入都可  



#### 问题

increase production parity。什么意思？