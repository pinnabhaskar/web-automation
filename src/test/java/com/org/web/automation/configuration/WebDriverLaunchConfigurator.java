package com.org.web.automation.configuration;

public class WebDriverLaunchConfigurator {
	private static final String WEB_DRIVER_LOCATION=System.getProperty("user.dir")+"/webdrivers/";
	
	public static void configure(WebBrowser browser) {
		switch (browser) {
			case FIREFOX:
				System.setProperty("webdriver.firefox.marionette", WEB_DRIVER_LOCATION+"geckodriver.exe");
				break;
			case CHROME:
				//System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", WEB_DRIVER_LOCATION+"chromedriver.exe");
				
				break;
			case IE:
				//System.setProperty("webdriver.ie.driver", "src/test/resources/webdriver/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", WEB_DRIVER_LOCATION+"IEDriverServer.exe");
				break;
			default:
				break;
		}
	}
	
	public static void configure(String browser) {
		configure(WebBrowser.fromString(browser));
	}
	
}
