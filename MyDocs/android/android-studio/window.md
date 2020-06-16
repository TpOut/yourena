## 窗口

https://developer.android.google.cn/studio/intro/#the_user_interface
tool bar, navigation bar, editor window, tool window bar, tool window, status bar
这里没有列出最顶上的菜单栏，称之为 menu

### 操作

- 双击shift或者右上角的放大镜按钮，弹出的搜索框，可以进行action的搜索。
- 点击左下角电脑图标会隐藏/显示tool window bar（隐藏后，点击alt再按下alt键会显示出来，且随着第二此按下抬起而隐藏）
- 选择某个tool window（可以点击也可以快捷键），然后键盘输入字符串，会开始查找文件名。
- tool window 1：可以设置project、Android、Problem等
- 如果不小心关掉了调试的内联提示，请在 Debug 窗口中点击 Settings ，然后选中 Show Values Inline 复选框。

## 快捷键

### 快捷键列表

https://developer.android.google.cn/studio/intro/keyboard-shortcuts

### 列表未提及快捷键

对于menu里的item, 可以用 `alt + <itemName的首字母>` 来点开（不完全，因为有重名）
如：
alt + v 会打开view界面，可以在tools windows 中查看很多tool window的快捷键

自定义 代码缩略 : file > settings > editor > live templates（[演示文章](https://medium.com/google-developers/writing-more-code-by-writing-less-code-with-android-studio-live-templates-244f648d17c7)）

**文档说明**：将光标放在 方法、成员、类名上，按住`ctrl + q`（快捷键页面有，原文是f1，但是没用） 或者可以用来查看图像和主题背景等资源，如@style/appTheme处

**全屏化**： View > Enter Distraction Free Mode，以及该选项附近几项（`ctrl+` `, 选择view mode，可以快速选择）

| Tool Window                      | Windows and Linux | Mac               |
| -------------------------------- | ----------------- | ----------------- |
| 显示version Control的tool window | Alt+9             | Command+9         |
| 显示android monitor的tool window | Alt+6             | Command+6         |
| Hide All Tool Windows            | Control+Shift+F12 | Command+Shift+F12 |

ps：这里的对 ctrl+shift+f12的解释比“快捷键列表”中的好，所以保留　

#### 代码自动补全

https://developer.android.google.cn/studio/intro#code-completion-shortcuts　
相关快捷键： ctrl+space, ctrl+shift+space, ctrl+shift+Enter

至今多次尝试，依旧没看懂怎么回事，还多试出一个ctrl + alt + space
c+s 感觉意思应该是和自动提示一样
c+s+s 和 c+a+s 可以在任何表达式写到一半的时候使用，会有对应提示（提示规则没看懂）；c+s+s 可以连按两次
c+s+e 在末尾没有分号、大括号等的时候好使，其他换行语句末尾缺失会补充错误（就是挺弱的）