//
// Created by Jesson on 2019-01-11.
//

#include <iostream>

#include <cstdio>
#include <cstring>
#include <string.h>
#include <string>

using  namespace std;

int main(){
    //const 修饰基本类型 不可修改
    const int  a = 10;
    const int* p = &a; //常量指针 不能修改里面的数值
    //*p = 11;
    int b = 99;
    //指针常量const_cast 转换操作

    int*  p2 = const_cast<int *>(&a); //c++ 4中类型转换

    *p2 = 200;

    cout<<a<<endl; //10
    cout<<*p2<<endl;//200



}