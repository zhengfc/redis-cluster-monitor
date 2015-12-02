package com.redis.cluster.monitor.model.cluster.slot;

import com.redis.cluster.monitor.model.cluster.node.Range;

public class Slot {
	private Range range;
	private String hostPort;
	
	public Range getRange() {
		return range;
	}
	public void setRange(Range range) {
		this.range = range;
	}
	public String getHostPort() {
		return hostPort;
	}
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}
}
