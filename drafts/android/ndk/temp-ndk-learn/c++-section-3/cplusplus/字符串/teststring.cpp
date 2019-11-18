//
// Created by Jesson on 2019-03-24.
//

#include<iostream>
#include<string>
#include<cstring>

//“string.h”头文件中则包含的是C语言标准库定义的，用来处理字符数组char*的函数方法。
//“cstring”头文件实际上只是在一个命名空间std中包含了“string.h”，这是因为C++要减少名称污染，
// 当遇到用户自己定义了名称相同的函数，能够与标准库std中函数区分开。
//而“string”是包含了C++专门定义一个处理字符串的类string的头文件。类来替代字符数组处理字符串当然有很多好处。


void testString();

void stringchangestringarray();

void stringinit();

using namespace std;
// char[] char* string
int main()
{
    char a[] = "abcddabc";
    char b[] = "dda";
    int j;
    string str1(a);
    string str2(b);
    //方法一
    int i = str1.find(str2);  //返回即子字符串索引3
    cout<<"find 查找字字符串的位置:"<<i<<endl;
    //方法二
    char *rel = strstr(a, b); //首次出现地址，strstr保存的是ddabc
    if (rel != NULL){
        j = rel -a;      //根据返回子字符串匹配结果输出索引位(char类型地址分配为1个字节，rel指向的是查找到子串在主字符串中的地址（例如：0x23544203），
                         // 而a是主字符串的首地址（例如：0x23544200），两者差值正好就是索引位。)
        cout<<"find 查找字字符串的位置:"<<i<<endl;
    }

    testString();
    stringchangestringarray();
    stringinit();

    return 0;
}

void stringinit() {
    char *pl = "Hello!";
    string a = "World yeyey";//类中重载了赋值符号，可以直接赋值字符串常量
    string b = pl;           //字符串数组赋值初始化
    string str("abc");       //调用有参构造函数赋值
    string c(pl);            //同上
    string d = c;            //赋值符号重载
    string e (5,'a');        //n个字符初始化

    e.append(a);        //字符串a附加到字符串e尾部，类似“+”操作符
    e.empty();          //判断字符串e是否为空，空则返回1，否则返回0
    e.swap(a);          //交换字符串对象e和a的值
    char nzArr[20]={0};
    e.copy(nzArr,5,0); //拷贝字符串e从第0(默认也为0)索引到第五给字符数组
    int i = a.find('W');//找到对应字符再字符串a中最先出现的索引位
    a.compare("World yeyey");

}

/**
 * string的几种转换模式 char* string const
 */
void stringchangestringarray() {
    //const char*转为string
    const char* nzArr = "abcd";
    string str = nzArr;

    //string转为const char*
    string str2("abcd");
    //调用string的c_str()方法
    const char* nzArr2 = str2.c_str();

    char* nzArr3 = "abcd";
    const char* nzConArr = nzArr;
    //const char*转为char*
    const char* nzConArr2 = "abcd";
    char* nzArr4 = const_cast<char*>(nzConArr2);


    //char*转为string
    char * nzArr5 = "abcd";
    string str5 = nzArr;

    //string转为char*
    string str6 = "abcd";
    char* nzArr_new = const_cast<char*>(str6.c_str());



}

void testString() {
    char nzArr[100] = "abcdef";
    int nLen = strlen(nzArr); //结果为6，并不是100，和分配数组内存大小无关
    cout<<"nLen:"<<nLen<<endl;

    char jesson[100]="abcdef",jesson1[100],jesson2[100];
    strcpy(jesson1,jesson);       //字符数组后者拷贝给前者
    strncpy(jesson2,jesson,3);    //字符数组后者拷贝3个字符给前者
    int len = strlen(jesson2); //没有意义 因为没有初始化，字符串后面没有\0
    cout<<"nLen:"<<len<<endl;

    char nzArr1[100]="abcd",nzBuf2[100]="DDDD";
    strcat(nzArr1,nzBuf2);  //nzArr结果为“abcdDDDD”,注意不要超过字符数组空间大小
    cout<<"nzArr1:"<<nzArr1<<endl;


    char nzArr3[10] ="ababcde",nzBuf4[10]="abc";
    char* nzCount  = (char*)malloc(sizeof(char)*10);
    char* nzCount1 = (char*)malloc(sizeof(char)*10);//结果分配空间
    memset(nzCount,0,sizeof(nzCount));   //赋初值
    memset(nzCount1,0,sizeof(nzCount1)); //防止未匹配到指定字符或字符数组，而成为野指针
    nzCount = strstr(nzArr3,nzBuf4);     //返回为'abcde' 返回的是地址 需要用指针来存储
    cout<<"nzCount:"<<nzCount<<endl;



}

