package com.redis.cluster.support.connection.jedis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.redis.connection.RedisClusterCommands.AddSlots;
import org.springframework.data.redis.connection.RedisClusterNode;

import com.redis.cluster.support.AddModes;

/*
 * Copyright 2013-2015 the original author or authors.
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


/**
 * Jedis type converters
 * 
 * @author Jennifer Hickey
 * @author Christoph Strobl
 * @author Thomas Darimont
 * @author Jungtaek Lim
 */
abstract public class JedisConverters extends org.springframework.data.redis.connection.jedis.JedisConverters {
	
	public static AddSlots toAddSlots(AddModes mode) {
		switch (mode) {
			case MIGRATING:
				return AddSlots.MIGRATING;
			case IMPORTING:
				return AddSlots.IMPORTING;
			case STABLE:
				return AddSlots.STABLE;
			case NODE:
				return AddSlots.NODE;
			default:
				throw new IllegalArgumentException();
		}
	}
	
	public static Set<RedisClusterNode> toSetNodes(Iterable<RedisClusterNode> nodes) {
		Set<RedisClusterNode> clusterNodes = new HashSet<RedisClusterNode>();
		for(RedisClusterNode node: nodes){
			clusterNodes.add(node);
		}
		return clusterNodes;
	}
}
