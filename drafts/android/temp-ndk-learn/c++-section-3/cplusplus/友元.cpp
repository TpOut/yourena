#include <iostream>
#include <cmath>

using namespace std;
//使用友元函数计算两点之间的距离
/***
 * 将数据和处理数据的函数封装在一起，构成类，实现了数据的隐藏，无疑是面向对象程序设计的一大优点。
 * 但是有时候封装不是绝对的。友元函数提供了不同类或对象的成员函数之间、类的成员函数和一般函数之间进行
 * 数据共享的机制。通俗的说，友元关系就是一个类主动声明哪些类或函数是它的朋友，
 * 进而给它们提供对本类的访问特性。也就是说，通过友元关系，一个普通函数或者类的成员函数可以访问封装
 * 于另外一个类中的数据。
 */
//class Point {
//
//public:
//    Point(int xx = 0, int yy = 0) {
//        X = xx;
//        Y = yy;
//    }
//
//    int GetX() { return X; }
//
//    int GetY() { return Y; }
//
//    friend float fDist(Point &a, Point &b); //友元函数
//
//private:
//    int X, Y;
//};
//
//
//float fDist(Point &p1, Point &p2) {
//    double x = double(p1.X - p2.X);//通过对象访问私有数据成员，而不是必须使用Getx()函数
//    double y = double(p1.Y - p2.Y);
//    return float(sqrt(x * x + y * y));
//}
//
//int main() {
//    Point p1(1, 1), p2(4, 5);
//    cout << "the distance is:";
//    cout << fDist(p1, p2) << endl;//计算两点之间的距离
//    return 0;
//}

//友元类和友元函数主要解决的问题就是 提供私有的变量给外界访问
class A {
public:
    int GetX() { return x; }

    friend class B;//B类是A类的友元类
    //其它成员略
private:
    int x;
};

class B {
public:
    void set(int i);
    //其他成员略
private:
    A a;
};

void B::set(int i) {
    a.x = i;//由于B类是A类的友元类，所以在B的成员函数中可以访问A类对象的私有成员
}

class C : private A{

};
//友元关系不能传递
//友元关系是单向的
//友元关系不能被继承