`24*5 + 22*5`是先执行前者，还是后者，取决于系统实现



sizeof

```c++
int num[] = {0,0,0}
sizeof(num) = 12; // 3 * int
sizeof(num[0]) = 4; //int
  
int *num = new int[3]
sizeof(*num) = 4; //即 num[0]，int
sizeof(num) = 8; //指针本身
```



```C++
++type ; type++
如果是基础类型，注意此时的返回值是type，而不是单纯的type + 1（会有数值转换的处理）
如果是自定义类型type， type++ 会先复制一个type，然后type+1，并返回；
比 ++type 的效率略低
  
//同时不要在一个表达式中多次使用递增、递减，会产生无法预期的结果
//c++ 不保证是每次使用递增就 +1，还是整个语句结束后 +n
```



