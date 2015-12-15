package com.redis.cluster.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redis.cluster.monitor.service.cluster.ClusterService;
import com.redis.cluster.monitor.util.context.RuntimeContainer;

@RestController
public class ClusterController {
	@Autowired private ClusterService clusterService;
	
	@RequestMapping(value = "/cluster/info", method = RequestMethod.GET)
	public Object clusterInfo() {
		clusterService.info();
		return RuntimeContainer.getRetMessage();
	}

	@RequestMapping(value = "/cluster/nodes", method = RequestMethod.GET)
	public Object clusterNodes() {
		clusterService.nodes();
		return RuntimeContainer.getRetMessage();
	}

	@RequestMapping(value = "/cluster/slots", method = RequestMethod.GET)
	public Object clusterSlots() {
		clusterService.slots();
		return RuntimeContainer.getRetMessage();
	}
	
	@RequestMapping(value = "/nodes/info", method = RequestMethod.GET)
	public Object infos() {
		clusterService.nodesInfo();
		return RuntimeContainer.getRetMessage();
	}
	
	@RequestMapping(value = "/{node}/info", method = RequestMethod.GET)
	public Object info(@PathVariable("node") String node ) {
		clusterService.nodeInfo(node);
		return RuntimeContainer.getRetMessage();
	}
}
