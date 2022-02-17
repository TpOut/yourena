if，for，while



break

```python
while(x < 1):
    print(x)
    break
```



可以添加else  

```python
for i in range(2, 10):
    print(i)
    # break # 如果添加这行，就不会打else  
else:  
    print("else")
```



if 如果很多怎么办，3.10 版本？  

```python
match x:
    case 1:
        print("不及格")
    case 2:
        print("及格")
    case 3 | 4:
        print("良好")
    case _:
        print("优秀")
```







