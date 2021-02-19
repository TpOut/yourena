RecyclerView 就是一个ViewGroup  

为了复用管理，使用`ViewHolder`  

为了简便抽象，使用`Adapter`  

- ConcatAdapter  
- ListAdapter  

甚至布局方式也可以修改，`LayoutManager`  

- LinearLayoutManager 单维度
- GridLayoutManager 双维度（第二维度约束  
- StaggeredGridLayoutManager  双维度（第二维度无约束  

而布局的改变，也可以定义动画`ItemAnimator`    

同时提供了DiffUtil 用于方便计算（ListAdapter，AsyncListDiffer      

SortedList  



但是刚出有个明显的缺点，就是不像listView 一样，很方便的添加触摸、点击事件。  

官方有一个`selection` 配套库，但是极其难用  













