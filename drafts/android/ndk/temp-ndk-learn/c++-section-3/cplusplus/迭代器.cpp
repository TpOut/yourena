//
// Created by Jesson on 2019-02-11.
//

/***
 * 迭代器是一种检查容器内元素并遍历元素的数据类型。
 * 它是一种行为类似指针的对象，它提供类似指针的功能，对容器的内容进行走访，而指针的各种行为中最常见也最重要的是“成员访问”。
 * 因此，迭代器最重要的编程工作就是对operator*和operator->进行重载工作。
 */
#include<iterator>
#include <vector>
#include <utility>
#include <iostream>
#include <cstring>
#include <string>
#include <list>

using namespace std;
typedef pair<string,string> author;


int main(){

    vector<int>::iterator iter;  // 将iter声明为int类型的向量迭代器
    vector<int> vec;
    /***
     * 迭代器类型iterator:
     * 元素的只读迭代器类型const_iterator、
     * 按逆序寻址元素的迭代器reverse_iterator、
     * 元素的只读逆序迭代器const_reverse_iterator。
     */


    vector<int>::const_iterator iter2;
    vector<int>::reverse_iterator iter3;
    vector<int>::const_reverse_iterator iter4;


    vector<int>::iterator iter5 = vec.begin();
    /***
     * begin()返回的迭代器指向容器内第一个元素，
     * end()返回的迭代器指向容器内“最后一个元素的下一个位置”，
     * end()操作返回的迭代器并不指向vector中任何实际的元素，它只是起到一个哨兵的作用，表示我们已经处理完vector中的所有元素。
　　    rbegin()返回一个逆序迭代器，它指向容器的最后一个元素，rend()返回一个逆序的迭代器，它指向容器的第一个元素前面的位置。
     */

    *iter2; iter2++; iter2+1; iter2--; //用于迭代器的寻址


    /***
     * pair 重要的理解 本质上是key-value
     */

    //pair<int,string> author;
//
//    template <class _T1, class _T2>
//    struct _LIBCPP_TEMPLATE_VIS pair
//#if defined(_LIBCPP_DEPRECATED_ABI_DISABLE_PAIR_TRIVIAL_COPY_CTOR)
//    : private __non_trivially_copyable_base
//#endif
//    {
//        typedef _T1 first_type;
//        typedef _T2 second_type;
//
//        _T1 first;
//        _T2 second;
//
//#if !defined(_LIBCPP_CXX03_LANG)
//        pair(pair const&) = default;
//        pair(pair&&) = default;

    author aa("name","jesson");
    author bb("age","33");

    cout<<aa.second<<bb.first<<endl;

    int a = 8;
    string m = "James";
    pair<int, string> newone;

    newone = make_pair(a, m);
    int num = newone.first;
    string str = newone.second;
    cout<<num<<str<<endl;
}