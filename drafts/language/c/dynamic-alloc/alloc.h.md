# alloc.h

```c
#include<stdlib.h>
#define malloc
#define MALLOC(num,type) (type *)alloc( (num) * sizeof(type) )
extern void *alloc(size_t size);
```



这里的define malloc 什么意思？