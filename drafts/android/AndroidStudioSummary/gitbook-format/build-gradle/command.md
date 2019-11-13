
## gradlew
所有的构建任务都是可以用gradle wrapper 命令行工具 执行的。  

```
./gradlew <task-name>  
```
示例：
```
#查看所有任务
./gradlew tasks

#debug包，已使用zipalign优化
gradlew assembleDebug

#debug包并安装
gradlew installDebug
```
