package com.redis.cluster.monitor.service.cluster;

import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.stereotype.Service;

import com.redis.cluster.monitor.model.cluster.node.Node;
import com.redis.cluster.monitor.model.cluster.slot.Slot;
import com.redis.cluster.monitor.util.context.RuntimeContainer;
import com.redis.cluster.monitor.util.convert.AppConverters;
import com.redis.cluster.support.core.RedisClusterTemplate;

@Service
public class ClusterServiceImpl implements ClusterService {
	private static final Log logger = LogFactory.getLog(ClusterServiceImpl.class);
	static {
		System.setProperty("line.separator", "\n");
	}
	@Autowired RedisClusterTemplate<String, Object> clusterTemplate;
	
	@Override
	public void info() {
		ClusterInfo info = clusterTemplate.opsForCluster().getClusterInfo();
		logger.info(info);
		RuntimeContainer.setRetMessage(info);
	}

	@Override
	public void slots() {
		Set<RedisClusterNode> clusterNodes = clusterTemplate.opsForCluster().getClusterNodes();
		logger.info(clusterNodes);
		Set<Slot> slots = AppConverters.toSetOfSlot().convert(clusterNodes);
		RuntimeContainer.setRetMessage(slots);
	}

	@Override
	public void nodes() {
		Set<RedisClusterNode> clusterNodes = clusterTemplate.opsForCluster().getClusterNodes();
		Set<Node> nodes = AppConverters.toSetOfNode().convert(clusterNodes);
		RuntimeContainer.setRetMessage(nodes);
	}

	@Override
	public void nodesInfo() {
		Properties prop = clusterTemplate.opsForCluster().info();
		logger.info(prop);
		logger.info(prop.elements());
		RuntimeContainer.setRetMessage(prop);
		
	}

	@Override
	public void nodeInfo(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void server(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clients(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memory(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persistence(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stats(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replication(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cpu(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cluster(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyspace(RedisNode node) {
		// TODO Auto-generated method stub
		
	}
}
