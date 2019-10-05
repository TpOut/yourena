//
// Created by Jesson on 2019-01-23.
//

/***
 *
 * 1) public继承方式
    基类中所有 public 成员在派生类中为 public 属性；
    基类中所有 protected 成员在派生类中为 protected 属性；
    基类中所有 private 成员在派生类中不能使用。

    2) protected继承方式
    基类中的所有 public 成员在派生类中为 protected 属性；
    基类中的所有 protected 成员在派生类中为 protected 属性；
    基类中的所有 private 成员在派生类中不能使用。

    3) private继承方式
    基类中的所有 public 成员在派生类中均为 private 属性；
    基类中的所有 protected 成员在派生类中均为 private 属性；
    基类中的所有 private 成员在派生类中不能使用。
 *
 *
 *
 *
 */

//#include <printf.h>
//#include <iostream>
//using namespace std;
//
//class People {
//public:
//    People(){}
//    People(const char *m_name, int m_age) : m_name(m_name), m_age(m_age) {}
//
//public:
//    void setname(const char *name) { m_name = name; }
//    void setage(int age) { m_age = age; }
//    const char *getname() const { return m_name; }
//    int getage() const { return m_age; }
//private:
//    const char *m_name;
//    int m_age;
//protected:
//
//};


////继承语法 class Type ： 修饰符 Type
//class Student : public People {
//public:
//    Student() : People() {}
//
//    Student(const char *name, int age):People(name,age){
//        cout<<"Student有参构造"<<endl;
//    };
//    void setscore(float score) { m_score = score; }
//    float getscore() const { return m_score; }
//private:
//    float m_score;
//
//};
//
//int main() {
//    //指针对象 ->
//    Student* s = new Student("jesson",99);;
//    s->setscore(111);
//    Student s1 ;
//    s1.setname("jesson");
//    s1.setage(18);
//    s1.setscore(111);
//    printf("name: %s, age: %d, score: %g\n", s->getname(), s->getage(), s->getscore());
//    printf("name: %s, age: %d, score: %g\n", s1.getname(), s1.getage(), s1.getscore());
//    return 0;
//}

//---------using 来改变权限的问题-----------
//using namespace std;

//class A {
//protected:
//    int m_a;
//    char m_b;
//    float m_c;
//private:
//    char *m_d;
//};
//
//class B : public A {
//public:
//    using A::m_a;
//protected:
//    using A::m_b;
//    //using A::m_d; // 基类的private成员在派生类中不可见
//private:
//    using A::m_c;
//};
//
//int main() {
//    B b;
//    b.m_a = 1;
//    return 0;
//}

//---------派生类  覆盖子类的方法-------------

//#include <cstdio>
//
//using namespace std;
//
//class A {
//public:
//    void func() {
//        printf("class A: func()\n");
//    }
//};
//
//class B : public A {
//public:
//    void func() {
//        printf("class B: func()\n");
//    }
//};
//
//int main() {
//    B b;
//    b.func();
//    b.A::func();
//    b.A::func(); //子类调用父类
//    return 0;
//}

//--------------做一个思考题--------派生类如何调用父类的方法 childClass.parentClass::method()---
//#include <cstdio>
//
//using namespace std;
//
//class A {
//public:
//    void func() {
//        printf("class A: func()\n");
//    }
//    void func(int) {
//        printf("class A: func(int)\n");
//    }
//};
//
//class B : public A {
//public:
//    void func(char) {
//        printf("class B: func(char)\n");
//    }
//    void func(float) {
//        printf("class B: func(float)\n");
//    }
//};
//
//int main() {
//    B b;
//    b.func('A'); // 正确
//    b.func(1.1f); // 正确
////    b.func(); // 错误
////    b.func(10); // 错误
//    b.A::func();
//    return 0;
//}

////--------------c++内存模型----***重要***-------
//没有继承关系时：成员变量和成员函数会分开存储，成员变量存储在栈、堆、全局数据区，而成员函数存储在代码区；
//有继承关系时：派生类的内存模型可以看成是基类成员变量和新增成员变量的总和，而所有成员函数仍然存储在代码区，由所有对象共享；
//
//基类的成员变量排在前面，派生类的排在后面； c++中类的内存大小和函数无关，只和成员变量有关系
//
#include <cstdio>
//
//using namespace std;
//
//class A {
//public:
//    A() : m_a(1) {} //这种写法很常见
//protected:
//    int m_a;
//};
//
//class B : public A {
//public:
//    B() : A(), m_a(2) {}
//protected:
//    int m_a;
//};
//
//class C : public B {
//public:
//    C() : B(), m_b(11) {}
//protected:
//    int m_b;
//};
//
//class D : public C {
//public:
//    D() : C(), m_b(22) {}
//protected:
//    int m_b;
//};
//
////父类的成员变量是在前面的
//int main() {
//    D d;
//    printf("sizeof(d) = %lu\n", sizeof(d));
//    printf("A::m_a = %d, B::m_a = %d, C::m_b = %d, D::m_b = %d\n",
//           *(int *)&d, //1
//           *(int *)((long)&d + 4), //2
//           *(int *)((long)&d + 8), //11
//           *(int *)((long)&d + 12)); //22
//    return 0;
//}

////--------------构造函数-----------
//1.类的构造函数不能被继承；(即使继承了，它的名字和派生类的名字也不一样，不能成为派生类的构造函数，当然更不能成为普通的成员函数；)
//2.在派生类的构造函数中调用基类的构造函数；派生类构造函数中只能调用直接基类的构造函数，不能调用间接基类的；通过派生类创建对象时必须要调用基类的构造函数，这是语法规定.
//class A {
//public:
//    A() {
//        printf("class_A A()\n");
//    }
//    A(int) {
//        printf("class_A A(int)\n");
//    }
//    A(char) {
//        printf("class_A A(char)\n");
//    }
//};
//
//class B : public A {
//public:
//    B() {
//        printf("class_B B()\n");
//    }
//    B(char) {
//        printf("class_B B(char)\n");
//    }
//};
//
//class C : public B {
//public:
//    C() : B('A') {
//        printf("class_C C()\n");
//    }
//};
//
//int main() {
//    C c;
//    return 0;
//}

//--========析构函数=========
//析构函数也不能被继承；在派生类的析构函数中不用显式地调用基类的析构函数，因为每个类只有一个析构函数，编译器知道如何选择，无需程序员干涉
//析构函数的执行顺序和构造函数的执行顺序也刚好相反：
//1--创建派生类对象时，构造函数的执行顺序和继承顺序相同，即先执行基类构造函数，再执行派生类构造函数；
//2---而销毁派生类对象时，析构函数的执行顺序和继承顺序相反，即先执行派生类析构函数，再执行基类析构函数；

//触摸时间 事件传递

//class A {
//public:
//    A() {
//        printf("A()\n");
//    }
//    ~A() {
//        printf("~A()\n");
//    }
//};
//
//class B : public A {
//public:
//    B() {
//        printf("B()\n");
//    }
//    ~B() {
//        printf("~B()\n");
//    }
//};
//
//class C : public B {
//public:
//    C() {
//        printf("C()\n");
//    }
//    ~C() {
//        printf("~C()\n");
//    }
//};
//
//int main() {
//    C c;
//    return 0;
//}

//--------------类的多继承----调用顺序----------
//基类构造函数的调用顺序和它们在派生类构造函数中出现的顺序无关，而是和声明派生类时基类出现的顺序相同
//class A {
//public:
//    A() {
//        printf("A()\n");
//    }
//    ~A() {
//        printf("~A()\n");
//    }
//protected:
//    void print() {
//        printf("A::print()\n");
//    }
//};
//
//class B {
//public:
//    B() {
//        printf("B()\n");
//    }
//    ~B() {
//        printf("~B()\n");
//    }
//protected:
//    void print() {
//        printf("B::print()\n");
//    }
//};
//
//class C : public A, public B {
//public:
//    C() : B(), A() {
//        printf("C()\n");
//    }
//    ~C() {
//        printf("~C()\n");
//    }
//public:
//    void print() {
//        A::print();
//        B::print();
//    }
//};
//
//int main() {
//    C c;
//    c.print();
//    return 0;
//}
//
