//
// Created by Jesson on 2019-03-22.
//

#include "ArrayList.h"


ArrayList::ArrayList() {
    ArrayList(5);
}

ArrayList::ArrayList(int initSize) {
    size = initSize;
    array = new int[size];
    for (int i = 0; i < size; i++) {
        array[i] = 0; // Fill with zeroes
    }
}

ArrayList::ArrayList(const ArrayList &original) {
    size  = original.size;
    array = new int[size];
    for (int i = 0; i < size; i++) {
        array[i] = original.array[i];
    }
}

ArrayList::~ArrayList() {
    delete[] array;
}


void ArrayList::insert(int position, int value) {
    array[position] = value;
}

int ArrayList::get(int position) {
    return array[position];
}

int ArrayList::getSize() {
    return size;
}

void ArrayList::resize(int newSize) {
    int *temp;
    temp = new int[newSize];
    for (int i = 0; i < (newSize); i++) {
        temp[i] = array[i];
    }
    delete[] array;
    array = temp;
    size = newSize;
}

bool ArrayList::operator==(ArrayList a)  {
    if (a.size != size) return false;

    for (int i = 0; i < (a.size); i++) {
        if (a[i] != array[i]) {
            cout << "false" << endl;
            return false;
        } else{
            cout << "true" << endl;
            return true;
        }
    }
}

bool ArrayList::operator!=(ArrayList a)  {
    if (a.size != size) return true;

    for (int i = 0; i < (a.size); i++) {
        if (a[i] != array[i]) return true;
    }
}

int &ArrayList::operator[](int index) {
    if ((index - 1) > size) {
        resize(index + 1);
    }
    return array[index];
}


int main(){
    cout << "begin" << endl;

    ArrayList array = ArrayList(4);
    array.insert(1, 7);
    array.insert(3, 2);
    array[2] = 4;
    array[10] = 4;


    ArrayList array2 = ArrayList(array);
    if (array == array2) {
        cout << "array == array2" << endl;
    }

    array[1] = 9999111;
    if (array != array2) {
        cout << "array != array2" << endl;
    }
    cout << array.get(0) << endl;
    cout << array.get(1) << endl;
    cout << array.get(2) << endl;
    cout << array.get(3) << endl;
    return 0;
}
