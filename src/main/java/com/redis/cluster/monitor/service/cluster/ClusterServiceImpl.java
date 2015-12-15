package com.redis.cluster.monitor.service.cluster;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.stereotype.Service;

import com.redis.cluster.monitor.model.cluster.node.Node;
import com.redis.cluster.monitor.model.cluster.slot.Slot;
import com.redis.cluster.monitor.model.info.Info;
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
		Map<String, Info> infos = new HashMap<String, Info>();
		Properties prop = clusterTemplate.opsForCluster().info();
		logger.info(prop);
		
		Enumeration<Object> keys = prop.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Properties subProp = (Properties) prop.get(key);
			Info info = AppConverters.toInfo().convert(subProp);
			infos.put(String.valueOf(key), info);
		}
		
		RuntimeContainer.setRetMessage(infos);

	}

	@Override
	public void nodeInfo(String node) {
		Properties prop = clusterTemplate.opsForCluster().info(create(node));
		Info info = AppConverters.toInfo().convert(prop);
		RuntimeContainer.setRetMessage(info);
	}

	@Override
	public void server(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clients(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memory(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persistence(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stats(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replication(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cpu(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cluster(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyspace(String node) {
		// TODO Auto-generated method stub
		
	}
	
	private RedisClusterNode create(String node){
		String[] hostAndPort = node.split(":");
		return new RedisClusterNode(hostAndPort[0], Integer.parseInt(hostAndPort[1]), null);
	}
}
