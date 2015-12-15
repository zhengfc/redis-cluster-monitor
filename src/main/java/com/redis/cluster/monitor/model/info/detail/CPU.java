package com.redis.cluster.monitor.model.info.detail;

public class CPU {
	private String used_cpu_sys;
	private String used_cpu_user;
	private String used_cpu_sys_children;
	private String used_cpu_user_children;
	
	public String getUsed_cpu_sys() {
		return used_cpu_sys;
	}
	public void setUsed_cpu_sys(String used_cpu_sys) {
		this.used_cpu_sys = used_cpu_sys;
	}
	public String getUsed_cpu_user() {
		return used_cpu_user;
	}
	public void setUsed_cpu_user(String used_cpu_user) {
		this.used_cpu_user = used_cpu_user;
	}
	public String getUsed_cpu_sys_children() {
		return used_cpu_sys_children;
	}
	public void setUsed_cpu_sys_children(String used_cpu_sys_children) {
		this.used_cpu_sys_children = used_cpu_sys_children;
	}
	public String getUsed_cpu_user_children() {
		return used_cpu_user_children;
	}
	public void setUsed_cpu_user_children(String used_cpu_user_children) {
		this.used_cpu_user_children = used_cpu_user_children;
	}
}
