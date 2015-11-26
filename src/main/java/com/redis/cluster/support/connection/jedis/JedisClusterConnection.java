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
package com.redis.cluster.support.connection.jedis;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;
import static org.springframework.util.ReflectionUtils.makeAccessible;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPipelineException;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.redis.cluster.support.connection.ClusterInfo;
import com.redis.cluster.support.connection.RedisClusterConnection;
import com.redis.cluster.support.connection.RedisClusterNode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterCommand;
import redis.clients.jedis.JedisClusterConnectionHandler;

public class JedisClusterConnection extends JedisConnection implements RedisClusterConnection {

	private static final Field CONNECTION_HANDLER;
	private final JedisCluster cluster;
	private final ThreadPoolTaskExecutor executor;

	private boolean closed;

	static {

		Field connectionHandler = findField(JedisCluster.class, "connectionHandler");
		makeAccessible(connectionHandler);
		CONNECTION_HANDLER = connectionHandler;
	}

	public JedisClusterConnection(JedisCluster cluster) {
		super(new Jedis());
		notNull(cluster);
		this.cluster = cluster;

		executor = new ThreadPoolTaskExecutor();
		this.executor.initialize();
		closed = false;
	}
	
	@Override
	public void close() throws DataAccessException {
		closed = true;
	}

	@Override
	public boolean isClosed() {
		return closed;
	}

	public Object getSelfConnection() {
		return cluster;
	}

	@Override
	public boolean isQueueing() {
		return false;
	}

	@Override
	public boolean isPipelined() {
		return false;
	}

	@Override
	public void openPipeline() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Object> closePipeline() throws RedisPipelineException {
		throw new UnsupportedOperationException();
	}

	@Override
	public RedisSentinelConnection getSentinelConnection() {
		return null;
	}

	@Override
	public Object execute(String command, byte[]... args) {
		throw new UnsupportedOperationException();
	}
	
	/**RedisClusterCommands*/
	@Override
	public Set<RedisClusterNode> getClusterNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<RedisClusterNode> getClusterSlaves(RedisNode master) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getClusterSlotForKey(byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RedisClusterNode getClusterNodeForSlot(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RedisClusterNode getClusterNodeForKey(byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClusterInfo getClusterInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSlots(RedisNode node, int... slots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long countKeys(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSlots(RedisNode node, int... slots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clusterForget(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clusterMeet(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clusterSetSlot(RedisNode node, int slot, AddSlots mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<byte[]> getKeysInSlot(int slot, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clusterReplicate(RedisNode master, RedisNode slave) {
		// TODO Auto-generated method stub
		
	}

	/**RedisClusterConnection*/
	@Override
	public String ping(RedisNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bgRewriteAof(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bgSave(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long lastSave(RedisNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long dbSize(RedisNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flushDb(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flushAll(RedisNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Properties info(RedisNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<byte[]> keys(RedisNode node, byte[] pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] randomKey(RedisNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown(RedisNode node) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Boolean exists(final byte[] key) {
		return runClusterCommand(new JedisClusterCommand<Boolean>(getClusterConnectionHandler(), 5) {

			@Override
			public Boolean execute(Jedis connection) {
				return connection.exists(key);
			}
		}, key);
	}
	
	private <T> T runClusterCommand(JedisClusterCommand<T> cmd, byte[] key) {
		try {
			return cmd.runBinary(key);
		} catch (Exception ex) {
			throw convertJedisAccessException(ex);
		}
	}
	
	private JedisClusterConnectionHandler getClusterConnectionHandler() {
		return (JedisClusterConnectionHandler) getField(CONNECTION_HANDLER, cluster);
	}

}
