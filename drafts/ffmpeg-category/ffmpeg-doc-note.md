最开始写全局配置，先写完输入文件，再写输出文件  

## 命令缩写
i input
r rate
s subtitle  
a audio
v video
d data
f filter
c copy  


## 简写笔记

所有命令都是再下个输入/输出文件生效  
-map #    
-filter_complex,-lavfi，overlay,amix #复杂过滤器，前两个等同，是全局命令  
fps,setpts #简单的过滤器可以只修改fps，和时间戳  
0,1,2:3 #文件、流顺序，每个输入文件可以包含多个流（具体取决于封装的格式），哪怕是不同类型的流  
-codec copy #让ffmpeg忽略解码编码的过程   
-vn,-an,-sn,-dn #跳过对应的部分  

ffmpeg的解析是，调用avformat库（包含demuxer），从输入文件获取包含编码数据的packets，多文件的时候，会根据每个输入流的最小时间戳来保证顺序。  
然后packets被传递给解码器（没有streamcopy的时候）  
解析成最原始的数据，如音频的pcm  
然后通过过滤器解析，再传给编码器，再传给合成器来写入输出文件  

## 案例記錄
文件：[在demo目錄下查找]

```
//合成
String commands[] = new String[]{
        "ffmpeg", "-y",
        "-i", aacPath,
        "-i", h264Path,
        "-vf",
        //裁剪
        "crop=720:720:" + (avPartInfo.mVideoConfig.flip ? (avPartInfo.mVideoConfig.width > 720 ? avPartInfo.mVideoConfig.width - 720 : 0) : 0) + ":0"
                //翻转
                + (avPartInfo.mVideoConfig.flip ? ", hflip" : "")
               //旋轉
                + ", rotate=PI/2"
        ,
        size == 1 ? mp4Path : tsPath
```
合成ts的时候很快，合成mp4的时候要等很长时间，估计是封装的区别　　
```
//拼接
拼接最快的还是concat, 但是用这种方式ts　拼接成 mp4的时候会造成gsyVideoPlayer不支持　　
系统是支持的　　
```
