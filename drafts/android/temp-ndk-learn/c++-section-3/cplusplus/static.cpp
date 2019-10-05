//
// Created by Jesson on 2019-01-17.
//

#include <iostream>

using namespace std;


class StaticObject{
public:
    static int objectCount; //
    int y;

    StaticObject(double l=22.0, double b=21.0, double h=62.0) //kotlin java
    {
        cout <<"我被调用了" << endl;
        length = l;
        breadth = b;
        height = h;
        // 每次创建对象时增加 1
        objectCount++;
    }

    static  int getCount(){
        return objectCount;
    }

private:
    double length;
    double breadth;
    double height;
};

int StaticObject::objectCount = 10;//初始化

int main(){
    StaticObject object1(3.3, 1.2, 1.5);
    StaticObject object2(8.5, 6.0, 2.0);
    StaticObject object3(2);
    cout << "Total objects: " << StaticObject::objectCount << endl;
    cout << "getCount(): " << StaticObject::getCount() << endl;
    return -1;
}