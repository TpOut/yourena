//
// Created by Jesson on 2019-02-12.
//

#include "静态.h"
//使用静态成员属性或者函数时候 需要使用 域运算符 ::

静态* 静态::instance = 0;
静态* 静态::getInstance() {

    if(!instance){
        instance = new 静态();
    }
    return instance;
}


