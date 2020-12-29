- transformation

  - map （mapIndexed, mapNotNull）

    对每个元素进行转换

  - zip

    对多个数据源的相同顺位数据进行合并

    > 目前好像只能是两个数据源，组合成Pair -- 当然嵌套也可以
    >
    > unzip 可以解pair  

  - 

- filter

- plus / minus

- group

- retrieve
  - part of
  - single
  
- order

- aggregate



一般性的操作符，都是产生一个新的数据，如`filter`    

如果需要将处理结果，添加到指定的一个数据后面，有对应的操作符，如`filterTo`  



有些操作符，都是不对源数据产生“副作用”的，如`sorted`，返回新数据  

但是也会有"副作用"的，如`sort`，返回源数据   



