### 通用
-	oh-my-zsh
-	Postman		
-	Sublime Text
-	Atom		
-	VIM		
-	FileZilla

### Linux
-	gitg		

### Mac
-	brew
-	Alfred
-	iTerm
-	Foxmail
-	Dash
-	GitX
-	Charles

## 使用索引
下文Ubuntu系统都是ubuntu16.04
### ubuntu基本设置
System Settings--Appearance--Behavior--Enable workspaces 启动工作空间（分屏导航）

### ubuntu快捷键
长按super键  
System Settings--Keyboard--Shortcuts 里面不仅仅有快捷键，还有运行器的快捷输入，不过部分用不了啊。

### Ubuntu常用
```python
#查看系统shell
cat /etc/shells  
#查看目前的shell
echo $SHELL
#设置默认shell,需要重启
chsh -s /bin/zsh
#直接输入密码
echo -n "请忽略："
sudo -S commond <<eof
tpout #密码
eof   
#文本编辑
nano fileName
vim fileName
```

### oh-my-zsh
http://ohmyz.sh/
#### 常用zsh配置
zsh的配置文件在 ~/.zshrc  
git常用命令的别名配置文件在~/.oh-my-zsh/plugins/git/git.plugin.zsh

```
#别名缩写
alias cls='clear';
#某种文件格式的默认编辑器
alias -s java=atom;
#
alias ctar='tar -cvf';
alias -s tar='tar -xvf';#输入*.tar直接解压
alias cgz='tar -zcvf';
alias -s gz='tar -zxvf';
alias cbz='tar -jcvf';
alias -s bz='tar -zxvf';
```

#### 常用zsh命令
```
directoryName #等同 cd directoryName
..  #等同 cd ..
... #等同 cd ../..
d   #以序号列出访问过的目录，再输入序号即可跳转
-   #切换到之前的目录
```
#### 强大zsh功能
grep ↑ #grep可以是任何命令，输入grep加上下键自动过滤对应历史输入  
kill psName tab #输入名称再tab即可补全进程ID
#### 常用zsh插件
zsh-syntax-highlighting //语法高亮  
autojump //可以实现j directoryName进行智能跳转，支持模糊。不过这个目录要之前访问过  
（类似的还有个Z，[become-command-line-power-user-oh-my-zsh-z](https://www.smashingmagazine.com/2015/07/become-command-line-power-user-oh-my-zsh-z/)）
#### 相关zsh文章
[终极 Shell——ZSH](https://zhuanlan.zhihu.com/p/19556676?columnSlug=mactalk)   
[为什么说 zsh 是 shell 中的极品？](https://www.zhihu.com/question/214184490)  
[一些命令行效率工具](http://wulfric.me/2015/08/zsh/)   
[Zsh-简体中文](https://wiki.archlinux.org/index.php/Zsh_(%E7%AE%80%E4%BD%93%E4%B8%AD%E6%96%87))
[awesome-zsh-plugins](https://github.com/unixorn/awesome-zsh-plugins#frameworks)
### Postman、Charles
新环境还没有用到网络请求，以后再试
### Sublime Text
[如何优雅地使用Sublime Text](http://jeffjade.com/2015/12/15/2015-04-17-toss-sublime-text/)
### Atom
#### Atom快捷键
在面板里找。。。
#### md文件和预览同步滚动
https://github.com/atom/markdown-preview/issues/24
### VIM
[vim简单使用](http://blog.csdn.net/u013867301/article/details/67639752)
### FileZilla
ftp工具
### gitg
git图形化查看工具
这货在我之前的电脑上(ubuntu16.04)老是崩溃,但是在新环境没有(ubuntu18.04)
