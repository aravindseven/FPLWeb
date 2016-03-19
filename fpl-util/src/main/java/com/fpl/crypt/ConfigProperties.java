package com.fpl.crypt;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigProperties {

	private final Properties configProperties;
	
	protected ConfigProperties(final String resourceBundleName) {
		this(ResourceBundle.getBundle(resourceBundleName));
	}
	
	protected ConfigProperties(final ResourceBundle resourceBundle) {
		this.configProperties = createConfigProperties(resourceBundle);
	}
	
	private Properties getConfigProperties() {
		return configProperties;
	}
	
	public Properties createConfigProperties(final ResourceBundle resourceBundle) {
		final Properties properties = new Properties();
		loadFromResourceBundle(resourceBundle, properties);
		return properties;
	}
	
	private void loadFromResourceBundle(final ResourceBundle resourceBundle, final Properties properties) {
		final List<String> keys = Collections.list(resourceBundle.getKeys());
		for(final String key : keys) {
			properties.setProperty(key, resourceBundle.getObject(key).toString());
		}
	}
	
	public String getProperty(final String key) {
		return getConfigProperties().getProperty(key);
	}
}
