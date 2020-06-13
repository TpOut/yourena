使用 sensor batching 来减少电耗（不是每个传感器的事件都单独发送，而是一起处理）  

计步器自机器启动后开始计数，每次步数发生变化都会发事件  



地磁旋转：TYPE_GEOMAGNETIC_ROTATION_VECTOR  

陀螺仪：TYPE_ROTATION_VECTOR  

计步器：TYPE_STEP_DETECTOR、TYPE_STEP_COUNTER  



`SensorManager` 设置减少数据间隔  

[getFifoMaxEventCount()

flush  

