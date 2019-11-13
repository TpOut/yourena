
## 关联步骤  
在关联到gradle之后，脚本如果有修改，都要Build > Refresh Linked C++ Projects

### 可视化操作
右键module，选择 Link C++ Project with Gradle， 然后选择脚本文件即可   

### 手动关联
model-level  
```
android {
  ...
  defaultConfig {...}
  buildTypes {...}

  //可以在buildTypes中对每个变体单独配置
  externalNativeBuild {
    cmake {

      // Provides a relative path to your CMake build script.
      path "CMakeLists.txt"
    }
  }
}
```
如果是ndk-build，使用 ndkBuild 替代 cmake  
然后gradle会自动包含Android.mk同目录下的Application.mk 文件


### cmake其他配置
注意和关联配置不在一个区块层级   
详细gradle参数和YASM支持：https://developer.android.google.cn/ndk/guides/cmake  


```
defaultConfig {
    ...
        // Passes optional arguments to CMake.
        arguments "-DANDROID_ARM_NEON=TRUE", "-DANDROID_TOOLCHAIN=clang"

        // Sets a flag to enable format macro constants for the C compiler.
        cFlags "-D__STDC_FORMAT_MACROS"

        // Sets optional flags for the C++ compiler.
        cppFlags "-fexceptions", "-frtti"
}

productFlavors {
    ...
    demo {
        //控制编译目标
        targets "native-lib-demo",
                "my-executible-demo"
    }
}
```
## 指定jni目录
需要额外包含到项目中的预构建库
```
android {
    ...
    sourceSets {
        main {
            jniLibs.srcDirs 'imported-lib/src/', 'more-imported-libs/src/'
        }
    }
}
```
## 过滤ABI  
```
android {

    defaultConfig{
      ndk {
          // 如果没指定externalNativeBuild.cmake.abiFilters
          // 那么这里指定的，包含构建和打包
          // 否则这里只是打包的
          abiFilters 'x86', 'x86_64', 'armeabi', 'armeabi-v7a',
                   'arm64-v8a'
      }
    }
    ...
    externalNativeBuild {
      cmake{
        // 指定需要构建的
        abiFilters 'x86'
      }
    }

}
```
