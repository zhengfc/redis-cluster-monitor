package com.redis.cluster.support.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.redis.cluster.support.serializer.DefaultKeySerializer;

@Configuration
public class MonitorConfig {
	@Value("${spring.redis.cluster.nodes}")
	private String clusterNodes;
	@Value("${spring.redis.cluster.timeout}")
	private Long timeout;
	@Value("${spring.redis.cluster.max-redirects}")
	private int redirects;
	@Value("${server.name:}")
	private String appCode;
	@Value("${expiration:0}")
	private long expiration;
	
	@Bean
	public RedisClusterConfiguration getClusterConfiguration(){
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("spring.redis.cluster.nodes", clusterNodes);
		source.put("spring.redis.cluster.timeout", timeout);
		source.put("spring.redis.cluster.max-redirects", redirects);
		return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
	}
	
	@Bean
	public JedisConnectionFactory getConnectionFactory() {
		return new JedisConnectionFactory(getClusterConfiguration());
	}
	
	@Bean
	public JedisClusterConnection getJedisClusterConnection(){
		return (JedisClusterConnection) getConnectionFactory().getConnection();
	}
	
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public RedisTemplate getRedisTemplate() {
		RedisTemplate clusterTemplate = new RedisTemplate();
		clusterTemplate.setConnectionFactory(getConnectionFactory());
		clusterTemplate.setKeySerializer(new DefaultKeySerializer());
		clusterTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		return clusterTemplate;
	}
}
