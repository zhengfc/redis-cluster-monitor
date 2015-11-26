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

import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisNode;

import com.redis.cluster.support.connection.ClusterInfo;
import com.redis.cluster.support.connection.RedisClusterConnection;
import com.redis.cluster.support.connection.RedisClusterNode;

/**
 * @author Christoph Strobl
 * @since 1.6
 * @param <K>
 * @param <V>
 */
public class DefaultRedisClusterOperations<K, V> extends AbstractOperations<K, V> implements
		RedisClusterOperations<K, V> {

	private final RedisClusterTemplate<K, V> template;

	public DefaultRedisClusterOperations(RedisClusterTemplate<K, V> template) {
		super(template);
		this.template = template;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.RedisClusterOperations#keys(org.springframework.data.redis.connection.RedisNode, byte[])
	 */
	@Override
	public Set<K> keys(final RedisNode node, final byte[] pattern) {

		return template.execute(new RedisClusterCallback<Set<K>>() {

			@Override
			public Set<K> doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return deserializeKeys(connection.keys(node, pattern));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.RedisClusterOperations#randomKey(org.springframework.data.redis.connection.RedisNode)
	 */
	@Override
	public K randomKey(final RedisNode node) {
		return template.execute(new RedisClusterCallback<K>() {

			@Override
			public K doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return deserializeKey(connection.randomKey(node));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.RedisClusterOperations#ping(org.springframework.data.redis.connection.RedisNode)
	 */
	@Override
	public String ping(final RedisNode node) {
		return template.execute(new RedisClusterCallback<String>() {

			@Override
			public String doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.ping(node);
			}
		});
	}
	
	@Override
	public Set<RedisClusterNode> getClusterNodes() {
		return template.execute(new RedisClusterCallback<Set<RedisClusterNode>>() {

			@Override
			public Set<RedisClusterNode> doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.getClusterNodes();
			}
		});
	}

	@Override
	public Set<RedisClusterNode> getClusterSlaves(final RedisClusterNode master) {
		return template.execute(new RedisClusterCallback<Set<RedisClusterNode>>() {

			@Override
			public Set<RedisClusterNode> doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.getClusterSlaves(master);
			}
		});
	}

	@Override
	public Integer getClusterSlotForKey(final K k) {
		final byte[] rawKey = rawKey(k);
		return template.execute(new RedisClusterCallback<Integer>() {

			@Override
			public Integer doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.getClusterSlotForKey(rawKey);
			}
		});
	}

	@Override
	public RedisClusterNode getClusterNodeForSlot(final int slot) {
		return template.execute(new RedisClusterCallback<RedisClusterNode>() {

			@Override
			public RedisClusterNode doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.getClusterNodeForSlot(slot);
			}
		});
	}

	@Override
	public RedisClusterNode getClusterNodeForKey(K k) {
		final byte[] rawKey = rawKey(k);
		return template.execute(new RedisClusterCallback<RedisClusterNode>() {

			@Override
			public RedisClusterNode doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.getClusterNodeForKey(rawKey);
			}
		});
	}

	@Override
	public ClusterInfo getClusterInfo() {
		return template.execute(new RedisClusterCallback<ClusterInfo>() {

			@Override
			public ClusterInfo doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.getClusterInfo();
			}
		});
	}

	@Override
	public void addSlots(final RedisClusterNode node, final int... slots) {
		template.execute(new RedisClusterCallback<Object>() {

			@Override
			public Object doInRedis(RedisClusterConnection connection) throws DataAccessException {
				connection.addSlots(node, slots);
				return null;
			}
		});
	}

	@Override
	public Long countKeys(final int slot) {
		return template.execute(new RedisClusterCallback<Long>() {

			@Override
			public Long doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.countKeys(slot);
			}
		});
	}

	@Override
	public void deleteSlots(final RedisClusterNode node, final int... slots) {
		template.execute(new RedisClusterCallback<Object>() {

			@Override
			public Object doInRedis(RedisClusterConnection connection) throws DataAccessException {
				connection.deleteSlots(node, slots);
				return null;
			}
		});
	}

	@Override
	public void clusterForget(final RedisClusterNode node) {
		template.execute(new RedisClusterCallback<Object>() {

			@Override
			public Object doInRedis(RedisClusterConnection connection) throws DataAccessException {
				connection.clusterForget(node);
				return null;
			}
		});
	}

	@Override
	public void clusterMeet(final RedisClusterNode node) {
		template.execute(new RedisClusterCallback<Object>() {

			@Override
			public Object doInRedis(RedisClusterConnection connection) throws DataAccessException {
				connection.clusterMeet(node);
				return null;
			}
		});
	}

	@Override
	public void clusterSetSlot(final RedisClusterNode node, final int slot,
			final AddModes mode) {
		template.execute(new RedisClusterCallback<Object>() {

			@Override
			public Object doInRedis(RedisClusterConnection connection) throws DataAccessException {
				connection.clusterSetSlot(node, slot, com.redis.cluster.support.connection.jedis.JedisConverters.toAddSlots(mode));
				return null;
			}
		});
	}

	@Override
	public Set<K> getKeysInSlot(final int slot, final Integer count) {
		return template.execute(new RedisClusterCallback<Set<K>>() {

			@Override
			public Set<K> doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return deserializeKeys(connection.getKeysInSlot(slot, count));
			}
		});
	}

	@Override
	public void clusterReplicate(final RedisClusterNode master, final RedisClusterNode slave) {
		template.execute(new RedisClusterCallback<Object>() {

			@Override
			public Object doInRedis(RedisClusterConnection connection) throws DataAccessException {
				connection.clusterReplicate(master, slave);
				return null;
			}
		});
	}
}
