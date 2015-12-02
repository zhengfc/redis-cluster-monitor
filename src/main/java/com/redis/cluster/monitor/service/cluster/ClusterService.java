package com.redis.cluster.monitor.service.cluster;

import org.springframework.data.redis.connection.RedisNode;

public interface ClusterService {
	//cluster
	void info();
	void slots();
	void nodes();
	
	//node
	void nodesInfo();
	void nodeInfo(RedisNode node);
	
	//node item
	void server(RedisNode node);
	void clients(RedisNode node);
	void memory(RedisNode node);
	void persistence(RedisNode node);
	void stats(RedisNode node);
	void replication(RedisNode node);
	void cpu(RedisNode node);
	void cluster(RedisNode node);
	void keyspace(RedisNode node);
}
