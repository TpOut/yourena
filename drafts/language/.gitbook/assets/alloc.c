#include <stdio.h>
#include "alloc.h"
#undef malloc

void *
alloc( size_t size){
  /*
  ** 请求所需内存，并检查
  */
  new_mem = malloc( size );
  if( new_mem == NULL ){
    printf("Out of memory !\n");
    exit(EXIT_FAILURE);
  }
  return new_mem;
}
