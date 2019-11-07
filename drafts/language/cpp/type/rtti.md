#### RTTI(Runtime Type Identification)

只适用于包含虚函数的类



dynamic_cast 运算符，转化失败返回空指针

typeid 运算符，返回一个指出对象的类型的值？

type_info 结构存储了有关特定类型的信息

```c++
if(Lsj * lsj = dynamic_cast<Lsj *> baseLsj){};
//引用类似，但是无空引用，直接引发异常
try{
  Lsj &lsj = dynamic_casy<Lsj &>(baseLsj);
}catch(bad_cast &){}
```

```c++
try{
  typeinfo info = typeid(*lsj); //lsj为空直接异常，typeinfo 随厂商实现而不同
}catch(bad_typeid){}
typeid(Lsj) == typeid(*lsj)
```