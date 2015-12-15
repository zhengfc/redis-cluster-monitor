package com.redis.cluster.monitor.util.convert;

import java.util.Properties;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisClusterNode.SlotRange;
import org.springframework.data.redis.connection.convert.Converters;

import com.redis.cluster.monitor.model.cluster.node.Node;
import com.redis.cluster.monitor.model.cluster.node.Range;
import com.redis.cluster.monitor.model.cluster.slot.Slot;
import com.redis.cluster.monitor.model.info.Info;

public class AppConverters extends Converters {
	private static final Converter<Set<RedisClusterNode>, Set<Node>> CLUSTER_NODES_TO_NODES = new ClusterNodesToNodesConvert();
	private static final Converter<Set<RedisClusterNode>, Set<Slot>> CLUSTER_NODE_TO_SLOTS = new ClusterNodesToSlotsConvert();
	private static final Converter<SlotRange, Range> SLOT_RANGE_TO_RANGE = new SlotRangeToRangeConvert();
	private static final Converter<Properties, Info> PROPERTIES_TO_INFO = new PropertiesToInfoConvert();

	public static Converter<Set<RedisClusterNode>, Set<Node>> toSetOfNode() {
		return CLUSTER_NODES_TO_NODES;
	}

	public static Converter<Set<RedisClusterNode>, Set<Slot>> toSetOfSlot() {
		return CLUSTER_NODE_TO_SLOTS;
	}

	public static Converter<SlotRange, Range> toRange() {
		return SLOT_RANGE_TO_RANGE;
	}

	public static Converter<Properties, Info> toInfo() {
		return PROPERTIES_TO_INFO;
	}

	public static RedisClusterNode getMasterById(String id, Set<RedisClusterNode> source) {
		for (RedisClusterNode cnode : source) {
			if (cnode.getId().equals(id)) {
				return cnode;
			}
		}
		return null;
	}
}
