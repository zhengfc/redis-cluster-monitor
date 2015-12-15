package com.redis.cluster.monitor.model.info.detail;

public class Stats {
	private String total_connections_received;
	private String total_commands_processed;
	private String instantaneous_ops_per_sec;
	private String total_net_input_bytes;
	private String total_net_output_bytes;
	private String instantaneous_input_kbps;
	private String instantaneous_output_kbps;
	private String rejected_connections;
	private String sync_full;
	private String sync_partial_ok;
	private String sync_partial_err;
	private String expired_keys;
	private String evicted_keys;
	private String keyspace_hits;
	private String keyspace_misses;
	private String pubsub_channels;
	private String pubsub_patterns;
	private String latest_fork_usec;
	private String migrate_cached_sockets;
	
	public String getTotal_connections_received() {
		return total_connections_received;
	}
	public void setTotal_connections_received(String total_connections_received) {
		this.total_connections_received = total_connections_received;
	}
	public String getTotal_commands_processed() {
		return total_commands_processed;
	}
	public void setTotal_commands_processed(String total_commands_processed) {
		this.total_commands_processed = total_commands_processed;
	}
	public String getInstantaneous_ops_per_sec() {
		return instantaneous_ops_per_sec;
	}
	public void setInstantaneous_ops_per_sec(String instantaneous_ops_per_sec) {
		this.instantaneous_ops_per_sec = instantaneous_ops_per_sec;
	}
	public String getTotal_net_input_bytes() {
		return total_net_input_bytes;
	}
	public void setTotal_net_input_bytes(String total_net_input_bytes) {
		this.total_net_input_bytes = total_net_input_bytes;
	}
	public String getTotal_net_output_bytes() {
		return total_net_output_bytes;
	}
	public void setTotal_net_output_bytes(String total_net_output_bytes) {
		this.total_net_output_bytes = total_net_output_bytes;
	}
	public String getInstantaneous_input_kbps() {
		return instantaneous_input_kbps;
	}
	public void setInstantaneous_input_kbps(String instantaneous_input_kbps) {
		this.instantaneous_input_kbps = instantaneous_input_kbps;
	}
	public String getInstantaneous_output_kbps() {
		return instantaneous_output_kbps;
	}
	public void setInstantaneous_output_kbps(String instantaneous_output_kbps) {
		this.instantaneous_output_kbps = instantaneous_output_kbps;
	}
	public String getRejected_connections() {
		return rejected_connections;
	}
	public void setRejected_connections(String rejected_connections) {
		this.rejected_connections = rejected_connections;
	}
	public String getSync_full() {
		return sync_full;
	}
	public void setSync_full(String sync_full) {
		this.sync_full = sync_full;
	}
	public String getSync_partial_ok() {
		return sync_partial_ok;
	}
	public void setSync_partial_ok(String sync_partial_ok) {
		this.sync_partial_ok = sync_partial_ok;
	}
	public String getSync_partial_err() {
		return sync_partial_err;
	}
	public void setSync_partial_err(String sync_partial_err) {
		this.sync_partial_err = sync_partial_err;
	}
	public String getExpired_keys() {
		return expired_keys;
	}
	public void setExpired_keys(String expired_keys) {
		this.expired_keys = expired_keys;
	}
	public String getEvicted_keys() {
		return evicted_keys;
	}
	public void setEvicted_keys(String evicted_keys) {
		this.evicted_keys = evicted_keys;
	}
	public String getKeyspace_hits() {
		return keyspace_hits;
	}
	public void setKeyspace_hits(String keyspace_hits) {
		this.keyspace_hits = keyspace_hits;
	}
	public String getKeyspace_misses() {
		return keyspace_misses;
	}
	public void setKeyspace_misses(String keyspace_misses) {
		this.keyspace_misses = keyspace_misses;
	}
	public String getPubsub_channels() {
		return pubsub_channels;
	}
	public void setPubsub_channels(String pubsub_channels) {
		this.pubsub_channels = pubsub_channels;
	}
	public String getPubsub_patterns() {
		return pubsub_patterns;
	}
	public void setPubsub_patterns(String pubsub_patterns) {
		this.pubsub_patterns = pubsub_patterns;
	}
	public String getLatest_fork_usec() {
		return latest_fork_usec;
	}
	public void setLatest_fork_usec(String latest_fork_usec) {
		this.latest_fork_usec = latest_fork_usec;
	}
	public String getMigrate_cached_sockets() {
		return migrate_cached_sockets;
	}
	public void setMigrate_cached_sockets(String migrate_cached_sockets) {
		this.migrate_cached_sockets = migrate_cached_sockets;
	}
}
