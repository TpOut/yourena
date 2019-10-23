c++ 标准的整形长度借鉴自c，在其已有的基础上，添加了一条

long long 至少64位，且比long 要长



int 对于机器而言是最“自然”的长度，所以一般性使用int

一般除非大型数组，否则没啥必要用short，当然尽量用（编写角度）

如果整数值可能大于16位整数，用long；可以增加移植性

如果超过20亿，则使用long long



c++中字节 和实现的基本字符集有关，

如ASCII，足够使用8位容纳，则c++字节是8

如果使用unicode，则可能需要相邻8位来容纳，因此有些实现可能是 16或者32位

术语octet 专门表示8位的字节

sizeOf 方法返回的字节数，即C++ 的字节



climits 文件是由编译器厂商提供的，所以不同系统的编译器会有不同的文件。



进制： 42，042， 0x42

对于不带后缀的10进制，存储最小选择：int， long， long long

对于不带后缀的16、8进制，最小选择：int，unsigned int, unsigned long, long long/unsigned long long

![企业微信截图_cbb21020-b55d-4e36-9ce0-632c8df9b8da](%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_cbb21020-b55d-4e36-9ce0-632c8df9b8da.png)

