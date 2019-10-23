#### const 指针

基本的两种方式，可以看c 部分回顾一下

**升级**：

```c++
//首先
const float some = 9.8;
const float *pSome = &some; //必须是const *，否则讲不通；当然可以强转

//一级间接关系（即一级指针），没问题
int age = 39;
int * pd = &age;
const int * pt = pd; //非const 赋值给 const
//二级指针，会产生混乱，所以干脆不允许
const int **pp2;
int * p1;
const int n = 13;
pp2 = &p1; //非const 赋值给const
*pp2 = &n; //即p1 指向n 的地址
*p1 = 10; //尝试修改n 了，讲不通
```