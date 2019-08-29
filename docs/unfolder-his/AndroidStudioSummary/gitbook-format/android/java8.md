## java8特性    
https://developer.android.google.cn/studio/write/java8-support
as 3.0以上支持所有java7，以及部分java8功能。虽然你可以设置成源兼容、目标兼容都是java7，但还是要用jdk8编译     
源兼容、目标兼容设置，在project structure > app > properties, 或者在build.gradle中：  
```
android {
  ...
  // Configure only for each module that uses Java 8
  // language features (either in its source code or
  // through dependencies).
  compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
  }
}
```
现在as实现java8的方式是一种内置（工具）链，  
.java被javac编译成.class,然后通过工具进行bytecode级转码（这个转码过程叫做desugar），转码后的.class再合入第三方插件打包成dex  
```
#禁止使用,在gradle.properties中
android.enableDesugar=false
```

特性/API和兼容的minSdkVersion（在不断增加中）：https://developer.android.google.cn/studio/write/java8-support/#supported_features  
>3.0还支持 try-with-resources的写法了。  
>desugar目前在minSdkVersion26以上支持 MethodHandle.invoke or MethodHandle.invokeExact  

### 过去式
由于desugar的存在,需要移植原先使用的[Jack 工具]，  
[Retrolambda](https://github.com/luontola/retrolambda), [DexGuard](https://www.guardsquare.com/en/products/dexguard).
> 移植方法看原文咯
