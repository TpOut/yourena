# 递归

标准没有说递归需要用堆栈实现，但是很多编译器都是这样   

逆序实现上使用递归比较合适。

### 尾部递归

阶乘和斐波那契数并不适合递归，前者无好处，后者效率很低。

#### 阶乘

```c
//阶乘递归
long
factorial(int n){
    if(n <= 0)
        retrun 1;
    else
        return n * factorial(n - 1);
}
```

如果仔细观察实现的递归函数，发现递归调用的时机是函数的最后，此时可以用一种循环实现：

```c
//阶乘迭代
long 
factorial(int n){
    int result = 1;
    while(n > 1){
        result *= n;
        n -= 1;
    }
    return result;
}
```

#### 斐波那契

```c
//斐波那契递归
long 
fibonacci(int n){
    if(n <= 2)
        return 1;
        
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

```c
//斐波那契迭代
long 
fibonacci(int n){
    long result;
    long previous_result;
    long next_older_result;
    
    result = previous_result = 1;
    while(n > 2){
        n -= 1;
        next_older_result = previous_result;
        previous_result = result;
        result = previous_result + next_older_result;
    }
    return result;
}
```

甚至斐波那契已经可以用公式表示了：[https://blog.paulhankin.net/fibonacci/](https://blog.paulhankin.net/fibonacci/)

