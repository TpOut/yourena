如果返回的数组数量为0，可以这么写 new elementType\[0\]，而不用Null来代替



**数组**

 当创建数组后， 它的元素被賦予默认值， 数值型基本数据类型的默认值为 0, char 型的 默认值为 '\u0000’， boolean 型的默认值为 false。

double\[\] myList; myList ={1.9, 2.9};

 Q1：参考正常的写法，这样为什么是错误的？ 

A1（暂时猜测）：简单来看，正常的初始化包括，new double\[2\], new double\[2\]{1.9, 2.9}, {1.9, 2.9}。由于myList是一个引用，可以很好的理解，前面两种new的方式是新建了一个对象并赋值给myList，而要判断第三种方式，是不是新建一个对象，就比较麻烦？

复制数组：System.arraycopy\(\)、clone\(\)、逐个循环复制

二维数组（如果不知道具体每个子数组的长度，可以这么创建，第一个维度值是必须的（这里是5））： int\[\]\[\] triangleArray = new int\[5\]\[\]; triangleArray\[0\] = new int\[5\]; triangleArray\[1\] = new int\[4\]; triangleArray\[2\] = new int\[3\]; triangleArray\[3\] = new int\[2\]; triangleArray\[4\] = new int\[1\];

