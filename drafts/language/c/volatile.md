# volatile

```c
if( value ){
    printf( "True\n" );
}else{
    printf( "False\n" );
}
if( value ){
    printf( "True\n" );
}else{
    printf( "False\n" );
```

如果在第二个if之前，value被信号处理函数异步修改，那么应该产生不同的结果。  

可是如果value没有被声明成volatile , 那么代码段可能被优化为：

```c
if( value ){
    printf( "True\n" );
    printf( "True\n" );
}else{
    printf( "False\n" );
    printf( "False\n" );
}
```

既可能出现非预期的结果  



