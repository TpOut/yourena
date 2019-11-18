#include <iostream>
#include "Teacher.h"

using namespace std;

void Teacher::setName(char* name){
    Teacher::name = name;
}

char*  Teacher::getName(){
    return Teacher::name;
}

void  Teacher::setAge(int age){
    Teacher::age = age;
}

int  Teacher::getAge(){
    return Teacher::age;
}

Teacher::Teacher() {
    cout<<"无参构造函数"<<endl;
    int length = strlen(Teacher::name);
    Teacher::name = (char*)(malloc(length+1));
    strcpy(name,"hello jesson");
    Teacher::age = 36;
}
Teacher::Teacher(char *name, int age) {
    cout<<"有参构造函数"<<endl;
//    Teacher::age = age;
//    Teacher::name = name;
    int length = strlen(name);
    Teacher::name = (char*)(malloc(length+1));
    strcpy(Teacher::name,name);
    Teacher::age = age;
}

//todo free delete

Teacher::~Teacher() {
    //内存的释放 delete 集合的不断删除
    cout<<"析构函数"<<endl;
    cout<<&name<<endl;
    free(Teacher::name);
    cout<<name<<endl;
}

Teacher::Teacher(const Teacher &obj) {
    //默认浅拷贝
    cout<<"拷贝构造函数"<<endl;
//    Teacher::name =obj.name;
//    Teacher::age =obj.age;
    //必须深拷贝
    int length = strlen(obj.name);
    Teacher::name = (char*)(malloc(length+1));
    strcpy(Teacher::name,obj.name);
    Teacher::age = obj.age;
}

void Teacher::print() {
    cout<<Teacher::age<<","<<Teacher::name<<endl;
}

void fun(){
    Teacher teacherJesson("jesson",22);
    Teacher teacher = teacherJesson;
    teacher.print();
}

void testxigou(){
    Teacher teacherJesson("jesson",35);
}



int main(){
    //testxigou();
    //Teacher teacher; //无参构造
    Teacher teacherJesson("jesson",22);//第一种写法
    Teacher teacherC = Teacher("jesson",22);//第二种写法

    Teacher student("jesson",33);
    Teacher a = student;

//
//    fun();
//
//    teacher.setAge(100);
//    teacher.setName("jesson");
    return 0;
}
