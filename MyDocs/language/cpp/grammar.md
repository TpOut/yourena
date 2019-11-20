

```c++
//C++ 中for循环的 condtion 只要是表达式语句即可
//现在的for 规则如下，可以不管，原文p130
for(for-init-statement condition ; expression){
    statement;
}
//foreach, c++ 11
for(double x : prices) 
for(double &x : prices)
for(double x : {1.0, 2.0, 3.0})
```



如果选项超过2个，在switch 支持的情况下，应当优先使用switch 而不是 if else