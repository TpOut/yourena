
### 内存管理

一般我们对于内存的分类也就这几种：栈区（stack area）、堆区（heap area）、全局区（静态区）（存放全局变量与静态变量static）、BSS段（存放未初始化的全局变量，未初始化的全局变量默认值为0）、文字常量区、数据区（data area）、代码区（code area）等。

![内存](https://ws3.sinaimg.cn/large/006tNc79ly1fz78bmtwvcj30bq0b4aae.jpg)

代码理解：

~~~c
# include<stdio.h>
# include<stdlib.h>
# include <unistd.h>

int num1;/*BSS段*/
int num2 = 20;/*全局区*/
char * str1 = "str1";/*文字常量区*/

int main(void){
    printf("%d\n",getpid());/*获取当前进程id号*/
    int num3 = 3;/*栈区*/
    static int num4 = 4;/*全局区*/
    const int num5 = 5;/*栈区*/
    char * str2 = "str2";/*文字常量区*/
    char str3[] = "str3";/*栈区*/
    int * p = malloc(sizeof(0));/*&p在栈区，p在堆区*/

    printf("num1:%p\nnum2:%p\nnum3:%p\nnum4:%p\nnum5:%p\n",&num1,&num2,&num3,&num4,&num5);
    printf("str1:%p\nstr2:%p\nstr3:%p\n",str1,str2,str3);
    printf("&p:%p\np:%p\n",&p,p);

    while(1){}/*死循环以保证进程不会结束，方便查看/proc/pid/maps文件*/
    free(p);
    return 0;
}
~~~

vim /proc/process id/maps

![image-20190115141654181](https://ws2.sinaimg.cn/large/006tNc79ly1fz78u46rogj31hq0kg45d.jpg)

备注：

~~~html
第一列代表内存段的虚拟地址
第二列代表执行权限，p=私有 s=共享 heap和stack段不应该有x
第三列代表在进程地址里的偏移量
第四列映射文件的主设备号和次设备号 通过 cat /proc/devices得知fd
第五列映像文件的节点号，即inode
第六列是映像文件的路径
补充资料：https://www.cnblogs.com/jiayy/p/3458076.html

~~~

首先看代码：

~~~c
 #include <stdlib.h>
 int var1 = 1;
  void test();
void test2(){
      int var2 = 2;
    test1();
}
  int main(void) {
  int var2 = 2;
  printf("hello, world!\n");
  exit(0);
 }
~~~

![image-20181117001228977](https://ws1.sinaimg.cn/large/006tNbRwly1fxacvb62p9j30uo0d4whg.jpg)

不同的OS看到的红色圈圈可能不一样。可执行文件在存储（也就是还没有载入到内存中）的时候，分为：代码区、数据区和未初始化数据区3个部分。下图所示为可执行代码存储时结构和运行时结构的对照图。一个正在运行着的C编译程序占用的内存分为代码区、初始化数据区、未初始化数据区、堆区和栈区5个部分。

![image-20181117001411911](https://ws1.sinaimg.cn/large/006tNbRwly1fxacx3sh3tj30wc0lktcl.jpg)

- 代码区（text segment）。代码区指令根据程序设计流程依次执行，对于顺序指令，则只会执行一次（每个进程），如果反复，则需要使用跳转指令，如果进行递归，则需要借助栈来实现。代码段： 代码段（code segment/text segment ）通常是指用来存放程序执行代码的一块内存区域。这部分区域的大小在程序运行前就已经确定，并且内存区域通常属于只读, 某些架构也允许代码段为可写，即允许修改程序。在代码段中，也有可能包含一些只读的常数变量，例如字符串常量等。代码区的指令中包括操作码和要操作的对象（或对象地址引用）。如果是立即数（即具体的数值，如5），将直接包含在代码中；如果是局部数据，将在栈区分配空间，然后引用该数据地址；如果是BSS区和数据区，在代码中同样将引用该数据地址。另外，代码段还规划了局部数据所申请的内存空间信息。

- 全局初始化数据区/静态数据区（Data Segment）。只初始化一次。数据段： 数据段（data segment ）通常是指用来存放程序中已初始化的全局变量的一块内存区域。数据段属于静态内存分配。data段中的静态数据区存放的是程序中已初始化的全局变量、静态变量和常量。

- 未初始化数据区（BSS）。在运行时改变其值。BSS 段： BSS 段（bss segment ）通常是指用来存放程序中未初始化的全局变量的一块内存区域。BSS 是英文Block Started by Symbol 的简称。BSS 段属于静态内存分配，即程序一开始就将其清零了。一般在初始化时BSS段部分将会清零。

- 栈区（stack）。由编译器自动分配释放，存放函数的参数值、局部变量的值等。存放函数的参数值、局部变量的值，以及在进行任务切换时存放当前任务的上下文内容。其操作方式类似于数据结构中的栈。每当一个函数被调用，该函数返回地址和一些关于调用的信息，比如某些寄存器的内容，被存储到栈区。然后这个被调用的函数再为它的自动变量和临时变量在栈区上分配空间，这就是C实现函数递归调用的方法。每执行一次递归函数调用，一个新的栈框架就会被使用，这样这个新实例栈里的变量就不会和该函数的另一个实例栈里面的变量混淆。栈(stack) ：栈又称堆栈， 是用户存放程序临时创建的局部变量，也就是说我们函数括弧"{}"中定义的变量（但不包括static 声明的变量，static 意味着在数据段中存放变量）。除此以外，在函数被调用时，其参数也会被压入发起调用的进程栈中，并且待到调用结束后，函数的返回值也会被存放回栈中。由于栈的先进先出特点，所以栈特别方便用来保存/ 恢复调用现场。从这个意义上讲，我们可以把堆栈看成一个寄存、交换临时数据的内存区。

- 堆区（heap）。用于动态内存分配。堆在内存中位于bss区和栈区之间。一般由程序员分配和释放，若程序员不释放，程序结束时有可能由OS回收。堆(heap)： 堆是用于存放进程运行中被动态分配的内存段，它的大小并不固定，可动态扩张或缩减。当进程调用malloc 等函数分配内存时，新分配的内存就被动态添加到堆上（堆被扩张）；当利用free 等函数释放内存时，被释放的内存从堆中被剔除（堆被缩减）。在将应用程序加载到内存空间执行时，操作系统负责代码段、数据段和BSS段的加载，并将在内存中为这些段分配空间。栈段亦由操作系统分配和管理，而不需要程序员显示地管理；堆段由程序员自己管理，即显式地申请和释放空间。

![image-20181117001749452](https://ws3.sinaimg.cn/large/006tNbRwly1fxad0v2v4kj30z00sq796.jpg)

***思考***：我们的c的函数是如何像操作系统申请内存的？和jvm游什么区别？

https://www.gnu.org/software/libc/

<https://www.gnu.org/software/libc/manual/pdf/libc.pdf>

我们一般使用malloc来分配内存，属于c标准库函数。malloc分配的内存是从堆中分配的。linux系统向用户提供申请的内存有brk(sbrk)和mmap函数。

案列1：手动分析代码在内存分布

```c
/* memory_allocate.c用于演示内存分布情况 */

  int a = 0;                      /* a在全局已初始化数据区 */
  char *p1;                       /* p1在BSS区(未初始化全局变量) */

  int main(void) {
    int b;                        /* b在栈区 */
    char s[] = "abc";             /* s为数组变量, 存储在栈区 */
    /* "abc"为字符串常量, 存储在已初始化数据区 */
   char *p1, p2;                 /* p1、p2在栈区 */
   char *p3 = "123456";          /* "123456\0"已初始化在数据区, p3在栈区 */
   static int c = 0;             /* c为全局(静态)数据, 存在于已初始化数据区 */
   /* 另外, 静态数据会自动初始化 */
   p1 = (char *)malloc(10);      /* 分配的10个字节的区域存在于堆区 */
   p2 = (char *)malloc(20);      /* 分配得来的20个字节的区域存在于堆区 */

   free(p1);
   free(p2);
 }
```

总结：

![image-20181211133018829](/Users/wuyue/Library/Application Support/typora-user-images/image-20181211133018829.png)

进程内存布局：

![image-20190117110150437](https://ws3.sinaimg.cn/large/006tNc79ly1fz9efro70dj30tg0o6dxq.jpg)



作业：brk mmap 看源码分析

动态内存申请总结：

**malloc**
	没有初始化内存的内容,一般调用函数memset来初始化这部分的内存空间.free（）

**calloc**
	申请内存并将初始化内存数据为null.

​	 ` int *pn = (int*)calloc(10, sizeof(int));`

**realloc**

​	 对malloc申请的内存进行大小的调整.

```c
char *a = (char*)malloc(10);
realloc(a,20);
```

特别的：
**alloca**
	在栈申请内存,因此无需释放.
`int *p = (int *)alloca(sizeof(int) * 10);`

面试题目：malloc(0)； 将返回什么？

~~~c
#include <stdio.h>
#include <malloc.h>

int main()
{
    int* p = (int*)malloc(0);
    printf("%p\n", p);
}
~~~



参见代码  动态内存分配.c

### 内存四区模型增加

void指针含义：

  ==c语言规定只有相同数据类型的指针才可以相互赋值==，因此void*指针作为左值用于“接收”任意类型的指针。void*指针作为右值赋值给其他指针时，需要进行强制类型转换。

变量：

既能读又能写的内存对象，我们称为变量；一旦初始化后不能修改的对象我们称之为常量。变量名实质是一段连续内存空间的别名。

内存四区：

![image-20190309163109690](https://ws3.sinaimg.cn/large/006tKfTcgy1g0wmk5h4vbj312i0l0wlq.jpg)

`操作系统管理内存原理`：

栈空间：堆空间：内存映射：

stack由系统自动分配，heap需要程序员自己申请，C中用函数malloc分配空间，用free释放，C++用new分配，用delete释放。对于堆空间来说，默认是没有软限制的，只依赖硬限制。栈空间可以通过ulimit -a命令进行查找。

内存泄漏：申请的内存一直申请不释放。

野指针：占用别的程序申请的内存。



案列3：二级指针操作文件

```~~~c
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<stdbool.h>

void readFileData(FILE *pFILE, int num, char **pString);
void printData(char **pString, int num);
int getFilelinenum(FILE* file){
    if(file ==NULL){
        printf("文件打开失败");
        return -1;
    }
    int num = 0;
    char buff[1024];//定义1024缓冲区
    while(fgets(buff,1024,file)!=NULL){
        printf("%s",buff);
        num++;
    }
    return num;
}

/**
 *
 * @param pFILE  文件指针
 * @param num 有效行数
 * @param pString 把有效的数据放到堆区
 */
void readFileData(FILE *pFILE, int num, char **pString) {
    if(pFILE ==NULL||num<0||pString==NULL){
        printf("参数错误");
        return ;
    }
    int index =0;
    char buff[1024];//定义1024缓冲区
    while(fgets(buff,1024,pFILE)!=NULL){
        int currentLen = strlen(buff)+1;
        char * current = malloc(sizeof(char*)*currentLen);
        strcpy(current,buff);
        pString[index++] = current;
        //清空缓冲区
        memset(buff,0,1024);
    }
}

void printData(char **pString, int num) {
    for (int i = 0; i < num; ++i) {
        printf("第%d行的数据是%s",i+1,pString[i]);
    }
}

int main(){
    printf("======BEGIN=========\n");
    FILE* file = fopen("../test.txt","r");
    if(file == NULL){
        printf("文件打开失败");
        return -1;
    }
   //注意这里把函数注释掉，程序就好了，为什么？要学员思考？(文件指针移动)
   // int num = getFilelinenum(file);
    int num = 5;
    char** pArrays = malloc(sizeof(char*)*num);
    readFileData(file,num,pArrays);
    printData(pArrays,num);
}
~~~
```

案列4：二级指针作为函数参数的输出特性
```
~~~c
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<stdbool.h>

//释放内存给大家做
void testfunction();
void allocaMem(int **pInt);
void printArray(int **pInt, int i);

int main(){
    printf("======BEGIN=========\n");
    testfunction();
}

void testfunction() {
    int* p = NULL;
    allocaMem(&p);
    printArray(&p,10);
}

void printArray(int **p, int i) {
    for (int j = 0; j < i; ++j) {
        printf("%d\n",(*p)[j]);
    }
}

void allocaMem(int **p) {
    int* arr = malloc(sizeof(int)*10);
    for (int i = 0; i < 10; ++i) {
        arr[i] = i+100;
    }
    *p = arr;
}
~~~
```

### 结构体

高级知识总结：

==铁律1==: 每个结构体成员的起始地址为该成员大小的整数倍，即int型成员的其实地址只能为0、4、8等

==铁律2==: 结构体的大小为其中最大成员大小的整数倍

1，数据类型自身对齐

数据类型的起始地址为其大小的整数倍

2，结构体的自身对齐

结构体的自身对齐值为其中最大的成员大小

3，指定对齐

可以使用关键词#pragma pack(1) 来指定结构体的对齐值

4，有效对齐值

有效对齐值为自身对齐值与指定对齐值中较小的一个。（即指定对齐值超过自身对齐值无意义）

![image-20190309174101650](https://ws3.sinaimg.cn/large/006tKfTcgy1g0wokstldlj30t807w3z1.jpg)

~~~c

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <sys/ioctl.h>
//char 1字节、short 2字节、int 4字节

//1，每个结构体成员的起始地址为该成员大小的整数倍，即int型成员的其实地址只能为0、4、8等
//2，结构体的大小为其中最大成员大小的整数倍

#pragma pack(10)
struct A{
    char a; //1
    int b; //3+4= 7 （规则1）
    short c; //2+2 （规则2）
};
#pragma pack(10)
struct B{
    char a; //1
    short b; //1+2
    int c; //4
};
int main(int argc, char *argv[])
{
    printf("sizeof(struct A)=%d, sizeof(struct B)=%d\n", sizeof(struct A), sizeof(struct B));
    return 1;
}
~~~
