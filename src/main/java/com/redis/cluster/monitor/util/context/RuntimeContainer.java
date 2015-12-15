package com.redis.cluster.monitor.util.context;

public class RuntimeContainer {
	private static ThreadLocal<Object> message = new ThreadLocal<Object>();
	
	public static Object getRetMessage() {
		return message.get();
	}
	public static void setRetMessage(Object retMessage) {
		message.set(retMessage);;
	}
	
	public static Object getErrorMessage(String cause) {
		message.set(ErrorMessage.create(cause));
		return message.get();
	}
}
