package com.redis.cluster.monitor.service.cmd;

import org.springframework.data.redis.connection.RedisNode;

public interface CmdService {
	//cron
	void cronInfo(RedisNode node, String ... cmds);
}
