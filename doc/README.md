# spring-data-redis cluster分支的扩展
  * jedisVersion：3.0.0-SNAPSHOT  
[https://github.com/xetorthio/jedis](https://github.com/xetorthio/jedis)  
进入jedis github源码网址, clone master后make package
  * fasterXmlJacksonDatabindVersion：2.6.1  
  * extention(仅扩展了jedis客户端)：spring-data-redis-1.6.0.DATAREDIS-315-SNAPSHOT  
[spring-data-redis-1.6.0.DATAREDIS-315](https://github.com/spring-projects/spring-data-redis/tree/issue/DATAREDIS-315)  
[Add support for RedisCluster](https://github.com/spring-projects/spring-data-redis/pull/129)  
说明：spring-data-redis为了适用集群和方便应用开发修改了部分代码([<b>doc/lib</b>](https://github.com/zhengfc/redis3.0-new/tree/master/doc/lib)下)

## 主要功能
  * 与spring-data-redis无缝融合
  * 基本隔离了集群功能操作带来业务系统api的变化
  * 支持快速简单接入以及定制化配置接入
  * 支持Cache Abstraction注解功能
  * 提供了测试Demo

## 如何使用
* **引入依赖(jackson:2.6.1，spring:4.1.6.RELEASE，jedis，pool)**：
	
	```xml
	<dependency><!-- jedis -->
	    <groupId>redis.clients</groupId>
	    <artifactId>jedis</artifactId>
	    <version>3.0.0-SNAPSHOT</version>
	</dependency>
    <dependency>
	    <groupId>org.apache.commons</groupId>
	    <artifactId>commons-pool2</artifactId>
	    <version>2.2</version>
	</dependency>
	<dependency><!-- spring -->
	  <groupId>org.springframework.data</groupId>
	  <artifactId>spring-data-redis</artifactId>
	  <version>1.6.0.DATAREDIS-315-SNAPSHOT</version>
	</dependency>
	<dependency>
	  <groupId>org.springframework</groupId>
	  <artifactId>spring-context-support</artifactId>
	  <version>${spring.version}</version>
	</dependency>
	<dependency> <!-- jackson -->
		<groupId>com.fasterxml.jackson.core</groupId>
		<artifactId>jackson-databind</artifactId>
		<version>${jackson.version}</version>
	</dependency>
	<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring.version}</version>
      <scope>test</scope>
    </dependency>
	```	
	
* **在classpath下添加配置文件redis.properties**：  
主要配置以下五个参数，连接池参数采用默认设置

	```properties
	spring.redis.cluster.nodes=10.48.193.201:7379,10.48.193.202:7380,10.48.193.203:7381
	spring.redis.cluster.timeout=2000
	spring.redis.cluster.max-redirects=5
	app-code=docker
	#expiration=3600
	
	jedis.pool.max-total=8
	jedis.pool.max-idle=8
	jedis.pool.min-idle=0
	``` 
	
* **cacheContext.xml配置如下**：
  
	```xml
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:cache="http://www.springframework.org/schema/cache"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/cache http://www.springframework.org/schema/cache/spring-cache.xsd">
		<context:property-placeholder location="classpath:redis.properties"/>
		<!--  build defaultSerializer, clusterTemplate, cacheManager-->
		<bean class="org.springframework.data.redis.app.CacheConfig" />
		<!-- 启用cache注解 -->
		<cache:annotation-driven cache-manager="cacheManager" proxy-target-class="true" />
		<!-- Cache Abstraction测试 -->
		<context:component-scan base-package="com.handpay.redis_new.service" />
	</beans>
	```	
	
* **key generator**：
默认keyGenerator支持String, Integer, Long三种类型;  
Key格式为：appCode@cacheName:key

* **api demo**：
    * Cache API 
   
    ```java
    //1. 注入CacheManager
    @Autowired CacheManager cacheManager;
    //2. 获取Cache(惰性加载)
    Cache cache = cacheManager.getCache("cacheName");
    //3. 调用API
    cache.putIfAbsent(key, value)；
    cache.put(key, value);
    cache.evict(key);
    cache.clear()；
    ```
常用API见单元测试[CacheTest.java](https://github.com/zhengfc/redis3.0-new/blob/master/src/test/java/com/handpay/redis_new/service/CacheTest.java)，详细文档请参考[spring官方文档](http://docs.spring.io/spring-data/redis/docs/1.6.0.RELEASE/api/)中的RedisCache.class及RedisCacheManager.class
	 
    * Template API  
 
	```java
	//1.注入Template
	@Autowired  RedisClusterTemplate<String, Object> template;
	//2.获取RedisOperations
	RedisClusterOperations<String, Object> clusterOps = template.opsForCluster();
	ValueOperations<String, Object> valueOps = template.opsForValue();
	ListOperations<String, Object> listOps = template.opsForList();
	SetOperations<String, Object> setOps = template.opsForSet();
	ZSetOperations<String, Object> zSetOps = template.opsForZSet();
	HashOperations<String, String, Object> hashOps = template.opsForHash();
	//3.调用API
	clusterOps.getClusterNodes();
	
	valueOps.set(k_value, "v1");
	valueOps.get(k_value);
	
	listOps.leftPushAll(k_list, new Object[] { 1, 2 });
	listOps.leftPop(k_list);
	
	setOps.add(k_set, new Object[] { 0, 1, 2 });
	zSetOps.add(k_zset, "50", 99);
	zSetOps.add(k_zset, "100", 50);
	zSetOps.incrementScore(k_zset, "100", 50);
	assertTrue(zSetOps.rank(k_zset, "50") < zSetOps.rank(k_zset, "100"));
	
	hashOps.put(k_hash, "hk", "hv");
	hashOps.get(k_hash, "hk");
	// not support
	@SuppressWarnings("deprecation")
	@Test(expected = UnsupportedOperationException.class)
	public void testUnsupported(){
		setOps.union("union1", "union2");
	}
	```
以上代码见单元测试[TemplateTest.java](https://github.com/zhengfc/redis3.0-new/blob/master/src/test/java/com/handpay/redis_new/service/TemplateTest.java)，更多API请参考[spring-data-redis官方文档](http://docs.spring.io/spring-data/redis/docs/1.6.0.RELEASE/api/)中的RedisTemplate.java  

    * Cache Abstraction  
    ```java
    @CachePut(value = "user", key = "#user.id")
    public User save(User user) { }
    @CachePut(value = "user", key = "#user.id")
    public User update(User user) { }
    
    @CacheEvict(value = "user", key = "#user.id")
    public User delete(User user) { }
    @CacheEvict(value = "user", allEntries = true)
    public void deleteAll() { }
    
    @Cacheable(value = "user", key = "#id")
    public User findById(int id) { }
    ```
以上代码见单元测试[UserServiceAnnotationTest.java](https://github.com/zhengfc/redis3.0-new/blob/master/src/test/java/com/handpay/redis_new/service/UserServiceAnnotationTest.java)，更多cache注解操作请参考[spring Cache Abstraction官方文档](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html)

## 配置说明及其它
  * 手动配置文件构建Bean
    * RedisClusterConfiguration  
    
    ```xml
    <bean id="redisClusterConfig" class="org.springframework.data.redis.connection.RedisClusterConfiguration">
		<constructor-arg value="${spring.redis.cluster.nodes}" />
		<constructor-arg value="${spring.redis.cluster.timeout}" />
		<constructor-arg value="${spring.redis.cluster.max-redirects}" />
	</bean>
    ```
    
    * JedisPoolConfig    
    ```xml
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
		<property name="maxTotal" value="${jedis.pool.max-total}" />
		<property name="maxIdle" value="${jedis.pool.max-idle}" />
		<property name="minIdle" value="${jedis.pool.min-idle}" />
	</bean>
    ```
    
    * JedisConnectionFactory
    
    ```xml
    <bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
		<constructor-arg name="clusterConfig" ref="redisClusterConfig" />
		<!--<constructor-arg name="poolConfig" ref="jedisPoolConfig" />-->
	</bean>
    ```

    * RedisClusterTemplate
    
    ```xml
    <bean id="clusterTemplate" class="org.springframework.data.redis.core.RedisClusterTemplate">
		<property name="connectionFactory" ref="jedisConnectionFactory"></property>
		<property name="keySerializer">
			<bean class="org.springframework.data.redis.serializer.DefaultKeySerializer"/>
		</property>
		<property name="defaultSerializer">
			<bean class="org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer"/>
		</property>
	</bean>
    ```
    
    * CacheManager
    
    ```xml
    <bean id="cachePrefix" class="org.springframework.data.redis.cache.DefaultRedisCachePrefix">
		<constructor-arg>
			<value>${app-code}</value>
		</constructor-arg>
	</bean>
	<bean id="cacheManager" class="org.springframework.data.redis.cache.RedisCacheManager">
		<constructor-arg name="template" ref="clusterTemplate" />
		<property name="usePrefix" value="true"/>
		<property name="cachePrefix" ref="cachePrefix"/>
	</bean>
    ```
    
  * more...  
    * [about cluster unsupported cmd](https://github.com/wandoulabs/codis/blob/master/doc/unsupported_cmds.md)  
    * [spring-data-redis api](http://docs.spring.io/spring-data/redis/docs/1.6.0.RELEASE/api/)  
    * 更多内容请参考[spring-data-redis](https://github.com/spring-projects/spring-data-redis)...