# redis-cluster-monitor

  1. git clone https://github.com/zhengfc/redis-cluster-monitor.git 
  2. change redis.properties for yourself
  3. make package
  4. java -jar target/monitor.jar &

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
