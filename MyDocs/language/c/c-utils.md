

```c
int calc_array_size(char arrays[]){
  return sizeof(arrays) / sizeof(arrays[0]);
}

int is_strs_equals(char *str1, char *str2){
  return strcmp(str1, str2) == 0;
}
```



### alloc

alloc.h

```c
#include<stdlib.h>
#define malloc
#define MALLOC(num,type) (type *)alloc( (num) * sizeof(type) )
extern void *alloc(size_t size);
```

alloc.c

```c
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
```

alloc_usage

```c
#include "alloc.h"

void
function(){
    int *new_memory;
    new_memory = MALLOC( 25,int );
}
```

