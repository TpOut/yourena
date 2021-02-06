中断的简略逻辑如下，程序编译时就分在不同的区域，如有中断响应，  

会停止当前代码块的执行（理解成一个开关？

然后跳转到对应的中断代码块，执行完毕之后，重新开启上一层的代码块执行开关  

![image-20210207005826830](C:\Users\41675\AppData\Roaming\Typora\typora-user-images\image-20210207005826830.png)



而51 单片机的设计上，中断可以手动开启关闭  

主要有几个中断口 

- 外部中断
- 定时器中断
- 串口中断

![image-20210207010629357](C:\Users\41675\AppData\Roaming\Typora\typora-user-images\image-20210207010629357.png)



中断口有一些设置项，主要分为

- TCON

  可以设置外部中断的触发模式  

  已经中断发生时，内部会设置的中断位    

- IE 

  中断口开关（单独和所有

- IP

  优先级类别设置

串联图如下：

![image-20210207012207079](C:\Users\41675\AppData\Roaming\Typora\typora-user-images\image-20210207012207079.png)



中断号如下：

![image-20210207013247329](C:\Users\41675\AppData\Roaming\Typora\typora-user-images\image-20210207013247329.png)



