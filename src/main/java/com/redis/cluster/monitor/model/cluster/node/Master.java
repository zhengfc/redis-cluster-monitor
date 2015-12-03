package com.redis.cluster.monitor.model.cluster.node;

public class Master extends Node{
	private String range;

	public String getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range.toString();
	}
}
