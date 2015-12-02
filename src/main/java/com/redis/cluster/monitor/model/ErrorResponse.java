package com.redis.cluster.monitor.model;

/**
 * @author fczheng
 *
 */
public class ErrorResponse {
	final String ERROR = "error";
	private String cause;

	public String getERROR() {
		return ERROR;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}
