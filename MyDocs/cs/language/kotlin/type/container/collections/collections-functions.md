#### normal

```kotlin
plus / minus

isEmpty()
get()  
```

#### populate

部分数据做处理，结果是map collection 的时候，可以传递两个lambda，一个处理key，一个处理value

- transformation

  - map （mapIndexed, mapNotNull）

    对每个元素进行转换

  - zip

    对多个数据源的相同顺位数据进行合并

    > 目前好像只能是两个数据源，组合成Pair -- 当然嵌套也可以
    >
    > unzip 可以解pair  

  - associate   

    将元素映射成map collection，key 或value 并关联生成value 或key  

  - flatten  ( flatMap )

    平铺内部collection  

  - joinTo

    将每个元素join 为字符串  

- filter

  - filter  (filterIndexed, filterNot, filterIsInstance, filterNotNull)

    过滤，新数据只包含没被过滤的数据  

  - partition

    过滤，产生两个数据，一个保留的数据，一个是过滤掉的数据  

  - any / none / all

    如果传入判定语句作为参数，则返回是否有元素符合判定  

    如果只是单纯的空调用（any / none），那么就是判断集合数量
    
    > 对于空集，all 的任何判定语句都返回true 。基本定律：Vacuous truth

- group

  - groupby

    对数据分组  

  - groupingBy

    返回`Grouping` 对象

    > 可以对其做 eachCount / fold / reduce / aggregate 操作

- retrieve
  - part of
  
    - slice
  
      裁剪一段数据
  
    - take / drop  (takeWhile, dropLast)
  
      从头或者从尾获取一定数量的数据
  
    - chunked （可加表达式
  
      固定大小分割成多段数据
  
    - windowed （zipWithNext
  
      遍历步长，取固定大小的数据，组成新数据
  
  - single
  
    - elementAt ( elementAtOrElse
    - first / last ( find )
    - random ( Random )
    - contains ( containsAll  
  
- order

- aggregate



一般性的操作符，都是产生一个新的数据，如`filter`    

如果需要将处理结果，添加到指定的一个数据后面，有对应的操作符，如`filterTo`  



有些操作符，都是不对源数据产生“副作用”的，如`sorted`，返回新数据  

但是也会有"副作用"的，如`sort`，返回源数据   



