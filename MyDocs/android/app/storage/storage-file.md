context 现在封装了很多文件读写方法:

```kotlin
context.openFileOutput(filename, Context.MODE_PRIVATE).use {
        it.write(fileContents.toByteArray())
}
context.openFileInput(filename).bufferedReader().useLines { lines ->
    lines.fold("") { some, text ->
        "$some\n$text"
    }
}
//  /res/raw
openRawResource()  
// 文件列表
 context.fileList()
// 递归创建  
context.getDir(dirName, Context.MODE_PRIVATE)  
```

