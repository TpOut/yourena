### 1、the distance between two Coordinate points
```java
double s =R*Math.acos(Math.cos(lat1*pi/180) *Math.cos(lat2*pi/180)*Math.cos(lng1*pi/180 -lng2*pi/180)+
Math.sin(lat1*pi/180 )*Math.sin(lat2*pi/180));
```
