package com.emp.util;

import java.io.InputStream;
import java.util.Properties;
/**
 * Singleton that can be used to load properties
 * @author manoh
 *
 */
public abstract class PropertiesUtil {

	
	private final Properties properties = new Properties();

	protected PropertiesUtil(String resourceFileName) {
		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(resourceFileName)) {

			if (input == null) {
				throw new RuntimeException("Properties " + resourceFileName  + " not found");

			}

			// load a properties file from class path, inside static method
			properties.load(input);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public  String getMessage(String messageKey) {
		return properties.getProperty(messageKey);
	}

}
