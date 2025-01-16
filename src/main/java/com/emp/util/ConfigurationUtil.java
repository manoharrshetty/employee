package com.emp.util;

/**
 * Singleton that can be used to load message properties
 * @author manoh
 *
 */
public class ConfigurationUtil extends PropertiesUtil{

	public static final ConfigurationUtil CONFIGURATION_PROPERTIES_INSTANCE = new ConfigurationUtil();

	private ConfigurationUtil() {
		super("query.properties");
	}

}
