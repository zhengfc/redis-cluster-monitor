package com.redis.cluster.monitor.model.info.detail;

public class Server {
	private String redis_version;
	private String redis_git_sha1;
	private String redis_git_dirty;
	private String redis_build_id;
	private String redis_mode;
	private String os;
	private String arch_bits;
	private String multiplexing_api;
	private String gcc_version;
	private String process_id;
	private String run_id;
	private String tcp_port;
	private String uptime_in_seconds;
	private String uptime_in_days;
	private String hz;
	private String lru_clock;
	private String config_file;
	
	public String getRedis_version() {
		return redis_version;
	}
	public void setRedis_version(String redis_version) {
		this.redis_version = redis_version;
	}
	public String getRedis_git_sha1() {
		return redis_git_sha1;
	}
	public void setRedis_git_sha1(String redis_git_sha1) {
		this.redis_git_sha1 = redis_git_sha1;
	}
	public String getRedis_git_dirty() {
		return redis_git_dirty;
	}
	public void setRedis_git_dirty(String redis_git_dirty) {
		this.redis_git_dirty = redis_git_dirty;
	}
	public String getRedis_build_id() {
		return redis_build_id;
	}
	public void setRedis_build_id(String redis_build_id) {
		this.redis_build_id = redis_build_id;
	}
	public String getRedis_mode() {
		return redis_mode;
	}
	public void setRedis_mode(String redis_mode) {
		this.redis_mode = redis_mode;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getArch_bits() {
		return arch_bits;
	}
	public void setArch_bits(String arch_bits) {
		this.arch_bits = arch_bits;
	}
	public String getMultiplexing_api() {
		return multiplexing_api;
	}
	public void setMultiplexing_api(String multiplexing_api) {
		this.multiplexing_api = multiplexing_api;
	}
	public String getGcc_version() {
		return gcc_version;
	}
	public void setGcc_version(String gcc_version) {
		this.gcc_version = gcc_version;
	}
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getRun_id() {
		return run_id;
	}
	public void setRun_id(String run_id) {
		this.run_id = run_id;
	}
	public String getTcp_port() {
		return tcp_port;
	}
	public void setTcp_port(String tcp_port) {
		this.tcp_port = tcp_port;
	}
	public String getUptime_in_seconds() {
		return uptime_in_seconds;
	}
	public void setUptime_in_seconds(String uptime_in_seconds) {
		this.uptime_in_seconds = uptime_in_seconds;
	}
	public String getUptime_in_days() {
		return uptime_in_days;
	}
	public void setUptime_in_days(String uptime_in_days) {
		this.uptime_in_days = uptime_in_days;
	}
	public String getHz() {
		return hz;
	}
	public void setHz(String hz) {
		this.hz = hz;
	}
	public String getLru_clock() {
		return lru_clock;
	}
	public void setLru_clock(String lru_clock) {
		this.lru_clock = lru_clock;
	}
	public String getConfig_file() {
		return config_file;
	}
	public void setConfig_file(String config_file) {
		this.config_file = config_file;
	}
}
