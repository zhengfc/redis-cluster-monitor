## Redis Cluster

## 集群组件
  * nodes(节点)  
持有数据， 捕获集群状态， 映射Key到合适的节点；  
自动检测其它节点， 察觉故障节点从而升级slave节点为master节点  
  * cluster bus(集群线)  
每一个节点与其它节点互相连接通过集群线  
节点之间通过gossip协议传播信息  
通过ping确认其它节点是否存活  
  * hash slots(哈希槽)  
客户端分片(redis客户端实现支持)  
代理方案(twemproxy)  
服务端分片(redis使用这种方式, 查询路由方式)  
哈希槽: 用来分片， 键空间被划分成16384(0~16383)个哈希插槽， 理论上可以有16384个master节点， 官方建议不超过1000个节点  
redis采用CRC16算法对key进行分槽: HASH_SLOT = CRC16(key) mod 16384  
why: 避免冲突(同一个K-V在多个节点存在的情况)，减轻单台主机压力    
带来问题: 不能同时访问多个键， 要同时访问多个键时需要确保这多个键所对应的hash槽在同一个redis节点， redis采用hash tags来解决这个问题；redis事务设计到多个键也不能用；分片将导致数据处理更加复杂；例如在分片过程中，随着redis实例的增加，数据备份等操作都将会变得更加复杂；redis目前不支持动态分片操作，扩容和缩容操作都会比较复杂，需要重新配置和启动客户端
  * redirect(查询路由：每个master节点保存一份路由信息，当查询key不在当前节点，重新定向)  
因为redis的key存取在不同的分槽中导致在特定redis-cli查询key值时，此key不在此节点， 所有需要重定向  
redis采用ASK和MOVED； 先询问在定向  
当以集群的方式启动默认服务端启动重定向功能  
redis-cli -c -p 6379  
以redis-cli -p 6379此方式启动客户端， 查询若key不在当前节点会报错  
  * hash tags(哈希标签)  
为了解决集群中不能同时访问多个键问题， redis加入hash标签； 即当键k1，k2被分配到不同redis节点时，此时不能同时访问k1，k2；但当使用MSET {user:1000}.name Angela {user:1000}.surname White时能保证此类键在同一个分槽中， 从而在同一节点上

## 集群搭建步骤  
以手动创建集群步骤为例(redis也支持自动脚本创建集群redis-trib.rb: ruby脚本需要环境安装ruby)

  1. 修改各节点配置文件(可以提取出redis-common.conf， 提取redis-common暂未测试)  
cluster enabled设置为yes  
修改相应的: pidfile， port， logfile， dbfilename， cluster-config-file， nodes-port.conf  

  2. 启动各节点实例  
<code>
./redis-server ../cluster-test/7000/redis-7000.conf  
</code>  
<code>
./redis-server ../cluster-test/7001/redis-7001.conf  
</code>  
<code>
./redis-server ../cluster-test/7010/redis-7010.conf  
</code>  
<code>
./redis-server ../cluster-test/7011/redis-7011.conf  
</code>  
<code>
./redis-server ../cluster-test/7020/redis-7020.conf  
</code>  
<code>
./redis-server ../cluster-test/7021/redis-7021.conf  
</code>

  3. 将节点加入至集群中  
<code>cluster meet ip port</code>

  4. slave节点设置  
<code>
7001> cluster replicate 7000
</code>  
<code>
7011> cluster replicate 7010  
</code>  
<code>
7021> cluster replicate 7020  
</code>

  5. 分配哈希插槽  
src下执行bash脚本:  
<code>
for i in {0..5500}; do redis-cli -h 127.0.0.1 -p 7000 cluster addslots ${i}; done  
</code>
<code>
for i in {5501..11000}; do redis-cli -h 127.0.0.1 -p 7010 cluster addslots ${i}; done  
</code>
<code>
for i in {11001..16383}; do redis-cli -h 127.0.0.1 -p 7020 cluster addslots ${i}; done  
</code>

  6. 查看集群信息  
<code>
cluster info  
</code>

![info](./doc/cluster-info.png)  
<code>
cluster nodes  
</code>

![nodes](./doc/cluster-nodes.png)  
自动升级功能已测试过，上图初始化时定义的master为7001，7003，7005；先停掉7001后7000自动升级为master，重启7001后7001变为7000的slave   
<code>
cluster slots  
</code>

![slots](./doc/cluster-slots.png)  
<code>
redirect  
</code>

![redirect](./doc/cluster-redirect.png)  
<code>
hash tags  
</code>

![hash-tags](./doc/cluster-hash-tags.png)  

更多集群命令请参照:  
[http://redis.io/commands/cluster-addslots](http://redis.io/commands/cluster-addslots)

## 优劣性
  * 优点(即特性)    
  * 缺点  
    * 可能造成写丢失  
redis集群写操作步骤(1)数据写入master (2)master返回消息给客户端 (3)master复制数据至slaves  
当执行完第二步， 此时master挂掉， 但数据还未复制数据至slave； 此时选举slave为新的master， 新的master没有刚才写入的数据， 写数据丢失但客户端返回ok信息  
    * 迁移数据麻烦(方案为先为要迁移的节点增加一个slave，将master数据复制到slave，停掉master，slave自动升级master)   
    * 增删节点相对麻烦(需提前规划好)  
redis支持集群运转时增加及删除节点， 增删节点对redis集群来说操作是相同的， 即移动哈希槽从一个节点到其它节点.  
增加节点: (1)增加一个新的节点到集群中 (2)移动其它节点的槽到新节点  
删除节点: 重新分片(1)移动槽 (2)移动数据， 删除节点    
    * Gossip/P2P的去中心化架构(节点两两交互)，不能自动Resharding(需要手动通过命令重新分片)，一旦有了中心节点(类似ESb)，能做的事情就多了，比如sharding不均匀时很容易自动rebalance的，而无中心的只能靠外界来搞   
    * master负责读写，slave只负责异步备份，违背负载均衡，即"冷备"(如果要集群，这一功能需要自身完善) 
    * 无监控管理UI：即便未来加了UI，像迁移进度这种信息在无中心化设计中很难得到  
    * 集群严格依赖客户端driver的成熟度(jedis对cluster支持相对较好)，但spring-data-redis对集群的支持尚在投票中  
[spring-data-redis vote for add support for redis cluster](https://jira.spring.io/browse/DATAREDIS-315)  
[lettue：A scalable Java Redis client](https://github.com/mp911de/lettuce)  
粗略看了下最新版jedis-2.7.2.jar对集群支持对redis官方集群功能跟进的较快，封装了redis集群新增功能的基本操作  
      * JedisCluster  
      * JedisClusterCommand(支持list分配slots)  
      * JedisClusterInfoCache  
      * JedisSlotBasedConnectionHandler  
      * JedisShardInfo  
      * SharedJedis  
  * redis商业版能够解决以上大部分问题 [redislabs](https://redislabs.com/)

## 特性(官方)
最核心的设计目标就是性能、水平伸缩(这一点是集群的目的)和可用性。

  * 高性能  
因为没有代理(redis官方集群实现为服务端分片无需代理，市面上代理实现有twitter的twemproxy)， 采用异步复制， 不能进行值合并操作(用Hash Tags可以解决此问题但带来key分布不均)； 性能高  
  * 水平扩展  
通过服务器端分片分槽机制， 能水平扩展多达1000个节点(1000台服务器)    
  * 写操作安全性达到可接受的程度  
  * 高可用性  
主节点都可达或者主节点对应的从节点至少有一个可达， 则redis可用；
当某个节点主从都不可达， 则redis集群不可用; 可修改默认设置cluster-require-full-coverage no使其它分片可用
  
## 适用场景  
主观意见：   
单项业务或单条业务线需要缓存的数据量已接近或超越单台redis服务器存储容量(数据容量不能超出物理内存大小， 可先扩内存)  
业务不能垂直拆分(可垂直拆分业务， 直接根据拆分业务划分redis服务器)；  
业务增长迅速(可提前准备及规划)  

建议：可以先预分片(在同一台服务器上部署多个实例，例如16或32个)，等后续需扩容时切换至集群(将同一台服务器上的不同实例部署到不同服务器上)  

Redis的预分片技术实例迁移步骤(例如1台服务器扩至3台服务器, 原一台服务器部署15个实例，现将实例重新划分5,5,5即可)：  

  * 在新机子上启动新的redis实例；
  * 将新redis实例作为slave将原redis实例作为master，将数据从原redis实例复制迁移到新服务器redis实例上；
  * 停止客户端（分片操作在客户端上时）
  * 更新客户端的配置信息，去掉被迁移的原redis实例的ip和端口等信息，加上新启动redis实例的IP地址和端口；
  * 向新启动的redis发送SLAVEOF NOONE命令，终止新redis实例对原redis实例的从属关系；
  * 重启客户端程，此时它们将会使用新的redis实例；
  * 关掉被迁移走数据的原redis实例；

注意：要使用集群功能需要缓存工具重新引入新版的Jedis客户端及修改相应Jedis客户端代码
         
## 测试(针对数据量测试: 待完善)
single data --- three redis data(3M， 3S)  
如何评估容量  
如何测试容量  
目标: 集群后容量N*70%左右

## Redis Sentinel(待研究)
单实例redis使用HA的最佳方案

## Lua脚本(待完善 redis2.6新引入)  
Lua脚本的支持给开发者提供一个非常友好的开发环境，从而大幅度解放用户的创造力。  
Lua脚本可以给性能和资源消耗带来非常大的改善。取代将数据传送给CPU，脚本允许你在最接近数据的地方执行逻辑，从而减少网络延时和数据的冗余传输。  

在Redis中，Lua一个非常经典的用例就是数据过滤或者将数据聚合到应用程序。check-and-set不高效问题  
通过将处理工作流程封装到一个脚本中，你只需要调用它就可以在更短的时间内使用很少的资源来获取结果。  

类似关系数据库的Stored Procedure 

redis中执行lua脚本的两个命令：  
eval  
evalsha  
[http://redis.io/commands/eval](http://redis.io/commands/eval)

lua脚本中调用redis两种方式：  
redis.call()  
redis.pcall()  

Lua数据类型
  
  * 数值(number)  
  * 字符串(string)  
  * 布尔(boolean)  
  * 函数(function)  
  * 表(table):数组, Map, 对象  
  * nil  

参考文档:  
[http://redis.io/topics/cluster-tutorial](http://redis.io/topics/cluster-tutorial)  
[http://redis.io/topics/cluster-spec](http://redis.io/topics/cluster-spec)  
[http://redis.io/commands](http://redis.io/commands)  
[http://redis.io/topics/partitioning](http://redis.io/topics/partitioning)  
[Redis Cluster架构优化](http://blog.csdn.net/dc_726/article/details/48733265) 