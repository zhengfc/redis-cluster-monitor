package com.redis.cluster.monitor.model.cluster.node;

public class Slave extends Node{
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
