变体虽然可以把多种情况写在一个页面，但是缺点很明显，太大了。。  
所以多apk也是有应用场景的，适用适当的屏幕分辨率、ABI  

## 配置  
在builde.gradle中，添加
```
splits {
    density {
        enable = true //打包多个apk

        exclude "","" //从打包列表中去除

        reset() //将默认列表清空
        include "ldpi", "xxhdpi"  //加入打包列表

        compatibleScreens "",""  //在manifest中插入。比在build.gradle中管理方便一些
    }
    abi {
       enable;exclue;reset();include //类似  

       universalApk = true //会创建一个适用所有abi的apk
    }
    //目前只支持Android Instant App
    language {

    }
}
```
density中没有universalApk是因为默认会创建适用所有screen的apk  

gradle插件3.1.0以上，不包含mips,mips64,armeabi；因为NDK r17不再支持  

生成的名字格式:modulename-screendensityABI-buildvariant.apk

### 版本问题  
同一个apk在google play中不能用同一个versionCode,所以要改一下  
universalApk的版本要低于其他确切的apk
```
android {
  ...
  defaultConfig {
    versionCode 4
  }
  splits {
  }
}

ext.abiCodes = ['armeabi-v7a':1, x86:2, x86_64:3]
// ext.densityCodes = ['mdpi': 1, 'hdpi': 2, 'xhdpi': 3]

import com.android.build.OutputFile

android.applicationVariants.all { variant ->

  variant.outputs.each { output ->

    def baseAbiVersionCode =
            project.ext.abiCodes.get(output.getFilter(OutputFile.ABI))

    if (baseAbiVersionCode != null) {
      // for only the output APK, not for the variant itself. Skipping this step simply
      // causes Gradle to use the value of variant.versionCode for the APK.
      output.versionCodeOverride =
              baseAbiVersionCode * 1000 + variant.versionCode
    }
  }
}
```
