package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"@target/failed_scenarios.txt"},
	    glue = {"stepDefinition"},
	    plugin = {"pretty", "html:target/cucumber.html",
		        "json:target/cucumber.json",
		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
			    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"rerun:target/failed_scenarios.txt"},
	    monochrome = true,
	    publish = true,
	    //tags="@login",
	    dryRun = false
	 
	)



public class failedTestRunner  extends AbstractTestNGCucumberTests  {


				   
}	
