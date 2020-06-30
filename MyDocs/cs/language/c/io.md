

可能需要查看#stdio.h

c不具备i/o语句，不具备异常处理语句，都是通过函数库实现的

### 输入

基准是stdin

当希望用户输入文件的时候，很自然的会用到gets和scanf方法来读取

#### scanf

会提示不安全，可以用strtol替换这里的。原来scanf是对stdin进行了字符转换，但是不够优秀

#### gets

会提示已经废弃，上cppreference查看，发现新标准11有gets\_s方法。原来是gets函数在误传数组而不是字符串的时候，容易溢出

但是使用11标准也无法找到gets\_s方法，gcc还会提示 undefined reference to \`gets\_s' 。原来\[这个方法是visual studio实现的，并且标准只是在附录中提到\]\([https://stackoverflow.com/questions/25593714/undefined-reference-to-gets-s](https://stackoverflow.com/questions/25593714/undefined-reference-to-gets-s)\#answer-25593782\)。

#### 结果

建议用fgets\(\)替换gets\(\) : 它会丢弃换行符，并在末尾存储一个NUL字节，只是需要指定输入的行大小。

> ```c
> char input[81];
> fgets(input, sizeof(input), stdin);
> ```
>
> 如果输入80个字符，input会只有80个字符，末尾**没有**换行符。
>
> 如果声明input\[80\]，输入80个字符，会有79个字符，末尾**没有**换行符
>
> 如果声明input\[82\]，输入80个字符，会有80个字符，末尾**有**换行符

```c
//以读取两个输入为例子
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>

#define NUL 0

int main() {

    int MAX_LENGTH_ONE_INPUT = 3;
    int input_length = 2 * MAX_LENGTH_ONE_INPUT + 1;
    
    int num;
    int offset;

    char input[input_length];
    fgets(input, sizeof(input), stdin);

    int space_time = 0;
    for (int i = 0; i < input_length; i++) {
        if (NUL == input[i] || isspace(input[i])) {
            space_time++;
            if (space_time == 2) {
                break;
            }
        } else {
            if (space_time == 0) {
                num = (int) strtol(input, NULL, 10);
                printf("%d", num);
            } else if (space_time == 1) {
                offset = (int) strtol(input + i, NULL, 10);
                printf("%d", offset);
            }
        }
    }

    return EXIT_SUCCESS;
}
```

从c本身的追求效率而言，我觉得还是不要这么搞吧。。

### stdio.h

stdio\_limit.h

```c
errno
void perror();

I/O 重定向

流错误相关函数
feof
ferror
clearerr 
```

```text
stdout  
stderr
```

```text
EOF
FOPEN_MAX  
TMP_MAX
FILENAME_MAX  
MAX_LINE_LENGTH  
BUFSIZ  

_IOFBF
_IONBF
_IOLBF
```

```text
fopen
freopen
fclose
fflush  

刷新和定位
ftell,fseek  
rewind
fgetpos
fsetpos  

改变缓冲方式
setbuf  
setvbuf
```

```text
临时文件
tempfile
tmpnam

文件操作函数
rename
remove
```

```text
二进制流I/O
getchar, putchar
gets/scanf, puts/printf  
fread, fwrite
```

```text
字符I/O
/*函数和宏*/
fgetc,getc ; getchar ;
fputc,putc ; putchar ;

未格式化的行I/O
fgets      ; gets    ;
fputs      ; puts    ;

格式化的行I/O 
fscanf     ; scanf   ; sscanf  
fprintf    ; printf  ; sprintf

//可变参
vprintf ;  vfprintf ; vsprintf

ungetc ‘回退’的字符具有位置关联性，如果fseek,fsetpos,rewind等改变了流的位置，‘回退’的字符会被丢弃
```

#### getchar  

```c
int ch = getchar()
//这里定义为 int 的原因是为了包含所有的char， 错误码 EOF必须大于char的大小 
```

