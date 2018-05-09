package com.org.web.automation.configuration;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<HashMap<String, WebDriver>> map = new ThreadLocal<HashMap<String, WebDriver>>();	
	
	@Bean
	public static synchronized WebDriver getDriver() {
    	if (driver.get() != null)
    		return driver.get();
    	
    	String browser = System.getProperty("browser");
    	System.out.println("browser:"+browser);
    	if (browser == null)
    		browser = ConfigRegistry.getConfig().getBrowser();
    	
    	if (!WebBrowser.supported(browser))
    		throw new IllegalWebDriverTypeException("Browser type invalid or unspecified. "
    				+ "Please specify a browser in your command line arguments "
    				+ "-Dbrowser=browserName "
    				+ "or specify a browser in your properties file");

    	return createDriver(browser);
    }
	
	public static synchronized WebDriver getDriver(String key) {
		return map.get().get(key);
	}
	
	public static synchronized WebDriver createDriver(String key, String browser) {
		WebDriver d = createDriver(browser);
		map.get().put(key, d);
		return d;
	}
	
	public static synchronized WebDriver createDriver(String browser) {
		WebDriverInstantiator inst = new WebDriverInstantiator(browser);
		WebDriver d = inst.get();
		System.out.println("driver details:>>"+driver.get());
    	driver.set(d);
    	return d;
	}
	
	public static synchronized JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) getDriver();
    }

	public static synchronized JavascriptExecutor getExecutor(String key) {
        return (JavascriptExecutor) getDriver(key);
    }
	
	public static boolean exists() {
		return driver.get() != null;
	}
	
	public static boolean exists(String key) {
		return map.get() != null 
				&& map.get().containsKey(key)
				&& map.get().get(key) != null;
	}
	
}
