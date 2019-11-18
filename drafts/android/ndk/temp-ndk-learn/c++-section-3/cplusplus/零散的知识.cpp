//
// Created by Jesson on 2018-09-20.
//

#include <iostream>
#include <stdio.h>

using namespace std;

//c++ 函数重载
int sum(int a,int b=0);
int sum(int a,int b, bool c=0);

int main(){

    cout<<"test cplus plus"<<endl;
    cout<<sum(1)<<endl;
    sum(1,2,-1); // 0 false 其他数值是true -1 true



    return -1;
}


int sum(int a,int b){
    return a+b;
}

int sum(int a,int b, bool flag){
    if(flag){
        cout<<"true"<<endl;
    }else{
        cout<<"false"<<endl;
    }

    return a+b;
}