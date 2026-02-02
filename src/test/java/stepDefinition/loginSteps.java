package stepDefinition;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import testContext.TestContextSetup;
import utils.ExcelReader;

public class loginSteps {
	
	private LoginPage loginPage;
	private TestContextSetup context;
	
	public loginSteps(TestContextSetup context) {
		this.context = context;
		this.loginPage = context.getLoginPage();
	}
	
	
	@Given("Enter the username and password with {string} details")
	public void enter_the_username_and_password_with_details(String scenario) {
	    Map<String, String> mapData = null;
	    try {
	        mapData = ExcelReader.getRowData("Login", scenario);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	   if (mapData == null) {
	        throw new RuntimeException("No data found in Excel for scenario: " + scenario);
	    }
		   if(scenario.equalsIgnoreCase("valid")) {
			   loginPage.enterUsernameAndPwd(context.getPropUsername(),context.getPropPassword());
		   }else {
			   loginPage.enterUsernameAndPwd(mapData.get("username"), mapData.get("password"));
			   }	    
	  
		}	
	
	@When("click the Login button")
	public void click_the_login_button() {
		loginPage.clickLogin();
	}
	
	
	@Then("verify the appropriate result displayed for {string}>")
	public void verify_the_appropriate_result_displayed_for(String scenario) {
		   boolean displayed = loginPage.dashboardPageDisplay();

//		    if(scenario.equalsIgnoreCase("valid")) {
//		        Assert.assertTrue(displayed);
//		    } else {
//		        Assert.assertFalse(displayed);
//		    }
	}

}
