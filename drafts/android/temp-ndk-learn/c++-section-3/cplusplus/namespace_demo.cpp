

#include "namespace_demo.h"
#include <iostream>

using namespace NameSpaceA;
using namespace std;

//组件化  分模块
//测试namespace
int main_namespace(){
    printf("a = %d\n", name);
    printf("a = %d\n", NameSpaceB::age);

    NameSpaceB::NameSpaceC::Teacher teacher; //
    NameSpaceB::NameSpaceC::Teacher t1;
    teacher = {"aaa", 3};
    printf("t1.name = %s\n", teacher.name);
    printf("t1.age = %d\n", teacher.age);

    return 0;
}


//引用知识
int main(){
    int a = 10; //c编译器分配4个字节内存。。。a内存空间的别名
    int &b = a;  //b就是a的别名。。。
    a =11; //直接赋值
        {
            int *p = &a;
            *p = 12;
            printf("a %d \n", a); //12
        }
    b  = 14;
    printf("a:%d b:%d", a, b); //14 14
}
