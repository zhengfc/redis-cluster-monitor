package com.redis.cluster.monitor.model.info;

import com.redis.cluster.monitor.model.info.detail.CPU;
import com.redis.cluster.monitor.model.info.detail.Clients;
import com.redis.cluster.monitor.model.info.detail.Cluster;
import com.redis.cluster.monitor.model.info.detail.Keyspace;
import com.redis.cluster.monitor.model.info.detail.Memory;
import com.redis.cluster.monitor.model.info.detail.Persistence;
import com.redis.cluster.monitor.model.info.detail.Replication;
import com.redis.cluster.monitor.model.info.detail.Server;
import com.redis.cluster.monitor.model.info.detail.Stats;

public class Info {
	private Server server;
	private Clients clients;
	private Memory memory;
	private Persistence persistence;
	private Stats stats;
	private Replication replication;
	private CPU cpu;
	private Cluster cluster;
	private Keyspace keyspace;
	
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	public Clients getClients() {
		return clients;
	}
	public void setClients(Clients clients) {
		this.clients = clients;
	}
	public Memory getMemory() {
		return memory;
	}
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	public Persistence getPersistence() {
		return persistence;
	}
	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public Replication getReplication() {
		return replication;
	}
	public void setReplication(Replication replication) {
		this.replication = replication;
	}
	public CPU getCpu() {
		return cpu;
	}
	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}
	public Cluster getCluster() {
		return cluster;
	}
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	public Keyspace getKeyspace() {
		return keyspace;
	}
	public void setKeyspace(Keyspace keyspace) {
		this.keyspace = keyspace;
	}
}
