package com.org.web.automation.configuration;

import java.util.Properties;

public interface IPropertiesDefinitionsContainer {

	Properties getProperties();
	void setProperties(Properties p);
	String getProperty(String key);
	Object getValue(String key);
	
}
