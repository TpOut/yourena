## 子模块

如果想直接上手操作，可以只看 “适用场景”、“定义”、“快速使用” 小节

否则看这篇文章或者 [原文](https://git-scm.com/book/en/v2/Git-Tools-Submodules) 都可以

[TOC]

### 适用场景

多个项目依赖一个库，每个项目可以修改库，并且保持库的同步。



### 定义

广泛的子模块：以A 为一个整体，B 为一个整体。将B 作为 A 中的一部分，即称B 为A 的子模块。

文件结构如下：

```shell
A
|-- README.md
|-- B
    |-- README.md
```

**父仓库**：git 的整体即仓库，故而本文称A 为父仓库。

**子模块**：同理，B 为子仓库，因为概念为submodule，故而本文称B 为子模块

> 因为仓库本身是工具型。所以子模块的添加不应该增加或应该较少增加工具使用的负担。因此git 子模块有专门的命令将子模块和父仓库关联，以达到直接在父仓库执行命令操作子模块的便捷性。
>
> 但是由于一定的原因，在克隆、同步父仓库的时候，父仓库默认不会操作子模块，即需要使用者额外增加参数或者步骤

**同步**：因为子模块有个`update` 的操作，所以仓库的`fetch`,`pull`,`push`操作在这里统一叫做同步

**更新**：专指 `update`



本文中【】括起来的文本表示命令，可以在“相关命令” 小节里查看对应的命令语法

如【添加子模块】，直接全文搜索 输入“添加子模块”， 就可以在 “相关命令” 小节看到，且会比使用的地方有更详细的说明



### 快速使用

父仓库和子模块，各自单独进行自身维护的时候，和常规流程没什么区别。这里不多做说明

主要在于父仓库添加了直接操作子模块的步骤，使得使用更加复杂。

但是关于操作复杂化这一点，熟悉命令之后，可以参考【配置】和【常用别名】进行一波命令优化。

>环境 git 2.20.1 (Apple Git-117)



#### 添加子模块

假设当前有两个独立仓库baba，didi，didiUrl 表示didi 的远程地址

希望baba 作为父仓库，didi 作为baba 的子模块

（可能需要 “常见问题” -- “忽略文件无效” 小节）

- 如果baba 仓库中没有didi 文件夹

    ```shell
    git submodule add didiUrl #在父仓库【添加子模块】
    git commit -sa #提交更新
    ```

- 如果baba 仓库中有同名didi 文件夹

    - 就是想将didi 转化成子模块 

        ```shell
        #把文件拷贝，并更新到didi仓库
        ```

    - ```shell
        git rm -r subDirectory #在父仓库先删除原先的didi index
        rm -r #然后常规删除didi 文件
        
        git submodule add didi #在父仓库【添加子模块】
        git commit -sa #提交更新
        ```

    - didi 只是冲突

        - 修改父仓库didi 文件

            ```shell
            mv ./didi ./didiInBaba #给父仓库的didi 换个文件名
            git rm -r subDirectory #删除原先的didi在父仓库的记录
            
            git submodule add didi #在父仓库【添加子模块】
            git commit -sa #提交更新
            ```

        - 修改didi 在父仓库的名字

            ```shell
            git submodule add didi didiIn #在父仓库【添加子模块】
            git commit -sa #提交更新
            ```



#### 同步本地到远程

现在本地仓库有了子模块，如果要同步，可以有两种方式

- 父仓库同步

    ```shell
    git push --recurse-submodules=check #【push递归子模块】，检查子模块同步，防止依赖使用失败
    ```

- 只是子模块同步

    ```shell
    cd didi #进入子模块目录
    git push origin #同步
    cd ..
    ```



#### 克隆带有子模块的仓库

```shell
git clone babaUrl #克隆
cd didi #进入didi 文件目录，此时没有任何文件
git submodule init #初始化子仓库本地配置文件，没有懂这里的配置文件是啥
git submodule update #同步子模块的数据

cd didiSubmodule #如果didi 也还有子模块
...
```

> 上述步骤实在有些繁琐
>
> ```shell
> git clone --recurse-submodules babaUrl #【克隆递归子模块】一步完成，并且自动递归
> ```

```shell
git checkout master #将didi 的HEAD 去除游离状态
```



#### 分支操作

现在已经有了 带有didi 子模块的babaWithDidi 仓库

- checkout 

    - 独步操作

        - 从有didi 的分支master 切换到没有didi 的分支dev

        ```shell
            (master)$ git checkout -f dev #强制覆盖, 可以先看下不加-f 会怎么样
        (master)$ rm -r didi #删除无关的文件
        ```

        - 从没有didi 的分支master 切换到有didi 的分支dev

            ```shell
            (dev)$ git checkout master
            (master)$ cd didi
            (master)$ git submodule update #更新子模块
            (master)$ git checkout master #去除游离态
            ```

    - 递归操作

        ```shell
        git checkout --recurse-submodules #【checkout递归子模块】
        ```

        

#### 同步远程到本地

现在已经有了 带有didi 子模块的本地 babaWithDidiLocal，克隆自远程的babaWithDidi 仓库

- 本地仓库没有修改

    - babaWithDidi 仓库整体修改

        ```shell
        git pull --recurse-submodules #【pull递归子模块】
        ```

        或者

        ```shell
        git pull #拉取
        git submodule update --init --recursive #【更新子模块】
        cd didi
        git checkout master #去除游离态
        ```

    - didi 修改，而babaWithDidi 没有修改 (其实也属于“babaWithDidi 仓库整体修改”的一种情况)

        ```shell
        cd didi #进入子模块目录
        git pull #同步
        
        cd ..
        git commit -sa #提交修改
        ```

        或者

        ```shell
        git submodule update --remote --merge #更新子模块
        git commit -sa #提交修改
        ```

- 本地仓库有修改

    - babaWithDidi 更新了babaWithDidi/didi， babaWithDidiLocal 也更新了 babaWithDidiLocal/didi

        ```shell
        #此时父仓库和子模块，对于远程而言，进度都已经分叉
        (babaWithDidiLocal)$ git pull #可以正常合并，或者使用【pull递归子模块】也可以
        ```



### 相关命令

关于命令的格式，参看 [命令行格式说明](../../cs-category/command-line.md)

没有模块名字作为参数的都是处理所有子模块

```shell
#配置

#子模块url 修改
git config submodule.<submoduleName>.url <URL> #修改子模块在本地配置的 url
git config --global diff.submodule log #配置后，git diff 等同于 git diff --submodule
git config status.submodulesummary 1 #配置后，git status 展示子模块的摘要
git config submodule.recurse [1|true] #配置后，除了clone，命令默认添加--recurse-submodules（支持的话） 
git config -f .gitmodules submodule.<submoduleName>.branch stable #配置【更新子模块】默认拉取的分支，-f 表示影响所有人,没有则只影响自己。可以省掉去除游离态的步骤
git config push.recurseSubmodules [check|on-demand] #配置【push递归子模块】时的检查行为

#常用别名
git config alias.sdiff '!'"git diff && git submodule foreach 'git diff'"
git config alias.spush 'push --recurse-submodules=on-demand'
git config alias.supdate 'submodule update --remote --merge'

#命令

#添加子模块
git submodule add <submoduleURL> [targetName]//subURL 可以是相对路径也可以是url
#更新子模块，本地配置
git submodule update [--init] [--recursive] //如果没有初始化过，需要init;如果有嵌套，需要recursive
#更新子模块，从远端
git submodule update --remote [--merge|rebase] //默认master，没有[--merge|rebase]是游离态，要在父仓库使用

#命令参数

git log -p --submodule //展示子模块的修改记录
git diff --cached --submodule 
git diff --cached [submoduleName]
#checkout递归子模块
git checkout --recurse-submodules
#pull递归子模块
git pull --recurse-submodules
#push递归子模块
git push --recurse-submodules=[check|on-demand] //check 表示如果子模块有没push的，直接失败；on-demand则先尝试push子模块再失败
#克隆递归子模块
git clone --recurse-submodules <srcURL> [targetURL]

#遍历工具

git submodule foreach <`gitCommand`> #对每个子模块执行command
#常用 foreach
git submodule foreach 'git stash'
git submodule foreach 'git checkout -b featureA'
git diff; git submodule foreach 'git diff'
```



### 信息补充

父仓库和子模块关联之后

- 子模块也会以一种特殊文件模式（160000，表示该文件在提交中，是作为一个目录整体）存储在父仓库的 `.git` 中。

- 会在父仓库修改或创建 `.gitmodules` ，这个文件记录了子模块和父仓库的映射关系，当前示例为：

    ```shell
    [submodule "didi"]
    	path = didi
    	url = ../didi
    ```

    当其他人克隆父仓库的时候，将依据此文件中每个子模块的url 拉取数据

    可以用【子模块url 修改】

- 子模块是否创建的依据，可以通过在子模块目录查看`git log`，成功则显示子模块 的日志

- `git status`默认看不到子模块的信息，需要参考【配置】

- 提示信息中会出现 `update` 、左箭头`<` 等相关的字样



### 遇到问题



#### 操作失败

Q：在分支A 进行了某个操作，同步到分支B 的时候，没有生效

A：

master 分支有didi 子目录，而dev 有didi 子模块 (且是从原先的didi 子目录转化过来)

同时didi 子目录中有一文件temp 是 gitignore 掉的

以如上场景执行`merge` 操作为例：

```shell
#个人理解，merge 简单来看，是将dev 中和master 的不同，在master 上补全
#只是因为temp 文件被忽略，dev 操作temp 的步骤，不会被git 追踪，即master 上无法获悉
#然后master 补全的时候，无法对 didi/temp 进行操作
#此时git 发现，master 上的didi 不为空，所以不会去做后续的操作
#最终导致didi 转化成子模块失败

#处理办法
(master)$ rm -r temp #只能先自己将temp 相关的操作再次执行一遍
(master)$ master merge didi #然后就可以了

#相信还有很多类似的情况，思路应该都是一样
```



#### 忽略文件无效

A：在子模块中的文件，并不会被父仓库的`.gitignore` 文件作用。

Q：需要自己再补充一个，否则可能造成"操作失败"的问题



### 未涉及部分



##### 子模块引用修改

额，用了git 几年了，基本没有引用修改的情况（都是宁可用新地址重新下一个哈哈哈）

暂时不测试了。（有需要看原文“合并子模块修改 Merging Submodule Changes“小节哦）



##### 嵌套子模块

因为暂时还不在需求范围之内，不做具体使用测试。估计没啥毛病

