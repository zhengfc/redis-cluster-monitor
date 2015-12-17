/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redis.cluster.support.core;

import java.util.Properties;
import java.util.Set;

import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.data.redis.connection.RedisClusterNode;

import com.redis.cluster.support.AddModes;

/**
 * @author Christoph Strobl
 */
public interface RedisClusterOperations<K, V> {
	// TODO: we still need to add operations here	
	/**
	 * Retrieve cluster node information such as {@literal id}, {@literal host}, {@literal port} and {@literal slots}.
	 * 
	 * @return
	 */
	Set<RedisClusterNode> getClusterNodes();

	/**
	 * Find the slot for a given {@code key}.
	 * 
	 * @param key
	 * @return
	 */
	Integer getClusterSlotForKey(K k);

	/**
	 * Find the {@link RedisClusterNode} serving given {@literal slot}.
	 * 
	 * @param slot
	 * @return
	 */
	RedisClusterNode getClusterNodeForSlot(int slot);

	/**
	 * Find the {@link RedisClusterNode} serving given {@literal key}.
	 * 
	 * @param key
	 * @return
	 */
	RedisClusterNode getClusterNodeForKey(K k);

	/**
	 * Get cluster information.
	 * 
	 * @return
	 */
	ClusterInfo getClusterInfo();

	/**
	 * Count the number of keys assigned to one {@literal slot}.
	 * 
	 * @param slot
	 * @return
	 */
	Long countKeys(int slot);

	/**
	 * Remove slots from {@link RedisClusterNode}.
	 * 
	 * @param node
	 * @param slots
	 */
	void deleteSlots(RedisClusterNode node, int... slots);

	/**
	 * @param node
	 * @param slot
	 * @param mode
	 */
	void clusterSetSlot(RedisClusterNode node, int slot, AddModes mode);

	/**
	 * Get {@literal keys} served by slot.
	 * 
	 * @param slot
	 * @param count
	 * @return
	 */
	Set<K> getKeysInSlot(int slot, Integer count);

	/**
	 * Assign a {@literal slave} to given {@literal master}.
	 * 
	 * @param master
	 * @param slave
	 */
	void clusterReplicate(RedisClusterNode master, RedisClusterNode slave);
	
	Properties info();
	
	Properties info(RedisClusterNode node);
}
