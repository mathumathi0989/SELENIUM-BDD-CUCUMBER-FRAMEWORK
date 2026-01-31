package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepDefinition"},
	    plugin = {"pretty", "html:target/cucumber.html",
		        "json:target/cucumber.json",
		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
			    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"rerun:target/failed_scenarios.txt"},
	    monochrome = true, publish = true,
	    		  // tags="@login",
	    dryRun = false
	 
	)
public class TestRunner  extends AbstractTestNGCucumberTests  {

	 @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	 

			   
}	
