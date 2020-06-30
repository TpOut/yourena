https://ffmpeg.org/ffmpeg-all.html  

目前已有摄像头传回的yuv和音频录制设备的pcm数据，个人认为是一帧一帧的流数据    
所以需要将其对应转换成h264编码和acc编码，再合成mp4格式  
因为文档非常大，所以挑需要的看先（会以节为基本单位）。  

##概要
```  
ffmpeg [global_options] {[input_file_options] - i input_url}...{[output_file_options] output_url}...     
```

## 说明
任何不能被解释的命令都会被理解成 output_url  
除了global的是一次生效，其他前置命令(options)会被紧跟的（输入/输出）文件消耗，一条命令行可以多次重复使用命令。  
虽然可以多个输入输出，但是请一次性写完输入文件，再写完输出文件，而不是把输入输出乱序写入  

```
-i 输入  
```

## 细节
对于文件的处理过程简单如下：  

input file   
=> (demuxer) => encoded data packets    
=> (decoder) => decoded frames  
(=> (filter)=> filtered frames)
=> (encoder) => encoded data packets  
=> (muxer) => output file   

### 过滤  
```
#命令
-filter #(-vf/-af)  
-filter_complex  #等同 -lavfi     
#过滤器
fps #修改帧数
setpts  #设置每帧的时间戳  
```
简单过滤器只适合单一输入输出，且两者文件格式一样的情况。   
否则需要使用复杂过滤器,注意复杂过滤器是complex的  

### 流复制  
```
-codec copy  
```  
可以省略编解码的过程，而只是进行合成/拆解  

## 流选择  （暂不需要）  
除了被复杂过滤器处理的流，可以对流文件进行选择  
```
-map 选择映射  
-vn #video
-an #audio
-sn #subtitle
-dn #data
```  
## 选项  
数值单位，K/M/G 等SI单位，1000倍数; i作为SI单位后缀的时候表示1024倍数；B作为SI后缀则再乘以8;  
如KB,MiB,G,B  

所有不带参数的选项都是将对应的值设置为true,如果要设置成false，则在参数前面添加no即可，如-nofoo  

### 流说明符  
很多流相关的选项，后面可以用冒号跟一些说明符，表示具体的对象。
```
-codec:a:1 ac3  #ac3的编解码器作用在audio的第二个流上
-b:a 128k  #所有音频流  
[-codec|-codec:] copy  #所有流
-threads:1 4 #表示第二个流的线程数为4
```  

这里有些额外流说明符，v表示视频，V表示没有附着图片(包括缩略图或封面)的视频；t表示attachments  

### 常规选项
https://ffmpeg.org/ffmpeg-all.html#Generic-options  
