发现欠缺很多，第一遍就不做记录了。复习时再记录

cpu指令集分类   

- RISC: reduced instruction set computing   
如sun公司的sparc系列，ibm的power architecture、arm系列  
- CISC: complex instruction set computing   
如啊amd,intel,via  


x86的来源是intel最初的cpu架构代号8086。  
而amd和via是被授权使用。  
后来从8位，升到16，升到32，升级到64后就是x86_64   

多媒体指令集：MMX,SSE,SSE2,SEE3,SEE4,AMD-3DNow  
虚拟化指令集：Inter-VT,AMD-SVM  
省电功能：Intel-SpeedStep,AMD-PowerNow  
64/32位兼容技术：AMD-AMD64,Intel-EM64T  

硬盘的最小物理量为512bytes，最小的组成单位为扇区(sector)  

cpu的性能， 以指令集的复杂数 * 每秒钟可以进行的工作次数（频率）  来衡量  
而复杂数只是个概念词，不同的cpu不好进行比较。所以可以通过频率来比较同系列的cpu性能  

外频指cpu与外部组件进行数据传输/运算时的速度，属于cpu的一部分    
倍频是指能够增加cpu内部性能（包括外频）的一个倍数，两者相乘才是cpu的频率。  
超频则是将外频或者倍频改高的方式。但是一般性倍频都是出厂锁定的，所以改的是外频。   

cpu和内存通信，每次工作的数据量上限由总线控制。  
一般主板芯片组分为北桥和南桥。前者为系统总线，速度较快，是内存传输的主要通道；后者是输入输出总线，用于“联系”硬盘，usb,网卡等接口设备。   

北桥支持的频率有333/400/533/800/1066/1333/1600MHz-- 这称为前端总线速度（Front side bus,FSB）  
而每次传送的位数，被称为总线宽度，常见的有32/64bit  
每秒钟的最大数据量，几总线频宽，就是 FSB * 总线宽度   

cpu每次梳理的数据量称为字组大小（word size），主要根据cpu设计分为32/64位 ，计算机的32位或者64位也是由此而来。  
所以内存在32位的cpu中，最多只支持4gb  
（问题：那么32位的cpu安装64位的系统呢？）
   
动态随机访问内存，Dynamic Random Access Memory , DRAM  
技术更新有 SDRAM, DDR(Double Data Rate) SDRAM  

SRAM, static random access memory  
rom, read only memory  
bios, basic input output system  

vga, video graphics array  


   
