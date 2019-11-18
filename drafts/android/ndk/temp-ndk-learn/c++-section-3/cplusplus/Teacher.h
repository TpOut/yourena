#pragma once //win

#ifndef CPLUSPLUS_TEACHER_H
#define CPLUSPLUS_TEACHER_H



class Teacher {

    friend class Student; //友元类
private:
    int age;
    char* name;
public:

    Teacher(); //无参数构造
    Teacher(char* name,int age); //有参构造
    ~Teacher(); //析构函数
    Teacher(const Teacher &obj);//拷贝构造函数(默认浅拷贝 数值拷贝)

    void setName(char* name);
    char* getName();
    void setAge(int age);
    int getAge();

    void print();
};

class Student{
public:
    void test(Teacher *t){
        t->age = 100;
    }
};


#endif //CPLUSPLUS_TEACHER_H
