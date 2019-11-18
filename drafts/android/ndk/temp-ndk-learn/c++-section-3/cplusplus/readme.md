## 知识点
   ==basic==
   面向对象初步理解
   引用
   可变参数（复习）
   对象大小计算
   三大函数（构造、析构、拷贝构造函数）
   友元类 友元函数
   
   继承 多态的理解
   虚函数
   模版
   ==api== 
   对比的学习
   Java
   list hashmap 多并发 set sort（binary stack 冒泡、堆排、二分）
   // 数据容器  算法  迭代（数据 3种 正序 倒序 const） 
   stl vetor map queue dequeu C++ 集合 操作数据 string string[] type[]
   jni 异常 Java c Linux  cmake make
   
   
   1 为了看懂最新的开源框架的代码  比如matrix
   ~~~
   class IOCanary {
       public:
           IOCanary(const IOCanary&) = delete;
           IOCanary& operator=(IOCanary const&) = delete;
   
           static IOCanary& Get();
   
           void RegisterDetector(DetectorType type);
           void SetConfig(IOCanaryConfigKey key, long val);
           void SetJavaMainThreadId(long main_thread_id);
   
           void SetIssuedCallback(OnPublishIssueCallback issued_callback);
   
           void OnOpen(const char *pathname, int flags, mode_t mode, int open_ret, const JavaContext& java_context);
           void OnRead(int fd, const void *buf, size_t size, ssize_t read_ret, long read_cost);
           void OnWrite(int fd, const void *buf, size_t size, ssize_t write_ret, long write_cost);
           void OnClose(int fd, int close_ret);
   
       private:
           IOCanary();
           ~IOCanary();
   
           void OfferFileIOInfo(std::shared_ptr<IOInfo> file_io_info);
           int TakeFileIOInfo(std::shared_ptr<IOInfo>& file_io_info);
           void Detect();
   
           bool exit_;
   
           IOCanaryEnv env_;
           OnPublishIssueCallback issued_callback_;
           std::vector<FileIODetector*> detectors_;
   
           IOInfoCollector collector_;
           std::deque<std::shared_ptr<IOInfo>> queue_;
           std::mutex queue_mutex_;
           std::condition_variable queue_cv_;
       };
   
   };
   ~~~
   
   
   ~~~
   template<class _Traits>
   basic_ostream<char, _Traits>&
   operator<<(basic_ostream<char, _Traits>& __os, const char* __str)
   {
       return _VSTD::__put_character_sequence(__os, __str, _Traits::length(__str));
   }

   ~~~
   
##类型转换
static_cast 基本数据类型的转换


## 参考资料
http://www.hawstein.com/posts/lru-cache-impl.html
https://github.com/HongYiMU/TheAlgorithm/blob/master/STL%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90%E7%AE%80%E4%BD%93%E4%B8%AD%E6%96%87%E5%AE%8C%E6%95%B4%E7%89%88(%E6%B8%85%E6%99%B0%E6%89%AB%E6%8F%8F%E5%B8%A6%E7%9B%AE%E5%BD%95).pdf