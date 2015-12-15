package com.redis.cluster.monitor.service.cluster;

public interface ClusterService {
	//cluster
	void info();
	void slots();
	void nodes();
	
	//node
	void nodesInfo();
	void nodeInfo(String node);
	
	//node item
	void server(String node);
	void clients(String node);
	void memory(String node);
	void persistence(String node);
	void stats(String node);
	void replication(String node);
	void cpu(String node);
	void cluster(String node);
	void keyspace(String node);
}
