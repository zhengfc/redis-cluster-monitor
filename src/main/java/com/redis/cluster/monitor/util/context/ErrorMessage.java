package com.redis.cluster.monitor.util.context;

/**
 * @author fczheng
 *
 */
public class ErrorMessage {
	final String ERROR = "error";
	private static ErrorMessage errorMessage = new ErrorMessage();
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
	
	public static ErrorMessage create(String cause) {
		errorMessage.setCause(cause);
		return errorMessage;
	}
}
