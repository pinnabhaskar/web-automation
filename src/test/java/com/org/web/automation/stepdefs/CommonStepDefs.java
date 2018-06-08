/**
 * 
 */
package com.org.web.automation.stepdefs;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.org.web.automation.configuration.ConfigRegistry;
import com.org.web.automation.configuration.ProjectConfig;
import com.org.web.automation.configuration.WebDriverFactory;
import com.org.web.automation.pages.CommonWebElementsPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;


/**
 * @author bpinna
 *
 */
public class CommonStepDefs {
    
	WebDriver webDriver;
	
	CommonWebElementsPage commonPage;
	@Autowired
	WebDriverFactory webDriverFactory;
	@Autowired
	ConfigRegistry configRegistry;
	ProjectConfig projectConfigBean;

	@Before
	public void setUp(){
		if(System.getProperty("browser")!=null) {
			System.setProperty("browser", System.getProperty("browser"));
				}
		else {
			System.setProperty("browser", "chrome");
		}
		webDriver= WebDriverFactory.createDriver(System.getProperty("browser"));

		System.out.println("webdriver before>>>"+webDriver);
		projectConfigBean=ConfigRegistry.getConfig(System.getProperty("env"));
	  	System.out.println("Bean values:"+projectConfigBean.getEnvUrl());
		commonPage = new CommonWebElementsPage(webDriver,projectConfigBean);
		System.out.println("webdriver commonPage>>>"+commonPage);
		
		
	}
	
	
	
	@Given("^wait for \"([^\"]*)\" seconds$")
	public void click_on_button(String waitTime) throws Throwable {
		Thread.sleep(Long.parseLong(waitTime));
	}

	@Given("^when user launch the application$")
	public void when_user_launch_the_application() throws Throwable {
		commonPage.launchApplication();
	}

	
	@After
	public void tearDown() {
		webDriver.quit();
	}

}
