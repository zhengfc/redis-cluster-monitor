# redis-cluster-monitor
Redis3.0 Cluster Monitor

  1. git clone https://github.com/zhengfc/redis-cluster-monitor.git 
  2. change redis.properties for yourself
  3. make package
  4. run war on server(tomcat/jetty)

## cluster
  * cluster info
  * slots
  * cluster nodes  
![cluster](./doc/img/clusterinfo.png) 

## nodes(You can change node with select)
  * info  
    * Server
    * Clients
    * Memory
    * Persistence
    * Stats
    * Replication
    * Cpu
    * Cluster
    * Keyspace  
![node](./doc/img/nodeinfo.png)

## chart | cmd will be coming...
key expire keysinslot slotsinnode...
