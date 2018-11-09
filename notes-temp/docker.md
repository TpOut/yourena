## 索引  
https://docs.docker.com/

linux 代理可能出现的问题： https://docs.docker.com/get-started/part2/#run-the-app  

Docker Trusted Registry(DTR): https://docs.docker.com/datacenter/dtr/2.4/guides/  

docker-machine:  https://docs.docker.com/machine/get-started/#create-a-machine  

Docker hub,即发布后的地址: https://hub.docker.com/  
通过docker hub可以存image，那么image或者service的配置文件呢？请看docker云：  
https://docs.docker.com/docker-cloud/    

compose(无法访问):   https://docs.docker.com/compose/overview/  

## 内容  
https://docs.docker.com/get-started/  

对于官网的  
![container解释](https://www.docker.com/sites/default/files/Container%402x.png)和![VM解释](https://www.docker.com/sites/default/files/VM%402x.png)  

有点奇怪为何一个是Host OS, 一个是Hypervisor  

## 内容概览
docker提供了打包和运行app的能力，但是需要一个前提，就是在一个叫做container的松散独立环境中，他是发布和测试app的最小单元。。  
足够轻量，无需额外的hypervisor，直接在kernel中运行。  

## 底层技术　　
docker用go实现并拥有linux 内核的部分功能优势。


## container format
docker engine 将命名空间,cgroups,unionFS合并入一个wrapper,这个wrapper被叫做ontainer format。  
默认为libcontainer，未来可能会支持BSD Jails or Solaris Zones.
### 命名空间  
container的独立空间即通过namespaces来实现，也算一个layer  
  类型有
The pid namespace: Process isolation (PID: Process ID).
The net namespace: Managing network interfaces (NET: Networking).
The ipc namespace: Managing access to IPC resources (IPC: InterProcess Communication).
The mnt namespace: Managing filesystem mount points (MNT: Mount).
The uts namespace: Isolating kernel and version identifiers. (UTS: Unix Timesharing System).

### control groups  
docker engine同样依赖于control gourps(cgourps)技术。用来控制资源独立、共享，使用量等使用。    

### Union file systems
文件系统层  
Docker Engine can use multiple UnionFS variants, including AUFS, btrfs, vfs, and DeviceMapper.

### docekr engine
Docker is licensed under the open source Apache 2.0 license.  

docker engine 是一个client-server应用，即docker的整个系统。它是一个守护进程(docker daemon : dockerd);具有REST API;集成CLI客户端（docker）  
即CLI 以脚本的方式 通过REST API控制（交互）dockerd。基于Unix sockets或者网络端口（client-server可以分离）    

dockerd可以管理对象如 images, containers, networks, volumes ..  
也可以与其他dockerd交互来管理 service  

docker store: https://store.docker.com/ 可以售卖获取image  

https://docs.docker.com/engine/images/engine-components-flow.png   

### docker Registry
registry存储image。比如docker hub 和docker cloud是所有人都能还是用的公共registry，  
docker从registry获取image, 默认配置为docker hub   
Docker Datacenter（DDC） 包含了DTR  

### image  
image是一个只读的结构模板，用来创建container。image一般是基于另一个image进行自定义  
运行image的时候，dockerfile的配置会被设置为一个layer，当配置修改并重建时，只是修改了这层layer    

## orientation

### Docker概念
Docker是一个使用者（开发或者系统管理）通过container操作（开发、部署、运行）应用的平台  
Linux container部署app的功能叫做containerization（这里限定linux是文档没及时更新吧？）  

containerization不断受欢迎的原因：
- 够灵活：即使很复杂的app也能被containerized
- 够轻量：container有效利用和共享了主机的内核
- Interchangeable：可以on-the-fly式更新
- portable:本地编译，云部署，任何地方运行
- scalable:随意复制、自动发布
- stackable:堆栈式且on-the-fly式放置服务

image:一个包涵了运行应用所需事物（代码、运行环境、库、配置文件）的可运行的包
container：是一个运行时的image，包涵了image状态、用户进程等。它运行在Linux本地且和其他container共享宿主机器的内核。运行在独立(discrete)进程。   
vm(virtual machine):通过Hypervisor建立一个，会模拟物理设备的整个操作系统。


### 将docker添加到非root用户
Docker daemon绑定unix socket ，而不是TCP端口。默认情况下unix socket需要root，否则需要使用sudo访问。  
所以Docker daemon永远以root 用户的形式运行。  
```
$ sudo groupadd docker  
$ sudo usermod -aG docker $USER  
```

## containers
container是image一个运行的实例，你可以对它执行start,stop,move,create,delete  
可以将container连接到一个或多个网络，接续存储，也可以从container当前状态创建一个image  
remove不会保存container当前的状态  

运行container时
docker会分派一个读写系统给container,做为最后的layer  
docker创建网络接口。。开启container并执行bash  
最后可以用exit退出    

container => service => stack     
dockerfile,定义了所需环境,包括访问资源时需要的网络接口和磁盘驱动       
感觉是由于共享目录的存在，启动应用后可以运行Dockerfile上的命令。  


### 共享image
一个仓库的集合叫做registry，而一个仓库是image的集合。一个账户对应一个registry  
docker命令行默认使用公开的registry，但是你可以自己装一个DTR  

## service  
service让你能够在多个dockerd上scale container。也允许你设置一些需要的状态，如同时的服务副本数量    
默认情况下，service是在worker nodes上是负载均衡的。  
而对于使用者(consumer)而言，service只是一个app  

services其实就是产品中的containers。一个service只运行一个image,但是换了一种方式：有一些配置信息。  
这些配置信息，包括端口、副本数量、进程资源等等  
而配置只需要一个 docker-compose.yml文件    

运行在service中的单独的container被叫做taask。taks会被赋予一个自增的数字id，这个数字id基于配置文件中的replicas  

这里涉及了三个命令，swarm,stack,service  
个人看命令输出是觉得，service就是container合集，stack又是service的组织方式。  

## swarm    
多个dockerd工作的组织叫做swarm，可以有多个manager和其余的worker  

将app部署到cluster,并运行在多个机器上。  
 Multi-container, multi-machine applications are made possible by joining multiple machines into a “Dockerized” cluster called a swarm.  

### 理解  
swarm是一组运行docker并被归入cluster的机器。因此也需要一个管理，即swarm manager。这些机器可以是实体也可以是虚拟的，它们被称为swarm的节点。  
swarm manager在管理时可以设置策略，如emptiest node -- 最小单元化； global -- 完全实例化。 当然也可以在配置文件中定义。  
牢记swarm manager是swarm中唯一可以执行命令的机器。  

目前为止，dokcer的使用都是本地机器的独立主机模式。但是docker是可以转换成swarm模式的，这会把当前机器设置为swarm manager   

## stack  
stack是一组内部关联的service,这些service共享依赖，并且可以被orchestrated and scaled together。  
stack足够部署一个应用。只是很多复杂应用需要多个stack  

部署redis的示例：  
使用的是官方的image，所以不需要配置image：value; 而6379端口是redis的预设。在这种结构下，所有节点都可以通过redis桌面管理器操作redis。  
更重要的是：redis总在manager上运行；redis会把主机上的/data目录当做数据存储目录；  

### 配置  

**创建集群**  
windows 10带hyper-v
运行hyper-v管理器，virtual switch manager => create virtual switch(external) => name,check share network
```
docker-machine create -d hyperv --hyperv-virtual-switch "myswitch" myvm1
docker-machine create -d hyperv --hyperv-virtual-switch "myswitch" myvm2
```
其他，先安装virtual box，再安装docker-machine（桌面版自带）,执行：  
```
docker-machine create --driver virtualbox myvm1
docker-machine create --driver virtualbox myvm2  
```
**链接vm的区别**  
docker-machine env  
会输出一些export和命令，你可以执行命令，让shell配置，这样就可以直接和vm交互。  

docker-machine ssh <machine> "<command>"  
的方式可以直接登录vm，但是不能操作主机文件   

```
# 跨机器拷贝，windows下需要git bash等linux terminal
docker-machine scp <file> <machine>:~  
```

## 命令
```
#查看命令  
docker
docker container --help
#查看信息
docker --version
docker version
docker info
#运行测试镜像
docker run hello-world  
#查看镜像
docker image ls  
#查看容器(running, all, all in quiet mode)
docker container ls
docker container ls --all
docker container ls -aq
docker ps -a
#删除镜像,如果有多个镜像,可以指定名称进行删除
docker rmi [imgId|imgName]  
docker image rm $(docker image ls -a -q)  #删除所有  
#删除容器
docker rm <container hash>  
docker container rm <hash>


# 创建app,末尾的.表示当前目录下的dockerfile  
docker build -t friendlyhello .
# 运行app,对port : 4000 转化为 80 ; linux宿主使用localhost:4000访问, windows 需要使用localIp:4000
docker run -p 4000:80 friendlyhello    
# 后台运行  
docker run -d -p 4000:80 friendlyhello  
# 停止容器运行
docker container stop <container id>  
docker container kill <container hash>

# 登录
docker login  
# 给镜像打标签，用来区分和标记版本  
docker tag imageName username/repository:tag  
# 上传
docker push username/repository:tag

# 列出容器id
docker container ls -q

# swarm模式
docker swarm init  
# 从任何节点离开swarm
docker swarm leave
# 从manager离开
docker swarm leave --force  
# 在其他机器上运行，来加入swarm  
docker swarm join

# 服务
docker stack rm <appname>  
docker stack ls
# 部署服务,支持修改属性后重新运行立即生效  
docker stack deploy -c <composefile> <serviceName>

docker service ls  
docker service ps <service>  
docker inspect <task or container>  

# 集群  
docker-machine ls  
# ssh进入虚拟机,并执行“command”命令  
docker-machine ssh <machineName> "command"
# 初始化swarm,docker-machine ls命令会显示端口号为2376，这是daemon的端口。所以这里不能用，一般可以设置为2377或者不写  
docker swarm init --advertise-addr 192.168.99.100   
# 将myvm2加入到myvm1的swarm中，token是上面myvm1初始化swarm之后返回的
docker swarm join --token SWMTKN-1-5q563y5uytmtydmr7i745whalm2z8ejivahlfwmfc6ofd7j7x7-92kddt6kcrscjxg4wj874k06s 192.168.99.100:2377
# 在manager上查看节点信息
docker node ls
# 将环境变量配置到shell的方式打印出来，执行最后的提示语之后，vm会变成active
docekr-machine env <machineName>    
# 重置env的配置
eval $(docker-machine env -u)  

docker-machine start <machine-name>  
```

Dockerfile  
```
# Use an official Python runtime as a parent image
FROM python:2.7-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install any needed packages specified in requirements.txt
RUN pip install --trusted-host pypi.python.org -r requirements.txt

# Make port 80 available to the world outside this container
EXPOSE 80

# Define environment variable
ENV NAME World

# Run app.py when the container launches
CMD ["python", "app.py"]

```
requirements.txt  
```
Flask
Redis
```
app.py  
```
from flask import Flask
from redis import Redis, RedisError
import os
import socket

# Connect to Redis
redis = Redis(host="redis", db=0, socket_connect_timeout=2, socket_timeout=2)

app = Flask(__name__)

@app.route("/")
def hello():
    try:
        visits = redis.incr("counter")
    except RedisError:
        visits = "<i>cannot connect to Redis, counter disabled</i>"

    html = "<h3>Hello {name}!</h3>" \
           "<b>Hostname:</b> {hostname}<br/>" \
           "<b>Visits:</b> {visits}"
    return html.format(name=os.getenv("NAME", "world"), hostname=socket.gethostname(), visits=visits)

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)
```
docker-compose.yml    
```
version: "3"
services:
  web:
    # replace username/repo:tag with your name and image details
    image: username/repo:tag
    deploy:
      replicas: 5
      resources:
        limits:
          cpus: "0.1"
          memory: 50M
      restart_policy:
        condition: on-failure
    ports:
      - "4000:80"
    networks:
      - webnet  

  visualizer:
    image: dockersamples/visualizer:stable
    ports:
      - "8080:8080"
    volumes:
      - "/var/run/docker.sock:/var/run/docker.sock"
    deploy:
      placement:
        constraints: [node.role == manager]
      networks:
        - webnet
  redis:
    image: redis
    ports:
      - "6379:6379"
    volumes:
      - "/home/docker/data:/data"
    deploy:
      placement:
        constraints: [node.role == manager]
      command: redis-server --appendonly yes
      networks:
        - webnet  
networks:
  webnet:
```
## 疑问  
* 使用负载均衡的案例之后，chrome无法访问localhost:4000，但是firefox可以

初步查询得到如下：https://bugs.chromium.org/p/chromium/issues/detail?id=489973

* 为什么service需要建立在swarm之上？
