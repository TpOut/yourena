## CMake
构建脚本名必须为`CMakeLists.txt`   

### 语法
[语法列表](https://cmake.org/cmake/help/latest/manual/cmake-commands.7.html)   

**常规代码库**
```
# 其中hello-jni 是编译结果指导名称，实际会在前面加上lib前缀，即 lib*hello-jni*    
# 因为SHARED标识，生成.so 格式的文件，即 lib*hello-jni*.so  
# so文件在`{appPath}/build/intermediates/cmake/debug/obj/{ABI}/libhello-jni.so`
add_library(
            hello-jni
            SHARED
            hello-jni.c #源文件
            )  

include_directories(src/main/cpp/include/) # 在编译时查找头文件  

add_executable()
```

如果有修改库名或者移除库的操作，需要clean project  

**添加NDK API**: 可以直接使用[NDK里的api和库](https://developer.android.google.cn/ndk/guides/stable_apis)  

```
#已经构建好的库
#预构建的NDK库已经在Android平台中，所以不需要打包到apk中
#并且NDK库是CMake的默认搜索路径，所以不需要指定库路径
find_library(
    # name of the path variable
    log-lib
    # name of ndk lib
    log
  )  
#没有构建过的源码库,ANDROID_NDK是AS会默认配置的  
add_library(
    app-glue
    STATIC
    ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c
  )
```   

**添加非NDK中的预构建库**
```
add_library( imported-lib
             SHARED
             IMPORTED )  
# 配置路径
set_target_properties( # Specifies the target library.
            imported-lib

            # Specifies the parameter you want to define.
            PROPERTIES IMPORTED_LOCATION

            # ANDROID_ABI变量使用默认的ABI列表或者gradle配置后的列表  
            imported-lib/src/${ANDROID_ABI}/libimported-lib.so )  

include_directories( imported-lib/include/ )
```

**关联库**  
上面的库都需要关联  
除非某个预构建库不在构建时被依赖--比如一个imported库依赖了一个native的预构建库，那native的不需要被link：
```
target_link_libraries(
    native-lib
    imported-lib
    app-glue
    ${log-lib}  
  )
```

#### 嵌套CMake    
需要一个CMakeLists.txt 作为顶级脚本，然后把其他需要嵌套的项目作为这个脚本的依赖  
```
# Sets lib_src_DIR to the path of the target CMake project.
set( lib_src_DIR ../gmath )

# Sets lib_build_DIR to the path of the desired output directory.
set( lib_build_DIR ../gmath/outputs )
file(MAKE_DIRECTORY ${lib_build_DIR})

# Adds the CMakeLists.txt file located in the specified directory
# as a build dependency.
add_subdirectory( # Specifies the directory of the CMakeLists.txt file.
                  ${lib_src_DIR}

                  # Specifies the directory for the build outputs.
                  ${lib_build_DIR} )

# Adds the output of the additional CMake build as a prebuilt static
# library and names it lib_gmath.
add_library( lib_gmath STATIC IMPORTED )
set_target_properties( lib_gmath PROPERTIES IMPORTED_LOCATION
                       ${lib_build_DIR}/${ANDROID_ABI}/lib_gmath.a )
include_directories( ${lib_src_DIR}/include )

# Links the top-level CMake build output against lib_gmath.
target_link_libraries( native-lib ... lib_gmath )
```

### 版本说明  

#### SDK Manager的常规配置
```
# SDK Manager中包含 3.6.0 和 3.10.2
# Gradle 不配置的情况下CMake版本是3.6.0  

android{
    ...
    externalNativeBuild}{
        cmake {
            ...
            version "3.10.2"    
        }
    }
}
```
#### 非SDK Manager中的配置
- 下载地址（版本 >= 3.7）：https://cmake.org/download/  
- 和常规配置一样声明版本
- 需要配置 cmake的路径
 - 在PATH 环境变量中添加CMake路径  
 - 或者在local.properties中写入`cmake.dir="path-to-cmake"`  
- 还需要下载[Ninja](https://ninja-build.org/)并添加到PATH路径  

## ndk-build  
native code 放在jni目录下面    
Android.mk 描述库  
Application.mk 配置ABI: 默认所有,toolchain：默认Clang,release/debug:默认release,stl:默认system  

### ndk-build 实现纯native app  
https://developer.android.google.cn/ndk/guides/concepts#naa  

相关源码可以查看native-activity sample ;或者下面的  
```
<ndk_root>/sources/android/native_app_glue/android_native_app_glue.c  
<ndk_root>/sources/android/native_app_glue/android_native_app_glue.h  
```

## ndkCompile  
此工具已经被废弃，所以需要移植到CMake或者ndk-build

### 移植步骤
因为ndkCompile和ndk-build使用的脚本都是.mk，所以以ndk-build为例：

在Make之后，找到 `project-root/module-root/build/intermediates/ndk/debug/Android.mk`  
然后把文件放到一个合适的目录，并对应修改文件中路径的配置  
然后在gradle中关联脚本文件
并去除`android.useDeprecatedNdk = true`  
同步即可   
