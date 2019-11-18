#include <iostream>
using namespace std;

struct Teacher{
    char name[100];
    int age;
};

struct TeaceherB {
    int &age; //引用
    int &num; //引用
};

void printfT(Teacher *pT)
{
    cout<<pT->age<<endl;
}
//pT是t1的别名 ,相当于修改了t1 参数传递引用
void printfT2(Teacher &pT)
{
    pT.age = 33;
}


//pT和t1的是两个不同的变量
void printfT3(Teacher pT) //形参
{
    pT.age = 45; //只会修改pT变量 ,不会修改t1变量
}



void testA()
{
    int a,b,sum;
    a=20;
    b=15;
    sum=a+b;
    printf("a=%d,b=%d,sum=%d\n",a,b,sum);
    printf("===================================\n");
    printf("char占用%d字节\n", sizeof(char));
    printf("int占用%d字节\n", sizeof(int));
    printf("double占用%d字节\n", sizeof(double));
    printf("long占用%d字节\n", sizeof(long));
    printf("short占用%d字节\n", sizeof(short));
    printf("float占用%d字节\n", sizeof(float));

    printf("=========数组相关==========================\n");
    int c[5];
    int i;
    for(i = 0; i < 5; i++)
    {
        c[i] = i + 1;
    }
    for (i = 0; i < 5; i++)
    {
        printf("%d\n", c[i]);
    }

}

//type& 引用 别名 const int*  int* const

void testReference() {
    printf("=========引用相关======================\n");
    int a = 100;
    int &b = a; //引用的定义
    a = 11;
    {
        int *p = &a;
        *p = 12;
        printf("a %d \n",a);
    }
    b =15;
    printf("a:%d b:%d\n", a, b);

    int a1 = 0;
    int* const b1 = &a1; //这里等同于int &b = a;
    *b1 = 11;
    printf("a1:%d b1:%d\n", a1, *b1); //11 11

    const int* c = &a1;
    //*c = 21;  //此处编辑器控制的 const 不能修改
    printf("a1:%d b1:%d\n", a1, *b1);


    int i1 = 30;
    int i2 = 40;
    const int *pi = &i1;
    pi = &i2; // 注意这里，pi可以在任意时候重新赋值一个新内存地址
    i2 = 80; //想想看：这里能用*pi = 80来代替吗？当然不能！
   // *pi = 80;
    printf("%d\n", *pi); //80


    int i3 = 30;
    int i4 = 40;
    int* const p2 = &i3;
   // p2 = &i2; // p2值是不能重新赋值修改了。它只能永远指向初始化时的内存地址了
    i3 = 80;
    printf("%d\n", *p2); //80

}

void testReferenceB() {
    printf("=========引用作为函数的参数传递======================\n");
    Teacher t1;
    t1.age = 88;


    printfT(&t1);

    printfT2(t1); //pT是t1的别名
    printf("t1.age:%d \n", t1.age); //33 这里会变

    printfT3(t1) ;// pT是形参 ,t1 copy一份数据 给pT
    printf("t1.age:%d \n", t1.age); //33



    int a = 10;
    int &b = a;
    //b是a的别名，请问c++编译器后面做了什么工作？
    b = 11;
    cout<<"b--->"<<a<<endl;
    printf("a:%d\n", a);
    printf("b:%d\n", b);
    printf("&a:%p\n", &a);  //&a的地址===&b
    printf("&b:%p\n", &b);  //请思考：对同一内存空间可以取好几个名字吗？

    /** 引用作为其它变量的别名而存在，因此在一些场合可以代替指针
     *  注意：
     *  引用这个玩意 传参 比较方便 改外部的地址 有时候也作为指针的替代
     *
     */
    return ;
}


int getT(Teacher **t){

}

void testPointerReference() {
    printf("=========指针引用======================\n");
    //在C语言中，如果要实现在函数内部改变外部变量的值的话，就应该传递这个变量的指针。如果要通过指针访问变量，必须使用指针运算符“*”
    int	i = 5;		// 变量i
    int* p = &i;	// 指针p，指向变量i
    int* &pr = p;	// 指针的引用，与p指向同一个变量i

    cout << i << "  " << *p << "  " << *pr << endl;
    i = 10;
    cout << i << "  " << *p << "  " << *pr << endl;

    cout << "addr: " << p << "  " << pr << endl;	// 指针的引用其它就是指针p的一个别名，和p有相同的地址
    pr = NULL;										// 因此也可以给指针赋空
    cout << "addr: " << p << "  " << pr << endl;

}

int main() {
//    testA();
//    testReference();
//    testReferenceB();
    testPointerReference();
    return 0;
}