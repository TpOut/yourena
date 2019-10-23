类的虚方法实现方式，通常是使用 指向 函数指针数组 的指针。



```c++
ClassType * pClass = new ClassType;
pClass -> field = (*pClass).field;
```

