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

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.RedisClusterCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.redis.cluster.support.AddModes;
import com.redis.cluster.support.connection.jedis.JedisConverters;

/**
 * @author Christoph Strobl
 * @since 1.6
 * @param <K>
 * @param <V>
 */
public class DefaultRedisClusterOperations<K, V> extends org.springframework.data.redis.core.DefaultClusterOperations<K, V> implements RedisClusterOperations<K, V> {

	private final RedisTemplate<K, V> template;

	public DefaultRedisClusterOperations(RedisTemplate<K, V> template) {
		super(template);
		this.template = template;
	}

	
	@Override
	public Set<RedisClusterNode> getClusterNodes() {
		return template.execute(new RedisClusterCallback<Set<RedisClusterNode>>() {

			@Override
			public Set<RedisClusterNode> doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return JedisConverters.toSetNodes(connection.clusterGetClusterNodes());
			}
		});
	}

	@Override
	public Integer getClusterSlotForKey(final K k) {
		final byte[] rawKey = rawSelfKey(k);
		return template.execute(new RedisClusterCallback<Integer>() {

			@Override
			public Integer doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.clusterGetSlotForKey(rawKey);
			}
		});
	}

	@Override
	public RedisClusterNode getClusterNodeForSlot(final int slot) {
		return template.execute(new RedisClusterCallback<RedisClusterNode>() {

			@Override
			public RedisClusterNode doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.clusterGetNodeForSlot(slot);
			}
		});
	}

	@Override
	public RedisClusterNode getClusterNodeForKey(K k) {
		final byte[] rawKey = rawSelfKey(k);
		return template.execute(new RedisClusterCallback<RedisClusterNode>() {

			@Override
			public RedisClusterNode doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.clusterGetNodeForKey(rawKey);
			}
		});
	}

	@Override
	public ClusterInfo getClusterInfo() {
		return template.execute(new RedisClusterCallback<ClusterInfo>() {

			@Override
			public ClusterInfo doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.clusterGetClusterInfo();
			}
		});
	}

	@Override
	public Long countKeys(final int slot) {
		return template.execute(new RedisClusterCallback<Long>() {

			@Override
			public Long doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.clusterCountKeysInSlot(slot);
			}
		});
	}

	@Override
	public void deleteSlots(final RedisClusterNode node, final int... slots) {
		template.execute(new RedisClusterCallback<Object>() {

			@Override
			public Object doInRedis(RedisClusterConnection connection) throws DataAccessException {
				connection.clusterDeleteSlots(node, slots);
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
				connection.clusterSetSlot(node, slot, JedisConverters.toAddSlots(mode));
				return null;
			}
		});
	}

	@Override
	public Set<K> getKeysInSlot(final int slot, final Integer count) {
		return template.execute(new RedisClusterCallback<Set<K>>() {

			@Override
			public Set<K> doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return deserializeKeys(connection.clusterGetKeysInSlot(slot, count));
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

	@Override
	public Properties info() {
		return template.execute(new RedisClusterCallback<Properties>() {

			@Override
			public Properties doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.info();
			}
		});
	}

	@Override
	public Properties info(final RedisClusterNode node) {
		return template.execute(new RedisClusterCallback<Properties>() {

			@Override
			public Properties doInRedis(RedisClusterConnection connection) throws DataAccessException {
				return connection.info(node);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	byte[] rawSelfKey(Object key) {
		Assert.notNull(key, "non null key required");
		if (keySerialize() == null && key instanceof byte[]) {
			return (byte[]) key;
		}
		return keySerialize().serialize(key);
	}
	
	@SuppressWarnings("rawtypes")
	RedisSerializer keySerialize() {
		return template.getKeySerializer();
	}
	
	@SuppressWarnings("unchecked")
	K deserializeSelfKey(byte[] value) {
		if (keySerialize() == null) {
			return (K) value;
		}
		return (K) keySerialize().deserialize(value);
	}
	
	/**
	 * @param keys
	 * @return
	 * @since 1.7
	 */
	Set<K> deserializeKeys(List<byte[]> keys) {

		if (CollectionUtils.isEmpty(keys)) {
			return Collections.emptySet();
		}
		Set<K> result = new LinkedHashSet<K>(keys.size());
		for (byte[] key : keys) {
			result.add(deserializeSelfKey(key));
		}
		return result;
	}
}
