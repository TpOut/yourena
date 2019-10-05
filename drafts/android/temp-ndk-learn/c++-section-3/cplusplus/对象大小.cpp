//
// Created by Jesson on 2019-01-21.
//


#include <iostream>
//using namespace std;
//
//class A {
//
//};
//class B : public A{
//
//};
//class C : public B{
//
//};
//
//int main() {
//    A a;
//    B b;
//    C c;
//    //这1个字节的空间是系统为该类的对象创建的一个占位符，表示该对象仅仅是存在而已，而没有实际内容。
//    cout << "size of a:" << sizeof(a) << endl;
//    cout << "size of b:" << sizeof(b) << endl;
//    cout << "size of c:" << sizeof(c) << endl;
//    return 0;
//}



//----------------SECTION 2------------------------------------------------------------
//#include <iostream>
//using namespace std;
//
//class A {
//public:
//    A(int x=0) {
//        cout<<"A"<<x<<endl;
//    }
//    void printA() {
//        cout<<"Hello A";
//    }
//};
//class B :public A{
//public:
//    B(int x=0) {
//        cout<<"B"<<x<<endl;
//    }
//    void printB() {
//        cout<<"Hello B";
//    }
//};
//
//class C : public B{
//public:
//    C() {
//        cout<<"C"<<endl;
//    }
//    void printC() {
//        cout<<"Hello C";
//    }
//};
//
//int main() {
//    A a;
//    B b;
//    C c;
//    //一般成员函数不会对类的大小造成影响。
//    cout<<"size of a:"<<sizeof(a)<<endl;
//    cout<<"size of b:"<<sizeof(b)<<endl;
//    cout<<"size of c:"<<sizeof(c)<<endl;
//    return 0;
//}


//----------------SECTION 3------------------------------------------------------------
#include <iostream>
using namespace std;

class A {
public:
    A(int x=0) {
        cout<<"A"<<x<<endl;
    }
    void printA() {
        cout<<"Hello A";
    }
private:
    char Data1[3];
    int Data2;
};

class B :public A{
public:
    B(int x=0) {
        cout<<"B"<<x<<endl;
    }
    void printB() {
        cout<<"Hello B";
    }
private:
    char Data1[3];
    int Data2;
};

class C : public B{
public:
    C(int x=0) {
        cout<<"C"<<x<<endl;
    }
    void printC() {
        cout<<"Hello C";
    }
private:
    char Data1[3];
    int Data2;
};

int main() {
    A a;
    B b;
    C c;

    //类对象大小=基类对象大小+自身成员大小。A当中三个字符变量3个字节，一个整形变量4个字节，考虑内存对齐因素(默认为4)，A类对象大小为8。
    // B类对象大小为A类对象大小基础上再加8，C类对象大小在B类对象大小基础上再加8。
    cout << "size of a:" << sizeof(a) << endl;
    cout << "size of b:" << sizeof(b) << endl;
    cout << "size of c:" << sizeof(c) << endl;
    return 0;
}


