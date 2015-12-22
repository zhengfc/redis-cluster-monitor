package com.redis.cluster.support.core;

import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;

import com.redis.cluster.TestConfig;

public class ClusterConnectionTest extends TestConfig {
	private static final Log logger = LogFactory.getLog(ClusterConnectionTest.class);
	@Autowired JedisClusterConnection clusterConnection;
	
	@Before
	public void setUp(){

	}
	
	@Test
	public void testExists(){
		assertTrue(true);
//		assertTrue(redisTemplate.hasKey("docker@10000test:15441"));
	}
	
	@Test
	public void testInfo() {
		System.setProperty("line.separator", "\n");
		logger.info("testinfo");
//		logger.info(redisTemplate.opsForCluster().getClusterInfo());
	}
}
