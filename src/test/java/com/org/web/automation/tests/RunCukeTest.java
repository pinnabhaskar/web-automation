package com.org.web.automation.tests;



import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/cucumber/cucumber-html-report",   
			 "json:target/cucumber/cucumber.json"
		     },
		tags={"~@wip"},
		monochrome=true,
		features = "classpath:features",
		glue = {"com.org.web.automation.stepdefs"}
)
public class RunCukeTest { 	
}
