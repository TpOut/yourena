从根本上来讲 Git 是一个内容寻址（content-addressable）文件系统，核心部分是一个简单的键值对数据库（以hash为key,内容为value），并在此之上提供了一个版本控制系统的用户界面

>SHA-1 hash：输入SHA-1时最少开头4个数，没歧义即可。

## .git目录：

description仅供GitWeb程序使用  
config包含配置选项
info包含全局性排除(global exclude)文件，存放不希望在.gitignore里记录的忽略  
hooks存放hook脚本 

HEAD ：只是目前被检出的分支
index ：暂存区信息
objects/ ：存储所有数据内容，即下述数据对象
refs/ ：存储提交对象的指针（引用）

## 数据对象

这种以hash为key,内容为value的最基本对象，叫blob对象；但是由于blob没有文件名信息，且每个版本文件的hash都要保存的话就不太现实，故而需要额外的记录方式，即tree对象；同样不同版本的tree的hash也很多，tree的一些额外信息也需要记录，便有了commit对象。还存在一种tag对象，他一般指向commit对象

### 对象格式

>头部信息：以对象类型作为开头（如blob），再添加一个空格，随后是数据内容的长度，最后是一个空字节（null byte）

将头部信息和原始数据拼接起来，得到初始内容。
key即初始内容的 SHA-1 校验和
value即通过 zlib 压缩初始内容后的结果

存储目录：SHA-1 值的前两个字符作为子目录名称，后 38 个字符则作为子目录内文件的名称

### 底层命令

底层（plumbing）：这些命令被设计成能以 UNIX 命令行的风格连接在一起，亦或藉由脚本调用
高层（porcelain）：那些更友好的命令

```
#底层命令，存储查询对象
$ echo 'test content' | git hash-object -w --stdin #同下
$ git hash-object -w [fileName] #存储对象,会返回hash
$ git cat-file -p [hash] #获取内容
$ git cat-file -t [hash] #获取类型
$ git cat-file -p master^{tree} # master 分支上最新的提交所指向的树对象
```

文件模式：
100644，表明这是一个普通文件。
100755，表示一个可执行文件；
120000，表示一个符号链接

```
$ git update-index --add --cacheinfo 100644 \ 
   <wholeHash> <fileName> #创建一个暂存区
$ git write-tree #将暂存区对象写入树
$ git read-tree --prefix=bak <wholeHash> #将树读入暂存区（再重新写的话，可以作为当前树的子树）
```

```
$ echo 'first commit' | git commit-tree <treeHash> [-p parentCommitHash] #创建commit
```

## 引用

```
$ echo "wholeHash" > .git/refs/heads/master #对commit创建引用  --- 不建议使用
$ git update-ref refs/heads/master wholeHash #更安全的更新引用
```

### HEAD引用

HEAD 文件是一个符号引用（symbolic reference），它并不像普通引用那样包含一个 SHA-1 值，而是一个指向其他引用的指针

```
$  git symbolic-ref HEAD #查看
$  git symbolic-ref HEAD refs/heads/test #可以修改.git/HEAD 文件的内容，这样更安全；格式必须以refs/开头
```

## 标签引用

```
$ git update-ref refs/tags/v1.0 wholeHash # 类似的创建方式
```

## 远程引用

## 包文件

如果git库只有文件A，此时修改A的一小行数据并提交；你会发现git库的大小接近两个A

Git 最初向磁盘中存储对象时所使用的格式被称为“松散（loose）”对象格式。 但是，Git 会时不时地将多个这些对象打包成一个称为“包文件（packfile）”的二进制文件，以节省空间和提高效率。 当版本库中有太多的松散对象，或者你手动执行 git gc 命令，或者你向远程服务器执行推送时，Git 都会这样做。有趣的地方在于，文件新版本完整保存了文件内容，而原始的版本反而是以差异方式保存的

打包之后是dangling文件、新创建的包文件和索引文件。dangling是从没添加至任何提交记录中的文件； 包文件包含了刚才从文件系统中移除的所有对象的内容；索引文件包含了包文件的偏移信息，

```
$ git gc #主动打包
$ git verify-pack -v .git/objects/pack/pack-978e03944f5c581011e6998cd0e9e30000905586.idx #查看打包内容
```

## refspec

添加远程版本库后会在你的 .git/config 文件中添加一个小节，并在其中指定远程版本库的名称（origin）、URL 和一个用于获取操作的引用规格（refspec）：

```
[remote "origin"]
	url = https://github.com/schacon/simplegit-progit
	fetch = +refs/heads/*:refs/remotes/origin/*
        fetch = ....   #可以配置两份以上
        push = refs/heads/master:refs/heads/qa/master
```

引用规格的格式由一个可选的 + 号和紧随其后的 <src>:<dst> 组成，其中 <src> 是一个模式（pattern），代表远程版本库中的引用；<dst> 是那些远程引用在本地所对应的位置。 + 号告诉 Git 即使在不能快进的情况下也要（强制）更新引用。

git 会把 [refs/][remotes/]origin/master 拓展成 refs/remotes/origin/master

```
fetch = +refs/heads/master:refs/remotes/origin/master #只更新master
```

## 传输协议

版本库之间传输数据主要有两种：“哑（dumb）”协议和“智能（smart）”协议
哑协议: 一般用于基于 HTTP 协议的只读版本库。在传输过程中，服务端不需要有针对 Git 特有的代码；抓取过程是一系列 HTTP 的 GET 请求

##拉取仓库的传输步骤

```
$ git clone http://server/simplegit-progit.git
```

- 拉取 info/refs ，使用 update-server-info （所以HTTP传输时，必须把它设置为 post-receive 钩子）
    ...

## 回收

大约需要 7000 个以上的松散对象或超过 50 个的包文件才能让 Git 启动一次真正的 gc 命令。 你可以通过修改 gc.auto 与 gc.autopacklimit 的设置来改动这些数值。

因为打包的存在。为了获得指定引用的正确 SHA-1 值，Git 会首先在 refs 目录中查找指定的引用，然后再到 packed-refs 文件中查找
注意这个文件的最后一行，它会以 ^ 开头。 这个符号表示它上一行的标签是附注标签，^ 所在的那一行是附注标签指向的那个提交。

```
$ git log -g # 详细reflog
$ git fsck --full #查看没有被引用的对象记录
$ git count-objects -v #查看对象统计数据
```

### 最大的文件的移除

```
$ git verify-pack -v .git/objects/pack/pack-29…69.idx \   #
  | sort -k 3 -n \
  | tail -3
$ git rev-list --objects --all | grep 82c99a3 #查看对象信息

#查看文件相關日志
$ git log --oneline --branches -- git.tgz
#對所有日志進行重寫
$ git filter-branch --index-filter \
  'git rm --ignore-unmatch --cached git.tgz' -- 7b30847^..
#刪除引用
$ rm -Rf .git/refs/original  
$ rm -Rf .git/logs/
#徹底刪除對象
$ git prune --expire now
$ git count-objects -v
```

## 環境變量

https://git-scm.com/book/zh/v2/Git-%E5%86%85%E9%83%A8%E5%8E%9F%E7%90%86-%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F

gitk, git citool


整合一下，git提交会产生一个引用树对象的commit对象，而这个树对象引用了文件对象。  
而这些对象在git数据库中，都是以对象各自的hash值为key保存的  
多个commit对象构成列表，即commit对象之间又会有指向关系。
branch实质上是指向某个commit的指针，所以branch的创建比较方便，创建一个指针即可
而为了区分当前所在的branch，增加了HEAD的概念，也是一个指针，表示当前所在位置。

术语：author表示最开始的工作者，commiter表示最后的工作者
track,追踪；index\stage，暂存；commit


分支引用，tag引用。commit引用。日志引用

fetch和pull的区别： pull = fetch + merge  
fetch把数据存到仓库，merge则把仓库里的分支信息和文件（暂存和工作区）部分的内容合并。  

tag,commit的区别：tag引用不会随提交一起前进，所以如果在tag分支进行提交，HEAD会变成detached状态，此时可以用checkout -b检出到常规分支。


merge的理解：以在master分支合入aB_2为例  

这里很有意思，8bf8670到3bea55b的路径是标红的，而其他部分是绿的  
是不是恰好表明了merge的行为路径?

* 3bea55b (HEAD -> master) Merge branch 'aB_2'
    |\
    | * 82fc78a (aB_2) jhj
    | * 724f855 sf

* | 8bf8670 sdfsfd

* | 8720e0c dsfsd
    |/

* 49a5f62 sddfggf

    

HEAD：指向当前分支最后一次提交
index：包含HEAD的快照和stage的部分  

## 问题

### 2、远程仓库概念

**场景**
在本地文件夹A下创建一个git仓库，如TestGit，使用命令clone到本地另一个文件夹下TestGit (clone)，此时用后者查询 git remote -v，可以看到远程仓库是前者。但是为什么从前者查询，远程仓库也是后者？



- 略过章4，配置服务器  
- 掠过章5，工作流  
- 掠过章6，Github  
- 略过章9，集成Git(及附录A1,A2)
- 略过附录A3，命令大致说明和介绍索引

git rev-parse topic1

### stage和working修改原理

https://git-scm.com/book/zh/v2/Git-%E5%B7%A5%E5%85%B7-%E9%87%8D%E7%BD%AE%E6%8F%AD%E5%AF%86  

head指向的snapshot是一个备份，
index部分是一个备份，
working directory是一个备份。

index相对于head的修改是stage的部分
working directory相对于index的修改是working的部分

add是把working directory部分覆盖到index，commit是把index部分覆盖到snapshot  

reset是先移动head到某个commit(soft到此为止)，然后把snapshot覆盖到index(mixed到此为止),然后把index覆盖到working directory(hard)

checkout会把snapshot覆盖到working,index；

checkout相对reset会做一些修改检查；且执行命令时（并未指定文件路径的情况），reset会移动分支指针和HEAD指针，而checkout只会移动HEAD；指定文件路径的话，不会移动任何指针，只是针对文件的覆盖



