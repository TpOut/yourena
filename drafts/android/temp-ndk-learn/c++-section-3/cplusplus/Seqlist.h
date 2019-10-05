//
// Created by Jesson on 2019-02-12.
//

#ifndef CPLUSPLUS_SEQLIST_H
#define CPLUSPLUS_SEQLIST_H


using namespace std;
const int MAXSIZE = 1000;

template<class T>
class Seqlist {

public:
    Seqlist() { length = 0; }
    Seqlist(const T a[], int n);
    int locate(T x);
    T get(int i);//获取第i个位置元素
    bool remove(int index);//删除给定下标的元素
private:
    T data[MAXSIZE];
    int length;

};


#endif //CPLUSPLUS_SEQLIST_H
