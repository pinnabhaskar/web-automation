/**
 * 
 */
package com.org.web.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.org.web.automation.configuration.ProjectConfig;

/**
 * @author bpinna
 *
 */
public class CommonWebElementsPage {
	
	WebDriver webDriver;
	@Autowired
	private ProjectConfig projectConfigBean;

	public CommonWebElementsPage(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}
	
	public CommonWebElementsPage(WebDriver webDriver, ProjectConfig projectConfigBean) {
		super();
		this.webDriver = webDriver;
		this.projectConfigBean = projectConfigBean;
	}

	
	
/* common code for clicking on the button by button name on the web page*/
	
public void clickOnByButtonName(String buttonName) {
	System.out.println("wd:"+webDriver);
		webDriver.findElement(By.xpath("//button[contains(text(),'"+buttonName+"')]")).click();
		
	}
	

/* common code for clicking on the anchor tag by name on web page */
public void clickOnAnchorTagByName(String anchorTagName) {
	
	webDriver.findElement(By.xpath("//a[contains(text(),'"+anchorTagName+"')]")).click();
	
}
/* common code for clicking on the anchor tag by name */
	public void clickOnAnchorTagByLinkText(String anchorTagLink) {
		webDriver.findElement(By.linkText(anchorTagLink)).click();

	}
	
	/* common code for entering the TextField Data on web page by id */
	public void enterTextFieldData(String inputId ,String inputData) {
		
		webDriver.findElement(By.xpath("//INPUT[@id='"+inputId+"']")).sendKeys(inputData);
	}
		

	/* logging process for the  application */
	public void launchApplication() throws Exception {

	webDriver.manage().window().maximize();
	System.out.println("bean value:"+projectConfigBean.getEnvUrl());
	webDriver.get(projectConfigBean.getEnvUrl());
    Thread.sleep(5000);
  /*  webDriver.findElement(By.xpath("//INPUT[@id='id']")).sendKeys(projectConfigBean.getUserAdminName());
    webDriver.findElement(By.xpath("//INPUT[@id='id']")).sendKeys(projectConfigBean.getUserAdminPassword());
	webDriver.findElement(By.xpath("//BUTTON[@type='button'][text()='Login']")).click();*/
	Thread.sleep(3000);
	}

}
