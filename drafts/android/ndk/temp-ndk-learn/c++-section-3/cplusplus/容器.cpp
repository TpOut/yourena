//
// Created by Jesson on 2019-01-22.
//

#include <vector>
#include <queue>
#include <string.h>
#include <iostream>
#include <stack>
#include <algorithm>
#include <list>
#include <map>


using namespace std;
/**
 *  打印vetor的数据内容
 * @param v
 */
void printvector(vector<int> v){
//    vector<int>::iterator i = v.begin();
    vector<int>::iterator it=v.begin();
    for(it;it!=v.end();++it){
        cout<<*it<<" ";
    }
    cout<<endl;
}



//int main(){
//    //实例化
//    vector<int> vec1;//默认初始化，vec1为空
//    vector<int> vec2(vec1);//使用vec1初始化vec2
//    vector<int> vec3(vec1.begin(),vec1.end());//使用vec1初始化vec2
//    vector<int> vec4(10);//10个值为0的元素
//    vector<int> vec5(10,4);//10个值为4的元素
//    vector<string> vec6(10);//10个值为null的元素
//    cout<<"获取容器容量大小"<<vec5.capacity()<<endl;
//    cout<<"获取容器容量大小"<<vec5.size()<<endl;
//
//    vector<int>::iterator iterator = vec5.begin(); //编译器要实现c++11
//    vec5.insert(iterator,8);
//
//
//    vec5.push_back(100);
//    vec5.push_back(101);
//
//    cout<<"获取容器容量大小"<<vec5.capacity()<<endl;
//    cout<<"获取下标元素"<<vec5[0]<<endl;
//    cout<<"获取下标元素"<<vec5[11]<<endl;
//
//    //注意C++在语法层面没有规定vector内部缓冲区提前增加容量的大小 这取决于STL的实现
//
//    printvector(vec5);
//
//    cout<<vec5.front()<<endl;
//    vec5.end();
//    vec5.clear();
//    vec5.erase(vec5.begin(),vec5.end());
//
//    vec5.size();//元素的个数
//
//    cout<<"获取容器容量大小"<<vec5.capacity()<<endl;//思考这个数据 怎么让容量是0
//
//    vector<int>().swap(vec5); //交换函数
//    cout<<"获取容器容量大小"<<vec5.capacity()<<endl;
//
//    return -1;
//}

//------------自适应容器--------------------------LIFO----------

//int main(){
//    //实例化
//    stack<int> stack1;
//    stack<vector<int>> stack2;
//    stack<int> stack3(stack1);
//
//    stack1.push(1);
//    stack1.push(11);
//    stack1.push(111);
//    stack1.push(1111);
//
//    while (stack1.size()>0){
//        cout<<"top-"<<stack1.top()<<endl;
//        stack1.pop();
//    }
//
//    cout<<stack1.empty()<<endl; //1 true 0 false
//
//
//    queue<int> queue1;
//    queue1.push(100);
//    queue1.push(200);
//    queue1.push(300);
//    while (queue1.size()>0){
//        queue1.pop();
//    }
//
//    cout<<queue1.size()<<endl;
//
//    //优先级队列
//    priority_queue<string,vector<string>,less<string>> priority_queue1;
//    priority_queue1.push("abs");
//    priority_queue1.push("ccc");
//    priority_queue1.push("ddd");
//    priority_queue1.push("wqwqw");
//    while(!priority_queue1.empty()){
//        cout<<"top-"<<priority_queue1.top()<<endl;
//        priority_queue1.pop();
//    }
//}
//


//-----------优先级队列----实现自定义排序------
//struct Node
//{
//    char szName[20];
//    int  priority;
//    Node(int nri, char *pszName)
//    {
//        strcpy(szName, pszName);
//        priority = nri;
//    }
//};
//
//struct NodeCmp
//{
//    bool operator()(const Node &na, const Node &nb)
//    {
//        if (na.priority != nb.priority)
//            return na.priority <= nb.priority;
//        else
//            return strcmp(na.szName, nb.szName) > 0;
//    }
//};
//
//void printfNode(const Node &na)
//{
//    printf("%s %d\n", na.szName, na.priority);
//}
//
//
//int main(){
//
//    priority_queue<Node, vector<Node>, NodeCmp> a;
//
//
//    a.push(Node(5, "小谭"));
//    a.push(Node(3, "小刘"));
//    a.push(Node(1, "小涛"));
//    a.push(Node(5, "小王"));
//    //队头的2个人出队
//    printfNode(a.top());
//    a.pop();
//    a.pop();
//    printf("--------------------\n");
//
//    //再进入3个人
//    a.push(Node(2, "小白"));
//    a.push(Node(2, "小强"));
//    a.push(Node(3, "小新"));
//    //所有人都依次出队
//    while (!a.empty()) {
//        printfNode(a.top());
//        a.pop();
//    }
//}

//----------------------演示list的用法-----------------
#include <numeric>

////创建一个list容器的实例LISTINT
//typedef list<int> LISTINT;
////创建一个list容器的实例LISTCHAR
//typedef list<int> LISTCHAR;
//
//int main(){
//    LISTINT listOne;
//    LISTINT::iterator i;
//
//    listOne.push_front (2);
//    listOne.push_front (1);
//
//    listOne.push_back (3);
//    listOne.push_back (4);
//
//    //从前向后显示listOne中的数据
//    cout<<"listOne.begin()--- listOne.end():"<<endl;
//    for (i = listOne.begin(); i != listOne.end(); ++i)
//        cout << *i << " ";
//    cout << endl;
//
//    //从后向后显示listOne中的数据
//    LISTINT::reverse_iterator ir;
//    cout<<"listOne.rbegin()---listOne.rend():"<<endl;
//    for (ir =listOne.rbegin(); ir!=listOne.rend();ir++) {
//        cout << *ir << " ";
//    }
//    cout << endl;
//
//    //使用STL的accumulate(累加)算法
//    int result = accumulate(listOne.begin(), listOne.end(),0);
//    cout<<"Sum="<<result<<endl;
//    cout<<"------------------"<<endl;
//
//    //--------------------------
//    //用list容器处理字符型数据
//    //--------------------------
//
//    //用LISTCHAR创建一个名为listOne的list对象
//    LISTCHAR listTwo;
//    //声明i为迭代器
//    LISTCHAR::iterator j;
//
//    //从前面向listTwo容器中添加数据
//    listTwo.push_front ('A');
//    listTwo.push_front ('B');
//
//    //从后面向listTwo容器中添加数据
//    listTwo.push_back ('x');
//    listTwo.push_back ('y');
//
//    //从前向后显示listTwo中的数据
//    cout<<"listTwo.begin()---listTwo.end():"<<endl;
//    for (j = listTwo.begin(); j != listTwo.end(); ++j)
//        cout << char(*j) << " ";
//    cout << endl;
//
//    //使用STL的max_element算法求listTwo中的最大元素并显示
//    j=max_element(listTwo.begin(),listTwo.end());
//    cout << "The maximum element in listTwo is: "<<char(*j)<<endl;
//}

//int main() {
//
//    map<int, string> mapStudent;
//
//    mapStudent.insert(pair<int, string>(1, "student_one"));
//
//    mapStudent.insert(pair<int, string>(2, "student_two"));
//
//    mapStudent.insert(pair<int, string>(3, "student_three"));
//
//    map<int, string>::iterator iter;
//
//    for (iter = mapStudent.begin(); iter != mapStudent.end(); iter++)
//        cout << iter->first << ' ' << iter->second << endl;
//
//    map<int, string> mapStudent2;
//
//    mapStudent2[1] = "student_one";
//
//    mapStudent2[2] = "student_two";
//
//    mapStudent2[3] = "student_three";
//
//    map<int, string>::iterator iter2;
//
//    for(iter2 = mapStudent2.begin(); iter2 != mapStudent2.end(); iter2++)
//        cout<<iter2->first<<' '<<iter2->second<<endl;
//
//    multimap <int, string> phonebook;
//    multimap <int, string>::iterator iterator1;
//    multimap <int, string>::iterator iterator2;
////    phonebook[1]="jesson";
//    phonebook.insert(pair<int,string>(3,"jesson"));
//    phonebook.insert(map<int,string>::value_type (3,"ahello"));
//    phonebook.insert(map<int,string>::value_type (4,"hello4"));
//    phonebook.insert(map<int,string>::value_type (5,"hello5"));
//    //    phonebook.insert("Harry","8225687"); // 家里电话
////    phonebook.insert("Harry","555123123"); // 单位电话
////    phonebook.insert("Harry"," 2532532532"); // 移动电话
// //   iterator1 = phonebook.find("Harry");
//    for(iterator1 = phonebook.begin(); iterator1 != phonebook.end(); iterator1++)
//        cout<<iterator1->first<<' '<<iterator1->second<<endl;
//    if(phonebook.size()>0) {
//        iterator2 = phonebook.find(5); //找到后立即返回指针，如果没有找到返回尾指针
//        cout<<(*iterator2).first<<"==="<<(*iterator2).second<<endl; //野指针
//        cout<<"***************************************"<<endl;
//    }
//}


//int main(){
//    //stack 解决进制转换，二叉树的中序遍历等等
//    stack<int> stk_fir;
//    stack<string> stk_sed;
//
//    stk_fir.push(10086);   // 栈顶入栈元素10086
//    stk_fir.push(10087);   // 栈顶入栈元素10087
//    stk_fir.push(10088);   // 栈顶入栈元素10088
//    stk_fir.pop();         // 删除一个栈顶元素
//    int a = stk_fir.top();         // 访问栈顶一个元素（而queue可以访问队首和队尾，分别是front()和back()方法）
//    cout<<a<<endl;
//    stk_fir.size();        // 返回栈中元素个数
//    //stk_fir.swap(stk_sed); // 交换两个相同类型的stack中所有数据
//    stk_fir.empty();       // 栈中为空则返回true
//}
//
//迭代器的多重用法
//int main(){
//    //HEAP
//    int nArr[10] = {0,2,8,9,1,4,3,7,5,6};
//    vector<int> vec(nArr,nArr+10);    //构建载体vector
//    make_heap(vec.begin(),vec.end()); //围绕vec构建一个堆
//    sort_heap(vec.begin(),vec.end()); //堆排序
//    for (int i = 0; i<vec.size(); ++i)
//    {
//
//        cout << vec[i]  << endl;
//    }
//
//    //第二种遍历方式，迭代器
//    cout << "第二种遍历方式，迭代器访问" << endl;
//    for (auto iter = vec.begin(); iter != vec.end(); iter++)
//    {
//        cout << (*iter)<< endl;
//    }
//    cout << "第3种遍历方式，auto" << endl;
//    //第二种遍历方式，auto
//    for(auto i : vec){
//        cout << i<< endl;
//    }
//}