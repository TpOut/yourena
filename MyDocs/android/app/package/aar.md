AAR 文件的文件扩展名为 .aar，Maven 工件类型也应当是 aar。文件本身是一个包含以下强制性条目的 zip 文件：

/AndroidManifest.xml
/classes.jar
/res/
/R.txt
此外，AAR 文件可能包含以下可选条目中的一个或多个：

/assets/
/libs/名称.jar
/jni/abi 名称/名称.so（其中 abi 名称 是 Android 支持的 ABI 之一）
/proguard.txt
/lint.jar

### 生成方式

模块 Build > Make Project 后可以在`项目名称/模块名称/build/outputs/aar/`中找到对应的aar

### 资源私有性

而aar作为库时，所有资源在默认情况下均处于公开状态，资源包括您项目的 res/ 目录中的所有文件

如果要设置资源私有，需要在库的 res/values/ 目录中创建 public.xml，如：

```
<resources>
    <public name="mylib_app_name" type="string"/>
    <public name="mylib_public_string" type="string"/>
</resources>
```

此时，所有没有在xml中指定的资源都是私有的。不会被ide用做 代码补全(工具)、主题编辑器自动提示的来源，且在使用者试图引用的时候给出lint警告。
除了以上好处，在库更新的时候，因为限制使用，可以更大限度的修改库的非公开部分而不影响使用者的代码。

#### 库模块添加ProGuard

库的progurd会被包含在aar中，构建时，会被附加到app的proguard.txt
添加lib的proguardfile:

```
 android {  
    defaultConfig {  
        consumerProguardFiles 'lib-proguard-rules.txt'  
    }  
    ...    
}
```

你在单独运行的时候，需要注意app和lib两者的混淆。
但如果你的lib是作为module依赖，则不需要额外运行lib的混淆。

#### 注意事项

- 资源合并冲突：为了避免，添加一致的唯一性前缀是一个好方案。

    > 如果lib和app的资源重复，则使用app的。如果多个aar库之间冲突，则使用先依赖的（即dependencies 块中顶部的）

- lib可以包含jar库: 但是要手动编辑相关app的构建路径，并添加jar文件的路径

- lib不支持assets目录的资源

- app的minSdkVersion >= lib的minSdkVersion: 总之要API兼容

- 每个lib库都有自己的R类： 会根据Lib的软件包名称命名。所以app和Lib库中都会有Lib的R类