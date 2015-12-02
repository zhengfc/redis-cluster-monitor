package com.redis.cluster.monitor.util.context;

public class RuntimeContainer {
	private static ThreadLocal<String> code = new ThreadLocal<String>();
	private static ThreadLocal<Object> message = new ThreadLocal<Object>();
	
	public static String getRetCode() {
		return code.get();
	}
	public static void setRetCode(String retCode) {
		code.set(retCode);;
	}
	public static Object getRetMessage() {
		return message.get();
	}
	public static void setRetMessage(Object retMessage) {
		message.set(retMessage);;
	}
}
