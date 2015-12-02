package com.redis.cluster.monitor.model.cluster.node;

public class Range {
	Integer lowerBound;
	Integer upperBound;
	
	public Integer getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(Integer lowerBound) {
		this.lowerBound = lowerBound;
	}
	public Integer getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(Integer upperBound) {
		this.upperBound = upperBound;
	}
	
	@Override
	public String toString() {
		return lowerBound + "-" + upperBound ;
	}
}
