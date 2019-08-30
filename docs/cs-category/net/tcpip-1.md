##章1，概述
tcp/ip是对 tcp/ip protocol suite的常规简称，实际上tcp和ip只是protocol suite中的两个协议。  

tcp/ip是一个四层协议系统，从底到上为：

- 链路层，由驱动和网卡等处理物理传输媒介的信号。包括ARP,RARP  
- 网络层，处理分组在网络中的活动。包括IP,ICMP,IGMP  
- 传输层，处理端对端的通信。包括TCP,UDP  
- 应用层，处理应用程序细节。包括Telnet,Rlogin,FTP,SMTP,SNMP  

应用层和传输层使用端到端（End to End）的协议  
网络层提供的却是逐跳-点到点（Hop by hop）协议  
前者只需要在终端系统使用，而后者在任何设备（包括中端和终端）都需要使用  

对进行转发的系统，都可称之为Router；对运行应用程序的系统，都可以称之为主机。  

网桥，路由器  

ICMP主要被IP使用，但是应用程序也可以直接访问使用。  
Ping Traceroute  

###网络地址 
 
- A类：0 + 7位网络号 + 24位主机号
- B类：10 + 14位网络号 + 16位主机号
- C类：110 + 21位网络号 + 8位主机号
- D类：1110 + 28位多博组号
- E类：11110 + 27位备用号

转换后：

- A类：0.0.0.0 到 127.255.255.255
- B类：128.0.0.0 到 191...
- C类：192.0.0.0 到 223...
- D类：224.0.0.0 到 239...
- E类：240.0.0.0 到 247...

管理机构是Internet Network Information Centre    
只分配网络号，主机号由系统管理员负责  

主机号，网络号，单播、广播、多播   

TCP报文段是 TCP segment  
IP数据单元是 IP datagram  
以太网传输的比特流是 Frame

Frame的物理特性是长度必须在46-1500字节之间  

DEC-10的系统中，字节不是8 bit,通常使用octet表示；而现在的系统都采用8 bit,所以用byte表示  

###数据包封装过程
  ![捕获.PNG](https://upload-images.jianshu.io/upload_images/1936727-8c7a3654476d7279.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

和TCP不同的是， U D P传给 I P的信息单元称作 U D P数据报（UDP datagram），而且U D P的首部长为8字节  

![协议之间的依赖关系.PNG](https://upload-images.jianshu.io/upload_images/1936727-7d304b445afb05fa.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

由于T C P、 U D P、 I C M P和I G M P都要向I P传送数据，因此 I P必须在生成的 I P首部中加入某种标识，以表明数据属于哪一层。为此， I P在首部中存入一个长度为8 b i t的数值，称作协议域。 1表示为I C M P协议， 2表示为I G M P协议， 6表示为T C P协议， 1 7表示为U D P协议  
类似地，许多应用程序都可以使用 T C P或U D P来传送数据。运输层协议在生成报文首部时要存入一个应用程序的标识符。 T C P和U D P都用一个1 6 b i t的端口号来表示不同的应用程序。T C P和U D P把源端口号和目的端口号分别存入报文首部中。
网络接口分别要发送和接收 I P、 A R P和R A R P数据，因此也必须在以太网的帧首部中加入某种形式的标识，以指明生成数据的网络层协议。为此，以太网的帧首部也有一个 16 bit的帧类型域。

从硬件传输媒介到应用层的数据解包（去掉首部，并根据首部中的额外标识来确定上层协议）行为叫做分用（D e m u l t i p l e x i n g）。 
###端口号
FTP对应的TCP端口号是21，Telnet对应的TCP端口号是23；TFTP对应的UDP端口号是69  
其他知名服务的端口号都在1-1023之间，具体的分配由Internet Assigned Numbers Authrity管理。而常规的临时端口号在1024-5000之间，大于5000的用于不常用的服务。  
###文档
所有关于 I n t e r n e t的正式标准都以 R F C（Request for Comment）文档出版   
R F C的篇幅从 1页到2 0 0页不等。每一项都用一个数字来标识，如 RFC 11 2 2，数字越大说明R F C的内容越新   
**获取RFC**的邮件方式：

![](https://upload-images.jianshu.io/upload_images/1936727-189f14876e2ba7a3.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
端口号是奇数的历史原因：TCP的前身是NCP（ARPANET的传输层协议），属于单工协议，不是全双工的。所以每个应用需要两个端口号，一般都是相连的奇数偶数。而TCP成为标准的时候，就不需要一对了，取了奇数端口。  

>i n t e r n e t意思是用一个共同的协议族把多个网络连接在一起。而 I n t e r n e t指的是世界范围内通过T C P / I P互相通信的所有主机集合（超过 1 0 0万台）。 I n t e r n e t是一个i n t e r n e t，但i n t e r n e t不等于I n t e r n e t  

##链路层
T C P / I P支持多种不同的链路层协议，这取决于网络所使用的硬件，如以太网、令牌环网、 F D D I（光纤分布式数据接口）及 R S-2 3 2串行线路等，本书将详细讨论以太网链路层协议，两个串行接口链路层协议（ S L I P和P P P），以及大多数实现都包含的环回（ l o o p b a c k）驱动程序  

以太网I P数据报的封装是在RFC 894[Hornig 1984]中定义的， 
IEEE 802网络的I P数据报封装是在RFC 1042[Postel and Reynolds 1988]中定义的   
每台Internet主机默认要支持RFC 894分组，并可选支持RFC 1042  


MTU  

CSMA/CD
