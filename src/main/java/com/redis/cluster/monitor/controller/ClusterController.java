package com.redis.cluster.monitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusterController {
	@RequestMapping(value = "/cluster/info", method = RequestMethod.GET)
	public Object clusterInfo() {
		return null;
	}

	@RequestMapping(value = "/cluster/nodes", method = RequestMethod.GET)
	public Object clusterNodes() {
		return null;
	}

	@RequestMapping(value = "/cluster/slots", method = RequestMethod.GET)
	public Object clusterSlots() {
		return null;
	}
	
	@RequestMapping(value = "/nodes/info", method = RequestMethod.GET)
	public Object infos() {
		return null;
	}
	
	@RequestMapping(value = "/node/info", method = RequestMethod.GET)
	public Object info() {
		return null;
	}
}
