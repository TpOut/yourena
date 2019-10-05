官网书[ProGit](https://git-scm.com/book/en/v2)，在线阅读可以选择中文版

如果你重新安装了一个git，那么请记得：

- 时刻记得求助的最好对象是help文档
- 配置你的git，比如user.name等
- 配置惯用别名
- 添加忽略文件

##帮助
```
//可以尝试在 Freenode IRC 服务器（ irc.freenode.net ）的 #git或 #github频道寻求帮助
$ git help <key>
$ git <key> -h //--help
$ man git-<key>
```

##忽略
###忽略参考
github库：https://github.com/github/gitignore  
```
$man gitignore
```
###忽略语法
.gitignore的格式规范如下
* 所有空行或者以 ＃ 开头的行都会被 Git 忽略。
* 可以使用标准的 glob 模式匹配。
* 匹配模式可以以（/）开头防止递归。
* 匹配模式可以以（/）结尾指定目录。
* 要忽略指定模式以外的文件或目录，可以在模式前加上惊叹号（!）取反。
**例子：**
```
# no .a files
*.a
# but do track lib.a, even though you're ignoring .a files above
!lib.a
# only ignore the TODO file in the current directory, not subdir/TODO
/TODO
# ignore all files in the build/ directory
build/
# ignore doc/notes.txt, but not doc/server/arch.txt
doc/*.txt
# ignore all .pdf files in the doc/ directory
doc/**/*.pdf
```

###Glob模式
所谓的 glob 模式是指 shell 所使用的简化了的正则表达式。 
* 星号（*）匹配零个或多个任意字符；
* [abc]匹配任何一个列在方括号中的字符（这个例子要么匹配一个 a，要么匹配一个 b，要么匹配一个 c）；
* 问号（?）只匹配一个任意字符；
* 如果在方括号中使用短划线分隔两个字符，表示所有在这两个字符范围内的都可以匹配（比如 [0-9] 表示匹配所有 0 到 9 的数字）。
* `使用两个星号（* ) 表示匹配任意中间目录，比如a/**/z可以匹配 a/z, a/b/z或 a/b/c/z等。`  

##配置
```
$ git config -f "configPath" #用configPath的文件配置git
$ git config [--scope] [<key> "value"] #scope表示作用范围
$ git config --list #查看配置列表
$ git config <key> #查看具体的某一项
# editor setting in windows   
$ git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -nosession"
$ git config --show-origin user.name //知悉是从哪个文件读取的属性
```
>`on windows, system config path is C:\ProgramData\Git\config(not include xp) ; global config path is $HOME/.gitignore($HOME == C:\Users\$USER for most people)。 only be changed use -f  `

##获取仓库
```
//现有目录中初始化
$ git init
//克隆现有的仓库
$ git clone [-o "remoteName"] <repoUrl> ["myLocalName"] #-o后参数改变远程缩写命名（默认origin）

```
##查看状态
```
$ git status [-s] #查看状态
$ git add <[fileName] | [key]>##添加暂存
```
##查看不同
```
$ git diff #工作目录中已跟踪文件的修改
$ git diff --theirs #合并分支过程中，theirs表示要合入的分支；--base, --ours
$ git diff --staged #暂存区域中文件的修改，--cached 一样效果
```
###图形化插件
个人常用软件：Beyond Compare
```
$ git difftool --tool-help #查看支持哪些
$ git difftool --tool=<tool>  #这个是一次性的，可以通过配置config来永久显示。
```
##提交commit
```
$ git commit [-s] [-v] #弹出编辑器编辑信息（有配置的话), -s 署名 ; -v 在输出中加入diff信息 
$ git commit [-a] [-m 'added new benchmarks'] #已跟踪文件可以使用-a来跳过修改后的add操作
$ git commit [--amend] #在上一次commit之后且无内容修改时使用，只修改commit message
```
##移除文件
```
$ git rm [-f] README #删除文件，同时取消git中的追踪记录
$ git rm --cached README #只删除追踪（实际操作为：git rm file ,并新创建一个file副本）
$ git rm log/\*.log  #支持glob模式。注意\
```
##移动文件
```
$ git mv "file_from" "file_to" #git重命名（实际操作为：重命名（移动）文件，并删除前一个文件的跟踪，同时添加后一个文件的跟踪）
> $ mv README.md README
> $ git rm README.md
> $ git add README
```
>git不记录文件的移动--文件移动信息不会被记录，但是git有自己的办法知道文件是移动的。
##查看历史
个人推荐：SourceTree
```
$ git show hash #查看某次提交的修改日志
$ git log 
#常用参数：-n 查看最近n次的日志，--stat 列出具体修改数据
#--cc 这个参数，才看了没两个星期就忘了干嘛的。。。 
$ git log --oneline --decorate #查看各个分支当前所指的对象
$ git log --oneline --decorate --graph --all
$ git log --pretty=oneline #控制显示的格式还有medium，short,full,fuller
$ git log --pretty=format:"%h - %an, %ar : %s"
$ git log  --author=gitster --since="2008-10-01" \--before="2008-11-01" --no-merges -- t/ #条件限制
$ git log --grep #筛选，可以写多个条件，满足一个即可显示；或者--all-match满足所有
$ git log -S "fileName" #显示修改了fileName的提交，AS有可视化操作
$ git log --abbrev-commit #展示最短的无歧义hash值
$ git log -- [directoryPath] #directoryPath作为最后一个参数，可以限制查看的范围
```
<font color=red>Q1： --since==date，其中date的格式多样，但没找到哪里有说明。</>
###日志的引用
引用日志只存在于本地仓库。  
注意windows,cmd控制台中换行符默认是^，所以要引号或者多打一个^ 
```
$ git log -g #可以查看引用日志的信息  
$ git reflog #最近几个月HEAD和分支引用的路径历史
$ git show "branchName" #等价于查看当前branchName指向的hash
$ git show HEAD@{n}	#n可以在reflog结果中看到
$ git show HEAD@{2.months.ago}
$ git show master@{yesterday} #昨天时master分支顶端指向哪
$ git show HEAD^1	//查看HEAD的父提交，1表示第一个，2则表示第二个父提交；可以链式：HEAD^^^
$ git show hashValue^1
$ git show HEAD~2	//查看HEAD第一父提交的第一父提交(第一父提交表示合并时所在的分支的提交)
```
###日志区间
```
$ git log master..tempBranch //查看在tempBranch但不在master上的提交，如果有一边留空，默认为HEAD
$ git log master ^tempB --not tempC	//表示查看master上，但不在tempB上且不在tempC上的提交。
$ git log --left-right master...experiment	//三点表示在其中一个分支，但不同时在两个分支的提交。
```
##撤销
```
$ git reset HEAD <fileName> #将fileName还原至HEAD的状态 
$ git reset [--mixed] [hash] [filepath] #按reset规则处理文件
$ git checkout [--] <fileName> #将仓库里的fileName文件检出到本地
```
##远程仓库
```
$ git remote [-v] #显示远程仓库名
$ git remote show origin #查看origin的更多信息
$ git remote add <shortname> <url>  #添加远程仓库
$ git remote rename <name_from> <name_to>  #远程仓库重命名
$ git fetch [remote-name] #从远程仓库中获取数据，可以使用上面的shortname
$ git push [remote-name] [branch-name] #推送
$ git remote rm [remote-name] #移除远程仓库
```
<font color=red>Q1: checkout相关命令，参数，既可以是branch，也可以是tag，还可以是文件名。简单测试三者重名没有冲突处理提示，需要人工保证命名？</>
##标签
>支持两种标签， lightweight 和 annotated.  
前者是对一个提交的引用，只有commit 信息，没有tag自己的信息  
后者则和commit一样拥有一些信息，比如标签的名字，邮件，日期，信息，签名
```
$ git tag #列出已有标签
$ git tag -l 'v1.8.5*' #''里是过滤匹配规则
$ git tag -a v1.4 -m 'my version 1.4' #-a表示创建附注标签
$ git show v1.4 #显示tag信息
$ git tag v1.4-lw #创建轻量标签，只需要名字
$ git tag -a v1.2 hash #指定从hash创建标签
$ git push origin tagName #默认push不传送tag到远程,需要指定推送的tag
$ git push origin --tags #push所有远程上没有的全部标点
$ git checkout -b [branchname] tagName  #你可以使用tag来创建分支
```
##别名
为命令创建别名
```
$ git config --global alias.co checkout #以后可以使用 $ git co 来代替 git checkout
$ git config --global alias.unstage 'reset HEAD --'
$ git config --global alias.last 'log -1 HEAD'
$ git config --global alias.visual '!gitk' #执行外部命令，以!开头
```
##分支
###创建删除合并
```
$ git branch [branchname]  #创建分支，但没切换到新分支
$ git checkout [branchname]  #切换分支
$ git checkout -b [branchname] (fromBranchName) #新建分支并切换
$ git merge [branchname]  #将branchName 合入当前分支，即当前分支指针移动
$ git branch -d [branchname]  #删除分支，-D 强制删除
```
###图形化工具
```
$ git mergetool
```
###查看
```
$ git branch  #查看所有分支列表
$ git branch -v  #查看每个分支的最后一次提交
$ git branch -vv #包含-v 以及远程分支信息
$ git branch --merged  #查看哪些分支已经合并到当前分支
$ git branch --no-merged 
```
###远程分支
对应有远程指针，origin/master 来记录服务器的情况  
```
$ git ls-remote (remote)  
$ git remote show (remote)
$ git push (remote) (loacalBranchName):(remoteBranchName) #推送
$ git fetch origin #拉取
$ git pull  #等于fetch之后merge
$ git checkout --track remote/branchName #在本地创建branchName分支，并跟踪远端branchName
$ git checkout branchName #远端branchName存在的情况下，会自动跟踪
$ git checkout -b sf remote/branchName #在本地创建sf分支，并跟踪远端branchName
$ git branch -u remote/branchName  #添加上游
$ git push origin --delete (remoteBranchName)  #删除远程分支：移除指针，数据等垃圾回收，这期间还可以恢复
```
###变基
不要对已经推送到服务器的分支进行变基操作，会产生很多麻烦
```
$ git pull --rebase #养成拉取时--rebase的习惯
$ git rebase (targetBranch) [fromBranch] #HEAD指针不会变化，将当前分支快照重放到targetBranch分支  
$ git rebase --onto master （notInThisBranch） (inThisBranch) #这个场景很少吧，只提取某一部分  
$ git rebase --onto （targetBranch） (sourceBranch) 
```

###合并
空白符的问题，即使按照步骤模拟操作，也没有遇到。暂且略过
```
$ git merge -Xignore-all-space 或 -Xignore-space-change //忽略空白修改，比如换行符  
$ git merge -s 

//出现合并冲突的时候，可以使用
$ git show :1:hello.rb > hello.common.rb //共同祖先的stage
$ git show :2:hello.rb > hello.ours.rb //当前分支的stage
$ git show :3:hello.rb > hello.theirs.rb //MERGE_HEAD,即将合入的stage  
//然后单独批量修改某个文件，比如每行的换行符,再重新合入
$ git merge-file -p hello.common.rb hello.ours.rb hello.theirs.rb > hello.rb  

//出现合并冲突的时候
$ git checkout --conflict=diff3 fileName //注意fileName不要和分支名一样。。  
$ git log --oneline --left-right HEAD...MERGE_HEAD //两个分支的独立提交
$ git log --oneline --left-right --merge -p //查看两个分支产生冲突的提交，并以patch形式。

```
###合并策略
常规模式（recursive，octopus）
```
$ git merge -Xours 或 -Xtheirs //在遇到冲突时，选择ours/theirs；注意没冲突时会合入theirs的修改
```
ours策略  
没理解官网的例子。
This can often be useful to basically trick Git into thinking that a branch is already merged when doing a merge later on. For example, say you branched off a release branch and have done some work on it that you will want to merge back into your master branch at some point. In the meantime some bugfix on master needs to be backported into your release branch. You can merge the bugfix branch into the release branch and also merge -s ours the same branch into your master branch (even though the fix is already there) so when you later merge the release branch again, there are no conflicts from the bugfix.
```
$ git merge -s ours //只保持ours的修改；即完全丢弃theirs的修改
```

subtree的策略看懂了，但是按命令没实现。  
```
//先把子仓库添加为父仓库的远程仓库sub_remote，然后检出到本地某个分支sub_master,然后切换回master
$ git read-tree --prefix=sub/ -u sub_master
//此时有了sub目录，复制自sub_master,但是stage状态，需要commit
//之后子仓库如有更新，sub_master正常pull即可

$ git diff-tree -p sub_master //比较sub目录和sub_master是否有区别；也可以比较sub_remote/master

$ git merge --squash -s recursive -Xsubtree=rack rack_branch //把上面的区别合并到master中；这里出现问题fatal: refusing to merge unrelated histories，添加了一个选项才可以
```
###重用冲突解决
rerere--reuse recorded resolution 

开启方式，可以查看配置中的设置，或者创建.git/rr-cache 目录  
此时如果之前处理过合并，那么会直接采用之前的方式（减少合并日志）；
```
$ git rerere //主动对文件使用之前的方式进行处理
$ git rerere status
$ git rerere diff 
$ git checkout --conflict=merge fileName //把文件还原到冲突时
```
##交互式暂存
```
$ git add -i	//交互模式
$ git add -p	//部分补丁

$ git <commondKey> --patch //commondKey可以是add ,checkout , reset, stash save
```

##底层命令
```
$ git cat-file -p HEAD
$ git ls-tree -r HEAD 
$ git ls-files -s 
```

##还原提交
```
$ git revert -m 1 HEAD //
```

##调试
```
$ git blame //查看文件每一行的修改，姓名、时间；带有^的hash提交表示对应信息和第一次添加到项目时一样   
-L 限制行数
-C 可以查看代码的初始来源（可能不在一个文件）


//如果不确定某个区间内，哪次提交导致了问题
$ git bisect //
$ git bisect start //开始执行
$ git bisect bad //标记当前为问题提交
$ git bisect good [commithash] //标记某一个好的提交
//此时会根据路径，进行二分跳转，然后一直bad 或者good标记直到找到问题所在提交。
$ git bisect reset //结束此次查询

```
##子模块
注意添加文件的时候，
普通模式是new file mode 100644
而子模块部分的new file mode 160000 //特殊的一种模式

.gitmodules中的url是克隆/拉取的时候首先拉取的部分  
<font color=red>没看完。准备用起来先</font>
```
$ git config submodule.DbConnector.url <私有URL>
$ git submodule add [subUrl] [dir]
$ git diff --cached --submodule

//或者直接 git clone --recursive [remote]
$ git submodule init //克隆带有子项目的父项目时，还需进入子项目目录初始化
$ git submodule update --remote --merge [submoduleName]//更新子模块, 类似正常的pull

$ git config -f .gitmodules submodule.DbConnector.branch stable //配置DbConnector子模块的默认更新分支，-f .gitmodules表示所有人
$ git config status.submodulesummary 1 //配置之后，status会显示子模块更改摘要
$ git log -p --submodule //

$ git push --recurse-submodules=check //或者=on-demand,保证push之前，所有改动过的子模块都已经推送

$ git submodule foreach 'git stash'
$ git submodule foreach 'git checkout -b featureA'
$ git diff; git submodule foreach 'git diff'

$ git config alias.sdiff '!'"git diff && git submodule foreach 'git diff'"
$ git config alias.spush 'push --recurse-submodules=on-demand'
$ git config alias.supdate 'submodule update --remote --merge'
$ git config --global diff.submodule log //用git diff 代替git diff --submodule  
```
## 打包
不像format-patch一样提交无数个patch  
感觉限制程度很高，没啥应用场景
```
$ git bundle create repo.bundle HEAD master ^hash //所有master的数据
$ git clone repo.bundle [repoName] [-b] [branchName] //如果前面没有HEAD，则需要指定分支  
$ git bundle verify ../bundleName.bundle 
$ git bundle list-heads ../bundleName.bundle //查看可以检出的分支
$ git fetch ../bundleName.bundle master:otherBranch 

```
### 替换
完全没看懂干嘛用的

###凭证存储


##储藏与清理
```
$ git stash
$ git stash save #添加msg
$ git stash list
$ git stash apply [stash@{index}] #默认第一个
$ git stash apply --index #把暂存状态也还原，默认取消暂存
$ git stash drop
$ git stash --keep-index  #只存储没有暂存的修改
$ git stash -u #包括未跟踪文件  
$ git stash --patch #分块添加
$ git stash --all
$ git stash branch stashBranch #从stash新建分支
$ git clean #交互模式，-i
```
##搜索
从提交历史或者工作目录中查找字符串，支持正则
```
$ git grep -n searchStr //记录文件和对应行
$ git grep --count searchStr //记录文件和匹配的次数，
$ git grep -p searchStr *.c //查看.c文件中的哪些方法调用
$ git log -SsearchStr --oneline //查看字符串在哪次日志添加删除  
-G //正则
$ git log -L:functionName:file.c //查找file中函数的增删改记录
```

###签署

```
$ gpg --list-keys //查看列表
$ gpg --gen-key //生成
$ git config --global user.signingkey keyValue //
$ git tag -s
$ git tag -v tagName //查看tag签名
$ git commit -S // 小写s本身有个签名作用，这里的-S不是很懂
$ git log --show-signature -1 //查看commit签名
$ git merge --verify-signatures [-S] branchName // 保证合并的提交均具有签名,-S签署合并的提交。

```

##修改提交

```
$ git commit --amend
$ git rebase -i HEAD~3 //从HEAD~4的位置开始，依次引入提交，进行修改变基。可以编辑，换顺序，合并成一个，拆分成多个（在编辑的时候进行reset并重新拆分提交）
$ git filter-branch --tree-filter 'rm -f tmp.txt' HEAD //在这个提交历史中移除tmp文件
$ git filter-branch --subdirectory-filter trunk HEAD //以subDirectory为新的提交的根目录；没看懂啥意思

// 全局修改邮箱：
$ git filter-branch --commit-filter '
        if [ "$GIT_AUTHOR_EMAIL" = "schacon@localhost" ];
        then
                GIT_AUTHOR_NAME="Scott Chacon";
                GIT_AUTHOR_EMAIL="schacon@example.com";
                git commit-tree "$@";
        else
                git commit-tree "$@";
        fi' HEAD
```

