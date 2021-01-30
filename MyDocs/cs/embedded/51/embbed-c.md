```c
sfr // 特殊功能寄存器类型，8位
sfr16 // 同上，16位
sbit // 特殊功能位声明  
bit // 位变量  
  
  
sfr SCON = 0x98 // ?? 直接上地址？
sbit TI = SCON^1 // 获取第二位，小端
```

