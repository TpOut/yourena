元运算符`...`

```c++
template<typename... Args>      //Args 是模板参数包（parameter pack），收集类型
void show_list(Args... args){}  //ars 是函数参数包，收集实参

//使用
template<typename T, typename... Args>
void show_list(T t, Args... args){
  std::cout << t << ",";
  show_list(args...);  //递归展开参数包，直到参数数量为0，自动停止。 show_list(null)?
}

//可以针对最后一项设置重载
void show_list(T t){...};

//设置展开模式（pattern）
...
void show_list(T t, const Args & ... args)
...
```

