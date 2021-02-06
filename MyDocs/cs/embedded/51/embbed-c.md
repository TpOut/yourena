```c
sfr // 特殊功能寄存器类型，8位
sfr16 // 同上，16位
sbit // 特殊功能位声明  
bit // 位变量  
  
#define led P2 // 直接使用P2 定义好的地址
P2 = 11111110 // 让最后一位为低电平
    
sfr SCON = 0x98 // ?? 直接上地址？
sbit TI = SCON^1 // 获取第二位，小端
    
// 中断函数
void int0() interrrupt 0 //using 1 （可选
{
    
}
```

