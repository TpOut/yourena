# typedef

用于简化声明

```c
typedef struct {
  int age;
  char *name;
} Simple;

//然后可以
Simple x;
```

```c
typedef char *ptr_to_char;
//然后可以
ptr_to_char a;
```

define陷阱：

```c
char *a, b; //表示a是指针，b不是
  
#define ptr_to_char char *
ptr_to_char a, b; //a是指针，b不是

typedef char * ptr_to_char;  
ptr_to_char a, b; //a，b都是指针
```

