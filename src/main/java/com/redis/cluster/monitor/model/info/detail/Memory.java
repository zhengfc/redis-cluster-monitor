package com.redis.cluster.monitor.model.info.detail;

public class Memory {
	private String used_memory;
	private String used_memory_human;
	private String used_memory_rss;
	private String used_memory_peak;
	private String used_memory_peak_human;
	private String used_memory_lua;
	private String mem_fragmentation_ratio;
	private String mem_allocator;
	
	public String getUsed_memory() {
		return used_memory;
	}
	public void setUsed_memory(String used_memory) {
		this.used_memory = used_memory;
	}
	public String getUsed_memory_human() {
		return used_memory_human;
	}
	public void setUsed_memory_human(String used_memory_human) {
		this.used_memory_human = used_memory_human;
	}
	public String getUsed_memory_rss() {
		return used_memory_rss;
	}
	public void setUsed_memory_rss(String used_memory_rss) {
		this.used_memory_rss = used_memory_rss;
	}
	public String getUsed_memory_peak() {
		return used_memory_peak;
	}
	public void setUsed_memory_peak(String used_memory_peak) {
		this.used_memory_peak = used_memory_peak;
	}
	public String getUsed_memory_peak_human() {
		return used_memory_peak_human;
	}
	public void setUsed_memory_peak_human(String used_memory_peak_human) {
		this.used_memory_peak_human = used_memory_peak_human;
	}
	public String getUsed_memory_lua() {
		return used_memory_lua;
	}
	public void setUsed_memory_lua(String used_memory_lua) {
		this.used_memory_lua = used_memory_lua;
	}
	public String getMem_fragmentation_ratio() {
		return mem_fragmentation_ratio;
	}
	public void setMem_fragmentation_ratio(String mem_fragmentation_ratio) {
		this.mem_fragmentation_ratio = mem_fragmentation_ratio;
	}
	public String getMem_allocator() {
		return mem_allocator;
	}
	public void setMem_allocator(String mem_allocator) {
		this.mem_allocator = mem_allocator;
	}
}
