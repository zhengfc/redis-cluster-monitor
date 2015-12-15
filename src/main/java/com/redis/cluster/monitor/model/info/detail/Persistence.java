package com.redis.cluster.monitor.model.info.detail;

public class Persistence {
	private String loading;
	private String rdb_changes_since_last_save;
	private String rdb_bgsave_in_progress;
	private String rdb_last_save_time;
	private String rdb_last_bgsave_status;
	private String rdb_last_bgsave_time_sec;
	private String rdb_current_bgsave_time_sec;
	private String aof_enabled;
	private String aof_rewrite_in_progress;
	private String aof_rewrite_scheduled;
	private String aof_last_rewrite_time_sec;
	private String aof_current_rewrite_time_sec;
	private String aof_last_bgrewrite_status;
	private String aof_last_write_status;
	private String aof_current_size;
	private String aof_base_size;
	private String aof_pending_rewrite;
	private String aof_buffer_length;
	private String aof_rewrite_buffer_length;
	private String aof_pending_bio_fsync;
	private String aof_delayed_fsync;
	
	public String getLoading() {
		return loading;
	}
	public void setLoading(String loading) {
		this.loading = loading;
	}
	public String getRdb_changes_since_last_save() {
		return rdb_changes_since_last_save;
	}
	public void setRdb_changes_since_last_save(String rdb_changes_since_last_save) {
		this.rdb_changes_since_last_save = rdb_changes_since_last_save;
	}
	public String getRdb_bgsave_in_progress() {
		return rdb_bgsave_in_progress;
	}
	public void setRdb_bgsave_in_progress(String rdb_bgsave_in_progress) {
		this.rdb_bgsave_in_progress = rdb_bgsave_in_progress;
	}
	public String getRdb_last_save_time() {
		return rdb_last_save_time;
	}
	public void setRdb_last_save_time(String rdb_last_save_time) {
		this.rdb_last_save_time = rdb_last_save_time;
	}
	public String getRdb_last_bgsave_status() {
		return rdb_last_bgsave_status;
	}
	public void setRdb_last_bgsave_status(String rdb_last_bgsave_status) {
		this.rdb_last_bgsave_status = rdb_last_bgsave_status;
	}
	public String getRdb_last_bgsave_time_sec() {
		return rdb_last_bgsave_time_sec;
	}
	public void setRdb_last_bgsave_time_sec(String rdb_last_bgsave_time_sec) {
		this.rdb_last_bgsave_time_sec = rdb_last_bgsave_time_sec;
	}
	public String getRdb_current_bgsave_time_sec() {
		return rdb_current_bgsave_time_sec;
	}
	public void setRdb_current_bgsave_time_sec(String rdb_current_bgsave_time_sec) {
		this.rdb_current_bgsave_time_sec = rdb_current_bgsave_time_sec;
	}
	public String getAof_enabled() {
		return aof_enabled;
	}
	public void setAof_enabled(String aof_enabled) {
		this.aof_enabled = aof_enabled;
	}
	public String getAof_rewrite_in_progress() {
		return aof_rewrite_in_progress;
	}
	public void setAof_rewrite_in_progress(String aof_rewrite_in_progress) {
		this.aof_rewrite_in_progress = aof_rewrite_in_progress;
	}
	public String getAof_rewrite_scheduled() {
		return aof_rewrite_scheduled;
	}
	public void setAof_rewrite_scheduled(String aof_rewrite_scheduled) {
		this.aof_rewrite_scheduled = aof_rewrite_scheduled;
	}
	public String getAof_last_rewrite_time_sec() {
		return aof_last_rewrite_time_sec;
	}
	public void setAof_last_rewrite_time_sec(String aof_last_rewrite_time_sec) {
		this.aof_last_rewrite_time_sec = aof_last_rewrite_time_sec;
	}
	public String getAof_current_rewrite_time_sec() {
		return aof_current_rewrite_time_sec;
	}
	public void setAof_current_rewrite_time_sec(String aof_current_rewrite_time_sec) {
		this.aof_current_rewrite_time_sec = aof_current_rewrite_time_sec;
	}
	public String getAof_last_bgrewrite_status() {
		return aof_last_bgrewrite_status;
	}
	public void setAof_last_bgrewrite_status(String aof_last_bgrewrite_status) {
		this.aof_last_bgrewrite_status = aof_last_bgrewrite_status;
	}
	public String getAof_last_write_status() {
		return aof_last_write_status;
	}
	public void setAof_last_write_status(String aof_last_write_status) {
		this.aof_last_write_status = aof_last_write_status;
	}
	public String getAof_current_size() {
		return aof_current_size;
	}
	public void setAof_current_size(String aof_current_size) {
		this.aof_current_size = aof_current_size;
	}
	public String getAof_base_size() {
		return aof_base_size;
	}
	public void setAof_base_size(String aof_base_size) {
		this.aof_base_size = aof_base_size;
	}
	public String getAof_pending_rewrite() {
		return aof_pending_rewrite;
	}
	public void setAof_pending_rewrite(String aof_pending_rewrite) {
		this.aof_pending_rewrite = aof_pending_rewrite;
	}
	public String getAof_buffer_length() {
		return aof_buffer_length;
	}
	public void setAof_buffer_length(String aof_buffer_length) {
		this.aof_buffer_length = aof_buffer_length;
	}
	public String getAof_rewrite_buffer_length() {
		return aof_rewrite_buffer_length;
	}
	public void setAof_rewrite_buffer_length(String aof_rewrite_buffer_length) {
		this.aof_rewrite_buffer_length = aof_rewrite_buffer_length;
	}
	public String getAof_pending_bio_fsync() {
		return aof_pending_bio_fsync;
	}
	public void setAof_pending_bio_fsync(String aof_pending_bio_fsync) {
		this.aof_pending_bio_fsync = aof_pending_bio_fsync;
	}
	public String getAof_delayed_fsync() {
		return aof_delayed_fsync;
	}
	public void setAof_delayed_fsync(String aof_delayed_fsync) {
		this.aof_delayed_fsync = aof_delayed_fsync;
	}
}
