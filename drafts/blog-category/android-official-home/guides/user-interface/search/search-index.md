关键要点提取：
1、The QUERY string is always included with the ACTION_SEARCH intent.
2、dialog的监听一般使用onDismissListener,它会在对话框消失的时候调用。而onCancelListener则只在用户关闭的时候才调用。（比如此文中，SearchBox中点击搜索，该搜索对话框消失，并不会调用后者）


20170710
# 搜索概览
搜索的实现有两种：
- 一种是系统级的search dialog，目前就是指Quick Search Box(后文写成SearchBox)
- 一种是内嵌widget，目前就是指SearchView（后文写成SearchView）  

两者都有的功能：
- 启用语音搜索
- 支持基于最近搜索记录的提示
- 支持自定义搜索提示策略
- 将搜索提示放入系统级的SearchBox

## 使用哪个
其实在3.0以后，已经推荐使用SearchView了，并且推荐放在app bar里。。

## 保护用户隐私
原则有二：
- 不要发送私人信息到服务器，如果实在有必要，则不要用日志打印它，如果一定要打印，则注意‘保护’并且尽快删除
`私人信息定义：姓名，邮件地址，账单以及其他可以暴露这些信息的数据`
- 提供删除记录的方式

# 基本知识
- SearchDialog是Android系统控制的UI组件，被用户激活的时候，从屏幕最上方出现。在用户点击搜索的时候，根据搜索内容和配置好的activity进行分发。
- SearchView，默认情况下，表现有点类似的EditText。但是你可以通过配置它来处理更多的搜索需求。

这里的搜索实现都是将query（查询的数据）封装进intent，然后传递给searchable activity，所需配置：
- searchable 配置：`一个XML文件`
- searchable activity：点击搜索之后要打开的activity
- 搜索入口，即SearchDialog或者SearchView

## searchable xml 配置
需要一个xml文件放置在res/xml/目录下，系统通过该文件实例化SearchableInfo对象（我们没法在运行时创建）
该xml必须包含<searchable>标签，必须有指向app名的label属性，例子：
```
<?xml version="1.0" encoding="utf-8"?>
<searchable xmlns:android="http://schemas.android.com/apk/res/android"
    android:label="@string/app_label"
    android:hint="@string/search_hint" >
</searchable>
```
然而在你开启SearchBox的搜索建议之前，是看不到label的提示的。  
如果是在SearchBox中，hint的格式最好让用户看明白，比如“搜索歌曲和词”，“搜索油管”

## searchable activity配置
如上文所述，打开activity的intent会有一个ACITON_SEARCH，然后content.Intent.getStringExtra() 时通过键值QUERY（query）获取传入的搜索关键字。
而activity要被打开，需要配置举例如下：
```
<application ... >
    <activity android:name=".SearchableActivity" >
        <intent-filter>
            <!--固定-->
            <action android:name="android.intent.action.SEARCH" />
        </intent-filter>
        <!--指向自定义的searchable xml文件-->
        <meta-data android:name="android.app.searchable"
                   android:resource="@xml/searchable"/>
    </activity>
    ...
</application>
```
此时的activity不需要DEFAULT category的属性，因为系统的分发是通过component name找的

## 实现搜索
步骤有三：
- 接收query
- 搜索数据
- 展现结果

### 接收query
```
    Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      doMySearch(query);
    }
```

### 搜索数据

如果是用SQLite存储数据，建议使用fts3，比LIKE更有效（但是搜索模式有些问题，可以自行查询一下）  
如果使用网络查询，记得添加进度条来增加用户体验

### 展现结果
不管什么方式，你喜欢就好

## 使用SearchBox

必要条件是配置searchable activity 
而如果你的searchable activity是一个单独的activity，那么需要配置开启activity，让他指向searchable act
例如：
```
<application ... >
    <!-- this is the searchable activity; it performs searches -->
    <activity android:name=".SearchableActivity" >
        <intent-filter>
            <action android:name="android.intent.action.SEARCH" />
        </intent-filter>
        <meta-data android:name="android.app.searchable"
                   android:resource="@xml/searchable"/>
    </activity>

    <!-- this activity enables the search dialog to initiate searches
         in the SearchableActivity -->
    <activity android:name=".OtherActivity" ... >
        <!-- enable the search dialog to send searches to SearchableActivity -->
        <!-- 该标签可以放到application内 -->
        <meta-data android:name="android.app.default_searchable"
                   android:value=".SearchableActivity" />
    </activity>
    ...
</application>
```
### 调用SearchBox
然后调用onSearchRequested() 
### 对activity生命周期的影响
dialog被调用的时候，activity只是失去焦点，甚至不会进入onPause，
如果你想处理相关的操作，可以重写如下：
```
@Override
public boolean onSearchRequested() {
    pauseSomeStuff();
    return super.onSearchRequested();
}
```

如果当前activity不是searchable的，那么没啥问题
如果是的，会产生两个实例，所以推荐设置launchMode为“singleTop”
	
### 传递用于查询的更多context数据

重写onSearchRequested()并且用startSearch()来激活dialog
```
@Override
public boolean onSearchRequested() {
     Bundle appData = new Bundle();
     appData.putBoolean(SearchableActivity.JARGON, true);
     startSearch(null, false, appData, false);
     //表示你处理了这个方法
     return true;
 }
```
NOTE：不要在onSearchRequested()方法外调用startSearch()

在searchable中提取：
```
Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
if (appData != null) {
    boolean jargon = appData.getBoolean(SearchableActivity.JARGON);
}
```
## 使用SearchView
### 配置SearchView
如SearchDialog，配置了searchabel的xml和activity之后，
需要为每一个SearchView设置setSearchableInfo()来添加搜索，示例代码如下：  
```
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the options menu from XML
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.options_menu, menu);

    // Get the SearchView and set the searchable configuration
    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
    // Assumes current activity is the searchable activity
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

    return true;
}

```

### 其他要素
 setSubmitButtonEnabled(true).setQueryRefinementEnabled(true).setIconified().

###　同时使用widget和dialog
如果屏幕实在太小，searchView只能使用icon模式，然后就需要结合使用searchDialog


## 添加语音搜索
这个好像没有添加成功
语音的转换是直接进行的，即searchabl界面可能接收到错误的转换后语句
所以慎重考虑使用场景
```
<?xml version="1.0" encoding="utf-8"?>
<searchable xmlns:android="http://schemas.android.com/apk/res/android"
    android:label="@string/search_label"
    android:hint="@string/search_hint"
    android:voiceSearchMode="showVoiceSearchButton|launchRecognizer" >
</searchable>
```
# 添加搜索建议
## 基础
基于 SearchRecentSuggestionsProvider创建一个provider并且注册在manifest中
在xml中添加先关配置
在每次搜索时保存

查询步骤就是：系统获悉provider，使用query进行查询，返回一个cursor，放入下拉列表

基本需求：
无论怎么查询，数据都是存入action为ACTION_SEARCH的intent中
1、实时更改。2、点击搜索时，以输入框内容为准。3、点击建议时，直接搜索

## 创建provider

示例：
```
public class MySuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.MySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public MySuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
```
## 修改配置文件
```
<?xml version="1.0" encoding="utf-8"?>
<searchable xmlns:android="http://schemas.android.com/apk/res/android"
    android:label="@string/app_label"
    android:hint="@string/search_hint"
    android:searchSuggestAuthority="com.example.MySuggestionProvider"
    android:searchSuggestSelection=" ?" >
</searchable>
```

searchSuggestAuthority需要和上面provider定义的完全相同
searchSuggestSelection需要是一个问号（SQLite的占位符）

## 保存查询
每次查询的时候调用SearchRecentSuggestions实例的saveRecentQuery
```
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Intent intent  = getIntent();

    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
        String query = intent.getStringExtra(SearchManager.QUERY);
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
        suggestions.saveRecentQuery(query, null);
    }
}
```
## 清除建议数据
需要提供一个按钮来让用户点击执行清除

```
SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
        HelloSuggestionProvider.AUTHORITY, HelloSuggestionProvider.MODE);
suggestions.clearHistory();
```

# 添加自定义建议

## 基本
自定义的时候，可以把ACTION_SEARCH换成任何其他的action。
所需如下：
1、搜素入口；2、配置；3、建立一张有固定格式的表；4、创建相应的provider；5、定义需要传递的intent

行为定义和最近搜索建议的类似。只是在点击建议选项的时候，传递的是自定义的action

## 配置searchable xml
```
<?xml version="1.0" encoding="utf-8"?>
<searchable xmlns:android="http://schemas.android.com/apk/res/android"
    android:label="@string/app_label"
    android:hint="@string/search_hint"
    android:searchSuggestAuthority="com.example.MyCustomSuggestionProvider">
</searchable>
```
## 创建provider
建议的框架从provider的query()方法返回的cursor中获取数据

### 处理建议query
query的uri格式如下：
`content://your.authority/optional.suggest.path/SUGGEST_URI_PATH_QUERY`
query text的uri如下：
`content://your.authority/optional.suggest.path/SUGGEST_URI_PATH_QUERY/puppies`


optional.suggest.path的路径来自xml中的 android:searchSuggestPath，只有在拥有多个searchable activity的时候才需要这个属性来做区分。


projection:空
selection：xml中的android:searchSuggestSelection，无则为空
selectionArgs：有且仅有query text，如果定义了android:searchSuggestSelection；否则为空
sortOrder：为空

### 从uri获取query
```
String query = uri.getLastPathSegment().toLowerCase();
```
### 从selection 参数获取query

```
<?xml version="1.0" encoding="utf-8"?>
<searchable xmlns:android="http://schemas.android.com/apk/res/android"
    android:label="@string/app_label"
    android:hint="@string/search_hint"
    android:searchSuggestAuthority="com.example.MyCustomSuggestionProvider"
    android:searchSuggestIntentAction="android.intent.action.VIEW"
    android:searchSuggestSelection="word MATCH ?">
</searchable>
```
### 创建一个建议表
需求列名：

- _ID  
- SUGGEST_COLUMN_TEXT_1

其他诸如第二文本列、第一图标列等等，都是可选项

### 创建一个intent

一般而言，最常见的自定义action是ACTION_VIEW
你可以如上在xml中定义，也可以在表中SUGGEST_COLUMN_INTENT_ACTION列定义

或者结合起来，默认的在xml中（因为是全局的）；而表中定义特别的

### 创建intent中的数据

在表列SUGGEST_COLUMN_INTENT_DATA中定义

或者xml的android:searchSuggestintentData 中定义

## 处理intent
```
Intent intent = getIntent();
if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
    // Handle the normal search query case
    String query = intent.getStringExtra(SearchManager.QUERY);
    doSearch(query);
} else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
    // Handle a suggestions click (because the suggestions all use ACTION_VIEW)
    Uri data = intent.getData();
    showResult(data);
}
```
## 重写query text
如果用户使用方向键或者轨迹球来滑动列表，此时需要获取列表当前聚焦的那个建议选项。并对

## 暴露搜索建议给SearchBox
xml中配置即可
```
android:includeInGlobalSearch = true
```

···
<provider android:name="MySuggestionProvider"
          android:authorities="com.example.MyCustomSuggestionProvider"
          android:readPermission="com.example.provider.READ_MY_DATA"
          android:writePermission="com.example.provider.WRITE_MY_DATA">
  <path-permission android:pathPrefix="/search_suggest_query"
                   android:readPermission="android.permission.GLOBAL_SEARCH" />
</provider>
···

### 用户设置允许（我手机上已经找不到这个选项了）
Settings--Search
























