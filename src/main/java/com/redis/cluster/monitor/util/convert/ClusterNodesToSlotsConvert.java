package com.redis.cluster.monitor.util.convert;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisClusterNode;

import com.redis.cluster.monitor.model.cluster.slot.Slot;

public class ClusterNodesToSlotsConvert implements Converter<Set<RedisClusterNode>, Set<Slot>> {

	@Override
	public Set<Slot> convert(Set<RedisClusterNode> source) {
		// master
		Set<Slot> slots = new HashSet<Slot>();
		for (RedisClusterNode cnode : source) {
			if (cnode.isMaster()) {
				Slot slot = new Slot();
				slot.setRange(AppConverters.toRange().convert(cnode.getSlotRange()));
				slot.setMaster(cnode.asString());
				slots.add(slot);
			}
		}
		// slave
		for (Slot slot : slots) {
			for (RedisClusterNode cnode : source) {
				if (cnode.isSlave() && slot.serverMaster(AppConverters.getMasterById(cnode.getMasterId(), source))) {
					slot.getSalves().add(cnode.asString());
				}
			}
		}
		return slots;
	}

}
