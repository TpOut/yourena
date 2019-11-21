迭代器，广义指针，iterator.begin, end,erase, insert, push_back

c++11 新增了cbegin, cend ... 对应每个方法的 const 版本 



 random_shuffle(随机排序), sort

```c++
//for_each 三参数可以写成
for(auto & x : books) InflateBooks(x) ;
```



p685, 章16.4.1 解释了迭代器的出现理念。

迭代器是泛型编程的通用桥梁

应尽量使用STL 函数（如for_each(), for循环），而不是直接操作迭代器



章16.4.2，迭代器类型：

能执行 *iterator 操作，能执行赋值，能进行相等比较（如果迭代器相同，则解除引用的值也应该相同）

输入迭代器：不修改容器内容，只是读取，能遍历所有元素（++操作），不保证每次的顺序，递增后不保证之前的解释引用的值和之前相同，单向，

输出迭代器：只修改内容，但是不能读取，单向（single-pass）

正向迭代器：默认可读写，用const可只读，保证便利顺序，保证每次的解释引用

双向迭代器：略...

随机访问迭代器：



迭代器类型是一种概念concept，概念上的“继承”实现叫做改进refinement，概念的具体实现叫做模型model

#### 常用

```c++
//输出拷贝到迭代器
copy(iterator.begin, iterator.end, ostream_iterator<int, char>(cout, " "));
//反向迭代器

//插入迭代器
back_insert_iterator, 插入尾部
front_insert_iterator, 插入前段
insert_iterator， 插入指定位置
```



#### 容器类别

deque， list， queue，prioirty_queue , stack , vector , map , multimap , set , multisitet , biset ,   

forward_list, unordered_map, un..multimap , un...set, un...multiset

（biset 在这里视为一种独立的类别）

在容器中类型必须是可复制构造和可赋值的（c++11 改进了概念，可复制插入CopyInsertabl和可移动插入MoveInsertable）

P695-697 讲述了一下容器的基本特征，以及序列sequence 的特征



vector 数组形式，deque 双端队列（比vector 的优势在于首部增删），list 双向链表（元素快速增删，c++11 forward_list），



关联容器（associative container），即map 等键值对查找

 Hash表，树



