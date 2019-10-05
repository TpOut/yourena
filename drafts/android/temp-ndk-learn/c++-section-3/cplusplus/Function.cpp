#include <iostream>
#include <string.h>

using namespace std;

class Student
{
public:
    Student(int age,char *name)
    {
        printf("构造函数执行\n");
        //this->name = name;
        this->name = (char*)(malloc(sizeof(char) * 100));
        strcpy(this->name,name);
        this->age = age;
    }

    /**注意这里的写法 有点像kotlin  赋值参数**/
    Student(char *name) : sex("男") {
        this->name = name;
    }

    Student() {
        printf("空参构造函数执行\n");
    }

    ~Student() //如果在对象内部堆内存上开辟了内存，一定要记得回收
    {
        printf("析构函数执行\n");
        free(this->name); //free 对应malloc
        this->name = NULL;

    }

    Student(const Student& student)
    {
        printf("对象的拷贝构造函数执行\n");
        this->name = student.name;
        this->age = student.age-1; //方便测试
    }

public:
    int getAge()
    {
        return this->age;
    }

    char* getName()
    {
        return this->name;
    }

    void setAge(int age) {
        Student::age = age;
    }

    void setName(char *name) {
        Student::name = name;
    }

    void printStudent(Student stu){// stu 是该方法栈中一个新的对象，拷贝构造函数赋值，方法执行完会调用析构函数
        cout << stu.getName() << " , " << stu.getAge() << endl;
    }


private:
    int age;
    char* name;
    char* sex;
};


//析构函数 构造函数使用
int main11(){
//   Student student; //第一种
//   Student student1(33,"jesson"); //第二种
//     Student student2[3] = {Student(12,"小明"),Student(13,"小王"),Student(22,"小李")};
//   Student* student = new Student(33,"jesson"); //第三种 new关键字 返回的是一级指针
   Student* student = (Student*)malloc(sizeof(Student));//第四种 //malloc 不会调用构造函数
   student->setAge(100);
   student->setName("jesson");
//   delete(student); //注意delete 是回收new对象的 并且是会调用析构函数的
   cout<<student->getAge()<<endl;
   cout<<student->getName()<<endl;
   free(student);
}


//-------拷贝构造函数-------- 析构函数执行执行多次了 是因为有个不同的student对象存在，
//这里和Java是不一样的

int main(){
//    Student student1(33,"jesson");

//    Student a = student1;   //这句话的意思是赋值，编译器的工作 persron1 和person2地址是不同的
//    cout<<a.getAge()<<endl;
//    cout<<&student1<<"==="<<&a<<endl;

//     Student stu2;// 声明变量，开辟变量内存
//     stu2 = student1; // 这个不会去调用拷贝构造函数，但是会赋值 c 的类似

     //作为参数传递的时候会调用拷贝构造函数
     Student stu(24,"Jesson");
     stu.printStudent(stu);
}
