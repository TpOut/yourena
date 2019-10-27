作用于声明，表示该声明不可修改其对应的值

（const double ar[] 被解释成 const double *ar）  



非类中 const 相当于 c 中的const static（这里的static 表示内部链接性，但类中又有 static const，其中的static 表示静态存储。那这样子在翻译的时候会不会出现问题呢？）

这是为了方便将const 写在头文件中设置的

可以用extern 修改成外部链接性



