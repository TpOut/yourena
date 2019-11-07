从p754开始



```c++
while(cin) //cin会忽略空白符, 如果输入错误或者EOF，才会为false

while(cin.get(ch)) ;  //cin.get(ch) 不会忽略空白符

cin.get() //类似c 的getchar

cin.get() != EOF //注意char 的系统区别，所以EOF 需要声明成int， 使用时再转成char

cin >> hex;
cin.getline() //读取行；读取并丢弃换行符
   .get(name, 20) //读取行，作为name；读取但不丢弃换行符
   .get()  //读取下一个字符，即换行符
   .get(height, 20)  //读取行
  
//类似上面，第一步会产生一个换行符，导致如果直接用getLine 会获取直接结束，需要先get 一下
(cin >> some).get().getline()
  
//cin 在检测到EOF 后，会设置eofbit 和failbit 为1；错误时设置failbit
cin.eof() //对应eofbit
cin.fail() //对应 eofbit 或failbit 
cin.clear() //需要清除才能继续读取
```



C++设计之初，并没有字符串，所以`cin.getline()` 并不支持字符串类型，（好奇为何不在类里添加，设计原则？）

后来在string 中添加友元函数，`getline(cin, str)`



```c++
int input ;
cin >> input;
cout << input;
//如果输入 字符，此时cin 会产生错误，而有些环境，会直接错误，不执行输出
//此时可以插入一段
if(!(cin >> input)){
  cin.clear(); //清除错误标记
  while(cin.get() != '\n'){  //错误的输入会被缓存，需要忽略掉
    continue;
  }
  cout << "pls, correct input! ";
}
```

```c++
try{
  while(cin >> input)
  {
    sum += input;
  }
}catch(ios_base::failure & bf){
  cout << bf.what();
}
```











