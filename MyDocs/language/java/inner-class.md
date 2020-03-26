```java
OuterClass.InnerClass innerObj = outerObj.new InnerClass() 

OuterClass.InnerClass innerObj = new OuterObj.InnerClass()
```

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-53b0caa0782894f5.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-c839075f86bde139.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)





访问该类定义所在的作用域中的数据，包括私有 可以对同一个包中的其他类隐藏 匿名内部类。

感觉可以理解成（内部机制另行研究）， 外部类如果构造在一个地址块，外部类的变量和内部类会被构造在该地址块内部，这样，内部类访问外部类的变量就好理解了。

内部类创建的时候，会由编译器合成一个带有外部类引用的构造器，可以分析对应的class文件（反射） 6.4.3，需要思考 ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-caf7d4e331865c78.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```java
public class TalkingClock{
    private boolean beep;
    class TimePrinter{
        public void doSomeThing(){
            if(beep){...}
        }
    }
}
```

![&#x5185;&#x90E8;&#x7C7B;&#x7684;&#x4F2A;&#x5B9E;&#x73B0;.PNG](https://upload-images.jianshu.io/upload_images/1936727-0bdece40a0a8611f.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) access$0对应上述伪实现的accessBeep ![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-f0b954259d692ffb.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以在方法快中定义类，这样基本做到完全隐藏，只对方法可见。（匿名内部类的真正语法）

![&#x6355;&#x83B7;.PNG](https://upload-images.jianshu.io/upload_images/1936727-21762462c5393631.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

\[图片上传失败...\(image-f9147a-1524051357145\)\]