package com.redis.cluster.monitor.util.convert;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.redis.cluster.monitor.model.info.Info;
import com.redis.cluster.monitor.model.info.detail.CPU;
import com.redis.cluster.monitor.model.info.detail.Clients;
import com.redis.cluster.monitor.model.info.detail.Cluster;
import com.redis.cluster.monitor.model.info.detail.Keyspace;
import com.redis.cluster.monitor.model.info.detail.Memory;
import com.redis.cluster.monitor.model.info.detail.Persistence;
import com.redis.cluster.monitor.model.info.detail.Replication;
import com.redis.cluster.monitor.model.info.detail.Server;
import com.redis.cluster.monitor.model.info.detail.Stats;

public class PropertiesToInfoConvert implements Converter<Properties, Info> {

	@Override
	public Info convert(Properties source) {
		if (source == null)
			return null;

		Info info = new Info();
		setInfoField(info, source);
		return info;
	}

	private void setInfoField(Info info, Properties source) {
		Server server = new Server();
		Clients clients = new Clients();
		Memory memory = new Memory();
		Persistence persistence = new Persistence();
		;
		Stats stats = new Stats();
		Replication replication = new Replication();
		CPU cpu = new CPU();
		Cluster cluster = new Cluster();
		Keyspace keyspace = new Keyspace();

		Enumeration<Object> keys = source.keys();
		while (keys.hasMoreElements()) {
			String key = String.valueOf(keys.nextElement());
			String value = String.valueOf(source.get(key));
			set(server, key, value);
			set(clients, key, value);
			set(memory, key, value);
			set(persistence, key, value);
			set(stats, key, value);
			set(replication, key, value);
			set(cpu, key, value);
			set(cluster, key, value);
			set(keyspace, key, value);
		}

		info.setServer(server);
		info.setClients(clients);
		info.setMemory(memory);
		info.setPersistence(persistence);
		info.setStats(stats);
		info.setReplication(replication);
		info.setCpu(cpu);
		info.setCluster(cluster);
		info.setKeyspace(keyspace);
	}

	private boolean set(Object detail, String key, String value) {
		Method method = ReflectionUtils.findMethod(detail.getClass(), "set" + capitalize(key), String.class);
		if (method != null) {
			ReflectionUtils.invokeMethod(method, detail, value);
			return true;
		}
		return false;
	}

	private String capitalize(String str) {
		if (str.startsWith("slave"))
			return "Slaves";
		return StringUtils.capitalize(str);
	}
}
