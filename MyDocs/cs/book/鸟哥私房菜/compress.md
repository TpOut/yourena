常见的压缩格式解释

- .Z  compress 程序压缩的文件，使用znew 命令转化成gzip
- .zip  zip 程序
- .gz  gzip 程序
- .bz2  bzip2 程序
- .xz  zx 程序
- .tar  tar程序打包，无压缩
- .tar.gz  tar 打包，gzip 压缩
- .tar.bz2  tar 打包，bzip2 压缩
- .tar.xz  tar 打包，xz 压缩



压缩程序只能针对一个文件，所以使用tar 来进行打包



gzip 可以解压compress, zip

.gz 文件可以被rar/7zip 解压

`zcat` `zmore` `zless` `zgrep`读取纯文本被gzip压缩成的压缩文件。  



bzip2 为了取代gzip 并提供更佳的压缩比

`bzcat` ... ` bzgrep` 



xz 的压缩比则更强

`xzcat` ... `xzgrep`



tar.gz 文件可以被winrar 解压

```shell
# 简单记忆
tar -jcv -f fillname.tar.bz2 sourceFile  # 压缩 
tar -jtv -f filename.tar.bz2  # 查询
tar -jxv -f filename.tar.bz2 -C targetFile  # 解压缩，如果targetFile 是tar 中的一个文件，则只解压一个
```

tar 打包的文件叫做tarfile, 如果还进行了压缩就叫做tarball  



`xsfdump` `xfsrestore` 文件系统的完全备份
