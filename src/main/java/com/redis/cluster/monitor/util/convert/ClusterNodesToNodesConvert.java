package com.redis.cluster.monitor.util.convert;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisClusterNode;

import com.redis.cluster.monitor.model.cluster.node.Master;
import com.redis.cluster.monitor.model.cluster.node.Node;
import com.redis.cluster.monitor.model.cluster.node.Slave;

public class ClusterNodesToNodesConvert implements Converter<Set<RedisClusterNode>, Set<Node>> {

	@Override
	public Set<Node> convert(Set<RedisClusterNode> source) {
		Set<Node> ret = new HashSet<Node>();
		for(RedisClusterNode cnode: source){
			if(cnode.isMaster()){
				Master master = new Master();
				setNode(master, cnode);
				master.setRange(AppConverters.toRange().convert(cnode.getSlotRange()));
				ret.add(master);
			}else {
				Slave slave = new Slave();
				setNode(slave, cnode);
				slave.setMasterId(cnode.getMasterId());
				RedisClusterNode father = AppConverters.getMasterById(cnode.getMasterId(), source);
				slave.setMasterHostPort(father.asString());
				ret.add(slave);
			}
		}
		return ret;
	}
	
	private void setNode(Node node, RedisClusterNode source){
		node.setId(source.getId());
		node.setHost(source.getHost());
		node.setPort(source.getPort());
		node.setType(source.getType());
	}
}
