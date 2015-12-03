package com.redis.cluster.monitor.model.cluster.slot;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.redis.connection.RedisClusterNode;

import com.redis.cluster.monitor.model.cluster.node.Range;

public class Slot {
	private Range range;
	private String master;
	private Set<String> salves = new HashSet<String>();
	
	public Range getRange() {
		return range;
	}
	public void setRange(Range range) {
		this.range = range;
	}
	
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	
	public Set<String> getSalves() {
		return salves;
	}
	public void setSalves(Set<String> salves) {
		this.salves = salves;
	}
	
	public boolean serverMaster(RedisClusterNode serverMaster) {
		if(serverMaster==null) return false;
		return master.equals(serverMaster.asString());
	}
}
