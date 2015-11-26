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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.Assert;

import com.redis.cluster.support.ClusterUnsupported;
import com.redis.cluster.support.connection.RedisClusterConnection;
import com.redis.cluster.support.connection.RedisClusterNode;

/**
 * @author Christoph Strobl
 * @since 1.6
 */
public class RedisClusterTemplate<K, V> extends RedisTemplate<K, V> {
	private RedisClusterOperations<K, V> clusterOps;
	private ValueOperations<K, V> valueOps;
	private SetOperations<K, V> setOps;
	private ZSetOperations<K, V> zSetOps;
	/**
	 * Executed wrapped command upon {@link RedisClusterConnection}.
	 * 
	 * @param callback
	 * @return
	 */
	public <T> T execute(RedisClusterCallback<T> callback) {

		Assert.notNull(callback, "ClusterCallback must not be null!");
		return callback.doInRedis(getConnection());
	}

	/**
	 * Get {@link RedisClusterOperations} operating upon {@link RedisClusterTemplate}.
	 * 
	 * @return
	 */
	public RedisClusterOperations<K, V> opsForCluster() {
		if(clusterOps == null)
			clusterOps = new DefaultValueOperationsWithUnallowed(this);
		return clusterOps;
	}

	private RedisClusterConnection getConnection() {

		RedisConnection connection = getConnectionFactory().getConnection();
		Assert.isInstanceOf(RedisClusterConnection.class, connection);

		return (RedisClusterConnection) connection;
	}

	@Override
	public ValueOperations<K, V> opsForValue() {
		if(valueOps == null)
			valueOps = new DefaultValueOperationsWithUnsupported(this);
		return valueOps;
	}

	@Override
	public SetOperations<K, V> opsForSet() {
		if(setOps == null)
			setOps = new DefaultSetOperationsWithUnsupported(this);
		return setOps;
	}

	@Override
	public ZSetOperations<K, V> opsForZSet() {
		if(zSetOps == null)
			zSetOps = new DefaultZSetOperationsWithUnsupported(this);
		return zSetOps;
	}
	
	class DefaultValueOperationsWithUnallowed extends DefaultRedisClusterOperations<K, V> {

		public DefaultValueOperationsWithUnallowed(RedisClusterTemplate<K, V> template) {
			super(template);
		}

		@Override
		public void addSlots(RedisClusterNode node, int... slots) {
			throw new UnsupportedOperationException("addSlots is not allowed within a cluster client");
		}

		@Override
		public void deleteSlots(RedisClusterNode node, int... slots) {
			throw new UnsupportedOperationException("deleteSlots is not allowed within a cluster client");
		}

		@Override
		public void clusterForget(RedisClusterNode node) {
			throw new UnsupportedOperationException("clusterForget is not allowed within a cluster client");
		}

		@Override
		public void clusterSetSlot(RedisClusterNode node, int slot, AddModes mode) {
			throw new UnsupportedOperationException("clusterSetSlot is not allow within a cluster client");
		}
	}
	
	class DefaultValueOperationsWithUnsupported extends DefaultValueOperations<K, V> {

		DefaultValueOperationsWithUnsupported(RedisTemplate<K, V> template) {
			super(template);
		}

		@Override
		public List<V> multiGet(Collection<K> keys) {
			if (keys.isEmpty()) {
				return Collections.emptyList();
			}
			List<V> ret = new ArrayList<V>();
			for (K k: keys){
				ret.add(this.get(k));
			}
			return ret;
		}

		@Override
		public void multiSet(Map<? extends K, ? extends V> m) {
			if (m.isEmpty()) {
				return;
			}
			for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
				this.set(entry.getKey(), entry.getValue());
			}
		}

		@Override
		public Boolean multiSetIfAbsent(Map<? extends K, ? extends V> m) {
			if (m.isEmpty()) {
				return true;
			}
			for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
				this.setIfAbsent(entry.getKey(), entry.getValue());
			}
			return true;
		}
	}


	class DefaultSetOperationsWithUnsupported extends DefaultSetOperations<K, V>  {

		public DefaultSetOperationsWithUnsupported(RedisTemplate<K, V> template) {
			super(template);
		}

		@Override
		public Set<V> difference(K key, K otherKey) {
			throw new UnsupportedOperationException("difference is not supported within a cluster");
		}

		@Override
		public Set<V> difference(K key, Collection<K> otherKeys) {
			throw new UnsupportedOperationException("difference is not supported within a cluster");
		}

		@Override
		public Long differenceAndStore(K key, K otherKey, K destKey) {
			throw new UnsupportedOperationException("differenceAndStore is not supported within a cluster");
		}

		@Override
		public Long differenceAndStore(K key, Collection<K> otherKeys, K destKey) {
			throw new UnsupportedOperationException("differenceAndStore is not supported within a cluster");
		}

		@Override
		public Set<V> intersect(K key, K otherKey) {
			throw new UnsupportedOperationException("intersect is not supported within a cluster");
		}

		@Override
		public Set<V> intersect(K key, Collection<K> otherKeys) {
			throw new UnsupportedOperationException("intersect is not supported within a cluster");
		}

		@Override
		public Long intersectAndStore(K key, K otherKey, K destKey) {
			throw new UnsupportedOperationException("intersectAndStore is not supported within a cluster");
		}

		@Override
		public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey) {
			throw new UnsupportedOperationException("intersectAndStore is not supported within a cluster");
		}

		@Override
		public Boolean move(K key, V value, K destKey) {
			throw new UnsupportedOperationException("move is not supported within a cluster");
		}

		@ClusterUnsupported
		@Override
		public Set<V> union(K key, K otherKey) {
			throw new UnsupportedOperationException("union is not supported within a cluster");
		}

		@Override
		public Set<V> union(K key, Collection<K> otherKeys) {
			throw new UnsupportedOperationException("union is not supported within a cluster");
		}

		@Override
		public Long unionAndStore(K key, K otherKey, K destKey) {
			throw new UnsupportedOperationException("unionAndStore is not supported within a cluster");
		}

		@Override
		public Long unionAndStore(K key, Collection<K> otherKeys, K destKey) {
			throw new UnsupportedOperationException("unionAndStore is not supported within a cluster");
		}
		
	}

	class DefaultZSetOperationsWithUnsupported extends DefaultZSetOperations<K, V>  {

		DefaultZSetOperationsWithUnsupported(RedisTemplate<K, V> template) {
			super(template);
		}

		@Override
		public Long intersectAndStore(K key, K otherKey, K destKey) {
			throw new UnsupportedOperationException("intersectAndStore is not supported within a cluster");
		}

		@Override
		public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey) {
			throw new UnsupportedOperationException("intersectAndStore is not supported within a cluster");
		}

		@Override
		public Long unionAndStore(K key, K otherKey, K destKey) {
			throw new UnsupportedOperationException("unionAndStore is not supported within a cluster");
		}

		@Override
		public Long unionAndStore(K key, Collection<K> otherKeys, K destKey) {
			throw new UnsupportedOperationException("unionAndStore is not supported within a cluster");
		}
	}
}
