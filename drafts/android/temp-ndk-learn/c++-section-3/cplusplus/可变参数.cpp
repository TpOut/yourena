//
// Created by Jesson on 2019-01-21.
//

#include <stdio.h>
#include <stdarg.h>
#include <iostream>



using namespace std;

//测试代码 看一看va_arg 获取的是第二个参数的数据 那么参数的格式是要自己写代码控制的 一般需要显示传递
void sum(int count,int first ...) {
    va_list vp; //创建list 保存你的可变参数
    //可变参数开始
    va_start(vp, count); //looper
    int num = va_arg(vp, int);
    cout << num << endl;

    int num2 = va_arg(vp, int);
    cout << num2 << endl;

    int num3 = va_arg(vp, int); //出现了内存的溢出
    cout << num3 << endl;


}


int add_va(int num, int first, ...) {
    int ret = 0, arg, i;
    va_list ap;  //定义一个可变参数变量

    ret = first;
    va_start(ap, first); //初始化可变参数
    for (i = 1; i < num; i++) {
        arg = va_arg(ap, int);//获取下一个类型为int的参数
        ret += arg;
    }

    va_end(ap);//清除变量ap
    return ret;
}


//思考如何实现累加

void myprintf(const char *format...) {

    va_list ap;  //定义一个可变参数变量
    va_start(ap, format); //va_start
    char ch;
    while (ch = *(format++)) {
        if (ch == '%') {
            ch = *(format++);
            if (ch == 's') {
                char *name = va_arg(ap, char *);    //va_arg
                cout << name;
            } else if (ch == 'd') {
                int age = va_arg(ap, int);    //va_arg
                cout << age;
            }
        } else {
            cout << ch;
        }
    }
}


int main() {
    sum(3, 4, 5);
    printf("====%d\n", add_va(3, 2, 3, 4));
    myprintf("My name is %s, age %d.", "AnnieKim", 24);

    //思路
    //1。肯定需要利用上面的一些可变参数的函数
    //start end
    //2。取出字符串遍历%
    //switch s  d
    return -1;
}

