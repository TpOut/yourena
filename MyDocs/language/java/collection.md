 在面向对象思想里，一种数据结构也被认为是一个容器（container）或者容器对象。

java合集框架支持以下两种类型的容器：  
一种是为了存储一个元素合集，简称为合集\(collection\) 另一种是为了存储键值对，称为映射表（map） collection包括：set, list, queue。他们都继承Collection接口，以期实现一些统一共用的功能

![collection&#x63A5;&#x53E3;&#x5B9A;&#x4E49;](https://upload-images.jianshu.io/upload_images/1936727-fa16d58113e69962.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Q1：Collection 接口的部分方法在子类中无法实现，会在子类中抛出异常。比如add方法，在AbstractCollection,AbstractList中均抛出异常UnsupportedOperationException，而在ArrayList中才实现 A1：如果子类不需要实现add方法--比如UnmodifiableCollection--那么就可以不去实现，且可以从父类来统一异常标准，避免子类一个个实现。

Q2：有空看下Spliterator



![2018424-154443.jpg](https://upload-images.jianshu.io/upload_images/1936727-6c2dd7104ce3969a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-cc89b676b9343295.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Q1：无序的add就不能叫做add吗？![2018424-160744.jpg](https://upload-images.jianshu.io/upload_images/1936727-0cdf6af5d2c51381.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Set&#x7ED3;&#x6784;.PNG](https://upload-images.jianshu.io/upload_images/1936727-181c679fdf30c038.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

HashSet不保证元素顺序，LinkedHashSet按插入顺序排序，TreeSet则会对元素进行排序



![Map.PNG](https://upload-images.jianshu.io/upload_images/1936727-81d1de252ab5bd67.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

不需要排序使用HashMap，按插入顺序或者访问顺序排序使用LinkedHashMap，按键值排序使用TreeMap

EnumSet,IdentityHashMap; System.IdentityHashCode\(Object obj)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-e6e99da503394309.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

RandomAccess接口用来标记是否支持高效的随机访问

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-3d9512520af4b485.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 

老版集合，vector,stack,hashTable,properties



![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-e78ddfebadd33ca3.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Collecitons.checkedList() 
keySet，视图

```text
String[] values = (String[]) s.toArray();//error
String[] values = s.toArray(new String[size]);
```

**遗留**  
hashtable,properties,vector,stack,bitset  
enumeration

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-77bb54afb1d34195.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

