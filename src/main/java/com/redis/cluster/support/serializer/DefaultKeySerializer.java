/*
 * Copyright 2011-2013 the original author or authors.
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
package com.redis.cluster.support.serializer;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

/**
 * Simple String to byte[] (and back) serializer. Converts Strings into bytes and vice-versa using the specified charset
 * (by default UTF-8).
 * <p>
 * Useful when the interaction with the Redis happens mainly through Strings.
 * <p>
 * Does not perform any null conversion since empty strings are valid keys/values.
 * 
 * @author Costin Leau
 */
public class DefaultKeySerializer implements RedisSerializer<Object> {

	private final Charset charset;
	private ThreadLocal<Class<?>> clazz = new ThreadLocal<Class<?>>();;

	public DefaultKeySerializer() {
		this(Charset.forName("UTF8"));
	}

	public DefaultKeySerializer(Charset charset) {
		Assert.notNull(charset);
		this.charset = charset;
	}

	@Override
	public Object deserialize(byte[] bytes) {
		String rawString = (bytes == null ? null : new String(bytes, charset));
		if(clazz.get().equals(Integer.class)){
			return Integer.parseInt(rawString);
		}
		if(clazz.get().equals(Long.class)){
			return Long.parseLong(rawString);
		}
		return rawString;
	}

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		clazz.set(t.getClass());
		if(clazz.get().equals(String.class) || clazz.get().equals(Long.class) || clazz.get().equals(Integer.class) ){
			String string = String.valueOf(t);
			return (string == null ? null : string.getBytes(charset));
		}
		throw new SerializationException("Key just support String, Integer, Long!");
	}
}
