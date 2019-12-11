原文：https://git-scm.com/book/en/v2/Git-Tools-Submodules



#### 适用场景

多个项目依赖一个库，每个项目拥有修改库的可能，每个项目还要保持同步该库。



#### 定义

同步：因为子模块有个`update` 的操作，所以仓库的`fetch`,`pull`,`push`操作在这里统一叫做同步

更新：专指 `update`

父仓库：含有子模块的仓库



【】括起来表示命令，可以在“相关命令” 小节里查看名称对应的命令

#### 快速使用

提前说明，可能是最初设计的原因，虽然现在 子模块 也被存储在 `.git` 中，但是在克隆、同步父仓库的时候，子模块的文件目录是一种特殊模式，父仓库默认不会对其进行自动操作。需要我们再进行一步更新操作，所以在流程上会更麻烦 （如果有疑问，看”教程和说明“小节就知道了）



- 初始化：

    【添加子模块】

- 克隆：

    【克隆递归子模块】

- 同步到本地：

    - 如果只需要同步子模块的内容，【更新子模块并合并】
    - 如果要同步父仓库和子模块的内容，【pull递归子模块】

    然后再【子模块切换分支】，再【更新子模块并合并/变基】

- 同步到远端：

    【push递归子模块】

    

#### 实际功效

子模块已经是第三次阅读了，理解能力的确是一点点上去的。



子模块，顾名思义，其自身需要自成一体，能够单独作为一个git 仓库，又同时能够作为另一个git 仓库的 内部数据。按我的理解：

- 自成一体：子模块单独作为一个git 仓库进行自身维护

- 内部数据：父仓库随时可以拉取子模块的最新内容，甚至对子模块修改后能推送到子模块的远程仓库
- 不受影响：父仓库自身的管理应该不受影响，日志不干扰，流程不变化



结果看下来”不受影响“这一点还是相差蛮大的，只是可以通过【配置】和【常用别名】减少影响。



#### 相关命令

没指定模块名字的都是处理所有子模块

```git
#配置
git config --global diff.submodule log //配置后，git diff 等同于 git diff --submodule
git config status.submodulesummary 1 //配置后，git status 展示子模块的摘要
git config submodule.recurse [1|true] //配置后，除了clone，命令默认添加--recurse-submodules（支持的话） 
git config -f .gitmodules submodule.<submoduleName>.branch stable //配置【更新子模块】默认拉取的分支，-f 表示影响所有人,没有则只影响自己；或者在`.git/config`也可配置
git config push.recurseSubmodules [check|on-demand] //配置【push递归子模块】的行为

#常用别名
$ git config alias.sdiff '!'"git diff && git submodule foreach 'git diff'"
$ git config alias.spush 'push --recurse-submodules=on-demand'
$ git config alias.supdate 'submodule update --remote --merge'

#命令

#添加子模块
git submodule add <submoduleURL> //subURL 可以是相对路径也可以是url
#更新子模块
git submodule update [--init] [--recursive] //如果没有初始化过，需要init;如果有嵌套，需要recursive
#更新子模块并合并/变基
git submodule update --remote [--merge|rebase] //默认master，没有合并/变基是游离态，要在父仓库使用

#命令参数

git log -p --submodule //展示子模块的修改记录
git diff --cached --submodule 
git diff --cached [submoduleName]
#checkout递归子模块
git checkout --recurse-submodules
#pull递归子模块
git pull --recurse-submodules
#push递归子模块
git push --recurse-submodules=[check|on-demand] //check 表示如果子模块有没push的，直接失败；on-demand则先尝试再次push子模块
#克隆递归子模块
git clone --recurse-submodules <srcURL> [targetURL]


#配置

#修改子模块的本地配置 url
git config submodule.<submoduleName>.url <URL>

#遍历工具

$ git submodule foreach 'git stash'
#子模块切换分支
$ git submodule foreach 'git checkout -b featureA'
$ git diff; git submodule foreach 'git diff'

```



### 教程和说明

#### 预设

有一个仓库 baba，一个仓库 didi

（为了方便起见，都建在本地；为了模拟使用情况，两个仓库里都初始化了README）

git 2.20.1 (Apple Git-117)



#### 添加子模块

baba【添加子模块】didi，此时

- 使用`ls` 查看，baba 目录里面增加了didi 文件夹，而且里面有didi 的README 文件。

- 使用`git status` 查看，didi 文件夹和一个叫做 `.gitmodules` 的文件已经被加到了`staged` 状态

    - `.gitmodules` 记录了子模块didi 和父仓库 baba的映射关系，当前示例为：

        ```git
        [submodule "didi"]
        	path = didi
        	url = ../didi
        ```

        当其他人克隆父仓库的时候，将依据此文件中每个子模块的url 拉取数据，具体测试在后面的 “协作” 小节

- 使用`git diff`，可以看到关于子模块的描述，其中有文件模式 `new file mode 160000`

然后`git commit`，记录的输出中也展示了文件模式

>文件模式
>
>100644 ：常规模式，表示一个子目录或者文件
>160000： 表示该文件在提交中，是作为一个目录整体（entry）。
>
>git 对于后者有特殊的处理。比如此处，在目录外时git 不会track 目录里的内容



此时进入didi 文件夹，使用`git log` ，会展示 didi 的日志



#### 克隆

baba 已经加入didi 的数据。

现在从别的地方按常规克隆一下baba 看看，把第三个仓库叫做 babaWithDidi

进入babaWithDidi，此时  

- `ls -a` 可以看到 `.gitmodules` 文件已存在
- `git log` 可以看到提交日志也是齐全的

进入didi 文件夹，使用 `git log` 会展示 babaWithDidi 的日志

但没有任何文件。别慌，这是正常现象

对于didi 的文件拉取，还要执行两个命令（如果只执行update 无效）：

```git
git submodule init //初始化本地配置文件，没有懂这里的配置文件是啥
git submodule update //fetch 子模块的数据，并且检出父仓库合适的提交日志
//上述两个命令可以被【更新子模块】代替
```



上面的步骤多的让人烦恼，甚至还有子模块的子模块，你可以使用【克隆递归子模块】一步达成

然后一切都和之前的baba 仓库一样了，只是didi 的HEAD 指向的是commitHash，即 **游离态**。



#### 游离态处理

当克隆、同步到本地的didi 的HEAD 是游离态

需要再进行额外的步骤，

首先进入didi 目录，然后`git checkout`到具体的分支

再返回父仓库目录，执行【更新子模块并合并/变基】，此时才是和常规的操作一样，真正的合并到本地



#### 中间点

经过 ”预设、添加子模块、克隆“ 三节之后，本地有三个仓库：

- didi 仓库

- baba 仓库，含有子模块didi

- babaWithDidi 仓库，克隆自 baba 仓库

    

接下来就是讲各种协作场景，拆分成步骤，其实就是仓库同步的逻辑。



#### 子模块同步

**远程同步到本地**

- 1、didi 修改，而baba/didi 没有修改

    - 1、

        在baba 目录 【更新子模块并合并/变基】

        或者进入baba/didi 目录进行常规同步操作

        

        返回baba 目录，会发现修改，此时

        用`git diff --submodule` ，查看区别

        然后commit修改即可

        

**本地同步到远程（注意先从”远程同步到本地“）**

- 2、didi 没有修改，而baba/didi 修改

    - 1、

        在baba/didi 执行常规的同步操作即可，只是会引起baba 的变化

        所以也可以直接使用baba 的同步方法，参看 ”父仓库同步“ 小节



#### 父仓库同步

子模块要是没有修改，就和原先的一样咯。下面就不说咯

**远程同步到本地**

- 1、didi 修改

    - 1、baba 更新了baba/didi（baba 自己是有提交的） babaWithDidi 没有更新 babaWithDidi/didi

        babaWithDidi `git pull` 之后会发现父仓库已经同步日志，子模块没有同步。

        此时用【配置】后的`git status`可以看到`update`提示，并且信息中有左箭头`<` 。

        

        所以其实`pull` 已经递归处理了子模块（个人猜测是指所有的常规文件），只是父仓库不会帮助子模块`update`

        即需要【更新子模块】(好习惯是加上初始化和递归参数)

        

        而上述步骤又可以优化成一步【pull递归子模块】

        只是didi 的HEAD 依旧是 **游离态**， 参看之前的 ”游离态处理“ 

    - 2、baba 更新了baba/didi， babaWithDidi 也更新了 babaWithDidi/didi

        此时

        baba/didi 和 babaWithDidi/didi 在相同分支

        baba 和 babaWithDidi 也在不同分支

        用babaWithDidi 执行 `git pull` ，会正常合并。也可以使用【pull递归子模块】

    - 3、baba 修改了baba/didi,  babaWithDidi 也修改了 babaWithDidi/didi

        此时

        baba/didi 和 babaWithDidi/didi 在不同分支

        baba 和 babaWithDidi 也在不同分支

        用babaWithDidi 执行 `git pull` ，会正常合并。也可以使用【pull递归子模块】

    

- 1、didi 的引用修改

    额，用了git 几年了，基本没有引用修改的情况（都是宁可用新地址重新下一个哈哈哈）

    暂时不测试了。（有需要看原文哦）

    

**本地同步到远程（注意先从”远程同步到本地“）**

- 1、

    常规的推送不会推送子模块，为了防止错误，需要在推送的时候检查子模块使用

    使用【push递归子模块】



#### 其他

##### 嵌套子模块

因为暂时还不在需求范围之内，不做具体使用测试。估计没啥毛病



#### issues

##### 分支切换

2.13以下的版本，切换依赖子模块和不依赖子模块的分支会有问题，需要再执行【子模块更新】；

而之后的版本使用【checkout递归子模块】可以解决



##### 将子目录转化成子模块

因为子目录记录在`.git`中，所以不能单纯的`rm -Rf subDirectory`

要使用`git rm -r subDirctory`, 然后【添加子模块】



此时，假设在master 上将目录转化成子模块，

如果常规的`checkout` 其他分支, 会发现子模块的文件在其他分支也有，只是`untracked`

也可以 `checkout -f`来强制覆盖，但是这会用当前子模块覆盖子目录，需要注意没有丢失数据。

而再从其他分支`checkout`回master的时候，会发现子模块目录下没有文件，甚至`update`都没救，此时需要进入子模块目录执行`git checkout .` 才行。