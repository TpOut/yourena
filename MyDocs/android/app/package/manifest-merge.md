合并manifest的规则

## 优先级

按优先级,以高优先级的为主体,把低优先级的manifest合入其中

library << main << variant lib之间的顺序,根据依赖声明顺序;varaint之间的优先级在变体章节有讲过

> build可以修改manifest的一些值.对于这些值,统一在build中设置,而不在manifest中会省却很多问题.

## 冲突解决(heuristics)

合并工具 的合并粒度会处理到 manifest 的 element 的 attribute

如果相同element的相同attribute具有不同的值,会报冲突错误.
冲突解决部分默认策略有:

- manifeset中元素的属性不会合并, 直接使用高优先级的属性值
- `<uses-feature>`和`<uses-library>`的 android:required属性值会使用OR合并,即所有都会合入
- `<uses-sdk>`:一般按第一条.除非低优先级的minSdkVersion比较高,需要设置overrideLibrary;低优先级的targetSdkVersion更小,使用高优先级的并添加需要的权限
- `<intent-filter>`不会被比较,都会被添加.

注意: 如果高优先级的manifest没有定义属性,即使用默认属性;而低优先级的manifest定义了这个属性. 那么合并后使用的是低优先级的.

## 合并规则标记(marker)

```
# 添加命名空间
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myapp"
    xmlns:tools="http://schemas.android.com/tools">
```

### 节点标记

```
# merge, 默认行为,合并所有
<activity ...
    tools:node="merge">
</activity>
# merge-only-attributes, 合并当前所在元素和属性,不处理嵌套子元素

# remove, 合并时去除当前元素和属性
<activity-alias ...>
  <meta-data android:name="cow"
      tools:node="remove"/>
</activity-alias>
# removeAll, 合并时去除所有具有相同元素名的元素
<activity-alias ...>
  <meta-data tools:node="removeAll"/>
</activity-alias>
# replace, 不比较直接替换
# strict,  对默认行为的一些行为也进行报错处理
```

### 属性标记

可以组合运用

```
# 比如去除依赖库的某个属性
tools:remove="attr, ..."
# 总是保留
tools:replace="attr, ..."
# 默认行为,有不同的时候会导致构建错误
tools:strict="attr, ..."
```

### 选择器标记

```
<permission android:name="permissionOne"
    tools:node="remove"
    tools:selector="com.example.lib1">
```

示例中的remove只对 com.example.lib1 生效
同样可以和属性标记联合使用

### 覆盖导入库的

```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.app"
          xmlns:tools="http://schemas.android.com/tools">
  <uses-sdk android:targetSdkVersion="22" android:minSdkVersion="2"
            tools:overrideLibrary="com.example.lib1, com.example.lib2"/>
```

### 隐式系统权限

权限管理原因,tartgetSdkVersion较低的情况下,有些系统权限可以使用.

而如果库的targetSdkVersion较低,而app的较高(无法声明对应的所需权限). 合并工具 会帮你显式添加
|版本|权限| |:--:|:--:| |<=3|WRITE_EXTERNAL_STORAGE, READ_PHONE_STATE| | <=15和 READ_CONTACTS | READ_CALL_LOG | | <=15和 WRITE_CONTACTS | WRITE_CALL_LOG |

### 检查合并后的manifest并找出冲突

你可以在manifest的底部,找到merged manifest页面来预览合并后的manifest
右侧分别是来源、无关来源、选中的行的合并来源

如果有合并问题，还会有错误提示;也可以在工具窗口 event log中找到
错误日志在`build/outputs/logs/manifest-merger-buildVariant-report.txt`

### 附录：合并协议

合并工具对于元素的唯一性判定是根据一个“match key”:可以是唯一的属性如`android:name`或者tag本身的唯一如`<supports-screen>`
对于两个相同的元素，会执行以下策略之一：

- merge: 合并tag下的所有不冲突属性；如果冲突，则根据标记规则处理
- merge children only: 不合并，只保留高优先级minifest里的属性；然后根据合并策略处理子元素
- keep: 对于相同的父元素，保留所有的子元素。

具体到每个元素的合并协议，可以查看原文底部的表格。https://developer.android.google.cn/studio/build/manifest-merge