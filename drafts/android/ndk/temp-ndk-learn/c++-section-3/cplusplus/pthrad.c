//
// Created by jesson on 2019-01-09.
//

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>


void* fun(void* arg){
    char* str = (char*)arg;
    int i=0;
    for (; i < 10; i++) {
        printf("%s线程的数据是,i是%d\n",str,i);
    }
    return 1;
}

void main_p(){
    printf("main thread");
    pthread_t* tid;
    pthread_create(&tid,NULL,fun,"1");
    //sleep(100);
    int* rval;
    pthread_join(tid,&rval);

}