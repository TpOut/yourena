//
// Created by Jesson on 2019-03-22.
//

#ifndef CPLUSPLUS_ARRAYLIST_H
#define CPLUSPLUS_ARRAYLIST_H

#include <iostream>

using namespace std;

class ArrayList {
public:
    ArrayList();
    ArrayList(int initSize);
    ArrayList(const ArrayList &original);
    ~ArrayList();

    void insert(int position, int value);
    int get(int position);

    int getSize();
    void resize(int newSize);

    int &operator[](int index);
    bool operator==(ArrayList);
    bool operator!=(ArrayList);

private:
    int size;
    int *array;


};


#endif //CPLUSPLUS_ARRAYLIST_H
