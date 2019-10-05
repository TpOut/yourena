//
// Created by Jesson on 2019-01-21.
//

//C++编译过程  https://www.cnblogs.com/mickole/articles/3659112.html

#include <iostream>

using namespace std;

//template<typename T>
//status_t writeByteVectorInternalPtr(Parcel* parcel,
//                                    const std::unique_ptr<std::vector<T>>& val)
//{
//    if (!val) {
//        return parcel->writeInt32(-1);
//    }
//
//    return writeByteVectorInternal(parcel, *val);
//}

//函数 重载 合并方法
template<typename T>
T sum(T a,T b){
    return a+b;
}

template<class T>
class CallBack{
public:
    void onError(){

    }

    int onSuccess(T result){
        cout<<result<<endl;
        //printf("%d",result);
    }
};

template<class T>
class HttpCallBack : public CallBack<T>{

};

int main(){
    std::cout<<sum(1,2)<<std::endl;
    std::cout<<sum(1.0,2.0)<<std::endl;
    printf("%.1f",sum(1.0,2.3));

    CallBack<string >* callBack = new CallBack<string >();
    callBack->onSuccess("sss");

    HttpCallBack<string>* httpCallBack =new HttpCallBack<string>();
    httpCallBack->onSuccess("hello");
}

