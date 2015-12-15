package com.redis.cluster.monitor.model.info.detail;

import java.util.HashMap;
import java.util.Map;

public class Replication {
	private String role;
	private String connected_slaves;
	private Map<String, String> slaves = new HashMap<String, String>();
    private String master_repl_offset;
    private String repl_backlog_active;
    private String repl_backlog_size;
    private String repl_backlog_first_byte_offset;
    private String repl_backlog_histlen;
    
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getConnected_slaves() {
		return connected_slaves;
	}
	public void setConnected_slaves(String connected_slaves) {
		this.connected_slaves = connected_slaves;
	}
	public Map<String, String> getSlaves() {
		return slaves;
	}
	public void setSlaves(String slave) {
		this.slaves.put("slave"+slaves.size(), slave);
	}
	public String getMaster_repl_offset() {
		return master_repl_offset;
	}
	public void setMaster_repl_offset(String master_repl_offset) {
		this.master_repl_offset = master_repl_offset;
	}
	public String getRepl_backlog_active() {
		return repl_backlog_active;
	}
	public void setRepl_backlog_active(String repl_backlog_active) {
		this.repl_backlog_active = repl_backlog_active;
	}
	public String getRepl_backlog_size() {
		return repl_backlog_size;
	}
	public void setRepl_backlog_size(String repl_backlog_size) {
		this.repl_backlog_size = repl_backlog_size;
	}
	public String getRepl_backlog_first_byte_offset() {
		return repl_backlog_first_byte_offset;
	}
	public void setRepl_backlog_first_byte_offset(String repl_backlog_first_byte_offset) {
		this.repl_backlog_first_byte_offset = repl_backlog_first_byte_offset;
	}
	public String getRepl_backlog_histlen() {
		return repl_backlog_histlen;
	}
	public void setRepl_backlog_histlen(String repl_backlog_histlen) {
		this.repl_backlog_histlen = repl_backlog_histlen;
	}
}
