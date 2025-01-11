package com.emp.util;

/**
 * Singleton that can be used to load message properties
 * @author manoh
 *
 */
public class MessageUtil extends PropertiesUtil{

	public static final MessageUtil MESSAGE_PROPERTIES_INSTANCE = new MessageUtil();

	private MessageUtil() {
		super("message.properties");
	}

}
