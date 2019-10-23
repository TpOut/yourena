```c++
//输出到文件, ofstream 是ostream 的子类，即支持cout 的各种方法
ofstream outFile;
outFile.open("out.txt");
outFile << 11; // 写入11
outFile.close();

//读取文件, ifstream 是 istream 的子类，即支持cin 的各种方法
ifstream inFile；
inFile.open("in.txt");
if(!inFile.is_open()){ //老版本没有这个方法
  
}
while(inFile >> value){ //检测bool 时，inFile 的结果就是inFile.good()
  
}
if(inFile.eof()){
  
}else if(inFile.fail()){
  
}else{
  cout << "unknown reason";
}
inFile >> value;
inFile.getline(line, 81);
```



