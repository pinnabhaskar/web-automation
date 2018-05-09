package com.org.web.automation.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverInstantiator {

	private WebBrowser b;
	
	public WebDriverInstantiator(WebBrowser b) {
		this.b = b;
	}
	
	public WebDriverInstantiator(String browser) {
		this(WebBrowser.fromString(browser));
	}
	
	public WebDriver get() {
		return create(String.valueOf(b).toLowerCase());
	}
	
	private static WebDriver create(String browser) {
    	browser = browser.trim().toLowerCase();
    	WebDriver d = null;
    	
    	configure(browser);
    	
    	switch (browser) {
	    	case "firefox":
	    	case "ff":
	    		d = getFirefoxDriver();
	    		break;
	    		
	    	case "chrome":
	    		d = getChromeDriver();
	    		break;
	    		
	    	case "internetexplorer":
	    	case "ie":
	    		d = getInternetExplorerDriver();
	    		break;
	    		
    		default:
    			d = getFirefoxDriver();
    			break;
    	}
    	
    	return d;
    }
    
    private static void configure(String browser) {
    	WebDriverLaunchConfigurator.configure(browser);
    }

    private static WebDriver getChromeDriver() {
    	return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

    private static WebDriver getInternetExplorerDriver() {
        return new InternetExplorerDriver();
    }

}
