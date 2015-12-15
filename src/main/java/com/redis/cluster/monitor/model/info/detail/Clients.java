package com.redis.cluster.monitor.model.info.detail;

public class Clients {
	private String connected_clients;
	private String client_longest_output_list;
	private String client_biggest_input_buf;
	private String blocked_clients;
	
	public String getConnected_clients() {
		return connected_clients;
	}
	public void setConnected_clients(String connected_clients) {
		this.connected_clients = connected_clients;
	}
	public String getClient_longest_output_list() {
		return client_longest_output_list;
	}
	public void setClient_longest_output_list(String client_longest_output_list) {
		this.client_longest_output_list = client_longest_output_list;
	}
	public String getClient_biggest_input_buf() {
		return client_biggest_input_buf;
	}
	public void setClient_biggest_input_buf(String client_biggest_input_buf) {
		this.client_biggest_input_buf = client_biggest_input_buf;
	}
	public String getBlocked_clients() {
		return blocked_clients;
	}
	public void setBlocked_clients(String blocked_clients) {
		this.blocked_clients = blocked_clients;
	}
}
