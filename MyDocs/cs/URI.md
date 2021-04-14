> 来自android 官方文档 URI 解释  



语法：  

```
[scheme:]scheme-specific-part[#fragment]
```

- absolute URI : 有一个scheme  
  - opaque URI  : 有一个scheme-specific-part 且不是以斜杠`/` 开头  

- relative URI : 非absolute  



除去opaque 的(absolute / relative) 合集，叫做 hierarchical URI ：必须有一个path（可为空）         

有更详细的语法：  

```
[scheme:][//authority][path][?query][#fragment]
```

- 其中authority 可以是server-based 或registry-based   

  前者的语法 ( 且是目前大部分的实现 )：  

  ```
  [user-info@]host[:port]
  ```

- 其中path 可以以`/` 开头，表示绝对路径

  如`file:///~/calendar`  

  > 这里会发现一个问题，因为也允许写法：`file:///~calendar`  
  >
  > 搜了一下https://www.npmjs.com/package/sass-loader  
  >
  > 大意应该是path 除了第一个字符，后续的字符其实没有那么严格，可以用于自定义解析  
  
  如果有authority，那么path 必须是绝对路径    



所有上面提到的变量名，有两种状态：定义 / 未定义，string 默认为 null，数字默认为 -1    

注意string 可以定义为空，和null 不一样  















