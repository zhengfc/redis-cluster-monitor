package com.redis.cluster.monitor.model.cluster.node;

import org.springframework.data.redis.connection.RedisClusterNode;

public class Slave extends Node{
	public Slave() {

	}

	public Slave(RedisClusterNode source) {
		super(source);
	}
	
	String masterId;
	private String masterHostPort;
	
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getMasterHostPort() {
		return masterHostPort;
	}
	public void setMasterHostPort(String masterHostPort) {
		this.masterHostPort = masterHostPort;
	}
}
