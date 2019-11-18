

#ifndef C_DEMO_LEAKMEM_H
#define C_DEMO_LEAKMEM_H

#include <mm_malloc.h>
#include "stdio.h"

#define MALLOC(n) mallocEx(n, __FILE__, __LINE__)
#define FREE(p) freeEx(p)

void* mallocEx(size_t n, const char* file, const line);
void freeEx(void* p);
void PRINT_LEAK_INFO();

#endif //C_DEMO_LEAKMEM_H


