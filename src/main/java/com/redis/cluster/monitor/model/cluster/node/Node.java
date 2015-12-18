package com.redis.cluster.monitor.model.cluster.node;

import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode.NodeType;

public class Node {
	private String id;
	private String host;
	private int port;
	private NodeType type;

	public Node(RedisClusterNode source) {
		this.id = source.getId();
		this.host = source.getHost();
		this.port = source.getPort();
		this.type = source.getType();
	}

	public Node() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}
}
