![](https://travis-ci.org/zhengfc/redis-cluster-monitor)

# redis-cluster-monitor

  1. git clone https://github.com/zhengfc/redis-cluster-monitor.git 
  2. change application.yml for yourself
  3. make package
  4. java -jar target/monitor.jar &  
     或者 java -jar target/monitor.jar --spring.config.location=yourslef.yml &

## cluster
  * cluster info, cluster slots, cluster nodes  
![cluster](./doc/img/clusterinfo.png) 

## nodes(You can change node with select)
  * info  
    * Memory, Cpu|Cluster|Keyspace
    * Stats, Persistence
    * Server, Clients|Replication
![node](./doc/img/nodeinfo.png)

## chart | cmd will be coming...
key expire keysinslot slotsinnode...

## TODO
* convert to spring boot app √
* dockerfile
* cmd
  * set/get/keysinslot/slotsinnode/expire
