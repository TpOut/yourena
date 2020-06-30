## C第1节
面试点： bitmap, mmap   


   

内存对齐  
重新赋值的 int32_t 等等  
gcc -E temp.c 的时候会看到一些extern 的打印，具体是什么意思？就是单纯的添加了一下变量？

linux: file、size（简单查看二进制结构）  
ls /proc 可以查看进程  
ps 查看进程信息  
ls /proc/{pid} 可以看到一个fd文件夹，会有android fd 泄露问题
cat /proc/20051/maps 可以看到内存占用情况，补充资料 https://www.cnblogs.com/jiayy/p/3458076.html  
![每列的说明的](proc-pid-maps.png)

内存分类：  
1：栈堆  
2：全局（静态）区  
3：BBS段 --- 存放未初始化的全局变量  
4：文字常量区  
5：数据区  
6：代码区  
具体对应的代码，可以再查看文档或者重新搜索。。  
![内存模型](内存模型.png)

c中static,const 的作用总结,文件相关方法    
c99中引入了stdbool  

c的malloc, linux底层使用brs(sbrk)和mmap 实现    
mmkv也基于mmap实现  
malloc(0) 返回什么？

https://www.gnu.org/software/libc/
https://www.gnu.org/software/libc/manual/pdf/libc.pdf

android elf

ffmpeg, libavcodec, audio_frame_queue

## 第2节  
结构体，内存对齐：
1、结构体成员的起始地址是该成员大小的整数倍，如int的成员起始地址只能为4的倍数    
2、结构体的大小是最大成员大小的整数倍    
可以使用 #pragma pack(n) 进行压缩，gcc支持n是1,2,4    

ndk的 信号提示，在libc的 singal相关部分里可以找到  

cpp预处理器 preprocessor：  https://gcc.gnu.org/onlinedocs/cpp/


1:53:06 左右看源码的process.java，简单讲解了下相关的知识，看不懂，先记录  

## 第3节  
c++  可变参数 stdarg.h  === var_start, var_end, var_arg  
aes、des 加密
volatile  
线程的部分，关联性不大？？
字符串常用：strcpy\cat\chr\tok\cmp

## C++第1节  
1:19:00左右调用两次析构的分析有问题。  

1：26：51析构中释放字符串，但是没有效果//todo  

## 第2节
const_cast、static_cast、dynamic_cast、reintepret_cast、  

子类：使用父类的成员变量  --- using A:name  
子类：使用父类的成员函数  --- son.Father::func()  

类的成员方法是在文本区，

## 第3节  
算法命中率  
《stl 源码剖析》github 上有  

## jni 第1节  
拉回到主线程那张图，如果是绑定线程，就比较好理解

然后代码中的底层抛出异常部分有些不太理解

安全阻塞队列： pthread_mutex_destroy ; pthread_cond_destroy ; pthread_cond_signal 等等       

分析native内存  

深入理解java虚拟机，第6章，第3章  

可以获取当前so库路径的api？

jni加载.so文件的流程分析在57分钟左右，总结在1:31左右   
主要是runtime,system,java_vm_cxt 查看源码跳转，一个是源码就不一样？一个是跳转的逻辑还得学习下。其实感觉还是经验型的  


方法嵌套一个空的{}是什么作用？  

linux dlopen,  
ELF文件结构很关键  

堆外内存，DirectByteBuffer  

## 第2节  
sysroot/user/include  
bitmap.h  
增量更新：b_differ、rsync(有一篇论文: https://www.samba.org/~tridge/phd_thesis.pdf)  

photoBase64项目中， constraintLayout 不成功应该是库的问题？ 然后解码的时候，报bitmap错误，应该是长宽的问题？  
Huffman 算法  
luban 压缩也只是对采样率修改，使用bitmap.compress,然后compress调用native方法最终调用SkBitmap, 是谷歌Skia 的定义类

3:39 左右开始讲AndFix 的解析，虽然过时了，看懂。。  
IDA软件，  

## linux  

## 音视频、图像处理技术  
opengl肯定要看grafika 库  

nvidia官网有gpu调试工具  

MediaHttpConnection  
ImageWriter  

后面的小部分有点走神，但是应该问题不大  

## android音视频API,OpenSL ES
重看  

AudioRecord  
pcm文件存储量 = （采样频率*采样位数*声道*时间）/8  
框架：sounds,pmodule  


## camera基础和GLSurfaceView  
前面50分钟讲的面试题。。。先跳过  
50分子之后还没看，视频老是卡一下，


## 其他
手写 ViewGroup 遍历？
替换recyclerView 为 listView; textureView优化

kotlin, 后置

c fork thread ， linux基础里注意一下  
