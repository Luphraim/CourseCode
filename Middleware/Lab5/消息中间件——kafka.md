# 消息中间件——kafka

## 前言



## 安装&配置

服务器：CentOS 7.7

安装前先确保已安装`jdk`和`zookeeper`：

### 安装`jdk`

安装之前先检查一下系统有没有自带open-jdk

```bash
$ rpm -qa |grep java
$ rpm -qa |grep jdk
$ rpm -qa |grep gcj
```

如果没有输入信息表示没有安装。

如果安装可以使用`rpm -qa | grep java | xargs rpm -e --nodeps`批量卸载所有带有Java的文件。

检索包含Java 1.8的列表

```bash
$ sudo yum list java-1.8*
```

安装1.8.0的所有文件

```bash
$ sudo yum install java-1.8.0-openjdk\* -y
```

检查是否安装成功

```bash
$ java -version
```

### 安装`zookeeper`

```bash
# 下载
$ wget http://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.5.7/apache-zookeeper-3.5.7-bin.tar.gz

# 解压
$ tar -zxvf apache-zookeeper-3.5.7.tar.gz

# 移动到local目录
$ sudo mv apache-zookeeper-3.5.7 /usr/local
$ cd /usr/local/apache-zookeeper-3.6.1/conf

# 创建数据存储目录与日志目录
$ mkdir ../dataDir
$ mkdir ../dataLogDir

# 编辑配置文件,修改dataDir和dataLogDir
$ cp zoo_sample.cfg zoo.cfg
$ vim zoo.cfg
```

![image-20200505195446622](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/image-20200505195446622.png)s

依赖JVM环境，所以JVM和zookeeper环境变量都要配置：

![image-20200505200536301](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/image-20200505200536301.png)

然后生效：

```bash
$ sourse /etc/profile
```

测试能否正常启动：

```bash
# 启动
$ /usr/local/apache-zookeeper-3.6.1/bin/zkServer.sh start
/usr/bin/java
ZooKeeper JMX enabled by default
Using config: /usr/local/apache-zookeeper-3.6.1/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED

# 查看状态
$ ./zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /usr/local/service/zookeeper/apache-zookeeper-3.5.7-bin/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Mode: standalone

# 连接并测试增删查改功能
$ ./zkCli.sh
```

![image-20200505210338441](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/image-20200505210338441.png)

功能正常，`zookeeper`配置完成。

### 安装`kafka`

```bash
# 下载
$ wget http://mirror.bit.edu.cn/apache/kafka/2.5.0/kafka_2.13-2.5.0.tgz

# 解压
$ tar -zxvf kafka_2.13-2.5.0.tgz

# 移动到local目录
$ sudo mv kafka_2.13-2.5.0 /usr/local
$ cd /usr/local/kafka_2.13-2.5.0/config

# 编辑配置文件
$ cp server.properties server.properties.bak
$ vim server.properties
```

主要修改如下几处：

```
broker.id=123  #每台服务器的broker.id都不能相同

# 监听地址要注意，需要使用真实ip，即ifconfig显示的ip，或者就是服务器的内网ip，而不是公网ip，否则会报错
listeners=PLAINTEXT://ip:9092 #监听地址

log.dirs=/usr/local/kafka_2.12-2.2.0/logs

#在log.retention.hours=168 下追加
message.max.byte=5242880
default.replication.factor=2
replica.fetch.max.bytes=5242880

#设置zookeeper的连接端口
zookeeper.connect=ip:2181
```

```bash
# 启动服务
$ bin/kafka-server-start.sh config/server.properties
```

![image-20200505212707766](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/image-20200505212707766.png)

使用`jps`查看：

![image-20200505213706208](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/image-20200505213706208.png)

看到上面的显示即代表`kafka`服务启动成功了，不过如果需要后台运行的话需要自行使用`nohup`和`&`将进程调度到后台。

## Spring调用



## 参考资料

https://www.cnblogs.com/zwcry/p/10244908.html

https://www.cnblogs.com/huangjianping/p/8012580.html

https://www.cnblogs.com/tarencez/p/10887931.html