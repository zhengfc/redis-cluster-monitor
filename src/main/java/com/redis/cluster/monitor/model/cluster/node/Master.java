package com.redis.cluster.monitor.model.cluster.node;

import org.springframework.data.redis.connection.RedisClusterNode;

public class Master extends Node {
	public Master() {

	}

	public Master(RedisClusterNode source) {
		super(source);
	}

	private String range;

	public String getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range.toString();
	}
}
