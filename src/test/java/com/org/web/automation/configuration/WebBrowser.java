package com.org.web.automation.configuration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum WebBrowser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie"),
    SAFARI("safari");

    private String text;

    WebBrowser(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static WebBrowser fromString(String text) {
        for (WebBrowser b : WebBrowser.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return getDefault();
    }
    
    private static WebBrowser getDefault() {
    	return fromString(ConfigRegistry.getConfig().getBrowser());
    }
    
    public Class<?> getTypeName() {
    	switch (this) {
	    	case CHROME:
	    		return ChromeDriver.class;
	    	case FIREFOX:
	    		return FirefoxDriver.class;
	    	case SAFARI:
	    		return SafariDriver.class;
	    	case IE:
	    		return InternetExplorerDriver.class;
	    	default:
	    		return null;
    	}
    }
    
    public static boolean supported(String browser) {
    	return fromString(browser) != null;
    }
    
}
