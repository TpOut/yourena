- 应该使用Preference类来构建，而不是使用View对象，主要是为了统一用户体验。。。
- Preference 对象是单个设置的构建基块，已经封装好了对应用默认SharedPreferences文件的存储和读取
SharedPreferences支持的类型有：boolean，float，int，long，String，String Set
- 3.0之前或者想创建双窗口，可以使用PreferenceActivity作为基类
否则使用activity管理 PreferenceFragment即可
- 首选项支持运行时实例化，但是在ＸＭＬ中定义有可读性较强的优势，且符合预定义的一般情况
