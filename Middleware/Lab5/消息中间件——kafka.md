# 消息中间件——kafka

## 前言

KafKa是一款由 Apache 软件基金会开源，使用 Scala 编写的一个 高吞吐 分布式
 发布 - 订阅 消息系统 和一个强大的队列，可以处理大量的数据，并使您能够将消息从一个端点传递到另一个端点。

Kafka适合 离线 和 在线 消息消费。 Kafka消息保留在磁盘上，**并在群集内复制以防止数据丢失**。 **Kafka构建在ZooKeeper同步服务之上**。 它与Apache Spark Streaming 非常好地集成，用于实时流式数据分析。

### 背景介绍

#### 消息队列

消息队列技术是分布式应用间交换信息的一种技术。消息队列可驻留在内存或磁盘上, 队列存储消息直到它们被应用程序读走。通过消息队列，应用程序可独立地执行--它们不需要知道彼此的位置、或在继续执行前不需要等待接收程序接收此消息。在分布式计算环境中，为了集成分布式应用，开发者需要对异构网络环境下的分布式应用提供有效的通信手段。为了管理需要共享的信息，对应用提供公共的信息交换机制是重要的。常用的消息队列技术是 Message Queue。

Message Queue 的通讯模式

1. 点对点通讯：点对点方式是最为传统和常见的通讯方式，它支持一对一、一对多、多对多、多对一等多种配置方式，支持树状、网状等多种拓扑结构。
2. 多点广播：MQ 适用于不同类型的应用。其中重要的，也是正在发展中的是"多点广播"应用，即能够将消息发送到多个目标站点 (Destination List)。可以使用一条 MQ 指令将单一消息发送到多个目标站点，并确保为每一站点可靠地提供信息。MQ 不仅提供了多点广播的功能，而且还拥有智能消息分发功能，在将一条消息发送到同一系统上的多个用户时，MQ 将消息的一个复制版本和该系统上接收者的名单发送到目标 MQ 系统。目标 MQ 系统在本地复制这些消息，并将它们发送到名单上的队列，从而尽可能减少网络的传输量。
3. 发布/订阅 (Publish/Subscribe) 模式：发布/订阅功能使消息的分发可以突破目的队列地理指向的限制，使消息按照特定的主题甚至内容进行分发，用户或应用程序可以根据主题或内容接收到所需要的消息。发布/订阅功能使得发送者和接收者之间的耦合关系变得更为松散，发送者不必关心接收者的目的地址，而接收者也不必关心消息的发送地址，而只是根据消息的主题进行消息的收发。
4. 群集 (Cluster)：为了简化点对点通讯模式中的系统配置，MQ 提供 Cluster(群集) 的解决方案。群集类似于一个域 (Domain)，群集内部的队列管理器之间通讯时，不需要两两之间建立消息通道，而是采用群集 (Cluster) 通道与其它成员通讯，从而大大简化了系统配置。此外，群集中的队列管理器之间能够自动进行负载均衡，当某一队列管理器出现故障时，其它队列管理器可以接管它的工作，从而大大提高系统的高可靠性。

#### Kafka 创建背景

Kafka 是一个消息系统，原本开发自 LinkedIn，用作 LinkedIn 的活动流（Activity Stream）和运营数据处理管道（Pipeline）的基础。现在它已被[多家不同类型的公司](https://cwiki.apache.org/confluence/display/KAFKA/Powered+By) 作为多种类型的数据管道和消息系统使用。

活动流数据是几乎所有站点在对其网站使用情况做报表时都要用到的数据中最常规的部分。活动数据包括页面访问量（Page View）、被查看内容方面的信息以及搜索情况等内容。这种数据通常的处理方式是先把各种活动以日志的形式写入某种文件，然后周期性地对这些文件进行统计分析。运营数据指的是服务器的性能数据（CPU、IO 使用率、请求时间、服务日志等等数据)。运营数据的统计方法种类繁多。

近年来，活动和运营数据处理已经成为了网站软件产品特性中一个至关重要的组成部分，这就需要一套稍微更加复杂的基础设施对其提供支持。

#### Kafka 简介

Kafka 是一种分布式的，基于发布 / 订阅的消息系统。主要设计目标如下：

- 以时间复杂度为 O(1) 的方式提供消息持久化能力，即使对 TB 级以上数据也能保证常数时间复杂度的访问性能。
- 高吞吐率。即使在非常廉价的商用机器上也能做到单机支持每秒 100K 条以上消息的传输。
- 支持 Kafka Server 间的消息分区，及分布式消费，同时保证每个 Partition 内的消息顺序传输。
- 同时支持离线数据处理和实时数据处理。
- Scale out：支持在线水平扩展。

### 为何使用消息系统

- **解耦**：在项目启动之初来预测将来项目会碰到什么需求，是极其困难的。消息系统在处理过程中间插入了一个隐含的、基于数据的接口层，两边的处理过程都要实现这一接口。这允许你独立的扩展或修改两边的处理过程，只要确保它们遵守同样的接口约束。
- **冗余**：有些情况下，处理数据的过程会失败。除非数据被持久化，否则将造成丢失。消息队列把数据进行持久化直到它们已经被完全处理，通过这一方式规避了数据丢失风险。许多消息队列所采用的 " 插入 - 获取 - 删除 " 范式中，在把一个消息从队列中删除之前，需要你的处理系统明确的指出该消息已经被处理完毕，从而确保你的数据被安全的保存直到你使用完毕。
- **扩展性**：因为消息队列解耦了你的处理过程，所以增大消息入队和处理的频率是很容易的，只要另外增加处理过程即可。不需要改变代码、不需要调节参数。扩展就像调大电力按钮一样简单。
- **灵活性 & 峰值处理能力**：在访问量剧增的情况下，应用仍然需要继续发挥作用，但是这样的突发流量并不常见；如果为以能处理这类峰值访问为标准来投入资源随时待命无疑是巨大的浪费。使用消息队列能够使关键组件顶住突发的访问压力，而不会因为突发的超负荷的请求而完全崩溃。
- **可恢复性**：系统的一部分组件失效时，不会影响到整个系统。消息队列降低了进程间的耦合度，所以即使一个处理消息的进程挂掉，加入队列中的消息仍然可以在系统恢复后被处理。
- **顺序保证**：在大多使用场景下，数据处理的顺序都很重要。大部分消息队列本来就是排序的，并且能保证数据会按照特定的顺序来处理。Kafka 保证一个 Partition 内的消息的有序性。
- **缓冲**：在任何重要的系统中，都会有需要不同的处理时间的元素。例如，加载一张图片比应用过滤器花费更少的时间。消息队列通过一个缓冲层来帮助任务最高效率的执行———写入队列的处理会尽可能的快速。该缓冲有助于控制和优化数据流经过系统的速度。
- **异步通信**：很多时候，用户不想也不需要立即处理消息。消息队列提供了异步处理机制，允许用户把一个消息放入队列，但并不立即处理它。想向队列中放入多少消息就放多少，然后在需要的时候再去处理它们。

### Kafka术语

在深入理解Kafka之前，先介绍一下Kafka中的术语。下图展示了Kafka的相关术语以及之间的关系：

![KafkaTerminologies](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/o_KafkaTerm.png)

上图中一个topic配置了3个partition。Partition1有两个offset：0和1。Partition2有4个offset。Partition3有1个offset。副本的id和副本所在的机器的id恰好相同。

如果一个topic的副本数为3，那么Kafka将在集群中为每个partition创建3个相同的副本。集群中的每个broker存储一个或多个partition。多个producer和consumer可同时生产和消费数据。

各个术语的详细介绍如下：

- **Topic：**在Kafka中，使用一个类别属性来划分数据的所属类，划分数据的这个类称为topic。如果把Kafka看做为一个数据库，topic可以理解为数据库中的一张表，topic的名字即为表名。
- **Partition：**topic中的数据分割为一个或多个partition。每个topic至少有一个partition。每个partition中的数据使用多个segment文件存储。partition中的数据是有序的，partition间的数据丢失了数据的顺序。如果topic有多个partition，消费数据时就不能保证数据的顺序。在需要严格保证消息的消费顺序的场景下，需要将partition数目设为1。
- **Partition offset：**每条消息都有一个当前Partition下唯一的64字节的offset，它指明了这条消息的起始位置。
- **Replicas of partition：**副本是一个分区的备份。副本不会被消费者消费，副本只用于防止数据丢失，即消费者不从为follower的partition中消费数据，而是从为leader的partition中读取数据。
- Broker：
  - Kafka 集群包含一个或多个服务器，服务器节点称为broker。
  - broker存储topic的数据。如果某topic有N个partition，集群有N个broker，那么每个broker存储该topic的一个partition。
  - 如果某topic有N个partition，集群有(N+M)个broker，那么其中有N个broker存储该topic的一个partition，剩下的M个broker不存储该topic的partition数据。
  - 如果某topic有N个partition，集群中broker数目少于N个，那么一个broker存储该topic的一个或多个partition。在实际生产环境中，尽量避免这种情况的发生，这种情况容易导致Kafka集群数据不均衡。
- **Producer：**生产者即数据的发布者，该角色将消息发布到Kafka的topic中。broker接收到生产者发送的消息后，broker将该消息追加到当前用于追加数据的segment文件中。生产者发送的消息，存储到一个partition中，生产者也可以指定数据存储的partition。
- **Consumer：**消费者可以从broker中读取数据。消费者可以消费多个topic中的数据。
- **Leader：**每个partition有多个副本，其中有且仅有一个作为Leader，Leader是当前负责数据的读写的partition。
- **Follower：**Follower跟随Leader，所有写请求都通过Leader路由，数据变更会广播给所有Follower，Follower与Leader保持数据同步。如果Leader失效，则从Follower中选举出一个新的Leader。当Follower与Leader挂掉、卡住或者同步太慢，leader会把这个follower从“in sync replicas”（ISR）列表中删除，重新创建一个Follower。

### Kafka分布式架构

![img](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/v2-f04083507c2860e62a686c3e868c719a_720w.jpg)

如上图所示，kafka将topic中的消息存在不同的partition中。如果存在键值（key），消息按照键值（key）做分类存在不同的partiition中，如果不存在键值（key），消息按照轮询（Round Robin）机制存在不同的partition中。默认情况下，键值（key）决定了一条消息会被存在哪个partition中。

partition中的消息序列是有序的消息序列。kafka在partition使用偏移量（offset）来指定消息的位置。一个topic的一个partition只能被一个consumer group中的一个consumer消费，多个consumer消费同一个partition中的数据是不允许的，但是一个consumer可以消费多个partition中的数据。

kafka将partition的数据复制到不同的broker，提供了partition数据的备份。每一个partition都有一个broker作为leader，若干个broker作为follower。所有的数据读写都通过leader所在的服务器进行，并且leader在不同broker之间复制数据。



![img](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/v2-e9b8513d58089eee6a131278ea949502_720w.jpg)

上图中，对于Partition 0，broker 1是它的leader，broker 2和broker 3是follower。对于Partition 1，broker 2是它的leader，broker 1和broker 3是follower。



![img](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/v2-a247b3d0f9fc622224f69bbdda1ab933_720w.jpg)

在上图中，当有Client（也就是Producer）要写入数据到Partition 0时，会写入到leader Broker 1，Broker 1再将数据复制到follower Broker 2和Broker 3。



![img](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/v2-9f15b48732fb965a8ed0644cc902bd67_720w.jpg)

在上图中，Client向Partition 1中写入数据时，会写入到Broker 2，因为Broker 2是Partition 1的Leader，然后Broker 2再将数据复制到follower Broker 1和Broker 3中。

上图中的topic一共有3个partition，对每个partition的读写都由不同的broker处理，因此总的吞吐量得到了提升。

### 常用 Message Queue 对比

|                |                  ActiveMQ                   |                           RabbitMQ                           |                            Kafka                             |                           RocketMQ                           |
| :------------: | :-----------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| 所属社区/公司  |                   Apache                    |                    Mozilla Public License                    |                            Apache                            |                             Ali                              |
|     成熟度     |                    成熟                     |                             成熟                             |                             成熟                             |                           比较成熟                           |
|    授权方式    |                    开源                     |                             开源                             |                             开源                             |                             开源                             |
|    开发语言    |                    Java                     |                            Erlang                            |                          Scala&Java                          |                             Java                             |
| 客户端支持语言 |   Java、C、C++、Python、PHP、Perl、.net等   | 官方支持Erlang、Java、Ruby等，社区产出多种语言API，几乎支持所有常用语言 | 官方支持Java，开源社区有多语言版本，如PHP、Python、Go、C/C++、Ruby、NodeJS等 |                     Java、C++（不成熟）                      |
|    协议支持    |      OpenWire、STOMP、REST、XMPP、AMQP      |             多协议支持：AMQP、XMPP、SMTP、STOMP              |               自有协议，社区封装了HTTP协议支持               |            自己定义的一套（社区提供JMS--不成熟）             |
|  消息批量操作  |                    支持                     |                            不支持                            |                             支持                             |                             支持                             |
|  消息推拉模式  |          多协议，Pull/Push均有支持          |                  多协议，Pull/Push均有支持                   |                             Pull                             |                  多协议，Pull/Push均有支持                   |
|       HA       | 基于Zookeeper+LevelDB的Master-Slave实现方式 |       Master/Slave模式，Master提供服务，Slave仅作备份        | 支持replica机制，leader宕掉后，备份自动顶替，并重新选举leader（基于Zookeeper） | 支持多Master模式、多Master多Slave模式，异步复制模式、多Master多Slave模式、同步双写 |
|   数据可靠性   |                Master/Slave                 |              可以保证数据不丢，有Slave用作备份               |          数据可靠，并有replica机制，有容错容灾能力           |        支持异步实时刷盘、同步刷盘、同步复制、异步复制        |
|   单机吞吐量   |                最差（万级）                 |                         其次（万级）                         |                        次之（十万级）                        |                        最高（十万级）                        |
|    消息延迟    |                      /                      |                            微秒级                            |                            毫秒级                            |                          比kafka快                           |
|   持久化能力   |             内存、文件、数据库              |    内存、文件，支持数据堆积，但数据堆积反过来影响生产速率    |        磁盘文件，只要磁盘容量够，可以做到无限消息堆积        |                           磁盘文件                           |
|    是否有序    |                可以支持有序                 |                 若想有序，只能使用一个Client                 |                       多Client保证有序                       |                             有序                             |
|      事务      |                    支持                     |                            不支持                            |        不支持，但可以通过Low Level API保证仅消费一次         |                             支持                             |
|      集群      |                    支持                     |                             支持                             |                             支持                             |                             支持                             |
|    负载均衡    |                    支持                     |                             支持                             |                             支持                             |                             支持                             |
|    管理界面    |                    一般                     |                             较好                             | 官方只提供了命令行版，Yahoo开源自己的Kafka Web管理界面Kafka-Manager |                          命令行界面                          |
|    部署方式    |                    独立                     |                             独立                             |                             独立                             |                             独立                             |

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

### Spring Kafka的注解

Spring Kafka相关的注解有如下几个：

| 注解类型           | 描述                                                         |
| ------------------ | ------------------------------------------------------------ |
| EnableKafka        | 启用由`AbstractListenerContainerFactory`在封面(covers)下创建的Kafka监听器注解端点，用于配置类； |
| EnableKafkaStreams | 启用默认的Kafka流组件                                        |
| KafkaHandler       | 在用KafkaListener注解的类中，将方法标记为Kafka消息监听器的目标的注解 |
| KafkaListener      | 将方法标记为指定主题上Kafka消息监听器的目标的注解            |
| KafkaListeners     | 聚合多个KafkaListener注解的容器注解                          |
| PartitionOffset    | 用于向KafkaListener添加分区/初始偏移信息                     |
| TopicPartition     | 用于向KafkaListener添加主题/分区信息                         |




1. `pom`文件加入依赖：

   ![image-20200506100521619](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/image-20200506100521619.png)

2. 配置文件application.yaml

   ![image-20200506101828454](%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6%E2%80%94%E2%80%94kafka.assets/image-20200506101828454.png)

3. 常量类

   

4. 消费者监听

   

5. 生产者监听

   

6. 生产者

   

7. 生产者测试

   

## 参考资料

https://www.cnblogs.com/zwcry/p/10244908.html

https://www.cnblogs.com/huangjianping/p/8012580.html

https://www.cnblogs.com/tarencez/p/10887931.html

https://zhuanlan.zhihu.com/p/31731892

https://www.infoq.cn/article/kafka-analysis-part-1

https://www.cnblogs.com/wangb0402/p/6187796.html

https://juejin.im/post/5e04184151882512282cf95d#heading-39

https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-kafka

https://juejin.im/entry/5a9e65296fb9a028dc408af5