基本需求为：

- 迷宫的组成为房间、墙、门
- 房间的四周是墙或者门，起码有一扇门。门的两边可能是房间或者出口。出口只有一个。
- 初始化时，玩家随机在某个房间中，通过上下左右在迷宫中移动
- 地图简单模式（全图可见）
- 操作简单模式（随意移动）

可能需求：

- 修改迷宫界面
- 操作困难模式（门密码，房间炸弹）
- 地图中等模式（路径可见），地图困难模式（当前可见）



按基本需求先写，为了简化，迷宫大小为4，代码为版本1。



设计中，玩家（User）所在的位置为一个房间（Room），房间有四个方向（平面）。

每个方向可能是：

- 一堵墙（Wall, 无法通过）

- 一扇门（Door, 打开后可通过，否则无法通过）
- 一个房间（可通过）

为了让判断延后到实例中统一处理，Wall, Door, Room 均继承自MapSite, 需要实现抽象方法Enter()



```java
//伪代码是
User.move(Direction){
  MapSite ms = mCurrentRoom.getMapSite(direction);
  boolean entered = ms.enter();
  if(entered){
    user.currentRoom = mCurrentRoom.nearbyRoom(direction);
  }else{
    ...
  }
}
```



