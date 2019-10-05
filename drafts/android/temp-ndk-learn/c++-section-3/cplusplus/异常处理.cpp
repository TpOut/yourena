//
// Created by Jesson on 2019-03-22.
//


#include <iostream>
#include <stdexcept>

using namespace std;

class Exception{
public:
    string message;
    Exception(char* msg){
        this->message = msg;
    }

    virtual const char* what() const{  //这里要注意 需要在Androidstudio下试一试
        return message.c_str();
    }
};


void yewu() throw(int,Exception)  {

    ///
}

void MyFunc(int c)
{
    if (c > numeric_limits< char> ::max())
        throw invalid_argument("MyFunc argument too large.");
    //...
}



//jni
int main(){
    try {
        int i =0;
        if(i == 0){
            throw 10.0;
            //throw new Exception("error");
        }

        //throw new Exception("error");
        //MyFunc(256);

    }catch (int num){
        cout<<"捕获到了异常"<<endl;
    }catch (Exception* ex){ //注意这里的* 另外这里的异常要交给Java处理  看bitmap的代码
        cout<<ex->what()<<endl;
    }catch (invalid_argument& e){
        cerr << e.what() << endl;
    }catch  (...){
        //捕获所有的异常 IOException RunTimeException Exception
        cout<<"捕获到了其他的异常信息"<<endl;
    }
//    catch (...){
//        cout<<"捕获到了其他的异常信息"<<endl;
//    }

}