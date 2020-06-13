/user/share/doc

the linux document project : http://www.tldp.org/

```  
//获取网页的返回数据
wget -qO- 127.0.0.1
```
https://github.com/dylanaraps/pure-bash-bible

终端颜色修改：https://github.com/Mayccoll/Gogh  
linux 编程学习：http://man7.org/

学习页面：https://linuxjourney.com  
linux服务器：https://medium.com/@mutendebrian/how-to-secure-your-linux-server-6026cfcdefd8  
linux服务：https://medium.com/@benmorel/creating-a-linux-service-with-systemd-611b5c8b91d6    

apt-aptget的区别：https://itsfoss.com/apt-vs-apt-get-difference/  

  [unix实现ide的功能选项](https://conanblog.me/Unix-as-IDE--Chinese-/index.html)         

  幂等bash脚本：https://arslan.io/2019/07/03/how-to-write-idempotent-bash-scripts/  
  bash alias:https://medium.com/@raimibinkarim/9-bash-aliases-to-make-your-life-easier-3e5855aa95fa  

  命令行查看图片工具：https://github.com/hackerb9/lsix   

  fzf\ripgrep:https://medium.com/@sidneyliebrand/how-fzf-and-ripgrep-improved-my-workflow-61c7ca212861  

unix操作文件命令：https://www.ibm.com/developerworks/aix/library/au-unixtext/index.html   
linux命令搜索：https://wangchujiang.com/linux-command/  

命令行工具增强：https://remysharp.com/2018/08/23/cli-improved    

linux内核源代码搜索：https://elixir.bootlin.com/linux/latest/source　　

syslog日志指南：https://devconnected.com/syslog-the-complete-system-administrator-guide/  

Linux系统教学：http://www.linuxfromscratch.org/

https://linux.cn/

shell 命令解释：https://www.explainshell.com/

shell 命令检查：https://www.shellcheck.net/

Curl ：https://catonmat.net/cookbooks/curl

lsb: https://www.linuxbase.org/lsb-cert/productdir.php?by_lsb

## fsearch
 github.

## awk  
对输出文本格式化   
教程：https://developer.ibm.com/tutorials/l-awk1/  

## tldr
提高man的可读性(github)  

## lsd
lsd : https://github.com/Peltoche/lsd  
exa : https://the.exa.website/  

## 内核  
linux内核揭秘：https://xinqiu.gitbooks.io/linux-insides-cn/content/index.html      
简单kernel:https://github.com/MRNIU/SimpleKernel  

## 命令  
### 文件结构

查看密码

```shell
/root/anaconda-ks.cfg
```

kickstart

查看硬件组件的型号

```
cat/proc/cpuinfo  
lspci   
```

内核版本
```
uname -r
```

脚本管理工具有很多，如   
Ansible, or Salt，shell scripts, Chef, or Puppet



不要把当前目录（.）放在PATH中，很容易误操作。而且进入公用区域时（如/tmp）可能操作恶意软件
