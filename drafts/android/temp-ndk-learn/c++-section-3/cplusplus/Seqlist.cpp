//
// Created by Jesson on 2019-02-12.
//

#include <iostream>
#include "Seqlist.h"


template<class T>
Seqlist<T>::Seqlist(const T a[], int n)
{
    if (n > MAXSIZE)throw"数组长度超过最大长度";
    for (int i = 0; i < n; i++)
    {
        data[i] = a[i];
    }
    length = n;
}

template<class T>
T Seqlist<T>::get(int i)
{
    if (i<1 || i>length)throw"位置非法";
    return data[i - 1];
}

template<class T>
int Seqlist<T>::locate(const T x)
{
    for (int i = 0; i < length; i++)
        if (x == data[i])
            return i + 1;
    return 0;//查找失败
}

template<class T>
bool Seqlist<T>::remove(int index) {
    if (index<1 || index>length)throw"位置非法";
    for(int i=index; i< this->length; i++) {
        this->data[i] = this->data[i+1];
    }
    this->length--;
    return true;

}

//inert



int main()//实例化
{
    int a[7] = { 21,24,32,43,54,65,71 };
    Seqlist<int>list(a, 7);
    int v = list.locate(5);
    cout << v << endl;
    int d = list.get(1);
    cout << d << endl;
    list.remove(1);
    int d2 = list.get(2);
    cout << d2 << endl;
}

