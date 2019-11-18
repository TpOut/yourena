//
// Created by Jesson on 2019-03-23.
//

//总结 C++ 中静态的用法
//静态 局部变量  全局变量  全局函数  类的成员函数 类的成员变量

#include <iostream>

using namespace std;
void fun();

extern int num1; //普通的全局变量可以通过extern来使用，但是也会担心导致冲突
//extern int num2; //static全局的变量不能通过extern关键字，但是也不用担心导致的文件冲突了


int main(){
    for (int i = 0; i < 4; ++i) {
        fun();
    }
    cout << "num1 = " << num1 << endl;
}


void fun(){
    static int i; //静态局部变量初始化1次，默认为0
    cout << "i = " << i << endl;
    i++;
}

//在看下我的静态.CPP :: 来访问 在类的外部初始化
