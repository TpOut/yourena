//
// Created by Jesson on 2019-01-22.
//

#include <iostream>
using namespace std;



class BaseClass {
public:
    int m_iNum;
    virtual void foo(){}; //基类必须有虚函数。保持多态特性才能使用dynamic_cast
};

class DerivedClass: public BaseClass {
public:
    char*m_szName[100];
    void bar(){
        cout<<"bar"<<endl;
    };
};

BaseClass* pb =new DerivedClass();
DerivedClass *pd1 = static_cast<DerivedClass *>(pb); //子类->父类，静态类型转换，正确但不推荐
DerivedClass *pd2 = dynamic_cast<DerivedClass *>(pb); //子类->父类，动态类型转换，正确

BaseClass* pb2 =new BaseClass();
DerivedClass *pd21 = static_cast<DerivedClass *>(pb2); //父类->子类，静态类型转换
DerivedClass *pd22 = dynamic_cast<DerivedClass *>(pb2); //父类->子类，动态类型转换，安全的。




int main(){
    double num = 80.33;
    int newnum = static_cast<int>(num);
    cout<<newnum<<endl; //80
 

    int n = 9;
    int*pn =&n;
    void* p = static_cast<void*>(pn);
    
    //static_cast void* 基本类型
    
    pd21->m_iNum;
    pd22->bar();
    /***
     * 1. 基类和子类之间转换：其中子类指针转换成父类指针是安全的；但父类指针转换成子类指针是不安全的。(基类和子类之间的动态类型转换建议用dynamic_cast)
        2. 基本数据类型转换。enum, struct, int, char, float等。static_cast不能进行无关类型（如非基类和子类）指针之间的转换。
        3. 把空指针转换成目标类型的空指针。
        4. 把任何类型的表达式转换成void类型。
        5. static_cast不能去掉类型的const、volitale属性(用const_cast)。
     */
}


//reinterpret_cast
//tips:
//去const属性用const_cast。
//基本类型转换用static_cast。
//多态类之间的类型转换用daynamic_cast。
//不同类型的指针类型转换用reinterpret_cast。

