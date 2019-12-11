归并排序

```java
List src = {6, 202, 100, 301, 38, 8, 1}
first = {6, 202} {100, 301} {38, 8} {1}
...
beforeEnd = {6, 100, 202, 301} {1, 8, 308}

void merge(List left, List right){
  List dest
  int length = Min(left.length, right.length)
  int i = 0;
  pick(left[i], right[i])
}

void pick(...){
  if(left[i] == null || right[i] == null){return}
  if(left[i] > right[i]){
    dest.add(right[i])
    pick(left[i], right[i+1])
  }else{
    pick(...)
  }
}
```



