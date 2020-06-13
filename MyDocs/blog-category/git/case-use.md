1、刚加了个密码配置文件 ignored，要忽略：

```markdown
.gitignore 中包含 `**/ignored`  ，`**` 表示匹配任意路径
```

2、写好了需求，结果后台说这版本上不了：

```shell
git revert
# 下个版本做好了
git log --oneline | grep "revert"
git revert "commitHash"
```

3、又到了一周写周报的时间：

```shell
git log --after "2020-05-22 00:00:00" | grep -B 2 -A 3 Fred
```

4、一坨临时本地日志，改成一次提交：

```shell
git rebase -i HEAD~3
# 或者reset 比较直白
```

5、amend 改了日志，没有log 了：

```shell
git reflog
```

6、很久以前写了个bug，还不知道哪里出了问题：

```shell
git bisect
```

7、刚接手项目，原先的大佬把配置文件都加入git：

```shell
git filter-branch —tree-filter ‘rm -f profile.txt’ HEAD
# 这命令还可以批量改邮箱
```

8、有些分支合并过的冲突，需要再次合并：

```shell
git rerere 
# 需要开启
```

9、需求写到一半，有个很急的bug 要改：

```shell
git worktree
# git stash 不够优秀
# 但是Android 这种比较依赖ide 的，目录变化了也不太好
```



