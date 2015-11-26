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
package com.redis.cluster.support.connection.jedis;

import com.redis.cluster.support.connection.RedisClusterCommands.AddSlots;
import com.redis.cluster.support.core.RedisClusterOperations.AddModes;

/**
 * Jedis type converters
 * 
 * @author Jennifer Hickey
 * @author Christoph Strobl
 * @author Thomas Darimont
 * @author Jungtaek Lim
 */
public class JedisConverters {

	
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
}
