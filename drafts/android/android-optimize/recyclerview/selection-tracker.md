## 基本使用
参考链接：   
http://androidkt.com/recyclerview-selection-28-0-0/    
https://medium.com/@Dalvin/android-recycler-view-with-multiple-item-selections-b2af90eb5825

相关链接：
[自己实现helper的大佬](https://medium.com/@BladeCoder/implementing-a-modal-selection-helper-for-recyclerview-1e888b4cd5b9)  


## term

st : SelectionTracker  
rv : RecyclerView  
sosi：set of selected items

## SelectionTracker  
提供对rv管理sosi的支持。  
Builder模式  

### selection  
对于sosi，每个选项需要一个对应的唯一key来进行存储，存储后用类Selection来管理。  
Selection对象会一个存储 主要sosi，和一个临时sosi。  

主要sosi　和用户的已选择项保持统一，即已选择项的集合　　
临时sosi　则用来存储临时的处理，比如ｓｈｉｔｆ多选，或者长按取消等附加性操作　　

而一个选项是否已经存储，会从两个sosi中进行查询。  

对于临时sosi，因为有添加和删除操作，对应存储的时候需要处理。　　
```
setProvisionalSelection();  
```
对于临时项，新添加的临时项里没有且已选中的项中没有，那么需要删除  
对于已选择选，如果新的临时项里没有，那么需要删除  
对于新的临时项，如果既不在主要sosi里，也不在临时sosi里，则需要添加
最后对临时项进行添加删除操作。  
（对于临时项的操作有些奇怪）  

延伸：  
MutableSelection 里说，要`@see android.text.Selection`，不知道干嘛的？  

### StorageStrategy  
对于唯一的key，所支持的类型由 StorageStrategy 实现。  
因为这个类主要用来对生命周期相关的存储进行处理，即讲对象转换成bundle。  
默认实现有long,string,parcelable    

### SelectionPredicate  
现在知道了选中的部分，就要对其进行过滤。  

过滤比较神奇，通过key或者position，找到对应的选项，来控制其是否可以变化。  

### ItemKeyProvider
而key和position之间的转化，用ItemKeyProvider 来实现。  

其中可以设置作用范围，
1、不管数据是否已经和view绑定，都需要进行处理，比如有些时候，你选中A需要连带选中B  
2、只处理和view绑定的数据  

### ActionMode  
完全没看明白干嘛用的。。  
