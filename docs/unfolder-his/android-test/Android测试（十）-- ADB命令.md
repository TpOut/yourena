
##测试分片
测试运行器支持将单一测试套件拆分成多个碎片，因此您可以将属于同一碎片的测试作为一个组在同一 Instrumentation 实例下运行。每个分片由一个索引号进行标识。运行测试时，使用 -e numShards 选项指定要创建的独立分片数量，并使用 -e shardIndex 选项指定要运行哪个分片。

例如，要将测试套件拆分成 10 个分片，且仅运行第二个碎片中的测试，请使用以下命令：
```
adb shell am instrument -w -e numShards 10 -e shardIndex 2
```