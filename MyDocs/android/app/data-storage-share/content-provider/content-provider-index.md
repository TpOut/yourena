内容提供者，可以用于管理对app 自身或其他app 存储数据的访问，以及数据分享  

主要在实际的数据存储之上封装了一套安全统一的机制    

主要用作分享数据之用，如果不作分享，就没啥必要使用。  

场景：

- ~~自定义搜索建议~~ SearchRecentSuggestionsProvider
- 对widgets 暴露数据
- 复制黏贴复杂数据(通过uri，临时权限)
- ~~同步数据~~ AbstractThreadedSyncAdapter
- ~~CursorLoader~~



8.0支持分页，刷新  

访问其他app 数据时需要xml 申明权限

异步加载，使用intent 让提供数据的app 自行处理 



API:

`ContentProvider` and `ContentProviderClient`.

`ContentResolver` 支持CRUD  

```
ContentResolver.notifyChange()` and `registerContentObserver(Uri, boolean, ContentObserver)
```







